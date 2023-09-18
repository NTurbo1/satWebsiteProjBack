package com.nturbo1.satWebsiteProjBack.service.dto.response;

import java.util.List;

import com.nturbo1.satWebsiteProjBack.repository.entities.Question;

public record QuestionTypeResponseDto(
		Long typeId,
		String typeName,
		List<Question> questions) {

}
