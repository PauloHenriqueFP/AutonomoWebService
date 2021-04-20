package com.empresax.autonomo.api.request;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.empresax.autonomo.model.OrderItem;
import com.empresax.autonomo.model.OrderStatus;

import lombok.Getter;

@Getter
public class OrderRequest {
	@NotEmpty
	private Long clientId;
	
	@NotEmpty
	private Double price;
	
	@NotEmpty
	private OrderStatus status;
	
	@NotEmpty
	private List<OrderItem> itens;
}
