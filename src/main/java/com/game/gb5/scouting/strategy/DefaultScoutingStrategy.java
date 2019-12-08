package com.game.gb5.scouting.strategy;

import com.game.gb5.entity.character.Character;
import com.game.gb5.entity.character.CharacterSet;
import com.game.gb5.scouting.ScouterStatus;
import com.game.gb5.scouting.report.EmptyScoutingReport;
import com.game.gb5.scouting.report.ScoutingReport;

import java.util.List;

public class DefaultScoutingStrategy implements ScoutingStrategy {
	@Override
	public ScoutingReport generateScoutingReport(ScouterStatus scouterStatus, CharacterSet characterSet) {
		// 획득확률에 따라무작위로 선택된, 리포트 캐릭터 개수 만큼의 캐릭터들로 구성됨
		List<Character> characterList = characterSet.getTargetCharacters();
		return new ScoutingReport();
	}
	
	@Override
	public ScoutingReport generateEmptyScoutingReport() {
		return new EmptyScoutingReport("");
	}
}
