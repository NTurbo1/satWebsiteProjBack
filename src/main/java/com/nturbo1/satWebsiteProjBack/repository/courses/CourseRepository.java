package com.nturbo1.satWebsiteProjBack.repository.courses;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

	@Query("""
			select c from Course c\s
			where c.status = 'ACTIVE'
			""")
	List<Course> getAllActiveCourses();
	
	@Query("""
			select c from Course c\s
			where c.status = 'INACTIVE'
			""")
	List<Course> getAllInactiveCourses();
	
	@Query("""
			select c from Course c\s
			where c.status = 'PAUSED'
			""")
	List<Course> getAllPausedCourses();
}
