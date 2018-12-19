package com.fifa.model.repository;
import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fifa.model.BetResult;

@Repository
public interface BetResultRepository extends CrudRepository<BetResult, BigInteger> {

	Iterable<BetResult> findByUserId(BigInteger userId);
}