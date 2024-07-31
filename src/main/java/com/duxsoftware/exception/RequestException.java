package com.duxsoftware.exception;

import org.springframework.http.HttpStatus;

public class RequestException extends RuntimeException{
	private HttpStatus status;
	
	public RequestException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}
