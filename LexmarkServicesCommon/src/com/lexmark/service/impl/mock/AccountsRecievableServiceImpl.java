package com.lexmark.service.impl.mock;

import java.util.List;

import com.lexmark.contract.AccountReceivableListContract;
import com.lexmark.contract.SRDetailContract;
import com.lexmark.domain.AccountPayable;
import com.lexmark.domain.SRDetail;
import com.lexmark.result.partnerPayments.AccountsRecievableListResult;
import com.lexmark.result.partnerPayments.SRDetailResult;

public class AccountsRecievableServiceImpl {

	public AccountsRecievableListResult retreiveAccountsRcvblList(AccountReceivableListContract contract) {
		AccountsRecievableListResult result = new AccountsRecievableListResult();
		List<AccountPayable> accountsRecivblList = PartnerDomainMockDataGenerator.getAccountsRecivblList();
		result.setAccountsRecievableList(accountsRecivblList);
		result.setTotalCount(accountsRecivblList.size());
		return result;
	}

	public SRDetailResult retrieveSRDetails(SRDetailContract contract) {
		SRDetailResult result = new SRDetailResult();
		List<SRDetail> srDetailsList = PartnerDomainMockDataGenerator.getSRDetailsList();
		result.setSrDetailsList(srDetailsList);
		result.setTotalCount(srDetailsList.size());
		return result;
	}
}
