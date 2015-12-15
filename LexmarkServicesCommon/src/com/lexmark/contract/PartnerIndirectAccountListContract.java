package com.lexmark.contract;

import java.io.Serializable;

public class PartnerIndirectAccountListContract implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4326061834039411111L;
	String mdmId;
	String mdmLevel;
	public String getMdmId() {
		return mdmId;
	}
	public void setMdmId(String mdmId) {
		this.mdmId = mdmId;
	}
	public String getMdmLevel() {
		return mdmLevel;
	}
	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}
	
	

}
