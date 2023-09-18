package com.nturbo1.satWebsiteProjBack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nturbo1.satWebsiteProjBack.repository.QuestionRepository;
import com.nturbo1.satWebsiteProjBack.repository.entities.Question;
import com.nturbo1.satWebsiteProjBack.service.dto.request.QuestionRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.QuestionResponseDto;
import com.nturbo1.satWebsiteProjBack.service.mapper.QuestionMapper;

@Service
public class QuestionService {
	
	private final QuestionRepository questionRepository;
	private final QuestionMapper questionMapper;

	@Autowired
	public QuestionService(QuestionRepository questionRepository, 
						   QuestionMapper questionMapper) {
		this.questionRepository = questionRepository;
		this.questionMapper = questionMapper;
	}
	
	public List<QuestionResponseDto> readAll() {
		return questionMapper
					.mapToQuestionResponseDtoList(questionRepository.findAll());
	}
	
	public QuestionResponseDto readById(Long id) {
		return questionMapper.map(questionRepository.findById(id).get());
	}
	
	public QuestionResponseDto update(QuestionRequestDto questionRequestDto) {
		
		Question question = questionMapper.map(questionRequestDto);
		
		return questionMapper.map(questionRepository.save(question));
	}
	
	public void delete(Long id) {
		if (questionRepository.existsById(id)) {
			questionRepository.deleteById(id);			
		} else {
			throw new RuntimeException("Question with " + id + " doesn't exist.");
		}
	}

}
