package com.assessment.web.dto;

public class UserBySkillDTO {
	
	String firstName;
	
	String lastName;
	
	String email;
	
	String skill;
	
	String scoreInPercentage;
	
	Integer noOfQuestionsAttempted;
	
	String companyId;
	
	String tests;

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

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	

	public String getScoreInPercentage() {
		return scoreInPercentage;
	}

	public void setScoreInPercentage(String scoreInPercentage) {
		this.scoreInPercentage = scoreInPercentage;
	}

	public Integer getNoOfQuestionsAttempted() {
		return noOfQuestionsAttempted;
	}

	public void setNoOfQuestionsAttempted(Integer noOfQuestionsAttempted) {
		this.noOfQuestionsAttempted = noOfQuestionsAttempted;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getTests() {
		return tests;
	}

	public void setTests(String tests) {
		this.tests = tests;
	}
	
	
	
	

}
