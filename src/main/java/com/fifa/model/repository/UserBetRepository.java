package com.fifa.model.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fifa.model.UserBet;

@Repository
public interface UserBetRepository extends CrudRepository<UserBet, BigInteger> {
 
	UserBet findByUserIdAndBetId(BigInteger userId, BigInteger betId);
	
	List<UserBet> findByMatchId(BigInteger matchId);
	
	List<UserBet> findByUserId(BigInteger userId, Pageable pageable);
	
	List<UserBet> findByUserIdAndUserBetStatusIsNotNull(BigInteger userId);
	
	UserBet findByUserIdAndMatchId(BigInteger userid, BigInteger matchId);
}
