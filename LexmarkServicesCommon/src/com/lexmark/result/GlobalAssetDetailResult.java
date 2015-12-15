package com.lexmark.result;

import java.io.Serializable;

import com.lexmark.domain.Asset;

public class GlobalAssetDetailResult implements	Serializable {
	
	private static final long serialVersionUID = 4758874735466010257L;
	
	Asset asset;

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}	

}
