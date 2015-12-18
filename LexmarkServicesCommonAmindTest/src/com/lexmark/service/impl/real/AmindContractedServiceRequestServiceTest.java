package com.lexmark.service.impl.real;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.AddressListContract;
import com.lexmark.contract.FavoriteAddressContract;
import com.lexmark.contract.ManualAssetContract;
import com.lexmark.contract.ServiceRequestAssociatedListContract;
import com.lexmark.contract.ServiceRequestHistoryListContract;
import com.lexmark.contract.ServiceRequestListContract;
import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.Agreement;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.result.AddressListResult;
import com.lexmark.result.FavoriteResult;
import com.lexmark.result.ManualAssetResult;
import com.lexmark.result.ServiceRequestListResult;
import com.lexmark.result.SiebelAccountListResult;
import com.lexmark.service.impl.real.domain.AccountByVendorIdDo;
import com.lexmark.service.impl.real.domain.AccountDo;
import com.lexmark.service.impl.real.domain.LBSAddress;
import com.lexmark.util.LangUtil;


/**
 * @see com.lexmark.service.impl.real.AmindContractedServiceRequestServiceTestSuite
 *
 * @author vpetruchok
 * @version 1.0, 2012-07-10
 */
public class AmindContractedServiceRequestServiceTest extends AmindServiceTest {

	AmindContractedServiceRequestService service;
	SiebelAccountListContract contract;

	@Before
	public void setUp() throws Exception {
		com.lexmark.service.impl.real.AmindServiceTest.setUp_AmindServerTest_static();
		service = new AmindContractedServiceRequestService();
		service.setSessionFactory(statelessSessionFactory);
		contract = new SiebelAccountListContract();
	}

	@After
	public void tearDown() throws Exception {
		com.lexmark.service.impl.real.AmindServiceTest.tearDown_AmindServerTest_static();
	}

	@Test
	public void testRetrieveSiebelAccountList() throws Exception {
		contract.setMdmId("1-8AOEMQ");
		contract.setMdmLevel(LexmarkConstants.MDM_LEVEL_SIEBEL);
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		print(result);
	}
	
	@Test
	public void testRetrieveSiebelAccountList_MPS_QA() throws Exception {
		contract.setMdmId("1-4LLYQJK");
		contract.setMdmLevel("Siebel");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(true);
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		print(result);
	}

	@Test
	public void testRetrieveSiebelAccountList_withPartnerFlag() throws Exception {
		contract.setMdmId("123477789");
		contract.setMdmLevel(LexmarkConstants.MDM_LEVEL_GLOBAL);
		contract.setVendorFlag(true);
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		print(result);
	}

/*	@Test
	public void testRetrieveSiebelAccountList_Defect4166_query() throws Exception {
		long t0 = System.currentTimeMillis();
		try {
			List<AccountDo> r = MiscTest.querySiebel(AccountDo.class,
                    "[mdmLevel3AccountId] <> '' AND [LXK SW MDM Account Level] ='Siebel'"
//					"[mdmLevel3AccountId] = '61630' AND [LXK SW MDM Account Level] ='Siebel'" //with data
//                    "[agreementId] = '1-17EKMY0'"
					, 10, 0);
			System.out.printf("[t1] Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
			for (AccountDo acc : LangUtil.notNull(r)) {
				System.out.println("tmdmLevel3AccountId = " + acc.getMdmLevel3AccountId());
				MiscTest.print(acc.getAccountServiceAgreements(), "id",
						"assetEntitlementFlag", "expediteOrderCount", "catalogEntitlementFlag", "catalogExpediteOrderCount");
			}
		} finally {
			System.out.printf("[t2] Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
		}
	}*/

