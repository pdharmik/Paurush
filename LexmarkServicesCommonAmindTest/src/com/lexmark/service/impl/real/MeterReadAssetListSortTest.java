package com.lexmark.service.impl.real;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.MeterReadAssetListContract;
import com.lexmark.domain.Asset;
import com.lexmark.result.MeterReadAssetListResult;

@RunWith(Parameterized.class)
public class MeterReadAssetListSortTest extends MeterReadServiceTestBase {
	
	private final Map<String, Object> sortCriteria;

	@Parameters
	public static List<Object[]> sortParameters() {
		List<Object[]> list = new ArrayList<Object[]>();
		list.add(new Object[] { "serialNumber", "ASCENDING" });
		// TODO add other parameters
		return list;
	}

	public MeterReadAssetListSortTest(String key, String value) {
		sortCriteria = new HashMap<String, Object>();
		sortCriteria.put(key, value);
	}

	@Test
	public void testRetrieveMeterReadAssetList() throws Exception {
		MeterReadAssetListContract contract = new MeterReadAssetListContract();
		contract.setMdmID("200443976");
		contract.setMdmLevel("Domestic");
		contract.setContactId("1-4IHKPB1");
		contract.setFavoriteFlag(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(-1);
		contract.setNewQueryIndicator(true);
		contract.setMeterReadType("Manual");
		//contract.setSortCriteria(sortCriteria);
		contract.setSessionHandle(handle);
		contract.setChlNodeId("1-219TUCC");
		Map<String,Object> filterMap = new HashMap<String,Object>();
	/*	filterMap.put("installAddress.city", "BC");
		filterMap.put("installAddress.country", "Canada");*/
		contract.setFilterCriteria(filterMap);
		MeterReadAssetListResult result = service.retrieveMeterReadAssetList(contract);

		
		assertNotNull("result is null!", result);
		List<Asset> assets = result.getAssets();

		assertNotNull("asset list is null!", assets);
		assertFalse("asset list is empty!", assets.isEmpty());
		assertEquals("Total count is incorrectly set",result.getTotalCount(),assets.size());

		logAssetList(assets);
	}
}
