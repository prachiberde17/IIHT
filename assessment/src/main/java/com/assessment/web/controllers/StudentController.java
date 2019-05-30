package com.assessment.web.controllers;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.io.FileUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.common.CompileData;
import com.assessment.common.CompileOutput;
import com.assessment.common.LanguageCodes;
import com.assessment.common.PropertyConfig;
import com.assessment.common.QuestionSequence;
import com.assessment.common.SectionSequence;
import com.assessment.common.util.EmailGenericMessageThread;
import com.assessment.data.FullStackOptions;
import com.assessment.data.Question;
import com.assessment.data.QuestionMapper;
import com.assessment.data.QuestionMapperInstance;
import com.assessment.data.QuestionType;
import com.assessment.data.Section;
import com.assessment.data.SectionInstance;
import com.assessment.data.Test;
import com.assessment.data.User;
import com.assessment.data.UserNonCompliance;
import com.assessment.data.UserTestSession;
import com.assessment.eclipseche.config.response.WorkspaceResponse;
import com.assessment.eclipseche.services.EclipseCheService;
import com.assessment.repositories.QuestionMapperInstanceRepository;
import com.assessment.services.CompanyService;
import com.assessment.services.QuestionMapperInstanceService;
import com.assessment.services.QuestionMapperService;
import com.assessment.services.SectionInstanceService;
import com.assessment.services.SectionService;
import com.assessment.services.StudentService;
import com.assessment.services.TestService;
import com.assessment.services.UserNonComplianceService;
import com.assessment.services.UserService;
import com.assessment.services.UserTestSessionService;
import com.assessment.services.impl.CompilerService;
import com.assessment.services.impl.ReportsService;
import com.assessment.web.dto.QuestionInstanceDto;
import com.assessment.web.dto.SectionInstanceDto;
import com.assessment.web.forms.StudentTestForm;


@Controller
public class StudentController {
	
	//Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TestService testService;
	
	@Autowired
	SectionService sectionService;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	QuestionMapperService questionMapperService;
	
	@Autowired
	SectionInstanceService sectionInstanceService;
	
	@Autowired
	QuestionMapperInstanceService questionMapperInstaceService;
	
	@Autowired
	UserTestSessionService userTestSessionService;
	
	@Autowired
	PropertyConfig propertyConfig;
	
	@Autowired
	UserNonComplianceService userNonComplianceService;
	
	@Autowired
	CompilerService compiler;
	
	@Autowired
	ReportsService reportsService;
	@Autowired
	QuestionMapperInstanceRepository  questionMapperInstanceRep;
	
	Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	 @RequestMapping(value = "/startTestSession", method = RequestMethod.GET)
	  public ModelAndView studentHome(@RequestParam(required=false) String sharedDirect,@RequestParam(required=false) String inviteSent, @RequestParam String userId, @RequestParam String companyId, @RequestParam String testId, HttpServletRequest request, HttpServletResponse response) {
		 StudentTestForm studentTest= new StudentTestForm();
		 userId=decodeUserId((String)request.getParameter("userId"));
		 companyId=(String)request.getParameter("companyId");
		 ModelAndView model= new ModelAndView("intro");
		 SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
	     String time = localDateFormat.format(new Date());
	     studentTest.setCurrentTime(time);
	     User userDetails=userService.findByPrimaryKey(userId, companyId);
	     request.getSession().setAttribute("user", userDetails);
	     	if(inviteSent != null) {
	     		Long timeInvite = Long.valueOf(inviteSent);
	     		Date date = new Date(timeInvite);
	     		studentTest.setTestInviteSent(date);
	     	}
	     	
	     	if(sharedDirect != null && sharedDirect.equalsIgnoreCase("yes")) {
	     		studentTest.setSharedDirect(true);
	     	}
	     
		// User userDetails=userService.findUserById(Long.parseLong(userId),companyId);
		if(userDetails!=null)
		{
			studentTest.setUserName(userDetails.getFirstName()+" "+userDetails.getLastName());
			studentTest.setEmailId(userDetails.getEmail());
			testId=(String)request.getParameter("testId");
			Test testDetails=testService.findTestById(Long.parseLong(testId),companyId);
			/**
			 * Add code for image logo mapping
			 */
			if(testDetails.getTestType() == null){
				studentTest.setTechLogo("https://yaksha.online/images/Java.png");
			}
			
			if(testDetails.getTestType().equals("Java")){
				studentTest.setTechLogo("https://yaksha.online/images/Java.png");
			}
			else if(testDetails.getTestType().equals("Microsoft technologies")){
				studentTest.setTechLogo("https://yaksha.online/images/Microsoft.png");
			}
			else if(testDetails.getTestType().equals("C/C++")){
				studentTest.setTechLogo("https://yaksha.online/images/C.png");
			}
			else if(testDetails.getTestType().equals("Python")){
				studentTest.setTechLogo("https://yaksha.online/images/Python.png");
			}
			else if(testDetails.getTestType().equals("Python")){
				studentTest.setTechLogo("https://yaksha.online/images/C.png");
			}
			else if(testDetails.getTestType().equals("General Knowledge")){
				studentTest.setTechLogo("https://yaksha.online/images/GK.png");
			}
			else if(testDetails.getTestType().equals("Composite Test")){
				studentTest.setTechLogo("https://yaksha.online/images/All_In_1.png");
			}
			else{
				studentTest.setTechLogo("https://yaksha.online/images/All_In_1.png");
			}
			//
			
			/**
			 * End code for image logo mapping
			 */
			
			
			User createTestUser = userService.findByPrimaryKey(testDetails.getCreatedBy(), companyId);
			studentTest.setTestCreatorName(createTestUser.getFirstName()+" "+createTestUser.getLastName());
			request.getSession().setAttribute("test", testDetails);
			//List<Section> sections = sectionService.getSectionsForTest(testDetails.getTestName(),companyId);
			studentTest.setTestCreatedBy(testDetails.getCreatedBy());
			
				if(testDetails.getTotalMarks() == null){
					model= new ModelAndView("studentMessageTest_WithNoQs");
					model.addObject("studentTestForm", studentTest);
					return model;
				}
			int questionsCountInAllSections=testDetails.getTotalMarks();
			int allQuestionsTimeInMin=0;
//			if(sections.size()>0)
//			{
//				for (Section section : sections) {
//					List<QuestionMapper> questionMappers = questionMapperService.getQuestionsForSection(testDetails.getTestName(), section.getSectionName(), companyId);
//					questionsCountInAllSections+=questionMappers.size();
//					//allQuestionsTimeInMin+=section.getSectionTimeInMinutes()==null?30:section.getSectionTimeInMinutes();
//				}
//			}
//			
			
			if(testDetails.getTestTimeInMinutes() == null || testDetails.getTestTimeInMinutes() == 0) {
				allQuestionsTimeInMin = 45;
			}
			else {
				allQuestionsTimeInMin = testDetails.getTestTimeInMinutes();
			}
			
			studentTest.setCompanyId(companyId);
			studentTest.setEmailId(userDetails.getEmail());	
			studentTest.setTestName(testDetails.getTestName());
			studentTest.setTotalQuestions(questionsCountInAllSections);
			studentTest.setDuration(allQuestionsTimeInMin);
			studentTest.setPublishedDate(testDetails.getCreateDate());
			studentTest.setFirstName(userDetails.getFirstName());
			studentTest.setLastName(userDetails.getLastName());
			studentTest.setTestCreatedBy(testDetails.getCreatedBy());
			String pattern = "dd-MM-yyyy HH:mm:ss";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			studentTest.setTestCreationDate(simpleDateFormat.format(testDetails.getCreateDate()));
			//Integer noOfAttempts = userTestSessionService.findNoOfAttempsByUserForTest(userDetails.getEmail(), testDetails.getTestName(), userDetails.getCompanyId());
		//	studentTest.setNoOfAttempts(noOfAttempts == null || noOfAttempts == 0 ?1:noOfAttempts +1);
			UserTestSession session = userTestSessionService.findUserTestSession(userDetails.getEmail(), testDetails.getTestName(), userDetails.getCompanyId());
			if(session != null && session.getComplete()) {
				
				studentTest.setLastUpdated(simpleDateFormat.format(session.getUpdateDate() == null?session.getCreateDate():session.getUpdateDate()));
				studentTest.setNoOfAttempts(session.getNoOfAttempts());
				model= new ModelAndView("studentNoTest");
				model.addObject("studentTestForm", studentTest);
				return model;
			}
			else if(session != null && !session.getComplete()) {
				studentTest.setNoOfAttempts(session.getNoOfAttempts());
			}
			else if(session == null){
				studentTest.setNoOfAttempts(1);
			}
			
		}
		model.addObject("studentTestForm", studentTest);
		request.getSession().setAttribute("studentTestForm", studentTest);
		model.addObject("userName", userDetails.getFirstName()+" "+userDetails.getLastName());
		putMiscellaneousInfoInModel(model, request);
		return model;
	  }
	 
