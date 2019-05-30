package com.assessment.services;

import java.util.List;

import com.assessment.data.UserTestSession;
import com.assessment.reports.manager.AssessmentTestData;

public interface UserTestSessionService {
	
public UserTestSession findUserTestSession(String user, String testName, String companyId);
	
	public UserTestSession saveOrUpdate(UserTestSession userTestSession);
	
	public List<AssessmentTestData> getAllResultsData(String companyId);

}
