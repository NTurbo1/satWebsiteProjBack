package com.nturbo1.satWebsiteProjBack.web.controller.courses;

import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nturbo1.satWebsiteProjBack.service.courses.CourseService;
import com.nturbo1.satWebsiteProjBack.service.dto.request.courses.CourseRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.courses.CourseResponseDto;
import com.nturbo1.satWebsiteProjBack.web.controller.RestApiConst;
import com.nturbo1.satWebsiteProjBack.web.versioning.ApiVersion;

import lombok.Data;

@RestController
@ApiVersion(1)
@RequestMapping(value = RestApiConst.COURSES_API_ROOT_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
@Data
public class CourseController {

	private final CourseService courseService;

	public CourseResponseDto createCourse(CourseRequestDto courseRequestDto) {
		return courseService.createCourse(courseRequestDto);
	}

	public List<CourseResponseDto> getAllCourses() {
		return courseService.getAllCourses();
	}
	
	public Optional<CourseResponseDto> getCourseById(Long id) {
		return courseService.getCourseById(id);
	}
	
	public CourseResponseDto updateCourse(CourseRequestDto courseRequestDto) {
		return courseService.updateCourse(courseRequestDto.id(), courseRequestDto);
	}
	
	public void deleteCourse(Long id) {
		courseService.deleteCourse(id);
	}
  
}
