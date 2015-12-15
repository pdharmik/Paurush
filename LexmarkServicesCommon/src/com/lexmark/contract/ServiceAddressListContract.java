package com.lexmark.contract;

import java.io.Serializable;
import java.util.Locale;

import com.lexmark.contract.api.ContractBase;

public class ServiceAddressListContract extends ContractBase implements Serializable {
	private static final long serialVersionUID = -535179951494957933L;
	private String mdmId;
	private String mdmLevel;
	private String accountId ;
	private Locale locale;
	private boolean favoriteFlag;
	private String contactId;
	
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
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
}
