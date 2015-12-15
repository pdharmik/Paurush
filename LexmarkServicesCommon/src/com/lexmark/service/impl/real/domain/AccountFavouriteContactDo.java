package com.lexmark.service.impl.real.domain;

/**
 * mapping file: "do-accountfavouritecontactdo-mapping.xml"
 */
public class AccountFavouriteContactDo extends AccountContactDo {
	private static final long serialVersionUID = 2442035188092251421L;

	private String favoriteContactId;
	private String contactId;
	private String favoriteFlagType;

	public String getFavoriteFlagType() {
		return favoriteFlagType;
	}

	public void setFavoriteFlagType(String favoriteFlagType) {
		this.favoriteFlagType = favoriteFlagType;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getFavoriteContactId() {
		return favoriteContactId;
	}

	public void setFavoriteContactId(String favoriteContactId) {
		this.favoriteContactId = favoriteContactId;
	}
}
