package com.lexmark.service.impl.real;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import util.TestSessionFactories;

import com.amind.session.StatelessSessionFactory;
import com.lexmark.contract.GlobalAssetListContract;
import com.lexmark.result.AssetListResult;
import com.lexmark.service.api.DeviceService;
import com.lexmark.service.api.SiebelCrmServiceException;

public class AccountGlobalListAssetTest {
	
	protected static final Log logger = 
		LogFactory.getLog(AccountGlobalListAssetTest.class);
	@Test
	public void testRetrieveGlobalAssetList() {
		String serialNumber = "9929MFT";

		DeviceService service = new AmindContractedDeviceService();
		StatelessSessionFactory sessionFactory =  TestSessionFactories.newStatelessSessionFactory();
		GlobalAssetListContract assetContract = new GlobalAssetListContract();
		((AmindContractedDeviceService)service).setSessionFactory(sessionFactory);
		assetContract.setSerialNumber(serialNumber);
		AssetListResult result = null;
		try {
				long endTime = 0L;
				long startTime = System.currentTimeMillis();
				int iterationCount = 0;

					
				logger.info
					("\nRetrieveDeviceList() -- " +
							"iteration "+iterationCount +" " +
									"elapsed Time(ms): " + 
									(endTime - startTime));
				result = service.retrieveGlobalAssetList(assetContract);
				logAssetList(result);
				
				if (result == null || 
						result.getAssets() == null || 
						result.getAssets().size() == 0){	
				}

		}
		catch(Exception e) {
			logger.error("Test case failed", e);
			throw new SiebelCrmServiceException(e);
		}
		finally 
		{
			sessionFactory.releaseAllStatelessSessions();
		}
	}
	
	

	private void logAssetList(AssetListResult result) {
		logger.info("Loggin results");
		List<com.lexmark.domain.Asset> assetList = result.getAssets();
			
		if(assetList != null && assetList.size() > 0)
		{

			logger.info("Found serviceRequest count = " + 
					assetList.size());
			for (com.lexmark.domain.Asset asset: assetList) {
				logger.info("product TLI:" + asset.getProductTLI());
				logger.info("serial number:" + asset.getSerialNumber());
				logger.info("Asset ID:" + asset.getAssetId());
				logger.info("Favorite:" + asset.getUserFavoriteFlag());
				logger.info("Machine Model:" + asset.getModelNumber());
				}
		}
		else
			logger.info("Service requests not found");
	}
}


