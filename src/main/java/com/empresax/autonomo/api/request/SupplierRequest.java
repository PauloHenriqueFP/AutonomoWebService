package com.empresax.autonomo.api.request;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.empresax.autonomo.model.Phone;

import lombok.Getter;

@Getter
public class SupplierRequest {
	@NotBlank
	private String name;
	
	@NotBlank
	private String cnpj;
	
	@NotBlank
	@Email
	private String email;
	
	@NotNull
	private AddressRequest address;
	
	@NotEmpty
	private List<Phone> phones;
}
