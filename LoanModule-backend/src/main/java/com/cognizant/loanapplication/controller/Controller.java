package com.cognizant.loanapplication.controller;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.loanapplication.dtos.EmiClacDTO;
import com.cognizant.loanapplication.dtos.LoanAppDTO;
import com.cognizant.loanapplication.dtos.LoanAppDTOResponse;
import com.cognizant.loanapplication.dtos.LoanApplicationDTO;
import com.cognizant.loanapplication.dtos.LoanDTO;
import com.cognizant.loanapplication.dtos.LoanStatusDTO;
import com.cognizant.loanapplication.dtos.ReducePaymentDTO;
import com.cognizant.loanapplication.entities.LoanApplication;
import com.cognizant.loanapplication.service.LoanApplicationService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
*@author Sayantan Das 
* This class represents rest API endpoints for LoanApplication Resource 
**/
@Tag(name="LoanApplication",description = "LoanApplication Rest API")
@OpenAPIDefinition(info=@Info(title="LoanApplication Rest APIs"))
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/loanapps")
public class Controller {
	
	@Autowired
	LoanApplicationService loanServiceImpl;

	
	
	/*
	  pull all loan application by specific date 
	 */
	@GetMapping("/pull/{date}")
	public ResponseEntity<List<LoanApplicationDTO>> findApplicationByDate(@Valid @PathVariable("date") LocalDate dt){
		List<LoanApplicationDTO> loanApplicationDto=loanServiceImpl.loanApplicationFindByDate(dt);
		if(loanApplicationDto.isEmpty()) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(loanApplicationDto,HttpStatus.OK);
	}
	
	
	
	/* update loan application by loan application id */
	@PutMapping("/update/{id}")
	public ResponseEntity<LoanApplicationDTO> updateById(@Valid @RequestBody LoanApplicationDTO loanApplicationDto, @PathVariable("id") String id){
		LoanApplicationDTO loanApplicationDto1=loanServiceImpl.loanApplicationUpdateById(loanApplicationDto, id);
		return new ResponseEntity<>(loanApplicationDto1,HttpStatus.OK);
	}
	
	
	/* get loan application by loan application id */
	@GetMapping("/view/{loan_app_id}")
	public ResponseEntity<LoanApplicationDTO> getById(@Valid @PathVariable("loan_app_id") String loan_app_id ){
		return ResponseEntity.ok(this.loanServiceImpl.loanApplicationFindById(loan_app_id));
	}
	
	/* update basic check in credit risk table */
	@PutMapping("/basicCheck")
	public ResponseEntity<LoanStatusDTO> updateBasicCheck(@RequestBody LoanAppDTO loanAppDto){
		return new ResponseEntity<>(this.loanServiceImpl.performBasicCheck(loanAppDto),HttpStatus.OK);
	}
	
	
	
	
	/* calculate EMI based on given data */
	@PostMapping("/checkEmi")
	public ResponseEntity<EmiClacDTO> claculateEmi(@RequestBody LoanDTO loanDto){
		EmiClacDTO emiClacDto=this.loanServiceImpl.calculateEmi(loanDto);
		if(emiClacDto.getEmi()!=0)
			return new ResponseEntity<>(emiClacDto,HttpStatus.OK);
		else
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	}
	
	
	/* update credit score in credit risk table */
	@PutMapping("/creditscore")
	public ResponseEntity<LoanStatusDTO> getAllCreditScore(@Valid @RequestBody LoanAppDTO loanAppDto){
		LoanStatusDTO loanStatusDtos=this.loanServiceImpl.fetchCreditScore(loanAppDto);
		return new ResponseEntity<>(loanStatusDtos,HttpStatus.OK);
	}
	
	/*  check customer acceptance status through loan application id */
	@GetMapping("/checkCustomerAcceptanceStatus/{loanAppID}")
	public ResponseEntity<LoanAppDTOResponse> checkCustomerAcceptanceStatus(@PathVariable("loanAppID") String loanAppID){
		LoanAppDTOResponse loanAppDtoResponse=this.loanServiceImpl.fetchAcceptance(loanAppID);
		return new ResponseEntity<>(loanAppDtoResponse,HttpStatus.OK);
	}
	
	
	/* sanction loan application by loan application id */
	@GetMapping("sanctionAmount/{loanAppID}")
	public ResponseEntity<List<ReducePaymentDTO>> sanctionLoanAmount(@PathVariable("loanAppID") String loanAppID ){
		List<ReducePaymentDTO> reducePaymentDto=this.loanServiceImpl.acceptenceReducePayment(loanAppID);
		return new ResponseEntity<>(reducePaymentDto,HttpStatus.OK);
	}
	
	
	/* get all loan applications */
	@GetMapping("/getall")
	public ResponseEntity<List<LoanApplicationDTO>> getAllLoanApplication(){
		List<LoanApplicationDTO> getAll=this.loanServiceImpl.getAllLoanApplications();
		if(getAll.isEmpty())
			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<LoanApplicationDTO>>(getAll,HttpStatus.OK);
	}
	
}
