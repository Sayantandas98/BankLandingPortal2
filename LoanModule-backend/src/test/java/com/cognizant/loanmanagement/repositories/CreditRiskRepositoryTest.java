package com.cognizant.loanmanagement.repositories;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import com.cognizant.loanapplication.BankLandingPortal1Application;
import com.cognizant.loanapplication.entities.CreditRisk;
import com.cognizant.loanapplication.repositories.CreditRiskRepository;

@DataJpaTest
@ContextConfiguration(classes = BankLandingPortal1Application.class)
public class CreditRiskRepositoryTest {
	@Autowired
	private CreditRiskRepository creditRiskRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	@Test
	public void DataSavePositive() {
		CreditRisk creditRisk=new CreditRisk();
		creditRisk.setCrId("Hello");
		creditRisk.setCreditScore(720);
		creditRisk.setEmi(3456);
		creditRisk.setBasicCheck("pass");
		entityManager.persist(creditRisk);
		Iterable<CreditRisk> it=creditRiskRepository.findAll();
		assertTrue(it.iterator().hasNext());
	}
//	@Test
//	public void DataSaveNegative() {
//		Iterable<CreditRisk> it=creditRiskRepository.findAll();
//		assertTrue(!it.iterator().hasNext());
//	}
	
	@Test
	public void testfindByIdPositive() {
		CreditRisk creditRisk=new CreditRisk();
		creditRisk.setCrId("Hello");
		creditRisk.setCreditScore(720);
		creditRisk.setEmi(3456);
		creditRisk.setBasicCheck("pass");
		entityManager.persist(creditRisk);
		Optional<CreditRisk> creditRisk1=creditRiskRepository.findById("Hello");
		assertTrue(creditRisk1.isPresent());
	}
	
	@Test
	public void testfindByIdNegetive() {
		Optional<CreditRisk> creditRisk1=creditRiskRepository.findById("Hello");
		assertTrue(creditRisk1.isEmpty());
	}
	
	@Test
	public void testSavePositive() {
		CreditRisk creditRisk=new CreditRisk();
		creditRisk.setCrId("Hello");
		creditRisk.setCreditScore(720);
		creditRisk.setEmi(3456);
		creditRisk.setBasicCheck("pass");
		entityManager.persist(creditRisk);
		Optional<CreditRisk> creditRisk1=creditRiskRepository.findById("Hello");
		assertTrue(creditRisk1.isPresent());
	}
	
}
