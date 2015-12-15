package com.lexmark.service.impl.real.domain;


import java.io.Serializable;


/**
 * The mapping file:  do-contractedassetlocation-mapping.xml
 */
public class AssetLocation extends AccountBasedDo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6518194732147555616L;

	private String assetCount;
	
	private String city;
	private String state;
	private String postalCode;
	private String streetAddress;
	private String streetAddress2;
	private String province;
	private String country;
	private String county;
	private String district;
	private String officeNumber;
	
	/**
	 * VL 09/17/2010: Added for updated version of 
	 * AmindDeviceService::retrieveAssetLocations.
	 */
	private String addressName;
	/**
	 * VL 09/17/2010: Added for updated version of 
	 * AmindDeviceService::retrieveAssetLocations.
	 */
	private String chlNodeId;
    
	private String entitlementStartDate;
	private String entitlementParentChain;
	
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getStreetAddress2() {
		return streetAddress2;
	}

	public void setStreetAddress2(String streetAddress2) {
		this.streetAddress2 = streetAddress2;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getAssetCount() {
		return assetCount;
	}

	public void setAssetCount(String assetCount) {
		this.assetCount = assetCount;
	}

	public void setChlNodeId(String chlNodeId) {
		this.chlNodeId = chlNodeId;
	}

	public String getChlNodeId() {
		return chlNodeId;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getAddressName() {
		return addressName;
	}

	public String getEntitlementStartDate() {
		return entitlementStartDate;
	}

	public void setEntitlementStartDate(String entitlementStartDate) {
		this.entitlementStartDate = entitlementStartDate;
	}

	public String getEntitlementParentChain() {
		return entitlementParentChain;
	}

	public void setEntitlementParentChain(String entitlementParentChain) {
		this.entitlementParentChain = entitlementParentChain;
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
	
	
}
