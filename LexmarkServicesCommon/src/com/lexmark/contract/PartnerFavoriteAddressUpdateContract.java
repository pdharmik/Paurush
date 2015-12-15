package com.lexmark.contract;

import java.io.Serializable;

public class PartnerFavoriteAddressUpdateContract implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7805506784427060743L;
	
	private String contactId;
	private String favoriteAddressId;
	private boolean favoriteFlag;
	private String partnerAccountId;
	
	public String getPartnerAccountId() {
		return partnerAccountId;
	}
	public void setPartnerAccountId(String partnerAccountId) {
		this.partnerAccountId = partnerAccountId;
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
