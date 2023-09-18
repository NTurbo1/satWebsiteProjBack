package com.nturbo1.satWebsiteProjBack.service.dto.response;

public record UserResponseDto(
		Long userId,
		String firstName,
		String lastName,
		String userPassword,
		String email) {

}
