package com.lexmark.contract;

import com.lexmark.contract.api.MdmSearchContractBase;

/**
 * @author vpetruchok
 * @version 1.0, 2012-10-10
 */
public class AccountPayableDetailContract extends MdmSearchContractBase {

    private static final long serialVersionUID = 1L;

    private String serviceRequestNumber;

    
    public String getServiceRequestNumber() {
        return serviceRequestNumber;
    }

    public void setServiceRequestNumber(String serviceRequestNumber) {
        this.serviceRequestNumber = serviceRequestNumber;
    }
}