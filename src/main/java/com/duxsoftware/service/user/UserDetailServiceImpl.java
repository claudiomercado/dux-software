package com.duxsoftware.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.duxsoftware.controller.dto.AuthenticationDTO;
import com.duxsoftware.controller.dto.LoginRequestDTO;
import com.duxsoftware.model.user.UserEntity;
import com.duxsoftware.repository.user.UserRepository;
import com.duxsoftware.util.JwtUtils;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	private final static String USERNAME_INVALID = "Nombre de usuario invalido";
	private final static String PASSWORD_INVALID = "Contraseña invalida";
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findUserByUsername(username)
				.orElseThrow(()-> new BadCredentialsException(USERNAME_INVALID));
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		return new User(user.getUsername(), user.getPassword(),true,true,true,true, authorities) ;
	}

	public AuthenticationDTO loginUser(LoginRequestDTO loginRequest) {
		String username = loginRequest.getUsername();
		String password = loginRequest.getPassword();
		
		Authentication authentication = this.authenticate(username,password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String accessToken = jwtUtils.createToken(authentication);
		
		AuthenticationDTO response = new AuthenticationDTO(accessToken);
		
		return response;
	}
	
	public Authentication authenticate(String username, String password) {
		UserDetails userDetails = this.loadUserByUsername(username);
		
		if (!userDetails.getUsername().equals(username) ) {
			throw new BadCredentialsException(USERNAME_INVALID);
		}
		
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException(PASSWORD_INVALID);
		}
		
		return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(),null); 
	}
}
