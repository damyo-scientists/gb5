package com.game.gb5.service;

import com.game.gb5.dao.CharacterSetDao;
import com.game.gb5.domain.character.CharacterSet;
import com.game.gb5.domain.player.Player;
import com.game.gb5.domain.scouting.Scouter;
import com.game.gb5.domain.scouting.report.EmptyScoutingReport;
import com.game.gb5.domain.scouting.report.ScoutingReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoutingService {
	@Autowired
	private CharacterSetDao characterSetDao;
	
	public ScoutingReport makeNewScoutingReport(Scouter scouter, Player player) {
		if (player.getTicketList().hasInstantAcquisitionReportTicket()) {
			CharacterSet characterSet = characterSetDao.getCharacterSetById();
			ScoutingReport scoutingReport = scouter.makeScoutingReport(characterSet);
			player.getTicketList().consumeInstantAcquisitionReportTicket();
			return scoutingReport;
		} else {
			return new EmptyScoutingReport("리포트 티켓이 부족합니다.");
		}
	}
}
