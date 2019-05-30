package com.assessment.web.controllers;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.common.PropertyConfig;
import com.assessment.common.util.EmailGenericMessageThread;
import com.assessment.data.CodeMetrics;
import com.assessment.data.QuestionMapperInstance;
import com.assessment.data.Status;
import com.assessment.data.Test;
import com.assessment.data.User;
import com.assessment.data.UserType;
import com.assessment.repositories.QuestionMapperInstanceRepository;
import com.assessment.services.CodeMetricsService;
import com.assessment.services.QuestionMapperInstanceService;
import com.assessment.services.TestService;
import com.assessment.services.UserService;

@Controller
public class ReviewerController {
	@Autowired
	QuestionMapperInstanceService qminService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TestService testService;
	
	@Autowired
	QuestionMapperInstanceRepository qminrep;
	
	@Autowired
	CodeMetricsService codeMetricsService;
	
	@Autowired
	PropertyConfig propertyConfig;
	
	Logger logger = LoggerFactory.getLogger(ReviewerController.class);
	
	
	 @RequestMapping(value = "/showJavaFullStack", method = RequestMethod.GET)
	 public ModelAndView showJavaFullStack(HttpServletRequest request, HttpServletResponse response,@RequestParam String qMapperInstanceId) throws Exception {
		 User user = (User) request.getSession().getAttribute("user");
		 if(!(user != null && user.getUserType().getType().equals(UserType.REVIEWER.getType()))){
			 ModelAndView mav = new ModelAndView("index");
			    User usr = new User();
			    mav.addObject("user", usr);
			    return mav;
		 }
		 ModelAndView mav = new ModelAndView("java_fullstack");
		 List<QuestionMapperInstance> instances = qminService.findFullStackQuestionMapperInstancesForJava(user.getCompanyId());
		 for(QuestionMapperInstance ins : instances){
		 		User u = userService.findByPrimaryKey(ins.getUser(), user.getCompanyId());
		 		ins.setUerFullName(u.getFirstName()+" "+u.getLastName());
		 	}
		 mav.addObject("instances", instances);
		 return mav;
	 }
	 
	 @RequestMapping(value = "/showDotNetFullStack", method = RequestMethod.GET)
	 public ModelAndView showDotNetFullStack(HttpServletRequest request, HttpServletResponse response,@RequestParam String qMapperInstanceId) throws Exception {
		 User user = (User) request.getSession().getAttribute("user");
		 if(!(user != null && user.getUserType().getType().equals(UserType.REVIEWER.getType()))){
			 ModelAndView mav = new ModelAndView("index");
			    User usr = new User();
			    mav.addObject("user", usr);
			    return mav;
		 }
		 ModelAndView mav = new ModelAndView("dotnet_fullstack");
		 List<QuestionMapperInstance> instances = qminService.findFullStackQuestionMapperInstancesForDotNet(user.getCompanyId());
		 for(QuestionMapperInstance ins : instances){
		 		User u = userService.findByPrimaryKey(ins.getUser(), user.getCompanyId());
		 		ins.setUerFullName(u.getFirstName()+" "+u.getLastName());
		 	}
		 mav.addObject("instances", instances);
		 return mav;
	 }
	 
	 @RequestMapping(value = "/showJavascriptFullStack", method = RequestMethod.GET)
	 public ModelAndView showJavascriptFullStack(HttpServletRequest request, HttpServletResponse response,@RequestParam String qMapperInstanceId) throws Exception {
		 User user = (User) request.getSession().getAttribute("user");
		 if(!(user != null && user.getUserType().getType().equals(UserType.REVIEWER.getType()))){
			 ModelAndView mav = new ModelAndView("index");
			    User usr = new User();
			    mav.addObject("user", usr);
			    return mav;
		 }
		 ModelAndView mav = new ModelAndView("javscript_fullstack");
		 List<QuestionMapperInstance> instances = qminService.findFullStackQuestionMapperInstancesForDotNet(user.getCompanyId());
		 for(QuestionMapperInstance ins : instances){
		 		User u = userService.findByPrimaryKey(ins.getUser(), user.getCompanyId());
		 		ins.setUerFullName(u.getFirstName()+" "+u.getLastName());
		 	}
		 mav.addObject("instances", instances);
		 return mav;
	 }
	 
