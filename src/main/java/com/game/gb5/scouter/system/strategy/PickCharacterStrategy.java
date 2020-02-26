package com.game.gb5.scouter.system.strategy;

import com.game.gb5.character.model.GameCharacter;
import com.game.gb5.scouter.model.ScouterStatus;

import java.util.List;

public interface PickCharacterStrategy {
	public List<GameCharacter> pickCharacters(List<GameCharacter> characterList, ScouterStatus scouterStatus);
}
