package com.lexmark.service.impl.real;

import static org.junit.Assert.*;
import static com.lexmark.service.impl.real.util.AmindServiceUtil.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import org.junit.runner.RunWith;

import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.domain.Account;
import com.lexmark.result.SiebelAccountListResult;

@RunWith(Parameterized.class)
public class SiebelAgreementListTest extends GlobalServiceStatelessBase {

	private final SiebelAccountListContract contract;

	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();

		list.add(new Object[] { "1-7823PB", ACCOUNT_MDMLEVEL });

		return list;
	}

	public SiebelAgreementListTest(String mdmId, String mdmLevel) {
		contract = new SiebelAccountListContract();
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
	}

	@Test
	public void testSiebelAgreementList() throws Exception {
		SiebelAccountListResult result = globalService.retrieveSiebelAgreementList(contract);
		assertNotNull("result is null!", result);
		List<Account> accounts = result.getAccountList();
		assertNotNull("list is null!", accounts);
		assertFalse("list is empty!", accounts.isEmpty());
	}

}
