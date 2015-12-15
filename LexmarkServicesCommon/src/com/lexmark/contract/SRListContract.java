package com.lexmark.contract;

import java.io.Serializable;
import com.lexmark.contract.api.SearchContractBase;

public class SRListContract extends SearchContractBase implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String vendorID;
	private String companyCode;
	private String invoiceNumber;
	private int posStart;
	
	public String getVendorID() {
		return vendorID;
	}
	public void setVendorID(String vendorID) {
		this.vendorID = vendorID;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public int getPosStart() {
		return posStart;
	}
	public void setPosStart(int posStart) {
		this.posStart = posStart;
	}
	
	
	
}
