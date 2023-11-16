package com.nturbo1.satWebsiteProjBack.service.mapper.users;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.nturbo1.satWebsiteProjBack.repository.entities.User;
import com.nturbo1.satWebsiteProjBack.service.dto.request.users.StudentRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.request.users.UserRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.users.UserResponseDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

	List<UserResponseDto> mapToUserResponseDtoList(List<User> entityList);
	
	List<User> mapToUserList(List<UserRequestDto> dtoRequestList);
	
	UserResponseDto map(User entity);
	
	User map(UserRequestDto value);
	
	@Mappings({
		@Mapping(target = "userId", ignore = true),
		@Mapping(target = "roles", ignore = true),
		@Mapping(target = "enrolledCourses", ignore = true),
		@Mapping(target = "createdTests", ignore = true),
		@Mapping(target = "userTests", ignore = true),
		@Mapping(target = "token", ignore = true)
	})
	void updateUserFromDto(UserRequestDto dto, @MappingTarget User user);
}
