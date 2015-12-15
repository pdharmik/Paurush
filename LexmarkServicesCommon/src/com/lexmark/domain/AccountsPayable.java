package com.lexmark.domain;

import java.io.Serializable;

import com.lexmark.contract.api.ContractBase;

public class AccountsPayable implements Serializable {

	/**
	 * This is the mock vendor class
	 */
	private static final long serialVersionUID = -854119297837494743L;
	
	private String vendorId;
	private String vendorName;
	private String country;
	private String companyCode;
	private String payeeName;
	private GenericAddress payableAddr;
	
	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public GenericAddress getPayableAddr() {
		return payableAddr;
	}

	public void setPayableAddr(GenericAddress payableAddr) {
		this.payableAddr = payableAddr;
	} 

}
