package com.assessment.services;

import org.springframework.data.domain.Page;

import com.assessment.data.Tenant;

public interface TenantService {
	
	public Tenant findTenant(String companyId, String companyName) ;
	
	public void saveOrUpdate(Tenant t);
	
	public Page<Tenant> findAllTenants(Integer pageNumber);

}
