package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.contract.api.ContractBase;
import com.lexmark.domain.Asset;

public class UpdateAssetMeterReadContract extends ContractBase implements
		Serializable {

	private static final long serialVersionUID = 7451010681562642234L;
	
	private String contactId;
	private Asset asset;

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getContactId() {
		return contactId;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public Asset getAsset() {
		return asset;
	}

}
