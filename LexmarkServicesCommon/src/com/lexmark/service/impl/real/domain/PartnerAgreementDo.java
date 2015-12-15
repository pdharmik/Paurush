package com.lexmark.service.impl.real.domain;

/**
 * mapping file: "do-partneragreement-mapping.xml"
 */
public class PartnerAgreementDo extends AccountBasedDo {
	private static final long serialVersionUID = -6339374962569272208L;

	private String mdmLevel;

	public String getMdmLevel() {
		return mdmLevel;
	}

	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}
}
