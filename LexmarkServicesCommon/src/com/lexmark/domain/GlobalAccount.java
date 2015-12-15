package com.lexmark.domain;

import java.io.Serializable;

public class GlobalAccount implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2449175228914392573L;
	private String legalName;
	private String dunsNumber;
	private String mdmId;
	private String mdmLevel;
	private String displayMdmId;
	
	
	
	public String getDisplayMdmId() {
		return displayMdmId;
	}
	public void setDisplayMdmId(String displayMdmId) {
		this.displayMdmId = displayMdmId;
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
	public void setMdmId(String mdmId) {
		this.mdmId = mdmId;
	}
	public String getMdmId() {
		return mdmId;
	}
	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}
	public String getMdmLevel() {
		return mdmLevel;
	}
	
}
