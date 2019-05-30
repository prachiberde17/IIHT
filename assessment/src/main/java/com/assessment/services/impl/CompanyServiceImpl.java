package com.assessment.services.impl;

import java.util.Date;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.Company;
import com.assessment.repositories.CompanyRepository;
import com.assessment.services.CompanyService;
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService{
@Autowired	
CompanyRepository companyRepository;

	@Override
	public void saveOrUpdate(Company company) {
		// TODO Auto-generated method stub
		Company company2 = findByPrimaryKey(company.getCompanyName(), company.getCompanyId());
		if(company2 == null) {
			//create
			company.setCreateDate(new Date());
			companyRepository.save(company);
		}
		else {
			//update
			Mapper mapper = new DozerBeanMapper();
			company.setId(company2.getId());
			company.setUpdateDate(new Date());
			mapper.map(company, company2);
			companyRepository.save(company2);
		}
	}

	@Override
	public Company findByPrimaryKey(String companyName, String companyId) {
		// TODO Auto-generated method stub
		return companyRepository.findByPrimaryKey(companyName, companyId);
	}

	@Override
	public Company findByCompanyName(String companyName) {
		// TODO Auto-generated method stub
		return companyRepository.findByCompanyNameIgnoreCase(companyName);
	}
	
	@Override
	public Company findByCompanyId(String companyId) {
		return companyRepository.findByCompanyId(companyId);
	}

}
