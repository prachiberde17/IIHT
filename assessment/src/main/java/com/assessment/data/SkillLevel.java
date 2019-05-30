package com.assessment.data;

public enum SkillLevel {
	
	BASIC("BASIC"), INTERMEDIATE("INTERMEDIATE"), EXPERT("EXPERT");
	
	String level;
	
	private SkillLevel(String level) {
		this.level = level;
	}

	public String getLevel() {
		return level;
	}
	
	

}
