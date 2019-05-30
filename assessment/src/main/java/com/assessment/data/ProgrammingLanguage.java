package com.assessment.data;

public enum ProgrammingLanguage {
	
JAVA("JAVA"), C("C"), CPLUSPLUS("CPLUSPLUS"),  DotNet("DotNet"), CHASH("CHASH"), PYTHON("PYTHON"), PHP("PHP"), JAVASCRIPT("JAVASCRIPT");
	
	String language;
	private ProgrammingLanguage(String language) {
		this.language = language;
	}
	public String getLanguage() {
		return language;
	}
	
	

}
