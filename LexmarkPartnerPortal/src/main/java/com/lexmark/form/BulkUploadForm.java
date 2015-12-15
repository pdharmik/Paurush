package com.lexmark.form;

import java.io.Serializable;

public class BulkUploadForm extends BaseForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1095990596459119610L;
	private float timezoneOffset;
	public float getTimezoneOffset() {
		return timezoneOffset;
	}
	public void setTimezoneOffset(float timezoneOffset) {
		this.timezoneOffset = timezoneOffset;
	}
}
