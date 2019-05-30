package com.assessment.scheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

@Service
public class ScheduleTaskService {
	 // Task Scheduler
	@Autowired
    TaskScheduler scheduler;
    
    // A map for keeping scheduled tasks
    Map<Integer, ScheduledFuture<?>> jobsMap = new HashMap<>();
    
    public ScheduleTaskService(){
    	
    }
    
    public ScheduleTaskService(TaskScheduler scheduler) {
        this.scheduler = scheduler;
    }
    
    
    // Schedule Task to be executed every night at 00 or 12 am
    public ScheduledFuture addTaskToScheduler(int id, Runnable task, CronTrigger cronTrigger) {
        ScheduledFuture<?> scheduledTask = scheduler.schedule(task, cronTrigger);
        jobsMap.put(id, scheduledTask);
        return scheduledTask;
    }
    
    // Remove scheduled task 
    public void removeTaskFromScheduler(int id) {
        ScheduledFuture<?> scheduledTask = jobsMap.get(id);
        if(scheduledTask != null) {
            scheduledTask.cancel(true);
            jobsMap.put(id, null);
        }
       // scheduler.
    }
    
    // A context refresh event listener
    @EventListener({ ContextRefreshedEvent.class })
    void contextRefreshedEvent() {
        // Get all tasks from DB and reschedule them in case of context restarted
    }


	public TaskScheduler getScheduler() {
		return scheduler;
	}


	public void setScheduler(TaskScheduler scheduler) {
		this.scheduler = scheduler;
	}


	public Map<Integer, ScheduledFuture<?>> getJobsMap() {
		return jobsMap;
	}


	public void setJobsMap(Map<Integer, ScheduledFuture<?>> jobsMap) {
		this.jobsMap = jobsMap;
	}
    
    
}
