package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.contract.api.ContractBase;

public class AssetOrderDetailContract extends ContractBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3118778941184583521L;
	String assetId;
	String contactId;
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
}
