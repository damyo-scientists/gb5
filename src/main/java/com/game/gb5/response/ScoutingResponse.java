package com.game.gb5.response;

import com.game.gb5.model.scouting.report.ScoutingReport;

public class ScoutingResponse extends BaseResponse {
	public ScoutingResponse(ScoutingReport data) {
		this(data, "");
	}
	
	private ScoutingResponse(ScoutingReport data, String message) {
		this(data, message, false);
	}
	
	private ScoutingResponse(ScoutingReport data, String message, boolean isError) {
		this.data = data;
		this.message = message;
		this.isError = isError;
	}
	
	protected ScoutingReport data;
}
