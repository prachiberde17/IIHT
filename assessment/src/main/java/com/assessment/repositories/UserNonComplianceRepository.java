package com.assessment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.UserNonCompliance;

public interface UserNonComplianceRepository extends JpaRepository< UserNonCompliance,Long>{

	
	@Query("SELECT u FROM UserNonCompliance u WHERE u.user=:user and u.testName=:testName and u.companyId=:companyId and u.userTestSessionId=:userTestSessionId")
	UserNonCompliance findByPrimaryKey(@Param("user") String user, @Param("testName")  String testName, @Param("companyId") String companyId, @Param("userTestSessionId") Long userTestSessionId);
	
	@Query("SELECT u FROM UserNonCompliance u WHERE u.user=:user and u.testName=:testName and u.companyId=:companyId")
	UserNonCompliance findNonCompliance(@Param("user") String user, @Param("testName")  String testName, @Param("companyId") String companyId);

}
