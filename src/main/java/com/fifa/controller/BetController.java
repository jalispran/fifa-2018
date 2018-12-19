package com.fifa.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fifa.appcode.FifaBlobType;
import com.fifa.appcode.FifaTeam;
import com.fifa.appcode.ResponseCode;
import com.fifa.model.CustomBet;
import com.fifa.model.FifaBlob;
import com.fifa.model.FifaMatch;
import com.fifa.model.User;
import com.fifa.model.UserBet;
import com.fifa.model.repository.CustomBetRepository;
import com.fifa.model.repository.FifaBlobRepository;
import com.fifa.model.repository.FifaMatchesRepository;
import com.fifa.model.repository.UserBetRepository;
import com.fifa.model.repository.UserRepository;
import com.fifa.service.CommonService;
import com.fifa.util.Input;
import com.fifa.util.Output;

@RestController
@RequestMapping("/bets")
public class BetController {
	private static final Logger log = LoggerFactory.getLogger(BetController.class);
	private static final int PROFILE_PIC_COMPRESSION_FACTOR = 4;
	
	@Autowired
	private CustomBetRepository customBetRepository;
	
	@Autowired
	private UserBetRepository userBetRepository;
	
	@Autowired
	private FifaMatchesRepository matchRepository;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FifaBlobRepository blobRepository;
	
	@GetMapping()
	@ResponseBody
	public ResponseEntity<?> getActiveBets() {
		log.info("GET ALL BETS THAT ARE ACTIVE");
		
		List<CustomBet> betsList = customBetRepository.findByValidityUptoIsAfter(new Date());
		
		Output out = new Output();
		out.setData("activeBets", betsList);
		out.setResponseCode(ResponseCode.OK);
		
		if (out.isSuccess())
			return ResponseEntity.ok(out);
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(out);
	}

	@SuppressWarnings("deprecation")
	@PostMapping()
	@ResponseBody
	public ResponseEntity<?> placeBet(@RequestBody Input input) {
		log.info("PLACE A BET");
		Output out = new Output();
		
		User user = commonService.getLoggerInUser();
		
		BigInteger betId = input.getBetId();
		String betCode = input.getTeamName();
		if(betCode == null) {				//	validate betCode
			out.setMessage("Please provide teamName");
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(out);
		}
		
//		validate betId
		Optional<CustomBet> c = customBetRepository.findById(betId);
		CustomBet customBet = null;
		if(!c.isPresent()) {
			out.setMessage("Invalid betId");
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(out);
		}
		else {				//	valid betId
			customBet = c.get();
			BigInteger matchIdFromDb = customBet.getMatchId();
			Date matchTime = customBet.getValidityUpto();
			
			int year = matchTime.getYear() + 1900;
			int month = matchTime.getMonth() + 1;
			int hours = matchTime.getHours();
			int minutes = matchTime.getMinutes();
			
			hours -= 5;
			minutes -= 30;
			
			System.out.println("DATE TIME FROM DATABASE : " + matchTime);
			
			LocalDateTime matchStartsAt = LocalDateTime.of(year, month, matchTime.getDate(), hours, minutes);
			System.out.println("CONVERTED DATE TIME (minus " + hours + " hours and " + minutes + " minutes) : " + matchStartsAt);
			
			LocalDateTime now = LocalDateTime.now();
			System.out.println("REQUEST RECEIVED AT : " + now);
			
			if(matchStartsAt.isBefore(now)) {
				out.setMessage("Time up. Bet can not be placed now");
				return ResponseEntity.ok(out);
			}
			
			if(matchIdFromDb != null) {
//				input.getTeam should return FifaTeam object when matchId is NOT null
				FifaTeam betTeam = null;
				try{
					betTeam = FifaTeam.valueOf(betCode);
					
					if((matchIdFromDb.compareTo(new BigInteger("48")) > 0) && betTeam.equals(FifaTeam.DRAW)) {
						out.setResponseCode(ResponseCode.ERR_INVALID_BET);
						out.setMessage("Knockout Phase. Draw prediction not allowed");
						return ResponseEntity.status(HttpStatus.OK).body(out);
					}
					betCode = betTeam.getCode();
				}
				catch (IllegalArgumentException e) {
					out.setMessage("Invalid teamName");
					return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(out);
				}
				
				Optional<FifaMatch> match = matchRepository.findById(matchIdFromDb);
				if(match.isPresent() && !FifaTeam.DRAW.getCode().equals(betCode)) {
					FifaMatch m = match.get();
					if(!(m.getTeam1Code().equals(betCode) || m.getTeam2Code().equals(betCode))) {
						out.setMessage(betTeam + " is not involved in this bet");
						return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(out);
					}
				}
			}
		}

//		handle duplication
		UserBet userBet = userBetRepository.findByUserIdAndBetId(user.getUserId(), betId);
		if(userBet != null)
			userBet.setBetCode(betCode);
		else {
			userBet = new UserBet();
			userBet.setBetId(betId);
			userBet.setMatchId(customBet.getMatchId());
			userBet.setUserId(user.getUserId());
			userBet.setBetCode(betCode);
		}
		userBetRepository.save(userBet);
		
		out.setResponseCode(ResponseCode.OK);
		
		if (out.isSuccess())
			return ResponseEntity.ok(out);
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(out);
	}
	
	@GetMapping("/fetch")
	@ResponseBody
	public ResponseEntity<?> fetchBets() {
		log.info("FETCH ALL BETS PLACEED BY THE USER");
		
		Output out = commonService.getUserBets();
		
		if (out.isSuccess())
			return ResponseEntity.ok(out);
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(out);
	}
	
