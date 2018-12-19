package com.fifa.official;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Booking
{
	private int Card;

	public int getCard() { return this.Card; }

	public void setCard(int Card) { this.Card = Card; }
	
	@JsonIgnore
	private int Period;

	public int getPeriod() { return this.Period; }

	public void setPeriod(int Period) { this.Period = Period; }
	
	@JsonIgnore
	private Object IdEvent;

	public Object getIdEvent() { return this.IdEvent; }

	public void setIdEvent(Object IdEvent) { this.IdEvent = IdEvent; }
	
	@JsonIgnore
	private Object EventNumber;

	public Object getEventNumber() { return this.EventNumber; }

	public void setEventNumber(Object EventNumber) { this.EventNumber = EventNumber; }

	@JsonIgnore
	private String IdPlayer;

	public String getIdPlayer() { return this.IdPlayer; }

	public void setIdPlayer(String IdPlayer) { this.IdPlayer = IdPlayer; }

	@JsonIgnore
	private String IdTeam;

	public String getIdTeam() { return this.IdTeam; }

	public void setIdTeam(String IdTeam) { this.IdTeam = IdTeam; }

	private String Minute;

	public String getMinute() { return this.Minute; }

	public void setMinute(String Minute) { this.Minute = Minute; }

	private Object Reason;

	public Object getReason() { return this.Reason; }

	public void setReason(Object Reason) { this.Reason = Reason; }
}