package com.nturbo1.satWebsiteProjBack.service.dto.response.courses.course;

import java.util.List;

import com.nturbo1.satWebsiteProjBack.repository.entities.courses.CourseSection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrolledStudentCourseResponseDto extends CourseResponseDto {
	
	private List<CourseSection> sections;

}
