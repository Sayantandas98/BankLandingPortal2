package com.cognizant.loanapplication.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.loanapplication.dtos.EmiClacDTO;
import com.cognizant.loanapplication.dtos.LoanAppDTO;
import com.cognizant.loanapplication.dtos.LoanAppDTOResponse;
import com.cognizant.loanapplication.dtos.LoanApplicationDTO;
import com.cognizant.loanapplication.dtos.LoanDTO;
import com.cognizant.loanapplication.dtos.LoanStatusDTO;
import com.cognizant.loanapplication.dtos.ReducePaymentDTO;
import com.cognizant.loanapplication.entities.CreditRisk;
import com.cognizant.loanapplication.entities.LoanApplication;
import com.cognizant.loanapplication.exception.ResourceNotFoundException;
import com.cognizant.loanapplication.modelmapper.ModelMap;
import com.cognizant.loanapplication.repositories.CreditRiskRepository;
import com.cognizant.loanapplication.repositories.LoanApplicationRepository;
import com.cognizant.loanapplication.service.LoanApplicationService;



/**
*@author Sayantan Das 
* This class represents all the business logics for LoanApplication 
**/
@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepo;
	
	@Autowired
	private CreditRiskRepository creditRiskRepository;
	
	@Autowired
	private ModelMap modelmapper;
	
	
	
	/* Fetch LoanApplication Records based on LocalDate */
	@Override
	public List<LoanApplicationDTO> loanApplicationFindByDate(LocalDate date) {
		List<LoanApplication> loanApplications = this.loanApplicationRepo.findAllByLoanAppDate(date);
		List<LoanApplicationDTO> loanApplicationDTOs =new ArrayList<LoanApplicationDTO>();
		if(!loanApplications.isEmpty()) {
			for (LoanApplication i : loanApplications) {
				loanApplicationDTOs.add(modelmapper.entityToDto(i));
			}
			return loanApplicationDTOs;
		}
		else {
			throw new ResourceNotFoundException("LoanApplications", "date",date);
		}
	}
	
	
	/* Update LoanApplication Table Base On LoanApplication ID */ 
	@Override
	public LoanApplicationDTO loanApplicationUpdateById(LoanApplicationDTO loanApplicationDto, String loanAppId) {
		LoanApplication loanApplication=loanApplicationRepo.findById(loanAppId).orElseThrow(()->new ResourceNotFoundException("LoanApplication","id",loanAppId));
			loanApplication.setLoanAppId(loanApplicationDto.getLoanAppId());
			loanApplication.setCustId(loanApplicationDto.getCustId());
			loanApplication.setLoanAmt(loanApplicationDto.getLoanAmt());
			loanApplication.setNoOfYears(loanApplicationDto.getNoOfYears());
			loanApplication.setPurpose(loanApplicationDto.getPurpose());
			loanApplication.setAppStatus(loanApplicationDto.getAppStatus());
			loanApplication.setTypeOfLoan(loanApplicationDto.getTypeOfLoan());
			loanApplication.setLoanAppDate(loanApplicationDto.getLoanAppDate());
			loanApplication.setStatus(loanApplicationDto.getStatus());
			LoanApplication loanApplication1=this.loanApplicationRepo.save(loanApplication);
			return this.modelmapper.entityToDto(loanApplication1);
	}
	
	
	/* Fetch Loan Application Based on LoanApplication Id */
	@Override
	public LoanApplicationDTO loanApplicationFindById(String loanAppId) {
		LoanApplication loanApplication=loanApplicationRepo.findById(loanAppId).orElseThrow(()->new ResourceNotFoundException("LoanApplication","id",loanAppId));
		return this.modelmapper.entityToDto(loanApplication);
	}
	
	
	/* Perform Basic Check for a loanApplication and put the result in CredikRisk table */
	@Override
	public LoanStatusDTO performBasicCheck(LoanAppDTO loanAppDto) {
		CreditRisk creditRisk=new CreditRisk();
		LoanStatusDTO loanStatusDto=new LoanStatusDTO();
		LoanApplication loanApplication=loanApplicationRepo.findById(loanAppDto.getLoanAppId()).orElseThrow(()->new ResourceNotFoundException("LoanApplication","id",loanAppDto.getLoanAppId()));
		if(loanAppDto.getAge()+loanAppDto.getNoOfYears()<=60) {
			creditRisk.setBasicCheck("pass");
			creditRisk.setLoanApplication(loanApplication);
			creditRisk.setCreditScore(650);
			creditRisk.setEmi(274627);
			creditRisk.setCrId("CR"+loanAppDto.getLoanAppId());
			CreditRisk creditRisk1=creditRiskRepository.save(creditRisk);
			loanStatusDto.setBasicCheck(creditRisk1.getBasicCheck());
			loanStatusDto.setCreditScore(creditRisk1.getCreditScore());
			loanStatusDto.setFinalcheck("accepted");
			return loanStatusDto;
		}
		else {
			creditRisk.setBasicCheck("fail");
			creditRisk.setLoanApplication(loanApplication);
			creditRisk.setCreditScore(650);
			creditRisk.setEmi(0);
			creditRisk.setCrId("CR"+loanAppDto.getLoanAppId());
			CreditRisk creditRisk1=creditRiskRepository.save(creditRisk);
			loanStatusDto.setBasicCheck(creditRisk1.getBasicCheck());
			loanStatusDto.setCreditScore(creditRisk1.getCreditScore());
			loanStatusDto.setFinalcheck("rejected");
			return loanStatusDto;
		}
	}
	
	
	/* Calculate EMI for a LoanApplication details will provide through LoanDTO from Outside */
	@Override
	public EmiClacDTO calculateEmi(LoanDTO loan) {
		double principalAmt = loan.getPrincipleAmount();
		int loanTenure = loan.getLoanTenureMonths();
 
		double interest = (loan.getMonthlyinterestRate() / 12 / 100);
 
		double emi = principalAmt * interest * Math.pow((1 + interest), loanTenure)/ (Math.pow((1 + interest), loanTenure) - 1);
		double totalAmount = emi * loanTenure;
		EmiClacDTO emiClacDto=new EmiClacDTO();
		emiClacDto.setEmi(Math.round(emi));
		emiClacDto.setTotalAmountPayable(Math.round(totalAmount));
		return emiClacDto;
	}
	
	
	/* Final check based on the LoanApplication */
	@Override
	public LoanStatusDTO fetchCreditScore(LoanAppDTO loanAppDto) {
		CreditRisk creditRisk=new CreditRisk();
		LoanStatusDTO loanStatusDto=new LoanStatusDTO();
		LoanApplication loanApplication=loanApplicationRepo.findById(loanAppDto.getLoanAppId()).orElseThrow(()->new ResourceNotFoundException("LoanApplication","id",loanAppDto.getLoanAppId()));
		if(loanAppDto.getAge()+loanAppDto.getNoOfYears()<=60) {
			creditRisk.setBasicCheck("pass");
			creditRisk.setLoanApplication(loanApplication);
			creditRisk.setCreditScore(650);
			creditRisk.setEmi(274627);
			creditRisk.setCrId("CR"+loanAppDto.getLoanAppId());
			CreditRisk creditRisk1=creditRiskRepository.save(creditRisk);
			loanStatusDto.setBasicCheck(creditRisk1.getBasicCheck());
			loanStatusDto.setCreditScore(creditRisk1.getCreditScore());
			loanStatusDto.setFinalcheck("accepted");
			return loanStatusDto;
		}
		else {
			creditRisk.setBasicCheck("fail");
			creditRisk.setLoanApplication(loanApplication);
			creditRisk.setCreditScore(650);
			creditRisk.setEmi(0);
			creditRisk.setCrId("CR"+loanAppDto.getLoanAppId());
			CreditRisk creditRisk1=creditRiskRepository.save(creditRisk);
			loanStatusDto.setBasicCheck(creditRisk1.getBasicCheck());
			loanStatusDto.setCreditScore(creditRisk1.getCreditScore());
			loanStatusDto.setFinalcheck("rejected");
			return loanStatusDto;
		}
		
	}
	
	
	
	
	/* Check Customer acceptance status weather it is accepted or rejected and based on the 
	 	acceptance show the reduce payment amount to the customer.
	*/
	@Override
	public LoanAppDTOResponse fetchAcceptance(String loanAppId) {
		LoanAppDTOResponse loanAppDto=new LoanAppDTOResponse();
		LoanApplication loanApplication=loanApplicationRepo.findById(loanAppId).orElseThrow(()->new ResourceNotFoundException("user","id",loanAppId));
		if(loanApplication.getAppStatus().equals("accepted")) {
			loanAppDto.setCustomerAcceptanceStatus(loanApplication.getAppStatus());
			loanAppDto.setReducePayment(5000);
			return loanAppDto;
		}
		else {
			loanAppDto.setCustomerAcceptanceStatus("rejected");
			loanAppDto.setReducePayment(0);
			return loanAppDto;
		}
	}
	
	/* Calculate the reduce payments based on the loan amount */
	@Override
	public List<ReducePaymentDTO> acceptenceReducePayment(String loanAppId) {
		LoanApplication loanApplication=loanApplicationRepo.findById(loanAppId).orElseThrow(()->new ResourceNotFoundException("user","id",loanAppId));
		List<ReducePaymentDTO> reducePaymentDtos =new ArrayList<>();
		double principalAmt = loanApplication.getLoanAmt();
		double pOutStandingEndOfMon=loanApplication.getLoanAmt();
		int loanTenure = (int)loanApplication.getNoOfYears()*12;
		double monthlyInterestRate=10;
		double interestRateMonthly = (monthlyInterestRate/ 12 / 100);
		int interest=(int)Math.ceil(principalAmt*interestRateMonthly);
		double emi =Math.round( principalAmt * interestRateMonthly * Math.pow((1 + interestRateMonthly), loanTenure)/ (Math.pow((1 + interestRateMonthly), loanTenure) - 1));
		for(int i=1;i<=loanTenure;i++) {
			ReducePaymentDTO reducePaymentDto=new ReducePaymentDTO();
			int interest1=(int)Math.ceil(principalAmt*interestRateMonthly);
			double principleRepayment=(emi-interest1);
			reducePaymentDto.setLoanappid(loanAppId);
			reducePaymentDto.setMonthNo(i);
			reducePaymentDto.setInstallment(Math.ceil(emi));
			reducePaymentDto.setInterest(interest1);
			reducePaymentDto.setPOutStandingBeginOfMon(principalAmt);
			reducePaymentDto.setPRepayment(principleRepayment);
			reducePaymentDto.setPOutStandingEndOfMon(principalAmt-principleRepayment);
			reducePaymentDtos.add(reducePaymentDto);
			pOutStandingEndOfMon=principalAmt-principleRepayment;
			principalAmt=pOutStandingEndOfMon;
		}
		return reducePaymentDtos;
	}
	
	
	/* Get all Loan Application from database  */
	@Override
	public List<LoanApplicationDTO> getAllLoanApplications(){
		List<LoanApplicationDTO> loanApplicationDTOs =new ArrayList<LoanApplicationDTO>();
		List<LoanApplication> loanApplications=loanApplicationRepo.findAll();
		for (LoanApplication i : loanApplications) {
			loanApplicationDTOs.add(modelmapper.entityToDto(i));
		}
		return loanApplicationDTOs;
	}



}
