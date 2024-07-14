package com.cognizant.loanapplication.dtos;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class LoanStatusDTO {
	@Pattern(regexp="^(pass|fail|pending)$",message="Please put pass or fail or pending")
	private String basicCheck;
	@Positive(message = "Credit score can't be negetive")
	private int creditScore;
	@NotEmpty(message = "Invalid Input")
	private String finalcheck;
}