	@GetMapping("/checkbets")
	@ResponseBody
	public ResponseEntity<?> checkBets() {
		log.info("CHECK BETS");
		Output out = new Output();
		
		Calendar c = Calendar.getInstance();
		Date today = c.getTime();
		c.add(Calendar.DATE, 1);
		Date tomorrow = c.getTime();

		List<ClassTwo> checkBetList = new ArrayList<>();
		
		List<FifaMatch> todaysMatchList = matchRepository.findByMatchDateTimeBetween(today, tomorrow);
		
		for(FifaMatch f : todaysMatchList) {
			BigInteger matchId = f.getFifaMatchId();
			List<UserBet> userBets = userBetRepository.findByMatchId(matchId);
			if(userBets == null) {
				out.setMessage("Come On! Be the first.");
				return ResponseEntity.ok(out);
			}
			for(UserBet b : userBets) {
				BigInteger userId = b.getUserId();
				User user = new User();
				Optional<User> ouser = userRepository.findById(userId);
				if(ouser.isPresent())
					user = ouser.get();
				else {
					out.setMessage("Something went worng");
					return ResponseEntity.ok(out);
				}
				
				String proPic = commonService.getProfilePic(user.getUserId());
				
				user.setProfilePic(proPic);
				checkBetList.add(new ClassTwo(user, f, b));
			}
		}
		
		out.setData("checkBets", checkBetList);
		out.setResponseCode(ResponseCode.OK);
		
		if (out.isSuccess())
			return ResponseEntity.ok(out);
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(out);
	}
	
	
	@GetMapping("/checkbets/{matchId}")
	@ResponseBody
	public ResponseEntity<?> checkMatchSpecificBets(@PathVariable BigInteger matchId) throws IOException {
		log.info("CHECK BETS USING MATCH ID");
		Output out = new Output();
		
		List<ClassTwo> checkBetList = new ArrayList<>();
		
		Optional<FifaMatch> omatch = matchRepository.findById(matchId);
		FifaMatch match = null;
		if(!omatch.isPresent()) {
			out.setMessage("Invalid matchId");
			return ResponseEntity.ok(out);
		}
		
		match = omatch.get();
		
		FifaTeam team1Name = null;
		String team1Code = match.getTeam1Code();
		for(FifaTeam fifaTeam : FifaTeam.values())
			if(fifaTeam.getCode().equals(team1Code))
				team1Name = fifaTeam;
		
		match.setTeam1Name(team1Name);
		
		FifaTeam team2Name = null;
		String team2Code = match.getTeam2Code();
		for(FifaTeam fifaTeam : FifaTeam.values())
			if(fifaTeam.getCode().equals(team2Code))
				team2Name = fifaTeam;
		
		match.setTeam2Name(team2Name);
		
		List<UserBet> userBets = userBetRepository.findByMatchId(matchId);
		if(userBets == null) {
			out.setMessage("Come On! Be the first.");
			return ResponseEntity.ok(out);
		}
		for(UserBet b : userBets) {
			BigInteger userId = b.getUserId();
			User user = null;
			Optional<User> ouser = userRepository.findById(userId);
			if(ouser.isPresent())
				user = ouser.get();
			else {
				out.setMessage("Something went worng");
				return ResponseEntity.ok(out);
			}
			
			String proPic = commonService.getCompressedImage(user.getUserId(), PROFILE_PIC_COMPRESSION_FACTOR);
			
			user.setProfilePic(proPic);
			checkBetList.add(new ClassTwo(user, match, b));
		}
		
		out.setData("team1Name", match.getTeam1Name());
		out.setData("team1Code", match.getTeam1Code());
		out.setData("team2Name", match.getTeam2Name());
		out.setData("team2Code", match.getTeam2Code());
		
		Long time = System.currentTimeMillis();
		long a = time % 2;
		FifaBlob blob;
		if(a == 0)
			blob = blobRepository.findByTeamCodeAndType(match.getTeam1Code(), FifaBlobType.BACKGROUND_IMAGE);
		else blob = blobRepository.findByTeamCodeAndType(match.getTeam2Code(), FifaBlobType.BACKGROUND_IMAGE);
		
		if(blob == null)
			if(a == 0)
				blob = blobRepository.findByTeamCodeAndType(match.getTeam1Code(), FifaBlobType.FLAG);
			else blob = blobRepository.findByTeamCodeAndType(match.getTeam2Code(), FifaBlobType.FLAG);
		
		String backgroundImage = commonService.getImageFromBlobId(blob.getBlobId());
		out.setData("bg", backgroundImage);
		
		out.setData("checkBets", checkBetList);
		out.setResponseCode(ResponseCode.OK);
		
		if (out.isSuccess())
			return ResponseEntity.ok(out);
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(out);
	}
}

class ClassTwo {
	User user;
	FifaMatch match;
	UserBet bet;
	
	public ClassTwo(User user, FifaMatch match, UserBet bet) {
		super();
		this.user = user;
		this.match = match;
		this.bet = bet;
	}
	
	public String getName() {
		return this.user.getName();
	}
	
	public String getProfilePic() {
		return this.user.getProfilePic();
	}
	
	public String getUserBet() {
		String betCode = this.bet.getBetCode();
		for(FifaTeam f : FifaTeam.values()) 
			if(f.getCode().equals(betCode))
				return String.valueOf(f);
		return "NA";
	}
	
	public String getUserBetStatus() {
		return String.valueOf(this.bet.getUserBetStatus());
	}
	
}