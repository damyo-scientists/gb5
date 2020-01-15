package com.game.gb5.domain.scouting.report;

import com.game.gb5.domain.character.GameCharacter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoutingReport implements Serializable {
	private Date baseTime = new Date();
	private boolean isEmpty = false;
	private List<GameCharacter> characterList;
	
	public ScoutingReport(List<GameCharacter> characterList) {
		this.characterList = characterList;
	}
}
