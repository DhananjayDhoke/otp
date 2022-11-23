package com.in.services;

import java.util.List;

import com.in.entity.UserDetails;

public interface Totp {

	
	public UserDetails createUser(UserDetails userDetails);
	public List<UserDetails> getAllUsers();
	public String genrateOtp(String secretKey);
	public String valid(String otp);
	
}
