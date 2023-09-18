package com.nturbo1.satWebsiteProjBack.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.nturbo1.satWebsiteProjBack.repository.entities.Question;
import com.nturbo1.satWebsiteProjBack.service.dto.request.QuestionRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.QuestionResponseDto;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
	
	List<QuestionResponseDto> mapToQuestionResponseDtoList(List<Question> entityList);
	
	List<Question> mapToQuestionList(List<QuestionRequestDto> dtoRequestList);
	
	QuestionResponseDto map(Question entity);
	
	Question map(QuestionRequestDto value);
	
//	QuestionTypeResponseDto questionTypeToQuestionTypeResponseDto(QuestionType questionType);
//	
//	QuestionType questionTypeResponseDtoToQuestionType(QuestionTypeResponseDto questionTypeResponseDto);
//	
//	TestResponseDto testToTestResponseDto(Test test);
//	
//	Test testResponseDtoToTest(TestResponseDto testResponseDto);
//	
//	List<QuestionTypeResponseDto> questionTypeEntityListToDtoList(List<QuestionType> entityList);
//	
//	List<QuestionType> dtoListToQuestionTypeEntityList(List<QuestionTypeResponseDto> dtoRequestList);
}
