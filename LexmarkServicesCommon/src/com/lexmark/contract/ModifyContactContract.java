package com.lexmark.contract;

import java.io.Serializable;
import java.util.Locale;

import com.lexmark.contract.api.ContractBase;
import com.lexmark.domain.AccountContact;

public class ModifyContactContract extends ContractBase implements Serializable {
	private static final long serialVersionUID = -317075904796542279L;
	private AccountContact accountContact;
	private String contactId;
	private Locale locale;
	public AccountContact getAccountContact() {
		return accountContact;
	}
	public void setAccountContact(AccountContact accountContact) {
		this.accountContact = accountContact;
	}
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
	
	
}
