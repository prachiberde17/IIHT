package com.assessment.reports.manager;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Only for test purpose
 * @author jsutaria
 *
 */
public class AssessmentTestData {

	Float percentageMarksRecieved;
	
	String testName;
	
	String user;
	
	Integer noOfAttempts;
	
	Boolean pass;
	
	String sectionResults;
	
	String companyId;
	
	String resultInString;
	
	Date testInviteSent;
	
	Boolean sharedDirect = false;
	
	String noOfQuestionsNotAnswered;
	
	Date testStartDate;
	
	Date testEndDate;
	
	public AssessmentTestData() {
		
	}
	
	public AssessmentTestData(Float percentageMarksRecieved, String testName, String user, Integer noOfAttempts, Boolean pass, String sectionResults,String companyId, Date testInviteSent, Boolean sharedDirect, String noOfQuestionsNotAnswered, Date testStartDate, Date testEndDate) {
		this.percentageMarksRecieved = percentageMarksRecieved;
		this.testName = testName;
		this.user = user;
		this.noOfAttempts = noOfAttempts;
		this.pass = pass;
		this.sectionResults = sectionResults;
		this.companyId = companyId;
		
		this.testInviteSent = testInviteSent;
		this.sharedDirect = sharedDirect;
		this.noOfQuestionsNotAnswered = noOfQuestionsNotAnswered;
		this.testStartDate = testStartDate;
		this.testEndDate = testEndDate;
	}

	public Float getPercentageMarksRecieved() {
		return percentageMarksRecieved;
	}

	public void setPercentageMarksRecieved(Float percentageMarksRecieved) {
		this.percentageMarksRecieved = percentageMarksRecieved;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Integer getNoOfAttempts() {
		return noOfAttempts;
	}

	public void setNoOfAttempts(Integer noOfAttempts) {
		this.noOfAttempts = noOfAttempts;
	}

	public Boolean getPass() {
		return pass;
	}

	public void setPass(Boolean pass) {
		this.pass = pass;
	}

	public String getSectionResults() {
		return sectionResults;
	}

	public void setSectionResults(String sectionResults) {
		this.sectionResults = sectionResults;
	}

	public String getResultInString() {
		return resultInString;
	}

	public void setResultInString(String resultInString) {
		this.resultInString = resultInString;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public Date getTestInviteSent() {
		return testInviteSent;
	}

	public void setTestInviteSent(Date testInviteSent) {
		this.testInviteSent = testInviteSent;
	}

	public Boolean getSharedDirect() {
		return sharedDirect;
	}

	public void setSharedDirect(Boolean sharedDirect) {
		this.sharedDirect = sharedDirect;
	}
	
	public String invitationDate() {
		
		if(getTestInviteSent() != null) {
			String pattern = "dd-MM-yyyy HH:mm:ss";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			return simpleDateFormat.format(getTestInviteSent());
		}
		else {
			return "NA";
		}
	}

	public String getNoOfQuestionsNotAnswered() {
		return noOfQuestionsNotAnswered;
	}

	public void setNoOfQuestionsNotAnswered(String noOfQuestionsNotAnswered) {
		this.noOfQuestionsNotAnswered = noOfQuestionsNotAnswered;
	}

	public Date getTestStartDate() {
		return testStartDate;
	}

	public void setTestStartDate(Date testStartDate) {
		this.testStartDate = testStartDate;
	}

	public Date getTestEndDate() {
		return testEndDate;
	}

	public void setTestEndDate(Date testEndDate) {
		this.testEndDate = testEndDate;
	}


	
	
	
	
}
