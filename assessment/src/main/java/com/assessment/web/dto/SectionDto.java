package com.assessment.web.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.assessment.data.Question;

public class SectionDto {
	
	String sectionName;

	String testName;
	
	String companyId;
	
	Long sectionId;
	
	Set<Question> questions = new HashSet<>();

	
	Integer sectionNo;
	

	Boolean current = false;

	String style;
	
	Integer percentQuestionsAsked = 100;
	
	Integer noOfQuestions;

	public SectionDto() {
		
	}
	
	public SectionDto(String sectionN) {
		this.sectionName = sectionN;
	}

	

	public Integer getSectionNo() {
		return sectionNo;
	}

	public void setSectionNo(Integer sectionNo) {
		this.sectionNo = sectionNo;
	}

	public Boolean getCurrent() {
		return current;
	}

	public void setCurrent(Boolean current) {
		this.current = current;
	}

	public String getStyle() {
		if(getCurrent()) {
			return "border-bottom: 1px solid #ddd;cursor: pointer;padding-left: 15px;padding-bottom: 5px;background-color: #b5e7a0;";
		}
		else {
			return "border-bottom: 1px solid #ddd;cursor: pointer;padding-left: 15px;padding-bottom: 5px;";
		}
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

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

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	@Override
	public int hashCode() {
		return getSectionName().hashCode();
	}
	
	@Override
	public boolean equals(Object object) {
		if(! (object instanceof SectionDto)) {
			return false;
		}
		
		if(object.hashCode() == this.hashCode()) {
			return true;
		}
		
		return false;
	}

	public Integer getPercentQuestionsAsked() {
		return percentQuestionsAsked;
	}

	public void setPercentQuestionsAsked(Integer percentQuestionsAsked) {
		this.percentQuestionsAsked = percentQuestionsAsked;
	}

	public Integer getNoOfQuestions() {
		return noOfQuestions;
	}

	public void setNoOfQuestions(Integer noOfQuestions) {
		this.noOfQuestions = noOfQuestions;
	}
	
	
}
