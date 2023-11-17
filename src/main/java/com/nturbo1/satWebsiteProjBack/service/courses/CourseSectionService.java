package com.nturbo1.satWebsiteProjBack.service.courses;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nturbo1.satWebsiteProjBack.repository.courses.CourseRepository;
import com.nturbo1.satWebsiteProjBack.repository.courses.CourseSectionRepository;
import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Course;
import com.nturbo1.satWebsiteProjBack.repository.entities.courses.CourseSection;
import com.nturbo1.satWebsiteProjBack.service.courses.utils.CourseRelatedEntitiesBeforeCRUDCheck;
import com.nturbo1.satWebsiteProjBack.service.courses.utils.ExistingCourseRelatedEntities;
import com.nturbo1.satWebsiteProjBack.service.dto.request.courses.CourseSectionRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.courses.CourseSectionResponseDto;
import com.nturbo1.satWebsiteProjBack.service.mapper.courses.CourseSectionMapper;

import lombok.Data;

@Service
@Data
public class CourseSectionService {
	
	private final CourseSectionRepository courseSectionRepository;
	private final CourseRepository courseRepository;
	private final CourseSectionMapper courseSectionMapper;
	
	private final CourseRelatedEntitiesBeforeCRUDCheck courseRelatedEntitiesBeforeCRUDCheck;

	// Only Admins
	public CourseSectionResponseDto createCourseSection(
			Long courseId, CourseSectionRequestDto courseSectionRequestDto) {
		
		CourseSection newCourseSection = courseSectionMapper.map(courseSectionRequestDto);
		// Checks for parentCourse existence
		Course existingCourse = 
				courseRelatedEntitiesBeforeCRUDCheck.returnExistingCourse(courseId);
		
		List<CourseSection> existingCourseSections = existingCourse.getSections();
		
		if (existingCourseSections == null) {
			existingCourseSections = new ArrayList<CourseSection>();		
			existingCourse.setSections(existingCourseSections);	
		}
		
		newCourseSection.setCourse(existingCourse);
		existingCourse.getSections().add(newCourseSection);
		
		return courseSectionMapper.map(
				courseSectionRepository.save(newCourseSection));
	}

	// Admins and enrolled students
	public List<CourseSectionResponseDto> getAllCourseSectionsByCourseId(Long courseId) {
		
		// Checks for parentCourse existence
		courseRelatedEntitiesBeforeCRUDCheck.returnExistingCourse(courseId);
		
		return courseSectionMapper
				.mapToCourseSectionResponseDtoList(
						courseSectionRepository
							.findCourseSectionsByCourseId(courseId));
	}

	// Admins and enrolled students
	public Optional<CourseSectionResponseDto> getCourseSectionById(
			Long courseId, Long courseSectionId) {
		
		// Checks if the parentCourse and child courseSection exists.
		// Also if the parent course has the child courseSection.
		ExistingCourseRelatedEntities existingCourseAndCourseSection =
				courseRelatedEntitiesBeforeCRUDCheck
					.returnExistingCourseAndCourseSection(courseId, courseSectionId);
		
		return Optional.of(
				courseSectionMapper.map(
						existingCourseAndCourseSection.getExistingCourseSection()));
	}

	// Only Admins
	public CourseSectionResponseDto updateCourseSection(
			Long courseId, Long courseSectionId,
			CourseSectionRequestDto courseSectionRequestDto) {
		
		// Checks if the parentCourse and child courseSection exists.
		// Also if the parent course has the child courseSection.
		ExistingCourseRelatedEntities existingCourseAndCourseSection =
				courseRelatedEntitiesBeforeCRUDCheck
					.returnExistingCourseAndCourseSection(courseId, courseSectionId);
				
		CourseSection updatedCourseSection = 
				existingCourseAndCourseSection.getExistingCourseSection();
		
		courseSectionMapper
			.updateCourseSectionFromDto(
					courseSectionRequestDto, updatedCourseSection);
		
		return courseSectionMapper.map(
				courseSectionRepository.save(updatedCourseSection));
	}

	// Only Admins
	public void deleteCourseSection(Long courseId, Long courseSectionId) {
		// Checks if the parentCourse and child courseSection exists.
		// Also if the parent course has the child courseSection.
		ExistingCourseRelatedEntities existingCourseAndCourseSection =
				courseRelatedEntitiesBeforeCRUDCheck
					.returnExistingCourseAndCourseSection(courseId, courseSectionId);	
	 	
		existingCourseAndCourseSection
			.getExistingCourse()
			.getSections()
			.remove(existingCourseAndCourseSection.getExistingCourseSection());
		
		courseSectionRepository.deleteById(courseSectionId);
	}
}
