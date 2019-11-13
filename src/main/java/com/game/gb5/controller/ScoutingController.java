package com.game.gb5.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game.gb5.model.scouting.Scouter;
import com.game.gb5.model.scouting.report.ScoutingReport;
import com.game.gb5.response.ScoutingResponse;

@RestController
@RequestMapping("/scouting")
public class ScoutingController {
	@GetMapping("/")
	public ScoutingResponse getScoutingReport(Scouter scouter) {
		ScoutingReport scoutingReport = scouter.makeScoutingReport();
		return new ScoutingResponse(scoutingReport);
	}
}
