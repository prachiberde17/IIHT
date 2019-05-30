package com.assessment.web.controllers;


import java.net.URLEncoder;
import java.util.Base64;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.common.AssessmentGenericException;
import com.assessment.common.CommonUtil;
import com.assessment.common.PropertyConfig;
import com.assessment.common.SchedulerTask;
import com.assessment.common.util.EmailGenericMessageThread;
import com.assessment.data.Company;
import com.assessment.data.DifficultyLevel;
import com.assessment.data.Question;
import com.assessment.data.QuestionMapperInstance;
import com.assessment.data.Test;
import com.assessment.data.TestScheduler;
import com.assessment.data.User;
import com.assessment.data.UserTestSession;
import com.assessment.data.UserType;
import com.assessment.repositories.TestSchedulerRepository;
import com.assessment.repositories.UserTestSessionRepository;
import com.assessment.scheduler.ScheduleTaskService;
import com.assessment.services.CompanyService;
import com.assessment.services.QuestionMapperInstanceService;
import com.assessment.services.QuestionService;
import com.assessment.services.TestService;
import com.assessment.services.UserService;
import com.assessment.web.dto.TestUserData;

@Controller
public class LoginController {
@Autowired	
UserService userService;
@Autowired
QuestionService questionService;
@Autowired
CompanyService companyService;
@Autowired
TestService testService;

@Autowired
PropertyConfig propertyConfig;

Boolean first = false;

@Autowired
TestSchedulerRepository rep;

@Autowired
ScheduleTaskService schedulerService;
@Autowired
PropertyConfig config;
@Autowired
UserTestSessionRepository testSessionRepository;

@Autowired
QuestionMapperInstanceService qminService;
	
	private final String prefixURL = "iiht_html";
	
	public void init(String companyId){
		List<TestScheduler> schedulers = rep.findByCompanyId(companyId);
		for(TestScheduler scheduler : schedulers){
			TimeZone timeZone = TimeZone.getTimeZone("Asia/Kolkata");
		    CronTrigger cronTrigger = new CronTrigger(scheduler.getCronExpression(), timeZone);
			SchedulerTask schedulerTask=  new SchedulerTask(scheduler.getTestId(), scheduler.getTestName(), scheduler.getCompanyId(), config.getBaseUrl(), scheduler.getUserEmails(), config.getTestLinkHtml_Generic_Location(), config);
			schedulerService.addTaskToScheduler(scheduler.getId().intValue(), schedulerTask, cronTrigger);
		}
	}

