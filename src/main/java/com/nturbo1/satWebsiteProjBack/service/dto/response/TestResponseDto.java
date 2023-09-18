package com.nturbo1.satWebsiteProjBack.service.dto.response;

import java.util.List;

import com.nturbo1.satWebsiteProjBack.repository.entities.Question;
import com.nturbo1.satWebsiteProjBack.repository.entities.User;

public record TestResponseDto(
		Long testId,                  
		String testName,         
		Integer durationSec,         
		User creator,
		List<Question> questions) {

}
