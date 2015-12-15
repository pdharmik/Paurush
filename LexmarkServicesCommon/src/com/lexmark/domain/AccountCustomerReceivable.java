package com.lexmark.domain;

import java.io.Serializable;
import java.util.List;

public class AccountCustomerReceivable implements Serializable {
	private static final long serialVersionUID = 4391803560627945927L;
	
	private String soldTo;
	private String billTo;
	private GenericAddress billAddress; 
	private String customerNumber;
	private String accountName;
	private String companyDescription;
	private String companyValue;
	private List<CompanyCode> companyCodes;
	public List<CompanyCode> getCompanyCodes() {
		return companyCodes;
	}
	public void setCompanyCodes(List<CompanyCode> companyCodes) {
		this.companyCodes = companyCodes;
	}
	public String getSoldTo() {
		return soldTo;
	}
	public void setSoldTo(String soldTo) {
		this.soldTo = soldTo;
	}
	public String getBillTo() {
		return billTo;
	}
	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}
	public GenericAddress getBillAddress() {
		return billAddress;
	}
	public void setBillAddress(GenericAddress billAddress) {
		this.billAddress = billAddress;
	}
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getCompanyDescription() {
		return companyDescription;
	}
	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}
	public String getCompanyValue() {
		return companyValue;
	}
	public void setCompanyValue(String companyValue) {
		this.companyValue = companyValue;
	}	
}
