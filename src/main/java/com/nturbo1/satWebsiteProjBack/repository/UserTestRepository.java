package com.nturbo1.satWebsiteProjBack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nturbo1.satWebsiteProjBack.repository.entities.UserTest;

@Repository
public interface UserTestRepository extends JpaRepository<UserTest, Long>{
	
}
