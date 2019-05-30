package com.assessment.Exceptions;

public class AssessmentGenericException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AssessmentGenericException() {
		
	}
	
	public AssessmentGenericException(String errorCode) {
		super(errorCode);
	}
	
	public AssessmentGenericException(String errorCode, Throwable t) {
		super(errorCode, t);
	}

}
