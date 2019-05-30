package com.assessment.data;

public enum QuestionType {
	
MCQ("MCQ"), CODING("CODING"), FULL_STACK_JAVA("FULL_STACK_JAVA"), FULLSTACK("FULLSTACK");
	
	String type;
	private QuestionType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	
	
	

}
