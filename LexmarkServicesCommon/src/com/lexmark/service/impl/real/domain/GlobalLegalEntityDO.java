package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

/**
 * mapping file: "do-globalLegalEntity-mapping.xml"
 */
public class GlobalLegalEntityDO extends AccountBasedDo implements Serializable {

	private static final long serialVersionUID = -6678261491390182864L;
	private String accountLevel;
	private String dunsNumber;

	public String getDunsNumber() {
		return dunsNumber;
	}

	public void setDunsNumber(String dunsNumber) {
		this.dunsNumber = dunsNumber;
	}

	public void setAccountLevel(String accountLevel) {
		this.accountLevel = accountLevel;
	}

	public String getAccountLevel() {
		return accountLevel;
	}
}
