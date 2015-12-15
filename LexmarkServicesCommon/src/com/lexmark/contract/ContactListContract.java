package com.lexmark.contract;

import java.io.Serializable;
import java.util.Locale;

import com.lexmark.contract.api.SearchContractBase;

public class ContactListContract extends SearchContractBase implements Serializable {
	private static final long serialVersionUID = -8909858949224667519L;
	private String mdmId;
	private String mdmLevel;
	private boolean favoriteFlag;
	private Locale locale;
	private String contactId;
	private boolean loadAllFlag;
	
	public boolean isLoadAllFlag() {
		return loadAllFlag;
	}
	public void setLoadAllFlag(boolean loadAllFlag) {
		this.loadAllFlag = loadAllFlag;
	}
	public boolean isFavoriteFlag() {
		return favoriteFlag;
	}
	public void setFavoriteFlag(boolean favoriteFlag) {
		this.favoriteFlag = favoriteFlag;
	}
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getMdmId() {
		return mdmId;
	}
	public void setMdmId(String mdmId) {
		this.mdmId = mdmId;
	}
	public String getMdmLevel() {
		return mdmLevel;
	}
	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
}
