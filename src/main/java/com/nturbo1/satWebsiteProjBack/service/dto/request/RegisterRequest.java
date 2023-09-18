package com.nturbo1.satWebsiteProjBack.service.dto.request;

import java.util.List;

import lombok.*;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
	
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private List<String> roles;
	
	@Override
	public String toString() {
		return "RegisterRequest [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", password="
				+ password + ", roles=" + roles + "]";
	}
	
	
}
