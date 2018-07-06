package com.fifa.official;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fifa.util.MultiDateDeserializer;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Data{
	private String Text;
	
	private String Title;
	
	private String Slug;
	
	private String ThumbnailTitle;
	
	private String CssClass;
	
	@JsonProperty("TranslatedEventName")
	private String TranslatedEventName;
	
	@JsonProperty("EventTime")
	private String EventTime;
	
	@JsonDeserialize(using = MultiDateDeserializer.class)
	@JsonProperty("EventDateUTC")
	private Date EventDateUTC;

	public String getText() {
		return Text;
	}

	public void setText(String text) {
		Text = text;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getSlug() {
		return Slug;
	}

	public void setSlug(String slug) {
		Slug = slug;
	}

	public String getThumbnailTitle() {
		return ThumbnailTitle;
	}

	public void setThumbnailTitle(String thumbnailTitle) {
		ThumbnailTitle = thumbnailTitle;
	}

	public String getCssClass() {
		return CssClass;
	}

	public void setCssClass(String cssClass) {
		CssClass = cssClass;
	}

	public String getTranslatedEventName() {
		return TranslatedEventName;
	}

	public void setTranslatedEventName(String translatedEventName) {
		TranslatedEventName = translatedEventName;
	}

	public String getEventTime() {
		return EventTime;
	}

	public void setEventTime(String eventTime) {
		EventTime = eventTime;
	}
	
	public Date getEventDateUTC() {
		return EventDateUTC;
	}
	
	public void setEventDateUTC(Date eventDateUTC) {
		EventDateUTC = eventDateUTC;
	}
}