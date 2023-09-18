package com.nturbo1.satWebsiteProjBack.service.dto.response;

import java.util.List;

import com.nturbo1.satWebsiteProjBack.repository.entities.Test;

public record InstructorResponseDto(
		Long userId,
		String firstName,
		String lastName,
		String userPassword,
		String email,
		List<Test> createdTests) {

}
