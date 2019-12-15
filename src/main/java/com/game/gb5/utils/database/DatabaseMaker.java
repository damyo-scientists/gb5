package com.game.gb5.utils.database;

import com.game.gb5.dao.GameCharacterDao;
import com.game.gb5.domain.character.GameCharacter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class DatabaseMaker implements CommandLineRunner {
	@Autowired
	private GameCharacterDao gameCharacterDao;
	
	@Override
	public void run(String... args) throws Exception {
		GameCharacter gameCharacter = new GameCharacter();
		gameCharacterDao.save(gameCharacter);
	}
}
