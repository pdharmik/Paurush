package com.lexmark.service.impl.real;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.DunsNumberContract;
import com.lexmark.result.DunsNumberResult;

@RunWith(Parameterized.class)
public class DunsNumberTest extends GlobalServiceStatelessBase {

	private final DunsNumberContract contract;

	@Parameters
	public static List<Object[]> testParameters() {
		List<Object[]> list = new ArrayList<Object[]>();

		list.add(new Object[] { "50901", "Global" });
		list.add(new Object[] { "T74848", "Account" });

		return list;
	}

	public DunsNumberTest(String mdmId, String mdmLevel) {
		contract = new DunsNumberContract();
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
		contract.setSessionHandle(null);
	}

	@Test
	public void testDunsNumber() {
		DunsNumberResult result = globalService.retrieveDunsNumber(contract);
		assertNotNull("result is null!",result);
		String dunsNumber = result.getDunsNumber();
		assertNotNull("dunsNumber is null!",dunsNumber);
		assertFalse("dunsNumber is empty!",dunsNumber.isEmpty());
		
		logger.debug("DUNS Number:" + dunsNumber);
	}
}
