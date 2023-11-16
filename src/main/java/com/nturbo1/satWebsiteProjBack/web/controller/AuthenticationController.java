package com.nturbo1.satWebsiteProjBack.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nturbo1.satWebsiteProjBack.service.dto.request.auth.AuthenticationRequest;
import com.nturbo1.satWebsiteProjBack.service.dto.request.auth.RegisterRequest;
import com.nturbo1.satWebsiteProjBack.service.dto.response.auth.AuthenticationResponse;
import com.nturbo1.satWebsiteProjBack.service.security.AuthenticationService;
import com.nturbo1.satWebsiteProjBack.web.controller.constants.RestApiConst;
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
    public ResponseEntity<Void> register(
    		@RequestBody RegisterRequest request) {
		
		System.out.println("Register request: " + request);
		authenticationService.register(request);
		return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
    		@RequestBody AuthenticationRequest request) {
    	return ResponseEntity.ok(authenticationService.authenticate(request));
    	
    }
}
