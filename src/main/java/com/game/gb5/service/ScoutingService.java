package com.game.gb5.service;

import com.game.gb5.dao.CharacterSetDao;
import com.game.gb5.dao.ReportCharacterDao;
import com.game.gb5.dao.ScouterDao;
import com.game.gb5.dao.ScoutingReportDao;
import com.game.gb5.domain.character.CharacterReportStatus;
import com.game.gb5.domain.player.Player;
import com.game.gb5.domain.scouting.Scouter;
import com.game.gb5.domain.scouting.condition.ReportGenerateCondition;
import com.game.gb5.domain.scouting.condition.TimeReportGenerateCondition;
import com.game.gb5.domain.scouting.report.EmptyScoutingReport;
import com.game.gb5.domain.scouting.report.ReportCharacter;
import com.game.gb5.domain.scouting.report.ScoutingReport;
import com.game.gb5.domain.scouting.strategy.DefaultScoutingStrategy;
import com.game.gb5.domain.scouting.strategy.ScoutingStrategy;
import com.game.gb5.utils.random.RandomMaker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ScoutingService {
	private static Logger logger = LogManager.getLogger(ScoutingService.class);
	
	@Autowired
	private CharacterSetDao characterSetDao;
	@Autowired
	private ScouterDao scouterDao;
	@Autowired
	private CharacterReportStatusService characterStatusReportService;
	@Autowired
	private ReportCharacterDao reportCharacterDao;
	@Autowired
	private ScoutingReportDao scoutingReportDao;
	
	public Scouter getScouterById(Long id) {
		Scouter scouter = scouterDao.getScouterById(id);
		ScoutingReport scoutingReport = scouter.getScoutingReport();
		if (scoutingReport != null) {
			List<ReportCharacter> reportCharacters = this.getDeflectedReportCharacters(scoutingReport.getReportCharacterList(), scouter, scouter.getSeed());
			scoutingReport.setReportCharacterList(reportCharacters);
		}
		return scouter;
	}
	
	public ScoutingReport makeNewScoutingReport(Scouter scouter, Player player) {
		if (player.getTicketList().hasInstantAcquisitionReportTicket()) {
			ScoutingReport scoutingReport = this.generateScoutingReportByStrategy(scouter);
			
			player.getTicketList().consumeInstantAcquisitionReportTicket();
			return scoutingReport;
		} else {
			return new EmptyScoutingReport("리포트 티켓이 부족합니다.");
		}
	}
	
	private ScoutingReport generateScoutingReportByStrategy(Scouter scouter) {
		ReportGenerateCondition reportGenerateCondition = new TimeReportGenerateCondition(scouter.getReportRegenTime());
		long seed = new RandomMaker().makeSeed();
		ScoutingStrategy scoutingStrategy = new DefaultScoutingStrategy(seed);
		if (reportGenerateCondition.isConditionSatisfied()) {
			LocalDateTime current = LocalDateTime.now();
			LocalTime resetTime = scouter.getScouterStatus().getReportResetTime();
			LocalDateTime regenTime = current.plusSeconds(resetTime.toSecondOfDay());
			ScoutingReport scoutingReport = scoutingStrategy.generateScoutingReport(scouter);
			List<ReportCharacter> reportCharacters = this.getDeflectedReportCharacters(scoutingReport.getReportCharacterList(), scouter, seed);
			scoutingReportDao.save(scoutingReport);
			reportCharacterDao.saveAll(reportCharacters);
			
			scouter.setSeed(seed);
			scouter.setReportRegenTime(regenTime);
			scouter.setScoutingReport(scoutingReport);
			scouterDao.save(scouter);
			return scoutingReport;
		}
		logger.debug("empty");
		return scoutingStrategy.generateEmptyScoutingReport("condition not satisfied");
	}
	
	private List<ReportCharacter> getDeflectedReportCharacters(List<ReportCharacter> reportCharacters, Scouter scouter, Long seed) {
		reportCharacters.forEach((reportCharacter -> {
			CharacterReportStatus characterReportStatus = characterStatusReportService
					.getDeflectedCharacterReport(reportCharacter.getTargetCharacter(), scouter.getScouterStatus(), seed);
			reportCharacter.setCharacterReportStatus(characterReportStatus);
		}));
		return reportCharacters;
	}
}
