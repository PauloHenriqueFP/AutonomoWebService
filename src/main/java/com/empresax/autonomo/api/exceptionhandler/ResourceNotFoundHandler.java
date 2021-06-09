package com.empresax.autonomo.api.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.empresax.autonomo.exception.ResourceNotFoundException;

@RestControllerAdvice
public class ResourceNotFoundHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessageNotFound handleResourceNotFound(ResourceNotFoundException e) {
		return new ErrorMessageNotFound(e.getMessage());
	}
	
}
