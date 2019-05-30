package com.assessment.web.dto;

public class UserDto {

	Long id;
	 String email;
	 String mobileNumber;
	 String firstName;
	 String lastName;
	 
	 String password;
	 String department;
	
	String userType;
	
	String groupOfUser;
	
	String grade;
	
	Boolean internalUser;
	
	Boolean selected;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getGroupOfUser() {
		return groupOfUser;
	}

	public void setGroupOfUser(String groupOfUser) {
		this.groupOfUser = groupOfUser;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Boolean getInternalUser() {
		return internalUser;
	}

	public void setInternalUser(Boolean internalUser) {
		this.internalUser = internalUser;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	
	
}
