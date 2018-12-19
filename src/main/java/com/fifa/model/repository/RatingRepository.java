package com.fifa.model.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;

import com.fifa.model.Rating;

public interface RatingRepository extends CrudRepository<Rating, BigInteger> {
	Rating findByUserId(BigInteger userid);
}
