package com.nturbo1.satWebsiteProjBack.service.dto.response;

import java.util.List;

import com.nturbo1.satWebsiteProjBack.repository.entities.QuestionType;
import com.nturbo1.satWebsiteProjBack.repository.entities.Test;

public record QuestionResponseDto(
		Long questionId,
		String questionText,
		String correctAnswer,
		List<QuestionType> questionTypes,
		Test test ) {

}
