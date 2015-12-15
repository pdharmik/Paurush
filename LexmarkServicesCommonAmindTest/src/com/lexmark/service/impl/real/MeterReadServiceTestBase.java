package com.lexmark.service.impl.real;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import util.TestSessionFactories;

import com.amind.session.Session;
import com.lexmark.domain.Asset;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.MeterReadService;

public abstract class MeterReadServiceTestBase {

	protected static final Log logger = LogFactory.getLog(MeterReadServiceTestBase.class);
	protected static CrmSessionHandle handle;
	protected static AmindContractedMeterReadService service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		logger.debug("globalService init");
		AmindGlobalService globalService = new AmindGlobalService();
		globalService.setStatefulSessionFactory(TestSessionFactories.newStatefulSessionFactory());
		logger.debug("handle init");
		handle = globalService.initCrmSessionHandle(null);
		logger.debug("MeterReadService init");
		service = new AmindContractedMeterReadService();

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		logger.debug("releasing the handle");
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

	protected void logAssetList(List<Asset> assets) {
		for (Asset asset : assets) {
			logger.debug("Asset Id" + asset.getAssetId());
			logger.debug("Serial Number" + asset.getSerialNumber());
			logger.debug("Favorite Flag" + asset.getUserFavoriteFlag());
			logger.debug("Page Color Reading Date" + asset.getLastPageReadDate());
			logger.debug("Page Color Count" + asset.getLastColorPageCount());
			logger.debug("Page Count" + asset.getLastPageCount());
			logger.debug("Page Date" + asset.getLastPageReadDate());
			logger.debug("Model Number" + asset.getModelNumber());
			logger.debug("Product TLI" + asset.getProductTLI());
		}
	}

}
