package com.cognizant.loanapplication.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class LoanAppDTO {
	@NotEmpty(message="Loan Application Id can't be null or blank, please put value")
	private String loanAppId;
	@Min(value = 1,message = "age must be greater than equal to 1")
	private int age;
	@Min(value=1,message="No of Years must be greater than 0")
	private long noOfYears;
	@Positive(message = "Salary can't be less than 0")
	private long salary;
}
