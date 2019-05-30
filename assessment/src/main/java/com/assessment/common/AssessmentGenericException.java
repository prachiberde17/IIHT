package com.assessment.common;

public class AssessmentGenericException extends RuntimeException{
	
	public AssessmentGenericException() {
		
	}
	
	public AssessmentGenericException(String errorCode) {
		super(errorCode);
	}
	
	public AssessmentGenericException(String errorCode, Throwable t) {
		super(errorCode, t);
	}

}
