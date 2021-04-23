package com.empresax.autonomo.api.controller;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresax.autonomo.model.User;
import com.empresax.autonomo.service.UserService;

@RestController
@RequestMapping("/auth/login")
public class AuthController {
	
	private final UserService userService;
	
	@Autowired
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public ResponseEntity<?> grantAccess(@Valid @RequestBody Login login) {
		
		try {
			
			User userWithEmailPassed = this.userService.getUserByEmail(login.email);
			
			if(userWithEmailPassed.getPassword().equals(login.password)) {
				
				return ResponseEntity.ok(true);
				
			} else {
				
				return ResponseEntity.ok(false);
				
			}
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email do not exists");
			
		}
		
		
	}
	
}

 class Login {
	@NotEmpty
	@Email
	public String email;
	
	@NotEmpty
	@NotBlank
	public String password;
}