	/**
	 *
	 * @see #testRetrieveSiebelAccountList_QA_Defect4166_withVendorFlag()
	 */
	@Test
	public void testRetrieveSiebelAccountList_Defect4166_byVendor_query() throws Exception {
		long t0 = System.currentTimeMillis();
		try {
			List<AccountByVendorIdDo> r = MiscTest.querySiebel(AccountByVendorIdDo.class,
					   "[mdmLevel3AccountId] <> '' AND [LXK MPS Partner Flag] = 'Y'"
//			        ""
//                    "[assetEntitlementFlag] <> '' OR [expediteOrderFlag] <> '' OR [catalogEntitlementFlag] <> ''"
//			           [assetEntitlementFlag] <> ''
//				      "[catalogEntitlementFlag] <> ''"
//                    "[assetEntitlementFlag] <> ''"
					, 10, 0);
			System.out.printf("[t1] Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
			MiscTest.print(r, "id", "mdmLevel3AccountId" ,"assetEntitlementFlag", "expediteOrderFlag", "catalogEntitlementFlag", "catalogExpediteFlag");
		} finally {
			System.out.printf("[t2] Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
		}
	}


	@Test
	public void testRetrieveSiebelAccountList_DEV_Defect4166() throws Exception {
		contract.setMdmId("12864"); // with expediteOrderFlag
//        contract.setMdmId("29917");   // without  expediteOrderFlag
		contract.setMdmLevel(LexmarkConstants.MDM_LEVEL_LEGAL);
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		MiscTest.print(result.getAccountList(), "accountId", "agreementId","expediteOrderFlag", "catalogEntitlementFlag");
//        MiscTest.print(result.getAccountList());
	}

	/**
	 *
	 * @see AmindOrderSuppliesAssetServiceTest#testRetrieveDeviceDetail_QA_Defect4166()
	 * @see #testRetrieveSiebelAccountList_Defect4166_query
	 * @see AccountDo
	 */
	@Test
	public void testRetrieveSiebelAccountList_QA_Defect4166() throws Exception {
//        contract.setMdmId("113631225");
//        contract.setMdmLevel(LexmarkConstants.MDM_LEVEL_GLOBAL);
//
//        contract.setMdmId("1003");
//        contract.setMdmLevel(LexmarkConstants.MDM_LEVEL_LEGAL);

//    	  contract.setVendorFlag(true);

		contract.setMdmId("61630");  // accountId=1-17EKMXJ,catalogEntitlementFlag=true,assetEntitlementFlag=true,expediteOrderFlag=true,catalogExpediteFlag=true
//		contract.setMdmId("33228");  
//		contract.setMdmId("75240");  
		contract.setMdmLevel(LexmarkConstants.MDM_LEVEL_LEGAL);
//        contract.setVendorFlag(true);

//		contract.setMdmId("241828169"); 
//		contract.setMdmLevel(LexmarkConstants.MDM_LEVEL_GLOBAL);

		long t0 =  System.currentTimeMillis();
		try {
			SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
			MiscTest.print(result.getAccountList(), "accountId", "accountName", 
					"catalogEntitlementFlag", "assetEntitlementFlag",  "assetExpediteFlag", "catalogExpediteFlag");
		} finally {
			System.out.printf("(total exec time = %.2f sec.)\n", (System.currentTimeMillis() - t0) / 1000.0);
		}
	}
	
	@Test
    public void cyclePerformence() throws Exception {
	    final int N = 1000000;
	    int[] a = new int[N];
	    Arrays.fill(a, -1);
	    a[N-1] = 1;
	    
	    long t0 = System.currentTimeMillis();
        try {
            for (int i = 0; i < N; i++) {
                if (a[i] == 1) {
                    System.out.printf("%d found\n", i);
                }
            }
        } finally {
            System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
        
    }

	/**
	 *
	 * @see AccountByVendorIdDo
	 * @see do-accountbyvendorid-mapping.xml
	 * @see #testRetrieveSiebelAccountList_Defect4166_byVendor_query
	 */
	@Test
	public void testRetrieveSiebelAccountList_QA_Defect4166_withVendorFlag() throws Exception {
		contract.setMdmId("13527");
		contract.setMdmLevel(LexmarkConstants.MDM_LEVEL_LEGAL);
		contract.setVendorFlag(true);
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		MiscTest.print(result.getAccountList(), "assetEntitlementFlag", "catalogEntitlementFlag", "assetExpediteFlag", "catalogExpediteFlag");
	}
	
	@Test
	public void testRetrieveSiebelAccountList_QA_Defect6602() throws Exception {
		contract.setMdmId("10809");
//		contract.setMdmId("5989");
		contract.setMdmLevel(LexmarkConstants.MDM_LEVEL_LEGAL);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(true);
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		MiscTest.print(result.getAccountList(),  "assetExpediteFlag", "catalogExpediteFlag", "assetEntitlementFlag", "catalogEntitlementFlag");
	}
	
    @Test
    public void testRetrieveSiebelAccountList_QA_Defect6615() throws Exception {
        contract.setMdmId("007874480");
//      contract.setMdmId("5989");
        contract.setMdmLevel(LexmarkConstants.MDM_LEVEL_GLOBAL);
        contract.setVendorFlag(false);
        contract.setAgreementFlag(true);
        SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
        MiscTest.print(result.getAccountList(),  "accountId", "assetEntitlementFlag", "catalogEntitlementFlag", "assetExpediteFlag", "catalogExpediteFlag");
    }	
	

	/**
	 * user: mpsone@mpscust.com
	 *
	 * @see do-accountdo-mapping.xml
	 */
	@Test
	public void testRetrieveSiebelAccountList_QA_mpsoneUser() throws Exception {
		contract.setMdmId("61630");
		contract.setMdmLevel(LexmarkConstants.MDM_LEVEL_LEGAL);
		long t0 = System.currentTimeMillis();
		try {
			SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
			MiscTest.print(result.getAccountList());
		} finally {
			System.out.printf("(total exec time = %.2f sec.)\n", (System.currentTimeMillis() - t0) / 1000.0);
		}
	}


	/**
	 *
	 * @see do-accountdo-mapping.xml
	 */
	@Test
	public void testRetrieveSiebelAccountList_QA_logging() throws Exception {
//        contract.setMdmId("61630");
		contract.setMdmLevel(LexmarkConstants.MDM_LEVEL_LEGAL);
		long t0 = System.currentTimeMillis();
		try {
			SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
			MiscTest.print(result.getAccountList());
		} finally {
			System.out.printf("(total exec time = %.2f sec.)\n", (System.currentTimeMillis() - t0) / 1000.0);
		}
	}


	@Test
	public void testRetrieveSiebelAccountList_QA_Defect3660() throws Exception {
		long t0 = System.currentTimeMillis();
        try {
            contract.setMdmId("023058159");
            contract.setMdmLevel(LexmarkConstants.MDM_LEVEL_GLOBAL);
            //        contract.setVendorFlag(true);
            SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
            MiscTest.print(result.getAccountList(), "accountId",
                    "catalogEntitlementFlag", "assetEntitlementFlag", "assetExpediteFlag");
            //        MiscTest.print(result.getAccountList());  
        } finally {
            System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
	}

    @Test
    public void testRetrieveSiebelAccountList_QA_Defect3660_2() throws Exception {
//        contract.setMdmId("61360");
//		contract.setMdmId("61630");
        
//        contract.setMdmId("42261");
//        contract.setMdmLevel(LexmarkConstants.MDM_LEVEL_LEGAL);
        
        
        contract.setMdmId("48828");
        contract.setMdmLevel(LexmarkConstants.MDM_LEVEL_ACCOUNT);
        
        long t0 = System.currentTimeMillis();
        try {
            //        contract.setVendorFlag(true);
            SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
            MiscTest.print(result.getAccountList(), "accountId",
                    "catalogEntitlementFlag", "assetEntitlementFlag", "assetExpediteFlag");
            //        MiscTest.print(result.getAccountList());  
        } finally {
            System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
    }

	/*@Test
	public void  testRetrieveSiebelAccountList_QA_Defect3660_query() throws Exception {
		List<AccountDo> r = MiscTest.sampleSiebelQuery(AccountDo.class,
				"[mdmLevel3AccountId] <> '' AND [LXK SW MDM Account Level] ='Siebel'"
				, 50);
//        List<AccountDo> r = MiscTest.sampleSiebelQuery(AccountDo.class,
//                "[mdmLevel3AccountId] = '61360' AND [LXK SW MDM Account Level] ='Siebel'"
//                , 10);
		for (AccountDo acc : r) {
			int i = 0;
			for (AccountServiceAgreementDo agreement  : LangUtil.notNull(acc.getAccountServiceAgreements()))  {
				System.out.println( i++ + " agreement = " + str(agreement));
//                for (AccountServiceAgreementCatalogDo catalog:  LangUtil.notNull(agreement.getCatalogs())) {
//                    System.out.println("\tcatalog = " + str(catalog));
//                    System.out.println("\t\tmdmLevel3AccountId = " + acc.getMdmLevel3AccountId());
//                }
			}
		}
	}*/

	@Test
	public void testRetrieveSiebelAccountList_DEV_Defect3660() throws Exception {
		contract.setMdmId("12864"); // with catalogEntitlementFlag
//        contract.setMdmId("29917");   // without  catalogEntitlementFlag
		contract.setMdmLevel(LexmarkConstants.MDM_LEVEL_LEGAL);
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		MiscTest.print(result.getAccountList(), "catalogEntitlementFlag");
//        MiscTest.print(result.getAccountList());
	}

	@Test
	public void testRetrieveSiebelAccountList_QA_Defect6362() throws Exception {
		contract.setMdmId("249568056");
		contract.setMdmLevel(LexmarkConstants.MDM_LEVEL_GLOBAL);
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		MiscTest.print(result.getAccountList(), "accountId", "agreementId");
	}

	private void print(SiebelAccountListResult result) {
		if (result == null) {
			System.out.println("<null>");
			return;
		}
		List<Account> accountList = result.getAccountList();
		MiscTest.print(accountList);
	}

	
    @Test
    public void testRetrieveSiebelAccountList_QA_Defect6739() throws Exception {
        long t0 = System.currentTimeMillis();
        try {
            contract.setMdmId("15341");
            contract.setMdmLevel(LexmarkConstants.MDM_LEVEL_LEGAL);
            contract.setNewQueryIndicator(true);
            //        contract.setVendorFlag(true);
            SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
            MiscTest.print(result.getAccountList(), "accountId",
                    "catalogEntitlementFlag", "assetEntitlementFlag", "assetExpediteFlag", "catalogExpediteFlag");
            //        MiscTest.print(result.getAccountList());  
        } finally {
            System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
    }
    
    @Test
    public void testRetrieveSiebelAccountList_MPS_QA_Defect7756() throws Exception {
        long t0 = System.currentTimeMillis();
        try {
            contract.setMdmId("1-YFNVUX");
            contract.setMdmLevel("Siebel");
            contract.setNewQueryIndicator(true);
            contract.setAgreementFlag(true);
            SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
            MiscTest.print(result.getAccountList(), "accountId",
                    "catalogEntitlementFlag", "assetEntitlementFlag", "assetExpediteFlag", "catalogExpediteFlag");
                    MiscTest.print(result.getAccountList());  
        } finally {
            System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
        }
    }
    
	
	@Test
	public void testRetrieveServiceRequestHistoryList_QA_defect885() throws Exception {
		ServiceRequestHistoryListContract hlc = new ServiceRequestHistoryListContract();
		hlc.setNewQueryIndicator(true);
		hlc.setAccountId("1-P7PY8H");
		hlc.setAssetId("1-418G82S");
		hlc.setServiceRequestNumber("  mock-sr-number  ");

		ServiceRequestListResult result = service.retrieveServiceRequestHistoryList(hlc);
		print(result);


	for (ServiceRequest sr :  LangUtil.notNull(result.getServiceRequests())) {
//           System.out.printf("asset=%s\n", str(sr.getAsset()));
		// Request Type and Area
		System.out.printf("requestNumber=%s, area=%s\n", sr.getRequestType(), sr.getArea());
	}
	}

	private void print(ServiceRequestListResult result) {
		if (result == null) {
			System.out.println("result = null");
			return;
		}

	System.out.printf("result.totalCount=%s\n", result.getTotalCount());

	for (ServiceRequest sr :  LangUtil.notNull(result.getServiceRequests())) {
		System.out.printf("serviceRequest=%s\n", str(sr));
	}

	if (LangUtil.isNotEmpty(result.getServiceRequests())) {
		System.out.printf("serviceRequest.size()=%s\n", result.getServiceRequests().size());
	}
	}

//	@Test
//	public void testRetrieveServiceRequest_QA() throws Exception {
//		ServiceRequestContract contract = new ServiceRequestContract();
////        contract.setRequestNumber("1-8853336051");
//		contract.setRequestNumber("1-8857428241");
//		ServiceRequestResult result = service.retrieveServiceRequest(contract);
//		System.out.println(result);
//		System.out.println("result.serviceRequest=" + str(result.getServiceRequest()));
//		System.out.println("result.serviceRequest.asset=" + str(result.getServiceRequest().getAsset()));
//		System.out.println("problemDescription=" + result.getServiceRequest().getProblemDescription());
//		System.out.println("resolutionCode=" + result.getServiceRequest().getResolutionCode());
//		System.out.println("serviceAddress=" + str(result.getServiceRequest().getServiceAddress()));
//	}
//
//	@Test
//	public void testRetrieveServiceRequest_QA_logging() throws Exception {
//		ServiceRequestContract contract = new ServiceRequestContract();
////        contract.setRequestNumber("1-8857428241");
//		ServiceRequestResult result = service.retrieveServiceRequest(contract);
//		System.out.println(result);
//	}
//
//	@Test
//	public void testRetrieveServiceRequest_QA_defect2367() throws Exception {
//		ServiceRequestContract contract = new ServiceRequestContract();
////        contract.setRequestNumber("1-9186755321");
//		contract.setRequestNumber("1-10030001361");
//		ServiceRequestResult result = service.retrieveServiceRequest(contract);
//		ServiceRequest sr = result.getServiceRequest();
//		MiscTest.print("", str(sr));
//		MiscTest.print("primaryContact = ", str(sr.getPrimaryContact()));
//		MiscTest.print("[activitywebUpdateActivities] ", sr.getActivitywebUpdateActivities());
//		MiscTest.print("[servicewebUpdateActivities] ", sr.getServicewebUpdateActivities());
//	}
//
//    @Test
//    public void testRetrieveServiceRequest_QA_20130218() throws Exception {
//        ServiceRequestContract contract = new ServiceRequestContract();
//        contract.setRequestNumber("1-11312286606");
//        long t0 = System.currentTimeMillis();
//        try {
//            ServiceRequestResult result = service.retrieveServiceRequest(contract);
//            ServiceRequest sr = result.getServiceRequest();
//            MiscTest.print("", str(sr));
//            MiscTest.print("attachments=", result.getServiceRequest().getAttachments());
//        } finally {
//            System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
//        }
//    }	
//    
//    @Test
//    public void testRetrieveServiceRequest_Prod_20130226() throws Exception {
//        ServiceRequestContract contract = new ServiceRequestContract();
//        contract.setRequestNumber("1-11150174741");
//        long t0 = System.currentTimeMillis();
//        try {
//            ServiceRequestResult result = service.retrieveServiceRequest(contract);
//            ServiceRequest sr = result.getServiceRequest();
//            MiscTest.print("", str(sr));
//            MiscTest.print("attachments=", result.getServiceRequest().getAttachments());
//        } finally {
//            System.out.printf("Excec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
//        }
//    }       
//	
//
//	/**
//	 * @see com.lexmark.service.impl.real.AmindContractedServiceRequestService#populateRequestTypeFieldMap()
//	 */
//	@Test
//	public void testRetrieveAssociatedServiceRequestList_sorting_QA_defect2145() throws Exception {
//		ServiceRequestAssociatedListContract contract = new ServiceRequestAssociatedListContract();
//		contract.setServiceRequestNumber("1-9077385291");
//		contract.setNewQueryIndicator(true);
//		contract.setStartRecordNumber(0);
//		contract.setIncrement(40);
//
//		String[][] sortFields = {
//				// format: <portalField>, <aMind field xpath>
//
//				{"requestNumber",  "serviceRequestNumber"},
//		};
//
//		TestErrors errors = new TestErrors();
//		for (String[] field:  sortFields) {
//			for (Boolean ascendingOrder : Arrays.asList(/*Boolean.TRUE,*/ Boolean.FALSE)) {
//				SortCriteria sortCriteria = new SortCriteria(field[0], field[1] ,ascendingOrder, true);
//				System.out.printf("***** processing %s\n",  str(field));
//				try {
//					contract.setSortCriteria(sortCriteria.toMap());
//					ServiceRequestListResult r = service.retrieveAssociatedServiceRequestList(contract);
//					checkSortOrder(r.getServiceRequests(), sortCriteria);
//				} catch (Throwable ex) {
//					errors.add(ex, "" + sortCriteria);
//				}
//			}
//		}
//		errors.check();
//	}
//
//	/**
//	 * @see com.lexmark.service.impl.real.AmindContractedServiceRequestService#populateRequestTypeFieldMap()
//	 */
//	@Test
//	public void testRetrieveAssociatedServiceRequestList_QA_defect2145() throws Exception {
//		Map<String, Object> m = new HashMap<String, Object>();
//		m.put("requestNumber", "DESCENDING");
//		ServiceRequestAssociatedListContract contract = new ServiceRequestAssociatedListContract();
//		contract.setServiceRequestNumber("1-9077385291");
//		contract.setSortCriteria(m);
//		contract.setNewQueryIndicator(true);
//		contract.setStartRecordNumber(0);
//		contract.setIncrement(40);
//		ServiceRequestListResult r = service.retrieveAssociatedServiceRequestList(contract);
//		for (ServiceRequest sr : r.getServiceRequests()) {
//			System.out.printf("serviceRequestNumber=%s, \n", sr.getServiceRequestNumber() );
//		}
//		SortUtil.checkSortOrder(r.getServiceRequests(), new SortCriteria("requestNumber", "serviceRequestNumber", false, false));
//	}
//
//	@Test
//	public void testRetrieveServiceRequest_QA_defect2919() throws Exception {
//		ServiceRequestContract contract = new ServiceRequestContract();
////        contract.setRequestNumber("1-9186755321");
//		contract.setRequestNumber("1-9286574143");
//		ServiceRequestResult result = service.retrieveServiceRequest(contract);
//		ServiceRequest sr = result.getServiceRequest();
////        System.out.println(str(sr));
//		MiscTest.print("[returnOrderLines] ", sr.getReturnOrderLines());
//		MiscTest.print("[shipmentOrderLines] ", sr.getShipmentOrderLines());
//	}
//
//	@Test
//	public void testRetrieveServiceRequest_QA_defect4539() throws Exception {
//		ServiceRequestContract contract = new ServiceRequestContract();
//		contract.setRequestNumber("1-9983769061");
//		ServiceRequestResult result = service.retrieveServiceRequest(contract);
//		ServiceRequest sr = result.getServiceRequest();
////        System.out.println(str(sr));
//		MiscTest.print("[returnOrderLines] ", sr.getReturnOrderLines());
//		MiscTest.print("[shipmentOrderLines] ", sr.getShipmentOrderLines());
//		MiscTest.print("[activitywebUpdateActivities] ", sr.getActivitywebUpdateActivities());
//		MiscTest.print("[servicewebUpdateActivities] ", sr.getServicewebUpdateActivities());
//	}
//
//	@Test
//	public void testRetrieveServiceRequest_QA_defect4515() throws Exception {
//		ServiceRequestContract contract = new ServiceRequestContract();
//		contract.setRequestNumber("1-10937884601");
////        contract.setRequestNumber("1-9983769061");
////        contract.setRequestNumber("1-9983769631");
//		ServiceRequestResult result = service.retrieveServiceRequest(contract);
//		ServiceRequest sr = result.getServiceRequest();
//		MiscTest.print("[activitywebUpdateActivities] ", sr.getActivitywebUpdateActivities(), "comment");
//		MiscTest.print("[servicewebUpdateActivities] ", sr.getServicewebUpdateActivities(), "comment");
//		MiscTest.print("[emailActivities] ", sr.getEmailActivities(), "comment");
//	}
//
//	@Test
//	public void testRetrieveServiceRequest_QA_defect6328() throws Exception {
//		ServiceRequestContract contract = new ServiceRequestContract();
//		contract.setRequestNumber("1-10937884601");
//		contract.setVisibilityRole("Customer");
//		ServiceRequestResult result = service.retrieveServiceRequest(contract);
//		ServiceRequest sr = result.getServiceRequest();
//		MiscTest.print("[activitywebUpdateActivities] ", sr.getActivitywebUpdateActivities());
//		MiscTest.print("[servicewebUpdateActivities] ", sr.getServicewebUpdateActivities());
//		MiscTest.print("[emailActivities] ", sr.getEmailActivities(), "activityDescription", "comment");
////        MiscTest.print("[emailActivities] ", sr.getEmailActivities());
//	}
//	
//	/**
//	 * searchspec:[SR Number]='1-11087511811'
//	 */
//	@Test
//	public void testRetrieveServiceRequest_QA_defect6761() throws Exception {
//		ServiceRequestContract contract = new ServiceRequestContract();
//		contract.setRequestNumber("1-11087511811");
//		contract.setVisibilityRole("Customer");
//		ServiceRequestResult result = service.retrieveServiceRequest(contract);
//		ServiceRequest sr = result.getServiceRequest();
//		System.out.println(str(sr));
//		MiscTest.print("[activitywebUpdateActivities] ", sr.getActivitywebUpdateActivities());
//		MiscTest.print("[servicewebUpdateActivities] ", sr.getServicewebUpdateActivities());
//		MiscTest.print("[emailActivities] ", sr.getEmailActivities(), "activityDescription", "comment");
////        MiscTest.print("[emailActivities] ", sr.getEmailActivities());
//	}
//	
//
//	@Test
//	public void testRetrieveServiceRequest_QA_defect3808() throws Exception {
//		ServiceRequestContract contract = new ServiceRequestContract();
//		contract.setRequestNumber("1-10941717431");
//		contract.setVisibilityRole("Customer");
//		ServiceRequestResult result = service.retrieveServiceRequest(contract);
//		ServiceRequest sr = result.getServiceRequest();
//		MiscTest.print("[activitywebUpdateActivities] ", sr.getActivitywebUpdateActivities());
//		MiscTest.print("[servicewebUpdateActivities] ", sr.getServicewebUpdateActivities());
//		MiscTest.print("[emailActivities] ", sr.getEmailActivities(), "activityDescription", "comment");
////        MiscTest.print("[emailActivities] ", sr.getEmailActivities());
//	}

	@Test
	public void testRetrieveAddressList_QA() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setContactId("mock-contactId");
		contract.setMdmId("33228");
		contract.setMdmLevel("Legal");
	
		AddressListResult result = service.retrieveAddressList(contract);
		MiscTest.print(result.getAddressList());
	}


	@Test
	public void testRetrieveAddressList_QA_fav() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setContactId("mock-contactId");
		contract.setMdmId("mock-mdmId");
		contract.setMdmLevel("Global");
		contract.setFavoriteFlag(true);

		AddressListResult result = service.retrieveAddressList(contract);
		MiscTest.print(result.getAddressList());
	}

	@Test
	public void testValidateManualAsset() throws Exception {
		ManualAssetContract contract = new ManualAssetContract();
		contract.setModelNumber("mock-modelNumber");
		contract.setProductTLI("mock-productTLI");

		ManualAssetResult result = service.validateManualAsset(contract);
		System.out.println(result.getResult());
	}


	@Test
	public void testRetrieveServiceRequestList_logging() throws Exception {
		ServiceRequestListContract c = new ServiceRequestListContract();
		c.setMdmID("mock-mdmId");
		c.setMdmLevel("Legal");
		ServiceRequestListResult r = service.retrieveServiceRequestList(c);
	}
	
	
//    @Test
//    public void queryServiceRequestDetail() throws Exception {
//        String s = "";
//        MiscTest.sampleSiebelQuery(ServiceRequestDetail.class, "[LXK SW Agreement Account LEGAL MDM ID] <> ''", 5);
//    }

	@Test
	public void testUpdateUserFavoriteAddress_logging() throws Exception {
		FavoriteAddressContract c = new FavoriteAddressContract();
		service.updateUserFavoriteAddress(c);

	}

	@Test
	public void testRetrieveSiebelAccountList_defect7662() throws Exception {

		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmId("68724");
		contract.setMdmLevel("Legal");
		contract.setVendorFlag(true);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(false);
		
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		MiscTest.print("Result: ", result);

	}
	
	@Test
	public void testRetrieveSiebelAccountList_searchspecTest() throws Exception {
		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmId("6825");
		contract.setMdmLevel("Account");
		contract.setNewQueryIndicator(true);
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		MiscTest.print("Result: ", result);
	}
	
	@Test
	public void testRetrieveSiebelAccountList_defect7692() throws Exception {

		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmId("1-4LLYQJK");
		contract.setMdmLevel("Siebel");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(true);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(false);
		
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		for (Account acc : result.getAccountList()) {
			System.out.println(acc.getCountryCode());
		}
		MiscTest.print("Result: ", result);

	}
	
	@Test
	public void testRetrieveSiebelAccountList_defect7697() throws Exception {

		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmId("519926455");
		contract.setMdmLevel("Global");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(true);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(false);
		
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		for (Account acc : result.getAccountList()) {
			System.out.println(acc.getCountryCode());
		}
		MiscTest.print("Result: ", result);

	}
	
	
	@Test
	public void testUnBookmarkAddressList_defect7889() throws Exception {
		FavoriteAddressContract contract = new FavoriteAddressContract();
		contract.setContactId("1-5HR5CYV");
		contract.setFavoriteFlag(true);
		contract.setFavoriteAddressId("1-1J1HOL7");
		contract.setSessionHandle(crmSessionHandle);
		
		Boolean result = service.updateUserFavoriteAddress(contract).isResult();
		System.out.println("End: " + result);

	}
	
	@Test
	public void testRetrieveAddressList_defect7983() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setLocale(new Locale("en_GB"));
		contract.setContactId("1-5HR5CYV");
		contract.setMdmLevel("Siebel");
		contract.setMdmId("1-YFNVUX");
		contract.setSessionHandle(crmSessionHandle);
		contract.setFavoriteFlag(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("city", "DESCENDING");
		contract.setSortCriteria(sortMap);
	
		AddressListResult result = service.retrieveAddressList(contract);
		
		for (GenericAddress address : result.getAddressList()) {
			System.out.println(address.getCity());
		}
		MiscTest.print(result.getAddressList());
	}
	
	@Test
	public void testRetrieveAddressList_defect8017() throws Exception {
		
		AddressListContract contract = new AddressListContract();
		contract.setLocale(new Locale("en_US"));
		contract.setContactId("1-5HR5CYV");
		contract.setMdmLevel("Legal");
		contract.setMdmId("29142");
		contract.setSessionHandle(crmSessionHandle);
		contract.setFavoriteFlag(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("officeNumber", "ASCENDING");
		contract.setSortCriteria(sortMap);
		
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("officeNumber", "ab");
		contract.setFilterCriteria(filterMap);
	
		AddressListResult result = service.retrieveAddressList(contract);
		
		for (GenericAddress address : result.getAddressList()) {
//			System.out.println("Office number: " + address.getOfficeNumber());
//			System.out.println("County: " + address.getCounty());
//			System.out.println("District: " + address.getDistrict());
		}
		MiscTest.print(result.getAddressList());
	}

	
	@Test
	public void testRetrieveAddressList_defect7889() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(1);

		executor.submit(new Runnable() {

			@Override
			public void run() {
				AddressListContract contract = new AddressListContract();
				contract.setContactId("1-5HR5CYV");
				contract.setFavoriteFlag(true);
				contract.setMdmLevel("Siebel");
				contract.setMdmId("1-YFNVUX");
				contract.setSessionHandle(crmSessionHandle);
				
				contract.setNewQueryIndicator(true);
				contract.setStartRecordNumber(0);
				contract.setIncrement(40);
				
				AddressListResult result = null;
				try {
					result = service.retrieveAddressList(contract);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				List<GenericAddress> addresses = result.getAddressList();
				int totalCount = result.getTotalCount();

				System.out.println("Size 1: " + addresses.size());
				System.out.println("Total count 1: " + totalCount);
			}
		}).get();
		
		executor.submit(new Runnable() {

			@Override
			public void run() {
				AddressListContract contract = new AddressListContract();
				contract.setContactId("1-5HR5CYV");
				contract.setFavoriteFlag(true);
				contract.setMdmLevel("Siebel");
				contract.setMdmId("1-YFNVUX");
				contract.setSessionHandle(crmSessionHandle);
				
				contract.setNewQueryIndicator(false);
				contract.setStartRecordNumber(40);
				contract.setIncrement(40);
				
				AddressListResult result = null;
				try {
					result = service.retrieveAddressList(contract);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				List<GenericAddress> addresses = result.getAddressList();
				int totalCount = result.getTotalCount();

				System.out.println("Size 2: " + addresses.size());
				System.out.println("Total count 2: " + totalCount);
			}
		}).get();

		executor.shutdown();
        
        System.out.println("End");

	}
	
	
	@Test
	public void testRetrieveAddressList_OfficeNumberIssue() throws Exception {
		
		AddressListContract contract = new AddressListContract();
		contract.setLocale(new Locale("en_GB"));
		contract.setContactId("1-5HR5CYV");
		contract.setMdmLevel("Siebel");
		contract.setMdmId("1-YFNVUX");
		contract.setSessionHandle(crmSessionHandle);
		contract.setFavoriteFlag(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("officeNumber", "ASCENDING");
		contract.setSortCriteria(sortMap);
		
//		Map<String, Object> filterMap = new HashMap<String, Object>();
//		filterMap.put("officeNumber", "6");
//		contract.setFilterCriteria(filterMap);
	
		AddressListResult result = service.retrieveAddressList(contract);
		
		for (GenericAddress address : result.getAddressList()) {
			System.out.println("Office number: " + address.getOfficeNumber());
//			System.out.println("County: " + address.getCounty());
//			System.out.println("District: " + address.getDistrict());
		}
		MiscTest.print(result.getAddressList());
	}
	
	@Test
	public void testRetrieveAddressList_Sandbox_defect12995() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setLocale(new Locale("en_US"));
		contract.setContactId("1-7CXV7CT");
		contract.setMdmLevel("Global");
		contract.setMdmId("315000554");
		contract.setSessionHandle(crmSessionHandle);
		contract.setFavoriteFlag(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("district", "ASCENDING");
		contract.setSortCriteria(sortMap);
		
		AddressListResult result = service.retrieveAddressList(contract);
		for (GenericAddress address : result.getAddressList()) {
			System.out.println("Office number: " + address.getOfficeNumber());
		}
		MiscTest.print(result.getAddressList());
	}
	
	@Test
	public void testRetrieveAddressList_Sandbox_defect16613() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setLocale(new Locale("en_US"));
		contract.setContactId("1-LMPR6YL");
		contract.setMdmLevel("Siebel");
		contract.setMdmId("1-ZIFBCQ");
		contract.setSessionHandle(crmSessionHandle);
		contract.setFavoriteFlag(false);
		contract.setLbsFlag(true);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("addressName", "ASCENDING");
		contract.setSortCriteria(sortMap);
		
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("region", "ny");
		contract.setFilterCriteria(filterCriteria );
		
		AddressListResult result = service.retrieveAddressList(contract);
		MiscTest.print(result.getAddressList());
	}
	
	@Test
	public void testUnBookmarkAddressList_defect8093() throws Exception {
		final FavoriteAddressContract contract = new FavoriteAddressContract();
		
		contract.setContactId("1-5HR5CYV");
		contract.setFavoriteAddressId("1-2J7T67B");
		contract.setSessionHandle(crmSessionHandle);
		contract.setFavoriteFlag(true);
		
//		contract.setContactId("1-6AH086J");
//		contract.setFavoriteAddressId("1-2O46L52");
//		contract.setSessionHandle(crmSessionHandle);
//		contract.setFavoriteFlag(true);
		
//		contract.setContactId("1-6DV03JT");
//		contract.setFavoriteAddressId("1-2NMGJHU");
//		contract.setSessionHandle(crmSessionHandle);
//		contract.setFavoriteFlag(true);
		
//		contract.setContactId("1-7E1VKFD");
//		contract.setFavoriteAddressId("1-F683WG");
//		contract.setSessionHandle(crmSessionHandle);
//		contract.setFavoriteFlag(false);
		
//		contract.setContactId("1-526QV0B");
//		contract.setFavoriteAddressId("1-17PWLRZ");
//		contract.setSessionHandle(crmSessionHandle);
//		contract.setFavoriteFlag(false);
		
		ExecutorService executor = Executors.newFixedThreadPool(1);
		
		Future<Boolean> totalCountFuture = executor.submit(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return service.updateUserFavoriteAddress(contract).isResult();
			}
		});
		
		Boolean result = totalCountFuture.get();
		
		executor.shutdown();
		
		System.out.println("End: " + result);
		
//		Boolean flag = true;
//		for (int i = 0; i < 10; i++) {
//			contract.setFavoriteFlag(flag);
//			Boolean result = service.updateUserFavoriteAddress(contract)
//					.isResult();
//			System.out.println("End: " + result);
//			flag = !flag;
//		}
		
//		Boolean result = service.updateUserFavoriteAddress(contract).isResult();
//		System.out.println("End: " + result);

	}
	
	@Test
	public void testRetrieveSiebelAccountList_defect7925() throws Exception {

		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmId("43800");
		contract.setMdmLevel("Legal");
		contract.setNewQueryIndicator(false);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(false);
		contract.setSessionHandle(crmSessionHandle);
		
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		
		System.out.println(result.getAccountList().size());
		
		for (Account acc : result.getAccountList()) {
			System.out.println(acc.isHardwareRequestFlag());
		}
		MiscTest.print("Result: ", result);

	}

	@Test
	public void testRetrieveSiebelAccountList_defect8569() throws Exception {

		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmId("107998");
		contract.setMdmLevel("Legal");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(true);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(false);
		contract.setSessionHandle(crmSessionHandle);
		
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		
		System.out.println(result.getAccountList().size());
		int i = 0;
		for (Account account : result.getAccountList()) {
			System.out.println("Agreement Id: " + account.getAgreementId());
			System.out.println("Agreement Name: " + account.getAgreementName());
			if (account.getAgreements() != null) {
				System.out.println("Size: " + account.getAgreements().size());
				for (Agreement agreement : account.getAgreements()) {
					System.out.println(agreement.getAgreementId());
					System.out.println(agreement.getAgreementName());
				}
			} else {
				System.out.println("Agreements are null: " + i);
			}
			
			i++;
		}
		
//		MiscTest.print(result.getAccountList());

	}
	
	
	
	
//	@Test
//	public void testRetrieveSiebelAccountList_defect8576() throws Exception {
//
//		SiebelAccountListContract contract = new SiebelAccountListContract();
//		contract.setMdmId("1-YFNVUX");
//		contract.setMdmLevel("Siebel");
//		contract.setNewQueryIndicator(true);
//		contract.setVendorFlag(false);
//		contract.setAgreementFlag(true);
//		contract.setHardwareFlag(true);
//		contract.setSessionHandle(crmSessionHandle);
//		
//		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
//		
//		List<Account> list = result.getAccountList();
//	
//		System.out.println(list.size());
//		
//		for (Account account : list) {
//			System.out.println(account.getAccountName());
//			System.out.println(account.getAgreementId());
//			System.out.println(account.getSoldToNumbers());
//		}
//	}
	
	
	@Test
	public void testRetrieveSiebelAccountList_HardwareIssue() throws Exception {

		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmLevel("Legal");
		contract.setMdmId("43800");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(false);
		contract.setAgreementFlag(true);
		contract.setHardwareFlag(true);
		contract.setSessionHandle(crmSessionHandle);
		
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		
		System.out.println(result.getAccountList().size());
		
		for (Account acc : result.getAccountList()) {
			if(acc.getSoldToNumbers()!=null) {
				System.out.println(acc.getSoldToNumbers().size());
			}
		}

	}
	
	
	@Test
	public void testRetrieveAddressList_IncorrectCount() throws Exception {
		
		AddressListContract contract = new AddressListContract();
		contract.setLocale(new Locale("en_US"));
		contract.setContactId("1-6DV03JT");
		contract.setMdmLevel("Legal");
		contract.setMdmId("2216");
		contract.setSessionHandle(crmSessionHandle);
		contract.setFavoriteFlag(true);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("addressLine1", "ASCENDING");
		contract.setSortCriteria(sortMap);
		
//		Map<String, Object> filterMap = new HashMap<String, Object>();
//		filterMap.put("officeNumber", "ab");
//		contract.setFilterCriteria(filterMap);
	
		AddressListResult result = service.retrieveAddressList(contract);
		
		List<GenericAddress> addresses = result.getAddressList();
		
		System.out.println("Size: " + addresses.size());
		System.out.println("Total count: " + result.getTotalCount());
		
	}
	
	
	@Test
	public void testUnBookmarkAddressList_defect8093_NewIssue() throws Exception {
		final FavoriteAddressContract contract = new FavoriteAddressContract();
//		contract.setContactId("1-6AH086J");
//		contract.setFavoriteAddressId("1-2O46L52");
//		contract.setSessionHandle(crmSessionHandle);
//		contract.setFavoriteFlag(false);
		
		contract.setContactId("1-6DV03JT");
		contract.setFavoriteAddressId("1-2NMGJHU");
		contract.setSessionHandle(crmSessionHandle);
		contract.setFavoriteFlag(false);
		
//		final AddressListContract addressListContract = new AddressListContract();
//		addressListContract.setLocale(new Locale("en_US"));
//		addressListContract.setContactId("1-6AH086J");
//		addressListContract.setMdmLevel("Legal");
//		addressListContract.setMdmId("43800");
//		addressListContract.setSessionHandle(crmSessionHandle);
//		addressListContract.setFavoriteFlag(false);
//		addressListContract.setNewQueryIndicator(true);
//		addressListContract.setStartRecordNumber(0);
//		addressListContract.setIncrement(40);
//		Map<String,Object> sortCriteria = new HashMap<String, Object>();
//		sortCriteria.put("addressLine1", "ASCENDING");
//		addressListContract.setSortCriteria(sortCriteria);
		
		long t = System.currentTimeMillis();
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		Future<FavoriteResult> totalCountFuture = executor.submit(new Callable<FavoriteResult>() {
			@Override
			public FavoriteResult call() throws Exception {
				System.out.println("First started");
				FavoriteResult fav = service.updateUserFavoriteAddress(contract);
				System.out.println("First finished");
				return fav;
			}
		});
		
		Future<AddressListResult> addressListFuture = executor.submit(new Callable<AddressListResult>() {
			@Override
			public AddressListResult call() throws Exception {
				System.out.println("Second started");
				Thread.sleep(3000);
//				AddressListResult all = service.retrieveAddressList(addressListContract);
				System.out.println("Second finished");
//				return all;
				
				return new AddressListResult();
			}
		});
		
		Boolean result = null;
		List<GenericAddress> addressList = null;

		
		try {
			result = totalCountFuture.get().isResult();
		}
		catch(Exception ex) {
			System.out.println(ex.getCause());
		}
		
		try {
			addressList = addressListFuture.get().getAddressList();
		}
		catch(Exception ex) {
			System.out.println(ex.getCause());
		}
		
		executor.shutdown();


		System.out.println("Exec time: " + (System.currentTimeMillis()-t)/1000.0);
		
		System.out.println("Unbookmark result: " + result);
		System.out.println("Address list is null: " + (addressList == null));
		System.out.println("Address List result: " + addressList.size());
		System.out.println("End");

	}
	
	
	@Test
	public void testUnBookmarkAddressList_SetServiceAddressFavoriteFlagFailed() throws Exception {
		FavoriteAddressContract contract = new FavoriteAddressContract();
		contract.setContactId("1-5HR5CYV");
		contract.setFavoriteAddressId("1-2O462O7");
		contract.setFavoriteFlag(false);
		contract.setSessionHandle(crmSessionHandle);
		
		Boolean result = service.updateUserFavoriteAddress(contract).isResult();
		System.out.println("End: " + result);

	}
	
	
	@Test
	public void testUnBookmarkAddressList_BookmarkAddressInPartnerPortal() throws Exception {
		FavoriteAddressContract contract = new FavoriteAddressContract();
		contract.setContactId("1-687WSZZ");
		contract.setFavoriteAddressId("1-BC68VQ");
		contract.setFavoriteFlag(true);
		contract.setSessionHandle(crmSessionHandle);
		
		Boolean result = service.updateUserFavoriteAddress(contract).isResult();
		System.out.println("End: " + result);

	}
	
	
	@Test
	public void testRetrieveAddressList_PaginationIssue() throws Exception {
		
		
		ExecutorService executor = Executors.newFixedThreadPool(1);

		executor.submit(new Runnable() {

			@Override
			public void run() {

				AddressListContract contract = new AddressListContract();
				contract.setLocale(new Locale("en_US"));
				contract.setContactId("1-76WLHJJ");
				contract.setMdmLevel("Global");
				contract.setMdmId("300021047");
				contract.setSessionHandle(crmSessionHandle);
				contract.setFavoriteFlag(false);
				contract.setNewQueryIndicator(true);
				contract.setStartRecordNumber(0);
				contract.setIncrement(40);
				
				Map<String, Object> sortMap = new HashMap<String, Object>();
				sortMap.put("addressName", "ASCENDING");
				contract.setSortCriteria(sortMap);
				
				
				AddressListResult result = null;
				
				try {
					result = service.retrieveAddressList(contract);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				System.out.println("First page size: " + result.getAddressList().size());
				System.out.println("First page toatal count: " + result.getTotalCount());
			}
		}).get();

		
		Thread.sleep(5000);
		
		
		executor.submit(new Runnable() {

			@Override
			public void run() {

				AddressListContract contract = new AddressListContract();
				contract.setLocale(new Locale("en_US"));
				contract.setContactId("1-76WLHJJ");
				contract.setMdmLevel("Global");
				contract.setMdmId("300021047");
				contract.setSessionHandle(crmSessionHandle);
				contract.setFavoriteFlag(false);
				contract.setNewQueryIndicator(false);
				contract.setStartRecordNumber(40);
				contract.setIncrement(40);
				
				Map<String, Object> sortMap = new HashMap<String, Object>();
				sortMap.put("addressName", "ASCENDING");
				contract.setSortCriteria(sortMap);
				
				AddressListResult result = null;
				
				try {
					result = service.retrieveAddressList(contract);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				System.out.println("Second page size: " + result.getAddressList().size());
				System.out.println("Second page toatal count: " + result.getTotalCount());

			}
		}).get();

		
		executor.shutdown();
		
		System.out.println("End");
	}

	
	@Test
	public void testUnBookmarkAddressList_defect9334() throws Exception {
		final FavoriteAddressContract contract = new FavoriteAddressContract();
		
//		contract.setContactId("1-7G9V69N");
//		contract.setFavoriteAddressId("1-1QHI9H5");
//		contract.setAccountId("1-JL69JH");
//		contract.setSessionHandle(crmSessionHandle);
//		contract.setFavoriteFlag(false);
		
		contract.setContactId("1-7G9V69N");
		contract.setFavoriteAddressId("1-1PS92YP");
		contract.setAccountId("1-JL69JH");
		contract.setSessionHandle(crmSessionHandle);
		contract.setFavoriteFlag(true);
		
		FavoriteResult fav = service.updateUserFavoriteAddress(contract);

		System.out.println("Result: " + fav.isResult());

	}
	
	@Test
	public void testRetrieveAddressList_defect11034() throws Exception {
		
		AddressListContract contract = new AddressListContract();
		contract.setLocale(new Locale("en_US"));
		contract.setContactId("1-76WLHJJ");
		contract.setMdmLevel("Global");
		contract.setMdmId("300021047");
		contract.setSessionHandle(crmSessionHandle);
		contract.setFavoriteFlag(true);
		contract.setCleansedAddress(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("addressName", "ASCENDING");
		contract.setSortCriteria(sortMap);
		
		AddressListResult result = service.retrieveAddressList(contract);
		
		List<GenericAddress> addresses = result.getAddressList();
		
		System.out.println("Size: " + addresses.size());
		System.out.println("Total count: " + result.getTotalCount());
		
//		for (GenericAddress address : result.getAddressList()) {
//			System.out.println("Address Name: " + address.getAddressName());
//			System.out.println("Address Line 1: " + address.getAddressLine1());
//			System.out.println("City: " + address.getCity());
//			System.out.println("Country: " + address.getCountry());
//		}
	}
	
	
	@Test
	public void testRetrieveAssociatedServiceRequestList_defect11890() throws Exception {
		ServiceRequestAssociatedListContract contract = new ServiceRequestAssociatedListContract();
		contract.setLocale(new Locale("en_US"));
		contract.setMdmLevel("Global");
		contract.setMdmID("001915172");
		contract.setServiceRequestNumber("1-18406618311");
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("requestNumber", "ASCENDING");
		contract.setSortCriteria(m);
		
		ServiceRequestListResult result = service.retrieveAssociatedServiceRequestList(contract);
		
		List<ServiceRequest> requests = result.getServiceRequests();
		
		System.out.println("Size: " + requests.size());
		System.out.println("Total count: " + result.getTotalCount());
		
		for (ServiceRequest serviceRequest : requests) {
			System.out.println(serviceRequest.getServiceRequestNumber());
		}
	}
	
	
	@Test
	public void testRetrieveSiebelAccountList_defect9952() throws Exception {

		SiebelAccountListContract contract = new SiebelAccountListContract();
		contract.setMdmId("13475");
		contract.setMdmLevel("Legal");
		contract.setNewQueryIndicator(true);
		contract.setVendorFlag(true);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(false);
		contract.setSessionHandle(crmSessionHandle);
		
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		
		List<Account> accounts = result.getAccountList();
		
		System.out.println(result.getAccountList().size());

		for (Account account : accounts) {
			if(account.getAccountId().equalsIgnoreCase("1-14EQBVT")) {
				System.out.println(account.isAddressFlag());
			}
		}
	}
	
	@Test
	public void testRetrieveAddressList_defect12995() throws Exception {
		
		AddressListContract contract = new AddressListContract();
		contract.setLocale(new Locale("en_US"));
		contract.setContactId("1-8VL6AF5");
		contract.setMdmLevel("Legal");
		contract.setMdmId("63530");
		contract.setSessionHandle(crmSessionHandle);
		contract.setFavoriteFlag(false);
		contract.setCleansedAddress(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("addressName", "ASCENDING");
		contract.setSortCriteria(sortMap);
		
		AddressListResult result = service.retrieveAddressList(contract);
		
		List<GenericAddress> addresses = result.getAddressList();
		
		System.out.println("Size: " + addresses.size());
		System.out.println("Total count: " + result.getTotalCount());
		
//		for (GenericAddress address : result.getAddressList()) {
//			System.out.println("Address Name: " + address.getAddressName());
//			System.out.println("Address Line 1: " + address.getAddressLine1());
//			System.out.println("City: " + address.getCity());
//			System.out.println("Country: " + address.getCountry());
//		}
		
		for (GenericAddress address : addresses) {
			System.out.println(address.getOfficeNumber());
		}
	}
	
	
	@Test
	public void testRetrieveAddressList_LBSAddressTest_2_FromArko() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setMdmId("007915069");
		contract.setMdmLevel("Global");
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setLbsFlag(true);
		contract.setCountry("USA");
		contract.setState("KY");


		AddressListResult result = service.retrieveLBSAddressList(contract);
		MiscTest.print(result.getLbsAddressList());
	}
	
	
	@Test
	public void testRetrieveAddressList_LBSAddressTest_3_fromArko() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setMdmId("144809");
		contract.setMdmLevel("Legal");
		contract.setLocale(new Locale("en_US"));
		contract.setContactId("80J75U7");
		contract.setNewQueryIndicator(true);
		contract.setFirstLoccationCall(false);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setLbsFlag(true);
//		contract.setCountry("USA");
//		contract.setState("KY");

		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("addressName", "DESCENDING");
		contract.setSortCriteria(sortMap);
		
		
		AddressListResult result = service.retrieveAddressList(contract);
		MiscTest.print(result.getLbsAddressList());
	}
	
	@Test
	public void testRetrieveSiebelAccountList_LBSFlag(){
		contract.setMdmLevel("Global");
		contract.setMdmId("009420159");
		
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		System.out.println("------------------------------");
		System.out.println("------------------------------");
		System.out.println("------------------------------");
		
		for (Account test : result.getAccountList()) {
			System.out.println("------------------------------");
			System.out.println(test.isLbsDisplayWeb());			
		}
		System.out.println("------------------------------");
		
	}
	
	
	@Test
	public void testRetrieveSiebelAccountList_populateLBSFLag() throws Exception {
		contract.setMdmId("315000554");
		contract.setMdmLevel("Global");
		contract.setNewQueryIndicator(true);
		
		
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		print(result);
	}
	
	@Test
	public void testRetrieveSiebelAccountList_defect14959() throws Exception {
		contract.setMdmId("65263");
		contract.setMdmLevel("Legal");
		contract.setNewQueryIndicator(true);
		contract.setAgreementFlag(true);
		contract.setHardwareFlag(true);
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		print(result);
	}
	
	@Test
	public void testRetrieveSiebelAccountList_AIR00085542() throws Exception {
		contract.setMdmId("623331717");
		contract.setMdmLevel("Global");
		contract.setNewQueryIndicator(false);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(false);
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		for (Account account : result.getAccountList()) {
			System.out.println("CatalogExpediteFlag: " + account.isCatalogExpediteFlag());
		}
		print(result);
	}
	
	@Test
	public void testRetrieveSiebelAccountList_defect17611() throws Exception {
		contract.setMdmId("623331717");
		contract.setMdmLevel("Global");
		contract.setNewQueryIndicator(true);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(false);
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		print(result);
	}
	
	@Test
	public void testRetrieveSiebelAccountList_INC0106674() throws Exception {
		contract.setMdmLevel("Global");
		contract.setMdmId("487201915");
		contract.setAccountId("1-LBTR7B");
		contract.setNewQueryIndicator(true);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(false);
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		for (Account account : result.getAccountList()) {
			System.out.println("Account name: " + account.getAccountName());
			System.out.println("Agreement name: " + account.getAgreementName());
			System.out.println("CreateServiceRequest: " + account.getCreateServiceRequest());
			System.out.println("----------------------");
		}
		System.out.println("size: " + result.getAccountList().size());
	}
	
	@Test
	public void testRetrieveSiebelAccountList_B2Bflag() throws Exception {
		contract.setMdmLevel("Global");
		contract.setMdmId("483419636");
		contract.setAccountId("1-LBTR7B");
		contract.setNewQueryIndicator(false);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(false);
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		for (Account account : result.getAccountList()) {
			System.out.println("B2bFlag: " + account.getB2bFlag());
		}
		System.out.println("size: " + result.getAccountList().size());
	}
	
	@Test
	public void testRetrieveSiebelAccountList_failing() throws Exception {
		contract.setMdmLevel("Legal");
		contract.setMdmId("65077");
//		contract.setAccountId("1-LBTR7B");
		contract.setNewQueryIndicator(true);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(false);
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		System.out.println("size: " + result.getAccountList().size());
	}
	
	@Test
	public void testRetrieveSiebelAccountList_INC0111506() throws Exception {
		contract.setMdmLevel("Legal");
		contract.setMdmId("2200");
//		contract.setAccountId("1-LBTR7B");
		contract.setNewQueryIndicator(true);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(false);
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		for (Account account : result.getAccountList()) {
			System.out.println("UserConsumables: " + account.getUserConsumables() + " (Account: " + account.getAccountName() + " )");
		}
		System.out.println("size: " + result.getAccountList().size());
	}
	
	@Test
	public void testRetrieveSiebelAccountList_SearchSpec() throws Exception {
		contract.setMdmLevel("Global");
		contract.setMdmId("062320692");
//		contract.setAccountId("1-LBTR7B");
		contract.setNewQueryIndicator(true);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(false);
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		System.out.println("size: " + result.getAccountList().size());
	}
	
	@Test
	public void testRetrieveSiebelAccountList_Data() throws Exception {
		contract.setMdmLevel("Account");
		contract.setMdmId("39002");
//		contract.setAccountId("1-LBTR7B");
		contract.setNewQueryIndicator(false);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(false);
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		System.out.println("size: " + result.getAccountList().size());
	}
	
	@Test
	public void testRetrieveSiebelAccountList_LBS1_5() throws Exception {
		contract.setMdmLevel("Legal");
		contract.setMdmId("33228");
//		contract.setAccountId("1-LBTR7B");
		contract.setNewQueryIndicator(false);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(false);
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		System.out.println("size: " + result.getAccountList().size());
	}
	
	@Test
	public void testRetrieveSiebelAccountList_INC0139413() throws Exception {
		contract.setMdmLevel("Domestic");
		contract.setMdmId("370514358");
//		contract.setAccountId("1-LBTR7B");
		contract.setNewQueryIndicator(true);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(false);
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		for (Account account : result.getAccountList()) {
			System.out.println("Acc Name: " + account.getAccountName());
		}
		System.out.println("size: " + result.getAccountList().size());
	}
	
	@Test
	public void testRetrieveSiebelAccountList_INC0144014() throws Exception {
		contract.setMdmLevel("Global");
		contract.setMdmId("077583318");
//		contract.setAccountId("1-LBTR7B");
		contract.setNewQueryIndicator(true);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(false);
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		for (Account account : result.getAccountList()) {
			System.out.println("AgreementId: " + account.getAgreementId());
			System.out.println("AccountId: " + account.getAccountId());
		}
		System.out.println("size: " + result.getAccountList().size());
	}
	
	@Test
	public void testRetrieveSiebelAccountList_defect19173() throws Exception {
		contract.setMdmLevel("Legal");
		contract.setMdmId("134191");
//		contract.setAccountId("1-LBTR7B");
		contract.setNewQueryIndicator(false);
		contract.setAgreementFlag(false);
		contract.setHardwareFlag(false);
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		for (Account account : result.getAccountList()) {
			System.out.println("Hardware flag: " + account.isHardwareRequestFlag());
		}
		System.out.println("size: " + result.getAccountList().size());
	}
	
	@Test
	public void testRetrieveSiebelAccountList_INC0162901() throws Exception {
		contract.setMdmLevel("Global");
		contract.setMdmId("008908956");
//		contract.setAccountId("1-LBTR7B");
		contract.setNewQueryIndicator(true);
		contract.setAgreementFlag(true);
		contract.setHardwareFlag(true);
		SiebelAccountListResult result = service.retrieveSiebelAccountList(contract);
		for (Account account : result.getAccountList()) {
			for (String soldto : account.getSoldToNumbers()) {
				System.out.println(soldto);
			}
		}
		System.out.println("size: " + result.getAccountList().size());
	}
	

	@Test
	public void testRetrieveAddressList_Defect_16135_1() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setMdmId("315000554");
		contract.setMdmLevel("Global");
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setLbsFlag(true);
		contract.setCountry("Belgium");


		AddressListResult result = service.retrieveLBSAddressList(contract);
		MiscTest.print(result.getLbsAddressList());
	}
	
	
	@Test
	public void testRetrieveAddressList_LBSAddressTest_3_fromArko_LBSFlag() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setMdmId("144809");
		contract.setMdmLevel("Legal");
		contract.setLocale(new Locale("en_US"));
		contract.setContactId("80J75U7");
		contract.setNewQueryIndicator(true);
		contract.setFirstLoccationCall(false);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setLbsFlag(true);
//		contract.setCountry("USA");
//		contract.setState("KY");

		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("addressName", "DESCENDING");
		contract.setSortCriteria(sortMap);
		
		
		AddressListResult result = service.retrieveAddressList(contract);
		MiscTest.print(result.getLbsAddressList());
	}
	
	@Test
	public void testRetrieveAddressList_LBSAddressTest_2_FromArko__() throws Exception {
	  AddressListContract contract = new AddressListContract();
	  contract.setSessionHandle(crmSessionHandle);
	  contract.setMdmId("007915069");
	  contract.setMdmLevel("Global");
	  contract.setNewQueryIndicator(true);
	  contract.setStartRecordNumber(0);
	  contract.setIncrement(40);
	  contract.setLbsFlag(true); //What happens when it's false???
	  contract.setCountry("USA");
	  contract.setState("KY");
//	  contract.setCity("Rustavi");
	  AddressListResult result = service.retrieveLBSAddressList(contract);
	  MiscTest.print(result.getLbsAddressList());
	}
	
	@Test
	public void testRetrieveAddressList_LBSAddressTest_3_FromArko_NoCountry() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setMdmId("007915069");
		contract.setMdmLevel("Global");
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setLbsFlag(true); 
		contract.setCountry("France");
		//contract.setState("KY");
		AddressListResult result = service.retrieveLBSAddressList(contract);
		for (LBSAddress address : result.getLbsAddressList()) {
			System.out.println(address.getCountry());
		}
		MiscTest.print(result.getLbsAddressList());
	}
	
	@Test
	public void testRetrieveAddressList_LBSAddressTest_2_FromArko_() throws Exception {
	  ExecutorService executor = null;
	  executor = Executors.newFixedThreadPool(2);
	 
	  Future<List<AddressListResult>> requestsListFutureCountry = executor.submit(new Callable<List<AddressListResult>>() {
	  	@Override
	  	public List<AddressListResult> call() throws Exception {
	  	  final AddressListContract contract = new AddressListContract();
	  	  contract.setSessionHandle(crmSessionHandle);
	  	  contract.setMdmId("007915069");
	  	  contract.setMdmLevel("Global");
	  	  contract.setNewQueryIndicator(true);
	  	  contract.setStartRecordNumber(0);
	  	  contract.setIncrement(40);
	  	  contract.setLbsFlag(true);
	  	  contract.setCountry("France");
	 	// contract.setState("KY");
	  	List<AddressListResult> addResList = new ArrayList<AddressListResult>();
		  AddressListResult lbsAddressList = service.retrieveLBSAddressList(contract);
		  addResList.add(lbsAddressList);
	  	  return addResList;
//	  	  return (List<AddressListResult>) service.retrieveLBSAddressList(contract);
	  	}
	  	});
	 
	  Future<List<AddressListResult>> requestsListFutureCity = executor.submit(new Callable<List<AddressListResult>>() {
		@Override
		public List<AddressListResult> call() throws Exception {
		  final AddressListContract contract1 = new AddressListContract();
		  contract1.setSessionHandle(crmSessionHandle);
		  contract1.setMdmId("007915069");
		  contract1.setMdmLevel("Global");
		  contract1.setNewQueryIndicator(true);
		  contract1.setStartRecordNumber(0);
		  contract1.setIncrement(40);
		  contract1.setLbsFlag(true);
		  contract1.setCountry("USA");
		  contract1.setState("KY");
		  List<AddressListResult> addResList = new ArrayList<AddressListResult>();
		  AddressListResult lbsAddressList = service.retrieveLBSAddressList(contract1);
		  addResList.add(lbsAddressList);
		  return addResList;
//		  return (List<AddressListResult>) service.retrieveLBSAddressList(contract1);
		}
		});
	  
	  Future<List<AddressListResult>> future = executor.submit(new Callable<List<AddressListResult>>() {
		  @Override
		  public List<AddressListResult> call() throws Exception {
			  final AddressListContract contract1 = new AddressListContract();
			  contract1.setSessionHandle(crmSessionHandle);
			  contract1.setMdmId("007915069");
			  contract1.setMdmLevel("Global");
			  contract1.setNewQueryIndicator(true);
			  contract1.setStartRecordNumber(0);
			  contract1.setIncrement(40);
			  contract1.setLbsFlag(true);
			  contract1.setCountry("USA");
			  contract1.setState("NY");
			  List<AddressListResult> addResList = new ArrayList<AddressListResult>();
			  AddressListResult lbsAddressList = service.retrieveLBSAddressList(contract1);
			  addResList.add(lbsAddressList);
			  return addResList;
//		  return (List<AddressListResult>) service.retrieveLBSAddressList(contract1);
		  }
	  });
	 
//		  requestsListFutureCountry.get();
//		  requestsListFutureCity.get();
	 
	// int totalCount = totalCountFuture.get();
	  
	  List<AddressListResult> countryList = requestsListFutureCountry.get();
	  List<AddressListResult> cityList = requestsListFutureCity.get();
	  
//	  AddressListResult result1 = (AddressListResult) requestsListFutureCountry.get();
//	  AddressListResult result2 = (AddressListResult) requestsListFutureCity.get();
	 
	  executor.shutdown();
	  
	  for (AddressListResult city : cityList) {
		List<LBSAddress> lbsAddressList = city.getLbsAddressList();
		for (LBSAddress lbsAddress : lbsAddressList) {
			System.out.println(lbsAddress.getCity());
		}
	}
	  
	  System.out.println("------------------");
	  
	  for (AddressListResult country : countryList) {
		List<LBSAddress> lbsAddressList = country.getLbsAddressList();
		for (LBSAddress lbsAddress : lbsAddressList) {
			System.out.println(lbsAddress.getCountry());
		}
	}
	  
	  System.out.println("-------------------");
	  System.out.println(countryList.size());
	  System.out.println(cityList.size());

//	  System.out.println("result ."+result1.getLbsAddressList().size());
//	  System.out.println("result 1 ."+result2.getLbsAddressList().size());
	 
	// MiscTest.print(result.getLbsAddressList());

	 }
	
	@Test
	public void testRetrieveLBSAddressList_defect19723() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setMdmId("315016972");
		contract.setMdmLevel("Global");
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setLbsFlag(true); 
		contract.setCountry("Germany");
		//contract.setState("KY");
		AddressListResult result = service.retrieveLBSAddressList(contract);
//		for (LBSAddress address : result.getLbsAddressList()) {
//			System.out.println("Province: " + address.getProvince());
//		}
		for (LBSAddress address : result.getLbsAddressList()) {
			System.out.println("City: " + address.getCity());
		}
		MiscTest.print(result.getLbsAddressList());
	}
	
	@Test
	public void testRetrieveLBSAddressList_defect19723_ProvinceSelected() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setMdmId("315016972");
		contract.setMdmLevel("Global");
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setLbsFlag(true); 
		contract.setCountry("Germany");
		contract.setProvince("Bayern");
		//contract.setState("KY");
		AddressListResult result = service.retrieveLBSAddressList(contract);
		for (LBSAddress address : result.getLbsAddressList()) {
			System.out.println("Province: " + address.getProvince());
		}
		for (LBSAddress address : result.getLbsAddressList()) {
			System.out.println("City: " + address.getCity());
		}
		MiscTest.print(result.getLbsAddressList());
	}
	
	@Test
	public void testRetrieveAddressList_LBSFlagFilter() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setLocale(new Locale("en_US"));
		contract.setContactId("1-80J75U7");
		contract.setMdmId("623331717");
		contract.setMdmLevel("Global");
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setLbsFlag(true);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("lbsAddressFlag", "Y");
		contract.setFilterCriteria(filterCriteria );
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("addressName", "DESCENDING");
		contract.setSortCriteria(sortCriteria );
		
		AddressListResult result = service.retrieveAddressList(contract);
		for (GenericAddress address : result.getAddressList()) {
			System.out.println("LBS flag: " + address.getLbsAddressFlag());
		}
		System.out.println("AddressList size: " + result.getAddressList().size());
	}
	
