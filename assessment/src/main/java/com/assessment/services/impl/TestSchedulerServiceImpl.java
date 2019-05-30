package com.assessment.services.impl;

import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.common.PropertyConfig;
import com.assessment.common.SchedulerTask;
import com.assessment.data.TestScheduler;
import com.assessment.repositories.TestSchedulerRepository;
import com.assessment.scheduler.ScheduleTaskService;
import com.assessment.services.TestSchedulerService;
@Service
@Transactional
public class TestSchedulerServiceImpl implements TestSchedulerService{
	@Autowired
	TestSchedulerRepository rep;
	
	@Autowired
	ScheduleTaskService schedulerService;
	
	@Autowired
	PropertyConfig config;
	
	

	@Override
	public void saveOrUpdate(TestScheduler scheduler) {
		// TODO Auto-generated method stub
		int id;
		if(scheduler.getId() == null){
			//create
			rep.save(scheduler);
			id = scheduler.getId().intValue();
		}
		else{
			TestScheduler scheduler2 = rep.findById(scheduler.getId()).get();
			scheduler2.getUserEmails().clear();
			//scheduler.setId(sch);
			Mapper mapper = new DozerBeanMapper();
			mapper.map(scheduler, scheduler2);
			rep.save(scheduler2);
			id = scheduler2.getId().intValue();
			schedulerService.removeTaskFromScheduler(id);
		}
		TimeZone timeZone = TimeZone.getTimeZone("Asia/Kolkata");
	       CronTrigger cronTrigger = new CronTrigger(scheduler.getCronExpression(), timeZone);
		SchedulerTask schedulerTask=  new SchedulerTask(scheduler.getTestId(), scheduler.getTestName(), scheduler.getCompanyId(), config.getBaseUrl(), scheduler.getUserEmails(), config.getTestLinkHtml_Generic_Location(), config);
		schedulerService.addTaskToScheduler(id, schedulerTask, cronTrigger);
	}

	@Override
	public List<TestScheduler> getTestSchedulersByCompany(String companyId) {
		// TODO Auto-generated method stub
		return rep.findByCompanyId(companyId);
	}

	@Override
	public void removeById(Long id) {
		// TODO Auto-generated method stub
		schedulerService.removeTaskFromScheduler(id.intValue());
		rep.deleteById(id);
	}

}
