package com.lexmark.contract;

import java.io.Serializable;

public class FSEAccountListContract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5625944636327425689L;

	private String siebelEmployeeId;

	public String getSiebelEmployeeId() {
		return siebelEmployeeId;
	}

	public void setSiebelEmployeeId(String siebelEmployeeId) {
		this.siebelEmployeeId = siebelEmployeeId;
	}
	
	
}