	 private void setTimeInCounter(HttpServletRequest request, Long timeElapsed) {
		 StudentTestForm studentTest = (StudentTestForm) request.getSession().getAttribute("studentTestForm");
		 studentTest.setTotalTestTimeElapsedInSeconds(studentTest.getTotalTestTimeElapsedInSeconds() + timeElapsed);
	 }
	 
	 private void putMiscellaneousInfoInModel(ModelAndView model, HttpServletRequest request) {
		 StudentTestForm studentTest = (StudentTestForm) request.getSession().getAttribute("studentTestForm");
		 model.addObject("studentTestForm", studentTest);
	 }
	
	 
	 @RequestMapping(value = "/studentJourney", method = RequestMethod.POST)
	 public ModelAndView studentStartExam(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("studentTestForm") StudentTestForm studentForm) throws Exception {
		 ModelAndView model= new ModelAndView("test");
		 User user = (User) request.getSession().getAttribute("user");
		 Test test = (Test) request.getSession().getAttribute("test");
		 request.getSession().setAttribute("testStartDate", new Date());
		 List<Section> sections = sectionService.getSectionsForTest(test.getTestName(),test.getCompanyId());
		
		 int count = 0;
		 List<SectionInstanceDto> sectionInstanceDtos = new ArrayList<>();
		 int totalQuestions = test.getTotalMarks();
		 for (Section section : sections) {
			 //from the sections creating an instance of section mapping with test
			 SectionInstanceDto sectionInstanceDto = new SectionInstanceDto();
			 sectionInstanceDtos.add(sectionInstanceDto);
			// sectionInstanceDto.setCurrent(current);
			 if(count == 0) {
				 sectionInstanceDto.setCurrent(true);
				 
				 List<QuestionMapper> questionMappers = questionMapperService.getQuestionsForSection(test.getTestName(), section.getSectionName(), user.getCompanyId());
				 Collections.shuffle(questionMappers);
				 List<QuestionMapper> questionMappersActual = questionMappers.subList(0, section.getNoOfQuestionsToBeAsked());
					List<QuestionInstanceDto> questionMapperInstances=new ArrayList<QuestionInstanceDto>();
					int pos = 0;
					for (QuestionMapper questionMapper : questionMappersActual) {
						//creating the instances of question mapper instance entity
						QuestionInstanceDto questionInstanceDto= new QuestionInstanceDto();
						pos++;
						questionInstanceDto.setPosition(pos);
						QuestionMapperInstance questionMapperInstance = new QuestionMapperInstance();
						questionInstanceDto.setQuestionMapperInstance(questionMapperInstance);
						questionMapperInstance.setQuestionMapper(questionMapper);
						questionMapperInstances.add(questionInstanceDto);
						if(questionMapper.getQuestion().getQuestionType() != null && questionMapper.getQuestion().getQuestionType().getType().equals(QuestionType.CODING.getType())){
							questionInstanceDto.setCode(questionMapper.getQuestion().getInputCode());
						}
					}
					sectionInstanceDto.setFirst(true);
					sectionInstanceDto.setQuestionInstanceDtos(questionMapperInstances);
					
					/**
					 * For only 1 Q and 1 section..adding this
					 */
					if(sections.size() == 1){
						if(questionMappersActual.size() == 1){
							sectionInstanceDto.setLast(true);
						}
					}
					
					/**
					 * End For only 1 Q and 1 section..adding this
					 */
					
					
					model.addObject("currentSection", sectionInstanceDto);
					model.addObject("currentQuestion", questionMapperInstances.get(0));
					request.getSession().setAttribute("currentSection", sectionInstanceDto);
					 /**
					  * Get the fullstack for Q if type is full stack.
					  * 
					  */
					 if(!questionMapperInstances.get(0).getQuestionMapperInstance().getQuestionMapper().getQuestion().getFullstack().getStack().equals(FullStackOptions.NONE.getStack())){
						 setWorkspaceIDEForFullStackQ(request, questionMapperInstances.get(0));
					 }
					 /**
					  * End full stack check
					  */
			 }
			 sectionInstanceDto.setNoOfQuestions(section.getNoOfQuestionsToBeAsked());
			 sectionInstanceDto.setSection(section);
			 count++;
			 //fetch the questions based on the associated sections
				
				
		}
		
		 	request.getSession().setAttribute("sectionInstanceDtos", sectionInstanceDtos);
		 	putMiscellaneousInfoInModel(model, request);
		 	model.addObject("sectionInstanceDtos", sectionInstanceDtos);
		 	model.addObject("percentage", "0");
		 	model.addObject("totalQuestions", ""+totalQuestions);
		 	model.addObject("noAnswered", "0");
		 	model.addObject("confidenceFlag", test.getConsiderConfidence());
		    return model;
	 }
	 
