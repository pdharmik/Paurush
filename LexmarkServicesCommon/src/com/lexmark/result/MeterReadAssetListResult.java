package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.Asset;

public class MeterReadAssetListResult implements Serializable {
	private static final long serialVersionUID = 2886857752869754083L;
	private int totalCount;
	private List<Asset> assets  = new ArrayList<Asset>();
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<Asset> getAssets() {
		return assets;
	}
	public void setAccountAssets(List<Asset> assets) {
		this.assets = assets;
	}


}
