package com.duxsoftware.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {
	
	@JsonProperty(value = "username")
	@NotBlank
	private String username;
	@JsonProperty(value = "password")
	@NotBlank
	private String password;
	
	public LoginRequestDTO() {}
	
	public LoginRequestDTO(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
