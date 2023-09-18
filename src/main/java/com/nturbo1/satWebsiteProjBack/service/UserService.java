package com.nturbo1.satWebsiteProjBack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nturbo1.satWebsiteProjBack.repository.UserRepository;
import com.nturbo1.satWebsiteProjBack.repository.entities.User;
import com.nturbo1.satWebsiteProjBack.service.dto.request.UserRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.UserResponseDto;
import com.nturbo1.satWebsiteProjBack.service.mapper.UserMapper;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	
	public UserService(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}
	
	public List<UserResponseDto> readAll() {
		return userMapper
					.mapToUserResponseDtoList(userRepository.findAll());
	}
	
	public UserResponseDto readById(Long id) {
		return userMapper.map(userRepository.findById(id).get());
	}
	
	// we don't use useMapper here because it should return 
	// UserDetails obj (User implements UserDetails) to implement
	// the UserDetailsService in ApplicationSecurityConfig
	public Optional<User> readByEmail(String email) {
		return Optional.of(userRepository.findByEmail(email).get());
	}
	
	public UserResponseDto update(UserRequestDto userRequestDto) {
		
		User user = userMapper.map(userRequestDto);
		
		return userMapper.map(userRepository.save(user));
	}
	
	public void delete(Long id) {
		
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);			
		} else {
			throw new RuntimeException("User with " + id + " doesn't exist.");
		}
	}
	
}
