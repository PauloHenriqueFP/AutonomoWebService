package com.empresax.autonomo.api.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
	@NotBlank
	private String name;

	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Length(min = 3)
	private String password; 
}
