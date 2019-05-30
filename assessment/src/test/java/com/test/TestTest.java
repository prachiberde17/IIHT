package com.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.Company;
import com.assessment.data.Question;
import com.assessment.data.QuestionMapper;
import com.assessment.data.QuestionMapperInstance;
import com.assessment.data.Section;
import com.assessment.data.SectionInstance;
import com.assessment.data.Skill;
import com.assessment.data.SkillLevel;
import com.assessment.data.User;
import com.assessment.data.UserTestSession;
import com.assessment.repositories.QuestionMapperRepository;
import com.assessment.repositories.UserTestSessionRepository;
import com.assessment.services.CompanyService;
import com.assessment.services.QuestionService;
import com.assessment.services.SectionInstanceService;
import com.assessment.services.SectionService;
import com.assessment.services.SkillService;
import com.assessment.services.TestService;
import com.assessment.services.UserService;
import com.assessment.services.UserTestSessionService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestTest {
	@Autowired
	TestService testService;
	
	@Autowired
	SkillService skillService;
	
	@Autowired
	SectionService sectionService; 
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	QuestionMapperRepository questionMapperRepository;
	
	@Autowired
	SectionInstanceService sectionInstanceService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserTestSessionService userTestSessionService;
	
	final String testName = "Java Competency Test";
	final String companyId = "IH";
	final String companyName = "IIHT";
	
//	company.setCompanyId("IH");
//	company.setCompanyName("IIHT");
	@Autowired
	UserTestSessionRepository repository;
//	
	private  User user = null;
	
	
	@Before
	public  void init() {
		user = userService.findByPrimaryKey("ashutosh.dhurve@thev2technologies.com", companyId);
	}
	
	@Test
	@Rollback(value=false)
	public void findUsersForTest(){
		List<UserTestSession> sess= repository.findByTestNamePart("john.doe2@smith.com", "image_audio_video", "IH");
		System.out.println(sess.size());
	}
	
	@Test
	@Rollback(value=false)
	public void testCreateTest() {
		com.assessment.data.Test test = new com.assessment.data.Test();
		test.setCompanyId(companyId);
		test.setCompanyName(companyName);
		test.setTestName("Java Test");
		test.setQualifier1("Java");
		test.setQualifier2("Java Basics");
		List<Skill> skills = skillService.getSkillsByCompanyId("V2");
		test.setSkills(skills);
		testService.saveOrUpdate(test);
	}
	
	@Test
	@Rollback(value=false)
	public void testCreateJavaTestWithOneSection() {
		/**
		 * Step 0 Create Test with no Questions
		 * 
		 */
		com.assessment.data.Test test = new com.assessment.data.Test();
		Company company = companyService.findByPrimaryKey(companyName, companyId);
		test.setCompanyId(company.getCompanyId());
		test.setCompanyName(company.getCompanyName());
		test.setTestName(testName);
		test.setTestTimeInMinutes(60);
		test.setQualifier1("Java");
		test.setQualifier2("Java Intermediate");
		Skill skill = skillService.findSkillByNameAndLevel("Java", SkillLevel.BASIC.getLevel(), company.getCompanyId());
		test.getSkills().add(skill);
		testService.saveOrUpdate(test);
		/**
		 * Step 1 Create Section
		 */
		Section section = new Section();
		section.setCompanyId(company.getCompanyId());
		section.setCompanyName(company.getCompanyName());
		section.setSectionName("Core Java");
		section.setSectionTimeInMinutes(60);
		section.setTestName(test.getTestName());
		sectionService.createSection(section);
		
		/**
		 * Step 2 Add Questions to Test
		 */
		List<Question> questions = questionService.findQuestionsByQualifier1(company.getCompanyId(), "Java");
		
			for(Question question : questions) {
				sectionService.addQuestionToSection(question, section, 2);
			}
		
		/**
		 * Step 3 Save Section
		 */
		//Not needed
		/**
		 * Step 4 Save Test
		 */
			//Not needed
	}
	
	@Test
	@Rollback(value=false)
	public void testCreateUserTestSession() {
		/**
		 * Step 1 - Get Test
		 */
		Company company = companyService.findByPrimaryKey("V2 Technologies", "V2");
		com.assessment.data.Test test = testService.findbyTest(testName, company.getCompanyId());
		/**
		 * Step 2 - Get Sections for Test
		 */
		List<Section> sections = sectionService.getSectionsForTest(test.getTestName(), company.getCompanyId());
		
		/**
		 * Step 3 - Get Questions for Section. Answer questions and save them along with a SectionMapperInstance
		 */
		for(Section section : sections) {
			/**
			 * Step 4 - Create Section instance 
			 */
			SectionInstance  sectionInstance = new SectionInstance();
			sectionInstance.setCompanyId(company.getCompanyId());
			sectionInstance.setCompanyName(company.getCompanyName());
			sectionInstance.setSectionName(section.getSectionName());
			sectionInstance.setTestName(test.getTestName());
			sectionInstance.setStartTime(System.currentTimeMillis());
			sectionInstance.setEndTime(System.currentTimeMillis() + 200000);
			sectionInstance.setUser(user.getEmail());
			//sectionInstanceService.sav
			List<QuestionMapper> questionMappers = questionMapperRepository.getQuestionsForSection(test.getTestName(), section.getSectionName(), company.getCompanyId());
			List<QuestionMapperInstance> questionMapperInstances = new ArrayList<>();
			for(QuestionMapper questionMapper : questionMappers) {
				/**
				 * Step 5 - Create QuestionMapper instances
				 */
				QuestionMapperInstance questionMapperInstance = new QuestionMapperInstance();
				questionMapperInstance.setQuestionMapper(questionMapper);
				questionMapperInstance.setAnswered(true);
				questionMapperInstance.setCompanyId(company.getCompanyId());
				questionMapperInstance.setCompanyName(company.getCompanyName());
				questionMapperInstance.setSectionName(section.getSectionName());
				questionMapperInstance.setTestName(test.getTestName());
				questionMapperInstance.setQuestionText(questionMapper.getQuestion().getQuestionText());
				questionMapperInstance.setUserChoices("Choice 3");
				questionMapperInstance.setUser(user.getEmail());
				questionMapperInstances.add(questionMapperInstance);
			}
			sectionInstanceService.saveSection(sectionInstance, questionMapperInstances);
		}
		
		/**
		 * Step 6 - Create UserTestSession instance for a given user
		 */
		UserTestSession userTestSession = new UserTestSession();
		userTestSession.setCompanyId(company.getCompanyId());
		userTestSession.setCompanyName(company.getCompanyName());
		
		userTestSession.setUser(user.getEmail());
		userTestSession.setTest(test);
		userTestSession.setTestName(test.getTestName());
		userTestSession.setComplete(true);
		userTestSessionService.saveOrUpdate(userTestSession);
		
	}
	
	@Test
	@Rollback(value=false)
	public void testUpdateMarksInAllTests() {
		List<com.assessment.data.Test> tests = testService.findByCompanyId("IH");
		for(com.assessment.data.Test test : tests) {
			testService.computeTestTotalMarksAndSave(test);
		}
//		com.assessment.data.Test test = testService.findTestById(1041l);
//		testService.computeTestTotalMarksAndSave(test);
	}

}
