package com.nturbo1.satWebsiteProjBack.service.courses;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nturbo1.satWebsiteProjBack.repository.courses.CourseRepository;
import com.nturbo1.satWebsiteProjBack.repository.courses.CourseSectionRepository;
import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Course;
import com.nturbo1.satWebsiteProjBack.service.courses.utils.CourseRelatedEntitiesBeforeCRUDCheck;
import com.nturbo1.satWebsiteProjBack.service.dto.request.courses.CourseRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.courses.CourseResponseDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.courses.GeneralCourseResponseDto;
import com.nturbo1.satWebsiteProjBack.service.mapper.courses.CourseMapper;
import com.nturbo1.satWebsiteProjBack.service.mapper.courses.GeneralCourseMapper;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class CourseService {
	
	private final CourseRepository courseRepository;
	private final CourseSectionRepository courseSectionRepository;
    private final CourseMapper courseMapper;
    private final GeneralCourseMapper generalCourseMapper;
    
    private final CourseRelatedEntitiesBeforeCRUDCheck courseRelatedEntitiesBeforeCRUDCheck;
	
	public CourseResponseDto createCourse(CourseRequestDto courseRequestDto) {
		Course newCourse = courseMapper.map(courseRequestDto);
		return courseMapper.map(courseRepository.save(newCourse));
	}

	public List<GeneralCourseResponseDto> getAllCourses() {
		return generalCourseMapper.mapToGeneralCourseResponseDtoList(courseRepository.findAll());
	}

	public Optional<CourseResponseDto> getCourseById(Long id) {
		Course existingCourse = 
				courseRelatedEntitiesBeforeCRUDCheck.returnExistingCourse(id);
		
		return Optional.of(courseMapper.map(existingCourse));
	}

	public List<GeneralCourseResponseDto> getAllCoursesWithStatus(String status) {
		List<Course> courses = new ArrayList<Course>();
		
		if (status.equals(CourseStatus.ACTIVE)) {
			courses = courseRepository.getAllActiveCourses();
		} else if (status.equals(CourseStatus.INACTIVE)) {
			courses = courseRepository.getAllInactiveCourses();
		} else {
			courses = courseRepository.getAllPausedCourses();
		}
		
		return generalCourseMapper.mapToGeneralCourseResponseDtoList(courses);
	}
	
	public CourseResponseDto updateCourse(Long id, CourseRequestDto courseRequestDto) {
		
		Course updatedCourse = 
				courseRelatedEntitiesBeforeCRUDCheck.returnExistingCourse(id);
		courseMapper.updateCourseFromDto(courseRequestDto, updatedCourse);
		
		return courseMapper.map(courseRepository.save(updatedCourse));
	}

	public void deleteCourse(Long id) {
		if (courseRepository.existsById(id)) {
			courseRepository.deleteById(id);
		} else {
			throw new IllegalArgumentException("Course with id " + id + " does not exist.");
		}
	}

}
