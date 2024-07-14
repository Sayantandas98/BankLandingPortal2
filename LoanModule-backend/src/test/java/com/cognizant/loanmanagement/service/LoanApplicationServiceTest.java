package com.cognizant.loanmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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
import com.cognizant.loanapplication.serviceimpl.LoanApplicationServiceImpl;


public class LoanApplicationServiceTest {
	@Mock
    private ModelMap modelmapper;
	
	@Mock
	private LoanApplicationRepository loanAppRepository;
	@Mock
	private LoanApplicationDTO loanApplicationDTO;
	
	@Mock
	private CreditRiskRepository creditRiskRepository;
	@InjectMocks
	private LoanApplicationServiceImpl loanApplicationServiceImpl;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		
	    LoanApplicationDTO loanApplicationDTO=new LoanApplicationDTO();
	    loanApplicationDTO.setLoanAppId("Hello");
		loanApplicationDTO.setCustId("CUHello");
     	loanApplicationDTO.setLoanAmt(4895);
		loanApplicationDTO.setNoOfYears(5);
		loanApplicationDTO.setPurpose("BBB");
		loanApplicationDTO.setAppStatus("accpted");
		loanApplicationDTO.setTypeOfLoan("Home Loan");
		loanApplicationDTO.setLoanAppDate(LocalDate.now());
		loanApplicationDTO.setStatus("fail");
		
		LoanApplication loanApplication=new LoanApplication();
	    loanApplication.setLoanAppId("Hello");
		loanApplication.setCustId("CUHello");
     	loanApplication.setLoanAmt(4895);
		loanApplication.setNoOfYears(5);
		loanApplication.setPurpose("BBB");
		loanApplication.setAppStatus("accepted");
		loanApplication.setTypeOfLoan("Home Loan");
		loanApplication.setLoanAppDate(LocalDate.now());
		loanApplication.setStatus("fail");
		CreditRisk creditRisk=new CreditRisk();
		creditRisk.setCrId("CRL101");
		creditRisk.setEmi(9000);
		creditRisk.setCreditScore(750);
		creditRisk.setBasicCheck("pass");
		creditRisk.setLoanApplication(null);
	}
	
	
//==============================================(1)====================================================
	@Test
	public void test_loanApplicationFindByDatePositive() {
		List<LoanApplicationDTO> loanApplicationDTOs =new ArrayList<LoanApplicationDTO>();
		List<LoanApplication> loanApplications=new ArrayList<LoanApplication>();
		LoanApplication loanApplication=new LoanApplication();
	    loanApplication.setLoanAppId("Hello");
		loanApplication.setCustId("CUHello");
     	loanApplication.setLoanAmt(4895);
		loanApplication.setNoOfYears(5);
		loanApplication.setPurpose("BBB");
		loanApplication.setAppStatus("accpted");
		loanApplication.setTypeOfLoan("Home Loan");
		loanApplication.setLoanAppDate(LocalDate.now());
		loanApplication.setStatus("fail");
		loanApplication.setCreditRisk(null);
		loanApplications.add(loanApplication);
		LoanApplicationDTO loanApplicationDTO=new LoanApplicationDTO();
	    loanApplicationDTO.setLoanAppId("Hello");
		loanApplicationDTO.setCustId("CUHello");
     	loanApplicationDTO.setLoanAmt(4895);
		loanApplicationDTO.setNoOfYears(5);
		loanApplicationDTO.setPurpose("BBB");
		loanApplicationDTO.setAppStatus("accpted");
		loanApplicationDTO.setTypeOfLoan("Home Loan");
		loanApplicationDTO.setLoanAppDate(LocalDate.now());
		loanApplicationDTO.setStatus("fail");
		loanApplicationDTO.setCreditRisk(null);
		loanApplicationDTOs.add(loanApplicationDTO);
		when(loanAppRepository.findAllByLoanAppDate(LocalDate.now())).thenReturn(loanApplications);
		when(modelmapper.entityToDto(loanApplication)).thenReturn(loanApplicationDTO);
		assertTrue(loanApplicationServiceImpl.loanApplicationFindByDate(LocalDate.now())!=null);
	}

	@Test
	public void test_loanApplicationFindByDateException() {
	    LocalDate date = LocalDate.now(); // Set a valid date
	    List<LoanApplication> emptyLoanApplications = Collections.emptyList();

	    when(loanAppRepository.findAllByLoanAppDate(date)).thenReturn(emptyLoanApplications);
	    try {
	    loanApplicationServiceImpl.loanApplicationFindByDate(date);
	    }catch(ResourceNotFoundException e) {
	    	assertTrue(e instanceof ResourceNotFoundException);
	    }
	}

	
