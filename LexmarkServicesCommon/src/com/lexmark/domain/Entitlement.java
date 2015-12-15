package com.lexmark.domain;

import java.io.Serializable;
import java.util.List;

public class Entitlement implements Serializable {
	private static final long serialVersionUID = 3477367866035917936L;
	private String entitlementId;
	private String entitlementName;
	private List<EntitlementServiceDetail> serviceDetails;
	
	public String getEntitlementId() {
		return entitlementId;
	}
	public void setEntitlementId(String entitlementId) {
		this.entitlementId = entitlementId;
	}
	public String getEntitlementName() {
		return entitlementName;
	}
	public void setEntitlementName(String entitlementName) {
		this.entitlementName = entitlementName;
	}

	public List<EntitlementServiceDetail> getServiceDetails() {
		return serviceDetails;
	}
	public void setServiceDetails(List<EntitlementServiceDetail> serviceDetails) {
		this.serviceDetails = serviceDetails;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
