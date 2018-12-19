package com.fifa.util;

import org.springframework.util.Assert;

public class Input {
	
	
	private String matchId;
	
	public String getMatchId() {
		Assert.notNull(matchId, "Please provide matchId");
		return matchId;
	}
	
	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}
	
}
