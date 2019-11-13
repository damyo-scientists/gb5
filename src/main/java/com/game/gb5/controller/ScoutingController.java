package com.game.gb5.controller;


import com.game.gb5.model.scouting.Scouter;
import com.game.gb5.model.scouting.report.ScoutingReport;
import com.game.gb5.response.ScoutingResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scouting")
public class ScoutingController {
	@GetMapping("")
	public ScoutingResponse getScoutingReport(Scouter scouter) {
		ScoutingReport scoutingReport = scouter.makeScoutingReport();
		if (scoutingReport == null) {
			return new ScoutingResponse(null, "리포트 생성에 실패했습니다.", true);
		}
		return new ScoutingResponse(scoutingReport);
	}
}
