package com.nturbo1.satWebsiteProjBack.repository.courses;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
	
	
	@Query("""
			select t from Topic t
			where t.courseSection.course.id = :courseId
			""")
	List<Topic> findAllTopicsByCourseId(@Param(value = "courseId") Long courseId);
	
	@Query("""
			select t from Topic t
			where t.courseSection.id = :courseSectionId and 
			t.courseSection.course.id = :courseId
			""")
	List<Topic> findAllTopicsByCourseIdAndCourseSectionId(
			@Param(value = "courseId") Long courseId,
			@Param(value = "courseSectionId") Long courseSectionId);

}
