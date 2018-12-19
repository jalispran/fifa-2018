package com.fifa.model.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fifa.model.TeamTrivia;

@Repository
public interface TeamTriviaRepository extends CrudRepository<TeamTrivia, BigInteger> {

}
