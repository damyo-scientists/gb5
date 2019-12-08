package com.game.gb5.service;

import com.game.gb5.entity.player.Player;
import com.game.gb5.scouting.Scouter;
import com.game.gb5.scouting.report.EmptyScoutingReport;
import com.game.gb5.scouting.report.ScoutingReport;

import org.springframework.stereotype.Service;

@Service
public class ScoutingService {
	public ScoutingReport makeNewScoutingReport(Scouter scouter, Player player) {
		if (player.getTicketList().hasInstantAcquisitionReportTicket()) {
			ScoutingReport scoutingReport = scouter.makeScoutingReport();
			player.getTicketList().consumeInstantAcquisitionReportTicket();
			return scoutingReport;
		} else {
			return new EmptyScoutingReport("리포트 티켓이 부족합니다.");
		}
	}
}
