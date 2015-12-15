package com.lexmark.service.impl.real.partnerPaymentService;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.FSEAccountListContract;
import com.lexmark.domain.Account;
import com.lexmark.result.FSEAccountListResult;
import com.lexmark.service.impl.real.GlobalServiceStatelessBase;

@RunWith(Parameterized.class)
public class PartnerDirectAccountListTest extends GlobalServiceStatelessBase {

	private final FSEAccountListContract contract;

	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();
		list.add(new Object[] { "1-E89900" });
		list.add(new Object[] { "1-2S8TRNT" });
		return list;
	}

	public PartnerDirectAccountListTest(String employeeId) {
		contract = new FSEAccountListContract();
		contract.setSiebelEmployeeId(employeeId);
	}

	@Test
	public void testRetrieveFSEAccountList() throws Exception {
		FSEAccountListResult result = globalService.retrieveFSEAccountList(contract);
		assertNotNull("result is null!", result);
		List<Account> accountList = result.getAccountList();
		assertNotNull("account list is null!", accountList);
		assertFalse("account list is empty!", accountList.isEmpty());
		logAccountList(accountList);
	}

}
