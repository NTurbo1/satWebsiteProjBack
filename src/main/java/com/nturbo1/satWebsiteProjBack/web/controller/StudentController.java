package com.nturbo1.satWebsiteProjBack.web.controller;

import java.util.List;

import org.springframework.http.MediaType; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nturbo1.satWebsiteProjBack.service.UserService;
import com.nturbo1.satWebsiteProjBack.service.dto.response.UserResponseDto;
import com.nturbo1.satWebsiteProjBack.web.versioning.ApiVersion;

import lombok.AllArgsConstructor;

//@CrossOrigin
@ApiVersion(1)
@RestController
@RequestMapping(value = RestApiConst.STUDENT_API_ROOT_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class StudentController {
	
	private final UserService userService;

	@GetMapping
	public ResponseEntity<List<UserResponseDto>> readAll() {
		return ResponseEntity.ok(userService.readAllStudents());
	}
}
