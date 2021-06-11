package com.empresax.autonomo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresax.autonomo.exception.ResourceNotFoundException;
import com.empresax.autonomo.model.User;
import com.empresax.autonomo.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User save(User user) {
		return this.userRepository.save(user);
	}

	public void delete(Long id) {
		this.userRepository.deleteById(id);
	}
	
	public User getUserById(Long id) throws RuntimeException {
		return this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " was not found"));
	}
	
	public User getUserByEmail(String email) throws Exception {
		return this.userRepository.findByEmail(email)
				.orElseThrow(() -> new Exception("Invalid email "));
	}
	
	public List<User> getUsers() {
		return this.userRepository.findAll();
	}

}
