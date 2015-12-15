package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;

/**
 * @author i.prikhodko
 *
 * do-mapping: "do-assetdetailspagecountsreadingsdo-mapping.xml"
 */

public class AssetDetailsPageCountsReadingsDO extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String reading;
	private String timestamp;

	public String getReading() {
		return reading;
	}

	public void setReading(String reading) {
		this.reading = reading;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "ReadingDo[reading=" + reading + ", timestamp=" + timestamp
				+ "]";
	}
}
