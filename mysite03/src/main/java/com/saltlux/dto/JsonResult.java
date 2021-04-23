package com.saltlux.dto;

public class JsonResult {
	private String result; // success or fail
	private Object data; //
	private String message;

	private JsonResult() {
	}

	private JsonResult(String message) {
		result = "fail";
		this.message = message;
	}

	private JsonResult(Object data) {
		this.result = "success";
		this.data = data;
	}

	public static JsonResult fail(String message) {
		return new JsonResult(message);
	}

	public static JsonResult success(Object data) {
		return new JsonResult(data);
	}

	public String getResult() {
		return result;
	}

	public Object getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}

}
