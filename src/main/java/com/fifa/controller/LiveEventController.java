package com.fifa.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fifa.official.Item;
import com.fifa.official.LiveBloggingObject;
import com.fifa.service.LiveBlogService;
import com.fifa.util.Input;
import com.fifa.util.Output;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController
@RequestMapping("/live/event")
public class LiveEventController {
	private static final Logger log = LoggerFactory.getLogger(LiveEventController.class);
	private static final String LIVE_BLOGGING_URL = "https://livebloggingdistributionapi.fifa.com/api/v1/FIFA FORGE/en-GB/blogs?tag.IdMatch=";

//	private String matchIdFifaServer = "300331506"; //"300331527";
	
	@Autowired
	private LiveBlogService liveBlogService;
	
	@PostMapping()
	@ResponseBody
	public ResponseEntity<?> getLiveEvents(@RequestBody Input input	) throws IOException {
		log.info("LIVE BLOGGING URL");
		Output out = new Output();
		
		String apikey = liveBlogService.getApiKey();
		out.setData("apiKey", apikey);
		
		String matchIdFifaServer = input.getMatchId();
		
		String url = getLiveBloggingUrl(matchIdFifaServer);
		
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url(url).get()
		  .addHeader("authorization", "LiveBlogging key=" + apikey)
		  .build();

		Response response = client.newCall(request).execute();
		ObjectMapper mapper = new ObjectMapper();
    	LiveBloggingObject blog = mapper.readValue(response.body().string(), LiveBloggingObject.class);
		
    	List<Item> items = blog.getItems();
    	String postsUrl = null;
    	for(Item item : items) {
    		postsUrl = item.getPostsUrl();
    		if(postsUrl != null) break;
    	}
    	
    	out = liveBlogService.refresh(postsUrl, apikey);
    	
		if (out.isSuccess())
			return ResponseEntity.ok(out);
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(blog);
	}

	private String getLiveBloggingUrl(String matchIdFifaServer){
		return LIVE_BLOGGING_URL + matchIdFifaServer;
	}
	
}
