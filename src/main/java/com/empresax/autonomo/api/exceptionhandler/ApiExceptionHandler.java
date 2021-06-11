package com.empresax.autonomo.api.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.empresax.autonomo.exception.ResourceNotFoundException;
import com.empresax.autonomo.exception.SaveEntityException;

@RestControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage handleResourceNotFound(ResourceNotFoundException e) {
		return new ErrorMessage(e.getMessage());
	}
	
	@ExceptionHandler(SaveEntityException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage handleSaveEntity(SaveEntityException e) {
		return new ErrorMessage(e.getMessage());
	}
	
}
