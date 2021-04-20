package com.empresax.autonomo.api.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductResponse {
	private Long id;
	private String name;
	private String description;
	private Double price;
	private String measurement;
	private Integer quantity;
	private Long supplierId;
}
