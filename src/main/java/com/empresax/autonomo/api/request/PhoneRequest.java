package com.empresax.autonomo.api.request;

import lombok.Getter;

@Getter
public class PhoneRequest {
	
	private String number;
	
	public PhoneRequest() {
		// TODO Auto-generated constructor stub
	}

	public PhoneRequest(String number) {
		super();
		this.number = number;
	}
	
}
