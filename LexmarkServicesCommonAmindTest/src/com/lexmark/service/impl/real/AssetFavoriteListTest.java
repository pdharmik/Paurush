package com.lexmark.service.impl.real;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.lexmark.contract.AssetListContract;
import com.lexmark.domain.Asset;
import com.lexmark.result.AssetListResult;

@RunWith(Parameterized.class)
public class AssetFavoriteListTest extends DeviceServiceStatefulBase {

	private final AssetListContract contract;

	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> list = new ArrayList<Object[]>();

		// TODO (pkozlov) need to pass some "contactId", I am not sure what is exactly
		list.add(new Object[]{"1-1QRZ7Z","Siebel","","",""});
		
		return list;
	}

	// TODO may be chlNodeID or assetType are not neccessary, I am not sure about it
	public AssetFavoriteListTest(String mdmId, String mdmLevel, String contactId, String chlNodeId,
			String assetType) {
		
		contract = new AssetListContract();
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
		contract.setContactId(contactId);
		contract.setChlNodeId(chlNodeId);
		contract.setAssetType(assetType);

		contract.setSessionHandle(handle);
		contract.setNewQueryIndicator(true);
	}
	
	@Test
	public void testFavoriteAssetList() throws Exception {
		contract.setFavoriteFlag(true);
		doTestAssetList(contract);
	}
	
	@Test
	public void testAllAssetList() throws Exception {
		contract.setFavoriteFlag(false);
		doTestAssetList(contract);
	}
	
	private void doTestAssetList(AssetListContract contract) throws Exception {
		AssetListResult result = service.retrieveDeviceList(contract);
		assertNotNull("result is null!",result);
		List<Asset> assetList = result.getAssets();
		assertNotNull("asset list is null!",assetList);
		assertFalse("asset list is empty",assetList.isEmpty());
		
		int size = assetList.size();
		int totalCount = result.getTotalCount();
		logger.debug("asset list size: " + size);
		logger.debug("result total count: " + totalCount);
		assertEquals("Total Count is incorrectly set!",size,totalCount);
		
		if (contract.isFavoriteFlag()) {
			for (Asset asset: assetList) {
				assertTrue("user favorite flag is not set!",asset.getUserFavoriteFlag());
			}
		}
	}

}
