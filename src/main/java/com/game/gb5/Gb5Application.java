package com.game.gb5;

import com.game.gb5.character.model.GameCharacterDao;
import com.game.gb5.common.utils.database.DatabaseMaker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Gb5Application {
	private static Logger logger = LogManager.getLogger(Gb5Application.class);
	
	public static void main(String[] args) {
		logger.debug("GB의 시작입니다. 저도 막 긴장이 되네요.");
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
