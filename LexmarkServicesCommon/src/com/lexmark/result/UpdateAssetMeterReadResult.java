package com.lexmark.result;

import java.io.Serializable;

public class UpdateAssetMeterReadResult implements Serializable {

	private static final long serialVersionUID = 4460841568319887445L;
	
	private Boolean result;

	public void setResult(Boolean result) {
		this.result = result;
	}

	public Boolean getResult() {
		return result;
	}

}
