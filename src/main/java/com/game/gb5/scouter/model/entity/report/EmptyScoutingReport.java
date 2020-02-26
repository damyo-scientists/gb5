package com.game.gb5.scouter.model.entity.report;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class EmptyScoutingReport extends ScoutingReport {
	String emptyReason;
	
	public EmptyScoutingReport(String emptyReason) {
		this.emptyReason = emptyReason;
	}
}
