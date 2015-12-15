package com.lexmark.service.impl.real;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.MeterReadStatusContract;
import com.lexmark.domain.MeterReadStatus;
import com.lexmark.result.MeterReadStatusListResult;

@RunWith(Parameterized.class)
public class MeterReadStatusListTest extends MeterReadServiceTestBase {

	private final MeterReadStatusContract contract;

	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();
		MeterReadStatusContract contract;

		contract = new MeterReadStatusContract();
		contract.setMdmId("DUN1-L4BTZC");
		list.add(new Object[] { contract });

		// TODO add other parameters

		return list;
	}

	public MeterReadStatusListTest(MeterReadStatusContract contract) {
		this.contract = contract;
	}

	@Test
	public void testMeterReadStatusList() throws Exception {

		contract.setSessionHandle(handle);

		MeterReadStatusListResult result = service.retrieveMeterReadStatusList(contract);
		assertNotNull(result);

		List<MeterReadStatus> entityList = result.getMeterReadStatusList();
		assertNotNull("entityList is null", entityList);
		assertTrue("entityList is empty", entityList.size() > 0);

		int rowsToShow = entityList.size() < 10 ? entityList.size() : 10;
		MeterReadStatus entity = null;
		for (int i = 0; i < rowsToShow; i++) {
			entity = entityList.get(i);
			logger.info("attachmentName is " + entity.getAttachmentName());
			logger.info("size  is" + entity.getSize());
			logger.info("Completed Date is" + entity.getCompletedOn());
			logger.info("Submitted Date is" + entity.getSubmittedOn());
		}

	}
}
