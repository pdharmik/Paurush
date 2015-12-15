package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;

/**
 * @author pkozlov
 *
 * mapping file: "do-agreement-mapping.xml"
 */
public class AgreementDo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -6041271121328596587L;

	private String agreementId;
	private String agreementName;

	public String getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

	public String getAgreementName() {
		return agreementName;
	}

	public void setAgreementName(String agreementName) {
		this.agreementName = agreementName;
	}
}
