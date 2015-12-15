package com.lexmark.service.impl.mock;

import java.util.List;

import com.lexmark.contract.AccountPayableListContract;
import com.lexmark.domain.AccountPayable;
import com.lexmark.result.AccountPayableListResult;
import com.lexmark.result.partnerPayments.AccountsPayableListResult;

public class AccountsPayableServiceImpl {
	
	public AccountPayableListResult retreiveAccountsPayableList(AccountPayableListContract contract) {		
		List<AccountPayable> accountsPayableList = PartnerDomainMockDataGenerator.getAccountsPayableList();
		AccountPayableListResult result = new AccountPayableListResult(accountsPayableList,accountsPayableList.size());
		return result;
	}

}
