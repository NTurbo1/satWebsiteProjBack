package com.nturbo1.satWebsiteProjBack.service.mapper.courses;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.nturbo1.satWebsiteProjBack.repository.entities.courses.CourseSection;
import com.nturbo1.satWebsiteProjBack.service.dto.request.courses.CourseSectionRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.courses.CourseSectionResponseDto;

@Mapper(componentModel = "spring")
public interface CourseSectionMapper {

  List<CourseSectionResponseDto> mapToCourseSectionResponseDtoList(List<CourseSection> entityList);
	
	List<CourseSection> mapToCourseSectionList(List<CourseSectionRequestDto> dtoRequestList);
	
	CourseSectionResponseDto map(CourseSection entity);
	
	CourseSection map(CourseSectionRequestDto value);
	
	@Mappings(
		{
			@Mapping(target = "id", ignore = true),
			@Mapping(target = "course", ignore = true),
			@Mapping(target = "topics", ignore = true)
		}
	)
    void updateCourseSectionFromDto(
    		CourseSectionRequestDto dto, 
    		@MappingTarget CourseSection entity);
}
