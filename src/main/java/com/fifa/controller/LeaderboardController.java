package com.fifa.controller;

import java.io.IOException;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fifa.service.CommonService;
import com.fifa.util.Output;

@RestController
@RequestMapping("/leaderboard")
public class LeaderboardController {
	private static final Logger log = LoggerFactory.getLogger(LeaderboardController.class);
	
	@Autowired
	private CommonService commonService;
	
	@GetMapping()
	@ResponseBody
	@Transactional
	public ResponseEntity<?> getLeaderboard() throws IOException {
		log.info("GET LEADERBOARD METHOD");
		
		Output out = commonService.getLeaderboardUsingStoredProc();

		if (out.isSuccess())
			return ResponseEntity.ok(out);
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(out);
	}
}

