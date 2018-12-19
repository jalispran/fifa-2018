package com.fifa.official;

public class Meta {
	private String lastUpdatedAt;
	private String apiVersion;
	private String generatedAt;


	// Getter Methods 

	public String getLastUpdatedAt() {
		return lastUpdatedAt;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public String getGeneratedAt() {
		return generatedAt;
	}

	// Setter Methods 

	public void setLastUpdatedAt(String lastUpdatedAt) {
		this.lastUpdatedAt = lastUpdatedAt;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public void setGeneratedAt(String generatedAt) {
		this.generatedAt = generatedAt;
	}
}