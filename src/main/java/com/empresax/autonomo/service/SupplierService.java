package com.empresax.autonomo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresax.autonomo.api.request.SupplierRequest;
import com.empresax.autonomo.exception.ResourceNotFoundException;
import com.empresax.autonomo.exception.SaveEntityException;
import com.empresax.autonomo.model.Address;
import com.empresax.autonomo.model.Supplier;
import com.empresax.autonomo.model.User;
import com.empresax.autonomo.repository.SupplierRepository;

@Service
public class SupplierService {
	
	private final SupplierRepository supplierRepository;
	private final UserService userService;
	
	@Autowired
	public SupplierService(SupplierRepository supplierRepository, 
						   UserService userService) {
		this.supplierRepository = supplierRepository;
		this.userService = userService;
	}

	public List<Supplier> getAllSuppliers(Long userId) {
		boolean userExists = this.userService.getUserById(userId) != null;
		
		if(userExists) {
			return this.supplierRepository.findAllByUserId(userId);			
		} else {
			throw new ResourceNotFoundException("User with id " + userId + "was not found.");		
		}
	}
	
	public Supplier getSupplier(Long userId, Long supplierId) {
		return this.supplierRepository.findByUserIdAndId(userId, supplierId)
				.orElseThrow (
						() -> new ResourceNotFoundException("user with id " + userId +
								" does not have a provider with id: " + supplierId)
				);
	}
	
	public Supplier save(Long userId, SupplierRequest supplierRequest) {
		
		User user = this.userService.getUserById(userId);
		
		Address newSupplierAddress = new Address();
			newSupplierAddress.setStreet(supplierRequest.getAddress().getStreet());
			newSupplierAddress.setNumber(supplierRequest.getAddress().getNumber());
			newSupplierAddress.setNeighborhood(supplierRequest.getAddress().getNeighborhood());
			newSupplierAddress.setCity(supplierRequest.getAddress().getCity());
			newSupplierAddress.setCep(supplierRequest.getAddress().getCep());
		
		Supplier newSupplier = new Supplier();
			newSupplier.setName(supplierRequest.getName());
			newSupplier.setCnpj(supplierRequest.getCnpj());
			newSupplier.setEmail(supplierRequest.getEmail());
			newSupplier.setPhones(supplierRequest.getPhones());
			newSupplier.setAddress(newSupplierAddress);
			newSupplier.setUser(user);
			
		try {
			
			return this.supplierRepository.save(newSupplier);	
			
		} catch (IllegalArgumentException e) {
			
			throw new SaveEntityException("Empty supplier");
			
		}
	}
	
	public void deleteById(Long userId, Long supplierId) {
		
		User user = this.userService.getUserById(userId);
		
		if(user != null) {
			Supplier supplier = this.supplierRepository.findByUserIdAndId(userId, supplierId)
					.orElseThrow( () -> 
						new ResourceNotFoundException("User with id " + userId + " does not have a supplier with id " + supplierId + " to delete")
					);
			
			this.supplierRepository.delete(supplier);
		}
	}

}
