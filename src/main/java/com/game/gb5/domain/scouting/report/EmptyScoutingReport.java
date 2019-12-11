package com.game.gb5.domain.scouting.report;

import java.util.ArrayList;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmptyScoutingReport extends ScoutingReport {
	boolean isEmpty = true;
	String emptyReason;
	
	public EmptyScoutingReport(String emptyReason) {
		super(new ArrayList<>());
		this.emptyReason = emptyReason;
	}
}
