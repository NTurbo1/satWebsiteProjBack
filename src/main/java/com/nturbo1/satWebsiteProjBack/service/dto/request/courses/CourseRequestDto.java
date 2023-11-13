package com.nturbo1.satWebsiteProjBack.service.dto.request.courses;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nturbo1.satWebsiteProjBack.repository.entities.courses.CourseSection;

@JsonIgnoreProperties({"enrolledCourses", "createdTests", "userTests"})
public record CourseRequestDto(
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
