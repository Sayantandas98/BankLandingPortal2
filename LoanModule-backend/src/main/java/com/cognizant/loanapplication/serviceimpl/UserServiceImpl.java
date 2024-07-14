package com.cognizant.loanapplication.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.loanapplication.dtos.UserDTO;
import com.cognizant.loanapplication.entities.Users;
import com.cognizant.loanapplication.repositories.UserRepository;
import com.cognizant.loanapplication.service.UserService;



/**
*@author Sayantan Das 
* This class represents authentication logic for LoanApplication 
**/
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	/* checking weather given user name or password is matching with the database data or not */
	@Override
	public UserDTO authenticateUser(String username, String password) {
		List<Users> users= (List<Users>)userRepository.findAll();
		UserDTO userModel=new UserDTO();
		for(Users user:users) {
			if(user.getUserName().equals(username) && user.getPassword().equals(password) && !user.isAccountLocked()) {
				userModel.setUserName(user.getUserName());
				userModel.setPassword(user.getPassword());
				userModel.setRole(user.getRole());
				userModel.setAccountLocked(user.isAccountLocked());
				break;
			}
		}
		return userModel;
	}

}
