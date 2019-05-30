package com.assessment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.Company;
import com.assessment.data.Question;

public interface CompanyRepository extends JpaRepository<Company,Long>
{
	
	@Query("SELECT c FROM Company c WHERE c.companyName=:companyName and c.companyId=:companyId")
	Company findByPrimaryKey(@Param("companyName") String companyName, @Param("companyId") String companyId);
	
	@Query("SELECT c FROM Company c WHERE c.companyName=:companyName")
	Company findByCompanyNameIgnoreCase(@Param("companyName") String companyName);
	
	@Query("SELECT c FROM Company c WHERE c.companyId=:companyId")
	Company findByCompanyId(@Param("companyId") String companyId);
}

