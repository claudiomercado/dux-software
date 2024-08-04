package com.duxsoftware.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.duxsoftware.controller.dto.ErrorDTO;

@RestControllerAdvice
public class ControllerAdvice {
	
	@ExceptionHandler(value = RequestException.class)
	public ResponseEntity<ErrorDTO> requestExceptionHandler(RequestException ex){
		ErrorDTO error = new ErrorDTO(ex.getMessage(), ex.getStatus().value());
		return new ResponseEntity<>(error,ex.getStatus());
	}
	
	@ExceptionHandler(value = BadCredentialsException.class)
	public ResponseEntity<ErrorDTO> badCredentialsExceptionHandler(BadCredentialsException ex){
		ErrorDTO error = new ErrorDTO(ex.getMessage(),HttpStatus.UNAUTHORIZED.value());
		return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);
	}
	
}
