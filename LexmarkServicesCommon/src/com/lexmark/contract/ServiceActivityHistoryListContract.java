package com.lexmark.contract;

import java.io.Serializable;

public class ServiceActivityHistoryListContract implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6144262145323831081L;
	
	private String assetId;
	private String serviceRequestId;


	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public void setServiceRequestId(String serviceRequestId) {
		this.serviceRequestId = serviceRequestId;
	}

	public String getServiceRequestId() {
		return serviceRequestId;
	}
	
	

}
