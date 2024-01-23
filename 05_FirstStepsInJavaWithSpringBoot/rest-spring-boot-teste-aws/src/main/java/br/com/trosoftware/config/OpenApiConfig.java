package br.com.trosoftware.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI customOpnenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("RESTful Api with Java, Spring, AWS e Docker")
						.version("v1")
						.description("Projeto realizado para conhecimento sobre docker e AWS")
						.termsOfService("")
						.license(new License()
								.name("Apache 2.0")
								.url("https://trosoftware.com.br/meus-cursos")));
	}

}