	@Test
	public void testRetrieveAddressList_LBSFlagSort() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setLocale(new Locale("en_US"));
		contract.setContactId("1-80J75U7");
		contract.setMdmId("623331717");
		contract.setMdmLevel("Global");
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setLbsFlag(false);
//		Map<String, Object> filterCriteria = new HashMap<String, Object>();
//		filterCriteria.put("lbsAddressFlag", "Y");
//		contract.setFilterCriteria(filterCriteria );
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("lbsAddressFlag", "ASCENDING");
		contract.setSortCriteria(sortCriteria );
		
		AddressListResult result = service.retrieveAddressList(contract);
		for (GenericAddress address : result.getAddressList()) {
			System.out.println("LBS flag: " + address.getLbsAddressFlag());
		}
		System.out.println("AddressList size: " + result.getAddressList().size());
	}
	
	@Test
	public void testRetrieveAddressList_INC0129520() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setLocale(new Locale("en_US"));
		contract.setContactId("1-8NGPUBX");
		contract.setMdmId("006961700");
		contract.setMdmLevel("Global");
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setLbsFlag(false);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("addressName", "T-0613");
		contract.setFilterCriteria(filterCriteria );
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("addressName", "DESCENDING");
		contract.setSortCriteria(sortCriteria );
		
		AddressListResult result = service.retrieveAddressList(contract);
		System.out.println("AddressList size: " + result.getAddressList().size());
	}
	
	@Test
	public void testRetrieveAddressList_LBS1_5() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setLocale(new Locale("en_US"));
		contract.setContactId("1-MON94I1");
		contract.setMdmId("15580");
		contract.setMdmLevel("Legal");
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setLbsFlag(true);
//		Map<String, Object> filterCriteria = new HashMap<String, Object>();
//		filterCriteria.put("addressName", "T-0613");
//		contract.setFilterCriteria(filterCriteria );
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("addressName", "DESCENDING");
		contract.setSortCriteria(sortCriteria );
		
		AddressListResult result = service.retrieveAddressList(contract);
		for (GenericAddress address : result.getAddressList()) {
			System.out.println("LevelOfDetails: " + address.getLevelOfDetails());
		}
		System.out.println("AddressList size: " + result.getAddressList().size());
	}
	
	@Test
	public void testRetrieveAddressList_INC0137985() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setLocale(new Locale("en_US"));
		contract.setContactId("1-5GJ091L");
		contract.setMdmId("7276");
		contract.setMdmLevel("Legal");
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setLbsFlag(false);
//		Map<String, Object> filterCriteria = new HashMap<String, Object>();
//		filterCriteria.put("addressName", "T-0613");
//		contract.setFilterCriteria(filterCriteria );
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("addressName", "DESCENDING");
		contract.setSortCriteria(sortCriteria );
		
		long t0 = System.currentTimeMillis();
		AddressListResult result = service.retrieveAddressList(contract);
		System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
		System.out.println("AddressList size: " + result.getAddressList().size());
	}
	
	@Test
	public void testRetrieveAddressList_defect18674_RecordCount() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setLocale(new Locale("en_US"));
		contract.setContactId("1-16X93QX");
		contract.setMdmId("1-DZ9CDJ");
		contract.setMdmLevel("Siebel");
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setLbsFlag(false);
//		Map<String, Object> filterCriteria = new HashMap<String, Object>();
//		filterCriteria.put("addressName", "T-0613");
//		contract.setFilterCriteria(filterCriteria );
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("addressName", "ASCENDING");
		contract.setSortCriteria(sortCriteria );
		
		AddressListResult result = service.retrieveAddressList(contract);
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("AddressList size: " + result.getAddressList().size());
	}
	
	@Test
	public void testRetrieveAddressList_defect19790_Filter() throws Exception {
		AddressListContract contract = new AddressListContract();
		contract.setSessionHandle(crmSessionHandle);
		contract.setLocale(new Locale("en_US"));
		contract.setContactId("1-NVKU6J7");
		contract.setMdmId("623331717");
		contract.setMdmLevel("Global");
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setLbsFlag(true);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("lbsAddressFlag", "Y");
		contract.setFilterCriteria(filterCriteria );
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("addressName", "DESCENDING");
		contract.setSortCriteria(sortCriteria );
		
		AddressListResult result = service.retrieveAddressList(contract);
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("AddressList size: " + result.getAddressList().size());
	}
	

}
