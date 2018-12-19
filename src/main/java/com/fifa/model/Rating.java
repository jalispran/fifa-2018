package com.fifa.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name= "rating")
public class Rating {

	@Id
	private BigInteger userId;
	
	@Column(name = "app")
	private Double rateApp;
	
	private String comment;
	
	/**
	 * Transient field
	 */
	@Transient
	private String name;
	
	/**
	 * Transient field
	 */
	@Transient
	private String profilePic;

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public Double getRateApp() {
		return rateApp;
	}

	public void setRateApp(Double rateApp) {
		this.rateApp = rateApp;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	/**
	 * Transient field
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Transient field
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * transient field
	 * @return
	 */
	public String getProfilePic() {
		return profilePic;
	}
	
	/**
	 * transient field
	 * @param profliPic
	 */
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	@Override
	public String toString() {
		return "Rating [userId=" + userId + ", rateApp=" + rateApp + ", comment=" + comment + "]";
	}
	
}
