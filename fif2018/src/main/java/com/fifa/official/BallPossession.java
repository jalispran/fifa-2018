package com.fifa.official;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BallPossession
{
	@JsonIgnore
  private ArrayList<Object> Intervals;

  public ArrayList<Object> getIntervals() { return this.Intervals; }

  public void setIntervals(ArrayList<Object> Intervals) { this.Intervals = Intervals; }

  @JsonIgnore
  private ArrayList<Object> LastX;

  public ArrayList<Object> getLastX() { return this.LastX; }

  public void setLastX(ArrayList<Object> LastX) { this.LastX = LastX; }

  private double OverallHome;

  public double getOverallHome() { return this.OverallHome; }

  public void setOverallHome(double OverallHome) { this.OverallHome = OverallHome; }

  private double OverallAway;

  public double getOverallAway() { return this.OverallAway; }

  public void setOverallAway(double OverallAway) { this.OverallAway = OverallAway; }
}