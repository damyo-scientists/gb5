package com.game.gb5.domain.scouting.report;

import com.game.gb5.domain.character.GameCharacter;

import java.util.List;

import lombok.Data;

@Data
public class ScoutingReport {
	boolean isEmpty = false;
	List<GameCharacter> characterList;
	
	public ScoutingReport(List<GameCharacter> characterList) {
		this.characterList = characterList;
	}
}
