package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootApplication
@EnableCaching
public class ImmobiApplication {
	@Bean(name="modelmapper")
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean(name="objectmapper")
	public ObjectMapper objectlMapper() {
		return new ObjectMapper();
	}
	
	@Bean(name = "bcrypt")
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(ImmobiApplication.class, args);
	}

}
