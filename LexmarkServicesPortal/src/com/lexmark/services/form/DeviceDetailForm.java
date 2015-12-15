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
	/**
	 * 
	 * @return Boolean 
	 */
	public boolean isConsumableAssetFlag() {
		return consumableAssetFlag;
	}
/**
 * 
 * @param consumableAssetFlag 
 */
	public void setConsumableAssetFlag(boolean consumableAssetFlag) {
		this.consumableAssetFlag = consumableAssetFlag;
	}
/**
 * 
 * @return String 
 */
	public String getServiceRequestHistoryListXML() {
		return serviceRequestHistoryListXML;
	}
/**
 * 
 * @param serviceRequestHistoryListXML 
 */
	public void setServiceRequestHistoryListXML(String serviceRequestHistoryListXML) {
		this.serviceRequestHistoryListXML = serviceRequestHistoryListXML;
	}
/**
 * 
 * @return Boolean 
 */
	public boolean isCreateServiceRequestFlag() {
		return createServiceRequestFlag;
	}
/**
 * 
 * @param createServiceRequestFlag 
 */
	public void setCreateServiceRequestFlag(boolean createServiceRequestFlag) {
		this.createServiceRequestFlag = createServiceRequestFlag;
	}
/**
 * 
 * @return String 
 */
	public String getServiceRequestsXML() {
		return serviceRequestsXML;
	}
/**
 * 
 * @param serviceRequestsXML 
 */
	public void setServiceRequestsXML(String serviceRequestsXML) {
		this.serviceRequestsXML = serviceRequestsXML;
	}
/**
 * 
 * @return Device 
 */
	public Asset getDevice() {
		return device;
	}
/**
 * 
 * @param device 
 */
	public void setDevice(Asset device) {
		this.device = device;
	}
/**
 * 
 * @return String 
 */
	public String getInstallDate() {
		return installDate;
	}
/**
 * 
 * @param installDate 
 */
	public void setInstallDate(String installDate) {
		this.installDate = installDate;
	}

}
