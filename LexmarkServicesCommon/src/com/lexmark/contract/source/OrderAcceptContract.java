package com.lexmark.contract.source;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrderAcceptContract implements Serializable {
    
    private static final long serialVersionUID = 1L;
	
	private Date statusUpdateDate;
	private String activityId;
	private String orderLineItemId;
	private String status;
	private String orderId;
	private List<String> vendorIds;
	
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public Date getStatusUpdateDate() {
		return statusUpdateDate;
	}
	public void setStatusUpdateDate(Date statusUpdateDate) {
		this.statusUpdateDate = statusUpdateDate;
	}
	public String getOrderLineItemId() {
		return orderLineItemId;
	}
	public void setOrderLineItemId(String orderLineItemId) {
		this.orderLineItemId = orderLineItemId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public List<String> getVendorIds() {
		return vendorIds;
	}
	public void setVendorIds(List<String> vendorIds) {
		this.vendorIds = vendorIds;
	}
	
	
}
