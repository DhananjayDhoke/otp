package com.in.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in.entity.UserDetails;
import com.in.exeption.InvalidOTP;
import com.in.exeption.InvalidUser;
import com.in.exeption.OTPexpired;
import com.in.repository.UserRepo;
import com.in.services.Totp;

import de.taimos.totp.TOTP;


@Service
@Transactional
public class TotpImpl implements Totp{

	private long validtill;
	private String OTP;
	private String EMAIL;
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public String genrateOtp(String secretKey) {
		
		UserDetails userDetails = userRepo.findByEmail(secretKey);
		if(userDetails == null )
			throw new InvalidUser("please insert a valid email addreass");
		
		long random = System.currentTimeMillis();
		validtill = random + 30000;
		Base32 base32 = new Base32();
	    byte[] bytes = base32.decode(secretKey+random);
	    String hexKey = Hex.encodeHexString(bytes);
	    OTP = TOTP.getOTP(hexKey);
	    EMAIL = secretKey;
	    return OTP;
	}

	@Override
	public String valid(String otp) throws InvalidOTP,OTPexpired {
		
		String valid = isValid(otp);
		
		if(valid.equals("INV_OTP")) {
			throw new InvalidOTP("please insert a valid OTP");
		}
		
		else if(valid.equals("OTP_EXP")) {
			throw new OTPexpired("OTP expired,please genrate a new OTP");
		}
		
		return "valid otp";
		
	}
	
	
	@Override
	public UserDetails createUser(UserDetails userDetails){
		try {
			return userRepo.save(userDetails);
		}
		catch (Exception e) {
			throw new InvalidUser("please provide a valid data");
		}
	}
	
	@Override
	public List<UserDetails> getAllUsers() {
		return userRepo.findAll();
	}

	public String isValid(String otp){
		if(!otp.equals(OTP)) {
			return "INV_OTP";
		}
		else if(System.currentTimeMillis() > validtill){
			return "OTP_EXP";
		}
		
		return "";
	}

	

	
}