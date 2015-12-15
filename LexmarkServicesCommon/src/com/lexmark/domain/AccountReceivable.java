package com.lexmark.domain;

import java.io.Serializable;

/**
 * See Integrations_PartnerPayments_ARAP.xls .
 * 
 * @see com.lexmark.domain.AccountPayable
 * 
 * @author vpetruchok
 * @version 1.0, 2012-10-09
 */
public class AccountReceivable implements Serializable {

    private static final long serialVersionUID = 1L;

    private String accountName;
    private String companyCode;
    private String soldToNumber;
    private GenericAddress billAddress; 

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getSoldToNumber() {
        return soldToNumber;
    }

    public void setSoldToNumber(String soldToNumber) {
        this.soldToNumber = soldToNumber;
    }

    public GenericAddress getBillAddress() {
        return billAddress;
    }

    public void setBillAddress(GenericAddress billAddress) {
        this.billAddress = billAddress;
    }
    
}
