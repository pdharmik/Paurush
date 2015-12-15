package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.contract.api.ContractBase;

public class LegalNameContract extends ContractBase implements Serializable {
	private static final long serialVersionUID = 3567866658806771604L;
	
	private String mdmLevel;
	private String legalMdmId;
	
	public void setLegalMdmId(String legalMdmId) {
		this.legalMdmId = legalMdmId;
	}
	public String getLegalMdmId() {
		return legalMdmId;
	}
	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}
	public String getMdmLevel() {
		return mdmLevel;
	}

}
