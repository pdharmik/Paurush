package com.lexmark.contract;

import java.util.Map;

import com.lexmark.domain.Account;
import com.lexmark.domain.GenericAddress;

public class PlacementContract {
	public String placementId;
	public GenericAddress address;
	public String placementName;
	public String modelName;
	public String ipAddress;
	public Account account;
	public Map<String,String> addressMap;
	
	public Map<String, String> getAddressMap() {
		return addressMap;
	}
	public void setAddressMap(Map<String, String> addressMap) {
		this.addressMap = addressMap;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public String getPlacementName() {
		return placementName;
	}
	public void setPlacementName(String placementName) {
		this.placementName = placementName;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	/**
	 * @return the placementId
	 */
	public String getPlacementId() {
		return placementId;
	}
	/**
	 * @param placementId the placementId to set
	 */
	public void setPlacementId(String placementId) {
		this.placementId = placementId;
	}
	/**
	 * @return the address
	 */
	public GenericAddress getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(GenericAddress address) {
		this.address = address;
	}
	
	
}
