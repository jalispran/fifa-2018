package com.fifa.official;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class HomeTeam
{
  private int Score;

  public int getScore() { return this.Score; }

  public void setScore(int Score) { this.Score = Score; }

  @JsonIgnore
  private Object Side;

  public Object getSide() { return this.Side; }

  public void setSide(Object Side) { this.Side = Side; }
  
  @JsonIgnore
  private String IdTeam;

  public String getIdTeam() { return this.IdTeam; }

  public void setIdTeam(String IdTeam) { this.IdTeam = IdTeam; }

  @JsonIgnore
  private String PictureUrl;

  public String getPictureUrl() { return this.PictureUrl; }

  public void setPictureUrl(String PictureUrl) { this.PictureUrl = PictureUrl; }

  private String IdCountry;

  public String getIdCountry() { return this.IdCountry; }

  public void setIdCountry(String IdCountry) { this.IdCountry = IdCountry; }

  @JsonIgnore
  private int TeamType;

  public int getTeamType() { return this.TeamType; }

  public void setTeamType(int TeamType) { this.TeamType = TeamType; }

  @JsonIgnore
  private int AgeType;

  public int getAgeType() { return this.AgeType; }

  public void setAgeType(int AgeType) { this.AgeType = AgeType; }

  private String Tactics;

  public String getTactics() { return this.Tactics; }

  public void setTactics(String Tactics) { this.Tactics = Tactics; }

  private String TeamName;

  public String getTeamName() { return this.TeamName; }

  public void setTeamName(ArrayList<TeamName> TeamNames) { 
	for(TeamName teamName : TeamNames) {
		String locale = teamName.getLocale();
		if("en-GB".equals(locale))
			this.TeamName = teamName.getDescription();
	}  
  }

  private ArrayList<Coach> Coaches;

  public ArrayList<Coach> getCoaches() { return this.Coaches; }

  public void setCoaches(ArrayList<Coach> Coaches) { this.Coaches = Coaches; }

  private ArrayList<Player> Players;

  public ArrayList<Player> getPlayers() { return this.Players; }

  public void setPlayers(ArrayList<Player> Players) { this.Players = Players; }

  private ArrayList<Booking> Bookings;

  public ArrayList<Booking> getBookings() { return this.Bookings; }

  public void setBookings(ArrayList<Booking> Bookings) { this.Bookings = Bookings; }

  private ArrayList<Goal> Goals;

  public ArrayList<Goal> getGoals() { return this.Goals; }

  public void setGoals(ArrayList<Goal> Goals) { this.Goals = Goals; }

  private ArrayList<Substitution> Substitutions;

  public ArrayList<Substitution> getSubstitutions() { return this.Substitutions; }

  public void setSubstitutions(ArrayList<Substitution> Substitutions) { this.Substitutions = Substitutions; }

  private int FootballType;

  public int getFootballType() { return this.FootballType; }

  public void setFootballType(int FootballType) { this.FootballType = FootballType; }

  private int Gender;

  public int getGender() { return this.Gender; }

  public void setGender(int Gender) { this.Gender = Gender; }
}