//=======================================(2)=========================================================

	@Test
	public void test_LoanApplicationUpdateByIdPositive() {
		String appId="L101";
		//act
		LoanApplicationDTO loanAppDto=new LoanApplicationDTO();
		loanAppDto.setLoanAppId(appId);
		loanAppDto.setPurpose("invalid");
		
		
		//Act
		
		LoanApplication LoanAppEntity=new LoanApplication();
		LoanAppEntity.setLoanAppId(appId);
		LoanAppEntity.setPurpose("invalid");
		
		
		when(loanAppRepository.findById(appId)).thenReturn(Optional.of(LoanAppEntity));
		when(loanAppRepository.save(LoanAppEntity)).thenReturn(LoanAppEntity); 
		when(modelmapper.entityToDto(LoanAppEntity)).thenReturn(loanAppDto);
		LoanApplicationDTO updatedLoanAppDto= loanApplicationServiceImpl.loanApplicationUpdateById(loanAppDto, appId);
		
		assertNotNull(updatedLoanAppDto);
		assertEquals(updatedLoanAppDto.getLoanAppId(),loanAppDto.getLoanAppId());
	}
	

	
	@Test
	public void test_loanApplicationUpdateByIdNotFoundException() {
		try {
			when(loanAppRepository.findById(anyString())).thenThrow(ResourceNotFoundException.class);
			loanApplicationServiceImpl.loanApplicationUpdateById(loanApplicationDTO,anyString());
			
		}catch(Exception e) {
			assertTrue(e instanceof ResourceNotFoundException);
		}
	}
//==========================================(3)======================================================
	
	@Test
	public void loanApplicationFindByIdPositive() {
		LoanApplicationDTO loanApplicationDTO=new LoanApplicationDTO();
	    loanApplicationDTO.setLoanAppId("Hello");
		loanApplicationDTO.setCustId("CUHello");
     	loanApplicationDTO.setLoanAmt(4895);
		loanApplicationDTO.setNoOfYears(5);
		loanApplicationDTO.setPurpose("BBB");
		loanApplicationDTO.setAppStatus("accpted");
		loanApplicationDTO.setTypeOfLoan("Home Loan");
		loanApplicationDTO.setLoanAppDate(LocalDate.now());
		loanApplicationDTO.setStatus("fail");
		
		LoanApplication loanApplication=new LoanApplication();
	    loanApplication.setLoanAppId("Hello");
		loanApplication.setCustId("CUHello");
     	loanApplication.setLoanAmt(4895);
		loanApplication.setNoOfYears(5);
		loanApplication.setPurpose("BBB");
		loanApplication.setAppStatus("accpted");
		loanApplication.setTypeOfLoan("Home Loan");
		loanApplication.setLoanAppDate(LocalDate.now());
		loanApplication.setStatus("fail");
		when(loanAppRepository.findById(anyString())).thenReturn(Optional.of(loanApplication));
		when(modelmapper.entityToDto(loanApplication)).thenReturn(loanApplicationDTO);
		LoanApplicationDTO dto=loanApplicationServiceImpl.loanApplicationFindById("Hello");
		assertTrue(dto.getLoanAppId()!=null);
	}
	
	@Test
	public void loanApplicationFindByIdNotFoundException() {
		try {
			when(loanAppRepository.findById(anyString())).thenThrow(ResourceNotFoundException.class);
			loanApplicationServiceImpl.loanApplicationFindById(anyString());
		}catch(Exception e) {
			assertTrue(e instanceof ResourceNotFoundException);
		}
	}
