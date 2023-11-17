package com.nturbo1.satWebsiteProjBack.service.users;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nturbo1.satWebsiteProjBack.repository.UserRepository;
import com.nturbo1.satWebsiteProjBack.repository.courses.CourseRepository;
import com.nturbo1.satWebsiteProjBack.repository.entities.User;
import com.nturbo1.satWebsiteProjBack.service.dto.request.users.StudentRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.courses.course.CourseResponseDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.users.StudentResponseDto;
import com.nturbo1.satWebsiteProjBack.service.mapper.courses.course.AdminCourseMapper;
import com.nturbo1.satWebsiteProjBack.service.mapper.courses.course.CourseMapper;
import com.nturbo1.satWebsiteProjBack.service.mapper.courses.course.StudentCourseMapper;
import com.nturbo1.satWebsiteProjBack.service.mapper.users.StudentMapper;

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
	private final StudentCourseMapper studentCourseMapper;
	private final AdminCourseMapper adminCourseMapper;
	
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
	
	public List<CourseResponseDto> getAllEnrolledCoursesByStudentIdForAdmin(Long studentId) {
		
		List<CourseResponseDto> courses = courseRepository.findAllEnrolledCoursesByStudentId(studentId)
			.stream()
			.map(course -> {
				return adminCourseMapper.map(course);		
			})
			.collect(Collectors.toList());
		
		return courses;
	}
	
	public List<CourseResponseDto> getAllEnrolledCoursesByStudentIdForStudent(Long studentId) {
		
		List<CourseResponseDto> courses = courseRepository.findAllEnrolledCoursesByStudentId(studentId)
				.stream()
				.map(course -> {
					return studentCourseMapper.map(course);		
				})
				.collect(Collectors.toList());
			
		return courses;
	}
	
	public StudentResponseDto update(Long studentId, StudentRequestDto studentRequestDto) {
		
		Optional<User> existingStudent = userRepository.findStudentByUserId(studentId);
		
		if (existingStudent.isPresent()) {
			
			User updatedStudent = existingStudent.get();
			studentMapper.updateStudentFromDto(studentRequestDto, updatedStudent);
			
			return studentMapper.map(userRepository.save(updatedStudent));
			
		} else {
			throw new IllegalArgumentException("Student with id = " + studentId +
					" doesn't exist.");
		}
	}
	
	public void delete(Long id) {
		
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);			
		} else {
			throw new RuntimeException("User with " + id + " doesn't exist.");
		}
	}
	
}
