package com.cognizant.loanapplication.dtos;

import java.time.LocalDate;

import com.cognizant.loanapplication.entities.CreditRisk;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Data
public class LoanApplicationDTO {
	@NotEmpty(message="Loan Application Id can't be null or Empty, please put value")
	private String loanAppId;
	@NotEmpty(message="Customer Id can't be null or Empty, please put value")
	private String custId;
	@Min(value=10000,message="loan Amount must be minimum 10000")
	private long loanAmt;
	@Min(value=1,message="No of Years must be greater than 0")
	private long noOfYears;
	@NotEmpty(message="purpose can't be null or blank, please put value")
	private String purpose;
	@NotEmpty(message="Application Status can't be null or blank, please put value")
	private String appStatus;
	@NotEmpty(message="Type of Loan can't be null or blank, please put value")
	private String typeOfLoan;
	@NotNull(message="Loan Application Date can't be null or blank, please put value")
	private LocalDate loanAppDate;
	@NotEmpty(message="status can't be null or blank, please put value")
	private String status;
	private CreditRisk creditRisk;
}