	 private ModelAndView processPercentages(ModelAndView model, List<SectionInstanceDto> sectionInstanceDtos, int noOfQs) {
		 int noOfQuestionsNotAnswered = 0;
		 int noOfQuestions = noOfQs;
		 for(SectionInstanceDto dto: sectionInstanceDtos) {
			 if(dto.getQuestionInstanceDtos().size() == 0) {
				 noOfQuestionsNotAnswered = noOfQuestionsNotAnswered + dto.getNoOfQuestions();//making sure no of qs not answered are considered for the test when it begins or lese the progress would be wrong
			 }
			 for(QuestionInstanceDto questionInstanceDto : dto.getQuestionInstanceDtos()) {
				if( questionInstanceDto.getOne() == false && questionInstanceDto.getTwo() == false && questionInstanceDto.getThree() == false && questionInstanceDto.getFour() == false && questionInstanceDto.getFive() == false && questionInstanceDto.getSix() == false) {
					noOfQuestionsNotAnswered ++;
				}
				//noOfQuestions++;
			 }
		 }
		 float per = (100 * (noOfQuestions - noOfQuestionsNotAnswered))/noOfQuestions;
		 DecimalFormat df = new DecimalFormat();
		 df.setMaximumFractionDigits(2);
		 String percentage = df.format(per);
		 model.addObject("percentage", percentage);
		 	model.addObject("totalQuestions", ""+noOfQuestions);
		 	model.addObject("noAnswered", ""+(noOfQuestions - noOfQuestionsNotAnswered) );
		 return model;
	 }
	 
	 
	 @RequestMapping(value = "/changeSection", method = RequestMethod.GET)
	  public ModelAndView changeSection(@RequestParam String sectionName, @RequestParam String timeCounter, HttpServletRequest request, HttpServletResponse response,@ModelAttribute("studentTestForm") StudentTestForm studentForm) {
		 ModelAndView model= new ModelAndView("test");
		 User user = (User) request.getSession().getAttribute("user");
		 Test test = (Test) request.getSession().getAttribute("test");
		 
		 List<SectionInstanceDto> sectionInstanceDtos = (List<SectionInstanceDto>) request.getSession().getAttribute("sectionInstanceDtos");
		 
		 int count = 0;
		 for(SectionInstanceDto sectionInstanceDto :  sectionInstanceDtos) {
			 count++;
			 sectionInstanceDto.setCurrent(false);
			// sectionInstanceDto.getQuestionInstanceDtos().clear();
			 if(sectionInstanceDto.getSection().getSectionName().equals(sectionName)) {
				 	if(count == 1) {
				 		sectionInstanceDto.setFirst(true);
				 		sectionInstanceDto.setLast(false);
				 	}
				 	
				 	if(count ==sectionInstanceDtos.size() ) {
						 sectionInstanceDto.setFirst(false);
						 sectionInstanceDto.setLast(false);
					 }
				 	
				 	if(count == 1 && sectionInstanceDtos.size() == 1){
				 		sectionInstanceDto.setFirst(true);
						 sectionInstanceDto.setLast(true);
				 	}
				 sectionInstanceDto.setCurrent(true);
				 sectionInstanceDto = populateWithQuestions(sectionInstanceDto, test.getTestName(),sectionInstanceDto.getSection().getSectionName(), user.getCompanyId());
				 model.addObject("currentSection", sectionInstanceDto);
				 QuestionInstanceDto currentQuestion = sectionInstanceDto.getQuestionInstanceDtos().get(0);
				// if(sectionInstanceDto.getQuestionInstanceDtos().size() == 1){
				 if(sectionInstanceDto.getQuestionInstanceDtos().size() == 1 && count == sectionInstanceDtos.size()){
					 sectionInstanceDto.setLast(true);
				 }
				 
				 	if(currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getQuestionType() != null && currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getQuestionType().getType().equals(QuestionType.CODING.getType())){
				 		if(currentQuestion.getCode() == null || currentQuestion.getCode().trim().length() == 0){
				 			currentQuestion.setCode(currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getInputCode());
				 		}
				 		
				 	}
				 model.addObject("currentQuestion", sectionInstanceDto.getQuestionInstanceDtos().get(0));
				 request.getSession().setAttribute("currentSection", sectionInstanceDto);
			 }
		 }
		 
		 
		 	request.getSession().setAttribute("sectionInstanceDtos", sectionInstanceDtos);
		 	model.addObject("sectionInstanceDtos", sectionInstanceDtos);
		 	putMiscellaneousInfoInModel(model, request);
		 	setTimeInCounter(request, Long.valueOf(timeCounter));
		 	processPercentages(model, sectionInstanceDtos, test.getTotalMarks());
		    return model;
	 }
	 
	 private SectionInstanceDto populateWithQuestions(SectionInstanceDto sectionInstanceDto, String testName, String sectionName, String companyId) {
		 	if(sectionInstanceDto.getQuestionInstanceDtos().size() > 0) {
		 		//its already populated
		 		return sectionInstanceDto;
		 	}
		 List<QuestionMapper> questionMappers = questionMapperService.getQuestionsForSection(testName, sectionName, companyId);
		 Collections.shuffle(questionMappers);
		 List<QuestionMapper> questionMappersActual = questionMappers.subList(0, sectionInstanceDto.getNoOfQuestions());
		 int pos = 0;
		 for (QuestionMapper questionMapper : questionMappersActual) {
				//creating the instances of question mapper instance entity
				QuestionInstanceDto questionInstanceDto= new QuestionInstanceDto();
				pos++;
				questionInstanceDto.setPosition(pos);
				QuestionMapperInstance questionMapperInstance = new QuestionMapperInstance();
				questionInstanceDto.setQuestionMapperInstance(questionMapperInstance);
				questionMapperInstance.setQuestionMapper(questionMapper);
				sectionInstanceDto.getQuestionInstanceDtos().add(questionInstanceDto);
			}	
		return sectionInstanceDto;
	 }
	 
	 private void setAnswers(HttpServletRequest request, SectionInstanceDto currentSection, QuestionInstanceDto currentQuestion, String questionMapperId) {
		 /**
		  * Get the corresponding section from session object
		  */
		 List<SectionInstanceDto> sectionInstanceDtos = (List<SectionInstanceDto>) request.getSession().getAttribute("sectionInstanceDtos");
		 	for(SectionInstanceDto sectionInstanceDto : sectionInstanceDtos) {
		 		if(sectionInstanceDto.getSection().getSectionName().equals(currentSection.getSection().getSectionName())) {
		 			
		 			
		 			/**
		 			 * Get the corresponding Question from section from the session object
		 			 */
		 			for(QuestionInstanceDto questionInstanceDto : currentSection.getQuestionInstanceDtos()) {
		 				
		 				
		 				
		 				
		 				if(questionInstanceDto.getQuestionMapperInstance().getQuestionMapper().getId().equals(Long.valueOf(questionMapperId))) {
		 					/**	
				 			 * Add code for evaluating coding engine Q
				 			 */
		 					if(questionInstanceDto.getQuestionMapperInstance().getQuestionMapper().getQuestion().getQuestionType() == null){
		 						questionInstanceDto.getQuestionMapperInstance().getQuestionMapper().getQuestion().setQuestionType(QuestionType.MCQ);
		 					}
				 			String type = questionInstanceDto.getQuestionMapperInstance().getQuestionMapper().getQuestion().getQuestionType().getType();
				 			Question q = questionInstanceDto.getQuestionMapperInstance().getQuestionMapper().getQuestion();
				 			if(type != null && type.equals(QuestionType.CODING.getType())){
				 				String lang = LanguageCodes.getLanguageCode(questionInstanceDto.getQuestionMapperInstance().getQuestionMapper().getQuestion().getLanguage().getLanguage());
				 				logger.info("compiling lang is "+lang);
				 				System.out.println("compiling lang is "+lang);
				 				CompileData compileData = new CompileData();
				 				compileData.setLanguage(lang);
				 				String code = currentQuestion.getCode();
				 				code = code.replaceAll("\\\\n", System.lineSeparator());
				 				code = code.replaceAll("\\\\t", "   ");
				 				compileData.setCode(code);
				 				compileData.setStdin(q.getHiddenInputNegative());
				 				CompileOutput op = compiler.compile(compileData);
				 				op.setOutput((op.getOutput() == null)?op.getOutput():op.getOutput().replaceAll("\n", ""));
				 				currentQuestion.setOutput(op.getOutput() == null?"wrong":op.getOutput());
				 				questionInstanceDto.setCode(currentQuestion.getCode());
				 				questionInstanceDto.setOutput(op.getOutput() == null?"wrong":op.getOutput());
				 				questionInstanceDto.getQuestionMapperInstance().setCodeByUser(currentQuestion.getCode().replaceAll("\r", ""));
				 				questionInstanceDto.getQuestionMapperInstance().setCodingOuputBySystemTestCase(op.getOutput() == null?"wrong":op.getOutput());
				 				sectionInstanceDto.setQuestionInstanceDtos(currentSection.getQuestionInstanceDtos());
				 				break;
				 			}
				 			/**
				 			 * End Add code for evaluating coding engine Q
				 			 */
		 					
		 					
		 					String userChoices = "";
		 					if(currentQuestion.getOne()) {
		 						userChoices = "Choice 1";
		 						questionInstanceDto.setOne(true);
		 					}
		 					else {
		 						questionInstanceDto.setOne(false);
		 					}
		 					
		 					if(currentQuestion.getTwo()) {
		 						if(userChoices.length() > 0) {
		 							userChoices += "-Choice 2";
		 							questionInstanceDto.setTwo(true);
		 						}
		 						else {
		 							userChoices = "Choice 2";
		 							questionInstanceDto.setTwo(true);
		 						}
		 					}
		 					else {
		 						questionInstanceDto.setTwo(false);
		 					}
		 					
		 					if(currentQuestion.getThree()) {
		 						if(userChoices.length() > 0) {
		 							userChoices += "-Choice 3";
		 							questionInstanceDto.setThree(true);
		 						}
		 						else {
		 							userChoices = "Choice 3";
		 							questionInstanceDto.setThree(true);
		 						}
		 					}
		 					else {
		 						questionInstanceDto.setThree(false);
		 					}
		 					
		 					if(currentQuestion.getFour()) {
		 						if(userChoices.length() > 0) {
		 							userChoices += "-Choice 4";
		 							questionInstanceDto.setFour(true);
		 						}
		 						else {
		 							userChoices = "Choice 4";
		 							questionInstanceDto.setFour(true);
		 						}
		 					}
		 					else {
		 						questionInstanceDto.setFour(false);
		 					}
		 					
		 					
		 					if(currentQuestion.getFive()) {
		 						if(userChoices.length() > 0) {
		 							userChoices += "-Choice 5";
		 							questionInstanceDto.setFive(true);
		 						}
		 						else {
		 							userChoices = "Choice 5";
		 							questionInstanceDto.setFive(true);
		 						}
		 					}
		 					else {
		 						questionInstanceDto.setFive(false);
		 					}
		 					
		 					if(currentQuestion.getSix()) {
		 						if(userChoices.length() > 0) {
		 							userChoices += "-Choice 6";
		 							questionInstanceDto.setSix(true);
		 						}
		 						else {
		 							userChoices = "Choice 6";
		 							questionInstanceDto.setSix(true);
		 						}
		 					}
		 					else {
		 						questionInstanceDto.setSix(false);
		 					}
		 					
		 					
		 					questionInstanceDto.getQuestionMapperInstance().setUserChoices(userChoices);
		 					/**
		 					 * Confidence based assessment
		 					 */
		 					questionInstanceDto.getQuestionMapperInstance().setConfidence(currentQuestion.getConfidence());
		 					questionInstanceDto.setConfidence(currentQuestion.getConfidence());
		 					sectionInstanceDto.setQuestionInstanceDtos(currentSection.getQuestionInstanceDtos());
		 					break;
		 				}
		 			}
		 		}
		 	}
	 }
	 
