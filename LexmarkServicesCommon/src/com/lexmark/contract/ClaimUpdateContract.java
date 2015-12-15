package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.domain.Activity;

public class ClaimUpdateContract  implements Serializable {

	private static final long serialVersionUID = 2365397568312166195L;
	private Activity activity;
	private String mdmId;
	private String mdmLevel;
	private String srType; //Added for CI BRD13-10-01
	private String createNewCustomerAddressFlag; //Added for CI BRD13-10-01
	
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
	public String getSrType() {
		return srType;
	}
	public void setSrType(String srType) {
		this.srType = srType;
	}
	public String getCreateNewCustomerAddressFlag() {
		return createNewCustomerAddressFlag;
	}
	public void setCreateNewCustomerAddressFlag(
			String createNewCustomerAddressFlag) {
		this.createNewCustomerAddressFlag = createNewCustomerAddressFlag;
	}
}
