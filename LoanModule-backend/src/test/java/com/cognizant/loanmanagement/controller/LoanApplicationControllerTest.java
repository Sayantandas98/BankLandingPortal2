package com.cognizant.loanmanagement.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;

import com.cognizant.loanapplication.BankLandingPortal1Application;
import com.cognizant.loanapplication.controller.Controller;
import com.cognizant.loanapplication.dtos.CreditRiskDTO;
import com.cognizant.loanapplication.dtos.EmiClacDTO;
import com.cognizant.loanapplication.dtos.LoanAppDTO;
import com.cognizant.loanapplication.dtos.LoanAppDTOResponse;
import com.cognizant.loanapplication.dtos.LoanApplicationDTO;
import com.cognizant.loanapplication.dtos.LoanDTO;
import com.cognizant.loanapplication.dtos.LoanStatusDTO;
import com.cognizant.loanapplication.dtos.ReducePaymentDTO;
import com.cognizant.loanapplication.entities.LoanApplication;
import com.cognizant.loanapplication.repositories.LoanApplicationRepository;
import com.cognizant.loanapplication.service.LoanApplicationService;
import com.cognizant.loanapplication.serviceimpl.LoanApplicationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = BankLandingPortal1Application.class)
public class LoanApplicationControllerTest {
	
	private MockMvc mockMvc;
	@Mock
	private LoanApplicationRepository loanApplicationRepository;
	
	@Mock
	private CreditRiskDTO creditRiskDto;
	@Mock
	private LoanAppDTO loanAppDto;
	@Mock
	private LoanAppDTOResponse loanAppDtoResponse;
	@Mock
	private LoanApplicationDTO loanApplicationDto;
	@Mock
	private LoanApplication loanApplication;
	@Mock
	private LoanDTO loanDto;
	@Mock
	private EmiClacDTO emiClacDto;
	@Mock
	private LoanStatusDTO loanStatusDto;
	@Mock
	private ReducePaymentDTO reducePaymentDto;
	@Mock
	private LoanApplicationService loanApplicationService;
	@InjectMocks
	private Controller controller;
	 
	@Autowired
	private LocalValidatorFactoryBean validator;
	private MockRestServiceServer mockServer;
	private RestTemplate template;
	private ObjectMapper mapper=new ObjectMapper();
    @BeforeEach
    void setUp() throws Exception {
//    	MockitoAnnotations.initMocks(this);
		mockMvc=MockMvcBuilders.standaloneSetup(controller).build();
		template=new RestTemplate();
		mockServer=MockRestServiceServer.createServer(template);
		loanApplicationDto = new LoanApplicationDTO();
    	loanApplicationDto.setLoanAppId("Hello");
		loanApplicationDto.setCustId("CUHello");
     	loanApplicationDto.setLoanAmt(4895);
		loanApplicationDto.setNoOfYears(5);
		loanApplicationDto.setPurpose("BBB");
		loanApplicationDto.setAppStatus("accpted");
		loanApplicationDto.setTypeOfLoan("Home Loan");
		loanApplicationDto.setLoanAppDate(LocalDate.now());
		loanApplicationDto.setStatus("fail");
		
		loanApplication=new LoanApplication();
    	loanApplication.setLoanAppId("Helloo");
		loanApplication.setCustId("CUHi");
     	loanApplication.setLoanAmt(489534);
		loanApplication.setNoOfYears(5);
		loanApplication.setPurpose("BBB");
		loanApplication.setAppStatus("reject");
		loanApplication.setTypeOfLoan("Gold Loan");
		loanApplication.setLoanAppDate(LocalDate.now());
		loanApplication.setStatus("fail");
		
		
		loanAppDto=new LoanAppDTO();
		loanAppDto.setAge(4);
		loanAppDto.setLoanAppId("L100");
		loanAppDto.setNoOfYears(5);
		loanAppDto.setSalary(40000);
		
		loanStatusDto=new LoanStatusDTO();
		loanStatusDto.setBasicCheck("accepted");
		loanStatusDto.setCreditScore(750);
		loanStatusDto.setFinalcheck("approved");
		
		loanDto=new LoanDTO();
		loanDto.setDueDate(LocalDate.now());
		loanDto.setLoanTenureMonths(120);
		loanDto.setMonthlyinterestRate(500000);
		loanDto.setPrincipleAmount(10000000);
		
		emiClacDto=new EmiClacDTO();
		emiClacDto.setEmi(17888);
		emiClacDto.setTotalAmountPayable(900000);
    }
 
