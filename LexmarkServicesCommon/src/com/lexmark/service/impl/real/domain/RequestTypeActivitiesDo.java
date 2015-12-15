package com.lexmark.service.impl.real.domain;

/**
 * @author imdzeluri
 * Mapping file: do-requesttypeactivitiesdo-mapping.xml
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.amind.common.domain.BaseEntity;
import com.lexmark.domain.GenericAddress;

public class RequestTypeActivitiesDo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -4481711432428338314L;
	
	private String serviceRequestNumber;
	private String status;
	private GenericAddress installAddress;
	private String serialNumber;
	private String deviceType;
	private String activityNumber;
	private String statusDetail;
    private String building;
    private String floor;
    private String office;
    private String description;
    private String deviceTag;
    private Date installDate;
    private String ipAddress;
    private String hostName;
    
    private String area;
    private String subArea;
    private String orderSource;
    private String serviceRequestStatus;
    private String serviceRequestCreated;
    private String requestorFirstName;
    private String requestorLastName;
    private String AccountName;
    
    //Primary contact
    private String primaryContactFirstName;
    private String primaryContactLastName;
    private String primaryContactWorkPhone;
    private String primaryContactEmail;
    //Secondary contact
    private String secondaryContactFirstName;
    private String secondaryContactLastName;
    private String secondaryContactWorkPhone;
    private String secondaryContactEmail;
    
    private String customerReferenceNumber;
    private String costCenter;
    private String addtnlDescription;
    private String notes;
    
    //Install Address
    private String district;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String country;
    private String countryCode;
    private String county;
    private String errorMessage;
    private String latitude;
    private String longitude;
    private String postalCode;
    private String province;
    private String region;
    private String state;
    private String stateFullName;
    
    private ArrayList<ActivityPartListDo> activityPartList;
    private ArrayList<ServiceRequestActivitiesAttachmentDo> serviceRequestActivitiesAttachmentDo;
    
	public String getServiceRequestNumber() {
		return serviceRequestNumber;
	}
	public void setServiceRequestNumber(String serviceRequestNumber) {
		this.serviceRequestNumber = serviceRequestNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public GenericAddress getInstallAddress() {
		return installAddress;
	}
	public void setInstallAddress(GenericAddress installAddress) {
		this.installAddress = installAddress;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getActivityNumber() {
		return activityNumber;
	}
	public void setActivityNumber(String activityNumber) {
		this.activityNumber = activityNumber;
	}
	public String getStatusDetail() {
		return statusDetail;
	}
	public void setStatusDetail(String statusDetail) {
		this.statusDetail = statusDetail;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDeviceTag() {
		return deviceTag;
	}
	public void setDeviceTag(String deviceTag) {
		this.deviceTag = deviceTag;
	}
	public Date getInstallDate() {
		return installDate;
	}
	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getSubArea() {
		return subArea;
	}
	public void setSubArea(String subArea) {
		this.subArea = subArea;
	}
	public String getOrderSource() {
		return orderSource;
	}
	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}
	public String getServiceRequestStatus() {
		return serviceRequestStatus;
	}
	public void setServiceRequestStatus(String serviceRequestStatus) {
		this.serviceRequestStatus = serviceRequestStatus;
	}
	public String getServiceRequestCreated() {
		return serviceRequestCreated;
	}
	public void setServiceRequestCreated(String serviceRequestCreated) {
		this.serviceRequestCreated = serviceRequestCreated;
	}
	public String getRequestorFirstName() {
		return requestorFirstName;
	}
	public void setRequestorFirstName(String requestorFirstName) {
		this.requestorFirstName = requestorFirstName;
	}
	public String getRequestorLastName() {
		return requestorLastName;
	}
	public void setRequestorLastName(String requestorLastName) {
		this.requestorLastName = requestorLastName;
	}
	public String getAccountName() {
		return AccountName;
	}
	public void setAccountName(String accountName) {
		AccountName = accountName;
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
	public String getSecondaryContactFirstName() {
		return secondaryContactFirstName;
	}
	public void setSecondaryContactFirstName(String secondaryContactFirstName) {
		this.secondaryContactFirstName = secondaryContactFirstName;
	}
	public String getSecondaryContactLastName() {
		return secondaryContactLastName;
	}
	public void setSecondaryContactLastName(String secondaryContactLastName) {
		this.secondaryContactLastName = secondaryContactLastName;
	}
	public String getSecondaryContactWorkPhone() {
		return secondaryContactWorkPhone;
	}
	public void setSecondaryContactWorkPhone(String secondaryContactWorkPhone) {
		this.secondaryContactWorkPhone = secondaryContactWorkPhone;
	}
	public String getSecondaryContactEmail() {
		return secondaryContactEmail;
	}
	public void setSecondaryContactEmail(String secondaryContactEmail) {
		this.secondaryContactEmail = secondaryContactEmail;
	}
	public String getCustomerReferenceNumber() {
		return customerReferenceNumber;
	}
	public void setCustomerReferenceNumber(String customerReferenceNumber) {
		this.customerReferenceNumber = customerReferenceNumber;
	}
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	public String getAddtnlDescription() {
		return addtnlDescription;
	}
	public void setAddtnlDescription(String addtnlDescription) {
		this.addtnlDescription = addtnlDescription;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStateFullName() {
		return stateFullName;
	}
	public void setStateFullName(String stateFullName) {
		this.stateFullName = stateFullName;
	}
	public ArrayList<ActivityPartListDo> getActivityPartList() {
		return activityPartList;
	}
	public void setActivityPartList(ArrayList<ActivityPartListDo> activityPartList) {
		this.activityPartList = activityPartList;
	}
	public ArrayList<ServiceRequestActivitiesAttachmentDo> getServiceRequestActivitiesAttachmentDo() {
		return serviceRequestActivitiesAttachmentDo;
	}
	public void setServiceRequestActivitiesAttachmentDo(
			ArrayList<ServiceRequestActivitiesAttachmentDo> serviceRequestActivitiesAttachmentDo) {
		this.serviceRequestActivitiesAttachmentDo = serviceRequestActivitiesAttachmentDo;
	}
}
