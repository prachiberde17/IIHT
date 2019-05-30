
package com.assessment.services;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import com.assessment.data.Question;
import com.assessment.data.Skill;
import com.assessment.data.Test;

public interface TestService {
	
	public Test findbyTest(String testName,String companyId);
	
	List<com.assessment.data.Test> findByCompanyId( @Param("companyId") String companyId);
	
	public Page<com.assessment.data.Test> findByCompanyId(String companyId, Integer pageNumber);
	
	
	public void saveOrUpdate(Test test);
	
	public List<Test> uploadUsersFromExcelDoc(FileInputStream fileInputStream,File mappingObjectFile);
	
	public Test testCompletionMailSendTo(String testName, String companyId,boolean sentToStudent,String defaultSendTo ,String optionalSendTo);
	
	public Test findTestById(Long testId,String companyId);
	
	public Test findTestById(Long id);
	
	public List<Skill> resolveSkills(List<Skill> skills);
	
	public List<Test> searchTests(String companyId, String testName);
	public Page<com.assessment.data.Test> searchTests(String companyId, String testName, Integer pageNumber);

	public void removeTest(String companyId, Long testId) ;
	
	public Integer computeTestTotalMarksAndSave(Test test);
	
	public  List<Test> populateWithPublicUrl(List<Test> tests);
	
	public Test populate(Test test);

}
