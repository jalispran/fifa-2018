package com.fifa.service;

import java.io.IOException;

import com.fifa.util.Output;

public interface LiveBlogService {
	String getApiKey() throws IOException;

	Output refresh(String postsUrl, String apiKey) throws IOException;
}
