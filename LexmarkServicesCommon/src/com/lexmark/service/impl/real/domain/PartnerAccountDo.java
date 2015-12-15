package com.lexmark.service.impl.real.domain;

import java.util.ArrayList;

/**
 * mapping file: "do-partneraccount-mapping.xml"
 */
public class PartnerAccountDo extends AccountBasedDo{
	private static final long serialVersionUID = 5970825576796488371L;
	
	private String accountName;
	private String addressName;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String city;
	private String province;
	private String postalCode;
	private String country;
	private String state;
	private String organizationId;
	private String partQuantityOrderLimit;
	private String mdmLevel;
	private String currency;
	private String addressId;
	private String partnerFlag;
	private ArrayList<PartnerAccountFlagDo> flags;
	private ArrayList<PartnerAccountTypeDo>  accountTypes;
	private String partnerCountry;
	
	private String county;
	private String district;
	private String officeNumber;
	
	private String primaryAddressID;
	
	private String primaryAddressName;
	private String primaryAddressLine1;
	private String primaryAddressLine2;
	private String primaryAddressLine3;
	private String primaryCity;
	private String primaryProvince;
	private String primaryPostalCode;
	private String primaryCountry;
	private String primaryState;
	
	private String primaryCounty;
	private String primaryDistrict;
	private String primaryOfficeNumber;
	
	
	private String massUploadFlag;
	private String partnerSetUpType;
	private String partnerSetUpFlag;
	
	private String shipToDefault;
	
	public String getPrimaryAddressID() {
		return primaryAddressID;
	}
	public void setPrimaryAddressID(String primaryAddressID) {
		this.primaryAddressID = primaryAddressID;
	}
	
	public String getPrimaryAddressName() {
		return primaryAddressName;
	}
	public void setPrimaryAddressName(String primaryAddressName) {
		this.primaryAddressName = primaryAddressName;
	}
	public String getPrimaryAddressLine1() {
		return primaryAddressLine1;
	}
	public void setPrimaryAddressLine1(String primaryAddressLine1) {
		this.primaryAddressLine1 = primaryAddressLine1;
	}
	public String getPrimaryAddressLine2() {
		return primaryAddressLine2;
	}
	public void setPrimaryAddressLine2(String primaryAddressLine2) {
		this.primaryAddressLine2 = primaryAddressLine2;
	}
	public String getPrimaryAddressLine3() {
		return primaryAddressLine3;
	}
	public void setPrimaryAddressLine3(String primaryAddressLine3) {
		this.primaryAddressLine3 = primaryAddressLine3;
	}
	public String getPrimaryCity() {
		return primaryCity;
	}
	public void setPrimaryCity(String primaryCity) {
		this.primaryCity = primaryCity;
	}
	public String getPrimaryProvince() {
		return primaryProvince;
	}
	public void setPrimaryProvince(String primaryProvince) {
		this.primaryProvince = primaryProvince;
	}
	public String getPrimaryPostalCode() {
		return primaryPostalCode;
	}
	public void setPrimaryPostalCode(String primaryPostalCode) {
		this.primaryPostalCode = primaryPostalCode;
	}
	public String getPrimaryCountry() {
		return primaryCountry;
	}
	public void setPrimaryCountry(String primaryCountry) {
		this.primaryCountry = primaryCountry;
	}
	public String getPrimaryState() {
		return primaryState;
	}
	public void setPrimaryState(String primaryState) {
		this.primaryState = primaryState;
	}
	public String getPrimaryCounty() {
		return primaryCounty;
	}
	public void setPrimaryCounty(String primaryCounty) {
		this.primaryCounty = primaryCounty;
	}
	public String getPrimaryDistrict() {
		return primaryDistrict;
	}
	public void setPrimaryDistrict(String primaryDistrict) {
		this.primaryDistrict = primaryDistrict;
	}
	public String getPrimaryOfficeNumber() {
		return primaryOfficeNumber;
	}
	public void setPrimaryOfficeNumber(String primaryOfficeNumber) {
		this.primaryOfficeNumber = primaryOfficeNumber;
	}
	
	
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getAddressLine3() {
		return addressLine3;
	}
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	public String getPartQuantityOrderLimit() {
		return partQuantityOrderLimit;
	}
	public void setPartQuantityOrderLimit(String partQuantityOrderLimit) {
		this.partQuantityOrderLimit = partQuantityOrderLimit;
	}
	public ArrayList<PartnerAccountFlagDo> getFlags() {
		return flags;
	}
	public void setFlags(ArrayList<PartnerAccountFlagDo> flags) {
		this.flags = flags;
	}
	public ArrayList<PartnerAccountTypeDo> getAccountTypes() {
		return accountTypes;
	}
	public void setAccountTypes(ArrayList<PartnerAccountTypeDo> accountTypes) {
		this.accountTypes = accountTypes;
	}
	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}
	public String getMdmLevel() {
		return mdmLevel;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCurrency() {
		return currency;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getAddressId() {
		return addressId;
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
	public String getMassUploadFlag() {
		return massUploadFlag;
	}
	public void setMassUploadFlag(String massUploadFlag) {
		this.massUploadFlag = massUploadFlag;
	}

	public boolean isMassUploadFlag(){
		if(massUploadFlag != null && massUploadFlag.equalsIgnoreCase("Y")){
			return true;
		}else {
			return false;
		}
	}
	public String getPartnerFlag() {
		return partnerFlag;
	}
	public void setPartnerFlag(String partnerFlag) {
		this.partnerFlag = partnerFlag;
	}
	public String getPartnerSetUpType() {
		return partnerSetUpType;
	}
	public void setPartnerSetUpType(String partnerSetUpType) {
		this.partnerSetUpType = partnerSetUpType;
	}
	public String getPartnerSetUpFlag() {
		return partnerSetUpFlag;
	}
	public void setPartnerSetUpFlag(String partnerSetUpFlag) {
		this.partnerSetUpFlag = partnerSetUpFlag;
	}
	public String getPartnerCountry() {
		return partnerCountry;
	}
	public void setPartnerCountry(String partnerCountry) {
		this.partnerCountry = partnerCountry;
	}
	public String getShipToDefault() {
		return shipToDefault;
	}
	public void setShipToDefault(String shipToDefault) {
		this.shipToDefault = shipToDefault;
	}
}
