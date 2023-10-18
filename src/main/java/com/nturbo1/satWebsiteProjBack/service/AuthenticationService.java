package com.nturbo1.satWebsiteProjBack.service;

import java.util.Optional; 
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nturbo1.satWebsiteProjBack.repository.RoleRepository;
import com.nturbo1.satWebsiteProjBack.repository.TokenRepository;
import com.nturbo1.satWebsiteProjBack.repository.UserRepository;
import com.nturbo1.satWebsiteProjBack.repository.entities.Token;
import com.nturbo1.satWebsiteProjBack.repository.entities.User;
import com.nturbo1.satWebsiteProjBack.service.dto.request.AuthenticationRequest;
import com.nturbo1.satWebsiteProjBack.service.dto.request.RegisterRequest;
import com.nturbo1.satWebsiteProjBack.service.dto.response.AuthenticationResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private final RoleRepository roleRepository;
	private final TokenRepository tokenRepository;
	

	public void register(RegisterRequest request) {
		
		Optional<User> foundUser = userRepository.findByEmail(request.getEmail());
		
		if (!foundUser.isEmpty()) {
			System.out.println("Found User: " + foundUser);
			throw new RuntimeException("User with email " + request.getEmail() + " already exists.");
		}
		
		User user = User.builder()
				.firstName(request.getFirstname())
				.lastName(request.getLastname())
				.email(request.getEmail())
				.userPassword(passwordEncoder.encode(request.getPassword()))
				.roles(request.getRoles()
							  .stream()
							  .map(roleName -> roleRepository.findByRoleName(roleName).get())
							  .collect(Collectors.toList()))
				.build();
		
		System.out.println("User: " + user);
		
		userRepository.save(user);
	}

  	public AuthenticationResponse authenticate(AuthenticationRequest request) {
  		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				request.getEmail(), 
				request.getPassword()
			)
		);

		var user = userRepository.findByEmail(request.getEmail())
							.orElseThrow();

		String jwtToken = jwtService.generateToken(user);
		removeUserToken(user);
		saveUserToken(jwtToken, user);
		
		return AuthenticationResponse.builder()
								.token(jwtToken)
								.firstName(user.getFirstName())
								.lastName(user.getLastName())
								.build();
  	}
  	
  	private void removeUserToken(User user) {
  		Optional<Token> userToken = tokenRepository.findByUserUserId(user.getUserId());
  		
  		if (userToken.isEmpty()) 
  			return;
  		
  		tokenRepository.delete(userToken.get());
  		
  	}
  	
  	private void saveUserToken(String token, User user) {
  		Token tokenEntity = Token.builder()
				.user(user)
				.token(token)
				.tokenType("Bearer")
				.build();
  		
		tokenRepository.save(tokenEntity);
  	}

}	
