package com.nturbo1.satWebsiteProjBack.repository.courses;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nturbo1.satWebsiteProjBack.repository.entities.courses.CourseSection;

@Repository
public interface CourseSectionRepository extends JpaRepository<CourseSection, Long> {

	@Query("""
			select cs from CourseSection cs
			where cs.course.id = :courseId
			""")
	List<CourseSection> findCourseSectionsByCourseId(
			@Param(value = "courseId") Long courseId);
}
