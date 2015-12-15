package com.lexmark.service.impl.real.domain;

import com.amind.common.domain.BaseEntity;

/**
 * mapping file: "do-userfavoriteaddressdo-mapping.xml"
 */
// TODO (pkozlov) does not implement serializable, but should?
public class UserFavoriteContactDo extends BaseEntity {

	private String favoriteContactId;
	private String contactId;
	private String accountId;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getFavoriteContactId() {
		return favoriteContactId;
	}

	public void setFavoriteContactId(String favoriteContactId) {
		this.favoriteContactId = favoriteContactId;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
}
