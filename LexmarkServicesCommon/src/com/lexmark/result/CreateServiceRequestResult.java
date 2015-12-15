package com.lexmark.result;

import java.io.Serializable;

public class CreateServiceRequestResult implements Serializable {
	private static final long serialVersionUID = -7598801205730902183L;
	private String serviceRequestNumber;
	private String serviceRequestRowId;
	
	public String getServiceRequestRowId() {
		return serviceRequestRowId;
	}
	public void setServiceRequestRowId(String serviceRequestRowId) {
		this.serviceRequestRowId = serviceRequestRowId;
	}
	public String getServiceRequestNumber() {
		return serviceRequestNumber;
	}
	public void setServiceRequestNumber(String serviceRequestNumber) {
		this.serviceRequestNumber = serviceRequestNumber;
	}
	
}
