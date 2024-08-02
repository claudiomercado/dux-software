package com.duxsoftware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duxsoftware.controller.dto.AuthenticationDTO;
import com.duxsoftware.controller.dto.LoginRequestDTO;
import com.duxsoftware.service.user.UserDetailServiceImpl;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private UserDetailServiceImpl userDetailServiceImpl;
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationDTO> login(@RequestBody @Valid LoginRequestDTO loginRequest) {
		return new ResponseEntity<>(this.userDetailServiceImpl.loginUser(loginRequest), HttpStatus.OK);
	}

}
