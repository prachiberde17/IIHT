package com.assessment.services.impl;

import java.util.Date;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.Tenant;
import com.assessment.repositories.TenantRepository;
import com.assessment.services.TenantService;
@Service
@Transactional
public class TenantServiceImpl implements TenantService{
@Autowired
TenantRepository tenantRepository;
	
	public Tenant findTenant(String companyId, String companyName) {
		return tenantRepository.findByPrimaryKey(companyName, companyId);
	}
	
	public void saveOrUpdate(Tenant t) {
		Tenant t2 = findTenant(t.getCompanyId(), t.getCompanyName());
		if(t2 == null) {
			t.setCreateDate(new Date());
			tenantRepository.save(t);
		}
		else {
			Mapper mapper = new DozerBeanMapper();
			t.setId(t2.getId());
			mapper.map(t, t2);
			t2.setUpdateDate(new Date());
			tenantRepository.save(t2);
		}
	}
	
	public Page<Tenant> findAllTenants(Integer pageNumber){
		return tenantRepository.findAllTenants(PageRequest.of(pageNumber, 10));
	}
}
