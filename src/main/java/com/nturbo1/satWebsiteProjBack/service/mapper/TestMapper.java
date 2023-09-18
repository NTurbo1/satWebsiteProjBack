package com.nturbo1.satWebsiteProjBack.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.nturbo1.satWebsiteProjBack.repository.entities.Test;
import com.nturbo1.satWebsiteProjBack.service.dto.request.TestRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.TestResponseDto;

@Mapper(componentModel = "spring")
public interface TestMapper {
	
	List<TestResponseDto> mapToTestResponseDtoList(List<Test> entityList);
	
	List<Test> mapToTestList(List<TestRequestDto> dtoRequestList);
	
	TestResponseDto map(Test entity);
	
	Test map(TestRequestDto value);
	
//	QuestionResponseDto entityToDto(Question entity);
//	
//	Question dtoToEntity(QuestionRequestDto dtoRequest);
//	
//	List<QuestionResponseDto> questionEntityListToDtoList(List<Question> entityList);
//	
//	List<Question> dtoListToQuestionEntityList(List<QuestionResponseDto> dtoRequestList);
}
