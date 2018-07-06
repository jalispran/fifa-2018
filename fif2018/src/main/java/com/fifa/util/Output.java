 package com.fifa.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fifa.appcode.ResponseCode;

@SuppressWarnings("serial")
public class Output implements Serializable {
	private ResponseCode responseCode;
	
	private String message;
	
	@JsonIgnore
	private boolean success = false;
	
	private Map<String, Object> data = new HashMap<>();
	
	public Output() {
		setResponseCode(ResponseCode.ERR_APLCAN);
	}

	public String getResponseCode() {
		return responseCode.getCode();
	}

	public void setResponseCode(ResponseCode responseCode) {
		this.responseCode = responseCode;
		this.message = responseCode.getDescription();
		if(responseCode.equals(ResponseCode.OK))
			this.success = true;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	public void setData(String key, Object value) {
		this.data.put(key, value);
	}

	public boolean isSuccess() {
		return success;
	}

}
