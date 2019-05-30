package com.assessment.common;

import java.util.HashMap;
import java.util.Map;

import com.assessment.data.ProgrammingLanguage;

public class LanguageCodes {
	
	private static Map<String, String> map = new HashMap<>();
	
	static{
		
		map.put(ProgrammingLanguage.JAVA.getLanguage(), "8");
		map.put(ProgrammingLanguage.C.getLanguage(), "7");
		map.put(ProgrammingLanguage.CPLUSPLUS.getLanguage(), "7");
		map.put(ProgrammingLanguage.CHASH.getLanguage(), "10");
		map.put(ProgrammingLanguage.PYTHON.getLanguage(), "0");
		map.put(ProgrammingLanguage.PHP.getLanguage(), "3");
		map.put(ProgrammingLanguage.JAVASCRIPT.getLanguage(), "4");
	}
	
	public static String getLanguageCode(String prog){
		return map.get(prog);
	}

}
