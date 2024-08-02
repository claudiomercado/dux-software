package com.duxsoftware.configuration.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("DUX SOFTWARE")
						.version("1.0")
						.description("Example with swagger for DUX API"))
						.addSecurityItem(new SecurityRequirement().addList("JavaInUseSecurityScheme"))
						.components(new Components().addSecuritySchemes("JavaInUseSecurityScheme", new SecurityScheme()
								.name("JavaInUseSecurityScheme").type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
	}
}
