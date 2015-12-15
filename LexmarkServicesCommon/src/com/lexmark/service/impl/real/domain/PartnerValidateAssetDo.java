package com.lexmark.service.impl.real.domain;

import com.amind.common.domain.BaseEntity;

public class PartnerValidateAssetDo extends BaseEntity {
	
	private String serialNumber;
	private String modelNumber;
	
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	
	
	

}
