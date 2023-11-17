package com.nturbo1.satWebsiteProjBack.service.mapper.courses.course;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;

import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Course;
import com.nturbo1.satWebsiteProjBack.service.dto.request.courses.CourseRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.courses.course.CourseResponseDto;

@Mapper(componentModel = "spring",
		nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION
)
public interface CourseMapper {

	List<CourseResponseDto> mapToCourseResponseDtoList(List<Course> entityList);
	
	List<Course> mapToCourseList(List<CourseRequestDto> dtoRequestList);
	
	CourseResponseDto map(Course entity);
	
	Course map(CourseRequestDto value);
	
	@Mappings(
			{
				@Mapping(target = "id", ignore = true),
				@Mapping(target = "sections", ignore = true),
				@Mapping(target = "enrolledStudents", ignore = true),
				@Mapping(target = "createdDate", ignore = true)
			}
	)
	void updateCourseFromDto(CourseRequestDto dto, 
			@MappingTarget Course entity);
}
