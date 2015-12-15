package com.lexmark.domain;

import java.io.Serializable;

public class OPSUserFilterPopupSetting implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1569984543528543332L;
	private String rowId;
	private String userId;
	private String address;
	private String device;
	private String serviceRequest;
	private char defaultPref;
	private String prefName;
	private String account;
	private String fieldPrefernce;
	
	
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return address;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getDevice() {
		return device;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserId() {
		return userId;
	}
	public void setServiceRequest(String serviceRequest) {
		this.serviceRequest = serviceRequest;
	}
	public String getServiceRequest() {
		return serviceRequest;
	}
	
	/**
	 * @param prefName the prefName to set
	 */
	public void setPrefName(String prefName) {
		this.prefName = prefName;
	}
	/**
	 * @return the prefName
	 */
	public String getPrefName() {
		return prefName;
	}
	/**
	 * @param defaultPref the defaultPref to set
	 */
	public void setDefaultPref(char defaultPref) {
		this.defaultPref = defaultPref;
	}
	/**
	 * @return the defaultPref
	 */
	public char getDefaultPref() {
		return defaultPref;
	}
	/**
	 * @param rowId the rowId to set
	 */
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	/**
	 * @return the rowId
	 */
	public String getRowId() {
		return rowId;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * @param fieldPrefernce the fieldPrefernce to set
	 */
	public void setFieldPrefernce(String fieldPrefernce) {
		this.fieldPrefernce = fieldPrefernce;
	}
	/**
	 * @return the fieldPrefernce
	 */
	public String getFieldPrefernce() {
		return fieldPrefernce;
	}
	
	
}
