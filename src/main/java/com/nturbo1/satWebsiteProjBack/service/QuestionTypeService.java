package com.nturbo1.satWebsiteProjBack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nturbo1.satWebsiteProjBack.repository.QuestionTypeRepository;
import com.nturbo1.satWebsiteProjBack.repository.entities.QuestionType;
import com.nturbo1.satWebsiteProjBack.service.dto.request.QuestionTypeRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.QuestionTypeResponseDto;
import com.nturbo1.satWebsiteProjBack.service.mapper.QuestionTypeMapper;

@Service
public class QuestionTypeService {
	
	private final QuestionTypeRepository questionTypeRepository;
	private final QuestionTypeMapper questionTypeMapper;
	
	@Autowired
	public QuestionTypeService(QuestionTypeRepository questionTypeRepository, QuestionTypeMapper questionTypeMapper) {
		this.questionTypeRepository = questionTypeRepository;
		this.questionTypeMapper = questionTypeMapper;
	}
	
	public List<QuestionTypeResponseDto> readAll() {
		return questionTypeMapper
					.mapToQuestionTypeResponseDtoList(questionTypeRepository.findAll());
	}
	
	public QuestionTypeResponseDto readById(Long id) {
		return questionTypeMapper.map(questionTypeRepository.findById(id).get());
	}
	
	public QuestionTypeResponseDto update(QuestionTypeRequestDto questionTypeRequestDto) {
		
		QuestionType questionType = questionTypeMapper.map(questionTypeRequestDto);
		
		return questionTypeMapper.map(questionTypeRepository.save(questionType));
	}
	
	public void delete(Long id) {
		
		if (questionTypeRepository.existsById(id)) {
			questionTypeRepository.deleteById(id);			
		} else {
			throw new RuntimeException("QuestionType with " + id + " doesn't exist.");
		}
	}
	
}
