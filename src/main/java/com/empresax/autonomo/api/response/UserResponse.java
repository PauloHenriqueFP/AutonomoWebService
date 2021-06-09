package com.empresax.autonomo.api.response;

import com.empresax.autonomo.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
	private Long id;
	private String name;
	private String email;
	
	public UserResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public UserResponse(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
	}
}
