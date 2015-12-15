package com.lexmark.service.impl.real;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import util.TestSessionFactories;

import com.amind.common.service.SiebelBusinessServiceProxy;
import com.amind.session.Session;
import com.amind.session.SessionFactory;
import com.lexmark.contract.AssetListContract;
import com.lexmark.result.AssetListResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.DeviceService;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.siebel.data.SiebelPropertySet;

public class ContractedAccountAssetTest {
	
	protected static final Log logger = 
		LogFactory.getLog(ContractedAccountAssetTest.class);
	@Test
	public void testRecordCount() throws Exception {
		SessionFactory sessionFactory =  TestSessionFactories.newStatefulSessionFactory();
		Session session = null;
		try {
			session = sessionFactory.attachSession();
//			String searchExpression = "EXISTS ([LXK SW Agreement Account Global DUNS] = '807049655') AND ([Operating Status]= 'New' OR [Operating Status]= 'Created' OR [Operating Status] = 'Active')";
//			String searchExpression = "EXISTS ([LXK SW Agreement Account Global DUNS] = '463355078') AND ([Operating Status]= 'New' OR [Operating Status]= 'Created' OR [Operating Status] = 'Active')";
			String searchExpression = "EXISTS ([LXK SW Agreement Account LEGAL MDM ID] = '139322') AND ([Operating Status]= 'New' OR [Operating Status]= 'Created' OR [Operating Status] = 'Active')";
			SiebelPropertySet input = new SiebelPropertySet();
			input.setProperty("Business Component", "LXK SW Contracted Asset - Service Web");
			input.setProperty("Business Object", "LXK SW Contracted Asset - Service Web");
			input.setProperty("Search Expression", searchExpression);
			SiebelBusinessServiceProxy businessServiceProxy = session.getSiebelBusServiceProxy();
			SiebelPropertySet output = businessServiceProxy.InvokeMethod("LXK Service Web Utilities", "GetTotalCount", input);
			logger.debug(output.toString());
		}
		catch (Exception e) {
			logger.error("testRecordCount failed", e);
			throw e;
		}
		finally {
			if (session != null) {
				session.release();
			}
		}
	}
	
	@Test
	public void testEaiUiDataAdapter() throws Exception {
		SiebelPropertySet input = new SiebelPropertySet();
		input.setProperty("PageSize", "20");
		input.setProperty("StartRowNum", "0");
		input.setProperty("ExecutionMode", "BiDirectional");
		input.setProperty("LOVLanguageMode", "LDC");
		input.setProperty("ViewMode", "All");
		input.setProperty("NewQuery", "true");
		
		SiebelPropertySet message = new SiebelPropertySet();
		message.setType("SiebelMessage");
		message.setProperty("IntObjectFormat", "Siebel Hierarchical");
		message.setProperty("MessageId", "");
		message.setProperty("IntObjectName", "LXK SW Contracted Asset - Service Web");
		message.setProperty("MessageType", "Integration Object");
		input.addChild(message);
		
		SiebelPropertySet listOf = new SiebelPropertySet();
		listOf.setType("ListOfLXK SW Contracted Asset - Service Web");
		listOf.setProperty("pagesize", "10");
		listOf.setProperty("startrownum", "0");
		listOf.setProperty("recordcountneeded", "true");
		message.addChild(listOf);
		
		SiebelPropertySet assetps = new SiebelPropertySet();
		assetps.setType("LXK SW Contracted Asset - Service Web");
		assetps.setProperty("searchspec", "EXISTS ([LXK SW Agreement Account Global DUNS] = '807049655') AND ([OperatingStatus]= 'New' OR [OperatingStatus]= 'Created' OR [OperatingStatus] = 'Active')");
//		assetps.setProperty("searchspec", "EXISTS ([LXK SW Agreement Account LEGAL MDM ID] = '139322') AND ([OperatingStatus]= 'New' OR [OperatingStatus]= 'Created' OR [OperatingStatus] = 'Active')");
		listOf.addChild(assetps);

		SessionFactory sessionFactory = TestSessionFactories.newStatefulSessionFactory();
		Session session = null;
		try {
			logger.debug(input);
			session = sessionFactory.attachSession();
			SiebelBusinessServiceProxy businessServiceProxy = session.getSiebelBusServiceProxy();
			SiebelPropertySet output = businessServiceProxy.InvokeMethod("EAI UI Data Adapter", "QueryPage", input);
			logger.debug(output.toString());
		}
		catch (Exception e) {
			logger.error("testRecordCount failed", e);
			throw e;
		}
		finally {
			if (session != null) {
				session.release();
			}
		}
		
		
//		> Type = 
//		> PageSize = 20
//		> StartRowNum = 0
//		> ExecutionMode = BiDirectional
//		> ViewMode = All
//		> NewQuery = true
//		- > Child property set #1 at level 1:
//		- > Value = 
//		- > Type = SiebelMessage
//		- > IntObjectFormat = Siebel Hierarchical
//		- > MessageId = 
//		- > IntObjectName = LXK SW Contracted Asset - Service Web
//		- > MessageType = Integration Object
//		- - > Child property set #1 at level 2:
//		- - > Value = 
//		- - > Type = ListOfLXK SW Contracted Asset - Service Web
//		- - - > Child property set #1 at level 3:
//		- - - > Value = 
//		- - - > Type = LXK SW Contracted Asset - Service Web
//		- - - > searchspec = EXISTS ([LXK SW Agreement Account Global DUNS] = '807049655') AND ([Operating Status]= 'New' OR [Operating Status]= 'Created' OR [Operating Status] = 'Active')
//		>

	}
	
