package com.nturbo1.satWebsiteProjBack.service.users;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nturbo1.satWebsiteProjBack.repository.UserRepository;
import com.nturbo1.satWebsiteProjBack.repository.courses.CourseRepository;
import com.nturbo1.satWebsiteProjBack.repository.entities.User;
import com.nturbo1.satWebsiteProjBack.service.dto.request.StudentRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.StudentResponseDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.courses.CourseResponseDto;
import com.nturbo1.satWebsiteProjBack.service.mapper.StudentMapper;
import com.nturbo1.satWebsiteProjBack.service.mapper.courses.CourseMapper;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class StudentService {
	
	private final UserRepository userRepository;
	private final CourseRepository courseRepository;
	private final StudentMapper studentMapper;
	private final CourseMapper courseMapper;
	
	public List<StudentResponseDto> readAllStudents() {
		return studentMapper
					.mapToStudentResponseDtoList(userRepository.findAllStudents());
	}
	
	public StudentResponseDto readById(Long id) {
		Optional<User> student = userRepository.findStudentByUserId(id);
		
		if (student.isPresent())
			return studentMapper.map(student.get());
		else
			throw new RuntimeException("Student with id = " + id + " doesn't exist.");
	}
	
	public List<CourseResponseDto> getAllEnrolledCoursesByStudentId(Long studentId) {
		return courseMapper.mapToCourseResponseDtoList(
				courseRepository.findAllEnrolledCoursesByStudentId(studentId));
	}
	
	public StudentResponseDto update(StudentRequestDto studentRequestDto) {
		
		User student = studentMapper.map(studentRequestDto);
		
		return studentMapper.map(userRepository.save(student));
	}
	
	public void delete(Long id) {
		
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);			
		} else {
			throw new RuntimeException("User with " + id + " doesn't exist.");
		}
	}
	
}
