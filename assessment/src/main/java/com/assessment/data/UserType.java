package com.assessment.data;

public enum UserType {
	
	STUDENT("STUDENT"), ADMIN("ADMIN"), EVALUATOR("EVALUATOR"), SUPER_ADMIN("SUPER_ADMIN"), REVIEWER("REVIEWER");
	String type;
	
	private UserType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
	

}
