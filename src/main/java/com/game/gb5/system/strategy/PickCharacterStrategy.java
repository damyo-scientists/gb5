package com.game.gb5.system.strategy;

import com.game.gb5.model.character.Character;
import com.game.gb5.model.scouting.ScouterStatus;

import java.util.List;

public interface PickCharacterStrategy {
	public List<Character> pickCharacters(List<Character> characterList, ScouterStatus scouterStatus);
}