//===========================================(4)======================================================
	@Test
	public void test_PerformBasicCheckAcceptedPositive() {
		CreditRisk creditRisk=new CreditRisk();
		creditRisk.setCrId("CRL101");
		creditRisk.setEmi(9000);
		creditRisk.setCreditScore(750);
		creditRisk.setBasicCheck("pass");
		creditRisk.setLoanApplication(null);
		LoanAppDTO loanAppDto=new LoanAppDTO();
		loanAppDto.setAge(30);
		loanAppDto.setNoOfYears(0);
		LoanApplication loanApplication=new LoanApplication();
	    loanApplication.setLoanAppId("Hello");
		loanApplication.setCustId("CUHello");
     	loanApplication.setLoanAmt(4895);
		loanApplication.setNoOfYears(5);
		loanApplication.setPurpose("BBB");
		loanApplication.setAppStatus("accpted");
		loanApplication.setTypeOfLoan("Home Loan");
		loanApplication.setLoanAppDate(LocalDate.now());
		loanApplication.setStatus("fail");
		loanApplication.setCreditRisk(null);
		when(loanAppRepository.findById(loanAppDto.getLoanAppId())).thenReturn(Optional.of(loanApplication));
		when(creditRiskRepository.save(Mockito.any())).thenReturn(creditRisk);
		LoanStatusDTO loanStatusDto=loanApplicationServiceImpl.performBasicCheck(loanAppDto);
		assertTrue(loanStatusDto!=null);
		assertEquals(loanStatusDto.getFinalcheck(),"accepted");
	}
	
	@Test
	public void test_PerformBasicCheckAcceptedNegetive() {
		CreditRisk creditRisk=new CreditRisk();
		creditRisk.setCrId("CRL101");
		creditRisk.setEmi(9000);
		creditRisk.setCreditScore(750);
		creditRisk.setBasicCheck("pass");
		creditRisk.setLoanApplication(null);
		LoanAppDTO loanAppDto=new LoanAppDTO();
		loanAppDto.setAge(30);
		loanAppDto.setNoOfYears(40);
		LoanApplication loanApplication=new LoanApplication();
	    loanApplication.setLoanAppId("Hello");
		loanApplication.setCustId("CUHello");
     	loanApplication.setLoanAmt(4895);
		loanApplication.setNoOfYears(5);
		loanApplication.setPurpose("BBB");
		loanApplication.setAppStatus("accpted");
		loanApplication.setTypeOfLoan("Home Loan");
		loanApplication.setLoanAppDate(LocalDate.now());
		loanApplication.setStatus("fail");
		loanApplication.setCreditRisk(null);
		when(loanAppRepository.findById(loanAppDto.getLoanAppId())).thenReturn(Optional.of(loanApplication));
		when(creditRiskRepository.save(Mockito.any())).thenReturn(creditRisk);
		LoanStatusDTO loanStatusDto=loanApplicationServiceImpl.performBasicCheck(loanAppDto);
		assertTrue(loanStatusDto!=null);
		assertEquals(loanStatusDto.getFinalcheck(),"rejected");
	}
	
