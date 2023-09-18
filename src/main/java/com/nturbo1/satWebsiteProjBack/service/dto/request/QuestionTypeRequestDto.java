package com.nturbo1.satWebsiteProjBack.service.dto.request;

import java.util.List;

import com.nturbo1.satWebsiteProjBack.repository.entities.Question;

public record QuestionTypeRequestDto(
		Long typeId,
		String typeName,
		List<Question> questions) {
}
