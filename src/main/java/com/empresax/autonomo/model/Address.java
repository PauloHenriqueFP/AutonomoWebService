package com.empresax.autonomo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "addresses")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String street;
	
	private String city;
	
	private Integer number;
	
	private String cep;

	public Address() {}
	
	public Address( String street,  String city,  Integer number,  String cep) {
		this.street = street;
		this.city = city;
		this.number = number;
		this.cep = cep;
	}
}
