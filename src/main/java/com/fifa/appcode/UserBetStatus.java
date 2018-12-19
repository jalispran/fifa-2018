package com.fifa.appcode;

public enum UserBetStatus {
	
	WIN("W"),
	LOSS("L"),
	DRAW("D"),
	NOBET("NB"),
	UNDEFINED("U");
	
	private String code;
	
	private UserBetStatus(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

}
