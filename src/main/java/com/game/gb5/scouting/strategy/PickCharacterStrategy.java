package com.game.gb5.scouting.strategy;

import com.game.gb5.entity.character.Character;
import com.game.gb5.scouting.ScouterStatus;

import java.util.List;

public interface PickCharacterStrategy {
	public Character pickCharacter(List<Character> characterList, ScouterStatus scouterStatus);
	
	public List<Integer> pickGradeList(List<Character> characterList, ScouterStatus scouterStatus);
}
