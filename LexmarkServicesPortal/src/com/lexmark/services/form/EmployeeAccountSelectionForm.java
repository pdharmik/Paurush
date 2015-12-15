package com.lexmark.services.form;

import java.io.Serializable;

public class EmployeeAccountSelectionForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3855030232196107149L;
	/**
	 * 
	 * @return String 
	 */
	public String getLegalName() {
		return legalName;
	}
	/**
	 * 
	 * @param legalName 
	 */
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	private String legalName;
	
}
