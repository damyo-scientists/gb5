package com.game.gb5.model.scouting;

import java.util.Date;

import lombok.Data;
import com.game.gb5.model.scouting.condition.ReportGenerateCondition;
import com.game.gb5.model.scouting.condition.TimeReportGenerateCondition;
import com.game.gb5.model.scouting.report.ScoutingReport;
import com.game.gb5.model.scouting.strategy.DefaultScoutingStrategy;
import com.game.gb5.model.scouting.strategy.ScoutingStrategy;

@Data
public class Scouter {
	private String name;
	private ScouterStatus scouterStatus;
	private ScoutingStrategy scoutingStrategy;
	private Date reportRegenTime;
	private ReportGenerateCondition reportGenerateCondition;
	
	public Scouter() {
		this.scoutingStrategy = new DefaultScoutingStrategy();
		this.reportGenerateCondition = new TimeReportGenerateCondition();
		this.reportRegenTime = new Date();
	}
	
	public ScoutingReport makeScoutingReport() {
		if (((TimeReportGenerateCondition) this.reportGenerateCondition).isConditionSatisfied(reportRegenTime)) {
			this.setReportRegenTime(new Date(new Date().getTime() + 20000));
			return scoutingStrategy.generateScoutingReport(scouterStatus);
		}
		return scoutingStrategy.generateEmptyScoutingReport();
	}
	
	public void setReportResetTime(Date date) {
		this.scouterStatus.setReportResetTime(date);
	}
}
