package com.nturbo1.satWebsiteProjBack.repository.entities.courses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "topic")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private String text;
	
	@ManyToOne
	@JoinColumn(name = "course_section_id")
	@JsonBackReference
	private CourseSection courseSection;
	
	@ManyToMany(
		cascade = {CascadeType.PERSIST, CascadeType.MERGE}
	)
	@JoinTable(
	    name = "topics_videos",
	    joinColumns = @JoinColumn(name = "topic_id"),
	    inverseJoinColumns = @JoinColumn(name = "video_id")
	)
	@JsonManagedReference
	private List<Video> videos;
	
}
