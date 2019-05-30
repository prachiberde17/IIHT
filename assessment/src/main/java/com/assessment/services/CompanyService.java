package com.assessment.services;

import com.assessment.data.Company;

public interface CompanyService {
	
	public void saveOrUpdate(Company company);
	
	public Company findByPrimaryKey(String companyName, String companyId);
	
	public Company findByCompanyName(String companyName);
	
	public Company findByCompanyId(String companyId);

}
