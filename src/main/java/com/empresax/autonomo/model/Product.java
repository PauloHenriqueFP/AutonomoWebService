package com.empresax.autonomo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private Double price;
	
	private String measurement;
	
	private Integer quantity;
	
	private String description;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Supplier supplier;
	
	public Product() {}

	public Product(
			String name,
			Double price,
			String measurement,
			Integer quantity,
			String description,
			User user,
			Supplier supplier) {
		this.name = name;
		this.price = price;
		this.measurement = measurement;
		this.quantity = quantity;
		this.description = description;
		this.user = user;
		this.supplier = supplier;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