    @Test
    public void findApplicationByDateTest_positiveReturnValue() {
    	List<LoanApplicationDTO> loanApplicationDtos=new ArrayList<>();
    	loanApplicationDtos.add(loanApplicationDto);
    	when(loanApplicationService.loanApplicationFindByDate(LocalDate.now())).thenReturn(loanApplicationDtos);
    	ResponseEntity<List<LoanApplicationDTO>> response = controller.findApplicationByDate(LocalDate.now());
    	List<LoanApplicationDTO> dtos=(List<LoanApplicationDTO>)response.getBody();
    	assertTrue(dtos.size()>0);
    }
    
    @Test
	public void findApplicationByDateTest_negativeReturnValue() {
		try {
		List<LoanApplicationDTO> loanApplicationDtos=new ArrayList<>();
		when(loanApplicationService.loanApplicationFindByDate(any())).thenReturn(loanApplicationDtos);
		ResponseEntity<?> responseEntity=controller.findApplicationByDate(any());
		assertNull(responseEntity.getBody());
		}catch(Exception e) {
			assertTrue(false);
		}
	}
    
    @Test
    public void findApplicationByDateTest_positivestatuscode() {
    	List<LoanApplicationDTO> loanApplicationDtos=new ArrayList<>();
    	LoanApplicationDTO loanApplicationDto=new LoanApplicationDTO();
    	loanApplicationDto.setLoanAppId("Hello");
		loanApplicationDto.setCustId("CUHello");
     	loanApplicationDto.setLoanAmt(4895);
		loanApplicationDto.setNoOfYears(5);
		loanApplicationDto.setPurpose("BBB");
		loanApplicationDto.setAppStatus("accpted");
		loanApplicationDto.setTypeOfLoan("Home Loan");
		loanApplicationDto.setLoanAppDate(LocalDate.now());
		loanApplicationDto.setStatus("fail");
    	loanApplicationDtos.add(loanApplicationDto);
    	when(loanApplicationService.loanApplicationFindByDate(LocalDate.now())).thenReturn(loanApplicationDtos);
    	
    	ResponseEntity<?> response = controller.findApplicationByDate(LocalDate.now());
    	try {
	    	assertEquals(200,response.getStatusCodeValue());
		}catch(Exception e) {
				assertTrue(false);
		}
    }
    
    
    @Test
	public void urihandleGetAllEmployees_Positive() {
		List<LoanApplicationDTO> mockListOfLoanApplicationDto=new ArrayList<>();
		LoanApplicationDTO loanApplicationDto=new LoanApplicationDTO();
		loanApplicationDto.setLoanAppId("Hello");
		loanApplicationDto.setCustId("CUHello");
     	loanApplicationDto.setLoanAmt(4895);
		loanApplicationDto.setNoOfYears(5);
		loanApplicationDto.setPurpose("BBB");
		loanApplicationDto.setAppStatus("accpted");
		loanApplicationDto.setTypeOfLoan("Home Loan");
		loanApplicationDto.setLoanAppDate(LocalDate.of(2023, 10, 21));
		loanApplicationDto.setStatus("fail");
		mockListOfLoanApplicationDto.add(loanApplicationDto);
		when(loanApplicationService.loanApplicationFindByDate(any())).thenReturn(mockListOfLoanApplicationDto);
		try {
			MvcResult mvcResult=mockMvc
			.perform(get("http://localhost:8082/api/loanapps/view/2023-10-21"))
			.andExpect(status().isOk())
			.andReturn();
		} catch (Exception e) {
			System.out.println(e);
			assertTrue(false);
		}
	}
    
    @Test
    public void updateByIdTest_positiveUpdate() {
    	LoanApplicationDTO loanApplicationDto1=new LoanApplicationDTO();
    	loanApplicationDto1.setLoanAppId("Hello");
		loanApplicationDto1.setCustId("CUHello");
     	loanApplicationDto1.setLoanAmt(4895);
		loanApplicationDto1.setNoOfYears(5);
		loanApplicationDto1.setPurpose("BBB");
		loanApplicationDto1.setAppStatus("accpted");
		loanApplicationDto1.setTypeOfLoan("Home Loan");
		loanApplicationDto1.setLoanAppDate(LocalDate.now());
		loanApplicationDto1.setStatus("fail");
    	when(loanApplicationService.loanApplicationUpdateById(loanApplicationDto1,"Helloo")).thenReturn(loanApplicationDto1);
    	ResponseEntity<LoanApplicationDTO> responseEntity=controller.updateById(loanApplicationDto1,"Helloo");
    	assertEquals(loanApplicationDto1, responseEntity.getBody());
    }
    
    
    @Test
    public void updateByIdTest_positiveStatusCode() {
    	try {
    	LoanApplicationDTO loanApplicationDto1=new LoanApplicationDTO();
    	loanApplicationDto1.setLoanAppId("Hello");
		loanApplicationDto1.setCustId("CUHello");
     	loanApplicationDto1.setLoanAmt(4895);
		loanApplicationDto1.setNoOfYears(5);
		loanApplicationDto1.setPurpose("BBB");
		loanApplicationDto1.setAppStatus("accpted");
		loanApplicationDto1.setTypeOfLoan("Home Loan");
		loanApplicationDto1.setLoanAppDate(LocalDate.now());
		loanApplicationDto1.setStatus("fail");
    	when(loanApplicationService.loanApplicationUpdateById(loanApplicationDto1,"Hello")).thenReturn(loanApplicationDto1);
    	ResponseEntity<LoanApplicationDTO> responseEntity=controller.updateById(loanApplicationDto1,"Hello");
    	assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    	}catch(Exception e)
    	{
    		assertTrue(false);
    	}
    }
    
