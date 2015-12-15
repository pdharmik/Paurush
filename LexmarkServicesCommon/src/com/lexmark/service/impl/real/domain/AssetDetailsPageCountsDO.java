package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.ArrayList;

import com.amind.common.domain.BaseEntity;

/**
 * @author i.prikhodko
 *
 * do-mapping: "do-assetdetailspagecountsdo-mapping.xml"
 */


public class AssetDetailsPageCountsDO extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private ArrayList<AssetDetailsPageCountsReadingsDO> latestReadings;

	public ArrayList<AssetDetailsPageCountsReadingsDO> getLatestReadings() {
		return latestReadings;
	}

	public void setLatestReadings(
			ArrayList<AssetDetailsPageCountsReadingsDO> latestReadings) {
		this.latestReadings = latestReadings;
	}

}
