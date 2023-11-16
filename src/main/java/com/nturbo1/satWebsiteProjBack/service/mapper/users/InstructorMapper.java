package com.nturbo1.satWebsiteProjBack.service.mapper.users;

import java.util.List;

import org.mapstruct.Mapper;

import com.nturbo1.satWebsiteProjBack.repository.entities.User;
import com.nturbo1.satWebsiteProjBack.service.dto.request.users.InstructorRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.users.InstructorResponseDto;

@Mapper(componentModel = "spring")
public interface InstructorMapper {
	
//	@Autowired
//	private TestMapper testMapper;
	
	List<InstructorResponseDto> mapToInstructorResponseDtoList(List<User> entityList);
	
	List<User> mapToUserList(List<InstructorRequestDto> dtoRequestList);
	
	InstructorResponseDto map(User entity);
	
//	@Mapping(target = "userTests", ignore = true)
//	@Mapping(target = "roles", ignore = true)
	User map(InstructorRequestDto value);
	
//	public abstract TestResponseDto testToTestResponseDto(Test test);
//	
//	public abstract Test testResponseDtoToTest(TestResponseDto testResponseDto);
//	
//	public abstract List<TestResponseDto> testEntityListToDtoList(List<Test> entityList);
//	
//	public abstract List<Test> dtoListToTestEntityList(List<TestResponseDto> dtoRequestList);
	
	
}
