package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.contract.api.ContractBase;

public class GlobalLegalEntityContract extends ContractBase implements Serializable {

	private static final long serialVersionUID = -7293051931555405862L;
	
	private String mdmId;
	private String mdmLevel;
	
	public void setMdmId(String mdmId) {
		this.mdmId = mdmId;
	}
	public String getMdmId() {
		return mdmId;
	}
	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}
	public String getMdmLevel() {
		return mdmLevel;
	}

}
