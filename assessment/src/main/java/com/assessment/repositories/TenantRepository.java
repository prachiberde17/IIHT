package com.assessment.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.Tenant;

public interface TenantRepository extends JpaRepository<Tenant,Long>{
	
	@Query("SELECT t FROM Tenant t WHERE t.companyName=:companyName and t.companyId=:companyId")
	Tenant findByPrimaryKey(@Param("companyName") String companyName, @Param("companyId") String companyId);
	
	@Query(value= "SELECT t FROM Tenant t", countQuery="SELECT COUNT(*) FROM Tenant t")
	public Page<Tenant> findAllTenants(Pageable pageable);
	
}
