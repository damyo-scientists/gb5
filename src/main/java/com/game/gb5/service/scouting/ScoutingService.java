package com.game.gb5.service.scouting;

import com.game.gb5.common.random.RandomMaker;
import com.game.gb5.model.character.CharacterReportStatus;
import com.game.gb5.model.user.User;
import com.game.gb5.model.scouting.Scouter;
import com.game.gb5.model.item.TicketType;
import com.game.gb5.model.report.EmptyScoutingReport;
import com.game.gb5.model.report.ReportCharacter;
import com.game.gb5.model.report.ScoutingReport;
import com.game.gb5.repository.scouting.ReportCharacterRepository;
import com.game.gb5.repository.scouting.ScouterRepository;
import com.game.gb5.repository.scouting.ScoutingReportDao;
import com.game.gb5.system.condition.ReportGenerateCondition;
import com.game.gb5.system.condition.TimeReportGenerateCondition;
import com.game.gb5.system.strategy.DefaultScoutingStrategy;
import com.game.gb5.system.strategy.ScoutingStrategy;
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
    private ScouterRepository scouterRepository;
    @Autowired
    private CharacterReportStatusService characterStatusReportService;
    @Autowired
    private ReportCharacterRepository reportCharacterRepository;
    @Autowired
    private ScoutingReportDao scoutingReportDao;

    public Scouter getScouterById(Long id) {
        Scouter scouter = scouterRepository.getScouterById(id);
        ScoutingReport scoutingReport = scouter.getScoutingReport();
        if (scoutingReport != null) {
            List<ReportCharacter> reportCharacters = this.getDeflectedReportCharacters(scoutingReport.getReportCharacterList(), scouter, scouter.getSeed());
            scoutingReport.setReportCharacterList(reportCharacters);
        }
        return scouter;
    }

    public ScoutingReport makeNewScoutingReport(Scouter scouter, User user) {
        if (user.getInventory().getTickets().containsKey(TicketType.INSTANT_ACQUIRE_REPORT)) {
            ScoutingReport scoutingReport = this.generateScoutingReportByStrategy(scouter);

            user.getInventory().consumeTicket(TicketType.INSTANT_ACQUIRE_REPORT);
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
            reportCharacterRepository.saveAll(reportCharacters);

            scouter.setSeed(seed);
            scouter.setReportRegenTime(regenTime);
            scouter.setScoutingReport(scoutingReport);
            scouterRepository.save(scouter);
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
