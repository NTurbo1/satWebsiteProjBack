package com.nturbo1.satWebsiteProjBack.service.dto.response.courses;

import java.util.List;

import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Topic;

public record VideoResponseDto (
  Long id,
	String name,
	String description,
	String link,
	List<Topic> topicContents
) {
  
}
