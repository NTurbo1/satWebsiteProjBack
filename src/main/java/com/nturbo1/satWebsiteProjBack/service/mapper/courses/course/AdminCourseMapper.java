package com.nturbo1.satWebsiteProjBack.service.mapper.courses.course;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Course;
import com.nturbo1.satWebsiteProjBack.service.dto.request.courses.CourseRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.courses.course.AdminSpecificCourseResponseDto;

@Mapper(componentModel = "spring")
public interface AdminCourseMapper {
	
	List<AdminSpecificCourseResponseDto> mapToDtoList(List<Course> entityList);
	
	@InheritInverseConfiguration
	List<Course> mapToEntityList(List<CourseRequestDto> dtoRequestList);
	
	AdminSpecificCourseResponseDto map(Course entity);
	
	Course map(CourseRequestDto value);
}
