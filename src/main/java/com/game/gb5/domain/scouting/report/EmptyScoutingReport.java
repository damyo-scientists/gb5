package com.game.gb5.domain.scouting.report;

import java.util.ArrayList;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class EmptyScoutingReport extends ScoutingReport {
	boolean isEmpty = true;
	String emptyReason;
	
	public EmptyScoutingReport(String emptyReason) {
		super(new ArrayList<>());
		this.emptyReason = emptyReason;
	}
}
