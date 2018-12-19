package com.fifa.controller;

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
import com.fifa.model.BetResult;
import com.fifa.model.User;
import com.fifa.model.repository.BetResultRepository;
import com.fifa.service.CommonService;
import com.fifa.util.Output;

@RestController
@RequestMapping("/result")
public class ResultController {
	private static final Logger log = LoggerFactory.getLogger(ResultController.class);
	
	@Autowired
	private BetResultRepository resultRepository;

	@Autowired
	private CommonService commonService;
	
	@GetMapping()
	@ResponseBody
	public ResponseEntity<?> getResult() {
		log.info("GET INDIVIDUAL RESULT");
		Output out = new Output();
		
		User user = commonService.getLoggerInUser();
		Iterable<BetResult> result = resultRepository.findByUserId(user.getUserId());
		
		out.setData("result", result);
		out.setResponseCode(ResponseCode.OK);
		
		if (out.isSuccess())
			return ResponseEntity.ok(out);
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(out);
	}
}
