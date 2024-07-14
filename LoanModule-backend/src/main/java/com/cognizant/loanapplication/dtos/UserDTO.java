package com.cognizant.loanapplication.dtos;

import lombok.Data;

@Data
public class UserDTO {

	private String userName;
	private String password;
	private String role;
	private boolean isAccountLocked;
	
	
}
