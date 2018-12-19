package com.fifa.model.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fifa.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, BigInteger> {
	
	User findByMobileNo(String mobileno);

}
