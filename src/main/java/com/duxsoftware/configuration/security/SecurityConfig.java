package com.duxsoftware.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.duxsoftware.configuration.filter.JwtTokenValidator;
import com.duxsoftware.service.user.UserDetailServiceImpl;
import com.duxsoftware.util.JwtUtils;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private JwtUtils jwtUtils;
	
	@Bean	
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,AuthenticationProvider authenticationProvider) throws Exception {
		httpSecurity
			.csrf(csrf -> csrf.disable())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(authorize -> {
				authorize.requestMatchers(HttpMethod.POST ,"/auth/**").permitAll();
				authorize.requestMatchers("/h2-console/**").permitAll();
				authorize.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll();
				authorize.anyRequest().authenticated();
		})
			.headers(headers -> headers.frameOptions(frame -> frame.disable()))
			.addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class);
		
     return httpSecurity.build();
 }
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailServiceImpl ) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		authenticationProvider.setUserDetailsService(userDetailServiceImpl);
		
		return authenticationProvider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	} 
	
}
	

