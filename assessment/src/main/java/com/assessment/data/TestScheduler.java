package com.assessment.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Entity
public class TestScheduler extends Base{
	
	@Column(length=700)
	String testName;
	
	String cronExpression;
	
	@ElementCollection(fetch=FetchType.EAGER)
	List<String> userEmails = new ArrayList<>();
	
	Long testId;

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public List<String> getUserEmails() {
		return userEmails;
	}

	public void setUserEmails(List<String> userEmails) {
		this.userEmails = userEmails;
	}

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}
	
	

}
