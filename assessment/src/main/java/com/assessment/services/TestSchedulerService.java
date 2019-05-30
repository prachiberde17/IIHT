package com.assessment.services;

import java.util.List;

import com.assessment.data.TestScheduler;

public interface TestSchedulerService {
	
	public void saveOrUpdate(TestScheduler scheduler );
	
	public List<TestScheduler> getTestSchedulersByCompany(String companyId);
	
	public void removeById(Long id);

}
