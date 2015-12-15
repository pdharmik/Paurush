package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.lexmark.service.impl.real.domain.AccountBasedDo;

/**
 * mapping file: "do-addressdo-mapping.xml"
 */
public class AddressDo extends AccountBasedDo implements Serializable {
	private static final long serialVersionUID = 6483553507743496750L;

	public static final String BO = "LXK Service Address - Service Web";
	public static final String BC = "LXK Service Address - Service Web";
	
	private String addressName;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String city;
	private String state;
	private String province;
	private String zip;
	private String country;
	private String storeFrontName;
	private String customAddressName;
	/* Added by sankha for LEX:AIR00065738 start*/
	private String status;
	private String county;
	private String district;
	private String officeNumber;
	private String region;
	private String countyCode;
	private Boolean lbsAddressFlag;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	/* End */
	
	/* Overridable method for use during conversion to DTO */
	
	public String getAddressId() {
		return getId();
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setAddressName(String addresName) {
		this.addressName = addresName;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setCustomAddressName(String customAddressName) {
		this.customAddressName = customAddressName;
	}

	public String getCustomAddressName() {
		return customAddressName;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getStoreFrontName() {
		return storeFrontName;
	}

	public void setStoreFrontName(String storeFrontName) {
		this.storeFrontName = storeFrontName;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getOfficeNumber() {
		return officeNumber;
	}

	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public Boolean isLbsAddressFlag() {
		return lbsAddressFlag;
	}

	public void setLbsAddressFlag(Boolean lbsAddressFlag) {
		this.lbsAddressFlag = lbsAddressFlag;
	}	
}
