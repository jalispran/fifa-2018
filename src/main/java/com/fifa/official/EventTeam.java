package com.fifa.official;

import java.util.List;

public class EventTeam {

	String teamName;
	int penaltyScore;
	int teamScore;
	String tactics;
	List<Player> players;
	List<Booking> bookings;
	List<Goal> goals;
	List<Substitution> substitutions;
	
	boolean isWinner = false;
	Weather weather;
	

	public EventTeam(HomeTeam homeTeam, RootObject event){
		this.setTeamName(homeTeam.getTeamName());
		this.setPenaltyScore(event.getHomeTeamPenaltyScore());
		this.setTeamScore(homeTeam.getScore());
		this.setTactics(homeTeam.getTactics());
		this.setPlayers(homeTeam.getPlayers());
		this.setBookings(homeTeam.getBookings());
		this.setGoals(homeTeam.getGoals());
		this.setSubstitutions(homeTeam.getSubstitutions());
		this.setWeather(event.getWeather());
		
		String winner = event.getWinner();
		String idHomeTeam = homeTeam.getIdTeam();
		if(idHomeTeam.equals(winner))
			setWinner(true);
	}

	public EventTeam(AwayTeam awayTeam, RootObject event){
		this.setTeamName(awayTeam.getTeamName());
		this.setPenaltyScore(event.getHomeTeamPenaltyScore());
		this.setTeamScore(awayTeam.getScore());
		this.setTactics(awayTeam.getTactics());
		this.setPlayers(awayTeam.getPlayers());
		this.setBookings(awayTeam.getBookings());
		this.setGoals(awayTeam.getGoals());
		this.setSubstitutions(awayTeam.getSubstitutions());
		this.setWeather(event.getWeather());
		
		String winner = event.getWinner();
		String idHomeTeam = awayTeam.getIdTeam();
		if(idHomeTeam.equals(winner))
			setWinner(true);
	}

	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public int getPenaltyScore() {
		return penaltyScore;
	}
	public void setPenaltyScore(int penaltyScore) {
		this.penaltyScore = penaltyScore;
	}
	public int getTeamScore() {
		return teamScore;
	}
	public void setTeamScore(int teamScore) {
		this.teamScore = teamScore;
	}
	public String getTactics() {
		return tactics;
	}
	public void setTactics(String tactics) {
		this.tactics = tactics;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public List<Booking> getBookings() {
		return bookings;
	}
	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}
	public List<Goal> getGoals() {
		return goals;
	}
	public void setGoals(List<Goal> goals) {
		this.goals = goals;
	}
	public List<Substitution> getSubstitutions() {
		return substitutions;
	}
	public void setSubstitutions(List<Substitution> substitutions) {
		this.substitutions = substitutions;
	}

	public boolean isWinner() {
		return isWinner;
	}

	public void setWinner(boolean isWinner) {
		this.isWinner = isWinner;
	}
	
	public Weather getWeather() {
		return weather;
	}
	
	public void setWeather(Weather weather) {
		this.weather = weather;
	}
}