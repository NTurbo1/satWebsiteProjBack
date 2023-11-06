package com.nturbo1.satWebsiteProjBack.web.controller.courses;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@PostMapping
	public ResponseEntity<Void> createCourse(
			@RequestBody CourseRequestDto courseRequestDto) {
		courseService.createCourse(courseRequestDto);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<CourseResponseDto>> getAllCourses() {
		return ResponseEntity.ok(
			courseService.getAllCourses()
		);
	}
	
	@GetMapping(value = "/{id:\\d+}")
	public ResponseEntity<CourseResponseDto> getCourseById(@PathVariable Long id) {
		return ResponseEntity.ok(
			courseService.getCourseById(id).get()
		);
	}
	
	@PutMapping(value = "/{id:\\d+}")
	public ResponseEntity<CourseResponseDto> updateCourse(@PathVariable Long id, 
			@RequestBody CourseRequestDto courseRequestDto) {
		return ResponseEntity.ok(
			courseService.updateCourse(id, courseRequestDto)
		);
	}
	
	@DeleteMapping(value = "/{id:\\d+}")
	public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
		courseService.deleteCourse(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
  
}
