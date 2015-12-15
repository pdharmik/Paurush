package com.lexmark.service.impl.real.domain;
import com.amind.common.domain.BaseEntity;

/**
 * The mapping file: "do-partneractivitycontactinfo-mapping.xml" 
 */
public class PartnerActivityContactInfoDo extends BaseEntity {

	private String firstName;
	private String lastName;
	private String emailAddress;
	private String primaryPersonalAddress;
	private String primaryPersonalAddress2;
	private String primaryPersonalCity;
	private String primaryPersonalCountry;
	private String primaryPersonalCounty;
	private String primaryPersonalPostalCode;
	private String primaryPersonalProvince;
	private String primaryPersonalState;
	private String workPhone;
	private String addressId;
	private String jobRole;
	private String physicalLocation1;
	private String physicalLocation2;
	private String physicalLocation3;
	
	public String getPhysicalLocation1() {
		return physicalLocation1;
	}
	public void setPhysicalLocation1(String physicalLocation1) {
		this.physicalLocation1 = physicalLocation1;
	}
	public String getPhysicalLocation2() {
		return physicalLocation2;
	}
	public void setPhysicalLocation2(String physicalLocation2) {
		this.physicalLocation2 = physicalLocation2;
	}
	public String getPhysicalLocation3() {
		return physicalLocation3;
	}
	public void setPhysicalLocation3(String physicalLocation3) {
		this.physicalLocation3 = physicalLocation3;
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
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPrimaryPersonalAddress() {
		return primaryPersonalAddress;
	}
	public void setPrimaryPersonalAddress(String primaryPersonalAddress) {
		this.primaryPersonalAddress = primaryPersonalAddress;
	}
	public String getPrimaryPersonalAddress2() {
		return primaryPersonalAddress2;
	}
	public void setPrimaryPersonalAddress2(String primaryPersonalAddress2) {
		this.primaryPersonalAddress2 = primaryPersonalAddress2;
	}
	public String getPrimaryPersonalCity() {
		return primaryPersonalCity;
	}
	public void setPrimaryPersonalCity(String primaryPersonalCity) {
		this.primaryPersonalCity = primaryPersonalCity;
	}
	public String getPrimaryPersonalCountry() {
		return primaryPersonalCountry;
	}
	public void setPrimaryPersonalCountry(String primaryPersonalCountry) {
		this.primaryPersonalCountry = primaryPersonalCountry;
	}
	public String getPrimaryPersonalCounty() {
		return primaryPersonalCounty;
	}
	public void setPrimaryPersonalCounty(String primaryPersonalCounty) {
		this.primaryPersonalCounty = primaryPersonalCounty;
	}
	public String getPrimaryPersonalPostalCode() {
		return primaryPersonalPostalCode;
	}
	public void setPrimaryPersonalPostalCode(String primaryPersonalPostalCode) {
		this.primaryPersonalPostalCode = primaryPersonalPostalCode;
	}
	public String getPrimaryPersonalProvince() {
		return primaryPersonalProvince;
	}
	public void setPrimaryPersonalProvince(String primaryPersonalProvince) {
		this.primaryPersonalProvince = primaryPersonalProvince;
	}
	public String getPrimaryPersonalState() {
		return primaryPersonalState;
	}
	public void setPrimaryPersonalState(String primaryPersonalState) {
		this.primaryPersonalState = primaryPersonalState;
	}
	public String getWorkPhone() {
		return workPhone;
	}
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getJobRole() {
		return jobRole;
	}
	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}
	
	
}
