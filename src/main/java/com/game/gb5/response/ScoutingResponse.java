package com.game.gb5.response;

import com.game.gb5.domain.scouting.report.ScoutingReport;

import lombok.Data;

@Data
public class ScoutingResponse extends BaseResponse {
	public ScoutingResponse(ScoutingReport data) {
		this(data, "");
	}
	
	public ScoutingResponse(ScoutingReport data, String message) {
		this(data, message, false);
	}
	
	public ScoutingResponse(ScoutingReport data, String message, boolean isError) {
		this.data = data;
		this.message = message;
		this.isError = isError;
	}
	
	protected ScoutingReport data;
}
