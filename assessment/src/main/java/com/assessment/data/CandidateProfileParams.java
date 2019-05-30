package com.assessment.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class CandidateProfileParams extends Base{

	
	private String qualifier1;
	
	private String qualifier2 = "NA";
	
	private String qualifier3 = "NA";
	
	private String qualifier4 = "NA";
	
	private String qualifier5 = "NA";
	
	String question = "NA";
	
	@Column(length=555)
	String LESS_THAN_TWENTY_PERCENT = "NA";
	
	@Column(length=555)
	String BETWEEN_TWENTY_AND_FIFTY = "NA";
	
	@Column(length=555)
	String BETWEEN_FIFTY_AND_SEVENTYFIVE = "NA";
	
	@Column(length=555)
	String BETWEEN_SEVENTYFIVE_AND_NINETY = "NA";
	
	@Column(length=555)
	String MORE_THAN_NINETY = "NA";
	
	@Transient
	String context;
	
	public CandidateProfileParams(){
		
	}
	
	public CandidateProfileParams(String qualifier1, String qualifier2, String qualifier3, String qualifier4, String qualifier5){
		this.qualifier1 = qualifier1;
		this.qualifier2  = qualifier2;
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
		return (qualifier2 == null || qualifier2.length()==0)?"NA":this.qualifier2;
	}

	public void setQualifier2(String qualifier2) {
		this.qualifier2 = qualifier2;
	}

	public String getQualifier3() {
		return (qualifier3 == null || qualifier3.length()==0)?"NA":this.qualifier3;
	}

	public void setQualifier3(String qualifier3) {
		this.qualifier3 = qualifier3;
	}

	public String getQualifier4() {
		return (qualifier4 == null || qualifier4.length()==0)?"NA" :this.qualifier4;
	}

	public void setQualifier4(String qualifier4) {
		this.qualifier4 = qualifier4;
	}

	public String getQualifier5() {
		return (qualifier5 == null || qualifier4.length()==0)?"NA":this.qualifier5;
	}

	public void setQualifier5(String qualifier5) {
		this.qualifier5 = qualifier5;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getLESS_THAN_TWENTY_PERCENT() {
		if(this.LESS_THAN_TWENTY_PERCENT != null && this.LESS_THAN_TWENTY_PERCENT.equals("NA")){
			return "";
		}
		return LESS_THAN_TWENTY_PERCENT;
	}

	public void setLESS_THAN_TWENTY_PERCENT(String lESS_THAN_TWENTY_PERCENT) {
		LESS_THAN_TWENTY_PERCENT = lESS_THAN_TWENTY_PERCENT;
	}

	public String getBETWEEN_TWENTY_AND_FIFTY() {
		if(this.BETWEEN_TWENTY_AND_FIFTY != null && this.BETWEEN_TWENTY_AND_FIFTY.equals("NA")){
			return "";
		}
		return BETWEEN_TWENTY_AND_FIFTY;
	}

	public void setBETWEEN_TWENTY_AND_FIFTY(String bETWEEN_TWENTY_AND_FIFTY) {
		BETWEEN_TWENTY_AND_FIFTY = bETWEEN_TWENTY_AND_FIFTY;
	}

	public String getBETWEEN_FIFTY_AND_SEVENTYFIVE() {
		if(this.BETWEEN_FIFTY_AND_SEVENTYFIVE != null && this.BETWEEN_FIFTY_AND_SEVENTYFIVE.equals("NA")){
			return "";
		}
		return BETWEEN_FIFTY_AND_SEVENTYFIVE;
	}

	public void setBETWEEN_FIFTY_AND_SEVENTYFIVE(String bETWEEN_FIFTY_AND_SEVENTYFIVE) {
		BETWEEN_FIFTY_AND_SEVENTYFIVE = bETWEEN_FIFTY_AND_SEVENTYFIVE;
	}

	public String getBETWEEN_SEVENTYFIVE_AND_NINETY() {
		if(this.BETWEEN_SEVENTYFIVE_AND_NINETY != null && this.BETWEEN_SEVENTYFIVE_AND_NINETY.equals("NA")){
			return "";
		}
		return BETWEEN_SEVENTYFIVE_AND_NINETY;
	}

	public void setBETWEEN_SEVENTYFIVE_AND_NINETY(String bETWEEN_SEVENTYFIVE_AND_NINETY) {
		BETWEEN_SEVENTYFIVE_AND_NINETY = bETWEEN_SEVENTYFIVE_AND_NINETY;
	}

	public String getMORE_THAN_NINETY() {
		if(this.MORE_THAN_NINETY != null && this.MORE_THAN_NINETY.equals("NA")){
			return "";
		}
		return MORE_THAN_NINETY;
	}

	public void setMORE_THAN_NINETY(String mORE_THAN_NINETY) {
		MORE_THAN_NINETY = mORE_THAN_NINETY;
	}
	
	@Override
	public int hashCode(){
		return (getQualifier1()+getQualifier2()+getQualifier3()+getQualifier4()+getQualifier5()).hashCode();
	}
	
	@Override
	public boolean equals(Object object){
		if(!(object instanceof CandidateProfileParams)){
			return false;
		}
		
		return this.hashCode() == object.hashCode();
	}
	@Override
	public String toString(){
		return getQualifier1()+getQualifier2()+getQualifier3()+getQualifier4()+getQualifier5();
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
	
	
	
}
