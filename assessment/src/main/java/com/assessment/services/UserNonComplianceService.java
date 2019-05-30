package com.assessment.services;

import java.util.List;

import com.assessment.data.UserNonCompliance;

public interface UserNonComplianceService {
	
public void increment(String user,   String testName,  String companyId, Long userTestSessionId);
	
	public UserNonCompliance getById(Long id);
	
    UserNonCompliance findByPrimaryKey(String user,   String testName, String companyId, Long userTestSessionId);
	
	
	UserNonCompliance findNonCompliance( String user,   String testName, String companyId);

}
