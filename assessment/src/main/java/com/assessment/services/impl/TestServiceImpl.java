package com.assessment.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.Exceptions.AssessmentGenericException;
import com.assessment.Exceptions.ErrorCodes;
import com.assessment.common.ExcelReader;
import com.assessment.common.PropertyConfig;
import com.assessment.data.Section;
import com.assessment.data.Skill;
import com.assessment.data.Test;
import com.assessment.repositories.QuestionMapperRepository;
import com.assessment.repositories.TestRepository;
import com.assessment.services.SectionService;
import com.assessment.services.SkillService;
import com.assessment.services.TestService;

@Service
@Transactional
public class TestServiceImpl implements TestService {
	
	Logger logger = Logger.getLogger(TestServiceImpl.class.getName());

	@Autowired
	TestRepository testRepository;

	@Autowired
	SkillService skillService;
	
	@Autowired
	SectionService sectionService;
	
	@Autowired
	QuestionMapperRepository questionMapperRepository;
	
	@Autowired
	PropertyConfig propertyConfig;
	
	public String getPublicUrlForTest(Long testId, String companyId) {
		// String after = "&testId="+URLEncoder.encode(Base64.getEncoder().encodeToString(testId.toString().getBytes())+"&companyId="+URLEncoder.encode(Base64.getEncoder().encodeToString(companyId.getBytes())));
		  String after = "&testId="+URLEncoder.encode(testId.toString())+"&companyId="+URLEncoder.encode(companyId);
			 String url = propertyConfig.getBaseUrl()+"publicTest?"+after;
			 return url;
	  }
	
	public  List<Test> populateWithPublicUrl(List<Test> tests){
		for(Test test : tests) {
			test = populate(test);
		}
		return tests;
	}
	
	public Test populate(Test test) {
		if(test == null) {
			return null;
		}
		String url = getPublicUrlForTest(test.getId(), test.getCompanyId());
		test.setPublicUrl(url);
		return test;
	}
	

	@Override
	public Test findbyTest(String testName, String companyId) {
		return populate(testRepository.findByPrimaryKey(testName, companyId));
	}

	@Override
	public void saveOrUpdate(Test test) {
		test.setSkills(resolveSkills(test.getSkills()));
		//Test test2 = findbyTest(test.getTestName(), test.getCompanyId());
		Test test2 = null;
			if(test.getId() != null) {
				test2 = testRepository.getOne(test.getId());
				
			}
		if (test2 == null) {
			test.setCreateDate(new Date());
			testRepository.save(test);
		} else {
			test.setId(test2.getId());
			test.setCreateDate(test2.getCreateDate());
			test.setUpdateDate(new Date());
			Mapper mapper = new DozerBeanMapper();
			mapper.map(test, test2);
			test2.setSkills(test.getSkills());
			testRepository.save(test2);

		}
	}
	


	public List<Skill> resolveSkills(List<Skill> skills) {

		for (Skill skill : skills) {
			skill = skillService.findSkillByNameAndLevel(skill.getSkillName(),
					skill.getSkillLevel().getLevel(), skill.getCompanyId());
		}
		return skills;
	}

	@Override
	public List<Test> uploadUsersFromExcelDoc(FileInputStream fileInputStream,
			File mappingObjectFile) {
		logger.info("START---->uploadUsersFromExcelDoc");
		List<Test> tests = null;
		try {
			tests = ExcelReader.parseExcelFileToBeans(fileInputStream,
					mappingObjectFile);
			if (tests.size()>0) {
				for (Test test : tests) {
					saveOrUpdate(test);
				}
			}
			logger.info("END---->uploadUsersFromExcelDoc");
		} catch (Exception e) {
			throw new AssessmentGenericException(
					ErrorCodes.ERROR_WHILE_PARSING_EXCEL_DOCUMENT);
		}
		return tests;
	}

	@Override
	public Test testCompletionMailSendTo(String testName, String companyId, boolean sentToStudent,
			String defaultSendTo, String optionalSendTo) {
		Test test=null;
		//to check and configure to whom the test report mail send to
		if((testName!=null && !testName.isEmpty()) && (companyId!=null && !companyId.isEmpty()))
		{
			test=findbyTest(testName,companyId);
			if(test!=null)
			{
				test.setDefaultSendTo(defaultSendTo);
				test.setOptionalSendTo(optionalSendTo);
				test.setSentToStudent(sentToStudent);
				saveOrUpdate(test);
			}
			
		}
		return populate(test);
	}
	


	@Override
	public List<Test> findByCompanyId(String companyId) {
		// TODO Auto-generated method stub
		return populateWithPublicUrl(testRepository.findByCompanyId(companyId));
	}

	@Override
	public List<Test> searchTests(String companyId, String testName) {
		// TODO Auto-generated method stub
		return populateWithPublicUrl(testRepository.searchTests(companyId, testName));
	}

	@Override
	public void removeTest(String companyId, Long testId) {
		// TODO Auto-generated method stub
		Test test = findTestById(testId);
		List<Section> sections = sectionService.getSectionsForTest(test.getTestName(), test.getCompanyId());
			for(Section section : sections) {
				sectionService.removeSection(section);
			}
		testRepository.deleteById(testId);
	}

	@Override
	public Test findTestById(Long id) {
		// TODO Auto-generated method stub
		return populate(testRepository.findById(id).get());
	}

	@Override
	public Test findTestById(Long testId, String companyId) {
		return populate(testRepository.findTestById(testId, companyId));
	}

	@Override
	public Page<Test> findByCompanyId(String companyId, Integer pageNumber) {
		// TODO Auto-generated method stub
		return testRepository.findByCompanyId(companyId, PageRequest.of(pageNumber, 5));
	}

	@Override
	public Page<Test> searchTests(String companyId, String testName, Integer pageNumber) {
		// TODO Auto-generated method stub
		return testRepository.searchTests(companyId, testName, PageRequest.of(pageNumber, 5));
	}
	
	@Override
	public Integer computeTestTotalMarksAndSave(Test test) {
		Test test2 = null;
		if(test.getId() != null) {
			test2 = testRepository.getOne(test.getId());
			
		}
		else {
			throw new AssessmentGenericException("NO_TEST_ID_PRESENT");
		}
		
		List<Section> sections = sectionService.getSectionsForTest(test.getTestName(), test.getCompanyId());
		Integer totalMarks = 0;
		for(Section section : sections) {
			Integer percent = (section.getPercentQuestionsAsked() == null || section.getPercentQuestionsAsked() == 0) ? 100:section.getPercentQuestionsAsked();
			Integer noOfQuestions = questionMapperRepository.findCountQuestionMapperForTestAndSection(section.getSectionName().trim(), test.getTestName().trim(), test.getCompanyId().trim());
			Integer sectionMarks = (Integer)((noOfQuestions * percent)/100);
			totalMarks += sectionMarks;
		}
		test2.setTotalMarks(totalMarks);
		testRepository.save(test2);
		return totalMarks;
	}
}
