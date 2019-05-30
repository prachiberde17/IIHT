package com.assessment.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.QuestionMapperInstance;

public interface QuestionMapperInstanceService {
	
	public QuestionMapperInstance findUniqueQuestionMapperInstanceForUser(String questionText, String testName, String sectionName, String user,String companyId);
	

	public List<QuestionMapperInstance> findQuestionMapperInstancesForUserForTest(String testName, String user,String companyId);
	
	public boolean canEditTest(String sectionName, String testName, String companyId);
	
	public List<QuestionMapperInstance> getInstances(String qualifier1, String companyId);
	
	public List<QuestionMapperInstance> getInstances(String qualifier1,String qualifier2, String companyId);
	
	public List<QuestionMapperInstance> getInstances(String qualifier1,String qualifier2, String qualifier3, String companyId);
	
	public List<QuestionMapperInstance> getInstances(String qualifier1,String qualifier2, String qualifier3, String qualifier4, String companyId);
	
	public List<QuestionMapperInstance> getInstances(String qualifier1,String qualifier2, String qualifier3, String qualifier4, String qualifier5, String companyId);
	
	//public List<QuestionMapperInstance> getInstances(String qualifier1,String qualifier2, String qualifier3,  String qualifier4, String qualifier5,String qualifier6, String companyId);
	
	public List<QuestionMapperInstance> getInstancesOR(String qualifier, String companyId);
	
	List<QuestionMapperInstance> findFullStackQuestionMapperInstancesForJava(String companyId);
	
	List<QuestionMapperInstance> findFullStackQuestionMapperInstancesForDotNet( String companyId);
	
	List<QuestionMapperInstance> findFullStackQuestionMapperInstancesForJavaScript(String companyId);
}
