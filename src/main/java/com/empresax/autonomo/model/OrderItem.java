package com.empresax.autonomo.model;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_itens")
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany
	private List<Product> products;
	
	public Double getOrderItemPrice() {
		// Lambdas
		Function<Product, Double> getPrice = (product) -> product.getPrice();
		BinaryOperator<Double> getTotal = (a, price) -> a + price;
		
		// Streams
		Double total = this.products.stream().map(getPrice).reduce(0.0, getTotal);
		return total;
	}

	public OrderItem() { }
	
	public OrderItem(List<Product> products) {
		this.products = products;
	}
	
}
