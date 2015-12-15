package com.lexmark.domain;

import java.io.Serializable;

/**
 * See "Partner Payments - AR-SAPv0.3.vsd", page 3 (Account Selection)
 * 
 * @author vpetruchok
 * @version 1.0, 2012-09-13
 */
public class AccountPayable implements Serializable {

    private static final long serialVersionUID = 1L;

    private String vendorId;
    private String vendorName;
    private String country;
    private String companyCode;
    private String payeeName;
    private GenericAddress payeeAddress;
    private String accountName;
    private String billTo;
    private String soldTo;
    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String value) {
        this.country = value;
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

    public GenericAddress getPayeeAddress() {
        return payeeAddress;
    }

    public void setPayeeAddress(GenericAddress payeeAddress) {
        this.payeeAddress = payeeAddress;
    }

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBillTo() {
		return billTo;
	}

	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}

	public String getSoldTo() {
		return soldTo;
	}

	public void setSoldTo(String soldTo) {
		this.soldTo = soldTo;
	}
    
}
