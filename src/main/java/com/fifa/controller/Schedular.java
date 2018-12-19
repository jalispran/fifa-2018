package com.fifa.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fifa.appcode.FifaTeam;
import com.fifa.model.FifaMatch;
import com.fifa.model.MatchIdMapper;
import com.fifa.model.repository.FifaMatchesRepository;
import com.fifa.model.repository.MatchIdMapperRepository;
import com.fifa.official.AwayTeam;
import com.fifa.official.Goal;
import com.fifa.official.HomeTeam;
import com.fifa.official.MatchListUrlResponse;
import com.fifa.official.RootObject;
import com.fifa.service.CommonService;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Component
public class Schedular {
	private static final Logger log = LoggerFactory.getLogger(Schedular.class);
	private static final String CURRENT_MATCH_LIST_URL = "https://api.fifa.com/api/v1/live/football/recent/17/254645?language=en-GB";
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private MatchIdMapperRepository matchMapperRepository;
	
	@Autowired
	private EntityManagerFactory entityManagerFactory; 
	
	@Autowired
	private FifaMatchesRepository matchRepository;
	
	@SuppressWarnings({ "deprecation", "rawtypes" })
	@Scheduled(fixedRate = 7200000)
	@Transactional
	public void checkForMatchConclusions() throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
		  .url(CURRENT_MATCH_LIST_URL).get().build();

		Response response = client.newCall(request).execute();
		String resp = response.body().string();
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
		MatchListUrlResponse event = mapper.readValue(resp, MatchListUrlResponse.class);
		
		List<RootObject> results = event.getResults();
		for(RootObject result : results) {
			int matchStatus = result.getMatchStatus();
			int period = result.getPeriod();
			
			if(!(matchStatus == 0 && period == 10)) {
				log.info("MATCH STATUS IS NOT FULL TIME YET. WAITING...");
				continue;				//wait untill match status is "full time"
			}
			
			FifaTeam winnerTeam = commonService.getWinnerTeam(result);
			String matchIdFifaServer = result.getIdMatch();
			
			MatchIdMapper matchIdMapper = matchMapperRepository.findByMatchIdFifaServer(matchIdFifaServer);
			if(matchIdMapper == null)	return;
			
			BigInteger matchId = matchIdMapper.getFifaMatchId();
			
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			Session session = entityManager.unwrap(Session.class);
			
			Query query = session.createSQLQuery("CALL p_upd_userBetStatus("+matchId+",'"+winnerTeam.getCode()+"')");
			entityManager.joinTransaction();
			query.executeUpdate();
			
//			find match result
			HomeTeam homeTeam = result.getHomeTeam();
			AwayTeam awayTeam = result.getAwayTeam();
			
			String homeTeamCode = homeTeam.getIdCountry();
			String awayTeamCode = awayTeam.getIdCountry();
			
			ArrayList<Goal> homeTeamGoals = homeTeam.getGoals();
			ArrayList<Goal> awayTeamGoals = awayTeam.getGoals();
			
			Optional<FifaMatch> omatch = matchRepository.findById(matchId);
			if(!omatch.isPresent())
				return;
			
			FifaMatch match = omatch.get();
			match.setResult(homeTeamCode + " " + homeTeamGoals + " - " + awayTeamGoals + " " + awayTeamCode);
			matchRepository.save(match);
		}
		
	}
}
