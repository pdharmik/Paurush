package com.lexmark.domain;

import java.io.Serializable;

public class AssetIdentifier implements Serializable {
	private static final long serialVersionUID = 1653632653852844347L;
	private String assetIdentifierId;
	private String assetIdentifierName;
	
	public String getAssetIdentifierId() {
		return assetIdentifierId;
	}
	public void setAssetIdentifierId(String assetIdentifierId) {
		this.assetIdentifierId = assetIdentifierId;
	}
	public String getAssetIdentifierName() {
		return assetIdentifierName;
	}
	public void setAssetIdentifierName(String assetIdentifierName) {
		this.assetIdentifierName = assetIdentifierName;
	}
	
}
