package com.game.gb5.model.scouting.strategy;

import com.game.gb5.model.scouting.ScouterStatus;
import com.game.gb5.model.scouting.report.EmptyScoutingReport;
import com.game.gb5.model.scouting.report.ScoutingReport;

public class NormalScoutingStrategy implements ScoutingStrategy {
	@Override
	public ScoutingReport generateScoutingReport(ScouterStatus scouterStatus) {
		return new ScoutingReport();
	}
	
	@Override
	public ScoutingReport generateEmptyScoutingReport() {
		return new EmptyScoutingReport();
	}
}
