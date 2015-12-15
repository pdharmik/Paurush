package com.lexmark.result;

import java.io.Serializable;
import java.util.List;

import com.lexmark.domain.Asset;
import com.lexmark.service.impl.real.domain.ProductSerialNumberDetailDo;

//import com.lexmark.domain.ContactInformation;

public class AssetResult implements Serializable {

	private static final long serialVersionUID = 7954245103723842581L;
	
	private Asset asset;
	
	private List<Asset> assetlist;

	public List<Asset> getAssetlist() {
		return assetlist;
	}

	public void setAssetlist(List<Asset> assetlist) {
		this.assetlist = assetlist;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public Asset getAsset() {
		return asset;
	}
	
	

}
