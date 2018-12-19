package com.fifa.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fifa.appcode.ResponseCode;
import com.fifa.official.AwayTeam;
import com.fifa.official.EventTeam;
import com.fifa.official.HomeTeam;
import com.fifa.official.RootObject;
import com.fifa.service.CommonService;
import com.fifa.util.Input;
import com.fifa.util.Output;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController
@RequestMapping("/live/match")
public class LiveMatchController {

	private static final String EVENT_URL = "https://api.fifa.com/api/v1/live/football/17/254645/275073/";
	
//	String matchId = "300331506";  		//"300331527";
	
	@Autowired
	private CommonService commonService;
	
	@PostMapping()
	public ResponseEntity<?> getLiveMatchDetails(@RequestBody Input input) throws IOException{
		Output out = new Output();
		String matchIdFifaServer = input.getMatchId();
		
		String eventUrl = getEventUrl(matchIdFifaServer);
		
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
		  .url(eventUrl).get().build();
		Response response = client.newCall(request).execute();
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
    	RootObject event = mapper.readValue(response.body().string(), RootObject.class);
		
    	int matchStatus = event.getMatchStatus();
    	switch(matchStatus) {
	    	case 0: {
	    		out.setData("matchStatus", "Full Time");
	    		break;
	    	}
	    	case 1 : {
	    		out.setData("matchStatus", "Match is yet to start");
				break;
	    	}
	    	case 3 : {
	    		out.setData("matchStatus", "Match live");
				break;
	    	}
	    	case 4: {
	    		out.setData("matchStatus", "Match abandoned");
				break;
	    	}
	    	case 7: {
	    		out.setData("matchStatus", "Match postponed");
				break;
	    	}
	    	case 8: {
	    		out.setData("matchStatus", "Match cancelled");
				break;
	    	}
	    	case 12: {
	    		out.setData("matchStatus", "Stay tuned! Lineups in progress.");
				break;
	    	}
	    	default: {
	    		out.setData("matchStatus", "Stay tuned!");
				break;
	    	}
    	}
    	
    	int period = event.getPeriod();
    	switch(period) {
	    	case 4: {
	    		out.setData("period", "Half Time");
	    		break;
	    	}
	    	case 6 : {
	    		out.setData("period", "Extra time");
	    		break;
	    	}
	    	case 8: {
	    		out.setData("period", "Extra half time");
	    		break;
	    	}
	    	case 11: {
	    		out.setData("period", "Penalty shootouts");
	    		break;
	    	}
	    	case 10: {
	    		out.setData("period", "Full Time");
	    		break;
	    	}
	    	default: {
	    		out.setData("period", "Stay tuned!");
				break;
	    	}
    	}
    	
    	HomeTeam homeTeam = event.getHomeTeam();
    	AwayTeam awayTeam = event.getAwayTeam();
    	
    	EventTeam team1 = new EventTeam(homeTeam, event);
    	EventTeam team2 = new EventTeam(awayTeam, event);
    	
    	out.setData("attendance", event.getAttendance());
    	out.setData("winner", commonService.getWinnerTeam(event));
    	out.setData("team1", team1);
    	out.setData("team2", team2);
    	
    	out.setResponseCode(ResponseCode.OK);
    	
		return ResponseEntity.ok(out);
	}
	
	private String getEventUrl(String matchIdFifaServer){
		return EVENT_URL + matchIdFifaServer + "?language=en-GB"; 
	}
	
	
}