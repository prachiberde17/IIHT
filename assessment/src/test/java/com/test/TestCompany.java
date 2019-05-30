package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.Company;
import com.assessment.services.CompanyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestCompany {
	@Autowired
	CompanyService companyService;
	
	@Test
	@Rollback(value=false)
	public void testCreateCompany() {
		Company company = new Company();
		company.setCompanyId("ALS2019");
		company.setCompanyName("ALS2019");
		company.setCompanyLocation("Bangalore");
		company.setSinglePointOfContactEmail("contact@thev2technologies.com");
		companyService.saveOrUpdate(company);
	}
	

}
