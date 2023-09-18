package com.nturbo1.satWebsiteProjBack.service.dto.request;

public record StudentRequestDto(
		Long userId,
		String firstName,
		String lastName,
		String userPassword,
		String email) {
}