	  @RequestMapping(value = "/login", method = RequestMethod.GET)
	  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("login_new");
	    User user = new User();
	   // user.setEmail("system@iiht.com");
	  //  user.setPassword("1234");
	 //   user.setCompanyName("IIHT");
	    mav.addObject("user", user);
	    return mav;
	  }
	  
	  @RequestMapping(value = "/", method = RequestMethod.GET)
	  public ModelAndView showRoot(HttpServletRequest request, HttpServletResponse response) {
		  return showLogin(request, response);
	  }
	  
	  //it shows public data
	  @RequestMapping(value = "/publicTest", method = RequestMethod.GET)
	  public ModelAndView showPublicTest(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("publicTest");
	    User user = new User();
	    TestUserData testUserData = new TestUserData();
	    String testId = request.getParameter("testId");
	    String companyId = request.getParameter("companyId");
	    String inviteSent = request.getParameter("inviteSent");
	    	if(inviteSent != null) {
	    		request.getSession().setAttribute("inviteSent", inviteSent);
	    	}
	    Company company = companyService.findByCompanyId(companyId);
	    	if(company == null) {
	    		return mav;
	    	}
	    user.setCompanyName(company.getCompanyName());
	    user.setCompanyId(company.getCompanyId());
	    testUserData.setUser(user);
	    Test test = testService.findTestById(Long.parseLong(testId));
	    testUserData.setTestId(test.getId()+"");
	    testUserData.setTestName(test.getTestName());
	    request.getSession().setAttribute("user", user);
	    mav.addObject("testUserData", testUserData);
	   
	    return mav;
	  }
	  
	  @RequestMapping(value = "/publicTestAuthenticate", method = RequestMethod.POST)
	  public ModelAndView publicTestAuthenticate(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("testUserData") TestUserData testUserData) {
		  
		  testUserData.getUser().setPassword("12345");
		  Test test = testService.findTestById(Long.parseLong(testUserData.getTestId()));
		  
		  /**
		   * Step 1 - figure out if the user has taken a test.
		   */
		  UserTestSession session = testSessionRepository.findByPrimaryKey(testUserData.getUser().getEmail(), test.getTestName(), test.getCompanyId());
		  String userNameNew = "";
		  if(session == null){
			  userNameNew = testUserData.getUser().getEmail();
		  }
		  else{
			  /**
			   * Step 2 - find out how many sessions for the given test the user has taken
			   */
			  List<UserTestSession> sessions = testSessionRepository.findByTestNamePart(testUserData.getUser().getEmail()+"["+test.getId(), test.getTestName(), test.getCompanyId());
			  int noOfConfAttempts = test.getNoOfConfigurableAttempts() ==null?50:test.getNoOfConfigurableAttempts();
			  	if(noOfConfAttempts <= (sessions.size()+1)){
			  		ModelAndView mav = new ModelAndView("studentNoTest_ExceededAttempts");
			  		mav.addObject("firstName", testUserData.getUser().getFirstName());
			  		mav.addObject("lastName", testUserData.getUser().getLastName());
			  		mav.addObject("attempts", sessions.size()+1);
			  		return mav;
			  	}
			  
			  userNameNew = testUserData.getUser().getEmail()+"["+test.getId()+"-"+(sessions.size()+1+"]");
		  }
		  
		  boolean validate = validateDomainCheck(test, testUserData.getUser().getEmail());
		  	if(!validate) {
		  		ModelAndView mav = new ModelAndView("studentNoTest_Domain");
		  		mav.addObject("firstName", testUserData.getUser().getFirstName());
		  		mav.addObject("lastName", testUserData.getUser().getLastName());
		  		mav.addObject("domain", test.getDomainEmailSupported());
		  		return mav;
		  	}
		  	testUserData.getUser().setEmail(userNameNew);
	   userService.saveOrUpdate(testUserData.getUser());
	   request.getSession().setAttribute("user", testUserData.getUser());
	  
	   request.getSession().setAttribute("test", test);
	  	if(testUserData.getUser() == null) {
	  		return showPublicTest(request, response);
	  	}
	  	String userId = URLEncoder.encode(Base64.getEncoder().encodeToString(testUserData.getUser().getEmail().getBytes()));
	  	String companyId = URLEncoder.encode(test.getCompanyId());
	    String inviteSent = (String) request.getSession().getAttribute("inviteSent");
	    String append = "";
	    	if(inviteSent != null) {
	    		append += "&inviteSent="+inviteSent;
	    	}
	  	String url = "redirect:/startTestSession?userId="+userId+"&companyId="+companyId+"&testId="+test.getId()+append+"&sharedDirect=yes";
		ModelAndView mav = new ModelAndView(url);
	    return mav;
	  }
	  
	  private boolean validateDomainCheck(Test test, String email) {
		  if(test.getDomainEmailSupported() == null || test.getDomainEmailSupported().trim().length() == 0 || test.getDomainEmailSupported().equals("*")) {
			  return true;
		  }
		  
		  String dom = email.substring(email.indexOf("@")+1, email.length());
		  if(test.getDomainEmailSupported().contains(dom)) {
			  return true;
		  }
		  
		  return false;
	  }
	  
	  @RequestMapping(value = "/problem", method = RequestMethod.GET)
	  public ModelAndView problem(HttpServletRequest request, HttpServletResponse response) {
	   
		  String stack = (String)request.getSession().getAttribute("errorStack");
		  	if(stack != null) {
		  		EmailGenericMessageThread client = new EmailGenericMessageThread("jatin.sutaria@thev2technologies.com", "Error in Assessment Platform", stack, propertyConfig);
				Thread th = new Thread(client);
				th.start();
		  	}
		  request.getSession().invalidate();
		 ModelAndView mav = new ModelAndView("errorPage");
	  
	    return mav;
	  }
	  
	  @RequestMapping(value = "/signoff", method = RequestMethod.GET)
	  public ModelAndView signoff(HttpServletRequest request, HttpServletResponse response) {
	    request.getSession().invalidate();
		// ModelAndView mav = new ModelAndView("index");
	    ModelAndView mav = new ModelAndView("login_new");
	    User user = new User();
	    //user.setEmail("system@iiiht.com");
	   // user.setPassword("1234");
	   // user.setCompanyName("IIHT");
	    mav.addObject("user", user);
	    return mav;
	  }
	  
	  @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	  public ModelAndView authenticate(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user ) {
		    ModelAndView mav = null;
		  user = userService.authenticate(user.getEmail(), user.getPassword(), user.getCompanyName());
		  	if(user == null) {
		  		//navigate to exception page
		  		//mav = new ModelAndView("index");
		  		mav = new ModelAndView("login_new");
		 	    user = new User();
		 	    mav.addObject("user", user);
		 	    mav.addObject("message", "Invalid Credentials ");// later put it as label
				mav.addObject("msgtype", "Failure");
		 	    return mav;
		  	}
		  	else if(user.getUserType().getType().equals(UserType.REVIEWER.getType())){
		  		 mav = new ModelAndView("java_fullstack");
		  		request.getSession().setAttribute("user", user);
		  		request.getSession().setAttribute("companyId", user.getCompanyId());
				 List<QuestionMapperInstance> instances = qminService.findFullStackQuestionMapperInstancesForJava(user.getCompanyId());
				 	for(QuestionMapperInstance ins : instances){
				 		User u = userService.findByPrimaryKey(ins.getUser(), user.getCompanyId());
				 		ins.setUerFullName(u.getFirstName()+" "+u.getLastName());
				 	}
				 mav.addObject("instances", instances);
				 return mav;
		  	}
		  	else {
		  		//to dashboard
		  		//List<Question> questions = questionService.findQuestions(user.getCompanyId());
		  		/**
		  		 * Get all scheduled tests and load them into scheduler for the first time.
		  		 */
		  		if(!first){
		  			init(user.getCompanyId());
		  			first = true;
		  		}
		  		Page<Question> questions = questionService.findQuestionsByPage(user.getCompanyId(), 0);
		  		request.getSession().setAttribute("user", user);
		  		request.getSession().setAttribute("companyId", user.getCompanyId());
		  		//request.getSession().setAttribute("questions", questions);
		  		
		  		mav = new ModelAndView("question_list");
		  		mav.addObject("qs", questions.getContent());
				mav.addObject("levels", DifficultyLevel.values());
				CommonUtil.setCommonAttributesOfPagination(questions, mav.getModelMap(), 0, "question_list", null);
		  	}
		    return mav;
		  }
	  
	  @RequestMapping(value = "/addQ", method = RequestMethod.GET)
	  public ModelAndView addQ(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("add_question");
	  
	    return mav;
	  }
	  
	  @RequestMapping(value = "/listQ", method = RequestMethod.GET)
	  public ModelAndView listQ(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("question_list");
	  
	    return mav;
	  }
}