	 private void saveSection(SectionInstanceDto currentSection, HttpServletRequest request) {
		 User user = (User) request.getSession().getAttribute("user");
		 Test test = (Test) request.getSession().getAttribute("test");
		 List<SectionInstanceDto> sectionInstanceDtos = (List<SectionInstanceDto>) request.getSession().getAttribute("sectionInstanceDtos");
		 	for(SectionInstanceDto sectionInstanceDto : sectionInstanceDtos) {
		 		int totalSectionQuestions = sectionInstanceDto.getQuestionInstanceDtos().size() ;
				int correctAnswersPerSection = 0;
				int noOfQuestionsNotAnswered = 0;
		 		if(sectionInstanceDto.getSection().getSectionName().equals(currentSection.getSection().getSectionName())) {
		 			SectionInstance sectionInstance = new SectionInstance();
		 			sectionInstance.setCompanyId(user.getCompanyId());
		 			sectionInstance.setCompanyName(user.getCompanyName());
		 			sectionInstance.setTestName(test.getTestName());
		 			sectionInstance.setSectionName(sectionInstanceDto.getSection().getSectionName());
		 			sectionInstance.setStartTime(System.currentTimeMillis());
					sectionInstance.setEndTime(System.currentTimeMillis() + 200000);
					sectionInstance.setUser(user.getEmail());
					List<QuestionMapperInstance> questionMapperInstances = new ArrayList<>();
					
					for(QuestionInstanceDto questionInstanceDto : sectionInstanceDto.getQuestionInstanceDtos() ) {
						questionInstanceDto.getQuestionMapperInstance().setCompanyId(user.getCompanyId());
						questionInstanceDto.getQuestionMapperInstance().setCompanyName(user.getCompanyName());
						questionInstanceDto.getQuestionMapperInstance().setTestName(test.getTestName());
						questionInstanceDto.getQuestionMapperInstance().setSectionName(sectionInstanceDto.getSection().getSectionName());
						questionInstanceDto.getQuestionMapperInstance().setUser(user.getEmail());
						questionMapperInstances.add(questionInstanceDto.getQuestionMapperInstance());
						if(questionInstanceDto.getQuestionMapperInstance().getCorrect()) {
							correctAnswersPerSection ++;
						}
						
						if(!questionInstanceDto.getQuestionMapperInstance().getAnswered()) {
							noOfQuestionsNotAnswered ++;
						}
					}
					
					sectionInstance.setResults("total-"+totalSectionQuestions+",correct-"+correctAnswersPerSection);
					sectionInstance.setNoOfQuestionsNotAnswered(noOfQuestionsNotAnswered);
		 			sectionInstanceService.saveSection(sectionInstance, questionMapperInstances);
		 			
		 			sectionInstanceDto.setNoOfQuestions(totalSectionQuestions);
		 			sectionInstanceDto.setTotalCorrectAnswers(correctAnswersPerSection);
		 			sectionInstanceDto.setNoOfQuestionsNotAnswered(noOfQuestionsNotAnswered);
		 		}
		 	}
	 }
	 
	 private void setValuesInSession(SectionInstanceDto currentSection, List<SectionInstanceDto> sectionInstanceDtos) {
		 for(SectionInstanceDto dto : sectionInstanceDtos) {
			 if(dto.getSection().getSectionName().equals(currentSection.getSection().getSectionName())) {
				 Mapper mapper = new DozerBeanMapper();
				 mapper.map(currentSection, dto);
				 break;
			 }
		 }
	 }
	 
