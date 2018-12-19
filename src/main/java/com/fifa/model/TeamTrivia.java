package com.fifa.model;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teamTrivia")
public class TeamTrivia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger keyId;
	
	private BigInteger teamId;
	
	private String trivia;

	public BigInteger getKeyId() {
		return keyId;
	}

	public void setKeyId(BigInteger keyId) {
		this.keyId = keyId;
	}

	public BigInteger getTeamId() {
		return teamId;
	}

	public void setTeamId(BigInteger teamId) {
		this.teamId = teamId;
	}

	public String getTrivia() {
		return trivia;
	}

	public void setTrivia(String trivia) {
		this.trivia = trivia;
	}

	@Override
	public String toString() {
		return "TeamTrivia [keyId=" + keyId + ", teamId=" + teamId + ", trivia=" + trivia + "]";
	}
	
}
