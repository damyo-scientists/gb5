package com.game.gb5.controller;


import com.game.gb5.entity.player.Player;
import com.game.gb5.scouting.Scouter;
import com.game.gb5.scouting.report.ScoutingReport;
import com.game.gb5.response.ScoutingResponse;
import com.game.gb5.service.ScoutingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scouting-report")
public class ScoutingReportController {
	@Autowired
	private ScoutingService scoutingService;
	
	@PostMapping("")
	public ScoutingResponse makeScoutingReport(Scouter scouter, Player player) {
		ScoutingReport scoutingReport = scoutingService.makeNewScoutingReport(scouter, player);
		return new ScoutingResponse(scoutingReport);
	}
}