//===========================================(5)=======================
	@Test
	public void testCalculateEmiPositive() {
        // Arrange
		LoanDTO loan=new LoanDTO();
		loan.setDueDate(LocalDate.now());
		loan.setLoanTenureMonths(12);
		loan.setMonthlyinterestRate(1.5);
		loan.setPrincipleAmount(100000);
        EmiClacDTO result = loanApplicationServiceImpl.calculateEmi(loan);
        assertNotNull(result.getEmi());
        assertNotNull(result.getTotalAmountPayable());
    }
	
	@Test
	public void testCalculateEmiNegetive() {
		LoanDTO loan=new LoanDTO();
		EmiClacDTO result = loanApplicationServiceImpl.calculateEmi(loan);
        assertEquals(0, result.getEmi());
	}
//===========================================(6)======================================================
	@Test
	public void test_FetchCreditScorePositive() {
		CreditRisk creditRisk=new CreditRisk();
		LoanAppDTO loanAppDto=new LoanAppDTO();
		loanAppDto.setAge(30);
		loanAppDto.setNoOfYears(0);
		LoanApplication loanApplication=new LoanApplication();
		when(loanAppRepository.findById(loanAppDto.getLoanAppId())).thenReturn(Optional.of(loanApplication));
		when(creditRiskRepository.save(Mockito.any())).thenReturn(creditRisk);
		LoanStatusDTO loanStatusDto=loanApplicationServiceImpl.fetchCreditScore(loanAppDto);
		assertTrue(loanStatusDto!=null);
		assertEquals(loanStatusDto.getFinalcheck(),"accepted");
	}
	
	@Test
	public void test_FetchCreditScoreNegetive() {
		CreditRisk creditRisk=new CreditRisk();
		LoanAppDTO loanAppDto=new LoanAppDTO();
		loanAppDto.setAge(30);
		loanAppDto.setNoOfYears(40);
		LoanApplication loanApplication=new LoanApplication();
		when(loanAppRepository.findById(loanAppDto.getLoanAppId())).thenReturn(Optional.of(loanApplication));
		when(creditRiskRepository.save(Mockito.any())).thenReturn(creditRisk);
		LoanStatusDTO loanStatusDto=loanApplicationServiceImpl.fetchCreditScore(loanAppDto);
		assertTrue(loanStatusDto!=null);
		assertEquals(loanStatusDto.getFinalcheck(),"rejected");
	}
	
	@Test
	public void test_FetchCreditScoreException() {
		try {
			LoanAppDTO loanAppDto=new LoanAppDTO();
			when(loanAppRepository.findById(anyString())).thenThrow(ResourceNotFoundException.class);
			LoanStatusDTO fetchAccptance= loanApplicationServiceImpl.fetchCreditScore(loanAppDto);
		}catch(Exception e) {
			assertTrue(e instanceof ResourceNotFoundException);
		}
	}
	
	
//===========================================(7)======================================================
//	

	@Test
	public void test_FetchAcceptanceIfPart() {
		LoanApplication loanApplication=new LoanApplication();
	    loanApplication.setLoanAppId("Hello");
		loanApplication.setCustId("CUHello");
     	loanApplication.setLoanAmt(4895);
		loanApplication.setNoOfYears(5);
		loanApplication.setPurpose("BBB");
		loanApplication.setAppStatus("accepted");
		loanApplication.setTypeOfLoan("Home Loan");
		loanApplication.setLoanAppDate(LocalDate.now());
		loanApplication.setStatus("accepted");
		when(loanAppRepository.findById("Hello")).thenReturn(Optional.of(loanApplication));
		LoanAppDTOResponse loanappdto1= loanApplicationServiceImpl.fetchAcceptance("Hello");
		assertEquals(loanappdto1.getReducePayment(),5000);
	}
	@Test
	public void test_FetchAcceptanceElsePart() {
		LoanApplication loanApplication=new LoanApplication();
	    loanApplication.setLoanAppId("Hello");
		loanApplication.setCustId("CUHello");
     	loanApplication.setLoanAmt(4895);
		loanApplication.setNoOfYears(5);
		loanApplication.setPurpose("BBB");
		loanApplication.setAppStatus("rejected");
		loanApplication.setTypeOfLoan("Home Loan");
		loanApplication.setLoanAppDate(LocalDate.now());
		loanApplication.setStatus("rejected");
		when(loanAppRepository.findById(anyString())).thenReturn(Optional.of(loanApplication));
		LoanAppDTOResponse loanappdto1= loanApplicationServiceImpl.fetchAcceptance("Hello");
		assertEquals(loanappdto1.getReducePayment(),0);
		assertEquals(loanappdto1.getCustomerAcceptanceStatus(),"rejected");
	}
	
	@Test
	public void test_FetchAcceptanceException() {
		try {
			when(loanAppRepository.findById(anyString())).thenThrow(ResourceNotFoundException.class);
			LoanAppDTOResponse fetchAccptance= loanApplicationServiceImpl.fetchAcceptance(anyString());
		}catch(Exception e) {
			assertTrue(e instanceof ResourceNotFoundException);
		}
	}
