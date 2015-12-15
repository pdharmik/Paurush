package com.lexmark.result;

import java.io.Serializable;

public class AssetMeterReadResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean upDateSuccess;
	public boolean isUpDateSuccess() {
		return upDateSuccess;
	}
	public void setUpDateSuccess(boolean upDateSuccess) {
		this.upDateSuccess = upDateSuccess;
	}

}
