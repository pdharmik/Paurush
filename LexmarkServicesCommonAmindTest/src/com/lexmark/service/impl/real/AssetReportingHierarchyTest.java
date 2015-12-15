package com.lexmark.service.impl.real;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import util.TestSessionFactories;

import com.amind.session.StatelessSessionFactory;
import com.lexmark.contract.LocationReportingHierarchyContract;
import com.lexmark.domain.CHLNode;
import com.lexmark.result.AssetReportingHierarchyResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.DeviceService;
import com.lexmark.service.api.SiebelCrmServiceException;

public class AssetReportingHierarchyTest {
	
	protected static final Log logger = LogFactory.getLog(AssetReportingHierarchyTest.class);
	
	@Test
	public void testRetrieveAssetsPagination() {

		String mdmId = "12669";
		String mdmLevel = "Legal";
	//	String contactId = "1-1LUIPWR";
		String chlNodeId = "";//1-6Q0EJR//"1-7YYU73";
		
		StatelessSessionFactory sessionFactory = TestSessionFactories.newStatelessSessionFactory();
		CrmSessionHandle handle = null;
		
		DeviceService service = new AmindContractedDeviceService();
		((AmindContractedDeviceService)service).setSessionFactory(sessionFactory);
		LocationReportingHierarchyContract assetConract = new LocationReportingHierarchyContract();
		assetConract.setSessionHandle(handle);
		assetConract.setMdmId(mdmId);
		assetConract.setMdmLevel(mdmLevel);
		assetConract.setChlNodeId(chlNodeId);
		AssetReportingHierarchyResult assetReport = new AssetReportingHierarchyResult();
		try 
		{
				long endTime = 0L;
				long startTime = System.currentTimeMillis();
				int iterationCount = 0;
				logger.info("Search criteria: mdm Id is '" + mdmId + "' mdm level is '" + mdmLevel+"'.");
				startTime = System.currentTimeMillis();
				assetReport = service.retrieveAssetReportingHierarchy(assetConract);
				endTime = System.currentTimeMillis();	
				logger.info("\nRetrieveAssetReportList() -- " + "iteration "+iterationCount +" " + "elapsed Time(ms): " + (endTime - startTime));

			if(assetReport != null && assetReport.getChlNodeList() != null)
			{
				logger.info("We're done, found total of " + assetReport.getChlNodeList().size() + " records.");
				Assert.assertTrue(assetReport.getChlNodeList().size() > 0);
				for(CHLNode chlNode : assetReport.getChlNodeList())
				{
					logger.debug("Id" + chlNode.getCHLNodeId());
					logger.debug("Name" + chlNode.getChlNodeName());
					logger.debug("Parent Id" + chlNode.getChlParentId());
				}
			}

		}
		catch(Exception e) 
		{
			logger.error("Test case failed", e);
			throw new SiebelCrmServiceException(e);
		}
		finally 
		{
			sessionFactory.releaseAllStatelessSessions();
		}	
		
	}

}


