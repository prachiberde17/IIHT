package com.assessment.services.impl;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.Company;
import com.assessment.data.Test;
import com.assessment.data.UserNonCompliance;
import com.assessment.repositories.UserNonComplianceRepository;
import com.assessment.services.CompanyService;
import com.assessment.services.TestService;
import com.assessment.services.UserNonComplianceService;
import com.assessment.services.UserTestSessionService;

@Service
@Transactional
public class UserNonComplianceServiceImpl implements UserNonComplianceService {
Logger logger = Logger.getLogger(UserNonComplianceServiceImpl.class.getName());
	
	@Autowired
	UserNonComplianceRepository userNonComplianceRepository;
	
	@Autowired
	TestService testService;
	
	@Autowired
	CompanyService companyService;

	@Override
	public void increment(String user, String testName, String companyId, Long userTestSessionId) {
		// TODO Auto-generated method stub
		Test test = testService.findbyTest(testName, companyId);
		UserNonCompliance compliance = null;
//			if(userTestSessionId != null) {
//				compliance = userNonComplianceRepository.findByPrimaryKey(user, testName, companyId, userTestSessionId);
//			}
//			else {
//				compliance = userNonComplianceRepository.findNonCompliance(user, testName, companyId);
//			}
		compliance = userNonComplianceRepository.findNonCompliance(user, testName, companyId);
		 
		if(compliance == null) {
			compliance = new UserNonCompliance();
			compliance.setUser(user);
			compliance.setTestName(testName);
			compliance.setTestId(test.getId());
			compliance.setCompanyId(companyId);
			compliance.setUserTestSessionId(userTestSessionId);
			compliance.setDate(new Date());
			compliance.setNoOfNonCompliances(1);
			Company company = companyService.findByCompanyId(companyId);
			compliance.setCompanyName(company.getCompanyName());
			compliance.setCreateDate(new Date());
		}
		else {
			compliance.setUpdateDate(new Date());
			compliance.setNoOfNonCompliances(compliance.getNoOfNonCompliances() + 1);
		}
		userNonComplianceRepository.save(compliance);
	}

	@Override
	public UserNonCompliance getById(Long id) {
		// TODO Auto-generated method stub
		return userNonComplianceRepository.findById(id).get();
	}

	@Override
	public UserNonCompliance findByPrimaryKey(String user, String testName, String companyId, Long userTestSessionId) {
		// TODO Auto-generated method stub
		return userNonComplianceRepository.findByPrimaryKey(user, testName, companyId, userTestSessionId);
	}

	@Override
	public UserNonCompliance findNonCompliance(String user, String testName, String companyId) {
		// TODO Auto-generated method stub
		return userNonComplianceRepository.findNonCompliance(user, testName, companyId);
	}

}
