package com.game.gb5.scouter.system.strategy;

import com.game.gb5.scouter.model.Scouter;
import com.game.gb5.scouter.model.report.ScoutingReport;

public interface ScoutingStrategy {
	public ScoutingReport generateScoutingReport(Scouter scouter);
	
	public ScoutingReport generateEmptyScoutingReport(String emptyReason);
}
