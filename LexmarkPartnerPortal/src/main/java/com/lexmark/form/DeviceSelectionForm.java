package com.lexmark.form;

import java.io.Serializable;

import com.lexmark.domain.Asset;

/**
 * Form posted when a device is selected to create new claim.
 *
 */
public class DeviceSelectionForm implements Serializable {

	private static final long serialVersionUID = -6898032943745689683L;
	
	private Asset asset;
	
	private String notMyPrinterFlag;
	
	private float timezoneOffset;

	public float getTimezoneOffset() {
		return timezoneOffset;
	}

	public void setTimezoneOffset(float timezoneOffset) {
		this.timezoneOffset = timezoneOffset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setNotMyPrinterFlag(String notMyPrinterFlag) {
		this.notMyPrinterFlag = notMyPrinterFlag;
	}

	public String getNotMyPrinterFlag() {
		return notMyPrinterFlag;
	}

}
