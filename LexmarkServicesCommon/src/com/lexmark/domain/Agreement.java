package com.lexmark.domain;

import java.io.Serializable;

public class Agreement implements Serializable {
	private static final long serialVersionUID = -2987642637255816403L;

	private String agreementId;
	private String agreementName;
	private String mpsQuantity;
	private String entitlementType;
	
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

	

	public String getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

	public String getAgreementName() {
		return agreementName;
	}

	public void setAgreementName(String agreementName) {
		this.agreementName = agreementName;
	}
}
