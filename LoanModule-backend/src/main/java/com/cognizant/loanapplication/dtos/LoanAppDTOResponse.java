package com.cognizant.loanapplication.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class LoanAppDTOResponse {
	@NotBlank(message = "Give the appropriate value")
	private String CustomerAcceptanceStatus;
	@Positive(message = "Reduce Payment value Can't be less than 0")
	private int reducePayment;
}
