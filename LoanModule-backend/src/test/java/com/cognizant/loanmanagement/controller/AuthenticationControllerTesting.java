package com.cognizant.loanmanagement.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;

import com.cognizant.loanapplication.BankLandingPortal1Application;
import com.cognizant.loanapplication.controller.AuthenticationController;
import com.cognizant.loanapplication.dtos.LoanApplicationDTO;
import com.cognizant.loanapplication.dtos.UserDTO;
import com.cognizant.loanapplication.dtos.UserRequest;
import com.cognizant.loanapplication.service.UserService;
import com.cognizant.loanapplication.serviceimpl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.bytebuddy.agent.VirtualMachine.ForHotSpot.Connection.Response;



@SpringBootTest(classes = BankLandingPortal1Application.class)
public class AuthenticationControllerTesting {
	private MockMvc mockMvc;
	@Mock
	private UserServiceImpl userService;
	
	@Autowired
	private LocalValidatorFactoryBean validator;
	private MockRestServiceServer mockServer;
	private RestTemplate template;
	private ObjectMapper mapper=new ObjectMapper();
	
	@InjectMocks
	private AuthenticationController controller;
	
	@BeforeEach
    void setUp() throws Exception {
//    	MockitoAnnotations.initMocks(this);
		mockMvc=MockMvcBuilders.standaloneSetup(controller).build();
		template=new RestTemplate();
		mockServer=MockRestServiceServer.createServer(template);
	}
	
	@Test
	public void test_authenticatePositive() {
		UserDTO userDto=new UserDTO();
		userDto.setAccountLocked(false);
		userDto.setPassword("ABCD");
		userDto.setRole("Admin");
		userDto.setUserName("Sayan");
		
		UserRequest userRequest=new UserRequest();
		userRequest.setPassword("ABCD");
		userRequest.setUserName("Sayan");
		when(userService.authenticateUser("Sayan", "ABCD")).thenReturn(userDto);
		ResponseEntity<UserDTO> response=controller.authenticate(userRequest);
		assertTrue(response.getBody().getUserName()!=null);
		
	}
	
	@Test
	public void test_authenticateStatusCode() {
		UserDTO userDto=new UserDTO();
		userDto.setAccountLocked(false);
		userDto.setPassword("ABCD");
		userDto.setRole("Admin");
		userDto.setUserName("Sayan");
		
		UserRequest userRequest=new UserRequest();
		userRequest.setPassword("ABCD");
		userRequest.setUserName("Sayan");
		when(userService.authenticateUser("Sayan", "ABCD")).thenReturn(userDto);
		ResponseEntity<UserDTO> response=controller.authenticate(userRequest);
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		
	}
	
	@Test
	public void test_authenticateNegetive() {
		UserDTO userDto=new UserDTO();
		UserRequest userRequest=new UserRequest();
		userRequest.setPassword("ABCD");
		userRequest.setUserName("Sayan");
		when(userService.authenticateUser("Sayan", "ABCD")).thenReturn(userDto);
		ResponseEntity<UserDTO> response=controller.authenticate(userRequest);
		assertTrue(response.getBody().getUserName()==null);
		
	}
	
	
	@Test
	public void test_authenticateNegetiveStatusCode() {
		UserDTO userDto=new UserDTO();
		UserRequest userRequest=new UserRequest();
		userRequest.setPassword("ABCD");
		userRequest.setUserName("Sayan");
		when(userService.authenticateUser("Sayan", "ABCD")).thenReturn(userDto);
		ResponseEntity<UserDTO> response=controller.authenticate(userRequest);
		assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
		
	}
	
//    @Test
//    public void findApplicationByDateTest_positiveReturnValue() {
//    	List<LoanApplicationDTO> loanApplicationDtos=new ArrayList<>();
//    	loanApplicationDtos.add(loanApplicationDto);
//    	when(loanApplicationService.loanApplicationFindByDate(LocalDate.now())).thenReturn(loanApplicationDtos);
//    	ResponseEntity<List<LoanApplicationDTO>> response = controller.findApplicationByDate(LocalDate.now());
//    	List<LoanApplicationDTO> dtos=(List<LoanApplicationDTO>)response.getBody();
//    	assertTrue(dtos.size()>0);
//    }
//    
//    @Test
//	public void findApplicationByDateTest_negativeReturnValue() {
//		try {
//		List<LoanApplicationDTO> loanApplicationDtos=new ArrayList<>();
//		when(loanApplicationService.loanApplicationFindByDate(any())).thenReturn(loanApplicationDtos);
//		ResponseEntity<?> responseEntity=controller.findApplicationByDate(any());
//		assertNull(responseEntity.getBody());
//		}catch(Exception e) {
//			assertTrue(false);
//		}
//	}
}
