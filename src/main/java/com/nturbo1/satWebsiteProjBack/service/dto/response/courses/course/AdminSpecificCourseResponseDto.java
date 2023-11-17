package com.nturbo1.satWebsiteProjBack.service.dto.response.courses.course;

import java.sql.Date;
import java.util.List;

import com.nturbo1.satWebsiteProjBack.repository.entities.courses.CourseSection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminSpecificCourseResponseDto extends CourseResponseDto {
   
   private Date createdDate;
   private String status;
   private List<CourseSection> sections;
   
   

} 
