package com.lexmark.service.impl.real.domain;

import com.amind.common.domain.BaseEntity;

/**
 * mapping file: "do-userfavoriteasset-mapping.xml"
 */
public class UserFavoriteAssetDo extends BaseEntity {

	private String favoriteAssetId;
	private String contactId;
	private boolean assetFavFlag;

	public boolean isAssetFavFlag() {
		return assetFavFlag;
	}

	public void setAssetFavFlag(boolean assetFavFlag) {
		this.assetFavFlag = assetFavFlag;
	}

	public String getFavoriteAssetId() {
		return favoriteAssetId;
	}

	public void setFavoriteAssetId(String favoriteAssetId) {
		this.favoriteAssetId = favoriteAssetId;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
}
