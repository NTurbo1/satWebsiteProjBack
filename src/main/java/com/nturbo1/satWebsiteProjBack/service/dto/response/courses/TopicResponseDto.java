package com.nturbo1.satWebsiteProjBack.service.dto.response.courses;

import com.nturbo1.satWebsiteProjBack.repository.entities.courses.CourseSection;

public record TopicResponseDto (
	Long id,
	String name,
	String description,
	String text,
	CourseSection courseSection
) {
  
}
