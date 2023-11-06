package com.nturbo1.satWebsiteProjBack.service.mapper.courses;

import java.util.List;

import org.mapstruct.Mapper;

import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Topic;
import com.nturbo1.satWebsiteProjBack.service.dto.request.courses.TopicRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.courses.TopicResponseDto;

@Mapper(componentModel = "spring")
public interface TopicMapper {
  
  List<TopicResponseDto> mapToTopicResponseDtoList(List<Topic> entityList);
	
	List<Topic> mapToTopicList(List<TopicRequestDto> dtoRequestList);
	
	TopicResponseDto map(Topic entity);
	
	Topic map(TopicRequestDto value);
}
