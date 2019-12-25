package com.game.gb5.domain.scouting;

import com.game.gb5.domain.BaseEntity;
import com.game.gb5.domain.character.CharacterSet;
import com.game.gb5.domain.scouting.condition.ReportGenerateCondition;
import com.game.gb5.domain.scouting.condition.TimeReportGenerateCondition;
import com.game.gb5.domain.scouting.report.ScoutingReport;
import com.game.gb5.domain.scouting.strategy.DefaultScoutingStrategy;
import com.game.gb5.domain.scouting.strategy.ScoutingStrategy;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Scouter extends BaseEntity {
	
	@Column
	private String name;
	@ManyToOne(fetch = FetchType.LAZY)
	private ScouterStatus scouterStatus;
	@ManyToOne(fetch = FetchType.LAZY)
	private CharacterSet characterSet;
	
	/**
	 * Server Only
	 */
	@Column
	private boolean isHold;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date reportRegenTime;
	
	public ScoutingReport makeScoutingReport(CharacterSet characterSet) {
		//Todo condition 및 strategy 분리
		reportRegenTime = new Date();
		ReportGenerateCondition reportGenerateCondition = new TimeReportGenerateCondition(reportRegenTime);
		ScoutingStrategy scoutingStrategy = new DefaultScoutingStrategy();
		if (reportGenerateCondition.isConditionSatisfied()) {
			this.setReportRegenTime(new Date(new Date().getTime() + 10000));
			return scoutingStrategy.generateScoutingReport(scouterStatus, characterSet);
		}
		return scoutingStrategy.generateEmptyScoutingReport();
	}
}
