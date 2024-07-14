package com.cognizant.loanmanagement.repositories;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import com.cognizant.loanapplication.BankLandingPortal1Application;
import com.cognizant.loanapplication.entities.LoanApplication;
import com.cognizant.loanapplication.repositories.LoanApplicationRepository;


@DataJpaTest
@ContextConfiguration(classes = BankLandingPortal1Application.class)
public class LoanApplicationRepositoryTest {
	
	@Autowired
	private LoanApplicationRepository loanAppRepo;
	
	@Autowired
	private TestEntityManager entityManager;
	@Test
	public void DataSavePositive() {
		LoanApplication loanApplication=new LoanApplication();
		loanApplication.setLoanAppId("AAA097");
		loanApplication.setCustId("CUAAA097");
		loanApplication.setLoanAmt(673546);
		loanApplication.setNoOfYears(6);
		loanApplication.setPurpose("Home");
		loanApplication.setAppStatus("Good");
		loanApplication.setTypeOfLoan("HomeLoan");
		loanApplication.setLoanAppDate(LocalDate.of(2024, Month.FEBRUARY, 10));
		loanApplication.setStatus("accept");
		entityManager.persist(loanApplication);
		
		Iterable<LoanApplication> it=loanAppRepo.findAll();
		assertTrue(it.iterator().hasNext());
	}
//	@Test
//	public void DataSaveNegative() {
//		Iterable<LoanApplication> it=loanAppRepo.findAll();
//		assertTrue(!it.iterator().hasNext());
//	}
	
	@Test
	public void testfindByLoanAppDatePositive() {
		LoanApplication loanApplication=new LoanApplication();
		loanApplication.setLoanAppId("AAA097");
		loanApplication.setCustId("CUAAA097");
		loanApplication.setLoanAmt(673546);
		loanApplication.setNoOfYears(6);
		loanApplication.setPurpose("Home");
		loanApplication.setAppStatus("Good");
		loanApplication.setTypeOfLoan("HomeLoan");
		loanApplication.setLoanAppDate(LocalDate.now());
		loanApplication.setStatus("accept");
		entityManager.persist(loanApplication);
		List<LoanApplication> loanApplication1=loanAppRepo.findAllByLoanAppDate(LocalDate.now());
		assertTrue(!loanApplication1.isEmpty());
	}
	
	@Test
	public void testfindByLoanAppDateNegetive() {
		List<LoanApplication> loanApplication1=loanAppRepo.findAllByLoanAppDate(LocalDate.of(2024, Month.FEBRUARY, 10));
		assertTrue(loanApplication1.isEmpty());
	}
	
	@Test
	public void testSavePositive() {
		LoanApplication loanApplication=new LoanApplication();
		loanApplication.setLoanAppId("AAA097");
		loanApplication.setCustId("CUAAA097");
		loanApplication.setLoanAmt(673546);
		loanApplication.setNoOfYears(6);
		loanApplication.setPurpose("Home");
		loanApplication.setAppStatus("Good");
		loanApplication.setTypeOfLoan("HomeLoan");
		loanApplication.setLoanAppDate(LocalDate.now());
		loanApplication.setStatus("accept");
		entityManager.persist(loanApplication);
		Optional<LoanApplication> loanApplication1=loanAppRepo.findById("AAA097");
		assertTrue(loanApplication1.isPresent());
	}
	
	@Test
	public void testDeletePositive() {
		LoanApplication loanApplication=new LoanApplication();
		loanApplication.setLoanAppId("AAA097");
		loanApplication.setCustId("CUAAA097");
		loanApplication.setLoanAmt(673546);
		loanApplication.setNoOfYears(6);
		loanApplication.setPurpose("Home");
		loanApplication.setAppStatus("Good");
		loanApplication.setTypeOfLoan("HomeLoan");
		loanApplication.setLoanAppDate(LocalDate.now());
		loanApplication.setStatus("accept");
		entityManager.persist(loanApplication);
		loanAppRepo.delete(loanApplication);
		Optional<LoanApplication> loanApplication1=loanAppRepo.findById("AAA097");
		assertTrue(!loanApplication1.isPresent());
	}
}
