package com.lexmark.service.impl.real;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import util.TestSessionFactories;

import com.amind.session.Session;
import com.amind.session.StatelessSessionFactory;
import com.lexmark.contract.AssetListContract;
import com.lexmark.contract.UserFavoriteAssetContract;
import com.lexmark.result.AssetListResult;
import com.lexmark.result.FavoriteResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.DeviceService;
import com.lexmark.service.api.SiebelCrmServiceException;

public class AccountAssetFavoriteTest {

	protected static final Log logger = LogFactory.getLog(AccountAssetFavoriteTest.class);

	@Test
	public void testSuiteRetrieveFavoriteAssets() throws Exception {
		testRetrieveFavoriteAssets("L1-L4BTZC", "Legal", "1-P3YKE");
	}

	protected void testRetrieveFavoriteAssets(String mdmId, String mdmLevel, String contactId)
			throws Exception {
		int startIndex = 0;
		int numRecords = 100;
		AmindGlobalService globalService = new AmindGlobalService();
		globalService.setStatefulSessionFactory(TestSessionFactories.newStatefulSessionFactory());
		DeviceService service = new AmindContractedDeviceService();
		CrmSessionHandle handle = globalService.initCrmSessionHandle(null);

		AssetListContract assetConract = new AssetListContract();
		assetConract.setSessionHandle(handle);
		assetConract.setMdmId(mdmId);
		assetConract.setMdmLevel(mdmLevel);
		assetConract.setContactId(contactId);
		assetConract.setStartRecordNumber(startIndex);
		assetConract.setIncrement(numRecords);
		assetConract.setNewQueryIndicator(true);
		assetConract.setFavoriteFlag(true);
		AssetListResult result = null;
		assetConract.setChlNodeId("1-219TUC4");
		try {
			long endTime = 0L;
			long startTime = System.currentTimeMillis();
			int iterationCount = 0;
			startTime = System.currentTimeMillis();
			result = service.retrieveDeviceList(assetConract);

			logger.info("\nRetrieveServiceReuqestList() -- " + "iteration " + iterationCount + " "
					+ "elapsed Time(ms): " + (endTime - startTime));
			logAssetList(result);

			if (result == null || result.getAssets() == null || result.getAssets().size() == 0) {
				logger.info("No records found for contactId: " + contactId);

			}

			logger.info("We're done, found total of " + startIndex + " records.");

		} catch (Exception e) {
			logger.error("Test case failed", e);
			throw new SiebelCrmServiceException(e);
		} finally {
			if (handle != null) {
				try {
					Session session = ((AmindCrmSessionHandle) handle).acquire();
					if (session != null) {
						session.release();
					}
					((AmindCrmSessionHandle) handle).release();
				} catch (InterruptedException e) {
					// squash
				}
			}
		}
	}

	private void logAssetList(AssetListResult result) {
		List<com.lexmark.domain.Asset> assetList = result.getAssets();

		if (assetList != null && assetList.size() > 0) {
			logger.info("Found serviceRequest count = " + assetList.size());
			for (com.lexmark.domain.Asset asset : assetList) {
				logger.info("Asset ID:" + asset.getAssetId());
				logger.info("Favorite:" + asset.getUserFavoriteFlag());
			}
		}
	}

	@Test
	public void testRetrieveDeviceListByCHLNodeId() {
		// String chlId = "1-3F3QGI";
		String chlId = "1-219TUC4";
		int startIndex = 0;
		int numRecords = 100;

		AmindGlobalService globalService = new AmindGlobalService();
		globalService.setStatefulSessionFactory(TestSessionFactories.newStatefulSessionFactory());
		CrmSessionHandle handle = globalService.initCrmSessionHandle(null);

		AssetListResult result = null;

		try {
			DeviceService service = new AmindContractedDeviceService();

			AssetListContract contract = new AssetListContract();
			contract.setSessionHandle(handle);

			contract.setStartRecordNumber(startIndex);
			contract.setIncrement(numRecords);
			contract.setNewQueryIndicator(true);
			contract.setChlNodeId(chlId);
			contract.setContactId(chlId);
			contract.setMdmId("023058159");
			contract.setMdmLevel("Global");
			result = service.retrieveDeviceList(contract);

			assertNotNull("Assets was null", result.getAssets());
			assertTrue(result.getAssets().size() > 0);
			logAssetList(result);
			assertTrue(result.getAssets().get(0).getAssetId() != null);
			assertTrue(!result.getAssets().get(0).getAssetId().isEmpty());

		} catch (Exception e) {
			logger.error("testRetrieveDeviceListByCHLNodeId caught exception", e);
			throw new RuntimeException(e);
		} finally {
			if (handle != null) {
				Session session = ((AmindCrmSessionHandle) handle).getSession();
				if (session != null) {
					session.release();
				}
			}
		}
	}

	@Test
	public void testUpdateUserFavoriteAsset() {
		// input params

		String contactId = "1-P3YKE";
		Boolean favoriteFlag = true;
		String favoriteAssetId = "1-GP19WL";

		// test both creation and deletion
		updateUserFavoriteAsset(contactId, favoriteFlag, favoriteAssetId);
		// updateUserFavoriteAsset(contactId, !favoriteFlag, favoriteAssetId);
	}

