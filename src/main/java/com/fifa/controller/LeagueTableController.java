package com.fifa.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fifa.appcode.ResponseCode;
import com.fifa.util.Output;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController		
public class LeagueTableController {
	private static final Logger log = LoggerFactory.getLogger(LeagueTableController.class);
	
	private static final String FOOTBALL_DATA_ORG_HEADER = "x-auth-token";
	private static final String FOOTBALL_DATA_ORG_API_KEY = "0d45809d0df84bac874d00a71cb1e767";
	private static final String URL = "http://api.football-data.org/v1/competitions/";
	private static final String COMPETITION_NUMBER = "467";
	private static final String LEAGUETABLE_ENDPOINT = "/leagueTable";

	@GetMapping("/leaguetable")
	@ResponseBody
	public ResponseEntity<?> getLeagueTable() {
		log.info("GET LEAGUE TABLE");
		Output out = new Output();
		
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url(URL + COMPETITION_NUMBER + LEAGUETABLE_ENDPOINT).get()
		  .addHeader(FOOTBALL_DATA_ORG_HEADER, FOOTBALL_DATA_ORG_API_KEY)
		  .build();

		Response response;
		String leagueDetails;
		try {
			response = client.newCall(request).execute();
			leagueDetails = response.body().string();
		} catch (IOException e) {
			log.error(e.getMessage());
			out.setMessage("Try after sometime");
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(out);
		}
		
		FifaLeagueTable leagueTable = new Gson().fromJson(leagueDetails, FifaLeagueTable.class);
		out.setData("leagueTable", leagueTable.getStandings());
		
		out.setResponseCode(ResponseCode.OK);
		
		if (out.isSuccess())
			return ResponseEntity.ok(out);
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(out);
	}
	
}

class FifaLeagueTable {
	@JsonIgnore
	String leagueCaption;
	@JsonIgnore
	int matchday;
	Standings standings;
	public String getLeagueCaption() {
		return leagueCaption;
	}
	public void setLeagueCaption(String leagueCaption) {
		this.leagueCaption = leagueCaption;
	}
	public int getMatchday() {
		return matchday;
	}
	public void setMatchday(int matchday) {
		this.matchday = matchday;
	}
	public Standings getStandings() {
		return standings;
	}
	public void setStandings(Standings standings) {
		this.standings = standings;
	}
	
}

class Standings {
	@JsonProperty("A")
	List<TeamDetailsForLeagueTable> A;
	@JsonProperty("B")
	List<TeamDetailsForLeagueTable> B;
	@JsonProperty("C")
	List<TeamDetailsForLeagueTable> C;
	@JsonProperty("D")
	List<TeamDetailsForLeagueTable> D;
	@JsonProperty("E")
	List<TeamDetailsForLeagueTable> E;
	@JsonProperty("F")
	List<TeamDetailsForLeagueTable> F;
	@JsonProperty("G")
	List<TeamDetailsForLeagueTable> G;
	@JsonProperty("H")
	List<TeamDetailsForLeagueTable> H;
}

class TeamDetailsForLeagueTable {
	String group;
	int rank;
	String team;
	@JsonIgnore
	int teamId;
	int playedGames;
	@JsonIgnore
	String crestURI;
	int points;
	int goals;
	int goalsAgainst;
	int goalDifference;
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public int getPlayedGames() {
		return playedGames;
	}
	public void setPlayedGames(int playedGames) {
		this.playedGames = playedGames;
	}
	public String getCrestURI() {
		return crestURI;
	}
	public void setCrestURI(String crestURI) {
		this.crestURI = crestURI;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getGoals() {
		return goals;
	}
	public void setGoals(int goals) {
		this.goals = goals;
	}
	public int getGoalsAgainst() {
		return goalsAgainst;
	}
	public void setGoalsAgainst(int goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}
	public int getGoalDifference() {
		return goalDifference;
	}
	public void setGoalDifference(int goalDifference) {
		this.goalDifference = goalDifference;
	}
	
}
