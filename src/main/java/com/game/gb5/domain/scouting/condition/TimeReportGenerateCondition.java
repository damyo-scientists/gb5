package com.game.gb5.domain.scouting.condition;

import java.time.LocalDateTime;

public class TimeReportGenerateCondition implements ReportGenerateCondition {
	private LocalDateTime dateCondition;
	
	public TimeReportGenerateCondition(LocalDateTime dateCondition) {
		this.dateCondition = dateCondition;
	}
	
	public boolean isConditionSatisfied() {
		LocalDateTime current = LocalDateTime.now();
		if (current.isAfter(dateCondition)) {
			return true;
		}
		return false;
	}
}
