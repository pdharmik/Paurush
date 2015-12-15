package com.lexmark.service.api;

import com.lexmark.contract.AccountAgreementSoldToContract;
import com.lexmark.contract.AccountReceivableListContract;
import com.lexmark.result.AccountAgreementSoldToResult;
import com.lexmark.result.AccountCustomerReceivableListResult;

public interface CustomerPaymentsService {
	public AccountCustomerReceivableListResult retrieveAccountReceivableList(AccountReceivableListContract contract);
	public AccountAgreementSoldToResult retrieveMPSB2BList(AccountAgreementSoldToContract contract);
   
}
