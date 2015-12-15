package com.lexmark.service.impl.real;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.SiebelLOVListContract;
import com.lexmark.domain.ListOfValues;
import com.lexmark.result.SiebelLOVListResult;

@RunWith(Parameterized.class)
public class SiebelLovListTest extends GlobalServiceStatelessBase {

	private final SiebelLOVListContract contract;

	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> parameters = new ArrayList<Object[]>();

		SiebelLOVListContract contract;

		// 4.112 seconds
		contract = new SiebelLOVListContract();
		contract.setLovName("ACTUAL_FAIL_CD");
		parameters.add(new Object[] { contract });

		// 0.942 seconds
		contract = new SiebelLOVListContract();
		contract.setLovName("LXK_RESOLUTION_CODE");
		parameters.add(new Object[] { contract });

		// 0.429 seconds
		contract = new SiebelLOVListContract();
		contract.setLovName("EXP_ITEM_TYPE");
		parameters.add(new Object[] { contract });

		// 1.163 seconds
		contract = new SiebelLOVListContract();
		contract.setLovName("FS_CARRIER");
		parameters.add(new Object[] { contract });

		// 0.543 seconds
		contract = new SiebelLOVListContract();
		contract.setLovName("LXK_SERVICE_ERR_CODE_1");
		parameters.add(new Object[] { contract });

		// 10.322 seconds
		contract = new SiebelLOVListContract();
		contract.setLovName("LXK_SERVICE_ERR_CODE_2");
		contract.setErrorCode1("Safety");
		parameters.add(new Object[] { contract });

		// 0.414 seconds
		contract = new SiebelLOVListContract();
		contract.setLovName("LXK_DEVICE_CONDITION");
		parameters.add(new Object[] { contract });
		
		// 1.283 seconds
		contract = new SiebelLOVListContract();
		contract.setLovName("LXK_PART_DISPOSITION");
		parameters.add(new Object[] { contract });

		return parameters;
	}

	public SiebelLovListTest(SiebelLOVListContract contract) {
		this.contract = contract;
	}

	@Test
	public void testRetrieveSiebelLovList() throws Exception {
		logger.debug("[IN] testRetrieveSiebelLovList");

		logger.debug("input parameters: ");
		logger.debug("lovName: " + contract.getLovName());
		logger.debug("parentName: " + contract.getParentName());

		SiebelLOVListResult result = globalService.retrieveSiebelLOVList(contract);
		assertNotNull("result is null!",result);
		List<ListOfValues> lovList = result.getLovList();
		assertNotNull("result list is null!", lovList);
		assertFalse("result list is empty!",lovList.isEmpty());

		logger.debug("output list start ==============: ");
		for (ListOfValues lov : lovList) {
			logger.debug("<====list of values start:");
			logger.debug("Lov Name: " + lov.getName());
			logger.debug("lov value: " + lov.getValue());
			logger.debug("list of values end====>");
		}
		logger.debug("output list end ================: ");

		logger.debug("[OUT] testRetrieveSiebelLovList");
	}

}
