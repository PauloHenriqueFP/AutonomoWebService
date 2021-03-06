package com.empresax.autonomo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresax.autonomo.api.request.ProductRequest;
import com.empresax.autonomo.exception.ResourceNotFoundException;
import com.empresax.autonomo.exception.SaveEntityException;
import com.empresax.autonomo.model.Product;
import com.empresax.autonomo.model.Supplier;
import com.empresax.autonomo.model.User;
import com.empresax.autonomo.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;
	
	private final UserService userService;
	
	private final SupplierService supplierService;

	@Autowired
	public ProductService(ProductRepository productRepository,
						  UserService userService,
						  SupplierService supplierService) {
		
		this.productRepository = productRepository;
		this.userService = userService;
		this.supplierService = supplierService;
	}
	
	public List<Product> getAllProducts(Long userId) {
		boolean userExists = this.userService.getUserById(userId) != null;
		
		if(userExists) {
			return this.productRepository.findAllByUserId(userId);	
		} else {
			throw new ResourceNotFoundException("User with id " + userId + " was not found");
		}
	}
	
	public Product getProduct(Long userId, Long productId) {
		return this.productRepository.findByUserIdAndId(userId, productId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"User with id " + userId + " does not have the product with the id: " + productId));
	}
	
	public Product saveUserProduct(Long userId, ProductRequest productRequest) {
		
		Long supplierAskedToSave = productRequest.getSupplierId();
		
		// if the user doesn't have a provider with the id asked to save
		// the getProvider() will throw a exception
		Supplier supplier = this.supplierService.getSupplier(userId, supplierAskedToSave);
		
		// If gets here, the user can save the product with its provider, because that provider id
		// belongs to a provider that belong to that user
		User user = this.userService.getUserById(userId);
	
		Product newProduct = 
				new Product(
					productRequest.getName(),
					productRequest.getPrice(),
					productRequest.getMeasurement(),
					productRequest.getQuantity(),
					productRequest.getDescription(),
					user,
					supplier
				);
		try {
			Product savedProduct = this.productRepository.save(newProduct);
			return savedProduct;			
		} catch (Exception e) {
			throw new SaveEntityException("Error trying to save the product");
		}
		
	}
	
	public boolean updateProduct(Long userId, Long productId, ProductRequest productRequest) {
		
		boolean userExists = this.userService.getUserById(userId) != null;
		
		if(userExists) {
			Long supplierAskedToSave = productRequest.getSupplierId();
			
			// if the user doesn't have a provider with the id asked to save
			// the getSupplier() will throw a exception
			Supplier supplier = this.supplierService.getSupplier(userId, supplierAskedToSave);
						
			// If gets here, the user can save the product with its provider, because that provider id
			// belongs to a provider that belong to that user
			User user = this.userService.getUserById(userId);
			
			Product newProduct = 
					new Product(
						productRequest.getName(),
						productRequest.getPrice(),
						productRequest.getMeasurement(),
						productRequest.getQuantity(),
						productRequest.getDescription(),
						user,
						supplier
					);
			newProduct.setId(productId);
			
			try {
				
				this.productRepository.save(newProduct);
				return true;
				
			} catch (Exception e) {
				
				return false;

			}
			
		}
		else {
			throw new ResourceNotFoundException("User with id " + userId + " does not have a product with id " + productId);
		}
	}
	
	public void deleteProduct(Long userId, Long productId) {
		boolean userExists = this.userService.getUserById(userId) != null;
		
		if(userExists) {
			Product productToDelete = this.productRepository.findByUserIdAndId(userId, productId)
					.orElseThrow(() -> new ResourceNotFoundException(
							"User with id " + userId + " does not have a product with id " + productId + " to delete"));
			this.productRepository.delete(productToDelete);
		} else {
			throw new ResourceNotFoundException("User with id" + userId + " does not exists");
		}
	}
		
}
