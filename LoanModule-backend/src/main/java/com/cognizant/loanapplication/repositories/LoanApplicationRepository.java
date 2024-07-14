package com.cognizant.loanapplication.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.loanapplication.entities.LoanApplication;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, String> {
	List<LoanApplication> findAllByLoanAppDate(LocalDate loanAppDate);
}
