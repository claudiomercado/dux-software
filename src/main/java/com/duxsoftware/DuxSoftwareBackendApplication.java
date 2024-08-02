package com.duxsoftware;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.duxsoftware.model.user.UserEntity;
import com.duxsoftware.repository.user.UserRepository;

@SpringBootApplication
public class DuxSoftwareBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DuxSoftwareBackendApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(UserRepository userRepository ) {
		return args -> {
			UserEntity user = new UserEntity("test",new BCryptPasswordEncoder().encode("12345"));
			userRepository.save(user);
		};
	}

}
