package com.assessment.web.controllers;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.data.Test;
import com.assessment.data.User;
import com.assessment.services.TestService;
import com.assessment.services.UserService;

@Controller
public class TestUserSessionController {
	@Autowired
	UserService userService;
	
	@Autowired
	TestService testService;
	
	@RequestMapping(value = "/startTest", method = RequestMethod.GET)
	  public ModelAndView testlist(@RequestParam String userId, @RequestParam String companyId, @RequestParam String testId, HttpServletRequest request, HttpServletResponse response) {
		    ModelAndView mav = new ModelAndView("studentTestJourney");
		    byte u[] = Base64.getDecoder().decode(userId);
		    String email = new String(u);
		    User user = userService.findByPrimaryKey(email, companyId);
		    Test test = testService.findTestById(Long.valueOf(testId));
		    mav.addObject("user", user);
		    mav.addObject("test", test);
		    return mav;
		  }
}
