package com.nturbo1.satWebsiteProjBack.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nturbo1.satWebsiteProjBack.repository.UserRepository;
import com.nturbo1.satWebsiteProjBack.repository.entities.User;
import com.nturbo1.satWebsiteProjBack.service.dto.request.StudentRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.StudentResponseDto;
import com.nturbo1.satWebsiteProjBack.service.mapper.StudentMapper;

@Service
public class StudentService {
	
	private final UserRepository userRepository;
	private final StudentMapper studentMapper;
	
	public StudentService(UserRepository userRepository, StudentMapper studentMapper) {
		this.userRepository = userRepository;
		this.studentMapper = studentMapper;
	}
	
	public List<StudentResponseDto> readAll() {
		return studentMapper
					.mapToStudentResponseDtoList(userRepository.findAll());
	}
	
	public StudentResponseDto readById(Long id) {
		return studentMapper.map(userRepository.findById(id).get());
	}
	
	public StudentResponseDto update(StudentRequestDto studentRequestDto) {
		
		User student = studentMapper.map(studentRequestDto);
		
		return studentMapper.map(userRepository.save(student));
	}
	
	public void delete(Long id) {
		
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);			
		} else {
			throw new RuntimeException("User with " + id + " doesn't exist.");
		}
	}
	
}
