package com.nturbo1.satWebsiteProjBack.repository.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "question")
@Getter
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id")
	private Long questionId;
	@Column(name = "question_text")
	private String questionText;
	@Column(name = "correct_answer")
	private String correctAnswer;
	
	@ManyToMany
	private List<QuestionType> questionTypes;

	@ManyToOne
	@JoinColumn(name = "test_id")
	private Test test;

	public Question() {}
	
	
}
