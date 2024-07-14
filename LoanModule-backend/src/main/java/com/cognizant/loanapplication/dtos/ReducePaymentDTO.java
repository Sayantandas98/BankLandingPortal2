package com.cognizant.loanapplication.dtos;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ReducePaymentDTO {
	@NotEmpty(message="Value should not be null or empty")
	private String loanappid;
	@Positive(message = "Give positive Value")
	private int monthNo;
	@Positive(message = "Value can't be negetive")
	private double installment;
	@Positive(message = "Value can't be negetive")
	private double interest;
	@Positive(message="Provide positive value")
	private double pOutStandingBeginOfMon;
	@Positive(message="Provide positive value")
	private double pRepayment;
	@Positive(message="Provide positive value")
	private double pOutStandingEndOfMon;


}
