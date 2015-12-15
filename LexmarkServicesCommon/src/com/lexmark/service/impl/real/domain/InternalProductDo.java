package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;

/**
 * mapping file: "do-internalproductdo-mapping.xml"
 */
public class InternalProductDo extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 4799070523894506236L;

	private String modelNumber;
	private String productTli;

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getProductTli() {
		return productTli;
	}

	public void setProductTli(String productTli) {
		this.productTli = productTli;
	}

}
