package com.lexmark.domain;

import java.io.Serializable;

public class PartnerAgreement implements Serializable {
	private static final long serialVersionUID = 386970732138813213L;

	private String partnerAgreementId;
	private String partnerAgreementName;

	public String getPartnerAgreementId() {
		return partnerAgreementId;
	}

	public void setPartnerAgreementId(String partnerAgreementId) {
		this.partnerAgreementId = partnerAgreementId;
	}

	public String getPartnerAgreementName() {
		return partnerAgreementName;
	}

	public void setPartnerAgreementName(String partnerAgreementName) {
		this.partnerAgreementName = partnerAgreementName;
	}
}
