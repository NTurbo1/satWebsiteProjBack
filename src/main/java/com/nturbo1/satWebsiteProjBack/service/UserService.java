package com.nturbo1.satWebsiteProjBack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nturbo1.satWebsiteProjBack.repository.UserRepository;
import com.nturbo1.satWebsiteProjBack.repository.entities.User;
import com.nturbo1.satWebsiteProjBack.service.dto.request.UserRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.UserResponseDto;
import com.nturbo1.satWebsiteProjBack.service.mapper.UserMapper;

import lombok.Data;

@Service
@Data
public class UserService {
	
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	
	public List<UserResponseDto> readAll() {
		return userMapper
					.mapToUserResponseDtoList(userRepository.findAll());
	}
	
	public List<UserResponseDto> readAllStudents() {
		return userMapper
					.mapToUserResponseDtoList(userRepository.findAllStudents());
	}
	
	public UserResponseDto readById(Long id) {
		Optional<User> user = userRepository.findById(id);
		
		if (user.isPresent()) {
			return userMapper.map(user.get());
		} else {
			throw new RuntimeException("User with " + id + " doesn't exist.");
		}
		
	}
	
	// we don't use useMapper here because it should return 
	// UserDetails obj (User implements UserDetails) to implement
	// the UserDetailsService in ApplicationSecurityConfig
	public Optional<User> readByEmail(String email) {
		return Optional.of(userRepository.findByEmail(email).get());
	}
	
	public UserResponseDto update(UserRequestDto userRequestDto) {
		
		User user = userMapper.map(userRequestDto);
		
		if (userRepository.existsById(user.getUserId()))
			return userMapper.map(userRepository.save(user));
		else
			throw new RuntimeException("User with id = " + user.getUserId() + " doesn't exist.");
	}
	
	public void delete(Long id) {
		
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);			
		} else {
			throw new RuntimeException("User with id = " + id + " doesn't exist.");
		}
	}
	
}
