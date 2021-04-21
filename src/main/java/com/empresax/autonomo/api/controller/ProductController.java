package com.empresax.autonomo.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresax.autonomo.api.request.ProductRequest;
import com.empresax.autonomo.api.response.ProductResponse;
import com.empresax.autonomo.model.Product;
import com.empresax.autonomo.service.ProductService;

@RestController
@RequestMapping("users/{userId}/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<ProductResponse>> getUserProducts(@PathVariable(required = true) Long userId) { 
		
		List<ProductResponse> response = this.productService.getAllProducts(userId)
				.stream()
				.map(product -> mapProduct(product))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<ProductResponse> getUserProduct(@PathVariable(required = true) Long userId,
														  @PathVariable(required = true) Long productId) {

		Product product = this.productService.getProduct(userId, productId);

		ProductResponse response = mapProduct(product);

		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<ProductResponse> saveProduct(@PathVariable Long userId,
													   @RequestBody ProductRequest productRequest) {
		
		Product savedProduct = this.productService.saveUserProduct(userId, productRequest);
		
		ProductResponse response = mapProduct(savedProduct);

		return new ResponseEntity<ProductResponse>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<Void> deleteProduct(@PathVariable(required = true, name = "userId") Long userId,
											  @PathVariable(required = true, name = "productId") Long productId) {

		this.productService.deleteProduct(userId, productId);

		return ResponseEntity.noContent().build();
	}
	
	private ProductResponse mapProduct(Product product) {
		
		// Later use the builder pattern
		
		ProductResponse productDTO = new ProductResponse();
			productDTO.setId(product.getId());
			productDTO.setName(product.getName());
			productDTO.setDescription(product.getDescription());
			productDTO.setPrice(product.getPrice());
			productDTO.setMeasurement(product.getMeasurement());
			productDTO.setQuantity(product.getQuantity());
			productDTO.setSupplierId(product.getSupplier().getId());
		
		return productDTO;
	}
}
