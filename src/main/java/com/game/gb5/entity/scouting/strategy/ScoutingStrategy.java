package com.game.gb5.entity.scouting.strategy;

import com.game.gb5.scouter.model.entity.Scouter;
import com.game.gb5.entity.scouting.report.ScoutingReport;

public interface ScoutingStrategy {
	public ScoutingReport generateScoutingReport(Scouter scouter);
	
	public ScoutingReport generateEmptyScoutingReport(String emptyReason);
}
