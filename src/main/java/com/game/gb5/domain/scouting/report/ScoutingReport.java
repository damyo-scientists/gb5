package com.game.gb5.domain.scouting.report;

import com.game.gb5.domain.character.Character;

import java.util.List;

import lombok.Data;

@Data
public class ScoutingReport {
	boolean isEmpty = false;
	List<Character> characterList;
	
	public ScoutingReport(List<Character> characterList) {
		this.characterList = characterList;
	}
}
