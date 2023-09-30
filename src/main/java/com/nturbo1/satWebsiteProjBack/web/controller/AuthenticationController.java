package com.nturbo1.satWebsiteProjBack.web.controller;

import org.springframework.http.MediaType; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nturbo1.satWebsiteProjBack.service.AuthenticationService;
import com.nturbo1.satWebsiteProjBack.service.dto.request.AuthenticationRequest;
import com.nturbo1.satWebsiteProjBack.service.dto.request.RegisterRequest;
import com.nturbo1.satWebsiteProjBack.service.dto.response.AuthenticationResponse;
import com.nturbo1.satWebsiteProjBack.web.versioning.ApiVersion;

@ApiVersion(1)
@RestController
@CrossOrigin
@RequestMapping(value = RestApiConst.AUTH_API_ROOT_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
	
    private final AuthenticationService authenticationService;
    
    public AuthenticationController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
    		@RequestBody RegisterRequest request) {
		
		System.out.println("Register request: " + request);
    	return ResponseEntity.ok(authenticationService.register(request));
    }
    
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
    		@RequestBody AuthenticationRequest request) {
    	return ResponseEntity.ok(authenticationService.authenticate(request));
    	
    }
}
