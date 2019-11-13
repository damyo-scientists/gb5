package com.game.gb5.model.scouting.condition;

import java.util.Date;

public class TimeReportGenerateCondition implements ReportGenerateCondition {
	public boolean isConditionSatisfied(Date condition) {
		Date current = new Date();
		if (condition.getTime() < current.getTime()) {
			return true;
		}
		return false;
	}
}
