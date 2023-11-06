package com.nturbo1.satWebsiteProjBack.service.dto.request.courses;

import java.util.List;

import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Course;
import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Topic;

public record CourseSectionRequestDto (
  Long id,
	String name,
	String description,
	Course course,
	List<Topic> topics
) {
}
