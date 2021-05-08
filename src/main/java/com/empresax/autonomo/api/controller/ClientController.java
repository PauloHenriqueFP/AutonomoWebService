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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresax.autonomo.api.request.ClientRequest;
import com.empresax.autonomo.api.response.ClientResponse;
import com.empresax.autonomo.model.Client;
import com.empresax.autonomo.service.ClientService;

@RestController
@RequestMapping("users/{userId}/clients")
public class ClientController {

	private final ClientService clientService;

	@Autowired
	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@GetMapping
	public ResponseEntity<List<ClientResponse>> getAll(@PathVariable(required = true) Long userId) {
		
		List<ClientResponse> response = this.clientService.getAllClients(userId)
				.stream()
				.map(client -> mapClient(client))
				.collect(Collectors.toList());
		
		return new ResponseEntity<List<ClientResponse>>(response, HttpStatus.OK);
	}

	@GetMapping("/{clientId}")
	public ResponseEntity<ClientResponse> getClient(@PathVariable(required = true) Long userId,
													@PathVariable(required = true) Long clientId) {

		Client client = this.clientService.getClient(userId, clientId);
		ClientResponse response = mapClient(client);
		
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<ClientResponse> saveClient(@PathVariable(required = true) Long userId,
											 		 @Valid @RequestBody ClientRequest clientRequest) {

		Client savedClient = this.clientService.saveUserClient(userId, clientRequest);
		ClientResponse response = mapClient(savedClient);
		
		return new ResponseEntity<ClientResponse>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/{clientId}")
	public ResponseEntity<ClientResponse> updateClient(@PathVariable(value = "userId", required = true) Long userId,
			 										   @PathVariable(value = "clientId", required = true) Long clientId,
			 										   @Valid @RequestBody(required = true) ClientRequest clientRequest) {
		Client updatedClient = this.clientService.updateClient(userId, clientId, clientRequest);
		ClientResponse response = mapClient(updatedClient);
		
		return new ResponseEntity<ClientResponse>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/{clientId}")
	public ResponseEntity<Void> deleteClient(@PathVariable(value = "userId", required = true) Long userId,
											 @PathVariable(value = "clientId", required = true) Long clientId) {
		
		if(this.clientService.deleteClientById(userId, clientId)) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	private ClientResponse mapClient(Client client) {
		
		// Later use the builder pattern
		
		ClientResponse clientDTO = new ClientResponse();
			clientDTO.setId(client.getId());
			clientDTO.setName(client.getName());
			clientDTO.setEmail(client.getEmail());
			clientDTO.setCpf(client.getCpf());
			clientDTO.setPhone(client.getPhone());
			clientDTO.setAddress(client.getAddress());
		
		return clientDTO;
	}

}
