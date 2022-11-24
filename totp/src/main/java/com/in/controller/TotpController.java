package com.in.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.in.entity.UserDetails;
import com.in.entity.UserResponce;
import com.in.exeption.InvalidOTP;
import com.in.exeption.InvalidUser;
import com.in.exeption.OTPexpired;
import com.in.serviceImpl.TotpImpl;

@RestController
@RequestMapping("api/v1.0.1")
public class TotpController {

	@Autowired
	TotpImpl totpImpl;


	@PostMapping("/genrateOtp")
	public ResponseEntity<UserResponce> create(@RequestBody UserDetails userdetails) {
		if(totpImpl.genrateOtp(userdetails.getEmail()) != null) {
			UserResponce responce = new UserResponce("200","124586952",totpImpl.genrateOtp(userdetails.getEmail()),"otp genrated successfully");
			return new ResponseEntity<>(responce, HttpStatus.OK);
		}
		throw new InvalidOTP("please enter a valid email");
	}

	@PostMapping("/validateOtp")
	public ResponseEntity<UserResponce> validate(@RequestBody UserResponce userResponce) {

		try {
			String s=totpImpl.valid(userResponce.getOtp());
			UserResponce responce = new UserResponce("200","124586952",s,"otp validate successfully");
			return new ResponseEntity<>(responce, HttpStatus.OK);

		} catch (InvalidOTP e) {
			throw new InvalidOTP("please insert a valid OTP.");
		} catch (OTPexpired e) {
			throw new OTPexpired("OTP expired,please login again");
		}
	}

	@PostMapping("/createUser")
	public ResponseEntity<UserDetails> createUser(@Valid @RequestBody UserDetails userDetails) {
		try {
			return new ResponseEntity<>(totpImpl.createUser(userDetails), HttpStatus.OK);
		}
		catch (HttpMessageNotReadableException e) {
			throw new InvalidUser(e.getLocalizedMessage());
		}
		catch (Exception e) {
			throw new InvalidUser("please provide a valid user");
		}

	}

	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserDetails>> getAllUsers() {

			List<UserDetails> allUsers = totpImpl.getAllUsers();
			return new ResponseEntity<>(allUsers,HttpStatus.OK);
	
	}

}
