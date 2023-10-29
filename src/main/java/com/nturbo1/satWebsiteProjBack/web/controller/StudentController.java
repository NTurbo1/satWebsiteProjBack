package com.nturbo1.satWebsiteProjBack.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nturbo1.satWebsiteProjBack.service.UserService;
import com.nturbo1.satWebsiteProjBack.service.dto.request.UserRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.UserResponseDto;
import com.nturbo1.satWebsiteProjBack.web.versioning.ApiVersion;

import lombok.AllArgsConstructor;

@CrossOrigin
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
	
	@GetMapping(value = "/{id:\\d+}")
	public ResponseEntity<UserResponseDto> readById(@PathVariable Long id) {
		return ResponseEntity.ok(userService.readById(id));
	}
	
	@PutMapping(value = "/{id:\\d+}")
	public ResponseEntity<UserResponseDto> update(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {
		return ResponseEntity.ok(userService.update(userRequestDto));
	}
	
	@DeleteMapping(value = "/{id:\\d+}")
	public ResponseEntity<Void> update(@PathVariable Long id) {
		userService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