//=============================================(8)===================================================
	@Test
	public void test_AcceptenceReducePayment_Positive() {
		LoanApplication loanApplication=new LoanApplication();
	    loanApplication.setLoanAppId("Hello");
		loanApplication.setCustId("CUHello");
     	loanApplication.setLoanAmt(4895);
		loanApplication.setNoOfYears(5);
		loanApplication.setPurpose("BBB");
		loanApplication.setAppStatus("accpted");
		loanApplication.setTypeOfLoan("Home Loan");
		loanApplication.setLoanAppDate(LocalDate.now());
		loanApplication.setStatus("fail");
		when(loanAppRepository.findById("Hello")).thenReturn(Optional.of(loanApplication));
		List<ReducePaymentDTO> reducePayments=loanApplicationServiceImpl.acceptenceReducePayment("Hello");
		assertTrue(reducePayments.size()>0);
		
	}
	@Test
	public void test_AcceptenceReducePayment_Negetive() {
		try {
			when(loanAppRepository.findById(anyString())).thenThrow(ResourceNotFoundException.class);
			loanApplicationServiceImpl.acceptenceReducePayment(anyString());
		}catch(Exception e) {
			assertTrue(e instanceof ResourceNotFoundException);
		}
	}
	
	@Test
	public void test_getAllLoanApplications() {
		List<LoanApplicationDTO> loanApplicationDTOs =new ArrayList<LoanApplicationDTO>();
		List<LoanApplication> loanApplications=new ArrayList<LoanApplication>();
		LoanApplication loanApplication=new LoanApplication();
	    loanApplication.setLoanAppId("Hello");
		loanApplication.setCustId("CUHello");
     	loanApplication.setLoanAmt(4895);
		loanApplication.setNoOfYears(5);
		loanApplication.setPurpose("BBB");
		loanApplication.setAppStatus("accpted");
		loanApplication.setTypeOfLoan("Home Loan");
		loanApplication.setLoanAppDate(LocalDate.now());
		loanApplication.setStatus("fail");
		loanApplications.add(loanApplication);
		LoanApplicationDTO loanApplicationDTO=new LoanApplicationDTO();
	    loanApplicationDTO.setLoanAppId("Hello");
		loanApplicationDTO.setCustId("CUHello");
     	loanApplicationDTO.setLoanAmt(4895);
		loanApplicationDTO.setNoOfYears(5);
		loanApplicationDTO.setPurpose("BBB");
		loanApplicationDTO.setAppStatus("accpted");
		loanApplicationDTO.setTypeOfLoan("Home Loan");
		loanApplicationDTO.setLoanAppDate(LocalDate.now());
		loanApplicationDTO.setStatus("fail");
		loanApplicationDTOs.add(loanApplicationDTO);
		when(loanAppRepository.findAll()).thenReturn(loanApplications);
		when(modelmapper.entityToDto(loanApplication)).thenReturn(loanApplicationDTO);
		List<LoanApplicationDTO> loanApplicationDtos=loanApplicationServiceImpl.getAllLoanApplications();
	}
}
