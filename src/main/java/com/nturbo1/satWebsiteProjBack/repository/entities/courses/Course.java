package com.nturbo1.satWebsiteProjBack.repository.entities.courses;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nturbo1.satWebsiteProjBack.repository.entities.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "course")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties({"sections"})
public class Course {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private String type;
	@Column(name = "created_date")
	private Date createdDate;
	private String status;
	private Integer price;
	
	@OneToMany(
			mappedBy = "course",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.EAGER 
			)
	@JsonManagedReference
	List<CourseSection> sections;
	
	@ManyToMany(mappedBy = "enrolledCourses",
			fetch = FetchType.LAZY)
	@JsonBackReference(value = "enrolledCourses")
	private List<User> enrolledStudents;
	
	
}
