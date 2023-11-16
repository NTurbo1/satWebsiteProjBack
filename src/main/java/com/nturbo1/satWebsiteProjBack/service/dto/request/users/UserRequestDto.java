package com.nturbo1.satWebsiteProjBack.service.dto.request.users;

public record UserRequestDto(
		String firstName,
		String lastName,
		String userPassword,
		String email) {

}
