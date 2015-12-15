package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import com.amind.common.domain.BaseEntity;

/**
 * @author pkozlov
 * 
 * mapping file "do-agreementaccount-mapping.xml"
 */
public class AgreementAccountDo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -6790695299318938480L;

	private String agreementId;

	public String getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

}
