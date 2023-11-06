package com.nturbo1.satWebsiteProjBack.service.dto.request.courses;

import java.util.List;

import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Topic;

public record VideoRequestDto (
  Long id,
	String name,
	String description,
	String link,
	List<Topic> topicContents
) {
  
}
