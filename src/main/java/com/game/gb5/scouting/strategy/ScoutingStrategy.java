package com.game.gb5.scouting.strategy;

import com.game.gb5.entity.character.CharacterSet;
import com.game.gb5.scouting.ScouterStatus;
import com.game.gb5.scouting.report.ScoutingReport;

public interface ScoutingStrategy {
	public ScoutingReport generateScoutingReport(ScouterStatus scouterStatus, CharacterSet characterSet);
	
	public ScoutingReport generateEmptyScoutingReport();
}
