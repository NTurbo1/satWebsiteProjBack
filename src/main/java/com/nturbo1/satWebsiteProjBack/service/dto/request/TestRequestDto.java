package com.nturbo1.satWebsiteProjBack.service.dto.request;

import java.util.List; 

import com.nturbo1.satWebsiteProjBack.repository.entities.Question;
import com.nturbo1.satWebsiteProjBack.repository.entities.User;

public record TestRequestDto(
		Long testId,                  
		String testName,         
		Integer durationSec,         
		User creator,
		List<Question> questions) {
}