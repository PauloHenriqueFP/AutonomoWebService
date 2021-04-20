package com.empresax.autonomo.api.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class ClientRequest {
	@NotBlank
	private String name;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String cpf;
	
	@NotBlank
	private String phone;
	
	@NotNull
	private AddressRequest address; 
}
