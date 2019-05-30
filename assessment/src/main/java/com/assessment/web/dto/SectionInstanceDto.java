package com.assessment.web.dto;

import java.util.ArrayList;
import java.util.List;

import com.assessment.data.Section;

public class SectionInstanceDto {
	
	Section section;
	
	String style;
	
	Integer noOfQuestions;
	
	Boolean current = false;
	
	List<QuestionInstanceDto> questionInstanceDtos = new ArrayList<>();
	
	Boolean first = false;
	
	Boolean last = false;
	
	Integer totalCorrectAnswers = 0;
	
	Integer noOfQuestionsNotAnswered = 0;

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public String getStyle() {
		if(getCurrent()) {
			return "class=\"active\"";
		}
		return "";
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public Integer getNoOfQuestions() {
		return noOfQuestions;
	}

	public void setNoOfQuestions(Integer noOfQuestions) {
		this.noOfQuestions = noOfQuestions;
	}

	public Boolean getCurrent() {
		return current;
	}

	public void setCurrent(Boolean current) {
		this.current = current;
	}

	public List<QuestionInstanceDto> getQuestionInstanceDtos() {
		return questionInstanceDtos;
	}

	public void setQuestionInstanceDtos(List<QuestionInstanceDto> questionInstanceDtos) {
		this.questionInstanceDtos = questionInstanceDtos;
	}

	public Boolean getFirst() {
		return first;
	}

	public void setFirst(Boolean first) {
		this.first = first;
	}

	public Boolean getLast() {
		return last;
	}

	public void setLast(Boolean last) {
		this.last = last;
	}

	public Integer getTotalCorrectAnswers() {
		return totalCorrectAnswers;
	}

	public void setTotalCorrectAnswers(Integer totalCorrectAnswers) {
		this.totalCorrectAnswers = totalCorrectAnswers;
	}

	public Integer getNoOfQuestionsNotAnswered() {
		return noOfQuestionsNotAnswered;
	}

	public void setNoOfQuestionsNotAnswered(Integer noOfQuestionsNotAnswered) {
		this.noOfQuestionsNotAnswered = noOfQuestionsNotAnswered;
	}
	
	

}
