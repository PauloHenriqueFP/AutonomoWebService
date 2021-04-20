package com.empresax.autonomo.api.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class AddressRequest {
	
	@NotBlank
	private String street;
	
	@NotBlank
	private String city;
	
	@NotNull
	private Integer number;
	
	@NotBlank
	private String cep;
	
	public AddressRequest() {}

	public AddressRequest(String street, String city, Integer number, String cep) {
		this.street = street;
		this.city = city;
		this.number = number;
		this.cep = cep;
	}

}
