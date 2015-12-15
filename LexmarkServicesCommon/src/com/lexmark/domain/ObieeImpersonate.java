package com.lexmark.domain;

import java.io.Serializable;

public class ObieeImpersonate implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7531499471165710919L;
	private String userId;
	private String siebelAccountId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSiebelAccountId() {
		return siebelAccountId;
	}
	public void setSiebelAccountId(String siebelAccountId) {
		this.siebelAccountId = siebelAccountId;
	}
	
}
