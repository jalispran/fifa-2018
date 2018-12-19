package com.fifa.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fifa.appcode.ResponseCode;
import com.fifa.model.Rating;
import com.fifa.model.Team;
import com.fifa.model.repository.RatingRepository;
import com.fifa.model.repository.TeamRepository;
import com.fifa.service.CommonService;
import com.fifa.util.Output;

@RestController
public class TriviaController {
	private static final Logger log = LoggerFactory.getLogger(TriviaController.class);

	@Value("${fifa.production-url:http://192.168.1.40:8102/}")
	private String productionUrl;
	
	@Value("${fifa.update-url}")
	private String updateUrl;
	
	@Value("${fifa.api-version-no}")
	private String versionNo;
	
	@Value("${fifa.tournament-winner:null}")
	private String winner;
	
	@Value("${fifa.tournament-winner-pic:null}")
	private String fifaWinnerPic;
	
	@Value("${fifa.tournament-winner-code:null}")
	private String fifaWinnerTeamCode;
	
	@Value("${fifa.neha-user-id:null}")
	private String nehaUserId;
	
	@Value("${fifa.pranjal-user-id:null}")
	private String pranjalUserId;
	
	@Value("${fifa.diwakar-sir-user-id:null}")
	private String diwakarSirUserId;
	
	
	@Autowired
	private RatingRepository ratingRepository;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	TeamRepository teamRepository;
	
	@GetMapping()
	@ResponseBody
	public ResponseEntity<?> getTrivia() throws IOException {
		log.info("GET TRIVIA AND CURRENT URL");
		Output out = new Output();
		
		String trivia = commonService.getTrivia();
		out.setData("trivia", trivia);
		
		out.setData("productionUrl", productionUrl);
		out.setData("versionNo", versionNo);
		out.setData("updateUrl", updateUrl);
		out.setData("fifaWinner", winner);
		out.setData("fifaWinnerCode", fifaWinnerTeamCode);
		out.setData("fifaWinnerPic", fifaWinnerPic);
		
//		find current rating
		Iterable<Rating> ratings = ratingRepository.findAll();
		Double currentRating = 0d;
		int total = 0;
		if(ratings == null) {
			out.setData("currentRating", 0);
			out.setData("numberOfVotes", 0);
		}
		else {
			Double rate = 0d;
			for(Rating rating : ratings) {
				total += 1;
				rate += rating.getRateApp();
			}
			if(total != 0)
				currentRating = rate/total;
		}
		
		BigInteger nehaId = null; 
		BigInteger pranjalId = null;
		BigInteger diwakarSirId = null;
		
		if(!nehaUserId.equals("null"))
			nehaId = new BigInteger(nehaUserId);
		if(!pranjalUserId.equals("null"))
			pranjalId = new BigInteger(pranjalUserId);
		if(!diwakarSirUserId.equals("null"))
			diwakarSirId = new BigInteger(diwakarSirUserId);
		
		String nehaProfilePic = commonService.getProfilePic(nehaId);
		String pranjalProfilePic = commonService.getProfilePic(pranjalId);
		String diwakarSirProfilePic = commonService.getProfilePic(diwakarSirId);
		
		out.setData("about", new About(nehaProfilePic, pranjalProfilePic, diwakarSirProfilePic, currentRating, total));
		
		out.setResponseCode(ResponseCode.OK);
		
		if (out.isSuccess())
			return ResponseEntity.ok(out);
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(out);
	}
	
	@GetMapping("/teamlogo")
	@ResponseBody
	public ResponseEntity<?> getTeamLogo() throws IOException {
		log.info("GET TEAM LOGO");
		Output out = new Output();
		
		Iterable<Team> teams = teamRepository.findAll();
		for(Team team : teams) {
			String teamCode = team.getTeamCode().getCode();
			System.out.println("@JsonProperty(\""+teamCode+"\")");
			System.out.println("private String " + teamCode+";");
		}
		
		out.setResponseCode(ResponseCode.OK);
		
		if (out.isSuccess())
			return ResponseEntity.ok(out);
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(out);
	}
}

class About{
	private String diwakarSirPic;
	private String pranjalPic;
	private String nehaPic;
	private String currentRating;
	private Integer numberOfVotes;
	
	public About(String nehaPic, String pranjalPic, String diwakarSirPic, Double currentRating, Integer numberOfVotes) {
		this.nehaPic = nehaPic;
		this.pranjalPic = pranjalPic;
		this.diwakarSirPic = diwakarSirPic;
		DecimalFormat df = new DecimalFormat("#.#");
		this.currentRating = df.format(currentRating);
		this.numberOfVotes = numberOfVotes;
	}
	
	public String getCurrentRating() {
		return currentRating;
	}

	public Integer getNumberOfVotes() {
		return numberOfVotes;
	}

	public String getPranjalPic() {
		return pranjalPic;
	}
	public String getNehaPic() {
		return nehaPic;
	}
	
	/*key-name is as instructed by Neha More*/
	public String getDiwakarPic() {
		return diwakarSirPic;
	}
	
	@Override
	public String toString() {
		return "About [pranjalPic=" + pranjalPic + ", nehaPic=" + nehaPic + "]";
	}
}