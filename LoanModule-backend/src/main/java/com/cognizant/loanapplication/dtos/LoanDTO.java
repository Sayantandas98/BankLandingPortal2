package com.cognizant.loanapplication.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class LoanDTO {
	@Positive(message = "Value must be positive")
	private double principleAmount;
	@Positive(message = "Value must be positive")
	private double monthlyinterestRate;
	@Positive(message = "Value must be positive")
	private int loanTenureMonths;
	@NotEmpty(message = "Due Date Id can't be null or Empty")
	private LocalDate dueDate;
}
