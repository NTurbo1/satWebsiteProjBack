package com.nturbo1.satWebsiteProjBack.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.nturbo1.satWebsiteProjBack.repository.entities.User;
import com.nturbo1.satWebsiteProjBack.service.dto.request.UserRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.UserResponseDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

	List<UserResponseDto> mapToUserResponseDtoList(List<User> entityList);
	
	List<User> mapToUserList(List<UserRequestDto> dtoRequestList);
	
	UserResponseDto map(User entity);
	
	User map(UserRequestDto value);
}
