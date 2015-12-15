package com.lexmark.contract;

import java.io.Serializable;

public class FRUPartListContract implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8285109579985884905L;
	private String modelNumber;
	private boolean hardwarePartsRequest;
	private boolean maintenanceKitValidation; 
	
	public boolean isMaintenanceKitValidation() {
		return maintenanceKitValidation;
	}

	public void setMaintenanceKitValidation(boolean maintenanceKitValidation) {
		this.maintenanceKitValidation = maintenanceKitValidation;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public boolean isHardwarePartsRequest() {
		return hardwarePartsRequest;
	}

	public void setHardwarePartsRequest(boolean hardwarePartsRequest) {
		this.hardwarePartsRequest = hardwarePartsRequest;
	}
}
