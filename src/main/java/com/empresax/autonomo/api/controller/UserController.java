package com.empresax.autonomo.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresax.autonomo.model.User;
import com.empresax.autonomo.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {
	
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users =  this.userService.getUsers();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable(required = true) Long id) {
		User user = this.userService.getUserById(id);
		return ResponseEntity.ok(user);
	}	
	
	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User savedUser = this.userService.save(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(required = true) Long id,
										   @RequestBody User user) {
		user.setId(id);
		User savedUser = this.userService.save(user);
		return ResponseEntity.ok(savedUser);
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable(required = true) Long id) {
		this.userService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}
	
}
