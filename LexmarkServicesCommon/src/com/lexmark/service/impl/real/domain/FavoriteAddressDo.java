package com.lexmark.service.impl.real.domain;

/**
 * mapping file: "do-favoriteaddressdo-mapping.xml"
 */
public class FavoriteAddressDo extends AddressDo {

	private static final long serialVersionUID = 4868165682562712418L;
	
	public static final String BO = "LXK SW Contact Favorite Addresses";
	public static final String BC = "LXK SW Contact Favorite Addresses";
	
	private String favoriteAddressId;
	private String contactId;
	private String accountId;

	/* Overridable method for use during conversion to DTO */
	public String getAddressId() {
		return getFavoriteAddressId();
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

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

}
