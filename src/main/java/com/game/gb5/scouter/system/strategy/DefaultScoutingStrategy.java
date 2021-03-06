package com.game.gb5.scouter.system.strategy;

import com.game.gb5.character.model.CharacterSet;
import com.game.gb5.character.model.Character;
import com.game.gb5.scouter.model.Scouter;
import com.game.gb5.scouter.model.ScouterStatus;
import com.game.gb5.scouter.model.report.EmptyScoutingReport;
import com.game.gb5.scouter.model.report.ReportCharacter;
import com.game.gb5.scouter.model.report.ScoutingReport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultScoutingStrategy implements ScoutingStrategy {
	private static Logger logger = LogManager.getLogger(ScoutingStrategy.class);
	private Long seed;

	public DefaultScoutingStrategy(Long seed) {
		this.seed = seed;
	}

	@Override
	public ScoutingReport generateScoutingReport(Scouter scouter) {
		ScouterStatus scouterStatus = scouter.getScouterStatus();
		CharacterSet characterSet = scouter.getCharacterSet();
		ScoutingReport scoutingReport = new ScoutingReport();

		// 획득확률에 따라무작위로 선택된, 리포트 캐릭터 개수 만큼의 캐릭터들로 구성됨
		List<Character> characterList = characterSet.getCharacters();
		PickCharacterStrategy pickCharacterStrategy = new DefaultPickCharacterStrategy(seed);
		List<Character> pickedCharacters = pickCharacterStrategy.pickCharacters(characterList, scouterStatus);
		List<ReportCharacter> reportCharacters = pickedCharacters.stream().map(chr -> {
			ReportCharacter reportCharacter = new ReportCharacter();
			reportCharacter.setTargetCharacter(chr);
			reportCharacter.setScoutingReport(scoutingReport);

			return reportCharacter;
		}).collect(Collectors.toList());

		scoutingReport.setReportCharacterList(reportCharacters);
		scoutingReport.setScouter(scouter);
		return scoutingReport;
	}

	@Override
	public ScoutingReport generateEmptyScoutingReport(String emptyReason) {
		return new EmptyScoutingReport(emptyReason);
	}
}
