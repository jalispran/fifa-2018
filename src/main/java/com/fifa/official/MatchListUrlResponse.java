package com.fifa.official;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchListUrlResponse {

	@JsonProperty("ContinuationToken")
	private Object ContinuationToken;
	
	@JsonProperty("ContinuationHash")
	private Object ContinuationHash;
	
	@JsonProperty("Results")
	private List<RootObject> Results;

	public Object getContinuationToken() {
		return ContinuationToken;
	}

	public void setContinuationToken(Object continuationToken) {
		ContinuationToken = continuationToken;
	}

	public Object getContinuationHash() {
		return ContinuationHash;
	}

	public void setContinuationHash(Object continuationHash) {
		ContinuationHash = continuationHash;
	}

	public List<RootObject> getResults() {
		return Results;
	}

	public void setResults(List<RootObject> results) {
		Results = results;
	}

	@Override
	public String toString() {
		return "MatchListUrlResponse [ContinuationToken=" + ContinuationToken + ", ContinuationHash=" + ContinuationHash
				+ ", Results=" + Results + "]";
	}
	
}
