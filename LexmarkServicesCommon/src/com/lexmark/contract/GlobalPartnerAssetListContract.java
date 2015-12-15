package com.lexmark.contract;

import java.io.Serializable;

public class GlobalPartnerAssetListContract implements Serializable {
	
	private static final long serialVersionUID = -2160968697633221631L;
	private String serialNumber;
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
}
