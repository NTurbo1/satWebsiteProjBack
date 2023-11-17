package com.nturbo1.satWebsiteProjBack.service.mapper.courses.course;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Course;
import com.nturbo1.satWebsiteProjBack.service.dto.request.courses.CourseRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.courses.course.EnrolledStudentCourseResponseDto;

@Mapper(componentModel = "spring")
public interface EnrolledStudentCourseMapper {

	List<EnrolledStudentCourseResponseDto> mapToDtoList(List<Course> entityList);
	
	@InheritInverseConfiguration
	List<Course> mapToEntityList(List<CourseRequestDto> dtoRequestList);
	
	EnrolledStudentCourseResponseDto map(Course entity);
	
	Course map(CourseRequestDto value);
}
