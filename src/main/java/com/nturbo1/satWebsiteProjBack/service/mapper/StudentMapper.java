package com.nturbo1.satWebsiteProjBack.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.nturbo1.satWebsiteProjBack.repository.entities.User;
import com.nturbo1.satWebsiteProjBack.service.dto.request.StudentRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.StudentResponseDto;

@Mapper(componentModel = "spring")
public interface StudentMapper {
	
	List<StudentResponseDto> mapToStudentResponseDtoList(List<User> entityList);
	
	List<User> mapToUserList(List<StudentRequestDto> dtoRequestList);
	
	StudentResponseDto map(User entity);
	
	User map(StudentRequestDto value);
}
