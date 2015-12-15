package com.lexmark.result;

import java.io.Serializable;

public class AssignedTechnicianUpdateResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5943306050061747099L;
	
	private Boolean result;

	public void setResult(Boolean result) {
		this.result = result;
	}

	public Boolean getResult() {
		return result;
	}

}
