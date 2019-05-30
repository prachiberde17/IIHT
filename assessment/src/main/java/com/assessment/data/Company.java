package com.assessment.data;

import javax.persistence.Entity;

/**
 * Primary key companyId
 * @author jsutaria
 *
 */
@Entity
public class Company extends Base{
	
	String companyLocation;
	
	String singlePointOfContactEmail;
	
	String singlePointOfContactPhone;
	
	String logoBigUrl;
	
	String logoSmallUrl;

	public String getCompanyLocation() {
		return companyLocation;
	}

	public void setCompanyLocation(String companyLocation) {
		this.companyLocation = companyLocation;
	}

	public String getSinglePointOfContactEmail() {
		return singlePointOfContactEmail;
	}

	public void setSinglePointOfContactEmail(String singlePointOfContactEmail) {
		this.singlePointOfContactEmail = singlePointOfContactEmail;
	}

	public String getSinglePointOfContactPhone() {
		return singlePointOfContactPhone;
	}

	public void setSinglePointOfContactPhone(String singlePointOfContactPhone) {
		this.singlePointOfContactPhone = singlePointOfContactPhone;
	}

	public String getLogoBigUrl() {
		return logoBigUrl;
	}

	public void setLogoBigUrl(String logoBigUrl) {
		this.logoBigUrl = logoBigUrl;
	}

	public String getLogoSmallUrl() {
		return logoSmallUrl;
	}

	public void setLogoSmallUrl(String logoSmallUrl) {
		this.logoSmallUrl = logoSmallUrl;
	}
	
	

}
