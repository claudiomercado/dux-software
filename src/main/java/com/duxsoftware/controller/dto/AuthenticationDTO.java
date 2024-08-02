package com.duxsoftware.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationDTO {

	@JsonProperty(value = "token")
	private String token;

	public AuthenticationDTO() {}
	
	public AuthenticationDTO(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
