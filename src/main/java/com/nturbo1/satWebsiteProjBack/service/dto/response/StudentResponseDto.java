package com.nturbo1.satWebsiteProjBack.service.dto.response;

import java.util.List;

import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Course;

public record StudentResponseDto(
		Long userId,
		String firstName,
		String lastName,
		String userPassword,
		String email,
		List<Course> enrolledCourses) {

}
