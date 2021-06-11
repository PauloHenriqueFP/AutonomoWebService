package com.empresax.autonomo.api.exceptionhandler;

public class ErrorMessage {
	
	private String cause;
	
	public ErrorMessage(String cause) {
		this.cause = cause;
	}
	
	public String getCause() {
		return cause;
	}
	
	public void setCause(String cause) {
		this.cause = cause;
	}
	
}
