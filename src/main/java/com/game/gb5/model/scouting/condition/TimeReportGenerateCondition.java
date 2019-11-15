package com.game.gb5.model.scouting.condition;

import java.util.Date;

public class TimeReportGenerateCondition implements ReportGenerateCondition {
	private Date dateCondition;
	
	public TimeReportGenerateCondition(Date dateCondition) {
		this.dateCondition = dateCondition;
	}
	
	public boolean isConditionSatisfied() {
		Date current = new Date();
		if (dateCondition.getTime() < current.getTime()) {
			return true;
		}
		return false;
	}
}
