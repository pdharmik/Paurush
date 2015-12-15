package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.contract.api.ContractBase;

public class UserFavoriteAssetContract extends ContractBase implements Serializable{

	private static final long serialVersionUID = 3140509794630450575L;
	
	private String contactId;
	private String favoriteAssetId;
	private boolean favoriteFlag;
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public String getFavoriteAssetId() {
		return favoriteAssetId;
	}
	public void setFavoriteAssetId(String favoriteAssetId) {
		this.favoriteAssetId = favoriteAssetId;
	}
	public boolean isFavoriteFlag() {
		return favoriteFlag;
	}
	public void setFavoriteFlag(boolean favoriteFlag) {
		this.favoriteFlag = favoriteFlag;
	}
}
