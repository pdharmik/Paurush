package com.lexmark.contract;

import java.io.Serializable;

public class FRUPartDetailContract implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5567445573178331500L;
	private String partNumber;
	private String partnerOrg;
	private String modelNumber;
	private boolean replacementFlag;
	private boolean hardwarePartRequest;
	private boolean maintenanceKitValidation; 
	
	public boolean isMaintenanceKitValidation() {
		return maintenanceKitValidation;
	}
	public void setMaintenanceKitValidation(boolean maintenanceKitValidation) {
		this.maintenanceKitValidation = maintenanceKitValidation;
	}
	public boolean isReplacementFlag() {
		return replacementFlag;
	}
	public void setReplacementFlag(boolean replacementFlag) {
		this.replacementFlag = replacementFlag;
	}
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public String getPartnerOrg() {
		return partnerOrg;
	}
	public void setPartnerOrg(String partnerOrg) {
		this.partnerOrg = partnerOrg;
	}
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	public boolean isHardwarePartRequest() {
		return hardwarePartRequest;
	}
	public void setHardwarePartRequest(boolean hardwarePartRequest) {
		this.hardwarePartRequest = hardwarePartRequest;
	}
}
