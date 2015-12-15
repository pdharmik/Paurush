package com.lexmark.contract;

import java.io.Serializable;

public class PageCountsContract implements Serializable {

	private static final long serialVersionUID = 1L;

	private String assetId;

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	
}
