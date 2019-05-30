package com.assessment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.TestScheduler;

public interface TestSchedulerRepository extends JpaRepository<TestScheduler,Long>{
	
	@Query("SELECT t FROM TestScheduler t WHERE  t.companyId=:companyId")
	List<com.assessment.data.TestScheduler> findByCompanyId( @Param("companyId") String companyId);

}