	 @RequestMapping(value = "/nextQuestion", method = RequestMethod.POST)
	  public ModelAndView nextQuestion(@RequestParam String questionId, @RequestParam String timeCounter,HttpServletRequest request, HttpServletResponse response,@ModelAttribute("currentQuestion") QuestionInstanceDto currentQuestion) throws Exception {
		 ModelAndView model= new ModelAndView("test");
		 User user = (User) request.getSession().getAttribute("user");
		 Test test = (Test) request.getSession().getAttribute("test");
		 List<SectionInstanceDto> sectionInstanceDtos = (List<SectionInstanceDto>) request.getSession().getAttribute("sectionInstanceDtos");
		 model.addObject("sectionInstanceDtos", sectionInstanceDtos);
		 SectionInstanceDto currentSection = (SectionInstanceDto) request.getSession().getAttribute("currentSection");
		//just in case a Q is of coding type value that comes from jsp has \r characters.so removng them so they can be rendered next time
		 	if(currentQuestion.getCode() != null){
		 		 currentQuestion.setCode(currentQuestion.getCode().replaceAll("\r", ""));
		 		 String rep = "\\\\n";
		 		 String rept = "\\\\t";
		 		currentQuestion.setCode(currentQuestion.getCode().replaceAll("\n", rep));
		 		currentQuestion.setCode(currentQuestion.getCode().replaceAll("\t", rept));
		 	}
		
		 setAnswers(request, currentSection, currentQuestion, questionId);
		// setValuesInSession(currentSection, sectionInstanceDtos);
		
		QuestionSequence questionSequence = new QuestionSequence(currentSection.getQuestionInstanceDtos());
		SectionSequence sectionSequence = new SectionSequence(sectionInstanceDtos);
		
		currentQuestion = questionSequence.nextQuestion(Long.valueOf(questionId));
		
		if(currentQuestion == null) {
			/**
			 * That means we are navigating into next section. Before doing that lets save the current section
			 */
			saveSection(currentSection, request);
			
			
			SectionInstanceDto nextSection = sectionSequence.nextSection(currentSection.getSection().getSectionName());
		
			if(nextSection != null) {
				nextSection = populateWithQuestions(nextSection, test.getTestName(), nextSection.getSection().getSectionName(), user.getCompanyId());
				//currentSection.getQuestionInstanceDtos().clear();
				currentQuestion = nextSection.getQuestionInstanceDtos().get(0);
				if(currentQuestion.getCode() == null || currentQuestion.getCode().trim().length() == 0){
		 			currentQuestion.setCode(currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getInputCode());
		 		}
				
				/**
				 * Making sure next and prev button behave for the first and last event
				 */
				questionSequence = new QuestionSequence(nextSection.getQuestionInstanceDtos());
				if(isQuestionLast(currentQuestion, questionSequence, sectionSequence)) {
					nextSection.setLast(true);
				 }
				else {
					nextSection.setLast(false);
				}
				 
				 if(isQuestionFirst(currentQuestion, questionSequence, sectionSequence)) {
					 nextSection.setFirst(true);
				 }
				 else {
					 nextSection.setFirst(false);
				 }
				
				 model.addObject("currentSection", nextSection);
				 nextSection.setCurrent(true);
				 currentSection.setCurrent(false);
				 model.addObject("currentQuestion", currentQuestion);
				 model.addObject("confidenceFlag", test.getConsiderConfidence());
				 request.getSession().setAttribute("currentSection", nextSection);
				 putMiscellaneousInfoInModel(model, request);
				 processPercentages(model, sectionInstanceDtos, test.getTotalMarks());
				 /**
				  * Get the fullstack for Q if type is full stack.
				  * 
				  */
				 if(!currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getFullstack().getStack().equals(FullStackOptions.NONE.getStack())){
					 setWorkspaceIDEForFullStackQ(request, currentQuestion);
				 }
				 /**
				  * End full stack check
				  */
				 return model;
			}
			else {
				//Save test and generate result
				model= new ModelAndView("report");
				putMiscellaneousInfoInModel(model, request);
				
				return model;
			}
		}
		else {
			 if(isQuestionLast(currentQuestion, questionSequence, sectionSequence)) {
				 currentSection.setLast(true);
			 }
			 else {
				 currentSection.setLast(false);
			 }
			 
			 if(isQuestionFirst(currentQuestion, questionSequence, sectionSequence)) {
				 currentSection.setFirst(true);
			 }
			 else {
				 currentSection.setFirst(false);
			 }
//			 if(currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getQuestionType() != null && currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getQuestionType().getType().equals(QuestionType.CODING.getType())){
//			 		currentQuestion.setCode(currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getInputCode().replaceAll("\r", ""));
//			 	}
			 model.addObject("currentSection", currentSection);
			 
			 if(currentQuestion.getCode() == null || currentQuestion.getCode().trim().length() == 0){
		 			currentQuestion.setCode(currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getInputCode());
		 		}
			 model.addObject("currentQuestion", currentQuestion);
			 request.getSession().setAttribute("currentSection", currentSection);
			 putMiscellaneousInfoInModel(model, request);
			 setTimeInCounter(request, Long.valueOf(timeCounter));
			 processPercentages(model, sectionInstanceDtos, test.getTotalMarks());
			 /**
			  * Get the fullstack for Q if type is full stack.
			  * 
			  */
			 if(!currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getFullstack().getStack().equals(FullStackOptions.NONE.getStack())){
				 setWorkspaceIDEForFullStackQ(request, currentQuestion);
			 }
			 /**
			  * End full stack check
			  */
			 model.addObject("confidenceFlag", test.getConsiderConfidence());
			 return model;
		}
		    
	 }
	 
	 private Boolean isQuestionLast(QuestionInstanceDto current, QuestionSequence questionSequence, SectionSequence sectionSequence) {
		 if(sectionSequence.nextSection(current.getQuestionMapperInstance().getQuestionMapper().getSectionName() )== null) {
			 //means this is the last section
			 if(questionSequence.nextQuestion(current.getQuestionMapperInstance().getQuestionMapper().getId()) == null) {
				 return true;
			 }
		 }
		 return false;
	 }
	 
	 private Boolean isQuestionFirst(QuestionInstanceDto current, QuestionSequence questionSequence, SectionSequence sectionSequence) {
		 if(sectionSequence.prevSection(current.getQuestionMapperInstance().getQuestionMapper().getSectionName() )== null) {
			 //means this is the first section
			 if(questionSequence.previousQuestion(current.getQuestionMapperInstance().getQuestionMapper().getId()) == null) {
				 return true;
			 }
		 }
		 return false;
	 }
	 
	 private void setWorkspaceIDEForFullStackQ(HttpServletRequest request, QuestionInstanceDto currentQuestion) throws Exception{
		logger.info("in setWorkspaceIDEForFullStackQ "+currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getFullstack().getStack());
		 if(currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getFullstack().getStack().equals(FullStackOptions.JAVA_FULLSTACK.getStack()) || currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getFullstack().getStack().equals(FullStackOptions.PHP_FULLSTACK.getStack()) || currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getFullstack().getStack().equals(FullStackOptions.ANGULARJS_FULLSTACK.getStack()) || currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getFullstack().getStack().equals(FullStackOptions.JAVASCRIPT_FULLSTACK.getStack()) || currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getFullstack().getStack().equals(FullStackOptions.DOTNET_FULLSTACK.getStack())){
			
			User user = (User) request.getSession().getAttribute("user");
			String fullName = user.getFirstName()+user.getLastName();
			Test test = (Test) request.getSession().getAttribute("test");
			fullName = fullName.replace(" ", "");
			String secName = currentQuestion.getQuestionMapperInstance().getQuestionMapper().getSectionName();
			QuestionMapperInstance qms = questionMapperInstanceRep.findUniqueQuestionMapperInstanceForUser(currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getQuestionText(), test.getTestName(), secName, user.getEmail(), user.getCompanyId());
		    String workspace = "";
		    if(qms == null){
		    	WorkspaceResponse workspaceResponse = generateWorkspace(currentQuestion, test.getId(), currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getId(), fullName, currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getFullstack());
		    	workspace = workspaceResponse.getLinks().getIde();
		    	qms = currentQuestion.getQuestionMapperInstance();
		 		qms.setCompanyId(test.getCompanyId());
		 		qms.setQuestionText(currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getQuestionText());
		 		qms.setTestName(test.getTestName());
		 		qms.setSectionName(secName);
		 		qms.setUser(user.getEmail());
		 		qms.setCreateDate(new Date());
		 		qms.setCompanyName(test.getCompanyName());
		 		qms.setWorkspaceUrl(workspace);
		 		qms.setWorkSpaceId(workspaceResponse.getId());
		 		questionMapperInstanceRep.save(qms);
		    }
		    else{
		    	if(qms.getWorkspaceUrl() == null || qms.getWorkspaceUrl().trim().length() == 0){
			    	// if(stackName.equals("Java")){
		    		//workspace = generateWorkspace(currentQuestion, test.getId(), currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getId(), fullName);
		    		WorkspaceResponse workspaceResponse = generateWorkspace(currentQuestion, test.getId(), currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getId(), fullName, currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getFullstack());
			    	workspace = workspaceResponse.getLinks().getIde();
		    		//return workspaceResponse.getLinks().getIde();
			    	qms.setWorkSpaceId(workspaceResponse.getId());
		    		qms.setWorkspaceUrl(workspace);
		    		qms.setUpdateDate(new Date());
		    		questionMapperInstanceRep.save(qms);
			 	// }
			    }
				else{
					workspace = qms.getWorkspaceUrl();
				}
		    }
		    currentQuestion.setQuestionMapperInstance(qms);
		    currentQuestion.getQuestionMapperInstance().setWorkspaceUrl(workspace);
		    
		}
		
	 	
	 }
	 
