package com.weblanch.spring_blog_rest_api;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Spring Boot Blog REST APIs",
description = "APIs for blog app with posts, comments, categories and authentication",
version = "V1.0",
contact = @Contact(name = "Shakil Mansuri",
		email = "smansuri960@gmail.com",
		url = "https://github.com/s-mansuri"),
license = @License(name = "Apache 2.0", url = "https://github.com/s-mansuri")),
externalDocs = @ExternalDocumentation(description = "Spring Boot APIs for Blog app"))
public class SpringBlogRestApiApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBlogRestApiApplication.class, args);
	}

}
