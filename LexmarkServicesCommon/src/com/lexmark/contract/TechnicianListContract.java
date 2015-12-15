package com.lexmark.contract;

import java.io.Serializable;

public class TechnicianListContract implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7656674724388973551L;
	
	private String partnerAccountId;

	public String getPartnerAccountId() {
		return partnerAccountId;
	}

	public void setPartnerAccountId(String partnerAccountId) {
		this.partnerAccountId = partnerAccountId;
	}
	
	
	

}
