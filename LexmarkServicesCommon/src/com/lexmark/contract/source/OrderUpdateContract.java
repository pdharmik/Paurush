package com.lexmark.contract.source;

import java.io.Serializable;
import com.lexmark.domain.Order;

public class OrderUpdateContract implements Serializable {
	private static final long serialVersionUID = 4022786791810388402L;
	private Order order;
	private String mdmId;
	private String mdmLevel;
	private String sourceReferenceNumber;
	private String vendorNumber;
	
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public String getMdmId() {
		return mdmId;
	}
	public void setMdmId(String mdmId) {
		this.mdmId = mdmId;
	}
	public String getMdmLevel() {
		return mdmLevel;
	}
	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}
	public String getSourceReferenceNumber() {
		return sourceReferenceNumber;
	}
	public void setSourceReferenceNumber(String sourceReferenceNumber) {
		this.sourceReferenceNumber = sourceReferenceNumber;
	}
	public String getVendorNumber() {
		return vendorNumber;
	}
	public void setVendorNumber(String vendorNumber) {
		this.vendorNumber = vendorNumber;
	}
}
