package com.lexmark.service.impl.real.domain;

import java.util.ArrayList;

import com.amind.common.domain.BaseEntity;

/**
 * mapping file: "do-assetmeasurementcharacteristicsbcdo-mapping.xml"
 */
// TODO (pkozlov) doesn't implement serializable, but should?
public class AssetMeasurementCharacteristicsBCDo extends BaseEntity {
	private String assetId;
	private ArrayList<AssetReadingLatestBcDo> assetReading;
	private String latestReading;

	public String getLatestReading() {
		return latestReading;
	}

	public void setLatestReading(String latestReading) {
		this.latestReading = latestReading;
	}

	public void setAssetReading(ArrayList<AssetReadingLatestBcDo> assetReading) {
		this.assetReading = assetReading;
	}

	public ArrayList<AssetReadingLatestBcDo> getAssetReading() {
		return assetReading;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getAssetId() {
		return assetId;
	}
}
