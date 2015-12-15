package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * mapping file: "do-contractedservicerequest-mapping.xml"
 */
public class ServiceRequest extends AccountBasedDo implements Serializable {
	private static final long serialVersionUID = -5879728134501611176L;
	private String serviceRequestNumber;
	private Date serviceRequestDate;
	private String status;
	private String serialNumber;
	private String productLine;
	private String contactPrimaryId;
	private String contactAlternateId;
	private String contactId;
	private Date expDate;
	private String subStatus;
	private String assetProductLine;
	private String assetId;
	private Date updateDate;
	private String parentChain;
	private String primaryContactWorkPhone;
	private String primaryContactEmail;
	private String primaryContactFirstName;
	private String primaryContactLastName;
	private String city;
	private String state;
	private String country;
	private String street1;
	private String street2;
	private String street3;
	private String postalCode;
	private String province;
	private Date serviceRequestStatusDate;
	private String problemDescription;
	private String modelNumber;
	private String parentServiceRequestId;
	private String addressName;
	// new field
	private String storeFrontName;

	private String agreementType;
	private String accountMdmLevel;

	// for associated list and for history list:
	private String requestType;
	private String area;

	// dwijen added Start
	private String serviceRequestETA;
	private String custRefNumber;
	private String serviceRequestSLA;
	private String resolutionCode;	// added by nelson for CI5
	
	private String county;
	private String district;
	private String officeNumber;

	public String getServiceRequestSLA() {
		return serviceRequestSLA;
	}

	public void setServiceRequestSLA(String serviceRequestSLA) {
		this.serviceRequestSLA = serviceRequestSLA;
	}

	public String getServiceRequestETA() {
		return serviceRequestETA;
	}

	public void setServiceRequestETA(String serviceRequestETA) {
		this.serviceRequestETA = serviceRequestETA;
	}

	public String getCustRefNumber() {
		return custRefNumber;
	}

	public void setCustRefNumber(String custRefNumber) {
		this.custRefNumber = custRefNumber;
	}

	// dwijen added End

	public String getAccountMdmLevel() {
		return accountMdmLevel;
	}

	public void setAccountMdmLevel(String accountMdmLevel) {
		this.accountMdmLevel = accountMdmLevel;
	}

	public String getAgreementType() {
		return agreementType;
	}

	public void setAgreementType(String agreementType) {
		this.agreementType = agreementType;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getParentServiceRequestId() {
		return parentServiceRequestId;
	}

	public void setParentServiceRequestId(String parentServiceRequestId) {
		this.parentServiceRequestId = parentServiceRequestId;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getProblemDescription() {
		return problemDescription;
	}

	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}

	public Date getServiceRequestStatusDate() {
		return serviceRequestStatusDate;
	}

	public void setServiceRequestStatusDate(Date serviceRequestStatusDate) {
		this.serviceRequestStatusDate = serviceRequestStatusDate;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getStreet3() {
		return street3;
	}

	public void setStreet3(String street3) {
		this.street3 = street3;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPrimaryContactWorkPhone() {
		return primaryContactWorkPhone;
	}

	public void setPrimaryContactWorkPhone(String primaryContactWorkPhone) {
		this.primaryContactWorkPhone = primaryContactWorkPhone;
	}

	public String getPrimaryContactEmail() {
		return primaryContactEmail;
	}

	public void setPrimaryContactEmail(String primaryContactEmail) {
		this.primaryContactEmail = primaryContactEmail;
	}

	public String getPrimaryContactFirstName() {
		return primaryContactFirstName == null ? null : primaryContactFirstName.trim();
	}

	public void setPrimaryContactFirstName(String primaryContactFirstName) {
		this.primaryContactFirstName = primaryContactFirstName.trim();
	}

	public String getPrimaryContactLastName() {
		return primaryContactLastName == null ? null :  primaryContactLastName.trim();
	}

	public void setPrimaryContactLastName(String primaryContactLastName) {
		this.primaryContactLastName = primaryContactLastName.trim();
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getContactAlternateId() {
		return contactAlternateId;
	}

	public void setContactAlternateId(String contactAlternateId) {
		this.contactAlternateId = contactAlternateId;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getContactPrimaryId() {
		return contactPrimaryId;
	}

	public void setContactPrimaryId(String contactPrimaryId) {
		this.contactPrimaryId = contactPrimaryId;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public void setExpDate(Date expiredDate) {
		this.expDate = expiredDate;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setSubStatus(String subStatus) {
		this.subStatus = subStatus;
	}

	public String getSubStatus() {
		return subStatus;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getProductLine() {
		return productLine;
	}

	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}

	public String getServiceRequestNumber() {
		return serviceRequestNumber;
	}

	public void setServiceRequestNumber(String serviceRequestNumber) {
		this.serviceRequestNumber = serviceRequestNumber;
	}

	public Date getServiceRequestDate() {
		return serviceRequestDate;
	}

	public void setServiceRequestDate(Date serviceRequestDate) {
		this.serviceRequestDate = serviceRequestDate;
	}

	public void setAssetProductLine(String assetProductLine) {
		this.assetProductLine = assetProductLine;
	}

	public String getAssetProductLine() {
		return assetProductLine;
	}

	public void setParentChain(String parentChain) {
		this.parentChain = parentChain;
	}

	public String getParentChain() {
		return parentChain;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	//	added by nelson for CI5
	public String getResolutionCode() {
		return resolutionCode;
	}

	public void setResolutionCode(String resolutionCode) {
		this.resolutionCode = resolutionCode;
	}
	//	end of addition for CI5 by nelson

	public String getStoreFrontName() {
		return storeFrontName;
	}

	public void setStoreFrontName(String storeFrontName) {
		this.storeFrontName = storeFrontName;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
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
