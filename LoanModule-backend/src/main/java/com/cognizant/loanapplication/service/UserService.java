package com.cognizant.loanapplication.service;

import java.util.List;

import com.cognizant.loanapplication.dtos.UserDTO;
import com.cognizant.loanapplication.entities.Users;


public interface UserService {
	
	public UserDTO authenticateUser(String username,String password);

}
