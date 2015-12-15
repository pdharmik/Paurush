package com.lexmark.contract;

import java.io.Serializable;

public class ValidateInstalledPrinterSerialNumberContract  implements Serializable{
	
	private static final long serialVersionUID = -3781847351811225571L;
	private String activityId;
	private String serviceRequestId;
	private String modelNumber;
	private String serialNumber;
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getServiceRequestId() {
		return serviceRequestId;
	}
	public void setServiceRequestId(String serviceRequestId) {
		this.serviceRequestId = serviceRequestId;
	}
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
}
