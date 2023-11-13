package com.nturbo1.satWebsiteProjBack.service.dto.response.courses;

import java.sql.Date;
import java.util.List;

import com.nturbo1.satWebsiteProjBack.repository.entities.courses.CourseSection;

public record CourseResponseDto(
	Long id,
	String name,
	String description,
	String type,
	Date createdDate,
	String status,
	Integer price,
	List<CourseSection> sections
) {

} 
