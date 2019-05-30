package com.assessment.reports.manager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AssessmentUserPerspectiveData {
	
	Logger logger = LoggerFactory.getLogger(AssessmentUserPerspectiveData.class);

	String firstName;
	
	String lastName;
	
	String email;
	
	String testName;
	
	Float overAllScore;
	
	String sectionWiseScore;
	
	Boolean pass;
	
	String result;
	
	Integer noOfAttempts;
	
	String reportCreationDate;
	
	String reportCreatedBy;
	
	Date testInviteSent;
	
	Boolean sharedDirect = false;
	
	String noOfQuestionsNotAnswered;
	
	Map<String, String> sections_score = new HashMap<>();
	
	Map<String, String> sections_noOfQuestionsNotAnswered = new HashMap<>();
	
	String companyId;

	String urlForUserSession;
	
	String testStartDate;
	
	String testEndDate;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Float getOverAllScore() {
		return overAllScore;
	}

	public void setOverAllScore(Float overAllScore) {
		this.overAllScore = overAllScore;
	}

	public String getSectionWiseScore() {
		return sectionWiseScore;
	}

	public void setSectionWiseScore(String sectionWiseScore) {
		this.sectionWiseScore = sectionWiseScore;
		if(sectionWiseScore!= null) {
			String secs[] = sectionWiseScore.split(",");
			for(String s : secs) {
				String s0[] = s.split("-");
				if(s0.length == 1){
					String s1[] = new String[2];
					s1[0] = s0[0];
					s1[1] = "0.0";
					s0 = s1;
				}
				try {
					sections_score.put(s0[0], s0[1]);
				} catch (ArrayIndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					logger.error("problem ..."+s0, e);
					System.out.println(s0);
					e.printStackTrace();
					throw e;
					
				}
			}
		}
	}

	public Boolean getPass() {
		return pass;
	}

	public void setPass(Boolean pass) {
		this.pass = pass;
	}

	public Integer getNoOfAttempts() {
		return noOfAttempts;
	}

	public void setNoOfAttempts(Integer noOfAttempts) {
		this.noOfAttempts = noOfAttempts;
	}

	public String getReportCreationDate() {
		return reportCreationDate;
	}

	public void setReportCreationDate(String reportCreationDate) {
		this.reportCreationDate = reportCreationDate;
	}

	public String getReportCreatedBy() {
		return reportCreatedBy;
	}

	public void setReportCreatedBy(String reportCreatedBy) {
		this.reportCreatedBy = reportCreatedBy;
	}

	public String getResult() {
			if(getPass()) {
				return "Pass";
			}
		return "Fail";
	}

	public void setResult(String result) {
		this.result = result;
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

	
	
	public String getNoOfQuestionsNotAnswered() {
		return noOfQuestionsNotAnswered;
	}

	public void setNoOfQuestionsNotAnswered(String noOfQuestionsNotAnswered) {
		this.noOfQuestionsNotAnswered = noOfQuestionsNotAnswered;
		if(getNoOfQuestionsNotAnswered() != null) {
			String secs[] = (noOfQuestionsNotAnswered+"").split(",");
			for(String s : secs) {
				String s0[] = s.split("-");
				if(s0.length == 1){
					String s1[] = new String[2];
					s1[0] = s0[0];
					s1[1] = "0.0";
					s0 = s1;
				}
				try {
					sections_noOfQuestionsNotAnswered.put(s0[0], s0[1]);
				} catch (ArrayIndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					logger.error("problem ..."+s0, e);
					System.out.println(s0);
					e.printStackTrace();
					throw e;
					
				}
			}
		}
		
	}

	public String getDateOfInvitation() {
		
		if(getTestInviteSent() != null) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
			return dateFormat.format(getTestInviteSent());
		}
		else {
			return "NA";
		}
	}

	public Map<String, String> getSections_score() {
		return sections_score;
	}

	public void setSections_score(Map<String, String> sections_score) {
		this.sections_score = sections_score;
	}

	public Map<String, String> getSections_noOfQuestionsNotAnswered() {
		return sections_noOfQuestionsNotAnswered;
	}

	public void setSections_noOfQuestionsNotAnswered(Map<String, String> sections_noOfQuestionsNotAnswered) {
		this.sections_noOfQuestionsNotAnswered = sections_noOfQuestionsNotAnswered;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getUrlForUserSession() {
		return urlForUserSession;
	}

	public void setUrlForUserSession(String urlForUserSession) {
		this.urlForUserSession = urlForUserSession;
	}

	public String getTestStartDate() {
		return testStartDate;
	}

	public void setTestStartDate(String testStartDate) {
		this.testStartDate = testStartDate;
	}

	public String getTestEndDate() {
		return testEndDate;
	}

	public void setTestEndDate(String testEndDate) {
		this.testEndDate = testEndDate;
	}
	
	
	
}
