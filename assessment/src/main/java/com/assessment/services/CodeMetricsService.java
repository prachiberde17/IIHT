package com.assessment.services;

import java.util.List;

import com.assessment.data.CodeMetrics;

public interface CodeMetricsService {

	
	public void saveOrUpdate(CodeMetrics codeMetrics);
	
	public CodeMetrics findByPrimaryKey(String companyId, String testName, String email, Long qId);
	
	public List<CodeMetrics> findCodeMetricsForTestAndQuestion(String companyId, String testName, Long qId);
	
	public List<CodeMetrics> findCodeMetricsForTest(String companyId, String testName);
	
}
