package com.cognizant.loanmanagement.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.catalina.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cognizant.loanapplication.dtos.UserDTO;
import com.cognizant.loanapplication.dtos.UserRequest;
import com.cognizant.loanapplication.entities.Users;
import com.cognizant.loanapplication.repositories.UserRepository;
import com.cognizant.loanapplication.serviceimpl.UserServiceImpl;



public class UserServiceTest {
	
	
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void test_authenticateUserPositive() {
		String username="sayan";
		String pass="sayan123";
		List<Users> users=new ArrayList<>();
		Users user=new Users();
		user.setUserName("sayan");
		user.setPassword("sayan123");
		user.setRole("admin");
		user.setAccountLocked(false);
		users.add(user);
		when(userRepository.findAll()).thenReturn(users);
		UserDTO userModel=userServiceImpl.authenticateUser(username, pass);
		assertTrue(userModel!=null);
		
	}
	
	@Test
	public void test_authenticateUserNegetive() {
		String username="sayan1";
		String pass="sayan1237";
		List<Users> users=new ArrayList<>();  
		when(userRepository.findAll()).thenReturn(users);
		UserDTO userModel=userServiceImpl.authenticateUser(username, pass);
		assertTrue(userModel.getUserName()==null);
		
	}
}
