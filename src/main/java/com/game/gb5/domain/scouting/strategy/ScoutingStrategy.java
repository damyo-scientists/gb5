package com.game.gb5.domain.scouting.strategy;

import com.game.gb5.domain.character.CharacterSet;
import com.game.gb5.domain.scouting.ScouterStatus;
import com.game.gb5.domain.scouting.report.ScoutingReport;

public interface ScoutingStrategy {
	public ScoutingReport generateScoutingReport(ScouterStatus scouterStatus, CharacterSet characterSet);
	
	public ScoutingReport generateEmptyScoutingReport();
}
