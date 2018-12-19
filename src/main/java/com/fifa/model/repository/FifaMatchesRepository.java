package com.fifa.model.repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fifa.model.FifaMatch;

@Repository
public interface FifaMatchesRepository extends CrudRepository<FifaMatch, BigInteger> {
	
	List<FifaMatch> findByMatchDateTime(Date matchdatetime);
	
	List<FifaMatch> findByMatchDateTimeBetween(Date date1, Date date2);
}
