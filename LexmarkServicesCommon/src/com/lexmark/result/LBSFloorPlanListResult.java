package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.lexmark.service.impl.real.domain.LBSAsset;

public class LBSFloorPlanListResult implements Serializable{

	/**
	 * @author David Tsamalashvili
	 */
	private static final long serialVersionUID = 6689722415261650266L;
	private List<LBSAsset> assetList  = new ArrayList<LBSAsset>();
		
	public List<LBSAsset> getAssetList() {
		return assetList;
	}
	public void setAssetList(List<LBSAsset> assetList) {
		this.assetList = assetList;
	}
}
