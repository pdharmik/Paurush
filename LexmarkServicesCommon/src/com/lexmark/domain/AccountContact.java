package com.lexmark.domain;

import java.io.Serializable;

public class AccountContact implements Serializable {
	private static final long serialVersionUID = 5102969776245512092L;
	private String contactId;
	private String department;
	private String workPhone;
	private String emailAddress;
	private boolean userFavorite;
	private String firstName;
	private String lastName;
	private boolean updateContactFlag;
	private boolean newContactFlag;
	private String shortId;	
	private String alternatePhone;
	private String middleName;
	private GenericAddress address;
	//This field is used in the existing code, so cannot be removed
	private String country;
	private boolean manageContactFlag;
	//MPS2.1 hardware debreief
	private String contactType;
	//Ends MPS2.1 hardware debreief
	
	private String deviceContactType;
	private String homePhone;
	
	private String technicianName;
	private String contactJobRole;
	
	
	public String getContactJobRole() {
		return contactJobRole;
	}

	public void setContactJobRole(String contactJobRole) {
		this.contactJobRole = contactJobRole;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getShortId() {
		return shortId;
	}

	public void setShortId(String shortId) {
		this.shortId = shortId;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public boolean getUserFavorite() {
		return userFavorite;
	}

	public void setUserFavorite(boolean userFavorite) {
		this.userFavorite = userFavorite;
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

	public boolean getUpdateContactFlag() {
		return updateContactFlag;
	}

	public void setUpdateContactFlag(boolean updateContactFlag) {
		this.updateContactFlag = updateContactFlag;
	}

	public boolean getNewContactFlag() {
		return newContactFlag;
	}

	public void setNewContactFlag(boolean newContactFlag) {
		this.newContactFlag = newContactFlag;
	}

	public String getAlternatePhone() {
		return alternatePhone;
	}

	public void setAlternatePhone(String alternatePhone) {
		this.alternatePhone = alternatePhone;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public GenericAddress getAddress() {
		return address;
	}

	public void setAddress(GenericAddress address) {
		this.address = address;
	}

	public void setManageContactFlag(boolean manageContactFlag) {
		this.manageContactFlag = manageContactFlag;
	}

	public boolean isManageContactFlag() {
		return manageContactFlag;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getContactType() {
		return contactType;
	}

	public String getDeviceContactType() {
		return deviceContactType;
	}

	public void setDeviceContactType(String deviceContactType) {
		this.deviceContactType = deviceContactType;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getTechnicianName() {
		return technicianName;
	}

	public void setTechnicianName(String technicianName) {
		this.technicianName = technicianName;
	}

}
