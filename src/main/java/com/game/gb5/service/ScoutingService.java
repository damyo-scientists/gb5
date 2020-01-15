package com.game.gb5.service;

import com.game.gb5.dao.CharacterSetDao;
import com.game.gb5.dao.ScouterDao;
import com.game.gb5.domain.character.CharacterSet;
import com.game.gb5.domain.player.Player;
import com.game.gb5.domain.scouting.Scouter;
import com.game.gb5.domain.scouting.condition.ReportGenerateCondition;
import com.game.gb5.domain.scouting.condition.TimeReportGenerateCondition;
import com.game.gb5.domain.scouting.report.EmptyScoutingReport;
import com.game.gb5.domain.scouting.report.ScoutingReport;
import com.game.gb5.domain.scouting.strategy.DefaultScoutingStrategy;
import com.game.gb5.domain.scouting.strategy.ScoutingStrategy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class ScoutingService {
	private static Logger logger = LogManager.getLogger(ScoutingService.class);
	
	@Autowired
	private CharacterSetDao characterSetDao;
	@Autowired
	private ScouterDao scouterDao;
	@Autowired
	private ModelMapper modelMapper;
	
	public Scouter getScouterById(Long id) {
		return scouterDao.getScouterById(id);
	}
	
	public ScoutingReport makeNewScoutingReport(Scouter scouter, Player player) {
		if (player.getTicketList().hasInstantAcquisitionReportTicket()) {
			CharacterSet characterSet = characterSetDao.getCharacterSetById(scouter.getCharacterSet().getId());
			ScoutingReport scoutingReport = this.generateScoutingReportByStrategy(scouter, characterSet);
			player.getTicketList().consumeInstantAcquisitionReportTicket();
			return scoutingReport;
		} else {
			return new EmptyScoutingReport("리포트 티켓이 부족합니다.");
		}
	}
	
	protected ScoutingReport generateScoutingReportByStrategy(Scouter scouter, CharacterSet characterSet) {
		ReportGenerateCondition reportGenerateCondition = new TimeReportGenerateCondition(scouter.getReportRegenTime());
		ScoutingStrategy scoutingStrategy = new DefaultScoutingStrategy();
		if (reportGenerateCondition.isConditionSatisfied()) {
			LocalDateTime current = LocalDateTime.now();
			LocalTime resetTime = scouter.getScouterStatus().getReportResetTime();
			LocalDateTime regenTime = current.plusSeconds(resetTime.toSecondOfDay());
			scouter.setReportRegenTime(regenTime);
			return scoutingStrategy.generateScoutingReport(scouter.getScouterStatus(), characterSet);
		}
		logger.debug("empty");
		return scoutingStrategy.generateEmptyScoutingReport();
	}
}
