package com.lexmark.service.impl.real.domain;

/**
 * 
 * @author ssasidharan
 *
 * do-mapping: "do-servicerequestpartnerofflinemodeactivityaccounttypedo-mapping.xml"
 */

public class ServiceRequestPartnerOfflineModeActivityAccountTypeDo extends AccountType{

	private static final long serialVersionUID = 1L;
	
	private String type;
	private String activity;
	private String overrideOffering;
	private String accountLevel;
	private String legalEntityNumber;
	private String status;
	
	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getOverrideOffering() {
		return overrideOffering;
	}

	public void setOverrideOffering(String overrideOffering) {
		this.overrideOffering = overrideOffering;
	}

	public String getAccountLevel() {
		return accountLevel;
	}

	public void setAccountLevel(String accountLevel) {
		this.accountLevel = accountLevel;
	}

	public String getLegalEntityNumber() {
		return legalEntityNumber;
	}

	public void setLegalEntityNumber(String legalEntityNumber) {
		this.legalEntityNumber = legalEntityNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
