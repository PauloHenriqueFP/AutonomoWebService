package com.empresax.autonomo.api.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class UserRequest {
	@NotBlank
	private String name;

	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Min(value = 6L)
	private String password; 
}