	private void updateUserFavoriteAsset(String contactId, boolean favoriteFlag, String favoriteAssetId) {
		CrmSessionHandle handle = null;
		StatelessSessionFactory sessionFactory = TestSessionFactories.newStatelessSessionFactory();

		try {
			DeviceService service = new AmindContractedDeviceService();
			((AmindContractedDeviceService) service).setSessionFactory(sessionFactory);
			UserFavoriteAssetContract contract = new UserFavoriteAssetContract();
			contract.setSessionHandle(handle);
			contract.setContactId(contactId);
			contract.setFavoriteFlag(favoriteFlag);
			contract.setFavoriteAssetId(favoriteAssetId);

			FavoriteResult result = service.updateUserFavoriteAsset(contract);
			assertTrue(result.isResult());
		} catch (Exception e) {
			logger.error("testUpdateUserFavoriteAsset caught exception", e);
			throw new RuntimeException(e);
		} finally {
			sessionFactory.releaseAllStatelessSessions();
		}
	}

	/**
	 * VL 09/17/2010: Added in order to test both retrieveDeviceList and
	 * updateUserFavoriteAsset Creates 2 favorites, tries to retrieve them,
	 * deletes favorites, retrieves them.
	 * 
	 * Not yet working, seems to be loosing session when calling service second
	 * time or smthng like that.
	 */
	@Test
	public void testRetrieveDeviceListUsingContactId() {
		String contactId = "1-EFHIGS";
		String favoriteAsset1 = "1-Q7X-542";
		String favoriteAsset2 = "1-4IXL5";
		int startIndex = 0;
		int numRecords = 100;

		CrmSessionHandle handle = null;
		try {

			AmindGlobalService globalService = new AmindGlobalService();
			globalService.setStatefulSessionFactory(TestSessionFactories.newStatefulSessionFactory());
			AmindContractedDeviceService service = new AmindContractedDeviceService();
			service.setSessionFactory(TestSessionFactories.newStatelessSessionFactory());

			AssetListContract assetConract = new AssetListContract();
			assetConract.setSessionHandle(globalService.initCrmSessionHandle(null));
			assetConract.setContactId(contactId);
			assetConract.setStartRecordNumber(startIndex);
			assetConract.setIncrement(numRecords);
			assetConract.setNewQueryIndicator(true);

			AssetListResult result = null;

			// creating 2 favorites
			UserFavoriteAssetContract contract = new UserFavoriteAssetContract();
			contract.setContactId(contactId);
			contract.setSessionHandle(globalService.initCrmSessionHandle(null));
			contract.setFavoriteFlag(true);
			contract.setFavoriteAssetId(favoriteAsset1);
			service.updateUserFavoriteAsset(contract);
			logger.debug("Created 1st fav");
			contract.setFavoriteAssetId(favoriteAsset2);
			service.updateUserFavoriteAsset(contract);
			logger.debug("Created 2nd fav");

			// retrieve
			result = service.retrieveDeviceList(assetConract);
			logger.debug("Found " + result.getTotalCount() + " records");

			// delete 2 favorites
			contract.setFavoriteFlag(false);
			service.updateUserFavoriteAsset(contract);
			logger.debug("Deleted 2nd fav");
			contract.setFavoriteAssetId(favoriteAsset1);
			service.updateUserFavoriteAsset(contract);
			logger.debug("Deleted 1st fav");

			// retrieve
			result = service.retrieveDeviceList(assetConract);
			assertEquals(0, result.getTotalCount());
			logger.debug("Found " + result.getTotalCount() + " records");
		} catch (Exception e) {
			logger.error("testRetrieveDeviceListUsingContactId failed", e);
			throw new SiebelCrmServiceException(e);
		} finally {
			if (handle != null) {
				Session session = ((AmindCrmSessionHandle) handle).getSession();
				if (session != null) {
					session.release();
				}
			}
		}
	}

	/**
	 * Tests retrieveDeviceList using specified chlNodeId in searchCriteria of
	 * contract
	 */
	@Test
	public void testRetrieveDeviceListUsingSearchCriteriaCHLNodeId() {
		String chlId = "1-219TUC4";
		String mdmId = "023058159";
		String level = "Global";
		int startIndex = 0;
		int numRecords = 10;

		AmindGlobalService globalService = new AmindGlobalService();
		globalService.setStatefulSessionFactory(TestSessionFactories.newStatelessSessionFactory());
		CrmSessionHandle handle = globalService.initCrmSessionHandle(null);

		AssetListResult result = null;

		try {
			DeviceService service = new AmindContractedDeviceService();

			AssetListContract contract = new AssetListContract();
			contract.setSessionHandle(handle);

			contract.setStartRecordNumber(startIndex);
			contract.setIncrement(numRecords);
			contract.setNewQueryIndicator(true);
			contract.setMdmId(mdmId);
			contract.setMdmLevel(level);
			contract.setContactId("1-26P3OIX");
			contract.setChlNodeId(chlId);

			Map<String, Object> filterCriteria = new HashMap<String, Object>();
			//filterCriteria.put("installAddress.country", "Canada");
			//contract.setFilterCriteria(filterCriteria);
			contract.setFavoriteFlag(false);
			result = service.retrieveDeviceList(contract);

			assertTrue(result.getAssets().size() > 0);

			logAssetList(result);
		} catch (Exception e) {
			logger.error("testRetrieveDeviceListByCHLNodeId caught exception", e);
			throw new RuntimeException(e);
		} finally {
			if (handle != null) {
				Session session = ((AmindCrmSessionHandle) handle).getSession();
				if (session != null) {
					session.release();
				}
			}
		}
	}
}
