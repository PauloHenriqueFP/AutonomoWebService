package com.empresax.autonomo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
public class MyAuthorities implements GrantedAuthority {

	private static final long serialVersionUID = -3321579100522085168L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String role;

	@Override
	public String getAuthority() {
		return this.role;
	}
	
}
