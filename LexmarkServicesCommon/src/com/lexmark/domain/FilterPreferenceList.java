package com.lexmark.domain;

import java.io.Serializable;

public class FilterPreferenceList implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1567980136828543332L;
	private String userId;
	
	private AddressFilter addressFilter;
	
	private DeviceFilter deviceFilter;
	
	private RequestFilter requestFilter;
	
	private AccountFilter accountFilter;
		
	private char isPreference;
	
	private String emailAddress;
	
	public DeviceFilter getDeviceFilter() {
		return deviceFilter;
	}

	public void setDeviceFilter(DeviceFilter deviceFilter) {
		this.deviceFilter = deviceFilter;
	}

	public RequestFilter getRequestFilter() {
		return requestFilter;
	}

	public void setRequestFilter(RequestFilter requestFilter) {
		this.requestFilter = requestFilter;
	}

	public AccountFilter getAccountFilter() {
		return accountFilter;
	}

	public void setAccountFilter(AccountFilter accountFilter) {
		this.accountFilter = accountFilter;
	}

	public char getIsPreference() {
		return isPreference;
	}

	public void setIsPreference(char isPreference) {
		this.isPreference = isPreference;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public AddressFilter getAddressFilter() {
		return addressFilter;
	}

	public void setAddressFilter(AddressFilter addressFilter) {
		this.addressFilter = addressFilter;
	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	
}

