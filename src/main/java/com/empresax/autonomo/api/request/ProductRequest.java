package com.empresax.autonomo.api.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public class ProductRequest {
	@NotBlank
	private String name;
	
	@NotNull
	private Double price;
	
	@Size(max = 255)
	private String description;
	
	@NotBlank
	private String measurement;
	
	@NotNull
	private Integer quantity;
	
	@NotNull
	private Long supplierId;
}
