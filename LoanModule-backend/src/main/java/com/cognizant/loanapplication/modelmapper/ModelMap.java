package com.cognizant.loanapplication.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.loanapplication.dtos.LoanApplicationDTO;
import com.cognizant.loanapplication.entities.LoanApplication;


@Component
public class ModelMap {
	@Autowired
	private ModelMapper modelMapper;
	
	public LoanApplication dtoToEntity(LoanApplicationDTO loanApplicationDto) {
		LoanApplication loanApplication = this.modelMapper.map(loanApplicationDto, LoanApplication.class);
		return loanApplication;
	}
	
	
	public LoanApplicationDTO entityToDto(LoanApplication loanApplication) {
		LoanApplicationDTO loanApplicationDto = this.modelMapper.map(loanApplication, LoanApplicationDTO.class);
		return loanApplicationDto;
	}

}
