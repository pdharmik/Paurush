package com.lexmark.service.impl.real;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import util.TestSessionFactories;

import com.amind.session.StatelessSessionFactory;
import com.lexmark.contract.AssetContract;
import com.lexmark.contract.GlobalAssetListContract;
import com.lexmark.contract.LocationReportingHierarchyContract;
import com.lexmark.domain.Asset;
import com.lexmark.result.AssetListResult;
import com.lexmark.result.AssetReportingHierarchyResult;
import com.lexmark.result.AssetResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.DeviceService;

public class AmindDeviceServiceDetailTest 
{
	protected static final Log logger = LogFactory.getLog(AmindDeviceServiceDetailTest.class);
	
	@Test
	public void testRetrieveDeviceDetail() throws Exception
	{
		//input params
		String assetId = "1-4TCI-5634";
		String contactId = "1-13LB1RX";
			
		StatelessSessionFactory sessionFactory = TestSessionFactories.newStatelessSessionFactory();
		CrmSessionHandle handle = null;
		
		try
		{
			DeviceService service = new AmindContractedDeviceService();
			((AmindContractedDeviceService)service).setSessionFactory(sessionFactory);
			AssetContract contract = new AssetContract();
			contract.setAssetId(assetId);
			contract.setContactId(contactId);
			contract.setSessionHandle(handle);
			
			AssetResult result = service.retrieveDeviceDetail(contract);
			
			Asset asset = result.getAsset();
			assertNotNull(asset);
			logger.info("asset="+asset.getAssetId() + ", Name:"+asset.getDeviceName() + ", AssetTag:"+ asset.getAssetTag());
			logger.info("Asset address="+ asset.getInstallAddress().getAddressLine1() + " " 
					+ asset.getInstallAddress().getCity()+ ", " + asset.getInstallAddress().getState() + " " + asset.getInstallAddress().getPostalCode());
			logger.info("Asset contact="+ asset.getAssetContact().getFirstName() + " " + asset.getAssetContact().getLastName() + " " + asset.getAssetContact().getEmailAddress());
			logger.info("Contact Id=" + asset.getAssetContact().getContactId());
			logger.info("favoriteFlag=" + asset.getUserFavoriteFlag());
			logger.debug("Asset Tag=" + asset.getAssetTag());
			logger.debug("Last LTPC Read="+ asset.getLastPageCount());
			logger.debug("Last Color Read="+ asset.getLastColorPageCount());
			logger.debug("Last Read Date="+ asset.getLastPageReadDate());
			logger.debug("Last Color Read Date="+ asset.getLastColorPageReadDate());
			logger.debug("Meter Read Date="+ asset.getMeterReadDate());
		}
		catch (Exception e) {
			logger.error("testRetrieveDeviceDetail caught exception", e);
			throw e;
		}
		finally 
		{
			sessionFactory.releaseAllStatelessSessions();
		}	
	}
	
	@Test
	public void testRetrieveGlobalAssetList()
	{
		//input params
		String serialNumber = "0003111";
		StatelessSessionFactory sessionFactory = TestSessionFactories.newStatelessSessionFactory();
		CrmSessionHandle handle = null;
		
		try
		{
			DeviceService service = new AmindContractedDeviceService();
			((AmindContractedDeviceService)service).setSessionFactory(sessionFactory);
			GlobalAssetListContract contract = new GlobalAssetListContract();
			contract.setSerialNumber(serialNumber);
			contract.setSessionHandle(handle);
			
			AssetListResult result = service.retrieveGlobalAssetList(contract);
			
			assertTrue(result.getAssets().size() > 0);
			assertEquals(result.getAssets().size(), result.getTotalCount());
			
		}
		catch (Exception e)
		{
			logger.error("testRetrieveGlobalAssetList caught exception", e);
			throw new RuntimeException(e);
		}
		finally 
		{
			sessionFactory.releaseAllStatelessSessions();
		}	

	}
	
	@Test
	public void retrieveAssetReportingHierarchy()
	{
		//1-1212XK
		String chlNodeId = "1-157IXP";
		
		StatelessSessionFactory sessionFactory = TestSessionFactories.newStatelessSessionFactory();
		CrmSessionHandle handle = null;
		
		try {

			DeviceService service = new AmindContractedDeviceService();
			((AmindContractedDeviceService)service).setSessionFactory(sessionFactory);
			LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
			contract.setSessionHandle(handle);
			contract.setChlNodeId(chlNodeId);
			
			AssetReportingHierarchyResult result = service.retrieveAssetReportingHierarchy(contract);
			
			assertNotNull(result);
			assertTrue(result.getChlNodeList().size() > 0);
		}
		catch (Exception e)
		{
			logger.error("retrieveAssetReportingHierarchy caught exception", e);
			throw new RuntimeException(e);
		}
		finally 
		{
			sessionFactory.releaseAllStatelessSessions();
		}	
	}
	
	@Test
	public void retrieveAssetReportingHierarchyByMdm()
	{
		String mdmId = "1-157IXP";
		String mdmLevel = "Siebel";
		
		StatelessSessionFactory sessionFactory = TestSessionFactories.newStatelessSessionFactory();
		CrmSessionHandle handle = null;
		
		try {

			DeviceService service = new AmindContractedDeviceService();
			((AmindContractedDeviceService)service).setSessionFactory(sessionFactory);
			LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
			contract.setSessionHandle(handle);
			contract.setMdmId(mdmId);
			contract.setMdmLevel(mdmLevel);
			
			AssetReportingHierarchyResult result = service.retrieveAssetReportingHierarchy(contract);
			
			assertNotNull(result);
			assertTrue(result.getChlNodeList().size() > 0);
		}
		catch (Exception e) {
			logger.error("retrieveAssetReportingHierarchyByMdm caught exception", e);
			throw new RuntimeException(e);
		}
		finally 
		{
			sessionFactory.releaseAllStatelessSessions();
		}	
	}
}
