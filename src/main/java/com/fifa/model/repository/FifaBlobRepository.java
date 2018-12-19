package com.fifa.model.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fifa.appcode.FifaBlobType;
import com.fifa.model.FifaBlob;

@Repository
public interface FifaBlobRepository extends CrudRepository<FifaBlob, BigInteger> {
	
	@Override
	@Cacheable(cacheNames = "matchesCache")
	default Optional<com.fifa.model.FifaBlob> findById(BigInteger id) {
		System.out.println("IN THE CACHED METHOD");
		return findByBlobId(id);
	}

	/*
	 * Dont use this method
	 */
	Optional<FifaBlob> findByBlobId(BigInteger blobid);
	
	
	@Cacheable(cacheNames = "matchesCache")
	FifaBlob findByTeamCodeAndType(String teamCode, FifaBlobType type);
}
