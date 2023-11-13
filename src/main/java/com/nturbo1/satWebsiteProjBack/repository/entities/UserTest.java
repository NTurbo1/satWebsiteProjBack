package com.nturbo1.satWebsiteProjBack.repository.entities;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nturbo1.satWebsiteProjBack.repository.entities.compositeKeys.UserTestId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "user_test")
@IdClass(UserTestId.class)
@Getter
public class UserTest {
	
	@Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "test_id")
    private Long testId;
    
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	@JsonBackReference
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "test_id", referencedColumnName = "test_id")
	private Test test;
	
	@Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    @Column(name = "score")
    private int score;
}
