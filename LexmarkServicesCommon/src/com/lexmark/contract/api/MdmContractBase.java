package com.lexmark.contract.api;

public abstract class MdmContractBase extends ContractBase {
	private static final long serialVersionUID = -1088202795823957926L;

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
