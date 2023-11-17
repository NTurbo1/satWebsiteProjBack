package com.nturbo1.satWebsiteProjBack.web.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nturbo1.satWebsiteProjBack.service.dto.request.users.StudentRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.courses.course.CourseResponseDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.users.StudentResponseDto;
import com.nturbo1.satWebsiteProjBack.service.users.StudentService;
import com.nturbo1.satWebsiteProjBack.web.controller.constants.RestApiConst;
import com.nturbo1.satWebsiteProjBack.web.controller.constants.UserRoleConst;
import com.nturbo1.satWebsiteProjBack.web.versioning.ApiVersion;

import lombok.AllArgsConstructor;

@CrossOrigin
@ApiVersion(1)
@RestController
@RequestMapping(value = RestApiConst.STUDENT_API_ROOT_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class StudentController {
	
	private final StudentService studentService;

	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<StudentResponseDto>> readAll() {
		return ResponseEntity.ok(studentService.readAllStudents());
	}
	
	@GetMapping(value = "/{id:\\d+}")
	@PreAuthorize("hasAuthority('ADMIN') or #id == authentication.principal.userId")
	public ResponseEntity<StudentResponseDto> readById(@PathVariable Long id) {
		return ResponseEntity.ok(studentService.readById(id));
	}
	
	@GetMapping(value = "/{studentId:\\d+}/courses")
	@PreAuthorize("hasAuthority('ADMIN') or #studentId == authentication.principal.userId")
	public ResponseEntity<List<CourseResponseDto>> getAllEnrolledCoursesByStudentId(
			@PathVariable Long studentId) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		
		List<CourseResponseDto> courses;
		
		if (authorities.contains(
				new SimpleGrantedAuthority(UserRoleConst.ADMIN_ROLE))) {
			courses = studentService.getAllEnrolledCoursesByStudentIdForAdmin(studentId);
			
		} else {
			courses = studentService.getAllEnrolledCoursesByStudentIdForStudent(studentId);
		}
		
		return new ResponseEntity<List<CourseResponseDto>>(
				courses, 
				HttpStatus.OK
			);
		
	}
	
	@PutMapping(value = "/{id:\\d+}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<StudentResponseDto> update(@PathVariable Long id, 
			@RequestBody StudentRequestDto studentRequestDto) {
		return ResponseEntity.ok(studentService.update(id, studentRequestDto));
	}
	
	@DeleteMapping(value = "/{id:\\d+}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		studentService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
