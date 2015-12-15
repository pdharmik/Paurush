package com.lexmark.service.impl.real;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.lexmark.contract.MeterReadAssetListContract;
import com.lexmark.domain.Asset;
import com.lexmark.result.MeterReadAssetListResult;

public class MeterReadAssetFavoriteListTest extends MeterReadServiceTestBase {
	
	private final MeterReadAssetListContract contract = new MeterReadAssetListContract(); 
	
	@Before
	public void setUp() {
		contract.setMdmID("236295");
		contract.setMdmLevel("Legal");
		contract.setContactId("1-1LUIPWR");
		contract.setNewQueryIndicator(true);
		contract.setMeterReadType("Manual");
		contract.setSessionHandle(handle);
	}

	@Test
	public void testAllMeterReadAssetList() throws Exception {
		logger.debug("[IN] testAllMeterReadAssetList");
		
		contract.setFavoriteFlag(false);
		MeterReadAssetListResult result = service.retrieveMeterReadAssetList(contract);

		assertNotNull("result is null!", result);
		List<Asset> assets = result.getAssets();

		assertNotNull("asset list is null!", assets);
		assertFalse("asset list is empty!", assets.isEmpty());
		assertEquals("Total count is incorrectly set",result.getTotalCount(),assets.size());

		logAssetList(assets);
		
		logger.debug("[OUT] testAllMeterReadAssetList");
	}
	
	@Test
	public void testFavoriteMeterReadAssetList() throws Exception {
		logger.debug("[IN] testFavoriteMeterReadAssetList");
		
		contract.setFavoriteFlag(true);
		MeterReadAssetListResult result = service.retrieveMeterReadAssetList(contract);

		assertNotNull("result is null!", result);
		List<Asset> assets = result.getAssets();

		assertNotNull("asset list is null!", assets);
		assertFalse("asset list is empty!", assets.isEmpty());
		assertEquals("Total count is incorrectly set",result.getTotalCount(),assets.size());
		
		for (Asset asset: assets) {
			assertTrue("favorite flag is false!",asset.getUserFavoriteFlag());
		}

		logAssetList(assets);
		
		logger.debug("[OUT] testFavoriteMeterReadAssetList");
	}

}
