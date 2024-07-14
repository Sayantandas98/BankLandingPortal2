package com.cognizant.loanapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.loanapplication.dtos.UserDTO;
import com.cognizant.loanapplication.dtos.UserRequest;
import com.cognizant.loanapplication.service.UserService;


/**
*@author Sayantan Das 
* This class represents authentication controller for LoanApplication 
**/
@RestController
@RequestMapping("/authenticate")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
	@Autowired
	private UserService userService;
	
	
	/* checking weather given user name or password is matching with the database data or not */
	@PostMapping("users")
	public ResponseEntity<UserDTO> authenticate(@RequestBody UserRequest userRequest){
		UserDTO userDTO=userService.authenticateUser(userRequest.getUserName(), userRequest.getPassword());
		if(userDTO.getUserName()!=null) {
			return new ResponseEntity<UserDTO>(userDTO,HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(userDTO,HttpStatus.FORBIDDEN);
		}
		
	}

}
