package com.lexmark.result;

import java.io.Serializable;
import java.util.List;

import com.lexmark.domain.MeterReadStatus;

public class MeterReadStatusListResult implements Serializable {
	private static final long serialVersionUID = 2886857752869754083L;
	private List<MeterReadStatus> meterReadStatusList;

	public List<MeterReadStatus> getMeterReadStatusList() {
		return meterReadStatusList;
	}

	public void setMeterReadStatusList(List<MeterReadStatus> meterReadStatusList) {
		this.meterReadStatusList = meterReadStatusList;
	}
}
