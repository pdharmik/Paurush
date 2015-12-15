package com.lexmark.service.impl.real.domain;

/**
 * @author Ivan Mdzeluri
 * Mapping file: do-madcrequestdo-mapping.xml
 * @Date 1.29.2015
 */

import java.io.Serializable;
import java.util.Date;

import com.amind.common.domain.BaseEntity;

public class MadcRequestDo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -4481711432428338314L;
	
	private String requestNumber;
	private Date dateCreated;
	private String area;
	private String subarea;
	private String serialNumber;
	private String deviceTag;
	private String status;
	private String productModel;
	private String referenceNumber;
	private String costCenter;
	private String accountName;
	private String addressName;
	private String storeFrontName;
	private String addressLine1;
	private String houseNumber;
	private String city;
	private String state;
	private String province;
	private String county;
	private String district;
	private String country;
	private String zipCode;
	private String primaryContactFirstName;
	private String primaryContactLastName;
	private String primaryContactEmail;
	private String primaryContactPhone;
	private String requesterFirstName;
	private String requesterLastName;
	private String requesterContactEmail;
	private String requesterContactPhone;
	private String coveredServices;
	
	public String getRequestNumber() {
		return requestNumber;
	}
	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getSubarea() {
		return subarea;
	}
	public void setSubarea(String subarea) {
		this.subarea = subarea;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getDeviceTag() {
		return deviceTag;
	}
	public void setDeviceTag(String deviceTag) {
		this.deviceTag = deviceTag;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProductModel() {
		return productModel;
	}
	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
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
	public String getStoreFrontName() {
		return storeFrontName;
	}
	public void setStoreFrontName(String storeFrontName) {
		this.storeFrontName = storeFrontName;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
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
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getPrimaryContactFirstName() {
		return primaryContactFirstName;
	}
	public void setPrimaryContactFirstName(String primaryContactFirstName) {
		this.primaryContactFirstName = primaryContactFirstName;
	}
	public String getPrimaryContactLastName() {
		return primaryContactLastName;
	}
	public void setPrimaryContactLastName(String primaryContactLastName) {
		this.primaryContactLastName = primaryContactLastName;
	}
	public String getPrimaryContactEmail() {
		return primaryContactEmail;
	}
	public void setPrimaryContactEmail(String primaryContactEmail) {
		this.primaryContactEmail = primaryContactEmail;
	}
	public String getPrimaryContactPhone() {
		return primaryContactPhone;
	}
	public void setPrimaryContactPhone(String primaryContactPhone) {
		this.primaryContactPhone = primaryContactPhone;
	}
	public String getRequesterFirstName() {
		return requesterFirstName;
	}
	public void setRequesterFirstName(String requesterFirstName) {
		this.requesterFirstName = requesterFirstName;
	}
	public String getRequesterLastName() {
		return requesterLastName;
	}
	public void setRequesterLastName(String requesterLastName) {
		this.requesterLastName = requesterLastName;
	}
	public String getRequesterContactEmail() {
		return requesterContactEmail;
	}
	public void setRequesterContactEmail(String requesterContactEmail) {
		this.requesterContactEmail = requesterContactEmail;
	}
	public String getRequesterContactPhone() {
		return requesterContactPhone;
	}
	public void setRequesterContactPhone(String requesterContactPhone) {
		this.requesterContactPhone = requesterContactPhone;
	}
	public String getCoveredServices() {
		return coveredServices;
	}
	public void setCoveredServices(String coveredServices) {
		this.coveredServices = coveredServices;
	}

}
