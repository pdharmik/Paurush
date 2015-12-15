package com.lexmark.contract;

import java.io.Serializable;
import com.lexmark.contract.api.SearchContractBase;

public class PartnerAddressListContract extends SearchContractBase implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5124032672207421496L;
	private String accountID;
	private boolean favoriteFlag;	
	private String contactId;

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getContactId() {
		return contactId;
	}

	public void setFavoriteFlag(boolean favoriteFlag) {
		this.favoriteFlag = favoriteFlag;
	}

	public boolean isFavoriteFlag() {
		return favoriteFlag;
	}
	

}
