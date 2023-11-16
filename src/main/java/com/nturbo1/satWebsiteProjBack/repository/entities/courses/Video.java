package com.nturbo1.satWebsiteProjBack.repository.entities.courses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "video")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private String link;
	
	@ManyToMany(mappedBy = "videos")
	@JsonBackReference
	private List<Topic> topics;

}
