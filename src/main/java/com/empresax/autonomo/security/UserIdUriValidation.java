package com.empresax.autonomo.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.empresax.autonomo.model.User;

/*
 * This class allow users to only access their resourses
 * For example: User with id 1 cannot access /users/2
 */

@Component("userSecurity")
public class UserIdUriValidation {
	
	public boolean hasUserId(Authentication authentication, Long userId) {
		try {
			
			User principal = (User) authentication.getPrincipal();	
			
			if(principal.getId() == userId) {
				return true;
			}
			
			return false;
			
		} catch(Exception e) {
			
			return false;

		}
	}
	
}
