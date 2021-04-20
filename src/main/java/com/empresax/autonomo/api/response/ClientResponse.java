package com.empresax.autonomo.api.response;

import com.empresax.autonomo.model.Address;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClientResponse {
	private Long id;
	private String name;
	private String email;
	private String cpf;
	private String phone;
	private Address address;
}
