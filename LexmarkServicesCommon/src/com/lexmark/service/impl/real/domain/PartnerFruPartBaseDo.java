package com.lexmark.service.impl.real.domain;


import com.amind.common.domain.BaseEntity;

public class PartnerFruPartBaseDo extends BaseEntity {
	private static final long serialVersionUID = -4106154123650551821L;
	
	private String modelNumber;
	private String partNumber;
	private String partName;
	private String materialLine;
	
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
	public String getMaterialLine() {
		return materialLine;
	}
	public void setMaterialLine(String materialLine) {
		this.materialLine = materialLine;
	}

}
