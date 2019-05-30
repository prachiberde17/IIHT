package com.assessment.data;

public enum Status {

	
NOT_STARTED("NOT_STARTED"), IN_PROGRESS("IN_PROGRESS"), COMPLETE("COMPLETE");
	
	private String level;
	
	private Status(String level){
		this.level = level;
	}

	public String getLevel() {
		return level;
	}
}
