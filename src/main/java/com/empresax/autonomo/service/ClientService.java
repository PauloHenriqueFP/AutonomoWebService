package com.empresax.autonomo.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresax.autonomo.api.request.ClientRequest;
import com.empresax.autonomo.exception.NullEntityException;
import com.empresax.autonomo.model.Address;
import com.empresax.autonomo.model.Client;
import com.empresax.autonomo.model.User;
import com.empresax.autonomo.repository.ClientRepository;

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
				.orElseThrow(() -> new EntityNotFoundException("user with id " + userId + 
						" does not have a client with id: " + clientId ));
	}
	
	public Client saveUserClient(Long userId, ClientRequest clientRequest) {
		try {
			User user = this.userService.getUserById(userId);
			
			
			Address newClientAddress = new Address();
				newClientAddress.setStreet(clientRequest.getAddress().getStreet());
				newClientAddress.setNumber(clientRequest.getAddress().getNumber());
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
			
			throw new NullEntityException("Error when saving a client for the user");
			
		}
	}

	public List<Client> getAllClients(Long userId) {
		return this.clientRepository.findAllByUserId(userId);
	}
}
