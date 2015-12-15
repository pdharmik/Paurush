package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.contract.api.ContractBase;

public class DunsNumberContract extends ContractBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2286157205345269749L;

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
