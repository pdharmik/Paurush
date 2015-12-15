package com.lexmark.service.impl.real;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.GlobalLegalEntityContract;
import com.lexmark.domain.GlobalAccount;
import com.lexmark.result.GlobalLegalEntityResult;

@RunWith(Parameterized.class)
public class GlobalLegalEntityTest extends GlobalServiceStatelessBase {

	private final GlobalLegalEntityContract contract;
	private final String expectedMdmId;
	private final String expectedMdmLevel;
	private final String expectedName;

	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();

		// from old GlobalLegalEntityServiceTest
		list.add(new Object[] { "758640155", "Global", "758640155", "Global",
				"QUEENSLAND INVESTMENT CORPORATION" });
		list.add(new Object[] { "537479581", "Domestic", "295157374", "Global",
				"DAWNAY DAY INTERNATIONAL LTD" });
		list.add(new Object[] { "152442", "Legal", "295157374", "Global", "DAWNAY DAY INTERNATIONAL LTD" });
		list.add(new Object[] { "63168", "Legal", "63168", "Legal", "Coast Capital Insurance Services" });
		list.add(new Object[] { "94063", "Account", "63168", "Legal", "Coast Capital Insurance Services" });
		list.add(new Object[] { "159472", "Account", "295157374", "Global", "DAWNAY DAY INTERNATIONAL LTD" });
		list.add(new Object[] { "1-6LFVN", "Siebel", "295157374", "Global", "DAWNAY DAY INTERNATIONAL LTD" });
		list.add(new Object[] { "1-ORMLI9", "Siebel", "236295", "Legal", "CUMMINS INC" });

		list.add(new Object[] { "211156", "Legal", null, null, null });
		list.add(new Object[] { "623331717", "Global", null, null, null });

		// from old GlobalEntityListTest
		list.add(new Object[] { "1-8AOEMQ", "Siebel", null, null, null });
		list.add(new Object[] { "123456789", "Global", null, null, null });

		return list;
	}

	public GlobalLegalEntityTest(String mdmId, String mdmLevel, String expectedMdmId,
			String expectedMdmLevel, String expectedName) {

		contract = new GlobalLegalEntityContract();
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
		this.expectedMdmId = expectedMdmId;
		this.expectedMdmLevel = expectedMdmLevel;
		this.expectedName = expectedName;
	}

	@Test
	public void testRetrieveGlobalLegalEntity() {
		GlobalLegalEntityResult result = globalService.retrieveGlobalLegalEntity(contract);
		assertNotNull("result is null!", result);
		GlobalAccount account = result.getAccount();
		assertNotNull("account is null!", account);

		String mdmId = account.getMdmId();
		String mdmLevel = account.getMdmLevel();
		String name = account.getLegalName();

		logger.debug("mdmId=" + mdmId + ", mdmLevel=" + mdmLevel + ", name=" + name);

		if (expectedMdmId != null) {
			assertEquals("mdmId not correct", expectedMdmId, mdmId);
		}
		if (expectedMdmLevel != null) {
			assertEquals("mdmLevel not correct", expectedMdmLevel, mdmLevel);
		}
		if (expectedName != null) {
			assertEquals("legalName not correct", expectedName, name);
		}
	}

}
