package com.empresax.autonomo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresax.autonomo.api.response.UserResponse;
import com.empresax.autonomo.model.User;
import com.empresax.autonomo.service.UserService;

@RestController
@RequestMapping("/users/{userId}")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<UserResponse> getUserDetails(@PathVariable(name = "userId") Long userId) {
		
		User user = userService.getUserById(userId);
		
		var userResponse = new UserResponse();
		userResponse.setId( user.getId() );
		userResponse.setEmail( user.getEmail() );
		userResponse.setName(  user.getName() );
		
		return ResponseEntity.ok(userResponse);
		
	}
	
}
