package com.empresax.autonomo.api.response;

import java.util.List;

import com.empresax.autonomo.model.Address;
import com.empresax.autonomo.model.Phone;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SupplierResponse {
	private Long id;
	private String name;
	private String cnpj;
	private String email;
	private Address address;
	private List<Phone> phones;
}
