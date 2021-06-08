package com.empresax.autonomo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GestaoMicroNegocioApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoMicroNegocioApplication.class, args);
		System.out.println(new BCryptPasswordEncoder(10).encode("aiaiaimeucaro"));
	}

}
