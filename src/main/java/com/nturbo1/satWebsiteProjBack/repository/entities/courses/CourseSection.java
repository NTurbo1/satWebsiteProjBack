package com.nturbo1.satWebsiteProjBack.repository.entities.courses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "course_section")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseSection {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "course_id")
	@JsonBackReference
	private Course course;
	
	@OneToMany(
			mappedBy = "courseSection",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	@JsonManagedReference
	private List<Topic> topics;
}
