package com.nturbo1.satWebsiteProjBack.service.mapper.courses;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Topic;
import com.nturbo1.satWebsiteProjBack.service.dto.request.courses.TopicRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.courses.TopicResponseDto;

@Mapper(componentModel = "spring")
public interface TopicMapper {
  
	List<TopicResponseDto> mapToTopicResponseDtoList(List<Topic> entityList);
	
	List<Topic> mapToTopicList(List<TopicRequestDto> dtoRequestList);
	
	TopicResponseDto map(Topic entity);
	
	Topic map(TopicRequestDto value);
	
	@Mappings({
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "courseSection", ignore = true),
		@Mapping(target = "videos", ignore = true)
	})
	void updateTopicFromDto(TopicRequestDto dto, @MappingTarget Topic entity);
}