	 private WorkspaceResponse generateWorkspace(QuestionInstanceDto currentQuestion, Long tid, Long qid, String fullName, FullStackOptions fullStackOptions) throws Exception{
		
		 logger.info("in generate workspace "+fullStackOptions.getStack());
		 String json = "";
			if(fullStackOptions == null || fullStackOptions.getStack().equals(FullStackOptions.JAVA_FULLSTACK.getStack())){
				System.out.println("generatin workspace for java");
				json = FileUtils.readFileToString(new File("assessment"+File.separator+"eclipseChe"+File.separator+"Java_FullStack.json"));
			}
			else if(fullStackOptions.getStack().equals(FullStackOptions.PHP_FULLSTACK.getStack())){
				System.out.println("generatin workspace for php");
				json = FileUtils.readFileToString(new File("assessment"+File.separator+"eclipseChe"+File.separator+"PHP_MySQL.json"));
			}
			else if(fullStackOptions.getStack().equals(FullStackOptions.ANGULARJS_FULLSTACK.getStack())){
				System.out.println("generatin workspace for angular");
				json = FileUtils.readFileToString(new File("assessment"+File.separator+"eclipseChe"+File.separator+"ANGULAR_JAVASCRIPT_MYSQL.json"));
			}
			else{
				System.out.println("generatin workspace for others");
				json = FileUtils.readFileToString(new File("assessment"+File.separator+"eclipseChe"+File.separator+"Java_FullStack.json"));
			}
		 
		 
	 		//String qid = ""+currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getId();
	 		json = json.replace("${APP_USER}", fullName+"-"+tid+"-"+qid+"-"+System.currentTimeMillis());
	 		//json = json.replace("${APP_USER}", "a01");
	 		json = json.replace("${APP_DESC}", "Skeleton Code............................Project\n\n\n.........");
	 		EclipseCheService eclipseCheService = new EclipseCheService();
	 		WorkspaceResponse workspaceResponse = eclipseCheService.createWorkSpace(json);
	 		//return workspaceResponse.getLinks().getIde();
	 		return workspaceResponse;
	 }
	 
	 
	 @RequestMapping(value = "/prevQuestion", method = RequestMethod.POST)
	  public ModelAndView prevQuestion(@RequestParam String questionId, @RequestParam String timeCounter,HttpServletRequest request, HttpServletResponse response,@ModelAttribute("currentQuestion") QuestionInstanceDto currentQuestion) throws Exception {
		 ModelAndView model= new ModelAndView("test");
		 User user = (User) request.getSession().getAttribute("user");
		 Test test = (Test) request.getSession().getAttribute("test");
		 List<SectionInstanceDto> sectionInstanceDtos = (List<SectionInstanceDto>) request.getSession().getAttribute("sectionInstanceDtos");
		 model.addObject("sectionInstanceDtos", sectionInstanceDtos);
		 
		SectionInstanceDto currentSection = (SectionInstanceDto) request.getSession().getAttribute("currentSection");
		//just in case a Q is of coding type value that comes from jsp has \r characters.so removng them so they can be rendered next time
		if(currentQuestion.getCode() != null){
	 		 currentQuestion.setCode(currentQuestion.getCode().replaceAll("\r", ""));
	 		 String rep = "\\\\n";
	 		 String rept = "\\\\t";
	 		currentQuestion.setCode(currentQuestion.getCode().replaceAll("\n", rep));
	 		currentQuestion.setCode(currentQuestion.getCode().replaceAll("\t", rept));
	 	}
		setAnswers(request, currentSection, currentQuestion, questionId);
		//setValuesInSession(currentSection, sectionInstanceDtos);
		
		QuestionSequence questionSequence = new QuestionSequence(currentSection.getQuestionInstanceDtos());
		SectionSequence sectionSequence = new SectionSequence(sectionInstanceDtos);
		currentQuestion = questionSequence.previousQuestion(Long.valueOf(questionId));
		if(currentQuestion == null) {
			
			
			SectionInstanceDto previousSection = sectionSequence.prevSection(currentSection.getSection().getSectionName());
			
			if(previousSection != null) {
				previousSection = populateWithQuestions(previousSection, test.getTestName(), previousSection.getSection().getSectionName(), user.getCompanyId());
				//currentSection.getQuestionInstanceDtos().clear();
				currentQuestion = previousSection.getQuestionInstanceDtos().get(previousSection.getQuestionInstanceDtos().size()-1);
				 model.addObject("currentSection", previousSection);
				 previousSection.setCurrent(true);
				 currentSection.setCurrent(false);
				 /**
					 * Making sure next and prev button behave for the first and last event
					 */
				 questionSequence = new QuestionSequence(previousSection.getQuestionInstanceDtos());//now get the last question from the prev section
					if(isQuestionLast(currentQuestion, questionSequence, sectionSequence)) {
						previousSection.setLast(true);
					 }
					else {
						previousSection.setLast(false);
					}
					 
					 if(isQuestionFirst(currentQuestion, questionSequence, sectionSequence)) {
						 previousSection.setFirst(true);
					 }
					 else {
						 previousSection.setFirst(false);
					 }
					 
				 if(currentQuestion.getCode() == null || currentQuestion.getCode().trim().length() == 0){
			 			currentQuestion.setCode(currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getInputCode());
			 	 }
				 model.addObject("currentQuestion", currentQuestion);
				 request.getSession().setAttribute("currentSection", previousSection);
				 putMiscellaneousInfoInModel(model, request);
				 processPercentages(model, sectionInstanceDtos, test.getTotalMarks());
				 /**
				  * Get the fullstack for Q if type is full stack.
				  * 
				  */
				 if(!currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getFullstack().getStack().equals(FullStackOptions.NONE.getStack())){
					 setWorkspaceIDEForFullStackQ(request, currentQuestion);
				 }
				 /**
				  * End full stack check
				  */
				 model.addObject("confidenceFlag", test.getConsiderConfidence());
				 return model;
			}
			else {
				//Save test and generate result
				model= new ModelAndView("intro");
				putMiscellaneousInfoInModel(model, request);
				return model;
			}
		}
		else {
			 model.addObject("currentSection", currentSection);
//			 if(currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getQuestionType() != null && currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getQuestionType().getType().equals(QuestionType.CODING.getType())){
//				 currentQuestion.setCode(currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getInputCode().replaceAll("\r", ""));
//				 //currentQuestion.setCode(currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getInputCode().replaceAll("\\r\\n|\\r|\\n", "<br />"));
//			 	}
			 if(currentQuestion.getCode() == null || currentQuestion.getCode().trim().length() == 0){
		 			currentQuestion.setCode(currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getInputCode());
		 		}
			 model.addObject("currentQuestion", currentQuestion);
			 if(isQuestionLast(currentQuestion, questionSequence, sectionSequence)) {
				 currentSection.setLast(true);
			 }
			 else {
				 currentSection.setLast(false);
			 }
			 
			 if(isQuestionFirst(currentQuestion, questionSequence, sectionSequence)) {
				 currentSection.setFirst(true);
			 }
			 else {
				 currentSection.setFirst(false);
			 }
			 
			 request.getSession().setAttribute("currentSection", currentSection);
			 putMiscellaneousInfoInModel(model, request);
			 setTimeInCounter(request, Long.valueOf(timeCounter));
			 processPercentages(model, sectionInstanceDtos, test.getTotalMarks());
			 /**
			  * Get the fullstack for Q if type is full stack.
			  * 
			  */
			 if(!currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getFullstack().getStack().equals(FullStackOptions.NONE.getStack())){
				 setWorkspaceIDEForFullStackQ(request, currentQuestion);
			 }
			 /**
			  * End full stack check
			  */
			 model.addObject("confidenceFlag", test.getConsiderConfidence());
			 return model;
		}
		    
	 }
	 
