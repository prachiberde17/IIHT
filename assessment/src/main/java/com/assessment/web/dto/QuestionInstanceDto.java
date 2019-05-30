package com.assessment.web.dto;

import com.assessment.data.QuestionMapperInstance;

public class QuestionInstanceDto {

	QuestionMapperInstance questionMapperInstance;
	
	String style;
	
	
	Boolean one = false;
	
	Boolean two = false;
	
	Boolean three = false;
	
	Boolean four = false;
	
	Boolean five = false;
	
	Boolean six = false;
	
	String code;
	
	String input;
	
	String output;
	
	Integer position;
	
	Boolean confidence;
	
	Boolean confidentYes;
	
	Boolean confidentNo;

	public QuestionMapperInstance getQuestionMapperInstance() {
		return questionMapperInstance;
	}

	public void setQuestionMapperInstance(QuestionMapperInstance questionMapperInstance) {
		this.questionMapperInstance = questionMapperInstance;
	}

	public String getStyle() {
		if(getQuestionMapperInstance().getAnswered()) {
			return "answered";
		}
		else {
			return "notanswered";
		}
		
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public Boolean getOne() {
		return one;
	}

	public void setOne(Boolean one) {
		this.one = one;
	}

	public Boolean getTwo() {
		return two;
	}

	public void setTwo(Boolean two) {
		this.two = two;
	}

	public Boolean getThree() {
		return three;
	}

	public void setThree(Boolean three) {
		this.three = three;
	}

	public Boolean getFour() {
		return four;
	}

	public void setFour(Boolean four) {
		this.four = four;
	}

	public Boolean getFive() {
		return five;
	}

	public void setFive(Boolean five) {
		this.five = five;
	}

	public Boolean getSix() {
		return six;
	}

	public void setSix(Boolean six) {
		this.six = six;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public Boolean getConfidence() {
		return confidence;
	}

	public void setConfidence(Boolean confidence) {
		this.confidence = confidence;
	}

	public Boolean getConfidentYes() {
		return confidentYes;
	}

	public void setConfidentYes(Boolean confidentYes) {
		this.confidentYes = confidentYes;
		if(confidentYes){
			setConfidence(true);
		}
		else{
			setConfidence(false);
		}
	}

	public Boolean getConfidentNo() {
		return confidentNo;
	}

	public void setConfidentNo(Boolean confidentNo) {
		this.confidentNo = confidentNo;
		if(confidentNo){
			setConfidence(false);
		}
		else{
			setConfidence(true);
		}
	}

	
	
	
	
}
