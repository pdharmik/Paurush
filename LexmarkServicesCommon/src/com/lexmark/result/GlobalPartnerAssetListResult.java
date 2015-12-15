package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.Asset;

public class GlobalPartnerAssetListResult implements Serializable {
	private static final long serialVersionUID = 8655267324573060022L;
	private int totalCount;
	private List<Asset> assetList  = new ArrayList<Asset>(0);
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<Asset> getAssetList() {
		return assetList;
	}
	public void setAssetList(List<Asset> assetList) {
		this.assetList = assetList;
	}
}
