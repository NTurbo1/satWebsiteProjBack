package com.nturbo1.satWebsiteProjBack.web.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nturbo1.satWebsiteProjBack.web.controller.constants.RestApiConst;
import com.nturbo1.satWebsiteProjBack.web.versioning.ApiVersion;

@ApiVersion(1)
@RestController
@RequestMapping(value = RestApiConst.TEST_API_ROOT_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class TestController {
	
	@GetMapping
	public ResponseEntity<String> readAll() {
		return ResponseEntity.ok("All tets");
	}
}
