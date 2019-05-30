package com.test;

import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.repositories.TestSchedulerRepository;
import com.assessment.scheduler.ScheduleTaskService;
import com.assessment.services.TestSchedulerService;
import com.assessment.services.TestService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestScheduler {
	
	@Autowired
	ScheduleTaskService scheduleTaskService;
	
	@Autowired
	TaskScheduler scheduler;
	
	@Autowired
	TestService testService;
	@Autowired
	TestSchedulerService testSchedulerService;
	@Autowired
	TestSchedulerRepository rep;
	@Test
	@Rollback(value=false)
	public void testGetSch(){
		com.assessment.data.TestScheduler  testScheduler = rep.findById(2778l).get();
		com.assessment.data.TestScheduler sch = new com.assessment.data.TestScheduler();
		Mapper mapper = new DozerBeanMapper();
		mapper.map(testScheduler, sch);
		sch.getUserEmails().clear();
		sch.getUserEmails().add("asif.khan@abc.com");
		testSchedulerService.saveOrUpdate(sch);
		System.out.println("domne");
	}
	
	@Test
	public void testSchedulerRun(){
//	   ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
//       threadPoolTaskScheduler.setPoolSize(2);
//       threadPoolTaskScheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
//       scheduleTaskService.setScheduler(threadPoolTaskScheduler);
       TimeZone timeZone = TimeZone.getTimeZone("Asia/Kolkata");
       CronTrigger cronTrigger = new CronTrigger("0 35 13 18 2 ?", timeZone);
      // cronTrigger.
       ScheduledFuture future =  scheduleTaskService.addTaskToScheduler(1, new Task(), cronTrigger);
     //  future.cancel(true);
      // scheduleTaskService.getScheduler().
       System.out.println("done");
	}
	
	@Test
	@Rollback(value=false)
	public void testScheduleRunWithObject(){
		com.assessment.data.TestScheduler  testScheduler = new com.assessment.data.TestScheduler();
		testScheduler.setId(2778l);
		testScheduler.setCompanyId("IH");
		testScheduler.setCompanyName("IIHT");
		testScheduler.setCronExpression("0 14 17 18 2 ?");
		com.assessment.data.Test test = testService.findTestById(2481l);
		String user = "asif.khan@abc.com";
		testScheduler.setTestId(test.getId());
		testScheduler.setTestName(test.getTestName());
		testScheduler.getUserEmails().add(user);
		testSchedulerService.saveOrUpdate(testScheduler);
		System.out.println("done");
	}
	
	
	
}

class Task implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Running the task");
	}
	
}
