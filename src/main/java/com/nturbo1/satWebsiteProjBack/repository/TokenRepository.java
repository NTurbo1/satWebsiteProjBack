package com.nturbo1.satWebsiteProjBack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nturbo1.satWebsiteProjBack.repository.entities.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long>{

	Optional<Token> findByToken(String token);
	
	Optional<Token> findByUserUserId(Long userId);
}
