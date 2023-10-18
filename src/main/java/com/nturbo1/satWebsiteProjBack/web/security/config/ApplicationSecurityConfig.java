package com.nturbo1.satWebsiteProjBack.web.security.config;

import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.nturbo1.satWebsiteProjBack.web.controller.RestApiConst;
import com.nturbo1.satWebsiteProjBack.web.security.jwtutils.JwtAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class ApplicationSecurityConfig {
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final AuthenticationProvider authenticationProvider;
	private final LogoutHandler logoutHandler;


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
//				.requiresChannel(channel -> 
//					channel.anyRequest().requiresSecure()
//				)
				.csrf( auth -> auth.disable())
				.authorizeHttpRequests( auth -> {
					auth.requestMatchers(RestApiConst.AUTH_API_ROOT_PATH + "/**").permitAll();
					auth.anyRequest().authenticated();
				})
				.sessionManagement( auth -> {
					auth.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				})
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.logout(logout -> {
					logout.logoutUrl(RestApiConst.LOGOUT_AUTH_PATH);
					logout.addLogoutHandler(logoutHandler);
					logout.logoutSuccessHandler(
						(request, response, authentication) -> {
							SecurityContextHolder.clearContext();
					});
				});
		
		return http.build();
	}
}
