package com.lexmark.contract.api;

public class MdmSearchContractBase extends SearchContractBase {
	private static final long serialVersionUID = -3820710343575019191L;

	private String mdmId;
	private String mdmLevel;

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
