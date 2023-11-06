package com.nturbo1.satWebsiteProjBack.service.mapper.courses;

import java.util.List;

import org.mapstruct.Mapper;

import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Video;
import com.nturbo1.satWebsiteProjBack.service.dto.request.courses.VideoRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.courses.VideoResponseDto;

@Mapper(componentModel = "spring")
public interface VideoMapper {

  List<VideoResponseDto> mapToVideoResponseDtoList(List<Video> entityList);
	
	List<Video> mapToVideoList(List<VideoRequestDto> dtoRequestList);
	
	VideoResponseDto map(Video entity);
	
	Video map(VideoRequestDto value);
  
}
