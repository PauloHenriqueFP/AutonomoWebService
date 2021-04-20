package com.empresax.autonomo.model;

import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double price;
	
	private OrderStatus status;
	
	@OneToMany
	private List<OrderItem> itens;
	
	private OffsetDateTime openingDate;
	
	private OffsetDateTime finalizedDate;
	
	@ManyToOne
	private Client client;

}
