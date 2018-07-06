package com.fifa.official;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fifa.util.MultiDateDeserializer;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Item {

	private String selfUrl;
	
	private int id; 
	
	private String title;
	
	private String description; 
	
	private String icon;
	
	@JsonDeserialize(using = MultiDateDeserializer.class)
	private Date date; 
	
	private Date scheduleDate;
	
	private String status;
	
	private int totalPosts;
	
	private String postsUrl; 
	
//	private String author;
	
	private int templateId;
	
	private List<Tag> tags;
	
	private List<Part> parts;

	public String getSelfUrl() {
		return selfUrl;
	}

	public void setSelfUrl(String selfUrl) {
		this.selfUrl = selfUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTotalPosts() {
		return totalPosts;
	}

	public void setTotalPosts(int totalPosts) {
		this.totalPosts = totalPosts;
	}

	public String getPostsUrl() {
		return postsUrl;
	}

	public void setPostsUrl(String postsUrl) {
		this.postsUrl = postsUrl;
	}

//	public String getAuthor() {
//		return author;
//	}
//
//	public void setAuthor(String author) {
//		this.author = author;
//	}

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	public List<Part> getParts() {
		return parts;
	}
	
	public void setParts(List<Part> parts) {
		this.parts = parts;
	}
	
}

class Tag{
	String name;
	String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
