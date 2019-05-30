package com.assessment.data;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
@Entity
public class SectionInstance extends Base{
	@NotNull
	String sectionName;
	
	@NotNull
	String testName;
	
	 @NotNull
	Long startTime;
	 
	 @NotNull
	Long endTime;
	 
	 @NotNull
	String user;
	 
	String results;
	
	Integer noOfQuestionsNotAnswered = 0;

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public Integer getNoOfQuestionsNotAnswered() {
		return noOfQuestionsNotAnswered;
	}

	public void setNoOfQuestionsNotAnswered(Integer noOfQuestionsNotAnswered) {
		this.noOfQuestionsNotAnswered = noOfQuestionsNotAnswered;
	}
	
	
	
}
