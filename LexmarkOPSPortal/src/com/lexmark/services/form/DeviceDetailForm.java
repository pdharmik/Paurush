package com.lexmark.services.form;

import java.io.Serializable;

import com.lexmark.domain.Asset;

public class DeviceDetailForm implements Serializable{
	
	private static final long serialVersionUID = -8835422830778645252L;
	
	private Asset device;
	private String serviceRequestsXML;
	private boolean createServiceRequestFlag;
	private String serviceRequestHistoryListXML;
	private String installDate; //Added for BRD 14-02-06

	/***This is done for device management MPS****/
	private boolean consumableAssetFlag;
	
	public boolean isConsumableAssetFlag() {
		return consumableAssetFlag;
	}

	public void setConsumableAssetFlag(boolean consumableAssetFlag) {
		this.consumableAssetFlag = consumableAssetFlag;
	}

	public String getServiceRequestHistoryListXML() {
		return serviceRequestHistoryListXML;
	}

	public void setServiceRequestHistoryListXML(String serviceRequestHistoryListXML) {
		this.serviceRequestHistoryListXML = serviceRequestHistoryListXML;
	}

	public boolean isCreateServiceRequestFlag() {
		return createServiceRequestFlag;
	}

	public void setCreateServiceRequestFlag(boolean createServiceRequestFlag) {
		this.createServiceRequestFlag = createServiceRequestFlag;
	}

	public String getServiceRequestsXML() {
		return serviceRequestsXML;
	}

	public void setServiceRequestsXML(String serviceRequestsXML) {
		this.serviceRequestsXML = serviceRequestsXML;
	}

	public Asset getDevice() {
		return device;
	}

	public void setDevice(Asset device) {
		this.device = device;
	}

	public String getInstallDate() {
		return installDate;
	}

	public void setInstallDate(String installDate) {
		this.installDate = installDate;
	}

}
