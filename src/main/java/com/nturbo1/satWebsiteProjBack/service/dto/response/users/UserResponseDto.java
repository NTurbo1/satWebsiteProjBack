package com.nturbo1.satWebsiteProjBack.service.dto.response.users;

import java.util.List;

import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Course;

public record UserResponseDto(
		Long userId,
		String firstName,
		String lastName,
		String userPassword,
		String email,
		List<Course> enrolledCourses) {

}
