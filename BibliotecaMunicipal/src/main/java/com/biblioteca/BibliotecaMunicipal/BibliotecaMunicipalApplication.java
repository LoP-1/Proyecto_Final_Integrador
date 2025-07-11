package com.biblioteca.BibliotecaMunicipal;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @io.swagger.v3.oas.annotations.info.Info(title = "API Biblioteca", version = "1.0"))
public class BibliotecaMunicipalApplication {
	public static void main(String[] args) {
		SpringApplication.run(BibliotecaMunicipalApplication.class, args);
	}

}
