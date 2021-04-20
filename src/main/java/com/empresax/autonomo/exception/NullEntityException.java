package com.empresax.autonomo.exception;

@SuppressWarnings("serial")
public class NullEntityException extends RuntimeException {
	public NullEntityException(String message) {
		super(message);
	}
}
