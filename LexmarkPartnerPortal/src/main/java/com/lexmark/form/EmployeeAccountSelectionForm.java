package com.lexmark.form;

import java.io.Serializable;

public class EmployeeAccountSelectionForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6031563234026275183L;
	private String legalName;
	private String accountNumber;
	private boolean showFseSelector;
	
	public String getLegalName() {
		return legalName;
	}
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public boolean isShowFseSelector() {
		return showFseSelector;
	}
	public void setShowFseSelector(boolean showFseSelector) {
		this.showFseSelector = showFseSelector;
	}
	
	
}
