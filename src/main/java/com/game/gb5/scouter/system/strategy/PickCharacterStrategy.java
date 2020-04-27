package com.game.gb5.scouter.system.strategy;

import com.game.gb5.character.model.Character;
import com.game.gb5.scouter.model.ScouterStatus;

import java.util.List;

public interface PickCharacterStrategy {
	public List<Character> pickCharacters(List<Character> characterList, ScouterStatus scouterStatus);
}
