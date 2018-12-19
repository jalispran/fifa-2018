package com.fifa.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

@NamedNativeQueries({ 
	  @NamedNativeQuery(
	    name = "leaderboardSummary", 
	    query = "CALL p_leaderBoard_summary()", 
	    resultClass = LeaderboardModel.class) 
	})

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LeaderboardModel {

	@Id
	private BigInteger userId;
	
	@Column(name = "Won")
	private Long won;
	
	@Column(name = "Lost")
	private Long loss;
	
	@Column(name = "Draw")
	private Long draw;
	
	@Column(name = "Points")
	private Long points;

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public Long getWon() {
		return won;
	}

	public void setWon(Long won) {
		this.won = won;
	}

	public Long getLoss() {
		return loss;
	}

	public void setLoss(Long loss) {
		this.loss = loss;
	}

	public Long getDraw() {
		return draw;
	}

	public void setDraw(Long draw) {
		this.draw = draw;
	}

	public Long getPoints() {
		return points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "LeaderboardModel [userId=" + userId + ", won=" + won + ", loss=" + loss + ", draw=" + draw + ", points="
				+ points + "]";
	}

}
