package com.empresax.autonomo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresax.autonomo.api.request.AddressRequest;
import com.empresax.autonomo.api.request.ClientRequest;
import com.empresax.autonomo.exception.ResourceNotFoundException;
import com.empresax.autonomo.exception.SaveEntityException;
import com.empresax.autonomo.model.Address;
import com.empresax.autonomo.model.Client;
import com.empresax.autonomo.model.User;
import com.empresax.autonomo.repository.ClientRepository;

/**
 * 
 * @author Paulo Henrique Flausino Patarello
 *
 */

@Service
public class ClientService {
	
	private final ClientRepository clientRepository;
	private final UserService userService;

	@Autowired
	public ClientService(ClientRepository clientRepository, UserService userService) {
		this.clientRepository = clientRepository;
		this.userService = userService;
	}

	public Client getClient(Long userId, Long clientId) {
		return this.clientRepository.findByUserIdAndId(userId, clientId)
				.orElseThrow(() -> new ResourceNotFoundException("user with id " + userId + 
						" does not have a client with id: " + clientId ));
	}
	
	public Client saveUserClient(Long userId, ClientRequest clientRequest) {
		try {
			User user = this.userService.getUserById(userId);
			
			
			Address newClientAddress = new Address();
				newClientAddress.setStreet(clientRequest.getAddress().getStreet());
				newClientAddress.setNumber(clientRequest.getAddress().getNumber());
				newClientAddress.setNeighborhood(clientRequest.getAddress().getNeighborhood());
				newClientAddress.setCity(clientRequest.getAddress().getCity());
				newClientAddress.setCep(clientRequest.getAddress().getCep());
				
			Client newClient = new Client();
				newClient.setName(clientRequest.getName());
				newClient.setEmail(clientRequest.getEmail());
				newClient.setCpf(clientRequest.getCpf());
				newClient.setPhone(clientRequest.getPhone());
				newClient.setAddress(newClientAddress);
				newClient.setUser(user);
			
			return this.clientRepository.save(newClient);	
			
		} catch (IllegalArgumentException e) {
			
			throw new SaveEntityException("Error when saving a client for the user " + userId);
			
		}
	}

	public List<Client> getAllClients(Long userId) {
		return this.clientRepository.findAllByUserId(userId);
	}
	
	public boolean deleteClientById(Long userId, Long clientId) {
		
		@SuppressWarnings("unused")
		User userExists = this.userService.getUserById(userId); // If user does not exits, getUserById will throw an exception
		
		Client client = this.clientRepository.findByUserIdAndId(userId, clientId)
					.orElse(null);
		
		if(client != null) {
			this.clientRepository.deleteById(clientId);
			return true;
		}
		else {
			throw new ResourceNotFoundException("User with id " + userId + " does not have a client with id " + clientId);
		}
				
	}

	public Client updateClient(Long userId, Long clientId, ClientRequest clientRequest) {
		
		User user = this.userService.getUserById(userId); // If user does not exits, getUserById will throw an exception
		
		Client oldClient = this.clientRepository.findByUserIdAndId(userId, clientId).get();
		
		if(oldClient == null) {
			throw new ResourceNotFoundException("User with id " + userId + " does not have a client with id " + clientId + " to update");
		}
		else {
			  
			AddressRequest addressRequest = clientRequest.getAddress();
		
			Address newAddress = new Address(
					addressRequest.getStreet(),
					addressRequest.getNeighborhood(),
					addressRequest.getCity(),
					addressRequest.getNumber(),
					addressRequest.getCep()
			);
			newAddress.setId(oldClient.getAddress().getId());
			
			Client newClient = new Client(
					clientRequest.getName(),
					clientRequest.getEmail(),
					clientRequest.getCpf(),
					clientRequest.getPhone(),
					newAddress,
					user
			); 
			newClient.setId(clientId);
			
			try {
				
				return this.clientRepository.save(newClient);
				
			} catch (Exception e) {
				
				throw new SaveEntityException("Error when trying to save the client");
				
			}
		}
		
	}
}
