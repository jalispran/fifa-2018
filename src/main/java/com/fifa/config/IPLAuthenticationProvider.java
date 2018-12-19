package com.fifa.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fifa.appcode.Authority;
import com.fifa.model.User;
import com.fifa.model.repository.UserRepository;

public class IPLAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String mobile = authentication.getName();
		Object credentials = authentication.getCredentials();

		if(!(credentials instanceof String))
			return null;

		String password = credentials.toString();
		User user = userRepository.findByMobileNo(mobile);
		if(user == null)
			throw new BadCredentialsException("Invalid mobile number");
		
		if(!user.getPassword().equals(password))
			throw new BadCredentialsException("Invalid credentials");
		
		List<SimpleGrantedAuthority> list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority(String.valueOf(Authority.EMPLOYEE)));
		
		return new UsernamePasswordAuthenticationToken(mobile, password, list);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
