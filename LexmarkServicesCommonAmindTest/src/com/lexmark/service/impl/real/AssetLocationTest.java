package com.lexmark.service.impl.real;

import static com.lexmark.service.impl.real.util.DoFileUtil.checkDoFile;
import static com.lexmark.service.impl.real.util.DoFileUtil.xmltag;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;

import util.TestSessionFactories;

import com.amind.session.StatelessSessionFactory;
import com.lexmark.contract.LocationReportingHierarchyContract;
import com.lexmark.result.AssetLocationsResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.DeviceService;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.domain.AssetLocation;
import com.lexmark.service.impl.real.util.DoFileUtil.FieldEntry;

public class AssetLocationTest {
	
	protected static final Log logger = LogFactory.getLog(AssetLocationTest.class);
    
    FieldEntry[] fieldEntries;

    @Before
    public void setUp() throws Exception {
        fieldEntries = new FieldEntry[] {
                xmltag("id", "Id"),

                xmltag("", "LXK MPS Vendor Account Domestic DUNS Number"),
                xmltag("", "LXK MPS Vendor Account Global DUNS Number"),
                xmltag("", "LXK MPS Vendor Account Id"),
                xmltag("", "LXK MPS Vendor Account Legal Entity Id"),
                xmltag("", "LXK MPS Vendor Account Level"),
                xmltag("", "LXK MPS Vendor Account MDM #"),
                xmltag("", ""),
        };
    }

    @Test
    public void testCheckFields() throws Exception {
        checkDoFile("../LexmarkServicesCommon/src/DoMappings/do-contractedassetlocation-mapping.xml");
    }

    @Test
    public void genXmlMappings() throws Exception {
        for (FieldEntry fe : fieldEntries) {
            System.out.println(fe.toXmlTag());
        }
    }
    
    @Test
    public void genJavaFileds() throws Exception {
        for (FieldEntry fe : fieldEntries) {
            System.out.println(fe.toJavaFieldDeclaration());
        }
    }

    @Test
    public void query1() throws Exception {
        MiscTest.sampleSiebelQuery(AssetLocation.class, 5, 0);
    }
    
	
	@Test
	public void testRetrieveAssetsLocation() {
		String mdmId = "807049655";
		String mdmLevel = "Global";
		String chlNodeId = "";
		
		StatelessSessionFactory sessionFactory = TestSessionFactories.newStatelessSessionFactory();
		CrmSessionHandle handle = null;
		
		DeviceService service = new AmindContractedDeviceService();
		((AmindContractedDeviceService)service).setSessionFactory(sessionFactory);
		LocationReportingHierarchyContract assetConract = new LocationReportingHierarchyContract();
		assetConract.setSessionHandle(handle);
		assetConract.setMdmId(mdmId);
		assetConract.setMdmLevel(mdmLevel);
		assetConract.setChlNodeId(chlNodeId);
		AssetLocationsResult assetLocation = new AssetLocationsResult();
		try 
		{
				long endTime = 0L;
				long startTime = System.currentTimeMillis();
				int iterationCount = 0;
				logger.info("Search criteria: mdm Id is '" + mdmId + "' mdm level is '" + mdmLevel+"'.");
				startTime = System.currentTimeMillis();
				assetLocation = service.retrieveAssetLocations(assetConract);
				endTime = System.currentTimeMillis();	
				logger.info("\nRetrieveServiceReuqestList() -- " + "iteration "+iterationCount +" " + "elapsed Time(ms): " + (endTime - startTime));

			if(assetLocation != null && assetLocation.getAddressList() != null && assetLocation.getAddressList().size() > 0)
			{
				logger.info("We're done, found total of " + assetLocation.getAddressList().size() + " records.");
			}
			else {
				logger.info("No Results");
				assertNotNull(assetLocation);
				assertNotNull(assetLocation.getAddressList());
				assertTrue(assetLocation.getAddressList().size() > 0);
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
	
	@Test
	public void testRetrieveAssetsPaginationUsingChlNodeId() {

		String mdmId = "807049655";
		String mdmLevel = "Global";
		String chlNodeId = "";
		
		StatelessSessionFactory sessionFactory = TestSessionFactories.newStatelessSessionFactory();
		CrmSessionHandle handle = null;
		DeviceService service = new AmindContractedDeviceService();
		((AmindContractedDeviceService)service).setSessionFactory(sessionFactory);
		LocationReportingHierarchyContract assetConract = new LocationReportingHierarchyContract();
		assetConract.setSessionHandle(handle);
		assetConract.setMdmId(mdmId);
		assetConract.setMdmLevel(mdmLevel);
		assetConract.setChlNodeId(chlNodeId);
		AssetLocationsResult assetLocation = new AssetLocationsResult();
		try 
		{
			long endTime = 0L;
			long startTime = System.currentTimeMillis();
			int iterationCount = 0;
			logger.info("Search criteria: mdm Id is '" + mdmId + "' mdm level is '" + mdmLevel+"'.");
			startTime = System.currentTimeMillis();
			assetLocation = service.retrieveAssetLocations(assetConract);
			
			logger.info
			("\nRetrieveServiceReuqestList() -- " +
					"iteration "+iterationCount +" " +
					"elapsed Time(ms): " + 
					(endTime - startTime));
			
			logger.info("We're done, found total of " + assetLocation.getAddressList().size() + " records.");
			assertTrue(assetLocation.getAddressList().size() > 0);
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
	
	@Test
    public void queryAssetLocation() throws Exception {
	    MiscTest.sampleSiebelQuery(AssetLocation.class, 
	            "EXISTS([chlNodeId] <> '')"
	            , 5);
    }
    
}


