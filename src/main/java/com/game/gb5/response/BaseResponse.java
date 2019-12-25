package com.game.gb5.response;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse implements Serializable {
	protected boolean isError = false;
	protected String message = "";
	protected Object data;
}
