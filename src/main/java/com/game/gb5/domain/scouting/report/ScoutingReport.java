package com.game.gb5.domain.scouting.report;

import com.game.gb5.domain.character.GameCharacter;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoutingReport implements Serializable {
	boolean isEmpty = false;
	List<GameCharacter> characterList;
	
	public ScoutingReport(List<GameCharacter> characterList) {
		this.characterList = characterList;
	}
}
