package com.game.gb5;

import com.game.gb5.character.model.GameCharacterDao;
import com.game.gb5.common.utils.database.DatabaseMaker;

import com.game.gb5.deck.model.Deck;
import com.game.gb5.game.model.Game;
import com.game.gb5.game.model.GameOptions;
import com.game.gb5.game.model.GameType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
		Deck deck1 = new Deck();
		Deck deck2 = new Deck();
		// let's think about builder pattern

		// game needs deck, and game has a type (vs ai, vs player)
		Game game = Game.builder().gameType(GameType.VERSUS_PLAYER).deck1(deck1).deck2(deck2).build();

		// game has game options
		GameOptions gameOptions = new GameOptions();;
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
