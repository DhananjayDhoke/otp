package com.in;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.in.entity.UserDetails;
import com.in.repository.UserRepo;
import com.in.serviceImpl.TotpImpl;


@SpringBootTest
class ServiceTest {
	
//	totpImpl usage userRepo
	
	@Autowired
	private TotpImpl totpImpl;
	
	@MockBean
	private UserRepo userRepo;
	
	@Test
	void getall() {
		when(userRepo
				.findAll())
				.thenReturn(Stream.of(
				new UserDetails(1, "lrs", "email", "mobNo."),
				new UserDetails(2, "abc", "ABC", "123"))
				.collect(Collectors.toList()));
		
		assertEquals(2, totpImpl.getAllUsers().size());
	}
	
	@Test
	void newUser() {
		UserDetails user = new UserDetails(1, "lrs", "email", "mobNo.");
		when(userRepo.save(user)).thenReturn(user);
		assertEquals(user, totpImpl.createUser(user));
	}

}
