package com.fifa.service.impl;

import org.springframework.stereotype.Service;

import com.fifa.appcode.FifaTeam;
import com.fifa.official.AwayTeam;
import com.fifa.official.HomeTeam;
import com.fifa.official.RootObject;
import com.fifa.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService {
	
	@Override
	public FifaTeam getWinnerTeam(RootObject event) {
		String winner = event.getWinner();
		HomeTeam homeTeam = event.getHomeTeam();
		AwayTeam awayTeam = event.getAwayTeam();
		
		String idHomeTeam = homeTeam.getIdTeam();
		String idAwayTeam = awayTeam.getIdTeam();
		
		String winnerTeamCode;
		if(idHomeTeam.equals(winner))
			winnerTeamCode = homeTeam.getIdCountry();
		else if(idAwayTeam.equals(winner))
			winnerTeamCode = awayTeam.getIdCountry();
		else winnerTeamCode = "DRW";
		
		for(FifaTeam ft : FifaTeam.values())
			if(ft.getCode().equals(winnerTeamCode))
				return ft;
		
		return null;
	}
}
