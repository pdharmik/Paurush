package com.lexmark.service.impl.real;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import util.TestSessionFactories;

import com.amind.session.Session;
import com.lexmark.contract.AssetListContract;
import com.lexmark.result.AssetListResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.DeviceService;
import com.lexmark.service.api.SiebelCrmServiceException;

public class AccountAssetIncrementTest {
	
	protected static final Log logger = 
		LogFactory.getLog(AccountAssetIncrementTest.class);
	@Test
	public void testRetrieveAssetsAllRecordsViewAll() throws Exception {
		
		String mdmId = "234567890";
		String mdmLevel = "Domestic";
		int startIndex = 0;
		String contactId = "1-P3YKE";
		int numRecords = -1;
		AmindGlobalService globalService = new AmindGlobalService();
		globalService.setStatefulSessionFactory(TestSessionFactories.newStatefulSessionFactory());
		DeviceService service = new AmindContractedDeviceService();
		CrmSessionHandle handle = globalService.initCrmSessionHandle(null);
		
		AssetListContract assetConract = new AssetListContract();
		assetConract.setSessionHandle(handle);
		assetConract.setContactId(contactId);
		assetConract.setMdmId(mdmId);
		assetConract.setMdmLevel(mdmLevel);
		assetConract.setStartRecordNumber(startIndex);
		assetConract.setIncrement(numRecords);
		assetConract.setNewQueryIndicator(true);
		assetConract.setFavoriteFlag(false);
		AssetListResult result = null;
		try {
				long endTime = 0L;
				long startTime = System.currentTimeMillis();
				int iterationCount = 0;
				logger.info("Search criteria: mdm Id is '" + mdmId + "' mdm level is '" + mdmLevel+"'.");
				startTime = System.currentTimeMillis();
				result = service.retrieveDeviceList(assetConract);
					
				logger.info
					("\nRetrieveServiceReuqestList() -- " +
							"iteration "+iterationCount +" " +
									"elapsed Time(ms): " + 
									(endTime - startTime));
				logAssetList(result);
				
				if (result == null || 
						result.getAssets() == null || 
						result.getAssets().size() == 0){
					logger.info("No records found for mdmId: " + mdmId + " and mdmLevel: " + mdmLevel);
					
				}

			logger.info("We're done, found total of " + startIndex + " records.");

		}
		catch(Exception e) {
			logger.error("Test case failed", e);
			throw new SiebelCrmServiceException(e);
		}
		finally {
//			session.closeSession(sessionId);
			if (handle != null) {
				try {
					Session session = ((AmindCrmSessionHandle) handle).acquire();
					if (session != null) {
						session.release();
					}
					((AmindCrmSessionHandle) handle).release();
				}
				catch (InterruptedException e) {
					//squash
				}
			}
		}
	}

	private void logAssetList(AssetListResult result) {
		List<com.lexmark.domain.Asset> assetList = 
			result.getAssets();
			
		if(assetList != null && assetList.size() > 0)
		{
			logger.info("Found serviceRequest count = " + 
					assetList.size());
			for (com.lexmark.domain.Asset asset: assetList) {
				logger.info("serialNumber:" + asset.getSerialNumber() +
						" Asset Tag" + asset.getAssetTag());

				}
		}
	}
}


