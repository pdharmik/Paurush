package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.Date;

import com.amind.common.domain.BaseEntity;

/**
 * IC: LXK SD Asset Reading Latest
 */
public class AssetReadingLatestBase extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -2119251394705678954L;

	private String assetMeasurementId;
	private Boolean completeFlag;
	private String reading;
	private Date timestamp;
	private boolean exceptionFlag;

	public boolean isExceptionFlag() {
		return exceptionFlag;
	}

	public void setExceptionFlag(boolean exceptionFlag) {
		this.exceptionFlag = exceptionFlag;
	}

	public void setAssetMeasurementId(String assetMeasurementId) {
		this.assetMeasurementId = assetMeasurementId;
	}

	public String getAssetMeasurementId() {
		return assetMeasurementId;
	}

	public void setCompleteFlag(Boolean completeFlag) {
		this.completeFlag = completeFlag;
		if (completeFlag == null)
			this.completeFlag = false;
	}

	public Boolean getCompleteFlag() {
		return completeFlag;
	}

	public void setReading(String reading) {
		this.reading = reading;
	}

	public String getReading() {
		return reading;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Date getTimestamp() {
		return timestamp;
	}

}
