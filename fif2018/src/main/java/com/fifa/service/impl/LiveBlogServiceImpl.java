package com.fifa.service.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fifa.appcode.ResponseCode;
import com.fifa.official.Item;
import com.fifa.official.LiveBloggingObject;
import com.fifa.official.Pagination;
import com.fifa.official.Part;
import com.fifa.service.LiveBlogService;
import com.fifa.util.Output;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class LiveBlogServiceImpl implements LiveBlogService{

	private static final String API_URL = "https://www.fifa.com/worldcup/matches/match/300331506/#match-liveblog";
	
	@Override
	public String getApiKey() throws IOException {
		Document doc = Jsoup.connect(API_URL).get();
		String htmlString = String.valueOf(doc);
		
		if(htmlString == null)
			return null;
		
		String[] iKey = htmlString.split("_cfg.liveBlogging.apiKey = 'key=");
		if(iKey.length < 2)
			return null;
		
		String[] jKey = iKey[1].split("';");
		if(jKey.length < 2)
			return null;
		
		return jKey[0];
	}

	@Override
	public Output refresh(String postsUrl, String apiKey) throws IOException {
		System.out.println("POSTS URL : " + postsUrl + "\n");
		Output out = new Output();
		
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url(postsUrl).get()
		  .addHeader("authorization", "LiveBlogging key=" + apiKey)
		  .build();
		Response response = client.newCall(request).execute();
		
		String responseBody = response.body().string();
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
    	LiveBloggingObject blog = mapper.readValue(responseBody, LiveBloggingObject.class);
    	
    	List<TranslatedEvents> translatedEvents = new ArrayList<>(); 
    	List<Item> items = blog.getItems();

    	for(Item item : items) {
    		List<Part> parts = item.getParts();
    		for(Part part : parts) {
    			String datasource = part.getDatasource();
    			if("MatchEvents".equals(datasource)) {
    				String translatedEventName = part.getData().getTranslatedEventName();
    				
    				String eventTime = part.getData().getEventTime();
    				Date eventDate = part.getData().getEventDateUTC();
    				
    				TranslatedEvents te = new TranslatedEvents(translatedEventName, eventDate, eventTime);
    				System.out.println("ONE MORE OBJECT ADDED : " + te);
    				translatedEvents.add(te);
    			} 
    		}
    	}
    	
    	out.setData("events", translatedEvents);
    	
    	Pagination page = blog.getPagination();
    	String nextUrl = page.getNextUrl();
    	if(nextUrl != null) {
    		Output o = refresh(nextUrl, apiKey);
    		Map<String, Object> odata = o.getData();
    		
    		Object events = odata.get("events");
    		
    		String data = mapper.writeValueAsString(events);
    		ArrayList<TranslatedEvents> list = mapper.readValue(data, new TypeReference<ArrayList<TranslatedEvents>>(){});
    		
    		System.out.println(list);
    		
    		if(list.addAll(translatedEvents)) {
    			Collections.sort(list, new Comparator<TranslatedEvents>() {
					@Override
					public int compare(TranslatedEvents o1, TranslatedEvents o2) {
						return o1.getDate().compareTo(o2.getDate());
					}
				});
    			
    			out.setData("events", list);
    			System.out.println("\n\nLIST NOW : ");
    			for(TranslatedEvents te : list) {
    				System.out.print(te + "\n");
    			}
    		}
    	}
    	
    	out.setResponseCode(ResponseCode.OK);
    	return out;
	}
}

class TranslatedEvents {
	private String translatedEventName;
	private String eventDate;
//	@JsonIgnore
	private Date date;
	
	public TranslatedEvents() {	}
	
	public TranslatedEvents(String translatedEventName, Date eventDate, String eventTime) {
		super();
		this.translatedEventName = translatedEventName;
		this.date = eventDate;
		DateFormat formatter = new SimpleDateFormat("hh:mm:ss a");
		formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata")); // Or whatever IST is supposed to be
		this.eventDate = formatter.format(eventDate);
		
	}
	public String getTranslatedEventName() {
		return translatedEventName;
	}
	public void setTranslatedEventName(String translatedEventName) {
		this.translatedEventName = translatedEventName;
	}
	
	public String getEventDate() {
		return eventDate;
	}
	
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	
	public Date getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "translatedEventName=" + translatedEventName + ", Date=" + date ;
	}

}
