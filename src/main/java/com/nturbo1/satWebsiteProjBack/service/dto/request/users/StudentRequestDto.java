package com.nturbo1.satWebsiteProjBack.service.dto.request.users;

public record StudentRequestDto(
		String firstName,
		String lastName,
		String userPassword,
		String email) {
}
