package com.lexmark.service.impl.real.domain;


import com.amind.common.domain.BaseEntity;

public class PartnerFruPartBaseDo extends BaseEntity {
	
	private String modelNumber;
	private String partNumber;
	private String partName;
	
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}

	
	

}
