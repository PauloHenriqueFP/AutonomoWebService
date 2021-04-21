package com.empresax.autonomo.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import com.empresax.autonomo.api.request.SupplierRequest;
import com.empresax.autonomo.api.response.SupplierResponse;
import com.empresax.autonomo.model.Supplier;
import com.empresax.autonomo.service.SupplierService;

@RestController
@RequestMapping("users/{userId}/suppliers")
public class SupplierController {
	@Autowired
	private SupplierService supplierService;
	
	@GetMapping
	public ResponseEntity<List<SupplierResponse>> getAllSuppliers(@PathVariable(required = true, name = "userId") Long userId){
		
		List<SupplierResponse> providers = this.supplierService.getAllSuppliers(userId)
				.stream()
				.map( supplier -> mapSupplier(supplier) )
				.collect( Collectors.toList() );
		
		return new ResponseEntity<List<SupplierResponse>>(providers, HttpStatus.OK);
	}
	
	@GetMapping("/{supplierId}")
	public ResponseEntity<SupplierResponse> getSupplier(@PathVariable(required = true, name = "userId") Long userId,
														@PathVariable(required = true, name = "supplierId") Long supplierId) {
		
		Supplier supplier = this.supplierService.getSupplier(userId, supplierId);
		
		SupplierResponse response = mapSupplier(supplier);
		
		return new ResponseEntity<SupplierResponse>(response, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<SupplierResponse> saveSupplier(@PathVariable(required = true, name = "userId") Long userId,
												 		 @Valid @RequestBody SupplierRequest supplierRequest) {
		
		Supplier savedSupplier = this.supplierService.save(userId, supplierRequest);
		
		SupplierResponse response = mapSupplier(savedSupplier);
		
		return new ResponseEntity<SupplierResponse>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{supplierId}")
	public ResponseEntity<Void> deleteSupplier(@PathVariable(required = true, name = "userId") Long userId,
											   @PathVariable(required = true, name = "supplierId") Long supplierId) {
		
		this.supplierService.deleteById(userId, supplierId);
		
		return ResponseEntity.noContent().build();
	}
	
	private SupplierResponse mapSupplier(Supplier supplier) {
		
		// Later use the builder pattern
		
		SupplierResponse supplierDTO = new SupplierResponse();
			supplierDTO.setId(supplier.getId());
			supplierDTO.setCnpj(supplier.getCnpj());
			supplierDTO.setName(supplier.getName());
			supplierDTO.setEmail(supplier.getEmail());
			supplierDTO.setAddress(supplier.getAddress());
			supplierDTO.setPhones(supplier.getPhones());
		
		return supplierDTO;
	}
}
