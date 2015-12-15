package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;

/**
 * mapping file: "do-soldtonumberdo-mapping.xml"
 */

public class SoldToNumberDo extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -7989796720182927399L;

	private String contractNumber;
	private String soldToNumber;

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getSoldToNumber() {
		return soldToNumber;
	}

	public void setSoldToNumber(String soldToNumber) {
		this.soldToNumber = soldToNumber;
	}
    
}
