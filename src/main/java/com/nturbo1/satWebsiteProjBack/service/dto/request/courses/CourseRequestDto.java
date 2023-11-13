package com.nturbo1.satWebsiteProjBack.service.dto.request.courses;

import java.sql.Date;
import java.util.List;

import com.nturbo1.satWebsiteProjBack.repository.entities.User;
import com.nturbo1.satWebsiteProjBack.repository.entities.courses.CourseSection;

public record CourseRequestDto(
	Long id,
	String name,
	String description,
	String type,
	Date createdDate,
	String status,
	Integer price,
	List<CourseSection> sections,
	List<User> enrolledStudents
) {

} 
