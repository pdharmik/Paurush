package com.lexmark.service.impl.real;

import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.Parameterized.*;
import org.junit.runners.*;
import static org.junit.Assert.*;

import com.lexmark.contract.MeterReadAssetListContract;
import com.lexmark.domain.Asset;
import com.lexmark.result.MeterReadAssetListResult;
import java.util.*;

@RunWith(Parameterized.class)
public class MeterReadAssetListFilterTest extends MeterReadServiceTestBase {

	private final Map<String, Object> filterCriteria;

	@Parameters
	public static List<Object[]> filterParameters() {
		List<Object[]> list = new ArrayList<Object[]>();
		list.add(new Object[] { "serialNumber", "3500" });
		// TODO add other parameters
		return list;
	}

	public MeterReadAssetListFilterTest(String key, String value) {
		filterCriteria = new HashMap<String, Object>();
		filterCriteria.put(key, value);
	}

	@Test
	public void testRetrieveMeterReadAssetList() throws Exception {
		MeterReadAssetListContract contract = new MeterReadAssetListContract();
		contract.setMdmID("236295");
		contract.setMdmLevel("Legal");
		contract.setContactId("1-1LUIPWR");
		contract.setFavoriteFlag(false);
		contract.setStartRecordNumber(0);
		contract.setIncrement(100);
		contract.setNewQueryIndicator(true);
		contract.setMeterReadType("Manual");
		contract.setFilterCriteria(filterCriteria);
		contract.setSessionHandle(handle);

		MeterReadAssetListResult result = service.retrieveMeterReadAssetList(contract);

		assertNotNull("result is null!", result);
		List<Asset> assets = result.getAssets();

		assertNotNull("asset list is null!", assets);
		assertFalse("asset list is empty!", assets.isEmpty());
		assertEquals("Total count is incorrectly set",result.getTotalCount(),assets.size());

		logAssetList(assets);
	}

}
