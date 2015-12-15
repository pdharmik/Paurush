package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.contract.api.ContractBase;

public class FavoriteAddressContract extends ContractBase implements Serializable {
	private static final long serialVersionUID = -8909858949224667519L;	
	private String contactId;
	private String accountId;
	private String favoriteAddressId;
	private boolean favoriteFlag;
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public String getFavoriteAddressId() {
		return favoriteAddressId;
	}
	public void setFavoriteAddressId(String favoriteAddressId) {
		this.favoriteAddressId = favoriteAddressId;
	}
	public boolean isFavoriteFlag() {
		return favoriteFlag;
	}
	public void setFavoriteFlag(boolean favoriteFlag) {
		this.favoriteFlag = favoriteFlag;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}
