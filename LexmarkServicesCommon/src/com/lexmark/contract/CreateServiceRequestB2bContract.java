package com.lexmark.contract;

public class CreateServiceRequestB2bContract {
	private static final long serialVersionUID = 1L;
	private String requesterType;
	private String vendorId;
	private String contactId;
	private String area;
	private String subArea;
	private String serviceRequestType;
	
	
	public void setRequesterType(String requesterType) {
		this.requesterType = requesterType;
	}
	public String getRequesterType() {
		return requesterType;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public String getContactId() {
		return contactId;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getArea() {
		return area;
	}
	public void setSubArea(String subArea) {
		this.subArea = subArea;
	}
	public String getSubArea() {
		return subArea;
	}
	public void setServiceRequestType(String serviceRequestType) {
		this.serviceRequestType = serviceRequestType;
	}
	public String getServiceRequestType() {
		return serviceRequestType;
	}
}
