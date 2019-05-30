package com.assessment.data;

import javax.persistence.Entity;

@Entity
public class Tenant  extends Base{
	

	String spoc;
	
	String databaseSchema;
	
	String schemaUser;

	String spocFirstName;
	
	String spocLastName;
	
	String spocMobile;
	
	String smallLogoUrl;
	
	String mediumLogoUrl;
	
	String largeLogoUrl;

	public String getSpoc() {
		return spoc;
	}

	public void setSpoc(String spoc) {
		this.spoc = spoc;
	}

	

	public String getDatabaseSchema() {
		return databaseSchema;
	}

	public void setDatabaseSchema(String databaseSchema) {
		this.databaseSchema = databaseSchema;
	}

	public String getSchemaUser() {
		return schemaUser;
	}

	public void setSchemaUser(String schemaUser) {
		this.schemaUser = schemaUser;
	}

	public String getSpocFirstName() {
		return spocFirstName;
	}

	public void setSpocFirstName(String spocFirstName) {
		this.spocFirstName = spocFirstName;
	}

	public String getSpocLastName() {
		return spocLastName;
	}

	public void setSpocLastName(String spocLastName) {
		this.spocLastName = spocLastName;
	}

	public String getSmallLogoUrl() {
		return smallLogoUrl;
	}

	public void setSmallLogoUrl(String smallLogoUrl) {
		this.smallLogoUrl = smallLogoUrl;
	}

	public String getMediumLogoUrl() {
		return mediumLogoUrl;
	}

	public void setMediumLogoUrl(String mediumLogoUrl) {
		this.mediumLogoUrl = mediumLogoUrl;
	}

	public String getLargeLogoUrl() {
		return largeLogoUrl;
	}

	public void setLargeLogoUrl(String largeLogoUrl) {
		this.largeLogoUrl = largeLogoUrl;
	}

	public String getSpocMobile() {
		return spocMobile;
	}

	public void setSpocMobile(String spocMobile) {
		this.spocMobile = spocMobile;
	}
	
	

}
