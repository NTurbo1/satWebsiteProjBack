package com.nturbo1.satWebsiteProjBack.service.dto.request.courses;

import com.nturbo1.satWebsiteProjBack.repository.entities.courses.CourseSection;

public record TopicRequestDto (
  Long id,
	String name,
	String description,
	String text,
	CourseSection courseSection
) {
  
}
