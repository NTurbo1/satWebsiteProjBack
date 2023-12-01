package com.nturbo1.satWebsiteProjBack.web.security.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.nturbo1.satWebsiteProjBack.service.courses.CourseStatus;
import com.nturbo1.satWebsiteProjBack.web.controller.constants.RestApiConst;
import com.nturbo1.satWebsiteProjBack.web.controller.constants.UserRoleConst;
import com.nturbo1.satWebsiteProjBack.web.security.jwtutils.JwtAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
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
				.cors(Customizer.withDefaults())
				.authorizeHttpRequests( auth -> {
					
					auth.requestMatchers(RestApiConst.AUTH_API_ROOT_PATH + "/**")
						.permitAll();
					
					auth.requestMatchers(RestApiConst.CHARGE_PAYMENT_PATH)
						.hasAuthority(UserRoleConst.STUDENT_ROLE);
					
					// Everyone can see the general info about available(status='ACTIVE') courses
					auth.
						requestMatchers(request -> {
							String url = request.getRequestURL().toString();
							if (!url.equals("http://localhost:8080/api/v1/courses"))
								return false;
							
							String param = request.getParameter("status");
							
							if (param == null) return false;
							
							return param.equals(CourseStatus.ACTIVE);
						})
						.permitAll();
					
					auth.requestMatchers(
							RestApiConst.STUDENT_API_ROOT_PATH + "/**")
						.authenticated();
					
					auth.requestMatchers(
							RestApiConst.COURSES_API_ROOT_PATH + "/**")
						.authenticated();
					
					auth.anyRequest().hasAuthority(UserRoleConst.ADMIN_ROLE);
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
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173", "https://nturbo1.github.io/sat-website-proj-front"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		configuration.setAllowedHeaders(List.of("Authorization", "Content-type"));
		configuration.setAllowCredentials(true);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		
		return source;
	}
}
