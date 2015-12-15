package com.lexmark.service.impl.real.domain;

import java.util.ArrayList;


/**
 * The mapping file:  do-agreementFlagsdo-mapping.xml
 * 
 */
public class AgreementFlagsDo extends AccountDo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -195920888404561403L;
	private ArrayList<AccountServiceAgreementDo> accountServiceAgreements;
	
    public ArrayList<AccountServiceAgreementDo> getAccountServiceAgreements() {
        return accountServiceAgreements;
    }

    public void setAccountServiceAgreements(ArrayList<AccountServiceAgreementDo> serviceAgreements) {
        this.accountServiceAgreements = serviceAgreements;
    }
}
