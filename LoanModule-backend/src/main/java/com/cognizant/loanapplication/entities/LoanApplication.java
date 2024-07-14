package com.cognizant.loanapplication.entities;

import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name="Loan_Application")
@ToString
public class LoanApplication {
	@Id
	@Column(name ="loan_appid")
	private String loanAppId;
	@Column(name = "cust_id")
	private String custId;
	@Column(name = "loan_amt")
	private long loanAmt;
	@Column(name = "no_of_years")
	private long noOfYears;
	@Column(name = "purpose")
	private String purpose;
	@Column(name = "app_status")
	private String appStatus;
	@Column(name = "type_of_loan")
	private String typeOfLoan;
	@Column(name = "loan_app_date")
	private LocalDate loanAppDate;
	@Column(name = "status")
	private String status;
	@OneToOne(mappedBy = "loanApplication")
	private CreditRisk creditRisk;
}
