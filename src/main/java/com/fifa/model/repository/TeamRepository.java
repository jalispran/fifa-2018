package com.fifa.model.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fifa.model.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team, BigInteger> {

}
