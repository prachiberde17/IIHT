package com.assessment.data;

public enum QualityIndice {
	
	
	
	EXCEPTIONAL("EXCEPTIONAL"), HIGH("HIGH"), AVERAGE("AVERAGE"), POOR("POOR"), NONE("NONE");
	
	private String level;
	
	private QualityIndice(String level){
		this.level = level;
	}

	public String getLevel() {
		return level;
	}
	
	

}
