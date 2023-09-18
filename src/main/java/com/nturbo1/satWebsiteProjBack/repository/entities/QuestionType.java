package com.nturbo1.satWebsiteProjBack.repository.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "question_type")
@Getter
public class QuestionType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "type_id")
	private Long typeId;
	@Column
	private String typeName;
	
	@ManyToMany(mappedBy = "questionTypes")
	private List<Question> questions = new ArrayList<>();

	public QuestionType() {}
}
