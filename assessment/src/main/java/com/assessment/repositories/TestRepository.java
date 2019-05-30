package com.assessment.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.Test;

public interface TestRepository extends JpaRepository<com.assessment.data.Test,Long>{
	
	@Query("SELECT t FROM Test t WHERE t.testName=:testName and t.companyId=:companyId")
	com.assessment.data.Test findByPrimaryKey(@Param("testName") String testName, @Param("companyId") String companyId);
	
	@Query("SELECT t FROM Test t WHERE  t.companyId=:companyId")
	List<com.assessment.data.Test> findByCompanyId( @Param("companyId") String companyId);
	@Query(value="SELECT t FROM Test t WHERE  t.companyId=:companyId", countQuery="SELECT COUNT(*) FROM Test t WHERE  t.companyId=:companyId")
	Page<com.assessment.data.Test> findByCompanyId(@Param("companyId") String companyId,  Pageable pageable);

	
	@Query("SELECT t FROM Test t WHERE t.companyId=:companyId and t.testName LIKE %:testName%")
	public List<Test> searchTests(@Param("companyId") String companyId, @Param("testName")  String testName);
	@Query(value="SELECT t FROM Test t WHERE t.companyId=:companyId and t.testName LIKE %:testName%", countQuery="SELECT COUNT(*) FROM Test t WHERE t.companyId=:companyId and t.testName LIKE %:testName%")
	Page<com.assessment.data.Test> searchTests(@Param("companyId") String companyId, @Param("testName")  String testName, Pageable pageable);
	
	@Query("SELECT t FROM Test t WHERE t.id=:testId and t.companyId=:companyId")
	com.assessment.data.Test findTestById(@Param("testId") Long TestId,@Param("companyId") String companyId);
}