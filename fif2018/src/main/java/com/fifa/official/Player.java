package com.fifa.official;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Player
{
	@JsonIgnore
	private String IdPlayer;

	public String getIdPlayer() { return this.IdPlayer; }

	public void setIdPlayer(String IdPlayer) { this.IdPlayer = IdPlayer; }
	
	@JsonIgnore
	private String IdTeam;

	public String getIdTeam() { return this.IdTeam; }

	public void setIdTeam(String IdTeam) { this.IdTeam = IdTeam; }

	private int ShirtNumber;

	public int getShirtNumber() { return this.ShirtNumber; }

	public void setShirtNumber(int ShirtNumber) { this.ShirtNumber = ShirtNumber; }
	
	@JsonIgnore
	private int Status;

	public int getStatus() { return this.Status; }

	public void setStatus(int Status) { this.Status = Status; }
	
	@JsonIgnore
	private Integer SpecialStatus;

	public Integer getSpecialStatus() { return this.SpecialStatus; }

	public void setSpecialStatus(Integer SpecialStatus) { this.SpecialStatus = SpecialStatus; }

	private boolean Captain;

	public boolean getCaptain() { return this.Captain; }

	public void setCaptain(boolean Captain) { this.Captain = Captain; }

	private String PlayerName;

	public String getPlayerName() { return this.PlayerName; }

	public void setPlayerName(ArrayList<PlayerName> PlayerNames) { 
		for(PlayerName playerName : PlayerNames) {
			String locale = playerName.getLocale();
			if("en-GB".equals(locale))
				this.PlayerName = playerName.getDescription();
		}
	}
	
	@JsonIgnore
	private String ShortName;

	public String getShortName() { return this.ShortName; }

	public void setShortName(ArrayList<ShortName> ShortNames) { 
		for(ShortName ShortName : ShortNames) {
			String locale = ShortName.getLocale();
			if("en-GB".equals(locale))
				this.ShortName = ShortName.getDescription();
		}
	}

	private int Position;

	public int getPosition() { return this.Position; }

	public void setPosition(int Position) { this.Position = Position; }

	@JsonIgnore
	private int FieldStatus;

	public int getFieldStatus() { return this.FieldStatus; }

	public void setFieldStatus(int FieldStatus) { this.FieldStatus = FieldStatus; }

	@JsonIgnore
	private Double LineupX;

	public Double getLineupX() { return this.LineupX; }

	public void setLineupX(Double LineupX) { this.LineupX = LineupX; }

	@JsonIgnore
	private Double LineupY;

	public Double getLineupY() { return this.LineupY; }

	public void setLineupY(Double LineupY) { this.LineupY = LineupY; }
}