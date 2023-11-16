package com.nturbo1.satWebsiteProjBack.service.courses.utils;

import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Course;
import com.nturbo1.satWebsiteProjBack.repository.entities.courses.CourseSection;
import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Topic;
import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Video;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExistingCourseRelatedEntities {

	private Course existingCourse;
	private CourseSection existingCourseSection;
	private Topic existingTopic;
	private Video exisitingVideo;
}
