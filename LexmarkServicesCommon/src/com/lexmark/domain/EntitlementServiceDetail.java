package com.lexmark.domain;

import java.io.Serializable;

public class EntitlementServiceDetail implements Serializable {
	private static final long serialVersionUID = -8671912975359664921L;
	private String serviceDetailId;
	private String serviceDetailDescription;
	private String siebelValue;
	private Boolean primaryFlag;
	
	public String getServiceDetailId() {
		return serviceDetailId;
	}
	public void setServiceDetailId(String serviceDetailId) {
		this.serviceDetailId = serviceDetailId;
	}
	public String getServiceDetailDescription() {
		return serviceDetailDescription;
	}
	public void setServiceDetailDescription(String serviceDetailDescription) {
		this.serviceDetailDescription = serviceDetailDescription;
	}
	public void setPrimaryFlag(Boolean primaryFlag) {
		this.primaryFlag = primaryFlag;
	}
	public Boolean getPrimaryFlag() {
		return primaryFlag;
	}
	public String getSiebelValue() {
		return siebelValue;
	}
	public void setSiebelValue(String siebelValue) {
		this.siebelValue = siebelValue;
	}
	

}
