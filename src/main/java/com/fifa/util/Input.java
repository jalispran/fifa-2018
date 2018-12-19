package com.fifa.util;

import java.math.BigInteger;

import org.springframework.util.Assert;

public class Input {
	
	private String mobileNo;
	
	private String password;
	
	private String proPic;
	
	private String name;
	
	private BigInteger betId;
	
	private String teamName;
	
	private BigInteger matchId;
	
	private Double rateApp;
	
	private String comment;
	
	private BigInteger userId;
	
	public String getMobileNo() {
		Assert.notNull(mobileNo, "Please provide mobileNo");
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPassword() {
		Assert.notNull(password, "Please provide password");
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProPic() {
		return proPic;
	}

	public void setProPic(String proPic) {
		this.proPic = proPic;
	}

	public String getName() {
		Assert.notNull(name, "Please provide name");
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public BigInteger getBetId() {
		Assert.notNull(betId, "Please provide betId");
		return betId;
	}
	
	public void setBetId(BigInteger betId) {
		this.betId = betId;
	}
	
	public String getTeamName() {
		return teamName;
	}
	
	public void setTeamName(String team) {
		this.teamName = team;
	}
	
	public BigInteger getMatchId() {
		return matchId;
	}
	
	public void setMatchId(BigInteger matchId) {
		this.matchId = matchId;
	}

	public Double getRateApp() {
		return rateApp;
	}

	public void setRateApp(Double rateApp) {
		this.rateApp = rateApp;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public BigInteger getUserId() {
		Assert.notNull(userId, "Please provide userId");
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	
}
