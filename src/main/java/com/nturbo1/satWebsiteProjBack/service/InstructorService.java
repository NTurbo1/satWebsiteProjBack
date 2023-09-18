package com.nturbo1.satWebsiteProjBack.service;

import java.util.List; 

import org.springframework.stereotype.Service;

import com.nturbo1.satWebsiteProjBack.repository.UserRepository;
import com.nturbo1.satWebsiteProjBack.repository.entities.User;
import com.nturbo1.satWebsiteProjBack.service.dto.request.InstructorRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.InstructorResponseDto;
import com.nturbo1.satWebsiteProjBack.service.mapper.InstructorMapper;

@Service
public class InstructorService {
	
	private final UserRepository userRepository;
	private final InstructorMapper instructorMapper;
	
	public InstructorService(UserRepository userRepository, InstructorMapper instructorMapper) {
		this.userRepository = userRepository;
		this.instructorMapper = instructorMapper;
	}
	
	public List<InstructorResponseDto> readAll() {
		return instructorMapper
					.mapToInstructorResponseDtoList(userRepository.findAll());
	}
	
	public InstructorResponseDto readById(Long id) {
		return instructorMapper.map(userRepository.findById(id).get());
	}
	
	public InstructorResponseDto update(InstructorRequestDto instructorRequestDto) {
		
		User instructor = instructorMapper.map(instructorRequestDto);
		
		return instructorMapper.map(userRepository.save(instructor));
	}
	
	public void delete(Long id) {
		
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);			
		} else {
			throw new RuntimeException("User with " + id + " doesn't exist.");
		}
	}
}
