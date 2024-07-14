package com.cognizant.loanapplication.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cognizant.loanapplication.entities.CreditRisk;
@Repository
public interface CreditRiskRepository extends JpaRepository<CreditRisk, String> {
	@Query("select s from CreditRisk s where s.loanApplication = :loanAppId1")
	Optional<CreditRisk> getByLoan_appid(@Param("loanAppId1") String loanAppId);
}
