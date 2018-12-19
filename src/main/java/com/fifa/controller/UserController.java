package com.fifa.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fifa.appcode.FifaBlobType;
import com.fifa.appcode.ResponseCode;
import com.fifa.appcode.UserBetStatus;
import com.fifa.model.FifaBlob;
import com.fifa.model.FifaMatch;
import com.fifa.model.User;
import com.fifa.model.UserBet;
import com.fifa.model.repository.FifaBlobRepository;
import com.fifa.model.repository.FifaMatchesRepository;
import com.fifa.model.repository.UserBetRepository;
import com.fifa.model.repository.UserRepository;
import com.fifa.service.CommonService;
import com.fifa.util.Input;
import com.fifa.util.Output;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

@RestController
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Value("${fifa.tournament-winner:null}")
	private String winner;
	
	@Value("${fifa.tournament-winner-pic:null}")
	private String fifaWinnerPic;
	
	@Value("${fifa.tournament-winner-code:null}")
	private String fifaWinnerCode;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FifaBlobRepository blobRepository;

	@Autowired
	private FifaMatchesRepository matchRepository;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private UserBetRepository userBetRepository;
	
	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<?> registrationMethod(@RequestBody Input input) {
		log.info("REGISTRATION METHOD");
		Output out = new Output();
		
		String mobileNo = input.getMobileNo();
		String name = input.getName().toUpperCase();
		String password = input.getPassword();
		String proPic = input.getProPic();

		FifaBlob blob = new FifaBlob();
		if(proPic != null) {
			byte[] data = javax.xml.bind.DatatypeConverter.parseBase64Binary(proPic);
			blob.setType(FifaBlobType.IMAGE);
			blob.setData(data);
			blobRepository.save(blob);
		}
		User user = userRepository.findByMobileNo(mobileNo);
		if(user == null) {			//new user
			Assert.notNull(name, "provide name please");
			Assert.notNull(password, "provide password please");

			user = new User();
			user.setMobileNo(mobileNo);
			user.setName(name);
			user.setPassword(password);
			user.setProPicId(blob.getBlobId());
			userRepository.save(user);
		}
		else {		//update user details
			out.setMessage("User already registered");
			out.setData("username", user.getName());
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(out);
		}
		
		out.setResponseCode(ResponseCode.OK);
		
		if (out.isSuccess())
			return ResponseEntity.ok(out);
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(out);
	}
	
	@GetMapping("/login")
	@ResponseBody
	public ResponseEntity<?> login() throws ParseException, IOException {
		log.info("\t---LOGIN METHOD---");
		Output out = new Output();
		
//		set user details in login response
		User user = commonService.getLoggerInUser();
		String profilePic = commonService.getProfilePic(user.getUserId());
		user.setProfilePic(profilePic);
		out.setData("userInfo", user);
		
//		set all matches in login response
		Iterable<FifaMatch> matchList = matchRepository.findAll();
		
		for(FifaMatch match : matchList) {
			BigInteger matchId = match.getFifaMatchId();
			BigInteger userId = user.getUserId();
			UserBet bet = userBetRepository.findByUserIdAndMatchId(userId, matchId);
			
			if(bet != null) {
				match.setBetTeamCode(bet.getBetCode());
				match.setUserBetStatus(bet.getUserBetStatus());
			}
			else if(match.getResult() != null)match.setUserBetStatus(UserBetStatus.NOBET);
		}
		
		out.setData("allMatches", matchList);
		
//		set todays's matches in login response
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		Date today = sdf.parse("15/05/2018");
		
		Calendar c = Calendar.getInstance();
		Date today = c.getTime();
		c.setTime(today); // Now use today's date.
		c.add(Calendar.DATE, 1); // Adding 1 days
		
		Date tomorrow = c.getTime();
		
		List<FifaMatch> todaysMatchList = matchRepository.findByMatchDateTimeBetween(today, tomorrow);
		out.setData("todaysMatches", todaysMatchList);
		
//		set historic bets
		Output o = commonService.getUserBets();
		Map<String, Object> dataMap = o.getData();
		out.setData("myPredictions", dataMap.get("myPredictions"));
		
//		get leaderboard
		o = commonService.getLeaderboardUsingStoredProc();
		dataMap = o.getData();
		out.setData("leaderboard", dataMap.get("leaderboard"));
				
//		fifa winner name and jpg
		out.setData("fifaWinner", winner);
		out.setData("fifaWinnerPic", fifaWinnerPic);
		out.setData("fifaWinnerCode", fifaWinnerCode);
		out.setData("title", "2018 FIFA WORLD CUP RUSSIA");
		
		String trivia = commonService.getTrivia();
		out.setData("trivia", trivia);
		
		out.setResponseCode(ResponseCode.OK);
		
		System.out.println("FINDING CACHE MANAGERS");
		List<CacheManager> cacheManagers = CacheManager.ALL_CACHE_MANAGERS;
		for(CacheManager cacheManager : cacheManagers) {
			System.out.println("ALL CACHE MANAGERS : " + cacheManager);
			for(String cacheNames : cacheManager.getCacheNames())
				System.out.println("\t\t CACHE NAMES : " + cacheNames);
			
			Cache matchesCache = cacheManager.getCache("matchesCache");
			System.out.println("MATCHES CACHE SIZE : " + matchesCache.getSize());
		}

		if (out.isSuccess())
			return ResponseEntity.ok(out);
		else{
			out.setData("trivia", trivia);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(out);	
		}
		
	}
	
	@PostMapping("/modify")
	@ResponseBody
	public ResponseEntity<?> modifyDetails(@RequestBody Input input) {
		log.info("MODIFY USER DETAILS");
		Output out = new Output();
		
		String proPic = input.getProPic();
		
		FifaBlob blob = new FifaBlob();
		if(proPic != null) {
			byte[] data = javax.xml.bind.DatatypeConverter.parseBase64Binary(proPic);
			blob.setType(FifaBlobType.IMAGE);
			blob.setData(data);
			blobRepository.save(blob);
		}
		
		User u = commonService.getLoggerInUser();
		Optional<User> ouser = userRepository.findById(u.getUserId());
		if(!ouser.isPresent()) {
			out.setMessage("Something went wrong");
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(out);
		}
		
		User user = ouser.get();
		user.setProPicId(blob.getBlobId());
		userRepository.save(user);
		
		out.setResponseCode(ResponseCode.OK);
		
		if (out.isSuccess())
			return ResponseEntity.ok(out);
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(out);
	}
	
	

	@PostMapping("/getdp")
	@ResponseBody
	public ResponseEntity<?> getProfilePic(@RequestBody Input input) {
		log.info("GET UNCOMPRESSED PROFILE PIC");
		Output out = new Output();
		
		BigInteger userId = input.getUserId();
		String profilePic = commonService.getProfilePic(userId);
		out.setData("profilePic", profilePic);
		
		out.setResponseCode(ResponseCode.OK);
		
		if (out.isSuccess())
			return ResponseEntity.ok(out);
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(out);
	}
}
