package com.nturbo1.satWebsiteProjBack.service.courses;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nturbo1.satWebsiteProjBack.repository.courses.CourseSectionRepository;
import com.nturbo1.satWebsiteProjBack.repository.entities.courses.CourseSection;

import lombok.Data;

@Service
@Data
public class CourseSectionService {
	
	private final CourseSectionRepository courseSectionRepository;

	public CourseSection createCourseSection(CourseSection courseSection) {
		return courseSectionRepository.save(courseSection);
	}

	public List<CourseSection> getAllCourseSections() {
		return courseSectionRepository.findAll();
	}

	public Optional<CourseSection> getCourseSectionById(Long id) {
		return courseSectionRepository.findById(id);
	}

	public CourseSection updateCourseSection(Long id, CourseSection courseSection) {
		if (courseSectionRepository.existsById(id)) {
			courseSection.setId(id);
			return courseSectionRepository.save(courseSection);
		} else {
			throw new IllegalArgumentException("CourseSection with id " + id + " does not exist.");
		}
	}

	public void deleteCourseSection(Long id) {
		if (courseSectionRepository.existsById(id)) {
			courseSectionRepository.deleteById(id);
		} else {
			throw new IllegalArgumentException("CourseSection with id " + id + " does not exist.");
		}
	}
}
