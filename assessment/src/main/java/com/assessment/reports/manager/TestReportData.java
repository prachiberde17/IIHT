package com.assessment.reports.manager;

public class TestReportData {
	
	String testName;
	
	String createdBy;
	
	Float overallAverageScore;
	
	Integer noOfSessions;
	
	String skills;
		
	String sectionsInfo;
	
	String averageScoreSummary; 
	
	
	
	String topCandidates;
	
	String topCandidatesEmail;
	
	public TestReportData() {
		
	}
	
public TestReportData(String testName, String createdBy, Float overallAverageScore, Integer noOfSessions) {
		this.testName = testName;
		this.createdBy = createdBy;
		this.overallAverageScore = overallAverageScore;
		this.noOfSessions = noOfSessions;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	

	public Integer getNoOfSessions() {
		return noOfSessions;
	}

	public void setNoOfSessions(Integer noOfSessions) {
		this.noOfSessions = noOfSessions;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public Float getOverallAverageScore() {
		return overallAverageScore;
	}

	public void setOverallAverageScore(Float overallAverageScore) {
		this.overallAverageScore = overallAverageScore;
	}

	public String getAverageScoreSummary() {
		return averageScoreSummary;
	}

	public void setAverageScoreSummary(String averageScoreSummary) {
		this.averageScoreSummary = averageScoreSummary;
	}

	public String getTopCandidates() {
		return topCandidates;
	}

	public void setTopCandidates(String topCandidates) {
		this.topCandidates = topCandidates;
	}

	public String getSectionsInfo() {
		return sectionsInfo;
	}

	public void setSectionsInfo(String sectionsInfo) {
		this.sectionsInfo = sectionsInfo;
	}

	public String getTopCandidatesEmail() {
		return topCandidatesEmail;
	}

	public void setTopCandidatesEmail(String topCandidatesEmail) {
		this.topCandidatesEmail = topCandidatesEmail;
	}
	
	
	
	
	

}
