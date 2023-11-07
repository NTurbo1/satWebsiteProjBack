package com.nturbo1.satWebsiteProjBack.service.security;

import org.springframework.stereotype.Service;

import com.nturbo1.satWebsiteProjBack.repository.TokenRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TokenService {

	private final TokenRepository tokenRepository;
	
	public boolean isUserTokenExists(String token) {
		return !tokenRepository.findByToken(token).isEmpty();
	}
}
