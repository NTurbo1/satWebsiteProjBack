package com.nturbo1.satWebsiteProjBack.service.dto.request.auth;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
	
	private String email;
	private String password;
	private List<String> roles;
}
