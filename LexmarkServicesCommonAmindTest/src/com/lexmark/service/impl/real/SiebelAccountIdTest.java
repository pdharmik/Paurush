package com.lexmark.service.impl.real;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.SiebelAccountIdContract;
import com.lexmark.result.SiebelAccountIdResult;

@RunWith(Parameterized.class)
public class SiebelAccountIdTest extends GlobalServiceStatelessBase {

	private final SiebelAccountIdContract contract;

	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();
		list.add(new Object[] { "50901", "Global" });
		return list;
	}

	public SiebelAccountIdTest(String mdmId, String mdmLevel) {
		contract = new SiebelAccountIdContract();
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
	}

	@Test
	public void testRetrievePartnerAccountList() throws Exception {
		SiebelAccountIdResult result = globalService.retrieveSiebelAccountId(contract);
		assertNotNull("result is null!", result);
		String accountId = result.getSiebelAccountId();
		assertNotNull("siebel account id is null!", accountId);
		assertFalse("siebel account id is empty!", accountId.isEmpty());
		logger.debug("siebel account id: " + accountId);
	}
}
