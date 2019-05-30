package com.assessment.data;

public enum DifficultyLevel {
	
	EASY("EASY"), MEDIUM("MEDIUM"), DIFFICULT("DIFFICULT");
	
	String level;
	private DifficultyLevel(String level) {
		this.level = level;
	}
	public String getLevel() {
		return level;
	}
	
	
}
