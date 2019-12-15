package com.game.gb5.domain.scouting.strategy;

import com.game.gb5.domain.character.GameCharacter;
import com.game.gb5.domain.character.CharacterSet;
import com.game.gb5.domain.scouting.ScouterStatus;
import com.game.gb5.domain.scouting.report.EmptyScoutingReport;
import com.game.gb5.domain.scouting.report.ScoutingReport;

import java.util.List;

public class DefaultScoutingStrategy implements ScoutingStrategy {
	private PickCharacterStrategy pickCharacterStrategy;
	
	@Override
	public ScoutingReport generateScoutingReport(ScouterStatus scouterStatus, CharacterSet characterSet) {
		// 획득확률에 따라무작위로 선택된, 리포트 캐릭터 개수 만큼의 캐릭터들로 구성됨
		List<GameCharacter> characterList = characterSet.getCharacters();
		pickCharacterStrategy = new DefaultPickCharacterStrategy();
		List<GameCharacter> pickedCharacters = pickCharacterStrategy.pickCharacters(characterList, scouterStatus);
		return new ScoutingReport(pickedCharacters);
	}
	
	@Override
	public ScoutingReport generateEmptyScoutingReport() {
		return new EmptyScoutingReport("");
	}
}
