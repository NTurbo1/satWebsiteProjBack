package com.nturbo1.satWebsiteProjBack.service.users;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nturbo1.satWebsiteProjBack.repository.UserRepository;
import com.nturbo1.satWebsiteProjBack.repository.courses.CourseRepository;
import com.nturbo1.satWebsiteProjBack.repository.entities.User;
import com.nturbo1.satWebsiteProjBack.service.dto.request.users.StudentRequestDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.courses.GeneralCourseResponseDto;
import com.nturbo1.satWebsiteProjBack.service.dto.response.users.StudentResponseDto;
import com.nturbo1.satWebsiteProjBack.service.mapper.courses.CourseMapper;
import com.nturbo1.satWebsiteProjBack.service.mapper.courses.GeneralCourseMapper;
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
	private final GeneralCourseMapper generalCourseMapper;
	
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
	
	public List<GeneralCourseResponseDto> getAllEnrolledCoursesByStudentId(Long studentId) {
		return generalCourseMapper.mapToGeneralCourseResponseDtoList(
				courseRepository.findAllEnrolledCoursesByStudentId(studentId));
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
