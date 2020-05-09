package com.game.gb5.system.strategy;

import com.game.gb5.model.Character;
import com.game.gb5.model.ScouterStatus;

import java.util.List;

public interface PickCharacterStrategy {
	public List<Character> pickCharacters(List<Character> characterList, ScouterStatus scouterStatus);
}
