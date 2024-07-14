package com.cognizant.loanapplication.dtos;

import com.cognizant.loanapplication.entities.LoanApplication;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class CreditRiskDTO {
	@NotBlank(message = "Customer ID Should not be null or Blank")
	private String crId;
	@Size(min=200,max=700,message="Credit Score must be in between 200-700")
	private int creditScore;
	@Positive(message="value must be in positive")
	private int emi;
	@Pattern(regexp="^(pass|fail|pending)$",message="Please put pass or fail or pending")
	private String basicCheck;
	private LoanApplication loanApplication;
}
