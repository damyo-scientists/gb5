package com.game.gb5;

import com.game.gb5.dao.GameCharacterDao;
import com.game.gb5.utils.database.DatabaseMaker;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Gb5Application {
	public static void main(String[] args) {
		SpringApplication.run(Gb5Application.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	CommandLineRunner init(GameCharacterDao gameCharacterDao) {
		return new DatabaseMaker();
	}
}
