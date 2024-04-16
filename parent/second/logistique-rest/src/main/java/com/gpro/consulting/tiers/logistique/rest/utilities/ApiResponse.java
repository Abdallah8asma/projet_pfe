package com.gpro.consulting.tiers.logistique.rest.utilities;

public class ApiResponse {
	
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ApiResponse(String msg) {
		super();
		this.msg = msg;
	}
	
	public ApiResponse() {
		super();
		
	}
	
}
