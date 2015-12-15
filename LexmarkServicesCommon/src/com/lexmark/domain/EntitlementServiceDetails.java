package com.lexmark.domain;

import java.io.Serializable;

public class EntitlementServiceDetails implements Serializable {
	private static final long serialVersionUID = -8671912975359664921L;
	private String serviceDetailId;
	private String serviceDetailsDescription;
	private Boolean primaryFlag;
	
	public String getServiceDetailId() {
		return serviceDetailId;
	}
	public void setServiceDetailId(String serviceDetailId) {
		this.serviceDetailId = serviceDetailId;
	}
	public String getServiceDetailsDescription() {
		return serviceDetailsDescription;
	}
	public void setServiceDetailsDescription(String serviceDetailsDescription) {
		this.serviceDetailsDescription = serviceDetailsDescription;
	}
	public void setPrimaryFlag(Boolean primaryFlag) {
		this.primaryFlag = primaryFlag;
	}
	public Boolean getPrimaryFlag() {
		return primaryFlag;
	}
	

}
