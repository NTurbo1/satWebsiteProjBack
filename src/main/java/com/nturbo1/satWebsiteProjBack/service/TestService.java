package com.nturbo1.satWebsiteProjBack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nturbo1.satWebsiteProjBack.repository.TestRepository;
import com.nturbo1.satWebsiteProjBack.repository.entities.Test;
import com.nturbo1.satWebsiteProjBack.service.dto.request.TestRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.TestResponseDto;
import com.nturbo1.satWebsiteProjBack.service.mapper.TestMapper;

@Service
public class TestService {
	
	private final TestRepository testRepository;
	private final TestMapper testMapper;
	
	@Autowired
	public TestService(TestRepository testRepository, TestMapper testMapper) {
		this.testRepository = testRepository;
		this.testMapper = testMapper;
	}
	
	public List<TestResponseDto> readAll() {
		return testMapper
					.mapToTestResponseDtoList(testRepository.findAll());
	}
	
	public TestResponseDto readById(Long id) {
		return testMapper.map(testRepository.findById(id).get());
	}
	
	public TestResponseDto update(TestRequestDto testRequestDto) {
		
		Test test = testMapper.map(testRequestDto);
		
		return testMapper.map(testRepository.save(test));
	}
	
	public void delete(Long id) {
		
		if (testRepository.existsById(id)) {
			testRepository.deleteById(id);			
		} else {
			throw new RuntimeException("Test with " + id + " doesn't exist.");
		}
	}
	

}
