package com.fifa.model.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;

import com.fifa.model.MatchIdMapper;

public interface MatchIdMapperRepository extends CrudRepository<MatchIdMapper, BigInteger>{

	MatchIdMapper findByFifaMatchId(BigInteger fifamatchid);
	
	MatchIdMapper findByMatchIdFifaServer(String matchIdFifaServer);
}
