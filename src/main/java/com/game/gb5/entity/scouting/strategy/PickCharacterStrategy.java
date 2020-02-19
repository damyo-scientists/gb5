package com.game.gb5.entity.scouting.strategy;

import com.game.gb5.entity.character.GameCharacter;
import com.game.gb5.scouter.model.entity.ScouterStatus;

import java.util.List;

public interface PickCharacterStrategy {
	public List<GameCharacter> pickCharacters(List<GameCharacter> characterList, ScouterStatus scouterStatus);
}
