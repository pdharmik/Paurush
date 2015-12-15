package com.lexmark.result;

import java.io.Serializable;

public class SiebelAccountIdResult implements Serializable {

    private static final long serialVersionUID = 1L;
	public String siebelAccountId;

	public String getSiebelAccountId() {
		return siebelAccountId;
	}

	public void setSiebelAccountId(String siebelAccountId) {
		this.siebelAccountId = siebelAccountId;
	}
	
	
}
