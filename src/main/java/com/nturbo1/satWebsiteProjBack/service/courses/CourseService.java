package com.nturbo1.satWebsiteProjBack.service.courses;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nturbo1.satWebsiteProjBack.repository.courses.CourseRepository;
import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Course;
import com.nturbo1.satWebsiteProjBack.service.dto.request.courses.CourseRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.courses.CourseResponseDto;
import com.nturbo1.satWebsiteProjBack.service.mapper.courses.CourseMapper;

import lombok.Data;

@Service
@Data
public class CourseService {
	
	private final CourseRepository courseRepository;
  private final CourseMapper courseMapper;
	
	public CourseResponseDto createCourse(CourseRequestDto courseRequestDto) {
    Course newCourse = courseMapper.map(courseRequestDto);
		return courseMapper.map(courseRepository.save(newCourse));
	}

	public List<CourseResponseDto> getAllCourses() {
		return courseMapper.mapToCourseResponseDtoList(courseRepository.findAll());
	}

	public Optional<CourseResponseDto> getCourseById(Long id) {
		return Optional.of(
      courseMapper
        .map(courseRepository.findById(id).get())
    );
	}

	public CourseResponseDto updateCourse(Long id, CourseRequestDto courseRequestDto) {
		if (courseRepository.existsById(id)) {
      Course updatedCourse = courseMapper.map(courseRequestDto);
			return courseMapper.map(courseRepository.save(updatedCourse));
		} else {
			throw new IllegalArgumentException("Course with id " + id + " does not exist.");
		}
	}

	public void deleteCourse(Long id) {
		if (courseRepository.existsById(id)) {
			courseRepository.deleteById(id);
		} else {
			throw new IllegalArgumentException("Course with id " + id + " does not exist.");
		}
	}

}
