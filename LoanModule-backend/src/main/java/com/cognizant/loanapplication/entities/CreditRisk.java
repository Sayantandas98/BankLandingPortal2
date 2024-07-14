package com.cognizant.loanapplication.entities;



import com.fasterxml.jackson.annotation.JsonIgnore;

//import com.cognizant.loanManagement.utility.EnumBasicCheck;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "Credit_Risk")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CreditRisk {
	@Id
	@Column(name = "cr_id")
	@NotEmpty
	private String crId;
	@Column(name="credit_score")
	private int creditScore;
	@Column(name = "emi")
	private int emi;
	@Pattern(regexp="^(pass|fail|pending)$",message="Please put pass or fail or pending")
	private String basicCheck;
	
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="loan_appid")
	private LoanApplication loanApplication;
	
}
