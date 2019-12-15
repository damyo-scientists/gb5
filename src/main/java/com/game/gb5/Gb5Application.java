package com.game.gb5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Gb5Application {
	public static void main(String[] args) {
		SpringApplication.run(Gb5Application.class, args);
	}
	
//	@Bean
//	CommandLineRunner init(GameCharacterDao gameCharacterDao) {
//		return new DatabaseMaker();
//	}
}
