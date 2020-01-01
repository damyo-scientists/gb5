package com.game.gb5.controller;


import com.game.gb5.domain.player.Player;
import com.game.gb5.domain.scouting.Scouter;
import com.game.gb5.domain.scouting.report.ScoutingReport;
import com.game.gb5.response.ScoutingResponse;
import com.game.gb5.service.PlayerService;
import com.game.gb5.service.ScoutingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scouting-report")
public class ScoutingReportController {
	@Autowired
	private ScoutingService scoutingService;
	
	@Autowired
	private PlayerService playerService;
	
	@PostMapping("")
	public ScoutingResponse makeScoutingReport(@RequestParam("scouter_id") Long scouterId,
	                                           @RequestParam("player_id") Long playerId) {
		Scouter scouter = scoutingService.getScouterById(scouterId);
		Player player = playerService.getPlayerById(playerId);
		ScoutingReport scoutingReport = scoutingService.makeNewScoutingReport(scouter, player);
		return new ScoutingResponse(scoutingReport);
	}
}
