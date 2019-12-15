package com.game.gb5.service;

import java.util.List;

import com.game.gb5.domain.character.GameCharacter;

public class CharacterRecruitmentService {
	private final int STARS_COUNT = 250; // 별 소모 개수
	
	public int recruitCharacters(List<GameCharacter> characterList) {
		return characterList.size() * STARS_COUNT;
	}
}
