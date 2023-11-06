package com.nturbo1.satWebsiteProjBack.service.mapper.courses;

import java.util.List;

import org.mapstruct.Mapper;

import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Course;
import com.nturbo1.satWebsiteProjBack.service.dto.request.courses.CourseRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.courses.CourseResponseDto;

@Mapper(componentModel = "spring")
public interface CourseMapper {

	List<CourseResponseDto> mapToCourseResponseDtoList(List<Course> entityList);
	
	List<Course> mapToCourseList(List<CourseRequestDto> dtoRequestList);
	
	CourseResponseDto map(Course entity);
	
	Course map(CourseRequestDto value);
}
