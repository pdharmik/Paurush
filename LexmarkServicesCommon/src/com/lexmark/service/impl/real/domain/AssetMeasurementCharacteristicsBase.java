package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.ArrayList;

import com.amind.common.domain.BaseEntity;

/**
 * IC: FS Asset Measurement Characteristics
 * 
 * Name is the needed field, but it's already in NamedEntity
 */
public class AssetMeasurementCharacteristicsBase extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 8912831824492802526L;
	
	private ArrayList<AssetReadingLatestBase> assetReading;

	public void setAssetReading(ArrayList<AssetReadingLatestBase> assetReading) {
		this.assetReading = assetReading;
	}

	public ArrayList<AssetReadingLatestBase> getAssetReading() {
		return assetReading;
	}
}
