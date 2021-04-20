package com.empresax.autonomo.api.response;

import java.time.OffsetDateTime;
import java.util.List;

import com.empresax.autonomo.model.OrderItem;
import com.empresax.autonomo.model.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderResponse {
	private Long id;
	private Long clientId;
	private Double price;
	private OrderStatus status;
	private List<OrderItem> itens;
	private OffsetDateTime openingDate;
	private OffsetDateTime finalizedDate;
}
