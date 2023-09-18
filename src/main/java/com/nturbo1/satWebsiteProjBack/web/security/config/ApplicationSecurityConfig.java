package com.nturbo1.satWebsiteProjBack.web.security.config;

import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nturbo1.satWebsiteProjBack.web.controller.RestApiConst;
import com.nturbo1.satWebsiteProjBack.web.security.jwtutils.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig {
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final AuthenticationProvider authenticationProvider;
	
	public ApplicationSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
									AuthenticationProvider authenticationProvider) {
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.authenticationProvider = authenticationProvider;
	}


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
				.csrf()
				.disable()
				.authorizeHttpRequests()
				.requestMatchers(RestApiConst.AUTH_API_ROOT_PATH + "/**")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
