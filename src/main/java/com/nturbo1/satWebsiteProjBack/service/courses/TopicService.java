package com.nturbo1.satWebsiteProjBack.service.courses;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nturbo1.satWebsiteProjBack.repository.courses.CourseSectionRepository;
import com.nturbo1.satWebsiteProjBack.repository.courses.TopicRepository;
import com.nturbo1.satWebsiteProjBack.repository.entities.courses.CourseSection;
import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Topic;
import com.nturbo1.satWebsiteProjBack.service.courses.utils.CourseRelatedEntitiesBeforeCRUDCheck;
import com.nturbo1.satWebsiteProjBack.service.courses.utils.ExistingCourseRelatedEntities;
import com.nturbo1.satWebsiteProjBack.service.dto.request.courses.TopicRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.courses.TopicResponseDto;
import com.nturbo1.satWebsiteProjBack.service.mapper.courses.TopicMapper;

import lombok.Data;

@Service
@Data
public class TopicService {
	
	private final TopicRepository topicRepository;
	private final CourseSectionRepository courseSectionRepository;
	
	private final TopicMapper topicMapper;
	
	private final CourseRelatedEntitiesBeforeCRUDCheck courseRelatedEntitiesBeforeCRUDCheck;

	public TopicResponseDto createTopic(Long courseId, Long courseSectionId, 
			TopicRequestDto topicRequestDto) {
		
		// Checks if the parents course and courseSection exists.
		// Also if the parent course has the parent courseSection.
		ExistingCourseRelatedEntities existingCourseAndCourseSection = 
				courseRelatedEntitiesBeforeCRUDCheck
				.returnExistingCourseAndCourseSection(
						courseId, courseSectionId);	
		
		Topic newTopic = topicMapper.map(topicRequestDto);
		CourseSection updatedCourseSection = 
				existingCourseAndCourseSection.getExistingCourseSection();
		
		newTopic.setCourseSection(updatedCourseSection);
		
		if (updatedCourseSection.getTopics() == null) {
			updatedCourseSection.setTopics(new ArrayList<Topic>());			
		}
		
		updatedCourseSection.getTopics().add(newTopic);
		
		return topicMapper.map(topicRepository.save(newTopic));
	}

	public List<TopicResponseDto> getAllTopics() {
		return topicMapper.mapToTopicResponseDtoList(
				topicRepository.findAll());
	}
	
	public List<TopicResponseDto> getAllTopicsByCourseId(Long courseId) {
		
		// Checks if the parent course exists.
		courseRelatedEntitiesBeforeCRUDCheck.returnExistingCourse(courseId); 
		
		return topicMapper.mapToTopicResponseDtoList(
				topicRepository.findAllTopicsByCourseId(courseId));
	}
	
	public List<TopicResponseDto> getAllTopicsByCourseIdAndCourseSectionId(Long courseId, 
			Long courseSectionId) {
		
		// Check if the parents course and courseSection exist.
		// Also if the parent course has the parent courseSection and the parent 
		// courseSection has the child topic.
		courseRelatedEntitiesBeforeCRUDCheck
			.returnExistingCourseAndCourseSection(courseId, courseSectionId);
		
		return topicMapper.mapToTopicResponseDtoList(topicRepository
				.findAllTopicsByCourseIdAndCourseSectionId(courseId, courseSectionId));
	}

	public Optional<TopicResponseDto> gettopicById(Long courseId, Long courseSectionId, Long topicId) {
		
		// Check if the parents course and courseSection, and the child topic exist.
		// Also if the parent course has the parent courseSection and the parent 
		// courseSection has the child topic.
		courseRelatedEntitiesBeforeCRUDCheck
			.returnExistingCourseCourseSectionAndTopic(courseId, courseSectionId, topicId);
		
		return Optional.of(topicMapper.map(topicRepository.findById(topicId).get()));
	}

	public TopicResponseDto updateTopic(Long courseId, Long courseSectionId, Long topicId, 
			TopicRequestDto topicRequestDto) {
		
		// Checks if the parents course and courseSection exists, and the child topic exists.
		// Also checks if the parent course has the parent courseSection as well as if the 
		// parent courseSection has the child topic.
		ExistingCourseRelatedEntities existingCourseCourseSectionAndTopic = 
				courseRelatedEntitiesBeforeCRUDCheck
					.returnExistingCourseCourseSectionAndTopic(
							courseId, courseSectionId, topicId);
		
		Topic updatedTopic = existingCourseCourseSectionAndTopic.getExistingTopic();
		CourseSection updatedCourseSection = existingCourseCourseSectionAndTopic
				.getExistingCourseSection();
		
		int topicIndex = updatedCourseSection.getTopics().indexOf(updatedTopic);	
		topicMapper.updateTopicFromDto(topicRequestDto, updatedTopic);
		updatedCourseSection.getTopics().set(topicIndex, updatedTopic);
		
		return topicMapper.map(topicRepository.save(updatedTopic));
	}

	public void deleteTopic(Long courseId, Long courseSectionId, Long topicId) {
		
		// Checks if the parents course and courseSection exists, and the child topic exists.
		// Also checks if the parent course has the parent courseSection as well as if the 
		// parent courseSection has the child topic.
		ExistingCourseRelatedEntities existingCourseCourseSectionAndTopic = 
				courseRelatedEntitiesBeforeCRUDCheck
					.returnExistingCourseCourseSectionAndTopic(
							courseId, courseSectionId, topicId);
		
//		existingTopic.get().setCourseSection(null);
		topicRepository.delete(existingCourseCourseSectionAndTopic.getExistingTopic());
	}
}
