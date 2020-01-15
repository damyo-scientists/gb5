package com.game.gb5.response;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> implements Serializable {
	protected boolean isError = false;
	protected String message = "";
	protected T data;
	
	public Response(T data) {
		this(data, "");
	}
	
	public Response(T data, String message) {
		this(data, message, false);
	}
	
	public Response(T data, String message, boolean isError) {
		this.data = data;
		this.message = message;
		this.isError = isError;
	}
}
