package com.empresax.autonomo.api.exceptionhandler;

public class ErrorMessageNotFound {
	
	private String cause;
	
	public ErrorMessageNotFound(String cause) {
		this.cause = cause;
	}
	
	public String getCause() {
		return cause;
	}
	
	public void setCause(String cause) {
		this.cause = cause;
	}
	
}
