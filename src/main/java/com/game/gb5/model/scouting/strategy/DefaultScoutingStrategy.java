package com.game.gb5.model.scouting.strategy;

import com.game.gb5.model.scouting.ScouterStatus;
import com.game.gb5.model.scouting.report.EmptyScoutingReport;
import com.game.gb5.model.scouting.report.ScoutingReport;

public class DefaultScoutingStrategy implements ScoutingStrategy {
	@Override
	public ScoutingReport generateScoutingReport(ScouterStatus scouterStatus) {
		// 획득확률에 따라무작위로 선택된, 리포트 캐릭터 개수 만큼의 캐릭터들로 구성됨
		
		return new ScoutingReport();
	}
	
	@Override
	public ScoutingReport generateEmptyScoutingReport() {
		return new EmptyScoutingReport();
	}
}
