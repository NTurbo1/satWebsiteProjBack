package com.nturbo1.satWebsiteProjBack.web.controller.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.nturbo1.satWebsiteProjBack.web.controller.constants.UserRoleConst;

@Component
public class AuthorizationUtil {
	
	
	public String getAuthenticatedUserRole() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String role;
		
		if (authentication.getAuthorities().contains(
				new SimpleGrantedAuthority(UserRoleConst.ADMIN_ROLE)))
			role = UserRoleConst.ADMIN_ROLE;
		else
			role = UserRoleConst.STUDENT_ROLE;
		
		return role;
	}

}
