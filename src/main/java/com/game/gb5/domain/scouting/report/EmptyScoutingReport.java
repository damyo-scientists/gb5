package com.game.gb5.domain.scouting.report;

import lombok.Data;

@Data
public class EmptyScoutingReport extends ScoutingReport {
	boolean isEmpty = true;
	String emptyReason;
	
	public EmptyScoutingReport(String emptyReason) {
		this.emptyReason = emptyReason;
	}
}