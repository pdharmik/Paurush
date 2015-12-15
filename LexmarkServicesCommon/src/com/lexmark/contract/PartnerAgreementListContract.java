package com.lexmark.contract;

import java.io.Serializable;

public class PartnerAgreementListContract implements Serializable {

	private static final long serialVersionUID = -2923754122229828980L;

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