    @Test
    public void getByIdTest_positive() {
    	try {
	    	LoanApplicationDTO loanApplicationDto1=new LoanApplicationDTO();
	    	loanApplicationDto1.setLoanAppId("Hello");
			loanApplicationDto1.setCustId("CUHello");
	     	loanApplicationDto1.setLoanAmt(4895);
			loanApplicationDto1.setNoOfYears(5);
			loanApplicationDto1.setPurpose("BBB");
			loanApplicationDto1.setAppStatus("accpted");
			loanApplicationDto1.setTypeOfLoan("Home Loan");
			loanApplicationDto1.setLoanAppDate(LocalDate.now());
			loanApplicationDto1.setStatus("fail");
			when(loanApplicationService.loanApplicationFindById("Hello")).thenReturn(loanApplicationDto1);
			ResponseEntity<LoanApplicationDTO> responseEntity=controller.getById("Hello");
			assertEquals(loanApplicationDto1, responseEntity.getBody());
    	}catch(Exception e)
    	{
    		assertTrue(false);
    	}
    }
    
    @Test
    public void getByIdTest_positiveStatusCode() {
    	try {
	    	LoanApplicationDTO loanApplicationDto1=new LoanApplicationDTO();
	    	loanApplicationDto1.setLoanAppId("Hello");
			loanApplicationDto1.setCustId("CUHello");
	     	loanApplicationDto1.setLoanAmt(4895);
			loanApplicationDto1.setNoOfYears(5);
			loanApplicationDto1.setPurpose("BBB");
			loanApplicationDto1.setAppStatus("accpted");
			loanApplicationDto1.setTypeOfLoan("Home Loan");
			loanApplicationDto1.setLoanAppDate(LocalDate.now());
			loanApplicationDto1.setStatus("fail");
			when(loanApplicationService.loanApplicationFindById("Hello")).thenReturn(loanApplicationDto1);
			ResponseEntity<LoanApplicationDTO> responseEntity=controller.getById("Hello");
			assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    	}catch(Exception e)
    	{
    		assertTrue(false);
    	}
    }
    
    
    @Test
    public void updateBasicCheck_positiveCheck() {
    	try {
    		when(loanApplicationService.performBasicCheck(loanAppDto)).thenReturn(loanStatusDto);
        	ResponseEntity<LoanStatusDTO> responseEntity=controller.updateBasicCheck(loanAppDto);
        	assertEquals(loanStatusDto, responseEntity.getBody());
    	}catch(Exception e) {
    		assertTrue(false);
    	}
    }
    
    @Test
    public void updateBasicCheck_StatusCodeCheck() {
    	try {
    		when(loanApplicationService.performBasicCheck(loanAppDto)).thenReturn(loanStatusDto);
        	ResponseEntity<LoanStatusDTO> responseEntity=controller.updateBasicCheck(loanAppDto);
        	assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    	}catch(Exception e) {
    		assertTrue(false);
    	}
    }
    
    @Test
    public void calculateEmi_positiveValueCheck() {
    	try {
    		when(loanApplicationService.calculateEmi(loanDto)).thenReturn(emiClacDto);
        	ResponseEntity<EmiClacDTO> responseEntity=controller.claculateEmi(loanDto);
        	assertEquals(emiClacDto, responseEntity.getBody());
    	}catch(Exception e) {
    		assertTrue(false);
    	}
    }
    
    @Test
    public void calculateEmi_positiveStatusCode() {
    	try {
    		when(loanApplicationService.calculateEmi(loanDto)).thenReturn(emiClacDto);
        	ResponseEntity<EmiClacDTO> responseEntity=controller.claculateEmi(loanDto);
        	assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    	}catch(Exception e) {
    		assertTrue(false);
    	}
    }
    
