package com.fifa.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fifa.appcode.FifaGroup;
import com.fifa.appcode.ResponseCode;
import com.fifa.model.Team;
import com.fifa.model.repository.TeamRepository;
import com.fifa.util.Output;

@RestController
public class TeamController {
	private static final Logger log = LoggerFactory.getLogger(TeamController.class);

	@Autowired
	private TeamRepository teamRepository;
	
	@GetMapping("/teams")
	@ResponseBody
	public ResponseEntity<?> getAllteamsByGroup() {
		log.info("GET ALL TEAMS BY THEIR GROUPS");
		Output out = new Output();
		
		Iterable<Team> teams = teamRepository.findAll();
		
		List<Team> teamsInGroupA = new ArrayList<>();
		List<Team> teamsInGroupB = new ArrayList<>();
		List<Team> teamsInGroupC = new ArrayList<>();
		List<Team> teamsInGroupD = new ArrayList<>();
		List<Team> teamsInGroupE = new ArrayList<>();
		List<Team> teamsInGroupF = new ArrayList<>();
		List<Team> teamsInGroupG = new ArrayList<>();
		List<Team> teamsInGroupH = new ArrayList<>();
		
		for(Team team : teams) {
			FifaGroup group = team.getTeamGroup();
			switch(group) {
			case A : 
				teamsInGroupA.add(team);
				break;
			case B : 
				teamsInGroupB.add(team);
				break;
			case C : 
				teamsInGroupC.add(team);
				break;
			case D : 
				teamsInGroupD.add(team);
				break;
			case E:
				teamsInGroupE.add(team);
				break;
			case F :
				teamsInGroupF.add(team);
				break;
			case G : 
				teamsInGroupG.add(team);
				break;
			case H : 
				teamsInGroupH.add(team);
			break;
			}
		}
		
		out.setData("groupA", teamsInGroupA);
		out.setData("groupB", teamsInGroupB);
		out.setData("groupC", teamsInGroupC);
		out.setData("groupD", teamsInGroupD);
		out.setData("groupE", teamsInGroupE);
		out.setData("groupF", teamsInGroupF);
		out.setData("groupG", teamsInGroupG);
		out.setData("groupH", teamsInGroupH);
		
		out.setResponseCode(ResponseCode.OK);
		
		if (out.isSuccess())
			return ResponseEntity.ok(out);
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(out);
	}
}
