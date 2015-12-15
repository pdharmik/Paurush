package com.lexmark.contract;

import java.io.Serializable;
import java.util.Locale;

import com.lexmark.contract.api.ContractBase;

public class FavoriteContract extends ContractBase implements Serializable {
	private static final long serialVersionUID = -8909858949224667519L;	
	private String contactId;
	private String favoriteContactId;
	private boolean favoriteFlag;
	
	public String getFavoriteContactId() {
		return favoriteContactId;
	}
	public void setFavoriteContactId(String favoriteContactId) {
		this.favoriteContactId = favoriteContactId;
	}
	public boolean isFavoriteFlag() {
		return favoriteFlag;
	}
	public void setFavoriteFlag(boolean favoriteFlag) {
		this.favoriteFlag = favoriteFlag;
	}
	private Locale locale;
	
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
