package com.duxsoftware.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorDTO {
	@JsonProperty(value = "mensaje")
	private String message;
	@JsonProperty(value = "codigo")
	private int code;
	
	public ErrorDTO(String message, int code) {
		this.message = message;
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
}
