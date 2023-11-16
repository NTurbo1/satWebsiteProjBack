package com.nturbo1.satWebsiteProjBack.service.users;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nturbo1.satWebsiteProjBack.repository.UserRepository;
import com.nturbo1.satWebsiteProjBack.repository.entities.User;
import com.nturbo1.satWebsiteProjBack.service.dto.request.users.UserRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.users.UserResponseDto;
import com.nturbo1.satWebsiteProjBack.service.mapper.users.UserMapper;

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
	
	public UserResponseDto update(Long userId, UserRequestDto userRequestDto) {
		
		Optional<User> existingUser = userRepository.findById(userId);
		
		if (existingUser.isPresent()) {
		
			User updatedUser = existingUser.get();
			userMapper.updateUserFromDto(userRequestDto, updatedUser);
			
			return userMapper.map(userRepository.save(updatedUser));
		
		} else {
			throw new RuntimeException("User with id = " + userId + " doesn't exist.");
		}
	}
	
	public void delete(Long id) {
		
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);			
		} else {
			throw new RuntimeException("User with id = " + id + " doesn't exist.");
		}
	}
	
}
