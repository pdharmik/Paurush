package com.lexmark.contract;

import java.io.Serializable;
import java.util.Locale;

import com.lexmark.contract.api.SearchContractBase;

public class ServiceRequestHistoryListContract extends SearchContractBase implements Serializable {

	private static final long serialVersionUID = 1936614135739879737L;
	
	private String assetId;
	private String mdmID;
	private String mdmLevel;
	private Locale locale;
	private String accountId;
	private String serviceRequestNumber;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getServiceRequestNumber() {
		return serviceRequestNumber;
	}

	public void setServiceRequestNumber(String serviceRequestNumber) {
		this.serviceRequestNumber = serviceRequestNumber;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getMdmID() {
		return mdmID;
	}

	public void setMdmID(String mdmID) {
		this.mdmID = mdmID;
	}

	public String getMdmLevel() {
		return mdmLevel;
	}

	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getAssetId() {
		return assetId;
	}

	
}
