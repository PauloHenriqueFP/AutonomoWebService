package com.empresax.autonomo.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresax.autonomo.api.request.UserRequest;
import com.empresax.autonomo.api.response.UserResponse;
import com.empresax.autonomo.model.User;
import com.empresax.autonomo.repository.UserRepository;

@RestController
@RequestMapping("/signUp")
public class SignUpController {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder encoder;
	
	@PostMapping
	public ResponseEntity<?> signUp(@RequestBody @Valid UserRequest form) {
		
		boolean existsByEmail = userRepository.existsByEmail(form.getEmail());
		if(existsByEmail) {
			return new ResponseEntity<String>("Email já esta em uso", HttpStatus.UNAUTHORIZED);
		}
		
		User newUser = new User(form.getName(), form.getEmail(), encoder.encode(form.getPassword()));
		
		User savedUser = userRepository.save(newUser);
		
		return new ResponseEntity<UserResponse>( new UserResponse(savedUser) , HttpStatus.CREATED);
		
	}
	
}
