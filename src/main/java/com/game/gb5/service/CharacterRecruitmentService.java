package com.game.gb5.service;

import java.util.List;

import com.game.gb5.model.character.Character;

public class CharacterRecruitmentService {
	private final int STARS_COUNT = 250;
	
	public int recruitCharacters(List<Character> characterList) {
		return characterList.size() * STARS_COUNT;
	}
}
