package com.assessment.web.dto;

import com.assessment.data.Question;

public class QuestionDto {

	
	Long questionId;
	
	String sectionName;
	
	Boolean selected = false;

	

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	
	@Override
	public int hashCode() {
		return getQuestionId().hashCode();
	}
	
	@Override
	public boolean equals(Object object) {
		if(! (object instanceof QuestionDto)) {
			return false;
		}
		
		QuestionDto dto = (QuestionDto) object;
		if(dto.hashCode() == hashCode()) {
			return true;
		}
		
		return false;
	}
	
}
