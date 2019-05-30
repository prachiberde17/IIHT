package com.assessment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.Section;

public interface SectionRepository extends JpaRepository<Section,Long>{
//	@Query("SELECT s FROM Section s WHERE s.sectionName=:sectionName and s.companyId=:companyId")
//	Section findByPrimaryKey(@Param("sectionName") String sectionName, @Param("companyId") String companyId);
	
	@Query("SELECT s FROM Section s WHERE s.sectionName=:sectionName and s.companyId=:companyId and s.testName=:testName")
	Section findByPrimaryKey(@Param("testName") String testName, @Param("sectionName") String sectionName, @Param("companyId") String companyId);
	
	@Query("SELECT s FROM Section s WHERE s.testName=:testName and s.companyId=:companyId")
	public List<Section> getSectionsForTest(@Param("testName") String testName,  @Param("companyId") String companyId);

}
