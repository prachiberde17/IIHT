package com.assessment.data;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class UserTestMapping extends Base{
	@NotNull
	 String email;
	 String mobileNumber;
	 String firstName;
	 String lastName;
	
	 String testName;
	 
	 String testLink;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
	
//	@Override
//	public int hashCode() {
//		(getTestName()+getId()).ha
//		return getId().hashCode();
//	}
//	
//	@Override
//	public boolean equals(Object object) {
//		if(! (object instanceof User)) {
//			return false;
//		}
//		
//		User dto = (User) object;
//		if(dto.hashCode() == hashCode()) {
//			return true;
//		}
//		
//		return false;
//	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getTestLink() {
		return testLink;
	}
	public void setTestLink(String testLink) {
		this.testLink = testLink;
	}

}
