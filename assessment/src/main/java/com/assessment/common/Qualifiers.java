package com.assessment.common;

import com.mchange.v2.encounter.StrongEqualityEncounterCounter;

public class Qualifiers {
	
	private String qualifier1;
	
	private String qualifier2;
	
	private String qualifier3;
	
	private String qualifier4;
	
	private String qualifier5;
	
	public Qualifiers(){
		
	}
	
	public Qualifiers(String qualifier1, String qualifier2, String qualifier3, String qualifier4, String qualifier5){
		this.qualifier1 = qualifier1;
		this.qualifier2 = qualifier2;
		this.qualifier3 = qualifier3;
		this.qualifier4 = qualifier4;
		this.qualifier5 = qualifier5;
	}

	public String getQualifier1() {
		return qualifier1;
	}

	public void setQualifier1(String qualifier1) {
		this.qualifier1 = qualifier1;
	}

	public String getQualifier2() {
		return qualifier2;
	}

	public void setQualifier2(String qualifier2) {
		this.qualifier2 = qualifier2;
	}

	public String getQualifier3() {
		return qualifier3;
	}

	public void setQualifier3(String qualifier3) {
		this.qualifier3 = qualifier3;
	}

	public String getQualifier4() {
		return qualifier4;
	}

	public void setQualifier4(String qualifier4) {
		this.qualifier4 = qualifier4;
	}

	public String getQualifier5() {
		return qualifier5;
	}

	public void setQualifier5(String qualifier5) {
		this.qualifier5 = qualifier5;
	}

	@Override
	public int hashCode(){
		if(getQualifier2() == null){
			setQualifier2("");
		}
		
		if(getQualifier3() == null){
			setQualifier3("");
		}
		
		if(getQualifier4() == null){
			setQualifier4("");
		}
		
		if(getQualifier5() == null){
			setQualifier5("");
		}
		
		return (getQualifier1()+getQualifier2()+getQualifier3()+getQualifier4()+getQualifier5()).hashCode();
	}
	
	
	public void setNA(){
		if(getQualifier2() == null || getQualifier2().length() == 0 ){
			setQualifier2("NA");
		}
		
		if(getQualifier3() == null || getQualifier3().length() == 0 ){
			setQualifier3("NA");
		}
		
		if(getQualifier4() == null || getQualifier4().length() == 0 ){
			setQualifier4("NA");
		}
		
		if(getQualifier5() == null || getQualifier5().length() == 0 ){
			setQualifier5("NA");
		}
	}
	
	@Override
	public String toString(){
		setNA();
		return getQualifier1()+"-"+getQualifier2()+"-"+getQualifier3()+"-"+getQualifier4()+"-"+getQualifier5();
	}
}
