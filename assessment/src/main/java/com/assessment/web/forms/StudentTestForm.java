package com.assessment.web.forms;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.assessment.data.Section;

public class StudentTestForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String emailId;
	private String testName;
	private int totalQuestions;
	private int duration;
	private Date publishedDate;
	private Boolean agreed;
	private String currentTime;
	private String testId;
	private String companyId;
	private  List<Section> sections;
	
	String firstName;
	String lastName;
	
	String lastUpdated;
	
	String testCreatedBy;
	
	Boolean testComplete;
	
	String testCreationDate;
	
	Integer noOfAttempts = 0;
	
	String testCreatorName;
	
	String domain;
	
	Date testInviteSent;
	
	Boolean sharedDirect = false;
	
	String techLogo;
	
	public Boolean getSharedDirect() {
		return sharedDirect;
	}
	public void setSharedDirect(Boolean sharedDirect) {
		this.sharedDirect = sharedDirect;
	}
	private Long totalTestTimeElapsedInSeconds = 0l;
	public List<Section> getSections() {
		return sections;
	}
	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public int getTotalQuestions() {
		return totalQuestions;
	}
	public void setTotalQuestions(int totalQuestions) {
		this.totalQuestions = totalQuestions;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Date getPublishedDate() {
		return publishedDate;
	}
	
	public String getFormattedPublishedDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
			if(getPublishedDate() != null) {
				return dateFormat.format(getPublishedDate());
			}
		return "NA";
	}
	
	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}
	public Boolean getAgreed() {
		return agreed;
	}
	public void setAgreed(Boolean agreed) {
		this.agreed = agreed;
	}
	public String getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}
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
	public String getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public Integer getNoOfAttempts() {
		return noOfAttempts;
	}
	public void setNoOfAttempts(Integer noOfAttempts) {
		this.noOfAttempts = noOfAttempts;
	}
	public String getTestCreatedBy() {
		return testCreatedBy;
	}
	public void setTestCreatedBy(String testCreatedBy) {
		this.testCreatedBy = testCreatedBy;
	}
	public Boolean getTestComplete() {
		return testComplete;
	}
	public void setTestComplete(Boolean testComplete) {
		this.testComplete = testComplete;
	}
	public String getTestCreationDate() {
		return testCreationDate;
	}
	public void setTestCreationDate(String testCreationDate) {
		this.testCreationDate = testCreationDate;
	}
	public Long getTotalTestTimeElapsedInSeconds() {
		return totalTestTimeElapsedInSeconds;
	}
	public void setTotalTestTimeElapsedInSeconds(Long totalTestTimeElapsedInSeconds) {
		this.totalTestTimeElapsedInSeconds = totalTestTimeElapsedInSeconds;
	}
	public String getTestCreatorName() {
		return testCreatorName;
	}
	public void setTestCreatorName(String testCreatorName) {
		this.testCreatorName = testCreatorName;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public Date getTestInviteSent() {
		return testInviteSent;
	}
	public void setTestInviteSent(Date testInviteSent) {
		this.testInviteSent = testInviteSent;
	}
	public String getTechLogo() {
		return techLogo;
	}
	public void setTechLogo(String techLogo) {
		this.techLogo = techLogo;
	}
	
	
	
	
	
	
	
}
