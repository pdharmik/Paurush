package com.lexmark.service.impl.real.partnerPaymentService;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.PartnerAccountListContract;
import com.lexmark.domain.Account;
import com.lexmark.result.PartnerAccountListResult;
import com.lexmark.service.impl.real.GlobalServiceStatelessBase;

@RunWith(Parameterized.class)
public class PartnerAccountListTest extends GlobalServiceStatelessBase {

	private final PartnerAccountListContract contract;

	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();
		list.add(new Object[] { "254334", "Legal" });
		//list.add(new Object[] { "12864", "Global" });
		return list;
	}

	public PartnerAccountListTest(String mdmId, String mdmLevel) {
		contract = new PartnerAccountListContract();
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
	}

	@Test
	public void testRetrieveAccountList() throws Exception {
		PartnerAccountListResult result = globalService.retrievePartnerAccountList(contract);
		assertNotNull("result is null!", result);
		List<Account> accountList = result.getAccountList();
		assertNotNull("account list is null!", accountList);
		assertFalse("account list is empty!", accountList.isEmpty());
		logAccountList(accountList);
	}

}
