package com.nturbo1.satWebsiteProjBack.service.security;

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
  		
  		System.out.println("The email and password match.");

		var user = userRepository
						.findByEmail(request.getEmail())
						.orElseThrow();

		String jwtToken = jwtService.generateToken(user);
		System.out.println("New token generated.");
		removeUserToken(user);
		System.out.println("Old token removed.");
		saveUserToken(jwtToken, user);
		System.out.println("new token saved.");
		
		return AuthenticationResponse.builder()
								.token(jwtToken)
								.firstName(user.getFirstName())
								.lastName(user.getLastName())
								.userId(user.getUserId())
								.build();
  	}
  	
  	private void removeUserToken(User user) {
  		Optional<Token> userToken = tokenRepository.findByUser(user);
  		
  		if (userToken.isEmpty()) {
  			System.out.println("userToken is empty");
  			return;
  		}
  		
  		System.out.println("userToken: " + userToken.get());
  		
  		user.setToken(null); // SYNCHRONIZING THE OTHER SIDE OF RELATIONSHIP, WHICH IS THE PARENT
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
