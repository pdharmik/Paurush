package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.Asset;

public class AssetListResult implements Serializable {
	private static final long serialVersionUID = 2886857752869754083L;
	private int totalCount;
	private List<Asset> asset  = new ArrayList<Asset>(0);
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<Asset> getAssets() {
		return asset;
	}
	public void setAccountAssets(List<Asset> asset) {
		this.asset = asset;
	}


}
