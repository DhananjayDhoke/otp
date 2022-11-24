package com.in.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponce {
   
   private String statusCode;
   private String traceID;
   private String otp;
   private String message;
   
public UserResponce(String otp) {
	super();
	this.otp = otp;
}


   
   
   
}
