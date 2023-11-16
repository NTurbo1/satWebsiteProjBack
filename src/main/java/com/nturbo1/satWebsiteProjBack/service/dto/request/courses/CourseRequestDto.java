package com.nturbo1.satWebsiteProjBack.service.dto.request.courses;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"enrolledCourses", "createdTests", "userTests"})
public record CourseRequestDto(
	String name,
	String description,
	String type,
	Date createdDate,
	String status,
	Integer price
) {

} 
