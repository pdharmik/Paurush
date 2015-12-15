package com.lexmark.contract;

import java.io.Serializable;

public class OpenClaimListContract implements Serializable {

	/**
	 * Contract to retrieve open claim list.
	 */
	private static final long serialVersionUID = 6001262079889617836L;
	
	private String assetId;
	
	private String mdmId;
	
	private String mdmLevel;

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

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
