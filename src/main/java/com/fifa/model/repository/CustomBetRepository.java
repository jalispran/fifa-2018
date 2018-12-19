package com.fifa.model.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fifa.model.CustomBet;
import java.util.Date;
import java.util.List;

@Repository
public interface CustomBetRepository extends CrudRepository<CustomBet, BigInteger> {
	List<CustomBet> findByValidityUptoIsAfter(Date validityupto);
	
	List<CustomBet> findByValidityUptoIsBetween(Date validityFrom, Date validityUpto);
	
	List<CustomBet> findByBetResultCodeNotNull();
	
}



