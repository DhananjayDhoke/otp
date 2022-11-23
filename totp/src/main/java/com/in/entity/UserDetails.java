package com.in.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;
	
	@Email(message = "Email is not valid")
	private String email;
	
	@Size(min=10,max=10,message="mobile number should be 10 charecter")
    @Pattern(regexp ="(0/91)?[7-9][0-9]{9}",message="Enter valid Mobile Number")
	private String mobileNumber;
	
}