	@Test
	public void testRetrieveAssetsPagination() {
//		testRetrieveAssetsPaginationInternal("236295", "Legal","1-XPAPFF","1-ORMLI9");
//		testRetrieveAssetsPaginationInternal("139322", "Legal","1-XPAPFF","");
//		testRetrieveAssetsPaginationInternal("009256819", "Global","1-XPAPFF",""); 
//		testRetrieveAssetsPaginationInternal("807049655", "Global","1-XPAPFF","");
		testRetrieveAssetsPaginationInternal("001338912", "Global","1-1LUIPWR","");
	

}
	
	protected void testRetrieveAssetsPaginationInternal(String contractMdmId, String contractMdmLevel, String contractContactId, String contractChlNodeId) {
		String mdmId = contractMdmId;
		String mdmLevel = contractMdmLevel;
		String contactId =contractContactId;// "1-1LUIPWR";
		String chlNodeId = contractChlNodeId;// null; //"1-QDH9FM";

		int startIndex = 0;
		int numRecords = 20;
		//domestic duns 234567890
		AmindGlobalService globalService = new AmindGlobalService();
		globalService.setStatefulSessionFactory(TestSessionFactories.newStatefulSessionFactory());
		DeviceService service = new AmindContractedDeviceService();
		CrmSessionHandle handle = globalService.initCrmSessionHandle(null);
		
		AssetListContract assetConract = new AssetListContract();
		assetConract.setSessionHandle(handle);
		assetConract.setMdmId(mdmId);
		assetConract.setMdmLevel(mdmLevel);
		assetConract.setStartRecordNumber(startIndex);
		assetConract.setIncrement(numRecords);
		assetConract.setNewQueryIndicator(true);
		assetConract.setContactId(contactId);
		assetConract.setFavoriteFlag(false);
		assetConract.setChlNodeId(chlNodeId);
		Map<String,Object> filterCriteria = new HashMap<String,Object>();
		filterCriteria.put("serialNumber", "0850633");
		assetConract.setSearchCriteria(filterCriteria);
		AssetListResult result = null;
		try {
				long endTime = 0L;
				long startTime = System.currentTimeMillis();
				int iterationCount = 0;
				logger.info("Search criteria: mdm Id is '" + mdmId + "' mdm level is '" + mdmLevel+"'.");
				startTime = System.currentTimeMillis();
				result = service.retrieveDeviceList(assetConract);
				endTime = System.currentTimeMillis();
					
				logger.info
					("\nRetrieveDeviceList() -- " +
							"iteration "+iterationCount +" " +
									"elapsed Time(ms): " + 
									(endTime - startTime));
				logAssetList(result);
				
				if (result == null || 
						result.getAssets() == null || 
						result.getAssets().size() == 0){
					logger.info("No records found for mdmId: " + mdmId + " and mdmLevel: " + mdmLevel);
					
				}
				else {
					logger.info("We're done, found total of " + result.getTotalCount() + " records.");
					logger.info("Page contains records " + startIndex + " to " + startIndex + result.getAssets().size());
				}

			
		}
		

		catch(Exception e) {
			logger.error("Test case failed", e);
			throw new SiebelCrmServiceException(e);
		}
		finally {
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
		logger.info("Loggin results");
		List<com.lexmark.domain.Asset> assetList = result.getAssets();
			
		if(assetList != null && assetList.size() > 0)
		{

			logger.info("Found Asstes count = " + result.getTotalCount());
			for (com.lexmark.domain.Asset asset: assetList) {
				logger.info("serial number:" + asset.getSerialNumber());
				logger.info("Product TLI: " + asset.getProductTLI());
				logger.info("Address Name:" + asset.getInstallAddress().getAddressName());
				logger.info("Asset ID:" + asset.getAssetId());
				logger.info("Mac Address:" + asset.getMacAddress());
				logger.info("favorite Flag:" + asset.getUserFavoriteFlag());
				logger.info("Favorite:" + asset.getUserFavoriteFlag());
				logger.info("Machine Model:" + asset.getModelNumber());
				logger.info("Asset tag" + asset.getAssetTag());
				}
		}
		else
			logger.info("Assets not found");
	}
}


