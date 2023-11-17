package com.nturbo1.satWebsiteProjBack.service.dto.response.courses.course;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseResponseDto {
	
	private Long id;
	private String name;
	private String description;
	private String type;
	private Integer price;
}
