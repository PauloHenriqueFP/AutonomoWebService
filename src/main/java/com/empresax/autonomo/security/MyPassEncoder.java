package com.empresax.autonomo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyPassEncoder {

	@Bean
	public PasswordEncoder getEncoder () {
		return new BCryptPasswordEncoder(10);
	}
	
}