	 @RequestMapping(value = "/getCodeMetrics", method = RequestMethod.GET)
	 @Produces(MediaType.APPLICATION_JSON)
	 public @ResponseBody CodeMetrics getCodeMetrics(HttpServletRequest request, HttpServletResponse response, @RequestParam String qMapperInstanceId){
		 QuestionMapperInstance instance = qminrep.findById(Long.parseLong(qMapperInstanceId)).get();
		 CodeMetrics codeMetrics = codeMetricsService.findByPrimaryKey(instance.getCompanyId(), instance.getTestName(), instance.getUser(), Long.parseLong(qMapperInstanceId));
		 if(codeMetrics == null){
			 return null;
		 }
		 codeMetrics.setFc(codeMetrics.getFunctionalCompliance().getLevel());
		 codeMetrics.setPer(codeMetrics.getDesignForPerforanceQuality().getLevel());
		 codeMetrics.setSca(codeMetrics.getDesignForScalabilityQuality().getLevel());
		 codeMetrics.setSec(codeMetrics.getDesignForSecurityQuality().getLevel());
		 codeMetrics.setFlex(codeMetrics.getDesignForFlexibilityQuality().getLevel());
		 codeMetrics.setAda(codeMetrics.getDesignForAdaptibilityQuality().getLevel());
		 codeMetrics.setTes(codeMetrics.getTestCasesQuality().getLevel());
		 codeMetrics.setSts(codeMetrics.getStatus().getLevel());
		 return codeMetrics;
	 }
	 
	 @RequestMapping(value = "/saveFullstackReview", method = RequestMethod.POST)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public @ResponseBody String   saveFullstackReview(HttpServletRequest request, HttpServletResponse response, CodeMetrics codeMetrics) throws Exception{
		 QuestionMapperInstance instance = qminrep.findById(codeMetrics.getQuestionMapperInstanceId()).get();
		 codeMetrics.setQuestionId(instance.getQuestionMapper().getQuestion().getId());
		 codeMetrics.setQuestion(instance.getQuestionMapper().getQuestion());
		 codeMetrics.setEmail(instance.getUser());
		// codeMetrics.setFullName(instance.getUerFullName());
		 codeMetrics.setTestName(instance.getTestName());
		 Test test = testService.findbyTest(instance.getTestName(), instance.getCompanyId());
		 codeMetrics.setTestId(test.getId());
		 codeMetrics.setCompanyId(instance.getCompanyId());
		 codeMetrics.setCompanyName(instance.getCompanyName());
		 codeMetricsService.saveOrUpdate(codeMetrics);
		 
		 if(codeMetrics.getStatus().getLevel().equals(Status.COMPLETE.getLevel())){
			 shareFullStackResultsByEmail(instance, codeMetrics, request);
		 }
		 return "ok";
	 }
	 
	 private void shareFullStackResultsByEmail(QuestionMapperInstance instance, CodeMetrics codeMetrics, HttpServletRequest request) throws Exception{
		 User user = (User) request.getSession().getAttribute("user");
		 String loction = propertyConfig.getFullStackReviewTemplate();
		 System.out.println("location of fullstack "+loction);
		 logger.info("location of fullstack "+loction);
		 logger.debug("location of fullstack "+loction);
		 String html = FileUtils.readFileToString(new File(loction));
		 html = html.replace("{REVIEWER}", user.getEmail());
		 User user2 = userService.findByPrimaryKey(instance.getUser(), user.getCompanyId());
		 html = html.replace("{STUDENT}", user2.getFirstName()+" "+user2.getLastName());
		 html = html.replace("{PROBLEM}", instance.getQuestionText());
		 html = html.replace("{TEST_NAME}", instance.getTestName());
		 html = html.replace("{STATUS}", "Review Complete");
		 html = html.replace("{OVERALL_COMMENTS}", codeMetrics.getOverAll());
		 html = html.replace("{FC}", codeMetrics.getFunctionalCompliance().getLevel());
		 html = html.replace("{PERF}", codeMetrics.getDesignForPerforanceQuality().getLevel());
		 html = html.replace("{SCAL}", codeMetrics.getDesignForScalabilityQuality().getLevel());
		 html = html.replace("{SEC}", codeMetrics.getDesignForSecurityQuality().getLevel());
		 html = html.replace("{FLEX}", codeMetrics.getDesignForFlexibilityQuality().getLevel());
		 html = html.replace("{ADAPT}", codeMetrics.getDesignForAdaptibilityQuality().getLevel());
		 html = html.replace("{TEST}", codeMetrics.getTestCasesQuality().getLevel());
		 
		 html = html.replace("{FC_COMMENTS}", codeMetrics.getFunctionalComplianceComments());
		 html = html.replace("{PERF_COMMENTS}", codeMetrics.getPerformanceComments());
		 html = html.replace("{SCAL_COMMENTS}", codeMetrics.getScalabilityComments());
		 html = html.replace("{SEC_COMMENTS}", codeMetrics.getSecurityComments());
		 html = html.replace("{FLEX_COMMENTS}", codeMetrics.getFlexibilityComments());
		 html = html.replace("{ADAPT_COMMENTS}", codeMetrics.getAdaptibilityComments());
		 html = html.replace("{TEST_COMMENTS}", codeMetrics.getTestCasesComments());
		 Test test = testService.findbyTest(instance.getTestName(), instance.getCompanyId());
		 EmailGenericMessageThread client = new EmailGenericMessageThread(test.getCreatedBy(), "FullStack Project Code Review for "+user2.getFirstName()+" "+user2.getLastName()+" for test- "+test.getTestName(), html, propertyConfig);
		 String cc[] = {user.getEmail()};
		 Thread th = new Thread(client);
		 th.start();
	 }

}
