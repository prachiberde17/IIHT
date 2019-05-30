package com.assessment.data;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class UserNonCompliance extends Base{
	
	
	
	String user;
	
	String testName;
	
	Long testId;
	
	
	
	Date date;
	
	Long userTestSessionId;
	
	Integer testAttemtByUser;
	
	Integer noOfNonCompliances = 0;

	

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getUserTestSessionId() {
		return userTestSessionId;
	}

	public void setUserTestSessionId(Long userTestSessionId) {
		this.userTestSessionId = userTestSessionId;
	}

	public Integer getNoOfNonCompliances() {
		return noOfNonCompliances;
	}

	public void setNoOfNonCompliances(Integer noOfNonCompliances) {
		this.noOfNonCompliances = noOfNonCompliances;
	}

	public Integer getTestAttemtByUser() {
		return testAttemtByUser;
	}

	public void setTestAttemtByUser(Integer testAttemtByUser) {
		this.testAttemtByUser = testAttemtByUser;
	}
	
	

}
