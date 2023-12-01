package com.nturbo1.satWebsiteProjBack.service.courses;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nturbo1.satWebsiteProjBack.repository.UserRepository;
import com.nturbo1.satWebsiteProjBack.repository.courses.CourseRepository;
import com.nturbo1.satWebsiteProjBack.repository.courses.CourseSectionRepository;
import com.nturbo1.satWebsiteProjBack.repository.entities.User;
import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Course;
import com.nturbo1.satWebsiteProjBack.service.courses.utils.CourseRelatedEntitiesBeforeCRUDCheck;
import com.nturbo1.satWebsiteProjBack.service.dto.request.courses.CourseRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.courses.course.AdminSpecificCourseResponseDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.courses.course.CourseResponseDto;
import com.nturbo1.satWebsiteProjBack.service.mapper.courses.course.AdminCourseMapper;
import com.nturbo1.satWebsiteProjBack.service.mapper.courses.course.CourseMapper;
import com.nturbo1.satWebsiteProjBack.service.mapper.courses.course.EnrolledStudentCourseMapper;
import com.nturbo1.satWebsiteProjBack.service.mapper.courses.course.StudentCourseMapper;
import com.nturbo1.satWebsiteProjBack.web.controller.constants.UserRoleConst;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class CourseService {
	
	private final CourseRepository courseRepository;
	private final CourseSectionRepository courseSectionRepository;
	private final UserRepository userRepository;
	
    private final CourseMapper courseMapper;
    private final StudentCourseMapper studentCourseMapper;
    private final AdminCourseMapper adminCourseMapper;
    private EnrolledStudentCourseMapper enrolledStudentCourseMapper;
    
    private final CourseRelatedEntitiesBeforeCRUDCheck courseRelatedEntitiesBeforeCRUDCheck;
	
    // Only admins can create a course
	public AdminSpecificCourseResponseDto createCourse(CourseRequestDto courseRequestDto) {
		Course newCourse = courseMapper.map(courseRequestDto);
		return adminCourseMapper.map(courseRepository.save(newCourse));
	}

	// Only admins can retrieve ALL courses
	public List<CourseResponseDto> getAllCourses() {
		
		return courseRepository.findAll()
					.stream()
					.map(course -> {
						return adminCourseMapper.map(course);
					})
					.collect(Collectors.toList());
	}
	
	// Admins and enrolled students can access
	public Optional<CourseResponseDto> getCourseById(Long courseId, String role) {
		Course existingCourse = 
				courseRelatedEntitiesBeforeCRUDCheck.returnExistingCourse(courseId);
		
		if (role.equals(UserRoleConst.ADMIN_ROLE))
			return Optional.of(adminCourseMapper.map(existingCourse));
		
		return Optional.of(enrolledStudentCourseMapper.map(existingCourse));
	}

	public List<CourseResponseDto> getAllCoursesWithStatus(String status, String role) {
		List<Course> courses = new ArrayList<Course>();
		
		if (status.equals(CourseStatus.ACTIVE)) {
			courses = courseRepository.getAllActiveCourses();
		} else if (status.equals(CourseStatus.INACTIVE)) {
			courses = courseRepository.getAllInactiveCourses();
		} else {
			courses = courseRepository.getAllPausedCourses();
		}
		
		if (role.equals(UserRoleConst.ADMIN_ROLE)) {
			return courses
						.stream()
						.map(course -> adminCourseMapper.map(course))
						.collect(Collectors.toList());
		}
		
		return courses
				.stream()
				.map(course -> studentCourseMapper.map(course))
				.collect(Collectors.toList());
	}
	
	// Only admins can update a course
	public CourseResponseDto updateCourse(Long id, CourseRequestDto courseRequestDto) {
		
		Course updatedCourse = 
				courseRelatedEntitiesBeforeCRUDCheck.returnExistingCourse(id);
		courseMapper.updateCourseFromDto(courseRequestDto, updatedCourse);
		
		return courseMapper.map(courseRepository.save(updatedCourse));
	}

	// Only admins can delete courses.
	public void deleteCourse(Long id) {
		
		Course existingCourse = courseRelatedEntitiesBeforeCRUDCheck.returnExistingCourse(id);
		
		existingCourse.getEnrolledStudents()
			.stream()
			.forEach(student -> {
				student.getEnrolledCourses().remove(existingCourse);
			});
		
		existingCourse.setEnrolledStudents(null);
		
		courseRepository.delete(existingCourse);
	}
	
	public boolean isEnrolled(Long courseId, Long studentId) {
		Optional<Course> exisitingCourse = courseRepository.findById(courseId);
		Optional<User> existingStudent = userRepository.findStudentByUserId(studentId);
		
		if (exisitingCourse.isPresent() && existingStudent.isPresent()) {
			return exisitingCourse.get()
						.getEnrolledStudents()
						.contains(
							existingStudent.get());
		}
		
		return false;
	}

}
