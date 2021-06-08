package com.empresax.autonomo.api.controller;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresax.autonomo.security.JwtUtilService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private JwtUtilService jwtUtilService;
	
	@Autowired
	private AuthenticationManager authenticationManager; // bean provided in SecurityConfig.java (Spring cannot inject for itself)
	
	@PostMapping
	public ResponseEntity<?> signIn(@Valid @RequestBody Login login) {
		
		Authentication credentials = new UsernamePasswordAuthenticationToken(login.email, login.password);
		
		try {
			
			Authentication principal = authenticationManager.authenticate(credentials);
			String jwt = jwtUtilService.generate(principal);
			
			return ResponseEntity.ok(jwt);
			
		} catch (AuthenticationException e) {
			
			return ResponseEntity.badRequest().build();
			
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
