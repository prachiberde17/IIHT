package com.assessment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.SectionInstance;

public interface SectionInstanceRepository extends JpaRepository<SectionInstance,Long>
{
	
	@Query("SELECT s FROM SectionInstance s WHERE s.sectionName=:sectionName and s.user=:user and s.companyId=:companyId")
	List<SectionInstance> findSectionForUser(@Param("sectionName") String sectionName, @Param("user") String user,@Param("companyId") String companyId);
}
