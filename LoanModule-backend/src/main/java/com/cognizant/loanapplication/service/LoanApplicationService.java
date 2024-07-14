package com.cognizant.loanapplication.service;

import java.time.LocalDate;
import java.util.List;


import com.cognizant.loanapplication.dtos.EmiClacDTO;
import com.cognizant.loanapplication.dtos.LoanAppDTO;
import com.cognizant.loanapplication.dtos.LoanAppDTOResponse;
import com.cognizant.loanapplication.dtos.LoanApplicationDTO;
import com.cognizant.loanapplication.dtos.LoanDTO;
import com.cognizant.loanapplication.dtos.LoanStatusDTO;
import com.cognizant.loanapplication.dtos.ReducePaymentDTO;
import com.cognizant.loanapplication.entities.LoanApplication;





public interface LoanApplicationService {
	
	List<LoanApplicationDTO> loanApplicationFindByDate(LocalDate date);
	
	LoanApplicationDTO loanApplicationUpdateById(LoanApplicationDTO loanApplicationDto,String loanAppId);
	
	LoanApplicationDTO loanApplicationFindById(String loanAppId);
	
	LoanStatusDTO performBasicCheck(LoanAppDTO loanAppDto);
	
	EmiClacDTO calculateEmi(LoanDTO loan);
	
	LoanStatusDTO fetchCreditScore(LoanAppDTO loanAppDto);
	
	LoanAppDTOResponse fetchAcceptance(String loanAppId);
	
	List<ReducePaymentDTO>  acceptenceReducePayment(String loanAppId);
	
	List<LoanApplicationDTO> getAllLoanApplications();
}
