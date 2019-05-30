package com.assessment.web.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.SchedulerBeanDefinitionParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.data.Test;
import com.assessment.data.TestScheduler;
import com.assessment.data.User;
import com.assessment.repositories.TestSchedulerRepository;
import com.assessment.repositories.UserRepository;
import com.assessment.services.TestSchedulerService;
import com.assessment.services.TestService;
import com.assessment.services.UserService;
import com.assessment.web.dto.ScheduleDto;

@Controller
public class ScheduleController {

	@Autowired
	TestSchedulerService testScheduleService;
	
	@Autowired
	TestSchedulerRepository testSchedulerRep;
	
	@Autowired
	TestService testService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRep;
	
	@RequestMapping(value = "/showAllSchedules", method = RequestMethod.GET)
	public ModelAndView showAllSchedules(@RequestParam(name= "sch", required = false) String sch, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("schedules");
		mav = showParentScreen(mav, request);
		return mav;
	}
	
	@RequestMapping(value = "/deleteSchedule", method = RequestMethod.GET)
	public ModelAndView showAllSchedules(@RequestParam(name= "schId", required = true) Long schId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("schedules");
		testSchedulerRep.deleteById(schId);
		mav.addObject("message", "Save Success ");// later put it as label
		mav.addObject("msgtype", "Information");
		return showParentScreen(mav, request);
	}
	
	@RequestMapping(value = "/addSchedule", method = RequestMethod.GET)
	public ModelAndView addSchedule( HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		ModelAndView mav = new ModelAndView("schedule");
		List<User> users = userRep.findByCompany(user.getCompanyId()); 
		mav.addObject("users", users);
		List<Test> tests = testService.findByCompanyId(user.getCompanyId());
		mav.addObject("tests", tests);
		ScheduleDto dto = new ScheduleDto();
		mav.addObject("schedule", dto);
		return mav;
	}
	
	@RequestMapping(value = "/updateSchedule", method = RequestMethod.GET)
	public ModelAndView updateSchedule( @RequestParam(name= "schId", required = true) Long schId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		ModelAndView mav = new ModelAndView("schedule");
		List<User> users = userRep.findByCompany(user.getCompanyId()); 
		
		mav.addObject("users", users);
		List<Test> tests = testService.findByCompanyId(user.getCompanyId());
		mav.addObject("tests", tests);
		TestScheduler scheduler = testSchedulerRep.findById(schId).get();
		
		ScheduleDto dto = new ScheduleDto();
		String exp[] = scheduler.getCronExpression().split(" ");
		dto.setSeconds(exp[0]);
		dto.setMinutes(exp[1]);
		dto.setHours(exp[2]);
		dto.setDate(exp[3]);
		dto.setMonth(exp[4]);
		dto.setYear("?");
		dto.setTestId(scheduler.getTestId());
		dto.setTestName(scheduler.getTestName());
		dto.setScheduleId(scheduler.getId());
		dto.setUsers(scheduler.getUserEmails());
		mav.addObject("schedule", dto);
		return mav;
	}
	
	private ModelAndView showParentScreen(ModelAndView mav, HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		List<TestScheduler> list = testSchedulerRep.findByCompanyId(user.getCompanyId());
		mav.addObject("schedules", list);
		List<ScheduleDto> dtos = new ArrayList<>();
		for(TestScheduler scheduler : list){
			ScheduleDto dto = new ScheduleDto();
			String cron = scheduler.getCronExpression();
			String exp[] = cron.split(" ");
			dto.setSeconds(exp[0]);
			dto.setMinutes(exp[1]);
			dto.setHours(exp[2]);
			dto.setDate(exp[3]);
			dto.setMonth(exp[4]);
			dto.setYear("?");
			dto.setTestId(scheduler.getTestId());
			dto.setTestName(scheduler.getTestName());
			dto.setScheduleId(scheduler.getId());
			dtos.add(dto);
		}
		mav.addObject("dtos", dtos);
		
		
		return mav;
	}
	
	@RequestMapping(value = "/saveTestSchedule", method = RequestMethod.POST)
	public ModelAndView saveTestSchedule(ScheduleDto scheduleDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("schedules");
		User user = (User) request.getSession().getAttribute("user");
		Test test = testService.findTestById(scheduleDto.getTestId());
		TestScheduler scheduler = new TestScheduler();
		scheduler.setCompanyId(user.getCompanyId());
		scheduler.setCompanyName(user.getCompanyName());
		scheduler.setCronExpression(scheduleDto.getSeconds()+" "+scheduleDto.getMinutes()+" "+scheduleDto.getHours()+" "+scheduleDto.getDate()+" "+scheduleDto.getMonth()+" "+scheduleDto.getYear());
		scheduler.setTestId(scheduleDto.getTestId());
		scheduler.setTestName(test.getTestName());
		scheduler.setId(scheduleDto.getScheduleId());
		scheduler.setUserEmails(scheduleDto.getUsers());
		testScheduleService.saveOrUpdate(scheduler);
		mav.addObject("message", "Save Success ");// later put it as label
		mav.addObject("msgtype", "Information");
		mav =  showParentScreen(mav, request);
		return mav;
	}
}
