package com.lexmark.contract;

import java.io.Serializable;

public class SiebelAccountIdContract implements Serializable {
    
    private static final long serialVersionUID = 1L;
	
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
