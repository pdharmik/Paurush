package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;

/**
 * mapping file: "do-paymenttypedo-mapping.xml"
 */

public class PaymentTypeDo extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -4582612066886357278L;
	
	private String paymentType;

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
    
}
