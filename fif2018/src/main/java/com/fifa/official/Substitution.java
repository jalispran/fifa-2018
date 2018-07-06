package com.fifa.official;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Substitution {

	@JsonIgnore
	private Object IdEvent;

	public Object getIdEvent() { return this.IdEvent; }

	public void setIdEvent(Object IdEvent) { this.IdEvent = IdEvent; }

	private int Period;

	public int getPeriod() { return this.Period; }

	public void setPeriod(int Period) { this.Period = Period; }

	@JsonIgnore
	private int Reason;

	public int getReason() { return this.Reason; }

	public void setReason(int Reason) { this.Reason = Reason; }

	private int SubstitutePosition;

	public int getSubstitutePosition() { return this.SubstitutePosition; }

	public void setSubstitutePosition(int SubstitutePosition) { this.SubstitutePosition = SubstitutePosition; }

	@JsonIgnore
	private String IdPlayerOff;

	public String getIdPlayerOff() { return this.IdPlayerOff; }

	public void setIdPlayerOff(String IdPlayerOff) { this.IdPlayerOff = IdPlayerOff; }

	@JsonIgnore
	private String IdPlayerOn;

	public String getIdPlayerOn() { return this.IdPlayerOn; }

	public void setIdPlayerOn(String IdPlayerOn) { this.IdPlayerOn = IdPlayerOn; }

	private String PlayerOffName;

	public String getPlayerOffName() { return this.PlayerOffName; }

	public void setPlayerOffName(ArrayList<PlayerOffName> PlayerOffNames) { 
		for(PlayerOffName PlayerOffName : PlayerOffNames) {
			String locale = PlayerOffName.getLocale();
			if("en-GB".equals(locale))
				this.PlayerOffName = PlayerOffName.getDescription();
		} 
	}

	private String PlayerOnName;

	public String getPlayerOnName() { return this.PlayerOnName; }

	public void setPlayerOnName(ArrayList<PlayerOnName> PlayerOnNames) { 
		for(PlayerOnName PlayerOnName : PlayerOnNames) {
			String locale = PlayerOnName.getLocale();
			if("en-GB".equals(locale))
				this.PlayerOnName = PlayerOnName.getDescription();
		}
	}

	private String Minute;

	public String getMinute() { return this.Minute; }

	public void setMinute(String Minute) { this.Minute = Minute; }

	@JsonIgnore
	private String IdTeam;

	public String getIdTeam() { return this.IdTeam; }

	public void setIdTeam(String IdTeam) { this.IdTeam = IdTeam; }
}