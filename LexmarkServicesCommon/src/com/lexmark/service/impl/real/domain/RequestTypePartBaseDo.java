package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;

public class RequestTypePartBaseDo extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String vendorPartNumber;
	
	private String partNumber;
	private String partDescription;
	private String partType;
	private String partOrderQuantity;
	private String partName;
	private boolean implicitFlag;
	
	public String getVendorPartNumber() {
		return vendorPartNumber;
	}

	public void setVendorPartNumber(String vendorPartNumber) {
		this.vendorPartNumber = vendorPartNumber;
	}
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getPartDescription() {
		return partDescription;
	}

	public void setPartDescription(String partDescription) {
		this.partDescription = partDescription;
	}

	public String getPartType() {
		return partType;
	}

	public void setPartType(String partType) {
		this.partType = partType;
	}

	public String getPartOrderQuantity() {
		return partOrderQuantity;
	}

	public void setPartOrderQuantity(String partOrderQuantity) {
		this.partOrderQuantity = partOrderQuantity;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getPartName() {
		return partName;
	}

	public void setImplicitFlag(boolean implicitFlag) {
		this.implicitFlag = implicitFlag;
	}

	public boolean isImplicitFlag() {
		return implicitFlag;
	}


}
