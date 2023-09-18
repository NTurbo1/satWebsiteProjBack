package com.nturbo1.satWebsiteProjBack.repository.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "test")
@Getter
public class Test {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "test_id")
	private long testId;
	@Column
	private String testName;
	@Column
	private int durationSec;
	
	@ManyToOne
	@JoinColumn(name = "creator_id")
	private User creator;
	
	@OneToMany(mappedBy = "test")
	private List<Question> questions;
	
	@OneToMany(mappedBy = "test")
	private List<UserTest> userTests;
	
	public Test() {}
	
}
