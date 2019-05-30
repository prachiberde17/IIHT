package com.assessment.web.dto;

public class CodeReportData {
	
	Long questionMapperInstanceId;

	String firstName;
	
	String lastName;
	
	String email;
	
	String testName;
	
	String overallScore;
	
	String pass;
	
	String problemStatement;
	
	String outputCode;
	
	Boolean analysisApplicable;

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

	public String getOverallScore() {
		return overallScore;
	}

	public void setOverallScore(String overallScore) {
		this.overallScore = overallScore;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getProblemStatement() {
		return problemStatement;
	}

	public void setProblemStatement(String problemStatement) {
		this.problemStatement = problemStatement;
	}

	public String getOutputCode() {
		return outputCode;
	}

	public void setOutputCode(String outputCode) {
		this.outputCode = outputCode;
	}

	public Boolean getAnalysisApplicable() {
		return analysisApplicable;
	}

	public void setAnalysisApplicable(Boolean analysisApplicable) {
		this.analysisApplicable = analysisApplicable;
	}

	public Long getQuestionMapperInstanceId() {
		return questionMapperInstanceId;
	}

	public void setQuestionMapperInstanceId(Long questionMapperInstanceId) {
		this.questionMapperInstanceId = questionMapperInstanceId;
	}
	
	
}
