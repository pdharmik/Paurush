package com.lexmark.domain;

import java.io.Serializable;

public class UserFilterSetting implements Serializable {
	
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
	private char isPreference;
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getServiceRequest() {
		return serviceRequest;
	}
	public void setServiceRequest(String serviceRequest) {
		this.serviceRequest = serviceRequest;
	}
	public char getDefaultPref() {
		return defaultPref;
	}
	public void setDefaultPref(char defaultPref) {
		this.defaultPref = defaultPref;
	}
	public String getPrefName() {
		return prefName;
	}
	public void setPrefName(String prefName) {
		this.prefName = prefName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getFieldPrefernce() {
		return fieldPrefernce;
	}
	public void setFieldPrefernce(String fieldPrefernce) {
		this.fieldPrefernce = fieldPrefernce;
	}
	
	public char getIsPreference() {
		return isPreference;
	}
	public void setIsPreference(char isPreference) {
		this.isPreference = isPreference;
	}
	
	public String toString(){
		
		return String.format("[rowId = %s , userId=%s , address = %s ," +
				" device = %s , service Request = %s, defaultPref = %s, prefName= %s, account =%s,fieldPreference =%s, isPreference = %s]",
				this.rowId,this.userId,this.address,this.defaultPref,this.serviceRequest,this.defaultPref,this.prefName,this.account,
				this.fieldPrefernce,this.isPreference);
	}	
}
