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
	
	@NotBlank
	private String neighborhood;
	
	@NotNull
	private Integer number;
	
	@NotBlank
	private String cep;
	
	public AddressRequest() {}

	public AddressRequest(@NotBlank String street, @NotBlank String city, @NotBlank String neighborhood,
			@NotNull Integer number, @NotBlank String cep) {
		this.street = street;
		this.city = city;
		this.neighborhood = neighborhood;
		this.number = number;
		this.cep = cep;
	}

	

}
