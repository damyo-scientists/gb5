package com.game.gb5.response;

import lombok.Data;

@Data
public class BaseResponse {
	protected boolean isError = false;
	protected String message = "";
	protected Object data;
}
