package com.lexmark.service.impl.real.domain;

/**
 * @author imdzeluri
 * Mapping file: do-activitypartlistdo-mapping.xml
 */

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;

public class ActivityPartListDo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -8833931229122213552L;
	
	private String partNumber;
	private String description;
	private String orderQuantity;
	
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
	public String getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(String orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	
}
