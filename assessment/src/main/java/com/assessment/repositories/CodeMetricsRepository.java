package com.assessment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.CodeMetrics;

public interface CodeMetricsRepository extends JpaRepository<CodeMetrics, Long> {

	@Query("SELECT q FROM CodeMetrics q WHERE q.testName=:testName and q.email=:email and q.questionMapperInstanceId=:questionMapperInstanceId and q.companyId=:companyId")
	public CodeMetrics getUniqueCodeMetrics(@Param("companyId") String companyId, @Param("testName") String testName, @Param("email") String email, @Param("questionMapperInstanceId") Long questionMapperInstanceId);
	
	@Query("SELECT q FROM CodeMetrics q WHERE q.testName=:testName and q.questionId=:qId and q.companyId=:companyId")
	public List<CodeMetrics> findCodeMetricsForTestAndQuestion(@Param("companyId") String companyId, @Param("testName") String testName,@Param("qId") Long qId);
	
	@Query("SELECT q FROM CodeMetrics q WHERE q.testName=:testName and q.companyId=:companyId")
	public List<CodeMetrics> findCodeMetricsForTest(@Param("companyId") String companyId, @Param("testName") String testName);
	
	
}