    @Test
    public void getCreditScore_positiveValueCheck() {
    	try {
    		when(loanApplicationService.fetchCreditScore(loanAppDto)).thenReturn(loanStatusDto);
        	ResponseEntity<LoanStatusDTO> responseEntity=controller.getAllCreditScore(loanAppDto);
        	System.out.println(responseEntity.getBody().getBasicCheck());
        	assertEquals(loanStatusDto, responseEntity.getBody());
    	}catch(Exception e) {
    		assertTrue(false);
    	}
    }
    
    @Test
    public void getCreditScore_positiveStatusCode() {
    	try {
    		when(loanApplicationService.fetchCreditScore(loanAppDto)).thenReturn(loanStatusDto);
        	ResponseEntity<LoanStatusDTO> responseEntity=controller.getAllCreditScore(loanAppDto);
        	assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    	}catch(Exception e) {
    		assertTrue(false);
    	}
    }
    
    @Test
    public void getCreditScore_customerAcceptanceValueCheck() {
    	try {
    		when(loanApplicationService.fetchAcceptance("Hello")).thenReturn(loanAppDtoResponse);
        	ResponseEntity<LoanAppDTOResponse> responseEntity=controller.checkCustomerAcceptanceStatus("Hello");
        	assertEquals(loanAppDtoResponse, responseEntity.getBody());
    	}catch(Exception e) {
    		assertTrue(false);
    	}
    }
    
    @Test
    public void getCreditScore_customerAcceptanceStatusCode() {
    	try {
    		when(loanApplicationService.fetchAcceptance("Hello")).thenReturn(loanAppDtoResponse);
        	ResponseEntity<LoanAppDTOResponse> responseEntity=controller.checkCustomerAcceptanceStatus("Hello");
        	assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    	}catch(Exception e) {
    		assertTrue(false);
    	}
    }
    
    @Test
    public void getCreditScore_sanctionAmountValueCheck() {
    	List<ReducePaymentDTO> r=new ArrayList<>();
    	ReducePaymentDTO r1=new ReducePaymentDTO();
    	r1.setInstallment(3455);
    	r1.setInterest(356);
    	r1.setLoanappid("L100");
    	r1.setMonthNo(4);
    	r1.setPOutStandingBeginOfMon(64574);
    	r1.setPOutStandingEndOfMon(72643);
    	r1.setPRepayment(23435);
    	r.add(r1);
    	try {
    		when(loanApplicationService.acceptenceReducePayment("Hello")).thenReturn(r);
        	ResponseEntity<List<ReducePaymentDTO>> responseEntity=controller.sanctionLoanAmount("Hello");
        	assertEquals(r, responseEntity.getBody());
    	}catch(Exception e) {
    		assertTrue(false);
    	}
    }
    
    @Test
    public void getCreditScore_sanctionAmountStatusCodeCheck() {
    	List<ReducePaymentDTO> r=new ArrayList<>();
    	ReducePaymentDTO r1=new ReducePaymentDTO();
    	r1.setInstallment(3455);
    	r1.setInterest(356);
    	r1.setLoanappid("L100");
    	r1.setMonthNo(4);
    	r1.setPOutStandingBeginOfMon(64574);
    	r1.setPOutStandingEndOfMon(72643);
    	r1.setPRepayment(23435);
    	r.add(r1);
    	try {
    		when(loanApplicationService.acceptenceReducePayment("Hello")).thenReturn(r);
        	ResponseEntity<List<ReducePaymentDTO>> responseEntity=controller.sanctionLoanAmount("Hello");
        	assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    	}catch(Exception e) {
    		assertTrue(false);
    	}
    }
    
    
    @Test
    public void getCreditScore_getAllLoanApplicationValueCheck() {
    	List<LoanApplicationDTO> loanApplicationDtos=new ArrayList<>();
    	loanApplicationDtos.add(loanApplicationDto);
    	try {
    		when(loanApplicationService.getAllLoanApplications()).thenReturn(loanApplicationDtos);
        	ResponseEntity<List<LoanApplicationDTO>> responseEntity=controller.getAllLoanApplication();
        	assertEquals(loanApplicationDtos, responseEntity.getBody());
    	}catch(Exception e) {
    		assertTrue(false);
    	}
    }
    
    @Test
    public void getCreditScore_getAllLoanApplicationStatusCodeCheck() {
    	List<LoanApplicationDTO> loanApplicationDtos=new ArrayList<>();
    	loanApplicationDtos.add(loanApplicationDto);
    	try {
    		when(loanApplicationService.getAllLoanApplications()).thenReturn(loanApplicationDtos);
        	ResponseEntity<List<LoanApplicationDTO>> responseEntity=controller.getAllLoanApplication();
        	assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    	}catch(Exception e) {
    		assertTrue(false);
    	}
    }
    
    
    

}
