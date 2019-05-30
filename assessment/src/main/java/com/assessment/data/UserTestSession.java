package com.assessment.data;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
/**
 * pk - user & test & companyId
 * @author jsutaria
 *
 */
@Entity
public class UserTestSession extends Base {

	@NotNull
	String user;
	@NotNull
	String testName;
	
	Integer timeTakenInMimnutes;
	@ManyToOne
	Test test;
	
	Boolean complete = false;
	
	Integer noOfAttempts;
	
	Float percentageMarksRecieved;
	
	Integer totalMarksRecieved;
	
	Integer totalMarks;
	
	String sectionResults;
	
	Boolean pass = false;
	
	Date testInviteSent;
	
	Boolean sharedDirect = false;
	
	Integer noOfQuestionsAnswered;
	
	String sectionsNoOfQuestionsNotAnswered;
	
	public Boolean getSharedDirect() {
		return sharedDirect;
	}
	public void setSharedDirect(Boolean sharedDirect) {
		this.sharedDirect = sharedDirect;
	}
	
//	@OneToMany
//	List<QuestionMapperInstance> questionMappersInstances;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Integer getTimeTakenInMimnutes() {
		return timeTakenInMimnutes;
	}

	public void setTimeTakenInMimnutes(Integer timeTakenInMimnutes) {
		this.timeTakenInMimnutes = timeTakenInMimnutes;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	public Integer getNoOfAttempts() {
		return noOfAttempts;
	}

	public void setNoOfAttempts(Integer noOfAttempts) {
		this.noOfAttempts = noOfAttempts;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Float getPercentageMarksRecieved() {
		return percentageMarksRecieved;
	}

	public void setPercentageMarksRecieved(Float percentageMarksRecieved) {
		this.percentageMarksRecieved = percentageMarksRecieved;
	}

	public Integer getTotalMarksRecieved() {
		return totalMarksRecieved;
	}

	public void setTotalMarksRecieved(Integer totalMarksRecieved) {
		this.totalMarksRecieved = totalMarksRecieved;
	}

	public Integer getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(Integer totalMarks) {
		this.totalMarks = totalMarks;
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

	public Date getTestInviteSent() {
		return testInviteSent;
	}

	public void setTestInviteSent(Date testInviteSent) {
		this.testInviteSent = testInviteSent;
	}
	public Integer getNoOfQuestionsAnswered() {
		return noOfQuestionsAnswered;
	}
	public void setNoOfQuestionsAnswered(Integer noOfQuestionsAnswered) {
		this.noOfQuestionsAnswered = noOfQuestionsAnswered;
	}
	public String getSectionsNoOfQuestionsNotAnswered() {
		return sectionsNoOfQuestionsNotAnswered;
	}
	public void setSectionsNoOfQuestionsNotAnswered(String sectionsNoOfQuestionsNotAnswered) {
		this.sectionsNoOfQuestionsNotAnswered = sectionsNoOfQuestionsNotAnswered;
	}

	
	
	
}
