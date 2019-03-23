package com.invillia.acme.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.invillia.acme.service.DatabaseInitialService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DatabaseInitialService databaseInitialService;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		databaseInitialService.instantiateTestDatabase();
		return true;
	}

}
