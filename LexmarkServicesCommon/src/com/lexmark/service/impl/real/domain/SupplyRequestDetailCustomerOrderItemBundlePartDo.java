package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;

/**
 * 
 * @author i.prikhodko
 *
 *	 do-mapping file: "do-supplyrequestdetailcustomerorderitembundlepart-mapping.xml"
 */

public class SupplyRequestDetailCustomerOrderItemBundlePartDo extends
		BaseEntity implements Serializable {

	private static final long serialVersionUID = 1115846548210654263L;
	
	private String quantity;
    private String partNumber;
    private String description;
    
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
}
