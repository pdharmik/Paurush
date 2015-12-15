package com.lexmark.domain;

import java.io.Serializable;

public class LegalEntity  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2449175228914392573L;
	private String mdmId;
	private String mdmLevel;
	private String legalName;
	private String dunsNumber;
	
	public String getMdmId() {
		return mdmId;
	}
	public void setMdmId(String mdmId) {
		this.mdmId = mdmId;
	}
	public String getMdmLevel() {
		return mdmLevel;
	}
	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}
	public String getLegalName() {
		return legalName;
	}
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	public String getDunsNumber() {
		return dunsNumber;
	}
	public void setDunsNumber(String dunsNumber) {
		this.dunsNumber = dunsNumber;
	}
	
}
