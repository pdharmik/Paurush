package com.lexmark.service.impl.real;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.MeterReadAssetListContract;
import com.lexmark.domain.Asset;
import com.lexmark.result.MeterReadAssetListResult;

@RunWith(Parameterized.class)
public class MeterReadAssetListPaginationTest extends MeterReadServiceTestBase {

	private final int startRecordNumber;
	private final int increment;

	@Parameters
	public static List<Object[]> paginationParameters() {
		List<Object[]> list = new ArrayList<Object[]>();
		list.add(new Object[] { 0, 100 });
		// TODO add other parameters
		return list;
	}

	public MeterReadAssetListPaginationTest(int startRecordNumber, int increment) {
		this.startRecordNumber = startRecordNumber;
		this.increment = increment;
	}

	@Test
	public void testRetrieveMeterReadAssetList() throws Exception {
		MeterReadAssetListContract contract = new MeterReadAssetListContract();
		contract.setMdmID("236295");
		contract.setMdmLevel("Legal");
		contract.setContactId("1-1LUIPWR");
		contract.setFavoriteFlag(false);
		contract.setStartRecordNumber(startRecordNumber);
		contract.setIncrement(increment);
		contract.setNewQueryIndicator(true);
		contract.setMeterReadType("Manual");
		contract.setSessionHandle(handle);
//		contract.setChlNodeId("1-QDH9FM");

		MeterReadAssetListResult result = service.retrieveMeterReadAssetList(contract);

		assertNotNull("result is null!", result);
		List<Asset> assets = result.getAssets();

		assertNotNull("asset list is null!", assets);
		assertFalse("asset list is empty!", assets.isEmpty());
		assertTrue("size more than increment",assets.size()<=increment);

		logAssetList(assets);
	}
}