	 @RequestMapping(value = "/submitTest", method = RequestMethod.POST)
	  public ModelAndView submitTest(@RequestParam String questionId, @RequestParam String timeCounter, HttpServletRequest request, HttpServletResponse response,@ModelAttribute("currentQuestion") QuestionInstanceDto currentQuestion) {
		 ModelAndView model= new ModelAndView("test");
		 User user = (User) request.getSession().getAttribute("user");
		 Test test = (Test) request.getSession().getAttribute("test");
		 List<SectionInstanceDto> sectionInstanceDtos = (List<SectionInstanceDto>) request.getSession().getAttribute("sectionInstanceDtos");
		 model.addObject("sectionInstanceDtos", sectionInstanceDtos);
		 SectionInstanceDto currentSection = (SectionInstanceDto) request.getSession().getAttribute("currentSection");
		 //if(currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getQuestionType() != null && currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getQuestionType().getType().equals(QuestionType.CODING.getType())){
		 	//	currentQuestion.setCode(currentQuestion.getQuestionMapperInstance().getQuestionMapper().getQuestion().getInputCode().replaceAll("\\r\\n|\\r|\\n", "<br />"));
//			if(currentQuestion.getCode() != null){
//				currentQuestion.setCode(currentQuestion.getCode().replaceAll("\r", ""));
//				String rep = "\\\\n";
//		 		String rept = "\\\\t";
//		 		currentQuestion.setCode(currentQuestion.getCode().replaceAll("\n", rep));
//		 		currentQuestion.setCode(currentQuestion.getCode().replaceAll("\t", rept));
//			}
		 	
		// }
		 
		 setAnswers(request, currentSection, currentQuestion, questionId);
		 	for(SectionInstanceDto sectionInstanceDto : sectionInstanceDtos) {
		 		saveSection(sectionInstanceDto, request);
		 		
		 	}
		 	UserTestSession userTestSession = new UserTestSession();
			userTestSession.setCompanyId(user.getCompanyId());
			userTestSession.setCompanyName(user.getCompanyName());
			
			userTestSession.setUser(user.getEmail());
			userTestSession.setTest(test);
			userTestSession.setTestName(test.getTestName());
			userTestSession.setComplete(true);
			
			/**
			 * Store sectio results in user test session
			 */
			
			 DecimalFormat df = new DecimalFormat("##.##");
			 String sectionsQuestionsNotAnswered = "";
			 for(SectionInstanceDto sectionInstanceDto : sectionInstanceDtos) {
				 userTestSession.setSectionResults((userTestSession.getSectionResults() == null?"":userTestSession.getSectionResults())+", "+sectionInstanceDto.getSection().getSectionName()+"-"+df.format((new Float(sectionInstanceDto.getTotalCorrectAnswers()) / new Float(sectionInstanceDto.getNoOfQuestions())) * 100 ));
				 userTestSession.setSectionsNoOfQuestionsNotAnswered((userTestSession.getSectionsNoOfQuestionsNotAnswered() == null?"": userTestSession.getSectionsNoOfQuestionsNotAnswered())+", "+sectionInstanceDto.getSection().getSectionName()+"-"+sectionInstanceDto.getNoOfQuestionsNotAnswered());
			 }
			 if(userTestSession.getSectionResults().startsWith(",")) {
				 userTestSession.setSectionResults( userTestSession.getSectionResults().replaceFirst(",", ""));
			 }
			 
			 if(userTestSession.getSectionsNoOfQuestionsNotAnswered() != null && userTestSession.getSectionsNoOfQuestionsNotAnswered().startsWith(",")) {
				 userTestSession.setSectionsNoOfQuestionsNotAnswered(userTestSession.getSectionsNoOfQuestionsNotAnswered().replaceFirst(",", ""));
			 }
			/**
			 * End storing section level results info
			 */
			 StudentTestForm studentTestForm = (StudentTestForm) request.getSession().getAttribute("studentTestForm");
			 userTestSession.setTestInviteSent(studentTestForm.getTestInviteSent());
			 userTestSession.setSharedDirect(studentTestForm.getSharedDirect());
			 Date createDate = (Date) request.getSession().getAttribute("testStartDate");
			 userTestSession.setCreateDate(createDate);
			 userTestSession.setUpdateDate(new Date());
			userTestSession = userTestSessionService.saveOrUpdate(userTestSession);
			
			studentTestForm.setNoOfAttempts(userTestSession.getNoOfAttempts());
		 
		 putMiscellaneousInfoInModel(model, request);
		 setTimeInCounter(request, Long.valueOf(timeCounter));
		 try {
			String rows = sendResultsEmail(request, userTestSession);
			model= new ModelAndView("studentTestCompletion");
			model.addObject("rows", rows);
			model.addObject("showResults", test.getSentToStudent());
				if(test.getSentToStudent()){
					model.addObject("TOTAL_QUESTIONS", userTestSession.getTotalMarks());
					model.addObject("TOTAL_MARKS", userTestSession.getTotalMarksRecieved());
					model.addObject("PASS_PERCENTAGE", test.getPassPercent());
					model.addObject("RESULT_PERCENTAGE", userTestSession.getPercentageMarksRecieved());
					model.addObject("STATUS", test.getPassPercent() > userTestSession.getPercentageMarksRecieved()?"Fail":"Success");
				}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			String message = "Results can not be sent for "+user.getEmail()+" for test "+test.getTestName();
			EmailGenericMessageThread client = new EmailGenericMessageThread("jatin.sutaria@thev2technologies.com", "Can not send Test link email", message, propertyConfig);
			Thread th = new Thread(client);
			th.start();
		}
		 return model;
	 }
	 
	 private String decodeUserId(String encodedUri)
	 {
		// Decode data on other side, by processing encoded data
		 String decoded = new String(DatatypeConverter.parseBase64Binary(encodedUri));
		 System.out.println("user id is " + decoded);
		 return decoded;
	 }
	 
