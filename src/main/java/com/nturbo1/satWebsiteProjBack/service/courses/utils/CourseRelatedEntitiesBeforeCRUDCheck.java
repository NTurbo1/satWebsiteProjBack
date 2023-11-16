package com.nturbo1.satWebsiteProjBack.service.courses.utils;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nturbo1.satWebsiteProjBack.repository.courses.CourseRepository;
import com.nturbo1.satWebsiteProjBack.repository.courses.CourseSectionRepository;
import com.nturbo1.satWebsiteProjBack.repository.courses.TopicRepository;
import com.nturbo1.satWebsiteProjBack.repository.courses.VideoRepository;
import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Course;
import com.nturbo1.satWebsiteProjBack.repository.entities.courses.CourseSection;
import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Topic;
import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Video;

import lombok.AllArgsConstructor;
import lombok.Data;

@Component
@Data
@AllArgsConstructor
public class CourseRelatedEntitiesBeforeCRUDCheck {

	private final CourseRepository courseRepository;
	private final CourseSectionRepository courseSectionRepository;
	private final TopicRepository topicRepository;
	private final VideoRepository videoRepository;
	
	public Course returnExistingCourse(Long courseId) {
		Optional<Course> existingCourse = courseRepository.findById(courseId);
		
		if (existingCourse.isPresent())
			return existingCourse.get();
		else
			throw new IllegalArgumentException(
					"Course with id " + courseId + " does not exist.");
	}
	
	public ExistingCourseRelatedEntities returnExistingCourseAndCourseSection(
			Long courseId, Long courseSectionId) {
		
		// throws exception
		Course existingCourse = returnExistingCourse(courseId);
		
		Optional<CourseSection> existingCourseSection = 
				courseSectionRepository.findById(courseSectionId);
		
		if (existingCourseSection.isPresent()) {
			if (existingCourseSection.get().getCourse().getId()
					.equals(existingCourse.getId())) {
			
				return ExistingCourseRelatedEntities
						.builder()
						.existingCourse(existingCourse)
						.existingCourseSection(existingCourseSection.get())
						.build();
				
			} else {
				throw new IllegalArgumentException(
					"Course with id " + courseId+ " doesn't have CourseSection " +
					"with id = " + courseSectionId);
			}
			
		} else {
			throw new IllegalArgumentException(
					"CourseSection with id " + courseSectionId + " does not exist.");
		}
	}
	
	public ExistingCourseRelatedEntities returnExistingCourseCourseSectionAndTopic(
			Long courseId, Long courseSectionId, Long topicId) {
		
		ExistingCourseRelatedEntities existingCourseRelatedEntities = 
				returnExistingCourseAndCourseSection(courseId, courseSectionId);	
		
		Optional<Topic> existingTopic = topicRepository.findById(topicId);
		
		if (existingTopic.isPresent()) {
			
			if (existingTopic.get().getCourseSection().getId()
					.equals(existingCourseRelatedEntities
								.getExistingCourseSection()
								.getId())) {
				existingCourseRelatedEntities.setExistingTopic(
						existingTopic.get());
				
				return existingCourseRelatedEntities;
				
			} else {
				throw new IllegalArgumentException(
						"CourseSection with id " + courseSectionId+ " doesn't have Topic " +
						"with id = " + topicId);
			}
			
		} else {
			throw new IllegalArgumentException(
					"Topic with id " + topicId + " does not exist.");
		}
	}
	
	public ExistingCourseRelatedEntities returnExistingCourseCourseSectionTopicAndVideo(
			Long courseId, Long courseSection, Long topicId, Long videoId) {
		
		ExistingCourseRelatedEntities existingCourseRelatedEntities = 
				returnExistingCourseCourseSectionAndTopic(courseId, courseSection, topicId);
						
		Optional<Video> existingVideo = videoRepository.findById(videoId);
		
		if (existingVideo.isPresent()) {
			
			if (existingVideo.get().getTopics()
					.stream()
					.filter(topic -> topic.getId().equals(topicId))
					.collect(Collectors.toList())
					.size() >= 1) {
				
				existingCourseRelatedEntities.setExisitingVideo(existingVideo.get());
				return existingCourseRelatedEntities;
				
			} else {
				throw new IllegalArgumentException(
					"Topic with id " + topicId + " doesn't have Video " +
					"with id = " + videoId);	
			}
			
		} else {
			throw new IllegalArgumentException(
					"Video with id " + videoId + " does not exist.");
		}
	}
}
