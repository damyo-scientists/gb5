package com.game.gb5.domain.scouting.strategy;

import com.game.gb5.domain.character.GameCharacter;
import com.game.gb5.domain.scouting.ScouterStatus;

import java.util.List;

public interface PickCharacterStrategy {
	public List<GameCharacter> pickCharacters(List<GameCharacter> characterList, ScouterStatus scouterStatus);
}
