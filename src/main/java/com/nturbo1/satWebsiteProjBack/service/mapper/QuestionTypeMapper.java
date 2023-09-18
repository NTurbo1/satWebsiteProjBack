package com.nturbo1.satWebsiteProjBack.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.nturbo1.satWebsiteProjBack.repository.entities.QuestionType;
import com.nturbo1.satWebsiteProjBack.service.dto.request.QuestionTypeRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.QuestionTypeResponseDto;

@Mapper(componentModel = "spring")
public interface QuestionTypeMapper {
	
	List<QuestionTypeResponseDto> mapToQuestionTypeResponseDtoList(List<QuestionType> entityList);
	
	List<QuestionType> mapToQuestionTypeList(List<QuestionTypeRequestDto> dtoRequestList);
	
	QuestionTypeResponseDto map(QuestionType entity);
	
	QuestionType map(QuestionTypeRequestDto value);
	
//	QuestionResponseDto questionToQuestionResponseDto(Question question);
//	
//	Question questionResponseDtoToQuestion(QuestionResponseDto questionResponseDto);
//	
//	List<QuestionResponseDto> questionEntityListToDtoList(List<Question> entityList);
//	
//	List<Question> dtoListToQuestionEntityList(List<QuestionResponseDto> dtoRequestList);
}
