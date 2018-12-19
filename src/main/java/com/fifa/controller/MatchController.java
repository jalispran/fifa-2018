package com.fifa.controller;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fifa.appcode.ResponseCode;
import com.fifa.model.FifaMatch;
import com.fifa.model.Team;
import com.fifa.model.repository.FifaMatchesRepository;
import com.fifa.model.repository.TeamRepository;
import com.fifa.service.CommonService;
import com.fifa.util.Output;

@RestController
@RequestMapping("/matches")
public class MatchController {
	private static final Logger log = LoggerFactory.getLogger(MatchController.class);

	@Autowired
	private FifaMatchesRepository matchRepository;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private CommonService commonService;
	
	@GetMapping()
	@ResponseBody
	public ResponseEntity<?> getMatchesForToday() throws ParseException {
		log.info("GET MATCHES FOR TODAY");
		Output out = new Output();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date today = sdf.parse("15/06/2018");
		Calendar c = Calendar.getInstance();
		c.setTime(today); // Now use today date.
		c.add(Calendar.DATE, 1); // Adding 1 day
		Date tomorrow = c.getTime();
		
		List<FifaMatch> todaysMatchList = matchRepository.findByMatchDateTimeBetween(today, tomorrow);
		for(FifaMatch m : todaysMatchList) {
			BigInteger team1Id = m.getTeam1Id();
			System.out.println("TEAM 1 ID : " + team1Id);
			Optional<Team> team = teamRepository.findById(team1Id);
			if(team.isPresent()) {
				Team team1 = team.get();
				BigInteger team1FlagId = team1.getFlagBlobId();
				String flag1 = commonService.getProfilePic(team1FlagId);
				m.setTeam1Logo(flag1);
			}
			
			BigInteger team2Id = m.getTeam2Id();
			Optional<Team> t2 =  teamRepository.findById(team2Id);
			if(team.isPresent()) {
				Team team2 = t2.get();
				BigInteger team2FlagId = team2.getFlagBlobId();
				String flag2 = commonService.getProfilePic(team2FlagId);
				m.setTeam2Logo(flag2);
			}
		}
		out.setData("todaysMatches", todaysMatchList);
		out.setResponseCode(ResponseCode.OK);
		
		if (out.isSuccess())
			return ResponseEntity.ok(out);
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(out);
	}

	@GetMapping("/all")
	@ResponseBody
	public ResponseEntity<?> getAllMatches() {
		log.info("GET ALL MATCHES");
		
		Iterable<FifaMatch> matchList = matchRepository.findAll();
		
		Output out = new Output();
		out.setData("allMatches", matchList);
		out.setResponseCode(ResponseCode.OK);
		
		if (out.isSuccess())
			return ResponseEntity.ok(out);
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(out);
	}
}
