package com.game.gb5.scouter.controller;


import com.game.gb5.player.model.entity.player.Player;
import com.game.gb5.scouter.model.entity.Scouter;
import com.game.gb5.entity.scouting.report.ScoutingReport;
import com.game.gb5.player.service.PlayerService;
import com.game.gb5.service.ScoutingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity makeScoutingReport(@RequestParam("scouter_id") Long scouterId,
	                                         @RequestParam("player_id") Long playerId) {
		Scouter scouter = scoutingService.getScouterById(scouterId);
		Player player = playerService.getPlayerById(playerId);
		ScoutingReport scoutingReport = scoutingService.makeNewScoutingReport(scouter, player);
		if (scoutingReport != null) {
			return new ResponseEntity<>(scoutingReport, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Scouting Report is null", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
