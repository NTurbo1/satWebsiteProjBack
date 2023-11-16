package com.nturbo1.satWebsiteProjBack.service.dto.response.courses;

public record GeneralCourseResponseDto(
		Long id,
		String name,
		String description,
		String type,
		Integer price) {

}
