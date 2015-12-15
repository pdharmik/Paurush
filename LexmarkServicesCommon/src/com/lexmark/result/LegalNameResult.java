package com.lexmark.result;

import java.io.Serializable;

public class LegalNameResult implements Serializable {

	private static final long serialVersionUID = 7248691885118970798L;
	
	private String legalName;

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public String getLegalName() {
		return legalName;
	}

}
