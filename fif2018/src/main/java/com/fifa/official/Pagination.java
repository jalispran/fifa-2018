package com.fifa.official;

public class Pagination {
	private String nextUrl;
	private String previousUrl = null;
	private int maxItems;

	public String getNextUrl() {
		return nextUrl;
	}

	public String getPreviousUrl() {
		return previousUrl;
	}

	public int getMaxItems() {
		return maxItems;
	}

	public void setNextUrl(String nextUrl) {
		this.nextUrl = nextUrl;
	}

	public void setPreviousUrl(String previousUrl) {
		this.previousUrl = previousUrl;
	}

	public void setMaxItems(int maxItems) {
		this.maxItems = maxItems;
	}
}