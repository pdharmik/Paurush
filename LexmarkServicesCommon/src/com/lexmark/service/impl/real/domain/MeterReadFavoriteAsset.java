package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

/**
 * mapping file: "do-meterreadfavoriteassetdo-mapping.xml"
 */
public class MeterReadFavoriteAsset extends MeterReadBase implements Serializable {
	private static final long serialVersionUID = 1926122927790833725L;

	private boolean assetFavFlag;

	public boolean isAssetFavFlag() {
		return assetFavFlag;
	}

	public void setAssetFavFlag(boolean assetFavFlag) {
		this.assetFavFlag = assetFavFlag;
	}
}
