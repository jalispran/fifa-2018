package com.fifa.official;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Goal
{
	@JsonIgnore
	private int Type;

	public int getType() { return this.Type; }

	public void setType(int Type) { this.Type = Type; }

	@JsonIgnore
	private String IdPlayer;

	public String getIdPlayer() { return this.IdPlayer; }

	public void setIdPlayer(String IdPlayer) { this.IdPlayer = IdPlayer; }

	private String Minute;

	public String getMinute() { return this.Minute; }

	public void setMinute(String Minute) { this.Minute = Minute; }

	@JsonIgnore
	private String IdAssistPlayer;

	public String getIdAssistPlayer() { return this.IdAssistPlayer; }

	public void setIdAssistPlayer(String IdAssistPlayer) { this.IdAssistPlayer = IdAssistPlayer; }

	private int Period;

	public int getPeriod() { return this.Period; }

	public void setPeriod(int Period) { this.Period = Period; }

	@JsonIgnore
	private Object IdGoal;

	public Object getIdGoal() { return this.IdGoal; }

	public void setIdGoal(Object IdGoal) { this.IdGoal = IdGoal; }

	@JsonIgnore
	private String IdTeam;

	public String getIdTeam() { return this.IdTeam; }

	public void setIdTeam(String IdTeam) { this.IdTeam = IdTeam; }
}