	 private String sendResultsEmail(HttpServletRequest request, UserTestSession userTestSession) throws Exception {
		 String table = "<tr style=\"border-collapse:collapse;border: 1px solid black\">\r\n" + 
		 		"                                                <td align=\"center\" style=\"border: 1px solid black\"> {SECTION_NAME}</td>\r\n" + 
		 		"						<td align=\"center\" style=\"border: 1px solid black\"> {SECTION_PERCENT}</td>\r\n" + 
		 		"                                             </tr>";
			String loction = propertyConfig.getResultLinkHtmlLocation();
			String html = FileUtils.readFileToString(new File(loction));
			User user = (User) request.getSession().getAttribute("user");
			 Test test = (Test) request.getSession().getAttribute("test");
			 html = html.replace("{FIRST_NAME}", user.getFirstName());
			 html = html.replace("{LAST_NAME}", user.getLastName());
			 html = html.replace("{TEST_NAME}", test.getTestName());
			 html = html.replace("{TOTAL_QUESTIONS}", ""+userTestSession.getTotalMarks());//change later
			 html = html.replace("{TOTAL_MARKS}", ""+userTestSession.getTotalMarksRecieved());
			 html = html.replace("{PASS_PERCENTAGE}", ""+test.getPassPercent());
			 html = html.replace("{RESULT_PERCENTAGE} ", ""+userTestSession.getPercentageMarksRecieved());
			 html = html.replace("{STATUS} ", test.getPassPercent() > userTestSession.getPercentageMarksRecieved()?"Fail":"Success");
			 String rows = "";
			 List<SectionInstanceDto> sectionInstanceDtos = (List<SectionInstanceDto>) request.getSession().getAttribute("sectionInstanceDtos");
			 DecimalFormat df = new DecimalFormat("##.##");
			 for(SectionInstanceDto sectionInstanceDto : sectionInstanceDtos) {
				 String record = table;
				 record = record.replace("{SECTION_NAME}", sectionInstanceDto.getSection().getSectionName());
				 record = record.replace("{SECTION_PERCENT}", df.format((new Float(sectionInstanceDto.getTotalCorrectAnswers()) / new Float(sectionInstanceDto.getNoOfQuestions())) * 100 ));
				 rows += record;
			 }
			 html = html.replace("{ROWS}", rows);
			 UserNonCompliance nonCompliance = null;
			  nonCompliance = userNonComplianceService.findNonCompliance(userTestSession.getUser(), userTestSession.getTestName(), userTestSession.getCompanyId());
			// nonCompliance = userNonComplianceService.findLastNonCompliance(userTestSession.getUser(), userTestSession.getTestName(), userTestSession.getCompanyId());
//			 
//			 if(userTestSession.getId() == null) {
//				 nonCompliance = userNonComplianceService.findNonCompliance(userTestSession.getUser(), userTestSession.getTestName(), userTestSession.getCompanyId());
//			 }
//			 else {
//				 nonCompliance =  userNonComplianceService.findByPrimaryKey(userTestSession.getUser(), userTestSession.getTestName(), userTestSession.getCompanyId(), userTestSession.getId());
//			 }
			 html = html.replace("{NO_OF_NONCOMPLIANCES}", "<b>("+(nonCompliance == null? 0:nonCompliance.getNoOfNonCompliances())+")</b>");
			 
			 if(test.getTestName().equals("General_Technology_Comprehensive") || test.getTestName().equals("Java_Technology_Behaviour_Experienced") || test.getTestName().equals("Java_Technology_Behaviour_Freshers")){
				 String file = reportsService.generatedetailedReportForCompositeTest(user.getCompanyId(), test.getTestName(), user.getEmail());
				 String email = "";
				 if(user.getEmail().lastIndexOf("[") > 0 ){
				 		email = user.getEmail().substring(0, user.getEmail().lastIndexOf("["));
				 	}
				 	else{
				 		email = user.getEmail();
				 	}
				 EmailGenericMessageThread client = new EmailGenericMessageThread(test.getCreatedBy(), "Test Results for "+user.getFirstName()+" "+user.getLastName()+" for test- "+test.getTestName(), html, email, propertyConfig, file, user.getFirstName()+" "+user.getLastName()+"-"+test.getTestName());
					
				 	Thread th = new Thread(client);
					th.start();
			 }
			 else if(test.getTestName().equalsIgnoreCase("Manual Testing")){
				 String file = reportsService.generatedetailedReportForCompositeTest(user.getCompanyId(), test.getTestName(), user.getEmail());
				 String email = "";
				 	if(user.getEmail().lastIndexOf("[") > 0 ){
				 		email = user.getEmail().substring(0, user.getEmail().lastIndexOf("["));
				 	}
				 	else{
				 		email = user.getEmail();
				 	}
				 String cc[] = {"abbas.meghani@gmail.com", email};
				 EmailGenericMessageThread client = new EmailGenericMessageThread(test.getCreatedBy(), "Test Results for "+user.getFirstName()+" "+user.getLastName()+" for test- "+test.getTestName(), html, email, propertyConfig, file, user.getFirstName()+" "+user.getLastName()+"-"+test.getTestName());
					client.setCcArray(cc);
				 	Thread th = new Thread(client);
					th.start();
			 }
			 else if(test.getTestName().equalsIgnoreCase("Java_Test_With_Recomm_Support")){
				 String file = reportsService.generatedetailedReportForCompositeTest(user.getCompanyId(), test.getTestName(), user.getEmail());
				 String email = "";
				 	if(user.getEmail().lastIndexOf("[") > 0 ){
				 		email = user.getEmail().substring(0, user.getEmail().lastIndexOf("["));
				 	}
				 	else{
				 		email = user.getEmail();
				 	}
				 String cc[] = {"sreeram.gopal@iiht.com", email};
				 EmailGenericMessageThread client = new EmailGenericMessageThread(test.getCreatedBy(), "Test Results for "+user.getFirstName()+" "+user.getLastName()+" for test- "+test.getTestName(), html, email, propertyConfig, file, user.getFirstName()+" "+user.getLastName()+"-"+test.getTestName());
					client.setCcArray(cc);
				 	Thread th = new Thread(client);
					th.start();
			 }
			 else if(test.getTestName().equalsIgnoreCase("Java Developer Infrasoft Intermediate 1.0 ")){
				 String file = reportsService.generatedetailedReportForCompositeTest(user.getCompanyId(), test.getTestName(), user.getEmail());
				 String email = "";
				 	if(user.getEmail().lastIndexOf("[") > 0 ){
				 		email = user.getEmail().substring(0, user.getEmail().lastIndexOf("["));
				 	}
				 	else{
				 		email = user.getEmail();
				 	}
				 String cc[] = {"akansha.gupta@infrasofttech.com", email};
				 EmailGenericMessageThread client = new EmailGenericMessageThread(test.getCreatedBy(), "Test Results for "+user.getFirstName()+" "+user.getLastName()+" for test- "+test.getTestName(), html, email, propertyConfig, file, user.getFirstName()+" "+user.getLastName()+"-"+test.getTestName());
					client.setCcArray(cc);
				 	Thread th = new Thread(client);
					th.start();
			 }
//			 else if(test.getTestName().equalsIgnoreCase("Chenova_Exp_MicrosoftTech_Test") || test.getTestName().equalsIgnoreCase("Chenova_Exp_JavaTech_Test")){
//				 String file = reportsService.generatedetailedReportForCompositeTest(user.getCompanyId(), test.getTestName(), user.getEmail());
//				 String email = "";
//				 	if(user.getEmail().lastIndexOf("[") > 0 ){
//				 		email = user.getEmail().substring(0, user.getEmail().lastIndexOf("["));
//				 	}
//				 	else{
//				 		email = user.getEmail();
//				 	}
//				 String cc[] = {"VKotian@chenoainc.com", email};
//				 EmailGenericMessageThread client = new EmailGenericMessageThread(test.getCreatedBy(), "Test Results for "+user.getFirstName()+" "+user.getLastName()+" for test- "+test.getTestName(), html, propertyConfig);
//						client.setCcArray(cc);
//				 	Thread th = new Thread(client);
//					th.start();
//			 }
			 else if(test.getSendRecommReport() != null && test.getSendRecommReport()){
				 String file = reportsService.generatedetailedReportForCompositeTest(user.getCompanyId(), test.getTestName(), user.getEmail());
				 String email = "";
				 	if(user.getEmail().lastIndexOf("[") > 0 ){
				 		email = user.getEmail().substring(0, user.getEmail().lastIndexOf("["));
				 	}
				 	else{
				 		email = user.getEmail();
				 	}
				 String cc[] = { email};
				 EmailGenericMessageThread client = new EmailGenericMessageThread(test.getCreatedBy(), "Test Results for "+user.getFirstName()+" "+user.getLastName()+" for test- "+test.getTestName(), html, email, propertyConfig, file, user.getFirstName()+" "+user.getLastName()+"-"+test.getTestName());
						if(test.getSentToStudent() != null && test.getSentToStudent()){
							client.setCcArray(cc);
						}
				 	
				 	Thread th = new Thread(client);
					th.start();
			 }
			 else if(test.getSentToStudent()){
				// String file = reportsService.generatedetailedReportForCompositeTest(user.getCompanyId(), test.getTestName(), user.getEmail());
				 String email = "";
				 	if(user.getEmail().lastIndexOf("[") > 0 ){
				 		email = user.getEmail().substring(0, user.getEmail().lastIndexOf("["));
				 	}
				 	else{
				 		email = user.getEmail();
				 	}
				 String cc[] = {email};
				 EmailGenericMessageThread client = new EmailGenericMessageThread(test.getCreatedBy(), "Test Results for "+user.getFirstName()+" "+user.getLastName()+" for test- "+test.getTestName(), html, propertyConfig);
					client.setCcArray(cc);
				 	Thread th = new Thread(client);
					th.start();
			 }
			 else{
				 EmailGenericMessageThread client = new EmailGenericMessageThread(test.getCreatedBy(), "Test Results for "+user.getFirstName()+" "+user.getLastName()+" for test- "+test.getTestName(), html, propertyConfig);
					
				 	Thread th = new Thread(client);
					th.start();
			 }
		return rows;	 
			 
	 }
}
