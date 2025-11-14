package com.example.booklab;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookLabApplication.class, args);
	}

	@Bean
	ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
