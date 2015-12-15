package com.lexmark.contract;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.lexmark.domain.EntitlementServiceDetail;

public class LocalizedEntitlementServiceListContract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7341225778639741722L;
 	private Locale locale;
 	private List<EntitlementServiceDetail> entitlementServiceDetails = new ArrayList<EntitlementServiceDetail>(0);
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public List<EntitlementServiceDetail> getEntitlementServiceDetails() {
		return entitlementServiceDetails;
	}
	public void setEntitlementServiceDetails(
			List<EntitlementServiceDetail> entitlementServiceDetails) {
		this.entitlementServiceDetails = entitlementServiceDetails;
	}
}
