package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.domain.Activity;

public class WarrantyClaimCreateContract implements Serializable{

	private static final long serialVersionUID = -5106968919606731109L;
	
	private Activity activity;
	private String mdmId;
	private String mdmLevel;
	private String createNewCustomerAddressFlag; //changed for CI-5 2098
	private String claimDraftStatus; //added by sbag BRD #13-10-01
	
	
	public String getClaimDraftStatus() {
		return claimDraftStatus;
	}
	public void setClaimDraftStatus(String claimDraftStatus) {
		this.claimDraftStatus = claimDraftStatus;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
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
	public String getCreateNewCustomerAddressFlag() {
		return createNewCustomerAddressFlag;
	}
	public void setCreateNewCustomerAddressFlag(String createNewCustomerAddressFlag) {
		this.createNewCustomerAddressFlag = createNewCustomerAddressFlag;
	}
	
	
}
