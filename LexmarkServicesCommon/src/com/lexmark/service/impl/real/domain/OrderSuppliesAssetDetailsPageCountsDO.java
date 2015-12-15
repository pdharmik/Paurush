package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.ArrayList;

import com.amind.common.domain.BaseEntity;

/**
 * @author i.prikhodko
 *
 * do-mapping: "do-ordersuppliesassetdetailspagecountsdo-mapping.xml"
 */

public class OrderSuppliesAssetDetailsPageCountsDO extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String assetId;
	private ArrayList<AssetDetailsPageCountsDO> pageCounts;

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public ArrayList<AssetDetailsPageCountsDO> getPageCounts() {
		return pageCounts;
	}

	public void setPageCounts(ArrayList<AssetDetailsPageCountsDO> pageCounts) {
		this.pageCounts = pageCounts;
	}

}
