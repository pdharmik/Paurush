package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.EntitlementServiceDetail;

			 
public class CheckedEntitlementServiceDetailResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4966607648421365187L;
	private List<EntitlementServiceDetail> entitlementServiceDetails = new ArrayList<EntitlementServiceDetail>(0);
	
	public List<EntitlementServiceDetail> getEntitlementServiceDetails() {
		return entitlementServiceDetails;
	}
	public void setEntitlementServiceDetails(
			List<EntitlementServiceDetail> entitlementServiceDetails) {
		this.entitlementServiceDetails = entitlementServiceDetails;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
}
