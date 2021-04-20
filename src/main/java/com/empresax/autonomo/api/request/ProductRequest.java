package com.empresax.autonomo.api.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;

@Getter
public class ProductRequest {
	@NotBlank
	private String name;
	
	@NotEmpty
	private Double price;
	
	@Max(value = 255)
	private String description;
	
	@NotBlank
	private String measurement;
	
	@NotEmpty
	private Integer quantity;
	
	@NotEmpty
	private Long supplierId;
}
