package com.game.gb5.service;

import com.game.gb5.model.scouting.Scouter;
import com.game.gb5.model.scouting.report.ScoutingReport;

public class ScoutingService {
	public ScoutingReport getNewScoutingReport(Scouter scouter) {
		return scouter.makeScoutingReport();
	}
}
