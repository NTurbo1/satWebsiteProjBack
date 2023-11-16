package com.nturbo1.satWebsiteProjBack.web.controller.courses;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nturbo1.satWebsiteProjBack.service.courses.CourseSectionService;
import com.nturbo1.satWebsiteProjBack.service.courses.CourseService;
import com.nturbo1.satWebsiteProjBack.service.courses.TopicService;
import com.nturbo1.satWebsiteProjBack.service.dto.request.courses.CourseRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.request.courses.CourseSectionRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.request.courses.TopicRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.courses.CourseResponseDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.courses.CourseSectionResponseDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.courses.GeneralCourseResponseDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.courses.TopicResponseDto;
import com.nturbo1.satWebsiteProjBack.web.controller.constants.RestApiConst;
import com.nturbo1.satWebsiteProjBack.web.versioning.ApiVersion;

import lombok.Data;

@RestController
@ApiVersion(1)
@RequestMapping(value = RestApiConst.COURSES_API_ROOT_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
@Data
public class CourseController {

	private final CourseService courseService;
	private final CourseSectionService courseSectionService;
	private final TopicService topicService;

	@PostMapping
	public ResponseEntity<Void> createCourse(
			@RequestBody CourseRequestDto courseRequestDto) {
		courseService.createCourse(courseRequestDto);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<GeneralCourseResponseDto>> getAllCourses(
			@RequestParam(required = false) String status) {
		
		List<GeneralCourseResponseDto> courses = new ArrayList<>();
		
		if (status != null) {
			courses = courseService.getAllCoursesWithStatus(status);
		} else {
			courses = courseService.getAllCourses();
		}
		
		return ResponseEntity.ok(courses);
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
	
	@GetMapping(value = "/{courseId:\\d+}/course-sections")
	public ResponseEntity<List<CourseSectionResponseDto>> getCourseSectionsByCourseId(
			@PathVariable Long courseId) {
		
		return new ResponseEntity<List<CourseSectionResponseDto>>(
				courseSectionService.getAllCourseSectionsByCourseId(courseId),
				HttpStatus.OK);
	}
	
	@GetMapping(value = "/{courseId:\\d+}/course-sections/{courseSectionId:\\d+}")
	public ResponseEntity<CourseSectionResponseDto> getCourseSectionsByCourseId(
			@PathVariable Long courseId, @PathVariable Long courseSectionId) {
		
		return new ResponseEntity<CourseSectionResponseDto>(
				courseSectionService
					.getCourseSectionById(courseId, courseSectionId)
						.get(),
				HttpStatus.OK);
	}
	
	@PostMapping(value = "/{courseId:\\d+}/course-sections")
	public ResponseEntity<CourseSectionResponseDto> createCourseSection(
			@PathVariable Long courseId, 
			@RequestBody CourseSectionRequestDto courseSectionRequestDto) {
		
		return new ResponseEntity<CourseSectionResponseDto>(
				courseSectionService
					.createCourseSection(courseId, courseSectionRequestDto),
				HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{courseId:\\d+}/course-sections/{courseSectionId:\\d+}")
	public ResponseEntity<CourseSectionResponseDto> updateCourseSection(
			@PathVariable Long courseId, @PathVariable Long courseSectionId,
			@RequestBody CourseSectionRequestDto courseSectionRequestDto) {
		
		return new ResponseEntity<CourseSectionResponseDto>(
				courseSectionService
					.updateCourseSection(
							courseId,
							courseSectionId,
							courseSectionRequestDto),
				HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{courseId:\\d+}/course-sections/{courseSectionId:\\d+}")
	public ResponseEntity<Void> deleteCourseSection(
			@PathVariable Long courseId, @PathVariable Long courseSectionId) {
	
		courseSectionService.deleteCourseSection(courseId, courseSectionId);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
  
	@GetMapping(value = "/{courseId:\\d+}/topics")
	public ResponseEntity<List<TopicResponseDto>> getAllTopicsByCourseId(
			@PathVariable Long courseId) {
		
		return new ResponseEntity<List<TopicResponseDto>>(
				topicService.getAllTopicsByCourseId(courseId),
				HttpStatus.OK);
	}
	
	@GetMapping(value = "/{courseId:\\d+}/course-sections/{courseSectionId:\\d+}/topics")
	public ResponseEntity<List<TopicResponseDto>> getAllTopicsByCourseIdAndCourseSectionId(
			@PathVariable Long courseId, @PathVariable Long courseSectionId) {
		
		return new ResponseEntity<List<TopicResponseDto>>(
				topicService
					.getAllTopicsByCourseIdAndCourseSectionId(courseId, courseSectionId),
				HttpStatus.OK);
	}
	
	@GetMapping(value = "/{courseId:\\d+}/course-sections/{courseSectionId:\\d+}/topics/{topicId:\\d+}")
	public ResponseEntity<TopicResponseDto> getTopicById(
			@PathVariable Long courseId, @PathVariable Long courseSectionId,
			@PathVariable Long topicId) {
		
		return new ResponseEntity<TopicResponseDto>(
				topicService
					.gettopicById(courseId, courseSectionId, topicId).get(),
				HttpStatus.OK);
	}

	@PostMapping(value = "/{courseId:\\d+}/course-sections/{courseSectionId:\\d+}/topics")
	public ResponseEntity<TopicResponseDto> createTopic(
			@PathVariable Long courseId, @PathVariable Long courseSectionId,
			@RequestBody TopicRequestDto topicRequestDto) {
		
		return new ResponseEntity<TopicResponseDto>(
				topicService
					.createTopic(courseId, courseSectionId, topicRequestDto),
				HttpStatus.CREATED);
	}

	@PutMapping(value = "/{courseId:\\d+}/course-sections/{courseSectionId:\\d+}/topics/{topicId:\\d+}")
	public ResponseEntity<TopicResponseDto> updateTopic(
			@PathVariable Long courseId, @PathVariable Long courseSectionId,
			@PathVariable Long topicId, @RequestBody TopicRequestDto topicRequestDto) {
		
		return new ResponseEntity<TopicResponseDto>(
				topicService
					.updateTopic(courseId, courseSectionId, topicId, topicRequestDto),
				HttpStatus.OK);
	}

	@DeleteMapping(value = "/{courseId:\\d+}/course-sections/{courseSectionId:\\d+}/topics/{topicId:\\d+}")
	public ResponseEntity<Void> deleteTopic(
			@PathVariable Long courseId, @PathVariable Long courseSectionId,
			@PathVariable Long topicId) {
		
		topicService.deleteTopic(courseId, courseSectionId, topicId);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
