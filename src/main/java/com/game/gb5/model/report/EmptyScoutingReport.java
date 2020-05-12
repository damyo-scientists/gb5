package com.game.gb5.model.report;

import com.game.gb5.model.report.ScoutingReport;
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
