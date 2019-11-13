package com.game.gb5.model.scouting.strategy;

import com.game.gb5.model.scouting.ScouterStatus;
import com.game.gb5.model.scouting.report.ScoutingReport;

public interface ScoutingStrategy {
	public ScoutingReport generateScoutingReport(ScouterStatus scouterStatus);
	
	public ScoutingReport generateEmptyScoutingReport();
}
