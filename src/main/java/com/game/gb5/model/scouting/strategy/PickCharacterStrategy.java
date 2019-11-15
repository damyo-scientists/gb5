package com.game.gb5.model.scouting.strategy;

import com.game.gb5.model.character.Character;
import com.game.gb5.model.scouting.ScouterStatus;

import java.util.List;

public interface PickCharacterStrategy {
	public Character pickCharacter(List<Character> characterList, ScouterStatus scouterStatus);
}
