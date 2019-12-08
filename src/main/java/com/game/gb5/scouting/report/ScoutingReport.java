package com.game.gb5.scouting.report;

import java.util.List;

import lombok.Data;

@Data
public class ScoutingReport {
	public ScoutingReport() {
	
	}
	
	public ScoutingReport(List<Character> characterList) {
		this.characterList = characterList;
	}
	
	boolean isEmpty = false;
	List<Character> characterList;
}
