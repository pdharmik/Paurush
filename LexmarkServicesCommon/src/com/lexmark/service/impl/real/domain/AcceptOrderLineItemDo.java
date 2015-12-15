package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;

/**
 * 
 * @author vshynkarenko
 * mapping-file: "do-acceptorderlineitem-mapping.xml"
 *
 */
public class AcceptOrderLineItemDo extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String status;
	private String vendorId;
	private String orderHeaderId;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getOrderHeaderId() {
		return orderHeaderId;
	}
	public void setOrderHeaderId(String orderHeaderId) {
		this.orderHeaderId = orderHeaderId;
	}
	
}
