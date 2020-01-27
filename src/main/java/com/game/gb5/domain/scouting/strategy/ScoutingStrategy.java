package com.game.gb5.domain.scouting.strategy;

import com.game.gb5.domain.scouting.Scouter;
import com.game.gb5.domain.scouting.report.ScoutingReport;

public interface ScoutingStrategy {
	public ScoutingReport generateScoutingReport(Scouter scouter);
	
	public ScoutingReport generateEmptyScoutingReport(String emptyReason);
}
