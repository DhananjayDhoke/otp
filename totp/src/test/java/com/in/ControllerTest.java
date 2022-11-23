package com.in;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.in.controller.TotpController;
import com.in.entity.UserDetails;
import com.in.serviceImpl.TotpImpl;


@SpringBootTest
class ControllerTest {

//	controller usage service
	
	@Autowired
	private TotpController controller;
	
	
	@MockBean
	private TotpImpl service;
	
	
	@Test
	void createUser1() {
		UserDetails user = new UserDetails(1, "lrs", "email", "mobNo.");
		when(service.createUser(user)).thenReturn(user);
		assertEquals(user, controller.createUser(user).getBody());
	}
	
	@Test
	void createUser2() {
		UserDetails user = new UserDetails(1, "lrs", "email", "mobNo.");
		when(service.createUser(user)).thenReturn(user);
		assertEquals(HttpStatus.OK, controller.createUser(user).getStatusCode());
	}

	
	@Test
	void getAllUsers1() {
		
		List<UserDetails> list = getUserList();
		when(service.getAllUsers()).thenReturn(list);
		assertEquals(list, controller.GetAllUsers().getBody());
	}
	
	
	@Test
	void getAllUsers2() {
		
		List<UserDetails> list = getUserList();
		when(service.getAllUsers()).thenReturn(list);
		assertEquals(HttpStatus.OK, controller.GetAllUsers().getStatusCode());
	}
	
	List<UserDetails> getUserList(){
		UserDetails user1 = new UserDetails(1, "lrs", "email", "mobNo.");
		UserDetails user2 = new UserDetails(1, "lrs", "email", "mobNo.");
		UserDetails user3 = new UserDetails(1, "lrs", "email", "mobNo.");
		UserDetails user4 = new UserDetails(1, "lrs", "email", "mobNo.");
		List<UserDetails> list = new ArrayList<>();
		list.add(user1);
		list.add(user2);
		list.add(user3);
		list.add(user4);
		
		return list;
	}

}
