package com.game.gb5.system.strategy;

import com.game.gb5.model.scouting.Scouter;
import com.game.gb5.model.report.ScoutingReport;

public interface ScoutingStrategy {
	public ScoutingReport generateScoutingReport(Scouter scouter);

	public ScoutingReport generateEmptyScoutingReport(String emptyReason);
}
