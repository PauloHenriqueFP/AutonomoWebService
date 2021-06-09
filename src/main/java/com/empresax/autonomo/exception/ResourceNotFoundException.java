package com.empresax.autonomo.exception;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -968139960982587487L;
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
