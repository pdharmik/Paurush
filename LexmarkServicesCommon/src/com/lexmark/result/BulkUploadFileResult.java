package com.lexmark.result;

import java.io.Serializable;

public class BulkUploadFileResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6040123260025657549L;
	private boolean upDateSuccess;
	public boolean isUpDateSuccess() {
		return upDateSuccess;
	}
	public void setUpDateSuccess(boolean upDateSuccess) {
		this.upDateSuccess = upDateSuccess;
	}

}
