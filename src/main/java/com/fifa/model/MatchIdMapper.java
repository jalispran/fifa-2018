package com.fifa.model;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "matchIdMapper")
public class MatchIdMapper {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger keyId;
	
	private BigInteger fifaMatchId;
	
	private String matchIdFifaServer;

	public BigInteger getKeyId() {
		return keyId;
	}

	public void setKeyId(BigInteger keyId) {
		this.keyId = keyId;
	}

	public BigInteger getFifaMatchId() {
		return fifaMatchId;
	}

	public void setFifaMatchId(BigInteger fifaMatchId) {
		this.fifaMatchId = fifaMatchId;
	}

	public String getMatchIdFifaServer() {
		return matchIdFifaServer;
	}

	public void setMatchIdFifaServer(String matchIdFifaServer) {
		this.matchIdFifaServer = matchIdFifaServer;
	}

	@Override
	public String toString() {
		return "MatchIdMapper [keyId=" + keyId + ", fifaMatchId=" + fifaMatchId + ", matchIdFifaServer="
				+ matchIdFifaServer + "]";
	}

	
}
