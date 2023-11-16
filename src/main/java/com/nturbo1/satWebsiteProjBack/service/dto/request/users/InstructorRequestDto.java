package com.nturbo1.satWebsiteProjBack.service.dto.request.users;

import java.util.List;

import com.nturbo1.satWebsiteProjBack.repository.entities.Test;

public record InstructorRequestDto(
		Long userId,
		String firstName,
		String lastName,
		String userPassword,
		String email,
		List<Test> createdTests) {

}
