package com.lexmark.service.impl.real.domain;

import com.amind.common.domain.BaseEntity;

/**
 * mapping file: "do-userfavoriteaddressdo-mapping.xml"
 */
public class UserFavoriteAddressDo extends BaseEntity {
	
	private String favoriteAddressId;
	private String contactId;
	private String relationshipType;
	private String accountId;
	private String status;
	
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

	public String getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
