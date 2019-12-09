package com.game.gb5.domain.scouting.strategy;

import com.game.gb5.domain.character.Character;
import com.game.gb5.domain.scouting.ScouterStatus;

import java.util.List;

public interface PickCharacterStrategy {
	public List<Character> pickCharacters(List<Character> characterList, ScouterStatus scouterStatus);
}
