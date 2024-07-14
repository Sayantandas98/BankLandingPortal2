package com.cognizant.loanapplication.exception;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
	String resourceName;
	String fieldName;
	String fieldValue;
	LocalDate date;
	public ResourceNotFoundException(String resourceName, String fieldName, String loanAppId) {
		super(String.format("%s not found with %s : %s",resourceName,fieldName,loanAppId));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = loanAppId;
	}
	public ResourceNotFoundException(String resourceName, String fieldName, LocalDate date) {
		super(String.format("%s not found with %s : %s",resourceName,fieldName,date));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.date = date;
	}
	
}
