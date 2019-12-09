package com.game.gb5.domain.scouting;

import com.game.gb5.domain.character.CharacterSet;
import com.game.gb5.domain.scouting.condition.ReportGenerateCondition;
import com.game.gb5.domain.scouting.condition.TimeReportGenerateCondition;
import com.game.gb5.domain.scouting.report.ScoutingReport;
import com.game.gb5.domain.scouting.strategy.DefaultScoutingStrategy;
import com.game.gb5.domain.scouting.strategy.ScoutingStrategy;

import java.util.Date;

import lombok.Data;

@Data
public class Scouter {
	// 변하는 값
	private boolean isHold;
	private ScoutingStrategy scoutingStrategy;
	private Date reportRegenTime;
	private ReportGenerateCondition reportGenerateCondition;
	
	// 변하지 않는 값
	private String name;
	private ScouterStatus scouterStatus;
	private CharacterSet characterSet;
	
	public Scouter() {
		this.scoutingStrategy = new DefaultScoutingStrategy();
		this.reportRegenTime = new Date();
		this.reportGenerateCondition = new TimeReportGenerateCondition(reportRegenTime);
	}
	
	public ScoutingReport makeScoutingReport(CharacterSet characterSet) {
		if (this.reportGenerateCondition.isConditionSatisfied()) {
			this.setReportRegenTime(new Date(new Date().getTime() + scouterStatus.getReportResetTime().getTime()));
			return scoutingStrategy.generateScoutingReport(scouterStatus, characterSet);
		}
		return scoutingStrategy.generateEmptyScoutingReport();
	}
}
