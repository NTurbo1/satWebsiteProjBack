package com.nturbo1.satWebsiteProjBack.service.mapper.users;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.nturbo1.satWebsiteProjBack.repository.entities.User;
import com.nturbo1.satWebsiteProjBack.service.dto.request.users.StudentRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.users.StudentResponseDto;

@Mapper(componentModel = "spring")
public interface StudentMapper {
	
	List<StudentResponseDto> mapToStudentResponseDtoList(List<User> entityList);
	
	List<User> mapToUserList(List<StudentRequestDto> dtoRequestList);
	
	StudentResponseDto map(User entity);
	
	User map(StudentRequestDto value);
	
	@Mappings({
		@Mapping(target = "userId", ignore = true),
		@Mapping(target = "roles", ignore = true),
		@Mapping(target = "enrolledCourses", ignore = true),
		@Mapping(target = "createdTests", ignore = true),
		@Mapping(target = "userTests", ignore = true),
		@Mapping(target = "token", ignore = true)
	})
	void updateStudentFromDto(StudentRequestDto dto, @MappingTarget User user);
}
