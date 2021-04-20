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
@Table(name = "suppliers")
public class Supplier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String cnpj;
	
	@NotEmpty
	@Email
	private String email;
	
	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	private List<Phone> phones;
	
	@Valid
	@ManyToOne
	private User user;
	
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	public Supplier() {
		// TODO Auto-generated constructor stub
	}

	public Supplier(
			Long id, 
			@NotEmpty String name, 
			@NotEmpty String cnpj, 
			@NotEmpty @Email String email,
			@Valid List<Phone> phones, 
			@Valid User user, 
			@Valid Address address) {
		this.id = id;
		this.name = name;
		this.cnpj = cnpj;
		this.email = email;
		this.phones = phones;
		this.user = user;
		this.address = address;
	}

	
}
