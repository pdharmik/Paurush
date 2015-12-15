package com.lexmark.service.impl.real.domain;

import com.amind.common.domain.BaseEntity;

/**
 * mapping file: "do-partneractivityhistorypart-mapping.xml"
 */
public class PartnerActivityHistoryParts extends BaseEntity {
	
	private String serviceRequestId;
	private int quantity;
	private String partNumber;
	private String partName;
	private String errorCode1;
	private String errorCode2;
	private String partDisposition;
	

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	public String getErrorCode1() {
		return errorCode1;
	}
	public void setErrorCode1(String errorCode1) {
		this.errorCode1 = errorCode1;
	}
	public String getErrorCode2() {
		return errorCode2;
	}
	public void setErrorCode2(String errorCode2) {
		this.errorCode2 = errorCode2;
	}
	public String getPartDisposition() {
		return partDisposition;
	}
	public void setPartDisposition(String partDisposition) {
		this.partDisposition = partDisposition;
	}
	public void setServiceRequestId(String serviceRequestId) {
		this.serviceRequestId = serviceRequestId;
	}
	public String getServiceRequestId() {
		return serviceRequestId;
	}
	
	

}
