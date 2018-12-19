package com.fifa.appcode;

public enum FifaBlobType {

	FLAG("FLG"),
	IMAGE("IMG"),
	BACKGROUND_IMAGE("BGM");
	
	private String code;
	
	private FifaBlobType(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
}
