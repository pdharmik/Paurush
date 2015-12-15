package com.lexmark.service.impl.real.domain;

/**
 * The mapping file: do-accountEntitlement-mapping.xml
 * 
 **/

public class AccountEntitlementDo extends AccountBasedDo{
	private String mpsQuantity;
	public String getMpsQuantity() {
		return mpsQuantity;
	}
	public void setMpsQuantity(String mpsQuantity) {
		this.mpsQuantity = mpsQuantity;
	}
	public String getEntitlementType() {
		return entitlementType;
	}
	public void setEntitlementType(String entitlementType) {
		this.entitlementType = entitlementType;
	}
	private String entitlementType;
	
	
}
