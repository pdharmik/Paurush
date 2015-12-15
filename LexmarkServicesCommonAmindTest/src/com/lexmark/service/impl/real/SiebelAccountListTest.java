package com.lexmark.service.impl.real;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.domain.Account;
import com.lexmark.result.SiebelAccountListResult;

/**
 * @see com.lexmark.service.impl.real.AmindContractedServiceRequestServiceTestSuite
 */
@RunWith(Parameterized.class)
public class SiebelAccountListTest extends RequestServiceStatelessBase {

	private final SiebelAccountListContract contract;

	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();

		list.add(new Object[] { "205529410", "Global" });

		return list;
	}

	public SiebelAccountListTest(String mdmId, String mdmLevel) {
		contract = new SiebelAccountListContract();
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(true);
	
	}

	@Test
	public void testRetrieveSiebelAccountList() {

		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		assertNotNull("result is null!", result);
		List<Account> accountList = result.getAccountList();
		assertNotNull("account list is null!", accountList);
		assertFalse("account list is empty!", accountList.isEmpty());
		
		logger.debug("list size: " + accountList.size());

		int max = accountList.size() > 10 ? 10 : accountList.size();
		logger.debug("First " + max + " accounts out of " + accountList.size());

		for (Account account : accountList) {
			logger.debug("Account: ID=" + account.getAccountId() + ", create SR="
					+ account.getCreateServiceRequest() + ", usesConsumable=" + account.getUserConsumables());
			logger.debug("agreementId: " + account.getAgreementId());
			logger.debug("agreementName: " + account.getAgreementName());
		}
	}

}
