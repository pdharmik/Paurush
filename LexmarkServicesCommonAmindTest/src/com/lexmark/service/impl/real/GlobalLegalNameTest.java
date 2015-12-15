package com.lexmark.service.impl.real;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.LegalNameContract;
import com.lexmark.result.LegalNameResult;

@RunWith(Parameterized.class)
public class GlobalLegalNameTest extends GlobalServiceStatelessBase {

	private final LegalNameContract contract;

	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();

		list.add(new Object[] { "L1-L4BTZC", "Legal" });
		list.add(new Object[] { "1-8AOEMQ", "Siebel" });
		list.add(new Object[] { "123456789", "Global" });

		return list;
	}

	public GlobalLegalNameTest(String legalMdmId, String mdmLevel) {
		contract = new LegalNameContract();
		contract.setLegalMdmId(legalMdmId);
		contract.setMdmLevel(mdmLevel);
	}

	@Test
	public void testRetrieveLegalName() {
		LegalNameResult result = globalService.retrieveLegalName(contract);
		assertNotNull("result is null!", result);
		String legalName = result.getLegalName();
		assertNotNull("legalName is null!", legalName);
		assertFalse("legalName is empty!", legalName.isEmpty());
		logger.debug("Legal Name: " + legalName);
	}

}
