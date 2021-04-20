package com.empresax.autonomo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String name;
	
	@Email
	private String email;
	
	@NotEmpty
	private String cpf;
	
	@NotEmpty
	private String phone;
	
	@ManyToOne
	private User user;
	
	@OneToMany
	private List<Order> orders;
	
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

	public Client() {}

	public Client(@NotEmpty String name, @Email String email, @NotEmpty String cpf, @NotEmpty String phone,
			Address address) {
		this.name = name;
		this.email = email;
		this.cpf = cpf;
		this.phone = phone;
		this.address = address;
	}
}
