package com.fifa.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fifa.appcode.ResponseCode;
import com.fifa.model.Rating;
import com.fifa.model.User;
import com.fifa.model.repository.RatingRepository;
import com.fifa.model.repository.UserRepository;
import com.fifa.service.CommonService;
import com.fifa.util.Input;
import com.fifa.util.Output;

@RestController
@RequestMapping("/rate")
public class RatingController {
	private static final Logger log = LoggerFactory.getLogger(RatingController.class);
	private static final String TOTAL_VOTES = "totalVotes";
	private static final String RATING = "rating";
	
	@Autowired
	private RatingRepository ratingRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CommonService commonService;
	
	@GetMapping()
	public ResponseEntity<?> getRatings() throws IOException{
		log.info("FETCH ALL COMMENTS AND RATINGS");
		Output out = new Output();
		
		Iterable<Rating> ratings = ratingRepository.findAll();
		Double rate = 0d;
		int total = 0;
		
		if(ratings == null) {
			out.setData(RATING, 0);
			out.setData(TOTAL_VOTES, 0);
			return ResponseEntity.ok(out);
		}
		
		for(Rating rating : ratings) {
			
			total += 1;
			rate += rating.getRateApp();
			
			BigInteger userId = rating.getUserId();
			Optional<User> ouser = userRepository.findById(userId);
		
			if(ouser.isPresent()) {
				User user = ouser.get();
				String name = user.getName();
				rating.setName(name);
			}

			String profilePic = commonService.getCompressedImage(userId, 8);
			rating.setProfilePic(profilePic);
			
			String comment = rating.getComment();
			if(comment != null) {
				comment = comment.trim();
				rating.setComment(comment);
			}
		}
		
		Double currentRating = 0d;
		if(total != 0)
			currentRating = rate/total;
		
		out.setData(RATING, ratings);
		out.setData("currentRating", currentRating);
		out.setData(TOTAL_VOTES, total);
		
		out.setResponseCode(ResponseCode.OK);
		
		if (out.isSuccess())
			return ResponseEntity.ok(out);
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(out);
	}
	
	@PostMapping()
	@ResponseBody
	public ResponseEntity<?> rate(@RequestBody Input input){
		log.info("RATE THE APP");
		Output out = new Output();
		User user = commonService.getLoggerInUser();
		BigInteger userId = user.getUserId();
		
		Rating rate = ratingRepository.findByUserId(userId);
		if(rate != null) {
			rate.setRateApp(input.getRateApp());
			rate.setComment(input.getComment());
		}
		else {
			rate = new Rating();
			rate.setUserId(userId);
			rate.setRateApp(input.getRateApp());
			rate.setComment(input.getComment());
		}
		ratingRepository.save(rate);
		
		Iterable<Rating> ratings = ratingRepository.findAll();
		if(ratings == null) {
			out.setData(RATING, 0);
			out.setData(TOTAL_VOTES, 0);
			return ResponseEntity.ok(out);
		}
		
		Double r = 0d;
		int total = 0;
		for(Rating rating : ratings) {
			total += 1;
			r += rating.getRateApp();
		}
		Double currentRating = 0d;
		if(total != 0)
			currentRating = r/total;
		
		out.setData(RATING, currentRating);
		out.setData(TOTAL_VOTES, total);
		
		out.setResponseCode(ResponseCode.OK);
		
		if (out.isSuccess())
			return ResponseEntity.ok(out);
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(out);
	}
	
}