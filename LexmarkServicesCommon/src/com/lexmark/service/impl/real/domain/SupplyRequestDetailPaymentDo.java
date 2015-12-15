package com.lexmark.service.impl.real.domain;

/**
 *  The mapping file: do-supplyrequestdetailpayment-mapping.xml
 */

import java.io.Serializable;
import java.math.BigDecimal;

import com.amind.common.domain.BaseEntity;

public class SupplyRequestDetailPaymentDo extends BaseEntity implements
		Serializable {

	private static final long serialVersionUID = -951300437748960784L;

	private String creditCardToken;
	private String creditCardType;

	public String getCreditCardToken() {
		return creditCardToken;
	}

	public void setCreditCardToken(String creditCardToken) {
		this.creditCardToken = creditCardToken;
	}

	public String getCreditCardType() {
		return creditCardType;
	}

	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}

}
