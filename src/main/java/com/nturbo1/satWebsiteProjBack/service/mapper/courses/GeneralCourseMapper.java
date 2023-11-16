package com.nturbo1.satWebsiteProjBack.service.mapper.courses;

import java.util.List;

import org.mapstruct.Mapper;

import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Course;
import com.nturbo1.satWebsiteProjBack.service.dto.request.courses.CourseRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.courses.GeneralCourseResponseDto;

@Mapper(componentModel = "spring")
public interface GeneralCourseMapper {
	
	List<GeneralCourseResponseDto> mapToGeneralCourseResponseDtoList(List<Course> entityList);
	
	List<Course> mapToCourseList(List<CourseRequestDto> dtoRequestList);
	
	GeneralCourseResponseDto map(Course entity);
	
	Course map(CourseRequestDto value);
}
