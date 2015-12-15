package com.lexmark.service.impl.real;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import util.TestSessionFactories;

import com.amind.session.SessionFactory;
import com.lexmark.contract.LocationReportingHierarchyContract;
import com.lexmark.contract.RequestContract;
import com.lexmark.contract.RequestListContract;
import com.lexmark.contract.ServiceRequestHistoryListContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Asset;
import com.lexmark.domain.BundlePart;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.Part;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.ServiceRequestActivity;
import com.lexmark.domain.ServiceRequestOrderLineItem;
import com.lexmark.result.RequestListResult;
import com.lexmark.result.RequestLocationsResult;
import com.lexmark.result.RequestResult;
import com.lexmark.result.ServiceRequestListResult;
import com.lexmark.service.impl.real.SortUtil.SortCriteria;
import com.lexmark.service.impl.real.domain.RequestLocationDo;
import com.lexmark.service.impl.real.domain.RequestTypeDo;
import com.lexmark.service.impl.real.domain.RequestTypeFavoriteAssetDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailDo;
import com.lexmark.service.impl.real.util.TestErrors;
import com.lexmark.util.LangUtil;

/**
 * 
 * @see com.lexmark.service.impl.real.AmindRequestTypeServiceTestSuite
 * 
 * @author vpetruchok
 * @version 1.0, 2012-03-02
 */
public class AmindRequestTypeServiceTest extends AmindServiceTest {

	AmindRequestTypeService service;
	RequestContract reqContract;
	RequestListContract reqListContract;
	private int exceptionCounter = 0;

	@Before
	public void setUp() {
		service = new AmindRequestTypeService();

		reqContract = new RequestContract();
		// reqContract.setSessionHandle(crmSessionHandle);

		reqListContract = new RequestListContract();
		// reqListContract.setSessionHandle(crmSessionHandle);
		// reqListContract.setStartRecordNumber(0);
		// reqListContract.setIncrement(40);

		// reqContract.setServiceRequestNumber("1-11665519231");
		// reqContract.setVisibilityRole("Customer");
		// reqContract.setIncrement(0);
		// reqContract.setStartRecordNumber(0);
		// reqContract.setNewQueryIndicator(false);
	}

	@Test
	public void testRetrieveSupplyRequestDetail() throws Exception {
		// reqContract.setServiceRequestNumber("1-33783029");
		reqContract.setServiceRequestNumber("1-1912444908");
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		System.out.println(result.getServiceRequest().getParts() == null);
		print(result);
	}

	@Test
	public void testRetrieveSupplyRequestDetail_QA() throws Exception {
		// reqContract.setServiceRequestNumber("1-8927811341");
		// reqContract.setServiceRequestNumber("1-9001562651");
		reqContract.setServiceRequestNumber("1-9121906043");
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		for (ServiceRequestOrderLineItem item : result.getServiceRequest()
				.getShipmentOrderLines()) {
			System.out
					.printf("[shipmentOrderLines] productDescription=%s, vendorProduct=%s, serialNumber=%s\n",
							item.getProductDescription(),
							item.getVendorProduct(), item.getSerialNumber());
		}
		// print(result);
	}

	@Test
	public void testRetrieveSupplyRequestDetail_QA_20130218() throws Exception {
		reqContract.setServiceRequestNumber("1-11312286606");
		long t0 = System.currentTimeMillis();
		try {
			RequestResult result = service
					.retrieveSupplyRequestDetail(reqContract);
			System.out.println(str(result.getServiceRequest()));
			MiscTest.print("attachments=", result.getServiceRequest()
					.getAttachments());
		} finally {
			System.out.printf("Excec time=%s sec. \n",
					(System.currentTimeMillis() - t0) / 1000.0);
		}
	}

	@Test
	public void testRetrieveSupplyRequestDetail_Prod_20130226()
			throws Exception {
		// reqContract.setServiceRequestNumber("1-11150174741");
		// reqContract.setServiceRequestNumber("1-11150936901");
		// reqContract.setServiceRequestNumber("1-11151425451");
		// reqContract.setServiceRequestNumber("1-11150225981");
		// reqContract.setServiceRequestNumber("1-11158178531");
		// reqContract.setServiceRequestNumber("1-11149729741");
		// reqContract.setServiceRequestNumber("1-11202404381");
		// reqContract.setServiceRequestNumber("1-11217876361");
		reqContract.setServiceRequestNumber("1-11201692571");
		long t0 = System.currentTimeMillis();
		try {
			RequestResult result = service
					.retrieveSupplyRequestDetail(reqContract);
			System.out.println(str(result.getServiceRequest()));
			MiscTest.print("attachments=", result.getServiceRequest()
					.getAttachments());
		} finally {
			System.out.printf("Excec time=%s sec. \n",
					(System.currentTimeMillis() - t0) / 1000.0);
		}
	}

	@Test
	public void testRetrieveSupplyRequestDetail_QA_attachments()
			throws Exception {
		// reqContract.setServiceRequestNumber("1-9121906043");
		reqContract.setServiceRequestNumber("1-9370320011");
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		MiscTest.print("[attachments] ", result.getServiceRequest()
				.getAttachments(), "id", "attachmentName");
	}

	@Test
	public void testRetrieveSupplyRequestDetail_parts_QA() throws Exception {
		// 1-9077385291, 1-9029360951
		// reqContract.setServiceRequestNumber("1-9077385291");
		reqContract.setServiceRequestNumber("1-9029360951");
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		List<Part> parts = result.getServiceRequest().getParts();
		MiscTest.print("[parts] ", parts);
		for (Part part : parts) {
			System.out.printf("status=%s\n", part.getStatus());
		}
		// print(result);
	}

	@Test
	public void testRetrieveSupplyRequestDetail_QA_defect1610()
			throws Exception {
		reqContract.setServiceRequestNumber("1-8927811341");
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		print(result);
	}

	@Test
	public void testRetrieveSupplyRequestDetail_QA_defect1873()
			throws Exception {
		TestErrors errors = new TestErrors();
		for (String srNumber : Arrays.asList("1-9077385291", "1-8997707221",
				"1-9029360951")) {
			System.out.printf("processing SR# %s\n", srNumber);
			reqContract.setServiceRequestNumber(srNumber);
			RequestResult result = service
					.retrieveSupplyRequestDetail(reqContract);
			{
				List<ServiceRequestOrderLineItem> pendingShipments = result
						.getServiceRequest().getPendingShipments();
				MiscTest.print("[pendingShipments] SR# " + srNumber + ": ",
						pendingShipments);
				for (ServiceRequestOrderLineItem lineItem : LangUtil
						.notNull(pendingShipments)) {
					if (LangUtil.isEmpty(lineItem.getStatus())) {
						String s = String.format("ERROR: SR# %s, %s", srNumber,
								str(lineItem));
						errors.add(new AssertionError("status is empty"), s);
					}
				}
			}

			{
				List<Part> parts = result.getServiceRequest().getParts();
				MiscTest.print("[parts] SR# " + srNumber + ": ", parts);
				for (Part part : LangUtil.notNull(parts)) {
					if (LangUtil.isEmpty(part.getStatus())) {
						String s = String.format("ERROR: SR# %s, %s", srNumber,
								str(part));
						errors.add(new AssertionError("status is empty"), s);
					}
				}
			}
		}
		errors.check();
	}

	@Test
	public void testRetrieveSupplyRequestDetail_QA_defect2348()
			throws Exception {
		// reqContract.setServiceRequestNumber("1-9185218919");
		reqContract.setServiceRequestNumber("1-9234951901");
		RequestResult r = service.retrieveSupplyRequestDetail(reqContract);
		MiscTest.print("[pendingShipments] ", r.getServiceRequest()
				.getPendingShipments());
		MiscTest.print("[shipmentOrderLines] ", r.getServiceRequest()
				.getShipmentOrderLines());
		MiscTest.print("[cancelledParts] ", r.getServiceRequest()
				.getCancelledParts());
		MiscTest.print("[parts] ", r.getServiceRequest().getParts());
	}

	@Test
	public void testRetrieveSupplyRequestDetail_MPSQA() throws Exception {
		// reqContract.setServiceRequestNumber("1-33783029");
		// reqContract.setServiceRequestNumber("1-1912444908"); // todo test
		// data

		reqContract.setServiceRequestNumber("1-12670648055");
		reqContract.setVisibilityRole("Customer");
		reqContract.setStartRecordNumber(0);
		reqContract.setIncrement(0);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		print(result);
	}

	private void printServiceRequestActivity(
			List<ServiceRequestActivity> activities) {
		int i = 0;
		for (ServiceRequestActivity activity : activities) {
			System.out.printf("[%s] activity=%s\n", ++i, str(activity));
		}
	}

	private void printShipmentOrderLines(ServiceRequest sr) {
		if (LangUtil.isEmpty(sr.getShipmentOrderLines())) {
			System.out.printf("[shipmentOrderLine] no data\n");
			return;
		}
		for (ServiceRequestOrderLineItem item : LangUtil.notNull(sr
				.getShipmentOrderLines())) {
			System.out.printf("shipmentOrderLine=%s\n", str(item));
			// System.out.printf("\tserialNumberList=%s\n",
			// item.getSerialNumberList());
		}
	}

	private void print(RequestResult result) {
		if (result == null) {
			System.out.println("<null>");
			return;
		}
		ServiceRequest sr = result.getServiceRequest();
		System.out
				.printf("serviceRequestDate=%s\n", sr.getServiceRequestDate());
		System.out.printf("assert=%s\n", str(sr.getAsset()));
		System.out.printf("serviceAddress=%s\n", str(sr.getServiceAddress()));
		System.out.printf("accountContact=%s\n", str(sr.getRequestType()));
		System.out.printf("secondaryContact=%s\n",
				str(sr.getSecondaryContact()));
		System.out.printf("primaryContact=%s\n", str(sr.getSecondaryContact()));
		System.out.printf("attachments=%s\n", str(sr.getAttachments()));
		printServiceRequestActivity(sr.getActivitywebUpdateActivities());
		// System.out.printf("pendingShippments=%s\n",
		// sr.getPendingShipments());
		// printShipmentOrderLines(sr);
		MiscTest.print("[shipmentOrderLines] ", sr.getShipmentOrderLines());
		MiscTest.print("[pendingShippments] ", sr.getPendingShipments());
		MiscTest.print("[parts] ", sr.getParts());
		MiscTest.print("[cancelledParts] ", sr.getCancelledParts());
		// System.out.println("serviceRequest = " +
		// str(result.getServiceRequest()));
		// MiscTest.showStructure(result.getServiceRequest());
	}

    @Test
    public void testRetrieveRequestList_FilterArea_defect11845() throws Exception {
    	reqListContract.setAssetFavoriteFlag(false);
    	reqListContract.setVendorFlag(false);
    	reqListContract.setShowAllFlag(true);
    	reqListContract.setChangeRequestFlag(true);
    	reqListContract.setMdmLevel("Global");
    	reqListContract.setMdmId("001915172");
    	reqListContract.setNewQueryIndicator(true);
    	reqListContract.setIncrement(40);
    	reqListContract.setStartRecordNumber(0);
    	reqListContract.setSessionHandle(crmSessionHandle);
    	reqListContract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
              "serviceRequest.startDate",  "02/03/2014 05:00:00",
              "serviceRequest.endDate", "02/18/2014 19:44:30",
//              "area", "data",
              "requestType", Arrays.asList("Fleet Management")));  
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("area", "ASCENDING");
		reqListContract.setSortCriteria(sortCriteria );
    	
    	RequestListResult result = service.retrieveRequestList(reqListContract);
    	for (ServiceRequest sr : result.getRequestList()) {
    		System.out.println("Area: " + sr.getArea());
    	}
    	MiscTest.print(result.getRequestList());
    }
    
	@Test
	public void querySupplyRequestDetailDo() throws Exception {
		List<?> list = MiscTest.querySiebel(SupplyRequestDetailDo.class,
				format("[serviceRequestNumber] = '%s'", "1-1912444908"));
		MiscTest.print(list);
	}

	@Test
	public void testRetrieveRequestList_QA() throws Exception {
		reqListContract.setContactId("1-2HOPM1N");
		RequestListResult result = service.retrieveRequestList(reqListContract);
		// MiscTest.print(result.getRequestList());
		for (ServiceRequest sr : result.getRequestList()) {
			System.out.printf("serialNumber=%s, modelNumber=%s\n", sr
					.getAsset().getSerialNumber(), sr.getAsset()
					.getModelNumber()); // under Web Product Model
		}
	}

	@Test
	public void testRetrieveRequestList_QA2() throws Exception {
		// reqListContract.setContactId("1-2HOPM1N");
		reqListContract.setMdmId("236295");
		reqListContract.setMdmLevel("Siebel");
		RequestListResult result = service.retrieveRequestList(reqListContract);
		MiscTest.print(result.getRequestList());
	}

	@Test
	public void testRetrieveRequestList_QA_defect2234() throws Exception {
		reqListContract.setStartRecordNumber(0);
		reqListContract.setIncrement(5);
		reqListContract.setMdmId("023058159");
		reqListContract.setMdmLevel("Global");
		// reqListContract.setChlNodeId("1-ZIFBCQ");
		RequestListResult result = service.retrieveRequestList(reqListContract);
		MiscTest.print(result.getRequestList());
	}

	/**
	 * 
	 * @see com.lexmark.service.impl.real.domain.RequestTypeDoTest#queryLegalIds_QA()
	 * @see com.lexmark.service.impl.real.domain.RequestTypeDoTest#queryLegalIds_QA_2()
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testRetrieveRequestList_QA_Performance() throws Exception {
		reqListContract.setStartRecordNumber(0);
		reqListContract.setIncrement(40);
		reqListContract.setMdmId("14788");
		reqListContract.setMdmLevel("Legal");
		reqListContract.setFilterCriteria((Map<String, Object>) MiscTest
				.newHashMap("serviceRequest.startDate", "11/20/2012 08:00:00",
						"serviceRequest.endDate", "02/20/2013 08:00:00"
				// ,
				// "requestType", Arrays.asList("Consumables Management")
				// ,
				// "requestType", Arrays.asList("Archived")
				));
		long t0 = System.currentTimeMillis();
		try {
			RequestListResult result = service
					.retrieveRequestList(reqListContract);
			MiscTest.print(result.getRequestList());
		} finally {
			System.out.printf("Exec time=%s sec. \n",
					(System.currentTimeMillis() - t0) / 1000.0);
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testRetrieveRequestList_Production_defect5969()
			throws Exception {
		reqListContract.setStartRecordNumber(0);
		reqListContract.setIncrement(5);
		reqListContract.setMdmId("62241");
		reqListContract.setMdmLevel("Legal");
		reqListContract.setNewQueryIndicator(true);
		// reqListContract.setFilterCriteria((Map<String, Object>)
		// MiscTest.newHashMap(
		// "serviceRequest.startDate", "09/02/2012 05:30:00",
		// "serviceRequest.endDate", "11/30/2012 10:59:47"
		// ,
		// "requestType", Arrays.asList("Consumables Management")
		// ,
		// "requestType", Arrays.asList("Archived")
		// ));
		long t0 = System.currentTimeMillis();
		try {
			RequestListResult result = service
					.retrieveRequestList(reqListContract);
			MiscTest.print(result.getRequestList());
			MiscTest.print("totalCount=", result.getTotalCount());
		} finally {
			System.out.printf("Excec time=%s sec. \n",
					(System.currentTimeMillis() - t0) / 1000.0);
		}
	}

	@Test
	public void testRetrieveRequestList_doClass_QA() throws Exception {
		String prefix = "([LXK MPS SR Status]<>'Archived' AND [LXK MPS Type]='MPS')"
				+ " AND ";
		String query = "([LXK R SR Contact Id] ='1-2HOPM1N' OR (([Contact Id] = '1-2HOPM1N'     AND [LXK R SR Contact Id] <> '1-2HOPM1N')) OR (([LXK MPS Alternate Contact Id] = '1-2HOPM1N'     AND [LXK R SR Contact Id] <>'1-2HOPM1N'     AND [Contact Id] <> '1-2HOPM1N')))";
		MiscTest.sampleSiebelQuery(RequestTypeDo.class, prefix + query, 5);
	}

	@Test
	public void testRetrieveRequestList_doClass_QA_2() throws Exception {
		String s = "([LXK MPS SR Status]<>'Archived' AND [LXK MPS Type]='MPS')";
		MiscTest.sampleSiebelQuery(RequestTypeDo.class, s, 50);
	}

	@Test
	public void testRetrieveRequestList_doClass_QA_Defect2234()
			throws Exception {
		// String prefix =
		// "([LXK MPS SR Status]<>'Archived' AND [LXK MPS Type]='MPS')" +
		// " AND ";
		// String query =
		// "([LXK R SR Contact Id] ='1-2HOPM1N' OR (([Contact Id] = '1-2HOPM1N'     AND [LXK R SR Contact Id] <> '1-2HOPM1N')) OR (([LXK MPS Alternate Contact Id] = '1-2HOPM1N'     AND [LXK R SR Contact Id] <>'1-2HOPM1N'     AND [Contact Id] <> '1-2HOPM1N')))";
		String s = "[LXK C Service Request (EAI).LXK MPS Global Ultimate DUNS]='023058159' AND [LXK C Service Request (EAI).LXK MPS Account Level]='Siebel' AND [LXK C Service Request (EAI).LXK MPS SR Status] <> 'Archived' AND [LXK C Service Request (EAI).LXK MPS Type]='MPS' AND [LXK C Service Request (EAI).LXK MPS Created Date] > '08/23/2012' and [LXK C Service Request (EAI).LXK MPS Created Date] < '08/25/2012'";
		MiscTest.sampleSiebelQuery(RequestTypeDo.class, s, 5);
	}

	@Test
	public void testRetrieveRequestList_doClass_QA_Defect3149()
			throws Exception {
		String s = "[serialNumber] = 'CISSP080'";
		// String s = "[requestNumber] = '1-9286573950'";
		MiscTest.sampleSiebelQuery(RequestTypeDo.class, s, 5);
	}

	@Test
	public void testRetrieveRequestList_QA_Defect3149() throws Exception {
		// reqListContract.setContactId("1-1V1KS7");
		reqListContract.setChlNodeId("1-I82SD7");
		reqListContract.setMdmLevel("Siebel");
		reqListContract.setMdmId("1-I82SD7");
		// reqListContract.setMdmId("1-49U0760");
		RequestListResult result = service.retrieveRequestList(reqListContract);
		MiscTest.print(result.getRequestList(), "asset.serialNumber",
				"asset.modelNumber");

		// for (ServiceRequest sr : result.getRequestList()) {
		// System.out.printf("serialNumber=%s, modelNumber=%s\n",
		// sr.getAsset().getSerialNumber(), sr.getAsset().getModelNumber());
		// //under Web Product Model
		// }
	}

	@Test
	public void testRetrieveRequestList_QA_Defect3502() throws Exception {
		reqListContract.setStartRecordNumber(0);
		reqListContract.setIncrement(40);
		reqListContract.setMdmId("1-A7JZ-183");
		reqListContract.setMdmLevel("Siebel");
		reqListContract.setVendorAccountId("1-A7JZ-183");
		reqListContract.setVendorFlag(true);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);

		reqListContract.setFilterCriteria((Map<String, Object>) MiscTest
				.newHashMap("serviceRequest.startDate", "09/02/2012 05:30:00",
						"serviceRequest.endDate", "10/02/2012 10:59:47",
						"requestType", Arrays.asList("Consumables Management")
				// ,
				// "requestType", Arrays.asList("Archived")
				));
		reqListContract.setSearchCriteria((Map<String, Object>) MiscTest
				.newHashMap("serviceRequestNumber", "DESCENDING"));

		RequestListResult result = service.retrieveRequestList(reqListContract);
		MiscTest.print(result.getRequestList(), "serviceRequestNumber",
				"requestType", "serviceRequestType");
	}

	/**
	 * 
	 * @see com.lexmark.service.impl.real.domain.RequestTypeDoTest#queryRequestTypeDoTest()
	 */
	@Test
	public void testRetrieveRequestList_QA_Defect6606() throws Exception {
		reqListContract.setStartRecordNumber(0);
		reqListContract.setIncrement(40);
		reqListContract.setMdmId("65850");
		reqListContract.setMdmLevel("Legal");
		reqListContract.setContactId("1-52X86KR");

		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(true);
		reqListContract.setShowAllFlag(false);
		reqListContract.setNewQueryIndicator(true);

		reqListContract.setFilterCriteria((Map<String, Object>) MiscTest
				.newHashMap("serviceRequest.startDate", "02/23/2013 09:00:00",
						"serviceRequest.endDate", "03/25/2013 21:32:59",
						"requestType", Arrays.asList("Consumables Management",
								"Fleet Management", "BreakFix")
				// ,
				// "requestType", Arrays.asList("Archived")
				));
		reqListContract.setSearchCriteria((Map<String, Object>) MiscTest
				.newHashMap("asset.serialNumber", "ASCENDING"));

		RequestListResult result = service.retrieveRequestList(reqListContract);
		MiscTest.print(result.getRequestList(), "serviceRequestNumber",
				"requestType", "serviceRequestType");
	}

	@Test
	public void testRetrieveRequestList_QA_Defect6606_query() throws Exception {
		MiscTest.sampleSiebelQuery(
				// RequestTypeDo.class,
				RequestTypeFavoriteAssetDo.class,
				"([LXK MPS SR Status]<>'Archived' AND [LXK MPS Type]='MPS') "
				// +
				// "AND [LXK MPS Legal Entity ID #] = '65850' AND [LXK MPS Account Level] = 'Siebel'"
				// + " AND EXISTS([LXK MPS Asset Contact Id] = '1-52X86KR')"
				// + " AND ([Created] >= '02/23/2013 09:00:00')"
				// + " AND ([Created] <= '03/25/2013 21:32:59')"
				// +
				// " AND ([LXK MPS SR Type] = 'Consumables Management' OR [LXK MPS SR Type] = 'Fleet Management'"
				// +
				// " OR ([LXK MPS SR Type]='Diagnosis' OR [LXK MPS SR Type]='Service' OR [LXK MPS SR Type]='Dispatch' OR [LXK MPS SR Type]='Presales' OR [LXK MPS SR Type]='Complaint - Formal' OR [LXK MPS SR Type]='Complaint - Informal' OR [LXK MPS SR Type]='Customer Does Not Wish to Pay' OR [LXK MPS SR Type]='Dealer Location Queries' OR [LXK MPS SR Type]='Email' OR [LXK MPS SR Type]='Entitlement Check' OR [LXK MPS SR Type]='Install/De-Install' OR [LXK MPS SR Type]='LXK Internet Site Query' OR [LXK MPS SR Type]='Lack of Customer Info' OR [LXK MPS SR Type]='Non-supported Product' OR [LXK MPS SR Type]='OEM Import' OR [LXK MPS SR Type]='Other LXK Dept (Sales, Office)' OR [LXK MPS SR Type]='Other TSC Dept (CPD or PSSD)' OR [LXK MPS SR Type]='Schedule Callback' OR [LXK MPS SR Type]='Web Account' OR [LXK MPS SR Type]='CFL Store' OR [LXK MPS SR Type]='Others'))"
				, 10);
	}

	/**
	 * 
	 * @see com.lexmark.service.impl.real.domain.RequestTypeDoTest#queryRequestTypeDoTest()
	 */
	@Test
	public void testRetrieveRequestList_QA_Defect6761() throws Exception {
		reqListContract.setStartRecordNumber(0);
		reqListContract.setIncrement(40);
		reqListContract.setMdmId("327376653");
		reqListContract.setMdmLevel("Global");
		// reqListContract.setContactId("1-52X86KR");

		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(false);
		reqListContract.setNewQueryIndicator(true);

		reqListContract.setFilterCriteria((Map<String, Object>) MiscTest
				.newHashMap(
				// "serviceRequest.startDate", "02/23/2013 09:00:00",
				// "serviceRequest.endDate", "03/25/2013 21:32:59"
				// ,
				// "requestType", Arrays.asList("Consumables Management",
				// "Fleet Management", "BreakFix")
						"serviceRequestNumber", "1-11087511811"
				// "asset.serialNumber", "7558100457137"
				// ,
				// "requestType", Arrays.asList("Archived")
				));
		// reqListContract.setSearchCriteria((Map<String, Object>)
		// MiscTest.newHashMap("asset.serialNumber", "ASCENDING"));

		long t0 = System.currentTimeMillis();
		try {
			RequestListResult result = service
					.retrieveRequestList(reqListContract);
			MiscTest.print(result.getRequestList(), "serviceRequestNumber",
					"asset.serialNumber", "requestType", "serviceRequestType");
		} finally {
			System.out.printf("Exec time=%s sec. \n",
					(System.currentTimeMillis() - t0) / 1000.0);
		}
	}

	@Test
	public void testRetrieveRequestList_QA_Defect3502_logTest()
			throws Exception {
		reqListContract.setStartRecordNumber(0);
		reqListContract.setIncrement(40);
		// reqListContract.setMdmId("1-A7JZ-183");
		reqListContract.setMdmLevel("Siebel");
		reqListContract.setVendorAccountId("1-A7JZ-183");
		reqListContract.setVendorFlag(true);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);

		reqListContract.setFilterCriteria((Map<String, Object>) MiscTest
				.newHashMap("serviceRequest.startDate", "09/02/2012 05:30:00",
						"serviceRequest.endDate", "10/02/2012 10:59:47",
						"requestType",
						Arrays.asList("Consumables Management", "Archived")
				// ,
				// "requestType", Arrays.asList("Archived")
				));
		reqListContract.setSearchCriteria((Map<String, Object>) MiscTest
				.newHashMap("serviceRequestNumber", "DESCENDING"));

		RequestListResult result = service.retrieveRequestList(reqListContract);
		MiscTest.print(result.getRequestList(), "serviceRequestNumber",
				"requestType", "serviceRequestType");
	}

	@Test
	public void testRetrieveSupplyRequestDetail_3149() throws Exception {
		reqContract.setServiceRequestNumber("1-9286573950");
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		System.out.println(MiscTest.str(result.getServiceRequest().getAsset()));
	}

	@Test
	public void testRetrieveSupplyRequestDetail_3753() throws Exception {
		reqContract.setServiceRequestNumber("1-9991908871");
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		ServiceRequest sr = result.getServiceRequest();
		System.out.println(MiscTest.str(sr));
		MiscTest.print(sr.getParts());
	}

	@Test
	public void testRetrieveRequestList_fav_QA() throws Exception {
		// reqListContract.setContactId("1-1TTZ1K");
		reqListContract.setMdmId("123");
		reqListContract.setMdmLevel("Siebel");
		// reqListContract.setContactId("1-2HOPM1N");
		// reqListContract.setAssetFavoriteFlag(true);
		RequestListResult result = service.retrieveRequestList(reqListContract);
		System.out.println(result.getTotalCount());
		MiscTest.print(result.getRequestList());
	}

	@Test
	public void testRetrieveRequestList_fav_doClass_QA() throws Exception {
		String prefix = "([LXK MPS SR Status]<>'Archived' AND [LXK MPS Type]='MPS')"
				+ " AND ";
		// String prefix = "";
		String query = "[Account Id] = '123' AND [LXK MPS Account Level] = 'Siebel' AND EXISTS([LXK MPS Asset Contact Id] = '1-1TTZ1K')";
		MiscTest.sampleSiebelQuery(RequestTypeFavoriteAssetDo.class, prefix
				+ query, 5);
	}

	@Test
	public void queryRequestTypeDoWithServiceRequestType() throws Exception {
		MiscTest.sampleSiebelQuery(RequestTypeDo.class,
				"[serviceRequestType] <> ''", 5);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testRetrieveRequestList_QA_withFilter() throws Exception {
		Map<String, Object> filter = new HashMap<String, Object>();
		// filter.put("requestType", "mock-Diagnosis");
		filter.put("requestType", Arrays.asList("Diagnosis"));
		reqListContract.setIncrement(5);
		reqListContract.setShowAllFlag(true);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("023058159");
		reqListContract.setSortCriteria((Map<String, Object>) MiscTest
				.newHashMap("serviceRequestDate", "ASCENDING"));
		reqListContract.setFilterCriteria((Map<String, Object>) MiscTest
				.newHashMap("serviceRequest.startDate", "09/03/2012 05:30:00",
						"serviceRequest.endDate", "10/03/2012 05:49:59",
						"requestType", Arrays.asList("Consumables Management",
								"Fleet Management", "BreakFix"), "county",
						"167"));
		// reqListContract.setContactId("1-2HOPM1N");
		RequestListResult result = service.retrieveRequestList(reqListContract);

		for (ServiceRequest serReq : result.getRequestList()) {
			System.out.println("Store front name: "
					+ serReq.getServiceAddress().getStoreFrontName());
		}

		MiscTest.print(result.getRequestList());
	}

	@Test
	public void testRetrieveRequestList_requestType_filter_QA()
			throws Exception {
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("requestType",
				Arrays.asList("Diagnosis", "mock-requestType"));
		reqListContract.setFilterCriteria(filter);
		reqListContract.setContactId("1-2HOPM1N");
		RequestListResult result = service.retrieveRequestList(reqListContract);
		assertEquals(2, result.getRequestList().size());
		MiscTest.print(result.getRequestList());

		filter.put("requestType", Arrays.asList("requestType-non-existing",
				"requestType-non-existing-2"));
		reqListContract.setFilterCriteria(filter);
		result = service.retrieveRequestList(reqListContract);
		assertEquals(0, result.getRequestList().size());
		MiscTest.print(result.getRequestList());
	}

	@Test
	public void testRetrieveRequestList() throws Exception {
		// reqListContract.setContactId("1-2HOPM1N");
		// "1-3S-445", "Siebel"
		reqListContract.setMdmId("1-3S-445");
		reqListContract.setMdmLevel("Siebel");
		RequestListResult result = service.retrieveRequestList(reqListContract);
		MiscTest.print(result.getRequestList());
	}

	@Test
	public void testStatusOnRetrieveSupplyRequestDetail_QA() throws Exception {
		reqContract.setServiceRequestNumber("1-8877152821");
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		ServiceRequest sr = result.getServiceRequest();
		for (Part p : sr.getParts()) {
			System.out.println("Device type: " + p.getDeviceType());
			for (BundlePart bundlePart : p.getBundlePartList()) {
				System.out.println("Order quantity: " + bundlePart.getQty());
				// System.out.println("Canceled quantity: " +
				// bundlePart.getCanceledQty());
				System.out
						.println("Part number: " + bundlePart.getPartNumber());
				System.out.println("Description: "
						+ bundlePart.getDescription());
			}
		}
		System.out.println("Status="
				+ result.getServiceRequest().getServiceRequestStatus());
	}

	@Test
	public void testStatusOnRetrieveRequestList_QA() throws Exception {
		reqListContract.setContactId("1-1TTZ1K");
		reqListContract.setMdmId("123");
		reqListContract.setMdmLevel("Siebel");
		RequestListResult result = service.retrieveRequestList(reqListContract);
		for (ServiceRequest sr : result.getRequestList()) {
			if (sr.getServiceRequestNumber() != null
					&& sr.getServiceRequestNumber().equals("1-8877152821")) {
				System.out.println("Status=" + sr.getServiceRequestStatus());
			}
		}

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("Size: " + list.size());
		System.out.println("Total: " + result.getTotalCount());

		for (ServiceRequest serviceRequest : list) {
			System.out.println(serviceRequest.getArea().getValue());
		}

		System.out.println("END");
	}

	@Test
	public void queryRequestTypeDo() throws Exception {
		List<RequestTypeDo> result = MiscTest.sampleSiebelQuery(
				RequestTypeDo.class, "[contactId] <> ''", 5);
		for (RequestTypeDo rtDo : result) {
			System.out.printf("id=%s, contactId=%s\n", rtDo.getId(),
					rtDo.getContactId());
		}
	}

	@Test
	public void queryRetrieveRequestListSearchSpec_QA() throws Exception {
		MiscTest.sampleSiebelQuery(
				RequestTypeDo.class,
				"[LXK R SR Contact Id] ='1-2HOPM1N' OR (([Contact Id] = '1-2HOPM1N'     AND [LXK R SR Contact Id] <> '1-2HOPM1N')) OR (([LXK MPS Alternate Contact Id] = '1-2HOPM1N'     AND [LXK R SR Contact Id] <>'1-2HOPM1N'     AND [Contact Id] <> '1-2HOPM1N'))",
				5);
		// MiscTest.sampleSiebelQuery(SupplyRequestDetailDo.class,
		// "[LXK R SR Contact Id] ='1-K3V3K' OR (([Contact Id] = '1-K3V3K'     AND [LXK R SR Contact Id] <> '1-K3V3K')) OR (([LXK SD SR Alternate Contact Id] = '1-K3V3K'     AND [LXK R SR Contact Id] <>'1-K3V3K'     AND [Contact Id] <> '1-K3V3K'))"
		// , 5);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testRetrieveRequestList_QA_Defect2443() throws Exception {
		// reqListContract.setMdmId("1-1G0U13D");
		reqListContract.setMdmId("1-ORMLI9");
		reqListContract.setMdmLevel("Siebel");
		// reqListContract.setVendorAccountId("1-1G0U13D");
		reqListContract.setStartRecordNumber(0);
		reqListContract.setIncrement(40);
		reqListContract.setSortCriteria((Map<String, Object>) MiscTest
				.newHashMap("serviceRequestNumber", "ASCENDING"));
		reqListContract.setFilterCriteria((Map<String, Object>) MiscTest
				.newHashMap("serviceRequest.endDate", "09/10/2012 12:13:32",
						"serviceRequest.startDate", "08/11/2012 05:30:00",
						"requestType", Arrays.asList("Consumables Management"),
						"serviceRequestNumber", "1-9169955541"));

		RequestListResult result = service.retrieveRequestList(reqListContract);

		MiscTest.print(result.getRequestList());
		for (ServiceRequest sr : result.getRequestList()) {
			if ("1-9169955541".equals(sr.getServiceRequestNumber())) {
				MiscTest.print("[pendingShipments] ", sr.getPendingShipments());
				MiscTest.print("[shipmentOrderLines] ",
						sr.getShipmentOrderLines());
				MiscTest.print("[returnOrderLines] ", sr.getReturnOrderLines());
				MiscTest.print("[parts] ", sr.getParts(), "partNumber",
						"description", "partType", "orderQuantity",
						"cancelledQuantity", "status");
				MiscTest.print("[cancelledParts] ", sr.getCancelledParts());
				MiscTest.print("[statusType] ", sr.getStatusType());
			}
		}
	}

	@Test
	public void testRetrieveSupplyRequestDetail_QA_Defect2443()
			throws Exception {
		// reqContract.setServiceRequestNumber("1-9259812621");
		reqContract.setServiceRequestNumber("1-9169955541");
		reqContract.setVisibilityRole("Partner");
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		System.out.println(str(result.getServiceRequest()));
		MiscTest.print("[pendingShipments] ", result.getServiceRequest()
				.getPendingShipments());
		MiscTest.print("[shipmentOrderLines] ", result.getServiceRequest()
				.getShipmentOrderLines());
		MiscTest.print("[returnOrderLines] ", result.getServiceRequest()
				.getReturnOrderLines());
		// MiscTest.print("[parts] ", result.getServiceRequest().getParts());
		MiscTest.print("[parts] ", result.getServiceRequest().getParts(),
				"partNumber", "description", "partType", "orderQuantity",
				"cancelledQuantity", "status");
		MiscTest.print("[cancelledParts] ", result.getServiceRequest()
				.getCancelledParts());
	}

	@Test
	public void queryRequestTypeDo_QA_Defect2443() {
		// MiscTest.sampleSiebelQuery(RequestTypeDo.class,
		// "([LXK MPS SR Status]<>'Archived' AND [LXK MPS Type]='MPS') AND (([Account Id] = '1-1G0U13D') OR ([LXK MPS Agreement Account Id] = '1-1G0U13D'))",
		// 5);
		MiscTest.sampleSiebelQuery(RequestTypeDo.class,
				"([LXK MPS SR Status]<>'Archived' AND [LXK MPS Type]='MPS') ",
				5);
	}

	@Test
	public void testRetrieveRequestLocations_QA_Defect2171() throws Exception {
		String mdmId = "023058159";
		String mdmLevel = "Global";
		LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
		contract.setSessionHandle(this.crmSessionHandle);
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
		long t0 = System.currentTimeMillis();
		try {
			RequestLocationsResult r = service
					.retrieveRequestLocations(contract);
			for (GenericAddress ga : r.getAddressList()) {
				System.out.printf("country=%s\n", ga.getCountry());
			}
		} finally {
			System.out.printf("Excec time=%s sec. \n",
					(System.currentTimeMillis() - t0) / 1000.0);
		}
	}

	@Test
	public void testRetrieveRequestLocations_QA_chlNodeId() throws Exception {
		LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
		contract.setSessionHandle(this.crmSessionHandle);
		contract.setChlNodeId("1-3F2FR9");
		contract.setMdmId("mock-mdmId");
		contract.setMdmLevel("mock-mdmLevel");
		long t0 = System.currentTimeMillis();
		try {
			RequestLocationsResult r = service
					.retrieveRequestLocations(contract);
			MiscTest.print(r.getAddressList(), "country");
		} finally {
			System.out.printf("Excec time=%s sec. \n",
					(System.currentTimeMillis() - t0) / 1000.0);
		}
	}

	@Test
	public void testRetrieveRequestLocations_DEV_chlNodeId() throws Exception {
		LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
		contract.setSessionHandle(this.crmSessionHandle);
		// contract.setChlNodeId("mock-chlNodeId");
		// contract.setChlNodeId("1-40-1289"); // without parentChain
		contract.setChlNodeId("1-72NZN"); // with parentChain == id
		contract.setMdmId("mock-mdmId");
		contract.setMdmLevel("mock-mdmLevel");
		long t0 = System.currentTimeMillis();
		try {
			RequestLocationsResult r = service
					.retrieveRequestLocations(contract);
			MiscTest.print(r.getAddressList(), "country");
		} finally {
			System.out.printf("Excec time=%s sec. \n",
					(System.currentTimeMillis() - t0) / 1000.0);
		}
	}

	@Test
	public void testRetrieveRequestLocations_DEV_chlNodeId_noParentChain()
			throws Exception {
		LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
		contract.setSessionHandle(this.crmSessionHandle);
		// contract.setChlNodeId("mock-chlNodeId");
		contract.setChlNodeId("1-40-1289"); // without parentChain
		contract.setMdmId("mock-mdmId");
		contract.setMdmLevel("mock-mdmLevel");
		contract.setStartRecordNumber(0);
		contract.setIncrement(5);
		long t0 = System.currentTimeMillis();
		try {
			RequestLocationsResult r = service
					.retrieveRequestLocations(contract);
			MiscTest.print(r.getAddressList(), "country");
		} finally {
			System.out.printf("Excec time=%s sec. \n",
					(System.currentTimeMillis() - t0) / 1000.0);
		}
	}

	@Test
	public void testRetrieveRequestLocations_DEV_chlNodeId_noChl()
			throws Exception {
		LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
		contract.setSessionHandle(this.crmSessionHandle);
		contract.setChlNodeId("mock-chlNodeId");
		// contract.setChlNodeId("1-40-1289"); // without parentChain
		contract.setMdmId("mock-mdmId");
		contract.setMdmLevel("mock-mdmLevel");
		contract.setStartRecordNumber(0);
		contract.setIncrement(5);
		long t0 = System.currentTimeMillis();
		try {
			RequestLocationsResult r = service
					.retrieveRequestLocations(contract);
			MiscTest.print(r.getAddressList(), "country");
		} finally {
			System.out.printf("Excec time=%s sec. \n",
					(System.currentTimeMillis() - t0) / 1000.0);
		}
	}

	@Test
	public void testRetrieveRequestLocations_DEV_chlNodeId_query()
			throws Exception {
		// RequestLocationDo
		// MiscTest.sampleSiebelQuery(RequestLocationDo.class,
		// "[LXK MPS CHL Id] LIKE '*1*'", 10);
		MiscTest.sampleSiebelQuery(RequestLocationDo.class,
				"len([LXK MPS CHL Id]) < 10", 10);
	}

	@Test
	public void testRetrieveRequestLocations_QA_dateFilterTest()
			throws Exception {
		String mdmId = "023058159";
		String mdmLevel = "Global";
		LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
		contract.setSessionHandle(this.crmSessionHandle);
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
		contract.setStartRecordNumber(0);
		contract.setIncrement(10); // is not implemented
		contract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
				"startDate", "08/11/2012 05:30:00", "endDate",
				"09/11/2012 05:30:00"));
		RequestLocationsResult r = service.retrieveRequestLocations(contract);
		MiscTest.print("[addressList] ", r.getAddressList());
	}

	@Test
	public void testRetrieveRequestLocations_QA_dateFilterTest_withVendorFlag()
			throws Exception {
		String mdmId = "136592842";
		String mdmLevel = "Global";
		LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
		contract.setSessionHandle(this.crmSessionHandle);
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
		contract.setStartRecordNumber(0);
		contract.setIncrement(10); // is not implemented
		contract.setVendorFlag(true);
		contract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
				"startDate", "08/11/2011 05:30:00", "endDate",
				"09/11/2012 05:30:00"));
		RequestLocationsResult r = service.retrieveRequestLocations(contract);
		MiscTest.print("[addressList] ", r.getAddressList());
	}
	
	@Test
	public void testRetrieveRequestLocations_QA_defect18193()	throws Exception {
		String mdmId = "023058159";
		String mdmLevel = "Global";
		LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
		contract.setSessionHandle(this.crmSessionHandle);
		contract.setMdmId(mdmId);
		contract.setMdmLevel(mdmLevel);
		contract.setEntitlementEndDate("06/19/2015");
		contract.setStartRecordNumber(0);
		contract.setIncrement(0);
		contract.setNewQueryIndicator(false);
		contract.setVendorFlag(false);
		contract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
				"serviceRequest.startDate", "05/20/2015 05:30:00", "serviceRequest.endDate",
				"06/19/2015 15:59:38"));
		RequestLocationsResult r = service.retrieveRequestLocations(contract);
		MiscTest.print("[addressList] ", r.getAddressList());
	}

	@Test
	public void queryRequestLocationDo() throws Exception {
		MiscTest.sampleSiebelQuery(
				RequestLocationDo.class,
				"(([LXK MPS Vendor Account Domestic DUNS Number] <> '' AND [LXK MPS Vendor Account Level] = 'Siebel'))",
				10);
	}

	@Test
	public void testRetrieveSupplyRequestDetail_QA_Defect2777()
			throws Exception {
		reqContract.setServiceRequestNumber("1-9436909181");
		// reqContract.setVisibilityRole("Partner");
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		ServiceRequest sr = result.getServiceRequest();
		System.out.println(str(sr));
		MiscTest.print("[activitywebUpdateActivities] ",
				sr.getActivitywebUpdateActivities());

		MiscTest.print("[pendingShipments] ", result.getServiceRequest()
				.getPendingShipments());
		MiscTest.print("[shipmentOrderLines] ", result.getServiceRequest()
				.getShipmentOrderLines());
		MiscTest.print("[returnOrderLines] ", result.getServiceRequest()
				.getReturnOrderLines());
		// MiscTest.print("[parts] ", result.getServiceRequest().getParts());
		MiscTest.print("[parts] ", result.getServiceRequest().getParts(),
				"partNumber", "description", "partType", "orderQuantity",
				"cancelledQuantity", "status");
		MiscTest.print("[cancelledParts] ", result.getServiceRequest()
				.getCancelledParts());
	}

	@Test
	public void testRetrieveSupplyRequestDetail_QA_Defect2834()
			throws Exception {
		reqContract.setServiceRequestNumber("1-9187103271");
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		System.out.println(result.getServiceRequest().getParts() == null);
		ServiceRequest sr = result.getServiceRequest();

		MiscTest.print("[pendingShipments] ", sr.getPendingShipments());
		MiscTest.print("[shipmentOrderLines] ", sr.getShipmentOrderLines());
	}

	@Test
	public void testRetrieveSupplyRequestDetail_QA_Defect2919()
			throws Exception {
		reqContract.setServiceRequestNumber("1-9286574143");
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		ServiceRequest sr = result.getServiceRequest();
		System.out.println(str(sr));

		MiscTest.print("[pendingShipments] ", sr.getPendingShipments());
		MiscTest.print("[shipmentOrderLines] ", sr.getShipmentOrderLines());
		MiscTest.print("[parts] ", sr.getParts());
		MiscTest.print("[cancelledParts] ", sr.getCancelledParts());
	}

	@Test
	public void testRetrieveSupplyRequestDetail_QA_Defect1877()
			throws Exception {
		reqContract.setServiceRequestNumber("1-9632047841");
		reqContract.setVisibilityRole("Customer");
		reqContract.setNewQueryIndicator(false);
		reqContract.setStartRecordNumber(0);
		reqContract.setIncrement(0);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		ServiceRequest sr = result.getServiceRequest();

		MiscTest.print("[pendingShipments] ", sr.getPendingShipments());
		MiscTest.print("[shipmentOrderLines] ", sr.getShipmentOrderLines());
		MiscTest.print("[attachments]", sr.getAttachments());
	}

	@Test
	public void testRetrieveSupplyRequestDetail_QA_Defect3302()
			throws Exception {
		reqContract.setServiceRequestNumber("1-9262708328");
		// reqContract.setVisibilityRole("Customer");
		reqContract.setNewQueryIndicator(false);
		reqContract.setStartRecordNumber(0);
		reqContract.setIncrement(40);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		ServiceRequest sr = result.getServiceRequest();
		System.out.println(str(sr));
		MiscTest.print("[pendingShipments] ", sr.getPendingShipments());
		MiscTest.print("[parts] ", sr.getParts(), "description", "partNumber",
				"partName", "partType", "status", "orderQuantity");

		MiscTest.print("[shipmentOrderLines] ", sr.getShipmentOrderLines(),
				"productDescription", "partnumber", "partName", "partType",
				"status", "shippedQuantity");
		MiscTest.print("[attachments] ", sr.getAttachments());
	}

	@Test
	public void testRetrieveSupplyRequestDetail_QA_Defect3302_query()
			throws Exception {
		// MiscTest.sampleSiebelQuery(SupplyRequestDetailDo.class,
		// "[orderItems] <> ''", 5, 0);
		MiscTest.sampleSiebelQuery(SupplyRequestDetailDo.class,
				"[SR Commit Time] <> ''", 5, 0);

	}

	@Test
	public void testRetrieveSupplyRequestDetail_QA_Defect3485()
			throws Exception {
		reqContract.setServiceRequestNumber("1-9728924161");
		// reqContract.setServiceRequestNumber("1-9983769061");
		// reqContract.setVisibilityRole("Customer");
		reqContract.setNewQueryIndicator(false);
		reqContract.setStartRecordNumber(0);
		reqContract.setIncrement(40);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		ServiceRequest sr = result.getServiceRequest();
		MiscTest.print("[pendingShipments] ", sr.getPendingShipments(),
				"partnumber", "partName", "productDescription");
		MiscTest.print("[cancelledParts] ", sr.getCancelledParts(),
				"partNumber", "partName", "description");
		// MiscTest.print("[shipmentOrderLines] ", sr.getShipmentOrderLines(),
		// "productDescription", "partnumber", "partName", "partType", "status",
		// "shippedQuantity");
		// MiscTest.print("[parts] ", sr.getParts(), "description",
		// "partNumber", "partName", "partType", "status", "orderQuantity");
	}

	@Test
	public void testRetrieveSupplyRequestDetail_QA_Defect3485_2()
			throws Exception {
		reqContract.setServiceRequestNumber("1-10030491881");
		// reqContract.setServiceRequestNumber("1-9983769061");
		// reqContract.setVisibilityRole("Customer");
		reqContract.setNewQueryIndicator(false);
		reqContract.setStartRecordNumber(0);
		reqContract.setIncrement(40);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		ServiceRequest sr = result.getServiceRequest();
		MiscTest.print("[pendingShipments] ", sr.getPendingShipments(),
				"partnumber", "partName", "productDescription", "status");
		MiscTest.print("[cancelledParts] ", sr.getCancelledParts(),
				"partNumber", "partName", "description");
		MiscTest.print("[shipmentOrderLines] ", sr.getShipmentOrderLines(),
				"productDescription", "partnumber", "partName", "partType",
				"status", "shippedQuantity");
		MiscTest.print("[parts] ", sr.getParts(), "description", "partNumber",
				"partName", "partType", "status", "orderQuantity");
	}

	/**
	 * @see AmindPartnerOrderServiceTest#querySupplyRequestDetailDo()
	 */
	@Test
	public void testRetrieveSupplyRequestDetail_QA_Defect4026()
			throws Exception {
		reqContract.setServiceRequestNumber("1-9959130052");
		// reqContract.setVisibilityRole("Customer");
		reqContract.setNewQueryIndicator(false);
		reqContract.setStartRecordNumber(0);
		reqContract.setIncrement(40);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		System.out.println(result.getServiceRequest().getParts() == null);
		ServiceRequest sr = result.getServiceRequest();
		System.out.println("storeFrontName = "
				+ sr.getServiceAddress().getStoreFrontName());
		System.out.println("addressLine2 = "
				+ sr.getServiceAddress().getAddressLine2());
	}

	/**
	 * 
	 * @see AmindPartnerOrderServiceTest#testRetrieveOrderDetail_QA_Defect4212()
	 */
	@Test
	public void testRetrieveSupplyRequestDetail_QA_Defect4212()
			throws Exception {
		reqContract.setServiceRequestNumber("1-9929687226");
		// reqContract.setVisibilityRole("Customer");
		reqContract.setNewQueryIndicator(false);
		reqContract.setStartRecordNumber(0);
		reqContract.setIncrement(40);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		ServiceRequest sr = result.getServiceRequest();
		System.out.println(str(sr));
		MiscTest.print("[shipmentOrderLines] ", sr.getShipmentOrderLines(),
				"partnumber", "partName", "shippedQuantity", "status",
				"fulfillmentType");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testRetrieveRequestList_QA_Defect4247_sortingIssue()
			throws Exception {
		Map<String, Object> filter = new HashMap<String, Object>();
		// filter.put("requestType", "mock-Diagnosis");
		filter.put("requestType", Arrays.asList("Diagnosis"));
		reqListContract.setIncrement(5);
		reqListContract.setShowAllFlag(true);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("023058159");
		reqListContract.setSortCriteria((Map<String, Object>) MiscTest
				.newHashMap("serviceRequestDate", "ASCENDING"));
		reqListContract.setFilterCriteria((Map<String, Object>) MiscTest
				.newHashMap("serviceRequest.startDate", "09/19/2012 09:30:00",
						"serviceRequest.endDate", "10/19/2012 09:11:15",
						"requestType", Arrays.asList("BreakFix")));

		reqListContract.setSortCriteria((Map<String, Object>) MiscTest
				.newHashMap(
				// "serviceRequestStatusDate", "ASCENDING",
						"serviceRequestDate", "ASCENDING"
				// "serviceRequestDate", "DESCENDING"
				));

		RequestListResult result = service.retrieveRequestList(reqListContract);
		MiscTest.print(result.getRequestList(), "serviceRequestDate",
				"serviceRequestStatusDate", "serviceRequestNumber");
		SortUtil.checkSortOrder(result.getRequestList(), new SortCriteria(
				"serviceRequestDate", true));
	}

	@Test
	public void testRetrieveSupplyRequestDetail_QA_Defect4277()
			throws Exception {
		reqContract.setServiceRequestNumber("1-9259337772");
		// reqContract.setVisibilityRole("Customer");
		reqContract.setNewQueryIndicator(false);
		reqContract.setStartRecordNumber(0);
		reqContract.setIncrement(40);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		System.out.println(result.getServiceRequest().getParts() == null);
		ServiceRequest sr = result.getServiceRequest();
		MiscTest.print("[pendingShipments] ", sr.getPendingShipments(),
				"partnumber", "partName", "productDescription");
		MiscTest.print("[cancelledParts] ", sr.getCancelledParts(),
				"partNumber", "partName", "description");
		MiscTest.print("[shipmentOrderLines] ", sr.getShipmentOrderLines(),
				"productDescription", "partnumber", "partName", "partType",
				"status", "shippedQuantity");
		MiscTest.print("[parts] ", sr.getParts(), "description", "partNumber",
				"partName", "partType", "status", "orderQuantity");
	}

	@Test
	public void testRetrieveSupplyRequestDetail_QA_Defect4515()
			throws Exception {
		reqContract.setServiceRequestNumber("1-9983769631");
		// reqContract.setVisibilityRole("Customer");
		reqContract.setNewQueryIndicator(false);
		reqContract.setStartRecordNumber(0);
		reqContract.setIncrement(40);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		System.out.println(result.getServiceRequest().getParts() == null);
		ServiceRequest sr = result.getServiceRequest();
		MiscTest.print("", str(sr));
		MiscTest.print("[emailActivities] ", sr.getEmailActivities());
		MiscTest.print("[activitywebUpdateActivities] ",
				sr.getActivitywebUpdateActivities());
		MiscTest.print("[servicewebUpdateActivities] ",
				sr.getServicewebUpdateActivities());
	}

	@Test
	public void testRetrieveSupplyRequestDetail_QA_shipmentOrderLines_fulfillmentType()
			throws Exception {
		// reqContract.setServiceRequestNumber("1-9187103271");
		reqContract.setServiceRequestNumber("1-9929687226");
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		ServiceRequest sr = result.getServiceRequest();
		MiscTest.print("[shipmentOrderLines] ", sr.getShipmentOrderLines(),
				"partnumber", "fulfillmentType");
	}

	@Test
	public void testRetrieveSupplyRequestDetail_QA_2() throws Exception {
		// reqContract.setServiceRequestNumber("1-9187103271");
		reqContract.setServiceRequestNumber("1-9991909571");
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		ServiceRequest sr = result.getServiceRequest();
		for (Part p : sr.getParts()) {
			System.out.println(p.getDeviceType());
		}
		MiscTest.print("[shipmentOrderLines] ", sr.getShipmentOrderLines(),
				"partnumber", "fulfillmentType");
	}

	@Test
	public void testRetrieveSupplyRequestDetail_QA_defect3564()
			throws Exception {
		reqContract.setServiceRequestNumber("1-9944153771");
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		ServiceRequest sr = result.getServiceRequest();
		MiscTest.print("serviceRequestStatus = ", sr.getServiceRequestStatus());
		MiscTest.print("primaryContact = ", str(sr.getPrimaryContact()));
	}

	@Test
	public void testRetrieveSupplyRequestDetail_Production_defect5750()
			throws Exception {
		reqContract.setServiceRequestNumber("1-10333604351");
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		ServiceRequest sr = result.getServiceRequest();
		MiscTest.print("asset.assetId = ", sr.getAsset().getAssetId());
		MiscTest.print("pageCounts = ", sr.getPageCounts());
	}

	@Test
	public void testRetrieveSupplyRequestDetail_QA_defect6320()
			throws Exception {
		// reqContract.setServiceRequestNumber("1-10938262261");
		reqContract.setServiceRequestNumber("1-10937884321");
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		ServiceRequest sr = result.getServiceRequest();
		System.out.printf("serviceRequest=%s\n\n", str(sr));

		MiscTest.print("serviceAddress = ", str(sr.getServiceAddress()));
	}

	@Test
	public void testRetrieveSupplyRequestDetail_QA_defect6320_query()
			throws Exception {
		long t0 = System.currentTimeMillis();
		try {
			List<SupplyRequestDetailDo> r = MiscTest
					.sampleSiebelQuery(
							SupplyRequestDetailDo.class,
							"[SR Number] = '1-10938262261' OR [SR Number] = '1-10937884321'",
							5);
			// List<SupplyRequestDetailDo> r =
			// MiscTest.sampleSiebelQuery(SupplyRequestDetailDo.class,
			// "[serviceCountry] <> ''", 5);
			MiscTest.print(r, "serviceRequestNumber", "serviceCountry");
		} finally {
			System.out.printf("Excec time=%s sec. \n",
					(System.currentTimeMillis() - t0) / 1000.0);
		}
	}

	@Test
	public void testRetrieveSupplyRequestDetail_QA_20130221() throws Exception {
		reqContract.setServiceRequestNumber("1-11053031811");
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		ServiceRequest sr = result.getServiceRequest();
		System.out.printf("serviceRequest=%s\n\n", str(sr));
		GenericAddress addr = sr.getServiceAddress();
		MiscTest.print("serviceAddress = ", str(sr.getServiceAddress()));
	}

	@Test
	public void testRetrieveSupplyRequestDetail_Prod_20130221()
			throws Exception {
		// reqContract.setServiceRequestNumber("1-11352456501");
		reqContract.setServiceRequestNumber("1-11349554681");
		// reqContract.setServiceRequestNumber("1-1136060435");
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		ServiceRequest sr = result.getServiceRequest();
		System.out.printf("serviceRequest=%s\n\n", str(sr));
		GenericAddress addr = sr.getServiceAddress();
		System.out
				.println("physicalLocation1 = " + addr.getPhysicalLocation1());
		System.out
				.println("physicalLocation2 = " + addr.getPhysicalLocation2());
		System.out
				.println("physicalLocation3 = " + addr.getPhysicalLocation3());
		MiscTest.print("serviceAddress = ", str(sr.getServiceAddress()));
	}

	@Test
	public void testRetrieveRequestList_AssetFavoriteFlagFilterIssue()
			throws Exception {

		reqListContract.setContactId("1-50WCH05");
		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(true);
		reqListContract.setShowAllFlag(false);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("205529410");
		reqListContract.setSortCriteria((Map<String, Object>) MiscTest
				.newHashMap("serviceRequestNumber", "DESCENDING"));
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setFilterCriteria((Map<String, Object>) MiscTest
				.newHashMap("serviceRequest.startDate", "07/01/2013 13:00:00",
						"serviceRequest.endDate", "07/17/2013 08:39:51",
						"requestType", Arrays.asList("Consumables Management",
								"Fleet Management", "BreakFix")));
		reqListContract.setNewQueryIndicator(true);
		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("Size: " + list.size());
		System.out.println("Total: " + result.getTotalCount());

		for (ServiceRequest serviceRequest : list) {
			System.out.println(serviceRequest.getArea().getValue());
		}

		System.out.println("END");

		MiscTest.print(result.getRequestList());
	}

	@Test
	public void testRetrieveRequestList_MPS_QA() throws Exception {
		reqListContract.setMdmId("1-YFNVUX");
		reqListContract.setMdmLevel("Siebel");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(true);
		reqListContract.setShowAllFlag(true);
		reqListContract.setFilterCriteria((Map<String, Object>) MiscTest
				.newHashMap("serviceRequest.startDate", "06/11/2013 08:00:00",
						"serviceRequest.endDate", "06/26/2013 21:50:29",
						"requestType", Arrays.asList("Consumables Management"),
						"requestType", Arrays.asList("Fleet Management"),
						"requestType", Arrays.asList("BreakFix")));
		RequestListResult result = service.retrieveRequestList(reqListContract);
		MiscTest.print(result.getRequestList());
	}

	@Test
	public void testRetrieveRequestList_FilterArea_defect7793()
			throws Exception {

		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setVendorFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("29142");
		reqListContract.setNewQueryIndicator(true);
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		// reqListContract.setFilterCriteria((Map<String, Object>)
		// MiscTest.newHashMap(
		// "serviceRequest.startDate", "07/01/2013 13:00:00",
		// "serviceRequest.endDate", "07/17/2013 11:19:03",
		// "area", Arrays.asList("Add Asset", "u"),
		// "area", "u",
		// "requestType", Arrays.asList("Fleet Management")));

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("area", "Asset Acceptance");
		reqListContract.setFilterCriteria(filterCriteria);

		reqListContract.setSortCriteria((Map<String, Object>) MiscTest
				.newHashMap("serviceRequestNumber", "DESCENDING"));

		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);
		for (ServiceRequest sr : result.getRequestList()) {
			System.out.println("Area: " + sr.getArea());
		}
	}

	@Test
	public void testRetrieveRequestList_FilterCountyDistrict() throws Exception {

		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setVendorFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setMdmLevel("Siebel");
		reqListContract.setMdmId("1-YFNVUX");
		reqListContract.setNewQueryIndicator(true);
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setFilterCriteria((Map<String, Object>) MiscTest
				.newHashMap("serviceRequest.startDate", "07/01/2013 22:30:00",
						"serviceRequest.endDate", "07/17/2013 11:50:48"
						// ,
						// "area", "Add Asset"
						, "serviceAddress.district", "Davidson"));

		reqListContract.setSortCriteria((Map<String, Object>) MiscTest
				.newHashMap("serviceAddress.country", "ASCENDING"));

		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);
		MiscTest.print(result.getRequestList());
	}

	@Test
	public void testRetrieveRequestList_defect7855() throws Exception {

		// reqListContract.setMdmLevel("Siebel");
		// reqListContract.setMdmId("1-YFNVUX");
		// // reqListContract.setServiceRequestNumber("1-13133099921");
		// reqListContract.setSessionHandle(crmSessionHandle);
		// reqListContract.setStartRecordNumber(0);
		// reqListContract.setIncrement(40);
		// reqListContract.setNewQueryIndicator(true);
		// reqListContract.setAssetFavoriteFlag(false);
		// reqListContract.setVendorFlag(false);
		// reqListContract.setShowAllFlag(true);
		// Map<String,Object> fiterCriteria = new HashMap<String, Object>();
		// fiterCriteria.put("serviceRequest.endDate", "08/20/2013 17:22:52");
		// fiterCriteria.put("serviceRequest.startDate", "08/05/2013 08:00:00");
		// fiterCriteria.put("requestType",
		// Arrays.asList("Consumables Management","Fleet Management","BreakFix"));
		// reqListContract.setFilterCriteria(fiterCriteria);
		// Map<String,Object> sortCriteria = new HashMap<String, Object>();
		// sortCriteria.put("serviceRequestNumber", "DESCENDING");
		// reqListContract.setSortCriteria(sortCriteria);

		reqListContract.setMdmLevel("Siebel");
		reqListContract.setMdmId("1-YFNVUX");
		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(true);
		// reqListContract.setServiceRequestNumber("1-13133099921");
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setIncrement(40);
		reqListContract.setNewQueryIndicator(true);

		reqListContract.setContactId("1-5VMXS0R");

		// Map<String,Object> fiterCriteria = new HashMap<String, Object>();
		// fiterCriteria.put("serviceRequest.endDate", "08/26/2013 13:04:31");
		// fiterCriteria.put("serviceRequest.startDate", "08/10/2013 22:30:00");
		// fiterCriteria.put("requestType", Arrays.asList("Fleet Management"));
		// reqListContract.setFilterCriteria(fiterCriteria);
		// Map<String,Object> sortCriteria = new HashMap<String, Object>();
		// sortCriteria.put("serviceRequestNumber", "ASCENDING");
		// reqListContract.setSortCriteria(sortCriteria);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("Size: " + list.size());
		System.out.println("Total: " + result.getTotalCount());

		for (ServiceRequest serviceRequest : list) {
			System.out.println(serviceRequest.getArea());
		}

		System.out.println("END");

	}

	@Test
	public void testRetrieveRequestList_defect7873() throws Exception {

		reqListContract.setMdmLevel("Siebel");
		reqListContract.setMdmId("1-YFNVUX");
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setIncrement(100);

		reqListContract.setFilterCriteria((Map<String, Object>) MiscTest
				.newHashMap("serviceAddress.city", "New York"));

		reqListContract.setSortCriteria((Map<String, Object>) MiscTest
				.newHashMap("serviceAddress.officeNumber", "DESCENDING"));

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("Size: " + list.size());
		System.out.println("Total: " + result.getTotalCount());

		for (ServiceRequest serviceRequest : list) {
			System.out.println(serviceRequest.getArea().getValue());
		}

		System.out.println("END");

		// System.out.println("Size: " + result.getRequestList().size());
		// System.out.println(result.getTotalCount());
		// for (ServiceRequest serReq : result.getRequestList()) {
		// System.out.println("Office number: " +
		// serReq.getServiceAddress().getOfficeNumber());
		// }
		// MiscTest.print(result.getRequestList());
	}

	@Test
	public void testRetrieveRequestList_BRD131002() throws Exception {

		reqListContract.setMdmLevel("Siebel");
		reqListContract.setMdmId("1-YFNVUX");
		// reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setIncrement(40);

		reqListContract.setFilterCriteria((Map<String, Object>) MiscTest
				.newHashMap("assetId", "1-BJWG-2459", "requestType", Arrays
						.asList("Consumables Management", "Fleet Management",
								"BreakFix")));

		reqListContract.setSortCriteria((Map<String, Object>) MiscTest
				.newHashMap("serviceRequestNumber", "DESCENDING"));

		RequestListResult result = service.retrieveRequestList(reqListContract);
		System.out.println("Size: " + result.getRequestList().size());
		System.out.println(result.getTotalCount());
		for (ServiceRequest serReq : result.getRequestList()) {
			System.out.println("Office number: "
					+ serReq.getServiceAddress().getOfficeNumber());
		}
		MiscTest.print(result.getRequestList());
	}

	@Test
	public void testRetrieveSupplyRequestDetailEmptyFields() throws Exception {
		reqContract.setServiceRequestNumber("1-13392786801");
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		for (Part part : result.getServiceRequest().getParts()) {
			for (BundlePart bundle : part.getBundlePartList()) {
				System.out.println("Quantity: " + bundle.getQty());
				System.out.println("PartNumber: " + bundle.getPartNumber());
				System.out.println("Description: " + bundle.getDescription());
			}
		}
		print(result);
	}

	@Test
	public void testRetrieveRequestList_BRD131022() throws Exception {
		RequestListContract contract = new RequestListContract();

		contract.setAssetFavoriteFlag(false);
		contract.setVendorFlag(false);
		contract.setShowAllFlag(true);
		contract.setMdmLevel("Siebel");
		contract.setMdmId("1-YFNVUX");
		contract.setNewQueryIndicator(true);
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
				"serviceRequest.endDate", "08/05/2013 11:02:59",
				"serviceRequestStatus", "Inprocess",
				"serviceRequest.startDate", "07/20/2013 18:30:00",
				"requestType", Arrays.asList("Consumables Management",
						"Fleet Management", "BreakFix")));
		contract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"serviceRequestDate", "DESCENDING"));
		contract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(contract);
		List<ServiceRequest> list = result.getRequestList();
		for (ServiceRequest serviceRequest : list) {
			System.out.println(serviceRequest == null);
		}
		System.out.println(list.size());
		System.out.println(result.getTotalCount());

		for (ServiceRequest serviceRequest : list) {
			System.out.println(serviceRequest.getArea().getValue());
		}

		System.out.println("END");

		assertNotNull("Service Request is null", list);
		assertFalse("Service Request list is empty", list.isEmpty());
	}

	@Test
	public void testStatusOnRetrieveRequestListBookmarkAssetsFilterIssue()
			throws Exception {

		reqListContract.setAssetFavoriteFlag(true);
		reqListContract
				.setContactId("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		reqListContract
				.setMdmId("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
		reqListContract.setMdmLevel("Global");

		RequestListResult result = service.retrieveRequestList(reqListContract);

		System.out.println(result.getTotalCount());
		System.out.println(result.getRequestList().size());
	}

	@Test
	public void testRetrieveSupplyRequestDetail_QA_defect8082()
			throws Exception {

		reqContract.setServiceRequestNumber("1-14004774009");
		// reqContract.setVisibilityRole("Customer");
		reqContract.setNewQueryIndicator(false);
		reqContract.setIncrement(40);
		reqContract.setStartRecordNumber(0);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		ServiceRequest sr = result.getServiceRequest();

		System.out.println("Sold to number " + sr.getSoldToNumber());

	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect8158() throws Exception {

		reqContract.setServiceRequestNumber("1-13506159931");
		reqContract.setVisibilityRole("Customer");
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		// System.out.println(result.getServiceRequest().getPrimaryContact());
		// System.out.println(result.getServiceRequest().getServiceAddress());
		// System.out.println(result.getServiceRequest().getParts() == null);
		//
		// System.out.println(result.getServiceRequest().getTotalAmount());
		// System.out.println(result.getServiceRequest().getTax());
		// System.out.println(result.getServiceRequest().getItemSubTotalBeforeTax());
		// System.out.println(result.getServiceRequest().getParts().get(0).getPrice());
		// System.out.println(result.getServiceRequest().getSecondaryContact().getFirstName());
		// System.out.println(result.getServiceRequest().getSecondaryContact().getEmailAddress());

		// print(result);

		System.out.println("Currency: "
				+ result.getServiceRequest().getCurrency());
	}

	@Test
	public void testRetrieveRequestList_defect8183() throws Exception {

		// reqListContract.setContactId("1-5VMXS0R");
		reqListContract.setShowAllFlag(true);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setVendorFlag(false);
		reqListContract.setMdmId("1-YFNVUX");
		reqListContract.setMdmLevel("Siebel");
		reqListContract.setChangeRequestFlag(true);
		reqListContract.setNewQueryIndicator(true);

		// Map<String, Object> filterCriteria = new HashMap<String, Object>();
		// filterCriteria.put("serviceRequest.endDate", "08/26/2013 13:04:31");
		// filterCriteria.put("requestType", Arrays.asList("Fleet Management"));
		// filterCriteria.put("serviceRequest.startDate",
		// "08/10/2013 22:30:00");
		// reqListContract.setFilterCriteria(filterCriteria);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("area", "Asset Acceptance");
		reqListContract.setFilterCriteria(filterCriteria);

		reqListContract.setStartRecordNumber(0);
		reqListContract.setIncrement(40);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestNumber", "ASCENDING");
		reqListContract.setSortCriteria(sortCriteria);

		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		System.out.println("Size: " + result.getRequestList().size());
		System.out.println("Total: " + result.getTotalCount());

		for (ServiceRequest srq : result.getRequestList()) {
			System.out.println(srq.getArea().getValue());
		}

		System.out.println("END");
	}

    @Test
    public void testRetrieveSupplyRequestDetail_defect9962() throws Exception {
    	
    	reqContract.setServiceRequestNumber("1-16274637381");
    	reqContract.setVisibilityRole("Customer");
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	
    	print(result);
    }
    
    @Test
    public void testRetrieveSupplyRequestDetail_defect9995() throws Exception {
    	
    	reqContract.setServiceRequestNumber("1-16274287241");
    	reqContract.setVisibilityRole("Customer");
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	
    	print(result);
    }
    
    @Test
    public void testRetrieveSupplyRequestDetail_defect9992() throws Exception {
    	
    	reqContract.setServiceRequestNumber("1-15695164781");
    	reqContract.setVisibilityRole("Customer");
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	
    	print(result);
    }
    
    @Test
    public void testRetrieveSupplyRequestDetail_defect10529() throws Exception {
    	
    	reqContract.setServiceRequestNumber("1-15762326051");
    	reqContract.setVisibilityRole("Customer");
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	
    	Assert.assertNotNull("Result is null", result);
    	ServiceRequest serviceRequest = result.getServiceRequest();
    	Assert.assertNotNull("ServiceRequest is null", serviceRequest);
    	Asset asset = serviceRequest.getAsset();
    	Assert.assertNotNull("Asset is null", asset);
        	
    	logger.debug("ProductTLI: " + asset.getProductTLI());
    	logger.debug("AssetCostCenter: " + asset.getAssetCostCenter());
    }
    
    @Test
    public void testRetrieveSupplyRequestDetail_defect10554() throws Exception {
    	
    	reqContract.setServiceRequestNumber("1-15762326051");
    	reqContract.setVisibilityRole("Customer");
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	
    	Assert.assertNotNull("Result is null", result);
    	ServiceRequest serviceRequest = result.getServiceRequest();
    	Assert.assertNotNull("ServiceRequest is null", serviceRequest);
    	Asset asset = serviceRequest.getAsset();
    	Assert.assertNotNull("Asset is null", asset);
    	
    	logger.debug("getAddressLine1: " + asset.getInstallAddress().getAddressLine1());
    	logger.debug("getCounty: " + asset.getInstallAddress().getCounty());
    	logger.debug("getOfficeNumber: " + asset.getInstallAddress().getOfficeNumber());
    	logger.debug("getCountry: " + asset.getInstallAddress().getCountry());
    }
    
    @Test
    public void testRetrieveSupplyRequestDetail_defect10557() throws Exception {
    	
    	reqContract.setServiceRequestNumber("1-15981593691");
    	reqContract.setVisibilityRole("Employee");
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	
    	Assert.assertNotNull("Result is null", result);
    	ServiceRequest serviceRequest = result.getServiceRequest();
    	Assert.assertNotNull("ServiceRequest is null", serviceRequest);
    	Asset asset = serviceRequest.getAsset();
    	Assert.assertNotNull("Asset is null", asset);
    	
    	logger.debug("SerialNumber: " + asset.getSerialNumber());
    }

	@Test
	public void testRetrieveRequestList_defect8322() throws Exception {

		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setVendorFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setMdmLevel("Siebel");
		reqListContract.setMdmId("1-YFNVUX");

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestDate", "DESCENDING");
		reqListContract.setSortCriteria(sortCriteria);

		reqListContract.setIncrement(20);
		reqListContract.setStartRecordNumber(0);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("serviceRequest.endDate", "08/21/2013 11:49:52");
		filterCriteria.put("requestType",
				Arrays.asList("Consumables Management"));
		filterCriteria.put("serviceRequest.startDate", "08/05/2013 13:00:00");
		reqListContract.setFilterCriteria(filterCriteria);

		reqListContract.setNewQueryIndicator(true);
		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("Size: " + list.size());
		System.out.println("Total: " + result.getTotalCount());

		for (ServiceRequest serviceRequest : list) {
			System.out.println(serviceRequest.getArea().getValue());
		}

		System.out.println("END");

	}

	@Test
	public void testRetrieveRequestList_defect8408() throws Exception {

		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setVendorFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setMdmLevel("Siebel");
		reqListContract.setMdmId("1-YFNVUX");
		reqListContract.setChangeRequestFlag(false);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortCriteria);

		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("serviceRequest.endDate", "08/23/2013 15:10:44");
		filterCriteria.put("requestType", Arrays.asList(
				"Consumables Management", "Fleet Management", "BreakFix"));
		filterCriteria.put("serviceRequest.startDate", "08/08/2013 08:00:00");
		reqListContract.setFilterCriteria(filterCriteria);

		reqListContract.setNewQueryIndicator(true);
		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		System.out.println("Size: " + result.getRequestList().size());
		System.out.println("Total: " + result.getTotalCount());

	}

	@Test
	public void testRetrieveRequestList_QA_defect8621() throws Exception {
		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setMdmId("1-YFNVUX");
		reqListContract.setMdmLevel("Siebel");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(true);
		reqListContract.setShowAllFlag(true);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestDate", "DESCENDING");
		reqListContract.setSortCriteria(sortCriteria);

		reqListContract.setFilterCriteria((Map<String, Object>) MiscTest
				.newHashMap("serviceRequest.startDate", "08/15/2013 13:00:00",
						"serviceRequest.endDate", "08/31/2013 05:11:26",
						// "requestType", Arrays.asList("Hardware")
						// ,
						"area", Arrays.asList("Add Asset")));

		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);
		System.out.println(result.getRequestList().size());
		System.out.println(result.getTotalCount());

		// List<ServiceRequest> sr = result.getRequestList();
		// for (ServiceRequest serviceRequest : sr) {
		// if (serviceRequest.getServiceRequestNumber().equalsIgnoreCase(
		// "1-13938190201")) {
		// System.out
		// .println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		// }
		// }
	}

	@Test
	public void testRetrieveRequestList_defect8617() throws Exception {

		reqListContract.setMdmLevel("Legal");
		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setMdmId("43167");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(true);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setHardwareRequestFlag(true);
		reqListContract.setChangeRequestFlag(false);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		// filterCriteria.put("serviceRequest.endDate", "09/03/2013 06:09:52");
		// filterCriteria.put("serviceRequest.startDate",
		// "08/18/2013 22:30:00");
		filterCriteria.put("area", Arrays.asList("Adhoc"));
		// filterCriteria.put("requestType", Arrays.asList("Fleet Management"));
		// filterCriteria.put("serviceRequestNumber", "1-13994941461");
		reqListContract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("area", "ASCENDING");
		reqListContract.setSortCriteria(sortCriteria);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("Size: " + list.size());
		System.out.println("Total: " + result.getTotalCount());

		for (ServiceRequest serviceRequest : list) {
			System.out.println(serviceRequest.getArea().getValue());
		}

		System.out.println("END");
	}

    @Test
    public void testRetrieveSupplyRequestDetail_defect10644() throws Exception {
    	
    	reqContract.setServiceRequestNumber("1-15711453621");
    	reqContract.setVisibilityRole("Customer");
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	
    	Assert.assertNotNull("Result is null", result);
    	ServiceRequest serviceRequest = result.getServiceRequest();
    	Assert.assertNotNull("ServiceRequest is null", serviceRequest);
    	Asset asset = serviceRequest.getAsset();
    	Assert.assertNotNull("Asset is null", asset);
    	
    	logger.debug("SerialNumber: " + asset.getSerialNumber());
    	logger.debug("ModelNumber: " + asset.getModelNumber());
    	logger.debug("ProductLine: " + asset.getProductLine());
    	logger.debug("DeviceTag: " + asset.getDeviceTag());
    }
    
    @Test
    public void testRetrieveSupplyRequestDetail_defect10555() throws Exception {
    	
    	reqContract.setServiceRequestNumber("1-16143766831");
    	reqContract.setVisibilityRole("Customer");
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	
    	Assert.assertNotNull("Result is null", result);
    	ServiceRequest serviceRequest = result.getServiceRequest();
    	Assert.assertNotNull("ServiceRequest is null", serviceRequest);
    	Asset asset = serviceRequest.getAsset();
    	Assert.assertNotNull("Asset is null", asset);
    	
    	logger.debug("SerialNumber: " + asset.getSerialNumber());
    	logger.debug("ProductTLI: " + asset.getProductTLI());
    	logger.debug("AssetCostCenter: " + asset.getAssetCostCenter());
    }
    
    @Test
    public void testRetrieveSupplyRequestDetail_defect10628() throws Exception {
    	
    	reqContract.setServiceRequestNumber("1-16078727232");
    	reqContract.setVisibilityRole("Customer");
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	
    	Assert.assertNotNull("Result is null", result);
    	ServiceRequest serviceRequest = result.getServiceRequest();
    	Assert.assertNotNull("ServiceRequest is null", serviceRequest);
    	Asset asset = serviceRequest.getAsset();
    	Assert.assertNotNull("Asset is null", asset);
    	for (Part parts : serviceRequest.getParts()) {
			logger.debug("PartNumber: " + parts.getPartNumber());
		}
    	
    	logger.debug("SerialNumber: " + asset.getSerialNumber());
    }
    
    @Test
    public void testRetrieveSupplyRequestDetail_defect10653() throws Exception {
    	
    	reqContract.setServiceRequestNumber("1-16115496611");
    	reqContract.setVisibilityRole("Customer");
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	
    	Assert.assertNotNull("Result is null", result);
    	ServiceRequest serviceRequest = result.getServiceRequest();
    	Assert.assertNotNull("ServiceRequest is null", serviceRequest);
    	Asset asset = serviceRequest.getAsset();
    	Assert.assertNotNull("Asset is null", asset);
    	
    	for (Part parts : serviceRequest.getParts()) {
			logger.debug("Total Price: " + parts.getTotalPrice());
		}
    	
    }
    
    @Test
    public void testRetrieveSupplyRequestDetail_defect10715() throws Exception {
    	
    	reqContract.setServiceRequestNumber("1-16143471701");
    	reqContract.setVisibilityRole("Customer");
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	
    	Assert.assertNotNull("Result is null", result);
    	ServiceRequest serviceRequest = result.getServiceRequest();
    	Assert.assertNotNull("ServiceRequest is null", serviceRequest);
    	Asset asset = serviceRequest.getAsset();
    	Assert.assertNotNull("Asset is null", asset);
    	
    	for (Part parts : serviceRequest.getParts()) {
    		logger.debug("Total Price: " + parts.getTotalPrice());
    	}
    	for (ServiceRequestOrderLineItem  pendings : serviceRequest.getPendingShipments()) {
			logger.debug("Currancy: " + pendings.getCurrencyCode());
		}
    	logger.debug("END");
    }
    
    @Test
    public void testRetrieveSupplyRequestDetail_defect10993_2() throws Exception {
    	
    	reqContract.setServiceRequestNumber("1-16805959341");
    	reqContract.setVisibilityRole("Customer");
//    	reqContract.setMadcServiceRequestFlag(true);
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	
    	Assert.assertNotNull("Result is null", result);
    	ServiceRequest serviceRequest = result.getServiceRequest();
    	Assert.assertNotNull("ServiceRequest is null", serviceRequest);
    	Asset asset = serviceRequest.getAsset();
    	Assert.assertNotNull("Asset is null", asset);
    	
    	logger.debug("END");
    }
    
    @Test
    public void testRetrieveSupplyRequestDetail_defect11035() throws Exception {
    	reqContract.setServiceRequestNumber("1-16938181441");
    	reqContract.setVisibilityRole("Customer");
    	reqContract.setMadcServiceRequestFlag(true);
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	
    	Assert.assertNotNull("Result is null", result);
    	ServiceRequest serviceRequest = result.getServiceRequest();
    	Assert.assertNotNull("ServiceRequest is null", serviceRequest);
    	Asset asset = serviceRequest.getAsset();
    	Assert.assertNotNull("Asset is null", asset);
    	
    	logger.debug("END");
    }
    
    @Test
    public void testRetrieveSupplyRequestDetail_defect11224() throws Exception {
    	reqContract.setServiceRequestNumber("1-17303850511");
    	reqContract.setVisibilityRole("Customer");
    	reqContract.setMadcServiceRequestFlag(true);
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	
    	Assert.assertNotNull("Result is null", result);
    	ServiceRequest serviceRequest = result.getServiceRequest();
    	Assert.assertNotNull("ServiceRequest is null", serviceRequest);
    	Asset asset = serviceRequest.getAsset();
    	Assert.assertNotNull("Asset is null", asset);
    	
    	logger.debug("Building: " + asset.getInstallAddress().getPhysicalLocation1());
    	logger.debug("Office: " + asset.getInstallAddress().getPhysicalLocation2());
    	logger.debug("Floor: " + asset.getInstallAddress().getPhysicalLocation3());
    	
    	logger.debug("END");
    }
    
    @Test
    public void testRetrieveSupplyRequestDetail_defect10999() throws Exception {
    	reqContract.setServiceRequestNumber("1-17188688931");
    	reqContract.setVisibilityRole("Customer");
    	reqContract.setMadcServiceRequestFlag(true);
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	
    	Assert.assertNotNull("Result is null", result);
    	ServiceRequest serviceRequest = result.getServiceRequest();
    	Assert.assertNotNull("ServiceRequest is null", serviceRequest);
    	Asset asset = serviceRequest.getAsset();
    	Assert.assertNotNull("Asset is null", asset);
    	
    	logger.debug("END");
    }
    
    @Test
    public void testRetrieveSupplyRequestDetail_defect11285() throws Exception {
//    	reqContract.setServiceRequestNumber("1-17438765601");
    	reqContract.setServiceRequestNumber("1-17517835541");
    	reqContract.setVisibilityRole("Customer");
    	reqContract.setMadcServiceRequestFlag(false);
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	
    	Assert.assertNotNull("Result is null", result);
    	ServiceRequest serviceRequest = result.getServiceRequest();
    	Assert.assertNotNull("ServiceRequest is null" + serviceRequest);
    	Asset asset = serviceRequest.getAsset();
    	Assert.assertNotNull("Asset is null" + asset);
    	logger.debug("Cost Center: " + serviceRequest.getCostCenter());
    }
    
    @Test
    public void testRetrieveSupplyRequestDetail_defect11411() throws Exception {
    	reqContract.setServiceRequestNumber("1-17589627753");
    	reqContract.setVisibilityRole("Customer");
    	reqContract.setMadcServiceRequestFlag(false);
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	
    	Assert.assertNotNull("Result is null", result);
    	ServiceRequest serviceRequest = result.getServiceRequest();
    	Assert.assertNotNull("ServiceRequest is null" + serviceRequest);
    	Asset asset = serviceRequest.getAsset();
    	Assert.assertNotNull("Asset is null" + asset);
    	logger.debug("Cost Center: " + serviceRequest.getCostCenter());
    }
    
    @Test
    public void testRetrieveSupplyRequestDetail_defect11921() throws Exception {
    	reqContract.setServiceRequestNumber("1-18489504061");
    	reqContract.setVisibilityRole("Customer");
    	reqContract.setMadcServiceRequestFlag(false);
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	
    	Assert.assertNotNull("Result is null", result);
    	ServiceRequest serviceRequest = result.getServiceRequest();
    	Assert.assertNotNull("ServiceRequest is null" + serviceRequest);
    	Asset asset = serviceRequest.getAsset();
    	Assert.assertNotNull("Asset is null" + asset);
    	logger.debug("CancelledParts: " + serviceRequest.getCancelledParts());
    }
    
    @Test
    public void testRetrieveSupplyRequestDetail_defect11921_2() throws Exception {
    	reqContract.setServiceRequestNumber("1-18489504061");
    	reqContract.setVisibilityRole("Customer");
    	reqContract.setMadcServiceRequestFlag(false);
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	
    	Assert.assertNotNull("Result is null", result);
    	ServiceRequest serviceRequest = result.getServiceRequest();
    	Assert.assertNotNull("ServiceRequest is null" + serviceRequest);
    	Asset asset = serviceRequest.getAsset();
    	Assert.assertNotNull("Asset is null" + asset);
    	logger.debug("CancelledParts: " + serviceRequest.getCancelledParts());
    }
    
    @Test
    public void testRetrieveSupplyRequestDetail_defect12058() throws Exception {
    	reqContract.setServiceRequestNumber("1-18881442341");
    	reqContract.setVisibilityRole("Customer");
    	reqContract.setMadcServiceRequestFlag(false);
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	
    	Assert.assertNotNull("Result is null", result);
    	ServiceRequest serviceRequest = result.getServiceRequest();
    	Assert.assertNotNull("ServiceRequest is null" + serviceRequest);
    	Asset asset = serviceRequest.getAsset();
    	Assert.assertNotNull("Asset is null" + asset);
    	logger.debug("CancelledParts: " + serviceRequest.getCancelledParts());
    }
    
    @Test
    public void testRetrieveSupplyRequestDetail_defect12073() throws Exception {
    	reqContract.setServiceRequestNumber("1-18714642901");
//    	reqContract.setVisibilityRole("Customer");
    	reqContract.setMadcServiceRequestFlag(false);
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	
    	Assert.assertNotNull("Result is null", result);
    	ServiceRequest serviceRequest = result.getServiceRequest();
    	Assert.assertNotNull("ServiceRequest is null" + serviceRequest);
    	Asset asset = serviceRequest.getAsset();
    	Assert.assertNotNull("Asset is null" + asset);
    	logger.debug("EmailActivities: " + serviceRequest.getEmailActivities());
    }
    
    @Test
    public void testRetrieveSupplyRequestDetail_defect12369() throws Exception {
//    	reqContract.setServiceRequestNumber("1-16921679002"); //non hw
//    	reqContract.setServiceRequestNumber("1-19199205231"); //hw
//    	reqContract.setServiceRequestNumber("1-19237794001"); //hw
    	reqContract.setServiceRequestNumber("1-19131494201"); //hw
    	reqContract.setVisibilityRole("Customer");
    	reqContract.setMadcServiceRequestFlag(false);
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	
    	Assert.assertNotNull("Result is null", result);
    	ServiceRequest serviceRequest = result.getServiceRequest();
    	Assert.assertNotNull("ServiceRequest is null" + serviceRequest);
    	Asset asset = serviceRequest.getAsset();
    	Assert.assertNotNull("Asset is null" + asset);
    	Assert.assertNotNull("Pending shipment is null" + serviceRequest.getPendingShipments());
//    	for (ServiceRequestOrderLineItem pendingShipment : serviceRequest.getPendingShipments()) {
//			logger.debug("Price: " + pendingShipment.getPrice());
//		}
    	System.out.println("END");
    }
    
    @Test
    public void testRetrieveSupplyRequestDetail_defect12510() throws Exception {
    	reqContract.setServiceRequestNumber("1-19813554221"); 
    	reqContract.setVisibilityRole("Customer");
    	reqContract.setMadcServiceRequestFlag(false);
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	
    	Assert.assertNotNull("Result is null", result);
    	ServiceRequest serviceRequest = result.getServiceRequest();
    	Assert.assertNotNull("ServiceRequest is null" + serviceRequest);
    	Asset asset = serviceRequest.getAsset();
    	Assert.assertNotNull("Asset is null" + asset);
    	System.out.println("END");
    }

    @Test
    public void testRetrieveSupplyRequestDetail_sqlError() throws Exception {
    	
    	reqContract.setServiceRequestNumber("1-16297569691");
    	reqContract.setVisibilityRole("Customer");
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	
    	print(result);
    }
    
    @Test
    public void testRetrieveSupplyRequestDetail_defect10269() throws Exception {
    	
    	reqContract.setServiceRequestNumber("1-15052483616");
    	reqContract.setVisibilityRole("Customer");
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	
    	print(result);
    }

    @Test
    public void testRetrieveSupplyRequestDetail_defect10251() throws Exception {
    	
    	reqContract.setServiceRequestNumber("1-16413054087");
    	reqContract.setVisibilityRole("Customer");
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	for (ServiceRequestActivity srActivity : result.getServiceRequest().getServicewebUpdateActivities()) {
			System.out.println("activityDate: " + srActivity.getActivityDate());
			System.out.println("activityStatus: " + srActivity.getActivityStatus());
		}
    }

	@Test
	public void testRetrieveRequestList_Multiple() throws Exception {

		reqListContract.setShowAllFlag(true);
		// reqListContract.setMdmLevel("Legal");
		// reqListContract.setMdmId(Arrays.asList(
		// "210093449",
		// "216310003",
		// "219646858",
		// "226336667",
		// "275142552",
		// "276279536",
		// "283328185",
		// "296625663",
		// "305531097",
		// "313214918",
		// "315000554",
		// "347781643",
		// "354085094",
		// "370006132",
		// "370043606",
		// "370163433",
		// "370412681",
		// "397491023",
		// "401627096",
		// "402057702",
		// "403758089",
		// "404786873",
		// "428201529",
		// "428742886",
		// "439152807",
		// "444368658",
		// "449001213",
		// "455627943",
		// "460013220",
		// "480001460",
		// "480498435",
		// "480694256",
		// "481492270",
		// "485147552",
		// "519140057",
		// "539815258",
		// "632089012",
		// "653712109",
		// "656165255",
		// "659667174",
		// "663222776",
		// "690001391",
		// "690622477",
		// "718855745",
		// "728666090",
		// "753465707",
		// "760033357",
		// "764558490",
		// "894302421"));

		// reqListContract.setMdmId(Arrays.asList(
		// "34485",
		// "34486",
		// "69492",
		// "69558",
		// "9618",
		// "34509",
		// "10865",
		// "34508",
		// "34490",
		// "34489",
		// "65314",
		// "34492",
		// "66309",
		// "34767",
		// "66868",
		// "445",
		// "11022",
		// "68505",
		// "14964",
		// "384",
		// "8302",
		// "34496",
		// "34497",
		// "34768",
		// "1708",
		// "67061",
		// "15000",
		// "34507",
		// "452",
		// "7978",
		// "15015",
		// "7978",
		// "67765",
		// "15023",
		// "66690",
		// "68808",
		// "7978",
		// "15341",
		// "69500",
		// "69503",
		// "66988",
		// "34503",
		// "34501",
		// "69498",
		// "30982",
		// "1049",
		// "35393",
		// "1059",
		// "15136",
		// "15138",
		// "69497",
		// "34510",
		// "69506",
		// "2307",
		// "35397",
		// "34511",
		// "2854",
		// "34514",
		// "34770",
		// "34772",
		// "11322",
		// "34773",
		// "108640",
		// "11339",
		// "4950",
		// "15245",
		// "69502",
		// "69493",
		// "5376",
		// "5487",
		// "69491",
		// "69490",
		// "5521",
		// "5626",
		// "5425",
		// "15313",
		// "69274",
		// "27085",
		// "33876",
		// "34520",
		// "69501",
		// "41769",
		// "45392",
		// "69499",
		// "49736",
		// "36391",
		// "36558",
		// "69505",
		// "69488",
		// "44403",
		// "50100",
		// "3385",
		// "111364",
		// "50757"
		// ));

		// reqListContract.setMdmId(Arrays.asList(
		// "1-1DKKKXX",
		// "1-17F49AI",
		// "1-2Y8FVM1",
		// "1-13NVV11",
		// "1-QLBG96",
		// "1-171T2TE",
		// "1-X4BU03",
		// "1-1GK6CN2",
		// "1-1G5H7UO",
		// "1-1AV83FN",
		// "1-1G5DJBB",
		// "1-OU6IYB",
		// "1-186KLU2",
		// "1-194ZU66",
		// "1-4BTY69M",
		// "1-13Z4GKU",
		// "1-NH1BWR",
		// "1-6JQL4P",
		// "1-WN04RG",
		// "1-OL2M6T",
		// "1-1BZC6IN",
		// "1-ZHGY0C",
		// "1-OL1QU9",
		// "1-1E0I797",
		// "1-194ZU25",
		// "1-10UO3QO",
		// "1-O8AFFA",
		// "1-186KLQJ",
		// "1-WN04TB",
		// "1-18N8EHS",
		// "1-O8AFHH",
		// "1-1LIJA9A",
		// "1-X46FMA",
		// "1-10UO3F1",
		// "1-P1I8YF",
		// "1-11092N7",
		// "1-P1I8VX",
		// "1-OL2MB1",
		// "1-NH1C1V",
		// "1-12LTK9T",
		// "1-2LOHUI9",
		// "1-3ABH4Q7",
		// "1-P110NL",
		// "1-17FFUFR",
		// "1-1ASSZK9",
		// "1-1DW6908",
		// "1-1CF5WX2",
		// "1-QLBG20",
		// "1-QHISOB",
		// "1-QHISTG",
		// "1-RVJXFL",
		// "1-TXS85X",
		// "1-3CIMQ9V",
		// "1-16D1971",
		// "1-20PR9RL",
		// "1-OU459J",
		// "1-PQDBDQ",
		// "1-185T2N3",
		// "1-RA2D14",
		// "1-1CZ6ASB",
		// "1-10ULK1L",
		// "1-10V2RIR",
		// "1-10KRAQH",
		// "1-13IYNJ6",
		// "1-RP60ZV",
		// "1-1A8657R",
		// "1-RORPJT",
		// "1-11S2FJI",
		// "1-1W0B1MT",
		// "1-2YF9ZJH",
		// "1-PQBHNV",
		// "1-PQE6RO",
		// "1-1LIFTZU",
		// "1-1QAFN6H",
		// "1-PQDBAC",
		// "1-PQFC36",
		// "1-PQDBIA",
		// "1-13PHWTR",
		// "1-A7MV-658",
		// "1-1LU86MX",
		// "1-13NVV2P",
		// "1-13NXU9K",
		// "1-13NGEBA",
		// "1-18P0DSE",
		// "1-2F9BJXE",
		// "1-2PJXNB5",
		// "1-3CZGY32",
		// "1-3CZGXWZ",
		// "1-3JP0XQP",
		// "1-230T7FG",
		// "1-3BXZ7TN",
		// "1-3CZGXYW",
		// "1-3CTWTAB",
		// "1-2WF3U4R",
		// "1-3SMAJAK",
		// "1-QLBGAQ",
		// "1-5C2ZCYY",
		// "1-34R17DE",
		// "1-3B9I779",
		// "1-16S71PD",
		// "1-2PMEBM7",
		// "1-1QZ4NGL",
		// "1-USFWSL",
		// "1-1E15TBQ",
		// "1-2O5445A",
		// "1-45IQYBX",
		// "1-1AL8VY8",
		// "1-FC2-2947",
		// "1-443Y87L",
		// "1-2EYDP90",
		// "1-3H1XX2I",
		// "1-2S2A2TN",
		// "1-1V9NYKD",
		// "1-2PMEBTF",
		// "1-3XPIQQF",
		// "1-N4UBFD",
		// "1-1I8KGYT",
		// "1-1KNY6AU",
		// "1-1Z1PSCW",
		// "1-1I8HU7W",
		// "1-3W73JPL",
		// "1-4XYQPDG",
		// "1-4EDNFWT",
		// "1-48YFVCQ",
		// "1-1GIC2FP",
		// "1-4X3RZWH",
		// "1-46B3ICH",
		// "1-1L5RYAH",
		// "1-452RT7M",
		// "1-OFWKMT",
		// "1-48CW69R",
		// "1-3O6942X",
		// "1-1HYVYEF",
		// "1-3943HYJ",
		// "1-5SILH1P",
		// "1-5QQR15R",
		// "1-3AAN3WT",
		// "1-58PS0BV",
		// "1-5NXC1B7",
		// "1-3UIY1EW",
		// "1-OFWKK3",
		// "1-J78R7F"
		// ));

		reqListContract.setMdmLevel("Siebel");
		reqListContract.setMdmId("1-NH1BWR");

		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("serviceRequest.endDate", "06/01/2013 00:00:00");
		filterCriteria.put("requestType", Arrays.asList(
				"Consumables Management", "Fleet Management", "BreakFix"));
		filterCriteria.put("serviceRequest.startDate", "04/01/2013 00:00:00");
		reqListContract.setFilterCriteria(filterCriteria);

		reqListContract.setNewQueryIndicator(true);
		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		System.out.println("Size: " + result.getRequestList().size());
		System.out.println("Total: " + result.getTotalCount());

	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect8794() throws Exception {

		reqContract.setServiceRequestNumber("1-14321693391");
		reqContract.setVisibilityRole("Customer");
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		ServiceRequest sr = result.getServiceRequest();

		System.out.println(sr.getExpediteOrder());

		System.out.println(sr.getShipmentOrderLines() == null);
		// System.out.println(sr.getShipmentOrderLines().size());
		System.out.println(sr.getReturnOrderLines() == null);
		// System.out.println(sr.getReturnOrderLines().size());
		System.out.println(sr.getPendingShipments() == null);
		// System.out.println(sr.getPendingShipments().size());

	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect8350() throws Exception {

		reqContract.setServiceRequestNumber("1-14441802031");
		reqContract.setVisibilityRole("Customer");
		reqContract.setNewQueryIndicator(false);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		ServiceRequest sr = result.getServiceRequest();
		GenericAddress address = sr.getServiceAddress();

		System.out.println(sr.getExpediteOrder());

		// System.out.println("District: " + address.getDistrict());
		// System.out.println("County: " + address.getCounty());
		// System.out.println("House number: " + address.getOfficeNumber());

		System.out.println("Currency: "
				+ result.getServiceRequest().getCurrency());

		System.out.println("Credit card token: "
				+ result.getServiceRequest().getCreditCardToken());

	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect9020() throws Exception {

		reqContract.setServiceRequestNumber("1-15182272051");
		reqContract.setVisibilityRole("Customer");
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		ServiceRequest sr = result.getServiceRequest();

		System.out.println("Credit card token: " + sr.getCreditCardToken());
		System.out.println("Credit card type: " + sr.getCreditCardType());

		// ServiceRequest sr = result.getServiceRequest();
		//
		// List<Part> parts = sr.getParts();
		//
		// for (Part part : parts) {
		// System.out.println("Currency: " + part.getCurrency());
		// System.out.println("Price: " + part.getPrice());
		// System.out.println("Tax: " + part.getTaxAmount());
		// System.out.println("Total price: " + part.getTotalPrice());
		// System.out.println("---------------");
		// }
	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect8620() throws Exception {

		reqContract.setServiceRequestNumber("1-14782129221");
		reqContract.setVisibilityRole("Customer");
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		ServiceRequest sr = result.getServiceRequest();

		System.out.println("Credit card token: " + sr.getCreditCardToken());
		System.out.println("Credit card type: " + sr.getCreditCardType());

		// ServiceRequest sr = result.getServiceRequest();

		// List<Part> parts = sr.getParts();
		//
		// for (Part part : parts) {
		// System.out.println("Currency: " + part.getCurrency());
		// System.out.println("Price: " + part.getPrice());
		// System.out.println("Tax: " + part.getTaxAmount());
		// System.out.println("Total price: " + part.getTotalPrice());
		// System.out.println("---------------");
		// }

		// System.out.println("Currency: " +
		// result.getServiceRequest().getCurrency());
	}

	@Test
	public void testRetrieveSupplyRequestDetail_Currency() throws Exception {

		reqContract.setServiceRequestNumber("1-14782193081");
		reqContract.setVisibilityRole("Customer");
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		ServiceRequest sr = result.getServiceRequest();

		// List<Part> parts = sr.getParts();

		// for (Part part : parts) {
		// System.out.println("Currency: " + part.getCurrency());
		// System.out.println("Price: " + part.getPrice());
		// System.out.println("Tax: " + part.getTaxAmount());
		// System.out.println("Total price: " + part.getTotalPrice());
		// System.out.println("---------------");
		// }

		System.out.println("Currency: " + sr.getCurrency());

	}

	@Test
	public void testRetrieveRequestList_defect9065() throws Exception {

		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setVendorFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setHardwareRequestFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("2216");
		reqListContract.setChangeRequestFlag(false);

		// Map<String, Object> sortCriteria = new HashMap<String, Object>();
		// sortCriteria.put("area", "DESCENDING");
		// reqListContract.setSortCriteria(sortCriteria);

		reqListContract.setIncrement(100);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		// filterCriteria.put("serviceRequest.endDate", "09/26/2013 05:51:26");
		filterCriteria.put("area", Arrays.asList("Adhoc"));
		// filterCriteria.put("requestType", Arrays.asList("Fleet Management"));
		filterCriteria.put("serviceRequest.startDate", "09/10/2013 13:00:00");
		reqListContract.setFilterCriteria(filterCriteria);

		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> requests = result.getRequestList();

		System.out.println("Size: " + result.getRequestList().size());
		System.out.println("Total: " + result.getTotalCount());

		for (ServiceRequest serviceRequest : requests) {
			System.out.println("Area: " + serviceRequest.getArea());
		}

	}

	@Test
	public void testRetrieveRequestList_QA_defect8826() throws Exception {
		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setMdmId("3740");
		reqListContract.setMdmLevel("Legal");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(true);
		reqListContract.setShowAllFlag(true);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestDate", "DESCENDING");
		reqListContract.setSortCriteria(sortCriteria);

		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);
		System.out.println(result.getRequestList().size());
		System.out.println(result.getTotalCount());

	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect9179() throws Exception {

		reqContract.setServiceRequestNumber("1-15052173089");
		reqContract.setVisibilityRole("Customer");
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		ServiceRequest sr = result.getServiceRequest();

		System.out.println(sr.getParts() == null ? "Null" : sr.getParts()
				.size());

		for (Part part : sr.getParts()) {
			System.out.println(part.getPartName());
			System.out.println(part.getBundlePartList().size());
		}

	}

	@Test
	public void testRetrieveRequestList_BookmarkedAssetRequestsFilterNotWorking()
			throws Exception {

		reqListContract.setContactId("1-6AH086J");
		reqListContract.setAssetFavoriteFlag(true);
		reqListContract.setVendorFlag(false);
		reqListContract.setShowAllFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("43800");

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestDate", "DESCENDING");
		reqListContract.setSortCriteria(sortCriteria);

		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		// filterCriteria.put("serviceRequest.endDate", "11/07/2013 18:56:01");
		filterCriteria.put("requestType", Arrays.asList(
				"Consumables Management", "Fleet Management", "BreakFix",
				"Fleet Management"));
		// filterCriteria.put("serviceRequest.startDate",
		// "10/22/2013 18:30:00");
		// filterCriteria.put("serviceRequestNumber", "1-16230016341");
		reqListContract.setFilterCriteria(filterCriteria);

		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> requests = result.getRequestList();

		// for (ServiceRequest serviceRequest : requests) {
		// System.out.println("SR number: " +
		// serviceRequest.getServiceRequestNumber());
		// System.out.println("Asset number: " +
		// serviceRequest.getAsset().getAssetId());
		// }

		System.out.println("Size: " + result.getRequestList().size());
		System.out.println("Total: " + result.getTotalCount());

		// for (ServiceRequest serviceRequest : requests) {
		// System.out.println("Area: " + serviceRequest.getArea());
		// }
	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect9842() throws Exception {

		reqContract.setServiceRequestNumber("1-16089151705");
		reqContract.setVisibilityRole("Customer");
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		ServiceRequest sr = result.getServiceRequest();
		System.out.println(sr.getExpediteOrder());
		List<Part> parts = sr.getParts();
		System.out.println(result.getTotalCount());

		System.out.println("Credit card token: " + result.getServiceRequest().getCreditCardToken());
	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect8217() throws Exception {

		reqContract.setServiceRequestNumber("1-16274637301");
		reqContract.setVisibilityRole("Customer");
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		System.out.println(result.getTotalCount());
		System.out.println(result.getServiceRequest() == null);
		System.out.println("Credit card token: "
				+ result.getServiceRequest().getCreditCardToken());

	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect9961() throws Exception {

		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setVendorFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("7758");

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("area", "ASCENDING");
		reqListContract.setSortCriteria(sortCriteria);

		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(40);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("serviceRequest.endDate", "11/25/2013 12:42:57");
		filterCriteria.put("requestType", Arrays.asList(
				"Consumables Management", "Fleet Management", "BreakFix",
				"Fleet Management"));
		filterCriteria.put("serviceRequest.startDate", "11/09/2013 23:30:00");
		reqListContract.setFilterCriteria(filterCriteria);

		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> requests = result.getRequestList();

		System.out.println("Size: " + result.getRequestList().size());
		System.out.println("Total: " + result.getTotalCount());

		for (ServiceRequest serviceRequest : requests) {
			System.out.println("Area: " + serviceRequest.getArea().getValue());
		}

	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect10120() throws Exception {

		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setVendorFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("205529410");

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestDate", "ASCENDING");
		reqListContract.setSortCriteria(sortCriteria);

		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("serviceRequest.endDate", "12/03/2013 19:24:55");
		filterCriteria.put("requestType", Arrays.asList(
				"Consumables Management", "Fleet Management", "BreakFix"));
		filterCriteria.put("serviceRequest.startDate", "08/20/2013 09:00:00");
		// filterCriteria.put("serviceRequestNumber", "1-15503968659");
		reqListContract.setFilterCriteria(filterCriteria);

		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> requests = result.getRequestList();

		System.out.println(requests.get(0).getServiceRequestNumber());
		System.out.println("Size: " + requests.size());
		System.out.println("Total: " + result.getTotalCount());

	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect9961_NewContract()
			throws Exception {

		reqListContract.setAssetFavoriteFlag(true);
		reqListContract.setContactId("ContractID");
		reqListContract.setVendorFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("58971");

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestDate", "DESCENDING");
		reqListContract.setSortCriteria(sortCriteria);

		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("serviceRequest.endDate", "11/27/2013 05:12:42");
		filterCriteria.put("requestType", Arrays.asList(
				"Consumables Management", "Fleet Management",
				"Fleet Management"));
		filterCriteria.put("serviceRequest.startDate", "08/13/2013 18:30:00");
		filterCriteria.put("area", "Adhoc");
		reqListContract.setFilterCriteria(filterCriteria);

		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> requests = result.getRequestList();

		System.out.println("Size: " + result.getRequestList().size());
		System.out.println("Total: " + result.getTotalCount());

		for (ServiceRequest serviceRequest : requests) {
			System.out.println("Area: " + serviceRequest.getArea().getValue());
		}

	}

	@Test
	public void testRetrieveSupplyRequestActivities() throws Exception {

		reqContract.setServiceRequestNumber("1-16648897151");
		reqContract.setVisibilityRole("Customer");
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service
				.retrieveSupplyRequestActivities(reqContract);

		System.out.println(result == null);

	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect9926() throws Exception {
		// reqContract.setServiceRequestNumber("1-9121906043");
		reqContract.setServiceRequestNumber("1-15693003014");
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		System.out.println(result.getServiceRequest().getExpediteOrder());

		System.out.println(result.getServiceRequest() == null);
		System.out
				.println(result.getServiceRequest().getServiceRequestNumber() == null ? "null"
						: result.getServiceRequest().getServiceRequestNumber());
		System.out.println("Credit card token: "
				+ result.getServiceRequest().getCreditCardToken());
	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect10105() throws Exception {

		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setVendorFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("205529410");

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestDate", "ASCENDING");
		reqListContract.setSortCriteria(sortCriteria);

		reqListContract.setIncrement(-1);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("serviceRequest.endDate", "12/03/2013 18:43:13");
		filterCriteria.put("requestType", Arrays.asList(
				"Consumables Management", "Fleet Management", "BreakFix"));
		filterCriteria.put("serviceRequest.startDate", "11/18/2013 10:00:00");
		reqListContract.setFilterCriteria(filterCriteria);

		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> requests = result.getRequestList();

		System.out.println("Size: " + requests.size());
		System.out.println("Total: " + result.getTotalCount());
		int i = 0;
		for (ServiceRequest serviceRequest : requests) {
			i++;
			if (serviceRequest.getServiceRequestNumber().equalsIgnoreCase(
					"1-15719291121")) {
				System.out.println("YES IT IS : "
						+ serviceRequest.getServiceRequestNumber());
				System.out.println(i);
			}
			if (serviceRequest.equals(requests.get(requests.size() - 1))) {
				System.out.println("NO IT IS NOT : "
						+ serviceRequest.getServiceRequestNumber());
			}
		}

	}

	@Test
	public void testRetrieveSupplyRequestDetail_Issue() throws Exception {

		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("16442");

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortCriteria);

		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("requestType", Arrays.asList(
				"Consumables Management", "Fleet Management", "BreakFix"));
		reqListContract.setFilterCriteria(filterCriteria);

		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> requests = result.getRequestList();

		System.out.println(requests.get(0).getServiceRequestNumber());
		System.out.println("Size: " + requests.size());
		System.out.println("Total: " + result.getTotalCount());

	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect9795() throws Exception {

		// reqListContract.setShowAllFlag(true);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("205529410");

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortCriteria);

		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		// reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("serviceRequest.endDate", "12/05/2013 08:10:09");
		filterCriteria.put("requestType", Arrays.asList("BreakFix"));
		filterCriteria.put("serviceRequest.startDate", "11/18/2013 05:00:00");
		reqListContract.setFilterCriteria(filterCriteria);

		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> requests = result.getRequestList();

		System.out.println(requests.get(0).getServiceRequestNumber());
		System.out.println(requests.get(0).getServiceRequestNumber()
				.equalsIgnoreCase("1-15536210528"));
		System.out.println("Size: " + requests.size());
		System.out.println("Total: " + result.getTotalCount());

	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect8217_newIssue()
			throws Exception {

		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setVendorFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("16442");

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortCriteria);

		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("serviceRequest.endDate", "12/06/2013 13:31:50");
		filterCriteria.put("requestType", Arrays.asList(
				"Consumables Management", "Fleet Management", "BreakFix"));
		filterCriteria.put("serviceRequest.startDate", "08/22/2013 18:30:00");
		filterCriteria.put("serviceRequestNumber", "1-15762163911");
		reqListContract.setFilterCriteria(filterCriteria);

		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> requests = result.getRequestList();

		System.out.println(requests.get(0).getServiceRequestNumber());
		System.out.println(requests.get(0).getServiceRequestNumber()
				.equalsIgnoreCase("1-15762163911"));
		System.out.println(requests.get(0).getArea());
		System.out.println("Size: " + requests.size());
		System.out.println("Total: " + result.getTotalCount());

	}

	@Test
	public void testRetrieveHardwareList_defect9983_2() throws Exception {

		ExecutorService executor = Executors.newFixedThreadPool(2);
		List<Future<?>> list = new ArrayList<Future<?>>();

		// for(int i = 0; i < 10; i++ ) {
				executor.submit(new Runnable() {

			@Override
			public void run() {

				reqListContract.setAssetFavoriteFlag(false);
				reqListContract.setVendorFlag(false);
				reqListContract.setShowAllFlag(true);
				reqListContract.setHardwareRequestFlag(false);
				reqListContract.setChangeRequestFlag(false);
				reqListContract.setMdmLevel("Global");
				reqListContract.setMdmId("205529410");

				Map<String, Object> sortCriteria = new HashMap<String, Object>();
				sortCriteria.put("serviceRequestDate", "ASCENDING");
				reqListContract.setSortCriteria(sortCriteria);

				reqListContract.setIncrement(40);
				reqListContract.setStartRecordNumber(0);
				reqListContract.setNewQueryIndicator(true);

				Map<String, Object> filterCriteria = new HashMap<String, Object>();
				filterCriteria.put("serviceRequest.endDate",
						"12/03/2013 19:24:55");
				filterCriteria.put("requestType", Arrays.asList(
						"Consumables Management", "Fleet Management",
						"BreakFix"));
				filterCriteria.put("serviceRequest.startDate",
						"08/20/2013 09:00:00");
				reqListContract.setFilterCriteria(filterCriteria);

				reqListContract.setSessionHandle(crmSessionHandle);

				try {
					RequestListResult result = service
							.retrieveRequestList(reqListContract);
				} catch (Exception e) {
					exceptionCounter++;
					e.printStackTrace();
				}
			}
		}).get();

				executor.submit(new Runnable() {

			@Override
			public void run() {

				// try {
				// Thread.sleep(500);
				// } catch (InterruptedException e1) {
				// }

				reqListContract.setAssetFavoriteFlag(false);
				reqListContract.setVendorFlag(false);
				reqListContract.setShowAllFlag(true);
				reqListContract.setHardwareRequestFlag(false);
				reqListContract.setChangeRequestFlag(false);
				reqListContract.setMdmLevel("Global");
				reqListContract.setMdmId("205529410");

				Map<String, Object> sortCriteria = new HashMap<String, Object>();
				sortCriteria.put("serviceRequestDate", "ASCENDING");
				reqListContract.setSortCriteria(sortCriteria);

				reqListContract.setIncrement(40);
				reqListContract.setStartRecordNumber(0);
				reqListContract.setNewQueryIndicator(true);

				Map<String, Object> filterCriteria = new HashMap<String, Object>();
				filterCriteria.put("serviceRequest.endDate",
						"12/03/2013 19:24:55");
				filterCriteria.put("requestType", Arrays.asList(
						"Consumables Management", "Fleet Management",
						"BreakFix"));
				filterCriteria.put("serviceRequest.startDate",
						"08/20/2013 09:00:00");
				filterCriteria.put("serviceRequestNumber", "1-15503968659");
				// filterCriteria.put("serviceRequestNumber", "1-1550");
				reqListContract.setFilterCriteria(filterCriteria);

				reqListContract.setSessionHandle(crmSessionHandle);

				try {
					RequestListResult result = service
							.retrieveRequestList(reqListContract);
				} catch (Exception e) {
					exceptionCounter++;
					e.printStackTrace();
				}

			}
		}).get();

		// }

//		for (Future<?> future : list) {
//			future.get();
//		}

		executor.shutdown();

		System.out.println("EXCEPTION COUNT: " + exceptionCounter);
		System.out.println("End");
	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect9926_newIssue()
			throws Exception {

		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setVendorFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("16442");

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestDate", "DESCENDING");
		reqListContract.setSortCriteria(sortCriteria);

		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("serviceRequest.endDate", "12/04/2013 18:42:52");
		filterCriteria.put("requestType", Arrays.asList(
				"Consumables Management", "Fleet Management", "BreakFix"));
		filterCriteria.put("serviceRequest.startDate", "08/21/2013 09:00:00");
		// filterCriteria.put("serviceRequestNumber", "1-15727108291");
		filterCriteria.put("serviceRequestNumber", "1-16115520941");
		reqListContract.setFilterCriteria(filterCriteria);

		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> requests = result.getRequestList();

		// System.out.println(requests.get(0).getServiceRequestNumber());
		System.out.println("Size: " + requests.size());
		System.out.println("Total: " + result.getTotalCount());

	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect8217_latestIssue()
			throws Exception {

		reqContract.setServiceRequestNumber("1-16054646231");
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		System.out.println(result.getTotalCount());

		ServiceRequest serviceRequest = result.getServiceRequest();

		System.out.println(serviceRequest.getServiceRequestNumber());
		System.out.println("Problem description: "
				+ serviceRequest.getProblemDescription());
		System.out.println("Requestor first name: "
				+ serviceRequest.getRequestor().getFirstName());
	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect10118() throws Exception {
		reqContract.setServiceRequestNumber("1-16054646231");
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		ServiceRequest serviceRequest = result.getServiceRequest();
		Asset asset = serviceRequest.getAsset();

		System.out.println("Customer reporting name: "
				+ asset.getCustomerReportingName());
		System.out.println("Product line: " + asset.getProductLine());
		System.out.println("Product TLI: " + asset.getProductTLI());
		System.out.println("Credit card token: "
				+ result.getServiceRequest().getCreditCardToken());
	}

	@Test
	public void testRetrieveSupplyRequestDetail_ReleaseSessionIssue()
			throws Exception {
		reqContract.setServiceRequestNumber("1-16117785441");
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		System.out.println(result.getServiceRequest() == null);
		System.out.println("Credit card token: "
				+ result.getServiceRequest().getCreditCardToken());
	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect10711() throws Exception {

		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("315000554");

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortCriteria);

		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		// filterCriteria.put("serviceRequest.endDate", "12/16/2013 06:10:23");
		filterCriteria.put("requestType", Arrays.asList(
				"Consumables Management", "Fleet Management", "BreakFix",
				"Fleet Management"));
		// filterCriteria.put("serviceRequest.startDate",
		// "09/01/2013 18:30:00");
		filterCriteria.put("assetId", "1-7XP0VFM");
		reqListContract.setFilterCriteria(filterCriteria);

		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> requests = result.getRequestList();

		System.out.println(requests == null ? "Reauest list is null" : "Size: "
				+ result.getRequestList().size());
		System.out.println("Total: " + result.getTotalCount());

		for (ServiceRequest serviceRequest : requests) {
			System.out.println("Status: " + serviceRequest.getStatusType());
		}

	}

	@Test
	public void testRetrieveSupplyRequestActivities_defect10483()
			throws Exception {

		reqContract.setServiceRequestNumber("1-16148528218");
		reqContract.setVisibilityRole("Customer");
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service
				.retrieveSupplyRequestActivities(reqContract);

		System.out
				.println(result == null || result.getServiceRequest() == null);

	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect10764() throws Exception {
		reqContract.setServiceRequestNumber("1-16261490321");
		reqContract.setVisibilityRole("Customer");
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		ServiceRequest serviceRequest = result.getServiceRequest();

		System.out.println("Credit card token: "
				+ serviceRequest.getCreditCardToken());
		System.out.println("Credit card number: "
				+ serviceRequest.getPoNumber());

	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect10361() throws Exception {
		reqContract.setServiceRequestNumber("1-16297569691");
		reqContract.setVisibilityRole("Customer");
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		ServiceRequest serviceRequest = result.getServiceRequest();

		Asset asset = serviceRequest.getAsset();

		System.out.println("Area: " + serviceRequest.getArea());
		System.out.println("Subarea: " + serviceRequest.getSubArea());
		System.out.println("Service request number: "
				+ serviceRequest.getServiceRequestNumber());

	}

	@Test
	public void testRetrieveRequestList_defect10769() throws Exception {

		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("16442");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("assetId", "1-1KVEIME");
		filterMap.put("requestType", Arrays.asList("Consumables Management",
				"Fleet Management", "BreakFix", "Fleet Management"));
		reqListContract.setFilterCriteria(filterMap);

		reqListContract
				.setSortCriteria((Map<String, Object>) new HashMap<String, Object>()
						.put("serviceRequestNumber", "ASCENDING"));

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		System.out.println("END");
	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect10118_2()
			throws Exception {
		reqContract.setServiceRequestNumber("1-16261490401");
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		ServiceRequest serviceRequest = result.getServiceRequest();
		Asset asset = serviceRequest.getAsset();

		System.out.println("Customer reporting name: "
				+ asset.getCustomerReportingName());
	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect10118_3()
			throws Exception {
		reqContract.setServiceRequestNumber("1-16261490401");
		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(false);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		ServiceRequest serviceRequest = result.getServiceRequest();
		Asset asset = serviceRequest.getAsset();

		System.out.println("Customer reporting name: "
				+ asset.getCustomerReportingName());

		System.out.println("Serial number: " + asset.getSerialNumber());
	}

	@Test
	public void testRetrieveRequestList_defect10922() throws Exception {

		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("50801");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "12/26/2013 18:25:42");
		filterMap.put("serviceRequest.startDate", "09/12/2013 05:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management",
				"Fleet Management", "BreakFix", "Fleet Management"));
		filterMap.put("serviceRequestNumber", "1-16399916371");
		reqListContract.setFilterCriteria(filterMap);

		reqListContract
				.setSortCriteria((Map<String, Object>) new HashMap<String, Object>()
						.put("serviceRequestNumber", "DESCENDING"));

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		System.out.println("END");
	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect10929() throws Exception {
		reqContract.setServiceRequestNumber("1-16584802101");
		reqContract.setVisibilityRole("Customer");
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		ServiceRequest serviceRequest = result.getServiceRequest();
		Asset asset = serviceRequest.getAsset();

		System.out.println(asset == null);
	}

	@Test
	public void testRetrieveRequestList_8148() throws Exception {

		reqListContract.setContactId("1-6AH086J");
		reqListContract.setAssetFavoriteFlag(true);
		reqListContract.setVendorFlag(false);
		reqListContract.setShowAllFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("43800");

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestDate", "DESCENDING");
		reqListContract.setSortCriteria(sortCriteria);

		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		// filterCriteria.put("serviceRequest.endDate", "11/07/2013 18:56:01");
		filterCriteria.put("requestType", Arrays.asList(
				"Consumables Management", "Fleet Management", "BreakFix",
				"Fleet Management"));
		// filterCriteria.put("serviceRequest.startDate",
		// "10/22/2013 18:30:00");
		// filterCriteria.put("serviceRequestNumber", "1-16230016341");
		reqListContract.setFilterCriteria(filterCriteria);

		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> requests = result.getRequestList();

		// for (ServiceRequest serviceRequest : requests) {
		// System.out.println("SR number: " +
		// serviceRequest.getServiceRequestNumber());
		// System.out.println("Asset number: " +
		// serviceRequest.getAsset().getAssetId());
		// }

		System.out.println("Size: " + result.getRequestList().size());
		System.out.println("Total: " + result.getTotalCount());

		// for (ServiceRequest serviceRequest : requests) {
		// System.out.println("Area: " + serviceRequest.getArea());
		// }
	}

	@Test
	public void testRetrieveSupplyRequestActivities_defect10979()
			throws Exception {

		reqContract.setServiceRequestNumber("1-16148528218");
		reqContract.setVisibilityRole("Customer");
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service
				.retrieveSupplyRequestActivities(reqContract);

		System.out
				.println(result == null || result.getServiceRequest() == null);

	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect10342() throws Exception {
		reqContract.setServiceRequestNumber("1-16805959291");
		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(true);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		ServiceRequest serviceRequest = result.getServiceRequest();

		Asset asset = serviceRequest.getAsset();

		// System.out.println(serviceRequest.getPrimaryContact() == null);
		// System.out.println(serviceRequest.getPrimaryContact().getFirstName());
		// System.out.println(asset.getInstallAddress() == null);
		// System.out.println(asset.getInstallAddress().getAddressName());
		// System.out.println(asset.getMoveToAddressGrouped());

		List<AccountContact> contacts = asset.getDeviceContact();
		System.out.println(contacts.size());

		for (AccountContact contact : contacts) {
			System.out.println("Contact type: "
					+ contact.getDeviceContactType());
			// System.out.println("First name: " + contact.getFirstName());
		}

	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect9813() throws Exception {
		reqContract.setServiceRequestNumber("1-16840518501");
		// reqContract.setServiceRequestNumber("1-16988416361");
		reqContract.setVisibilityRole("Customer");
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);
		reqContract.setMadcServiceRequestFlag(true);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		ServiceRequest serviceRequest = result.getServiceRequest();

		Asset asset = serviceRequest.getAsset();

		System.out.println("Cost center: " + serviceRequest.getCostCenter());
	}

	@Test
	public void testRetrieveRequestList_defect10118() throws Exception {

		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("58971");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "01/06/2014 12:00:58");
		filterMap.put("serviceRequest.startDate", "09/22/2013 13:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management"));
		filterMap.put("serviceRequestNumber", "1-16261490401");
		reqListContract.setFilterCriteria(filterMap);

		reqListContract
				.setSortCriteria((Map<String, Object>) new HashMap<String, Object>()
						.put("serviceRequestNumber", "DESCENDING"));

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		for (ServiceRequest serviceRequest : list) {
			System.out.println(serviceRequest.getAsset()
					.getCustomerReportingName());
		}

		System.out.println("END");
	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect10715_2() throws Exception {
		reqContract.setServiceRequestNumber("1-16143471701");
		reqContract.setVisibilityRole("Customer");
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		List<Part> parts = result.getServiceRequest().getParts();

		System.out.println("Parts size: " + parts.size());

		for (Part part : parts) {
			System.out.println("Part number: " + part.getPartNumber());
			System.out.println("Price: " + part.getPrice());
			System.out.println("Currency: " + part.getCurrency());
		}

		System.out.println("--------------");
		List<ServiceRequestOrderLineItem> pendingShipments = result
				.getServiceRequest().getShipmentOrderLines();

		System.out
				.println("Pending shipments size: " + pendingShipments.size());

		for (ServiceRequestOrderLineItem serviceRequestOrderLineItem : pendingShipments) {
			// System.out.println("Part number: " +
			// serviceRequestOrderLineItem.getPartNumber());
			System.out.println("Price: "
					+ serviceRequestOrderLineItem.getPrice());
			// System.out.println("Currency: " +
			// serviceRequestOrderLineItem.getCurrency());
		}
		System.out.println("End");
	}

	@Test
	public void testRetrieveRequestList_defect10769_2() throws Exception {

		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("58971");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("assetId", "1-15TDGHT");
		filterMap.put("requestType", Arrays.asList("Consumables Management",
				"Fleet Management", "BreakFix", "Fleet Management"));
		reqListContract.setFilterCriteria(filterMap);

		reqListContract
				.setSortCriteria((Map<String, Object>) new HashMap<String, Object>()
						.put("serviceRequestNumber", "ASCENDING"));

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		System.out.println("END");
	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect10993() throws Exception {
		reqContract.setServiceRequestNumber("1-16805959341");
		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(false);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		ServiceRequest serviceRequest = result.getServiceRequest();

		Asset asset = serviceRequest.getAsset();

		System.out.println("Asset tag: " + asset.getAssetTag());
		System.out.println("IP address: " + asset.getIpAddress());
		System.out.println("Host name: " + asset.getHostName());
		System.out.println("Install date: " + asset.getInstallDate());

		// List<ServiceRequestActivity> list =
		// serviceRequest.getServicewebUpdateActivities();
		//
		// System.out.println(list == null ? "null" : list.size());
	}

	@Test
	public void testRetrieveRequestList_defect11002() throws Exception {

		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("023058159");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("assetId", "1-6W6F-17145");
		filterMap.put("requestType", Arrays.asList("Consumables Management",
				"Fleet Management", "BreakFix"));
		reqListContract.setFilterCriteria(filterMap);

		reqListContract
				.setSortCriteria((Map<String, Object>) new HashMap<String, Object>()
						.put("serviceRequestNumber", "DESCENDING"));

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		for (ServiceRequest serviceRequest : list) {

			List<PageCounts> pageCountsList = serviceRequest.getPageCounts();

			// System.out.println(pageCountsList == null);
			// System.out.println(serviceRequest.getAsset().getPageCounts() ==
			// null);
			// System.out.println("-------");

			// for (PageCounts pageCounts : pageCountsList) {
			// System.out.println("Name: " + pageCounts.getName());
			// System.out.println("Reading: " + pageCounts.getCount());
			// System.out.println("---------");
			// }
		}

		System.out.println("END");
	}

	@Test
	public void testRetrieveRequestList_defect11174() throws Exception {

		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("315000554");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "01/16/2014 16:15:35");
		filterMap.put("serviceRequest.startDate", "10/03/2013 09:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management",
				"Fleet Management", "BreakFix"));
		// filterMap.put("asset.serialNumber", "98B0KVM");
		reqListContract.setFilterCriteria(filterMap);

		reqListContract
				.setSortCriteria((Map<String, Object>) new HashMap<String, Object>()
						.put("serviceRequestDate", "DESCENDING"));

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		for (ServiceRequest serviceRequest : list) {
			System.out.println(serviceRequest.getAsset().getInstallDate());
		}

		System.out.println("END");
	}

	@Test
	public void testRetrieveSupplyRequestDetail_2() throws Exception {

		long t = System.currentTimeMillis();

		reqContract.setServiceRequestNumber("1-16148528218");
		reqContract.setVisibilityRole("Customer");
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);
		reqContract.setMadcServiceRequestFlag(false);

		RequestResult result = service
				.retrieveSupplyRequestActivities(reqContract);

		ServiceRequest serviceRequest = result.getServiceRequest();

		Asset asset = serviceRequest.getAsset();

		System.out.println(asset == null);

		System.out.println("Exec time: " + (System.currentTimeMillis() - t)
				/ 1000.0);
	}

	@Test
	public void testRetrieveSupplyRequestActivities_defect10979_2()
			throws Exception {

		long t = System.currentTimeMillis();

		reqContract.setServiceRequestNumber("1-16148528218");
		reqContract.setVisibilityRole("Customer");
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service
				.retrieveSupplyRequestActivities(reqContract);

		System.out
				.println(result == null || result.getServiceRequest() == null);

		System.out.println("Exac time: " + (System.currentTimeMillis() - t)
				/ 1000.0);

		System.out.println("End");
	}

	@Test
	public void testRetrieveRequestList_QA_Performance_defect_11027()
			throws Exception {
		long t0 = System.currentTimeMillis();
		reqListContract.setStartRecordNumber(0);
		reqListContract.setIncrement(40);
		reqListContract.setMdmId("315000554");
		reqListContract.setMdmLevel("Global");
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setVendorFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setNewQueryIndicator(true);

		reqListContract.setSortCriteria((Map<String, Object>) MiscTest
				.newHashMap("serviceRequestDate", "DESCENDING"));

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "01/28/2014 21:16:08");
		filterMap.put("serviceRequest.startDate", "10/14/2013 23:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management",
				"Fleet Management", "BreakFix", "Fleet Management"));
		filterMap.put("serviceRequestStatus", "Inprocess");
		reqListContract.setFilterCriteria(filterMap);

		// reqListContract.setFilterCriteria((Map<String, Object>) MiscTest
		// .newHashMap("requestType", Arrays.asList("Consumables Management",
		// "Fleet Management"
		// ,"BreakFix","Fleet Management"),
		// //"assetId", "1-672J-2296",
		// "serviceRequest.endDate", "01/28/2014 21:16:08",
		// "serviceRequest.startDate", "10/14/2013 23:00:00"
		// "serviceRequestStatus","Inprocess"
		// ));

		reqListContract.setSessionHandle(crmSessionHandle);
		try {
			RequestListResult result = service
					.retrieveRequestList(reqListContract);
			MiscTest.print(result.getRequestList());
		} finally {
			System.out.printf("Exec time=%s sec. \n",
					(System.currentTimeMillis() - t0) / 1000.0);
		}
	}

	@Test
	public void testRetrieveRequestList_QA_Performance_defect_11027_2()
			throws Exception {
		long t0 = System.currentTimeMillis();
		reqListContract.setStartRecordNumber(0);
		reqListContract.setIncrement(40);
		reqListContract.setMdmId("315000554");
		reqListContract.setMdmLevel("Global");
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setVendorFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setNewQueryIndicator(true);
		reqListContract.setSessionHandle(crmSessionHandle);
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestDate", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("assetId", "16HYXL5");

		filterMap.put("serviceRequest.endDate", "01/28/2014 21:16:08");
		filterMap.put("serviceRequest.startDate", "10/14/2013 23:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management",
				"Fleet Management", "BreakFix", "Fleet Management"));
		filterMap.put("serviceRequestStatus", "Inprocess");
		reqListContract.setFilterCriteria(filterMap);

		try {
			RequestListResult result = service
					.retrieveRequestList(reqListContract);
			MiscTest.print(result.getRequestList());
		} finally {
			System.out.printf("Exec time=%s sec. \n",
					(System.currentTimeMillis() - t0) / 1000.0);
		}
	}

	@Test
	public void testRetrieveRequestList_defect8148() throws Exception {

		reqListContract.setContactId("1-7WTFIA5");
		reqListContract.setAssetFavoriteFlag(true);
		reqListContract.setVendorFlag(false);
		reqListContract.setShowAllFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setMdmLevel("Domestic");
		reqListContract.setMdmId("428201529");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(true);
		reqListContract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "01/30/2014 05:15:02");
		filterMap.put("serviceRequest.startDate", "01/15/2014 05:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management",
				"Fleet Management", "BreakFix"));
		reqListContract.setFilterCriteria(filterMap);

		reqListContract
				.setSortCriteria((Map<String, Object>) new HashMap<String, Object>()
						.put("serviceRequestDate", "DESCENDING"));

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		for (ServiceRequest serviceRequest : list) {
			System.out.println(serviceRequest.getServiceRequestNumber());
		}

		System.out.println("END");
	}

	@Test
	public void testRetrieveRequestList_defect10781() throws Exception {

		long t = System.currentTimeMillis();

		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("205529410");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "01/30/2014 12:31:47");
		filterMap.put("serviceRequest.startDate", "10/17/2013 00:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management",
				"Fleet Management", "BreakFix"));
		filterMap.put("serviceRequestStatus", "Inprocess");
		reqListContract.setFilterCriteria(filterMap);

		reqListContract
				.setSortCriteria((Map<String, Object>) new HashMap<String, Object>()
						.put("serviceRequestDate", "DESCENDING"));

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		System.out.println("Execution time: "
				+ ((System.currentTimeMillis() - t) / 1000.0) + " sec.");

		System.out.println("END");
	}

	@Test
	public void testRetrieveServiceRequestHistoryList_defect8217()
			throws Exception {
		ServiceRequestHistoryListContract contract2 = new ServiceRequestHistoryListContract();
		contract2.setLocale(new Locale("en_GB"));
		contract2.setMdmLevel("Global");
		contract2.setAccountId("1-17EKMXJ");
		contract2.setMdmID("205529410");
		contract2.setAssetId("1-9XMW-4462");
		contract2.setServiceRequestNumber("1-10337265154");
		contract2.setNewQueryIndicator(true);
		contract2.setIncrement(40);
		contract2.setStartRecordNumber(0);
		contract2.setSessionHandle(crmSessionHandle);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestStatusDate", "ASCENDING");
		contract2.setSortCriteria(sortCriteria);

		AmindContractedServiceRequestService service2 = new AmindContractedServiceRequestService();
		SessionFactory sessionFactory = TestSessionFactories
				.newStatelessSessionFactory();
		service2.setSessionFactory(sessionFactory);

		ServiceRequestListResult result = service2
				.retrieveServiceRequestHistoryList(contract2);

		System.out.println("Total count: " + result.getTotalCount());
	}

	@Test
	public void testRetrieveRequestList_defect8217() throws Exception {

		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("205529410");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		// filterMap.put("serviceRequest.endDate", "12/26/2013 18:25:42");
		// filterMap.put("serviceRequest.startDate", "09/12/2013 05:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management",
				"Fleet Management", "BreakFix"));
		filterMap.put("assetId", "1-9XMW-4462");
		reqListContract.setFilterCriteria(filterMap);

		reqListContract
				.setSortCriteria((Map<String, Object>) new HashMap<String, Object>()
						.put("serviceRequestNumber", "ASCENDING"));

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		System.out.println("END");
	}


	@Test
	public void testRetrieveRequestList_QA_Performance_() throws Exception {
		long t0 = System.currentTimeMillis();
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setVendorFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("315000554");

		Map<String, Object> filterMap = new HashMap<String, Object>();
		// filterMap.put("assetId", "1-672J-2296");
		filterMap.put("serviceRequest.endDate", "02/06/2014 20:43:18");
		filterMap.put("serviceRequest.startDate", "01/22/2014 10:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management",
				"Fleet Management", "BreakFix", "Fleet Management"));
		// filterMap.put("serviceRequestStatus", "Inprocess");
		reqListContract.setFilterCriteria(filterMap);
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestDate", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setIncrement(40);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);

		try {
			RequestListResult result = service
					.retrieveRequestList(reqListContract);
			MiscTest.print(result.getRequestList());
		} finally {
			System.out.printf("Exec time=%s sec. \n",
					(System.currentTimeMillis() - t0) / 1000.0);
		}
	}

	@Test
	public void testRetrieveRequestList_defect8148_MyRequests()
			throws Exception {

		reqListContract.setContactId("1-7E1VKC1");
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setVendorFlag(false);
		reqListContract.setShowAllFlag(false);
		reqListContract.setChangeRequestFlag(true);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("16442");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(true);
		reqListContract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "02/04/2014 10:30:18");
		filterMap.put("serviceRequest.startDate", "01/19/2014 13:00:00");
		filterMap.put("requestType", Arrays.asList("Fleet Management"));
		reqListContract.setFilterCriteria(filterMap);

		reqListContract
				.setSortCriteria((Map<String, Object>) new HashMap<String, Object>()
						.put("serviceRequestNumber", "DESCENDING"));

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		for (ServiceRequest serviceRequest : list) {
			System.out.println(serviceRequest.getServiceRequestNumber());
		}

		System.out.println("END");
	}

	@Test
	public void testRetrieveRequestList_defect8148_Bookmarked()
			throws Exception {

		reqListContract.setContactId("1-7E1VKC1");
		reqListContract.setAssetFavoriteFlag(true);
		reqListContract.setVendorFlag(false);
		reqListContract.setShowAllFlag(false);
		reqListContract.setChangeRequestFlag(true);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("16442");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(true);
		reqListContract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "02/04/2014 10:28:29");
		filterMap.put("serviceRequest.startDate", "01/19/2014 13:00:00");
		filterMap.put("requestType", Arrays.asList("Fleet Management"));
		reqListContract.setFilterCriteria(filterMap);

		reqListContract
				.setSortCriteria((Map<String, Object>) new HashMap<String, Object>()
						.put("serviceRequestNumber", "DESCENDING"));

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		for (ServiceRequest serviceRequest : list) {
			System.out.println(serviceRequest.getServiceRequestNumber());
		}

		System.out.println("END");
	}

	@Test
	public void testRetrieveRequestList_TimeOutException_AllRequests()
			throws Exception {

		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("50801");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "02/12/2014 12:46:15");
		filterMap.put("serviceRequest.startDate", "01/27/2014 13:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management",
				"Fleet Management", "BreakFix"));
		reqListContract.setFilterCriteria(filterMap);

		reqListContract
				.setSortCriteria((Map<String, Object>) new HashMap<String, Object>()
						.put("serviceRequestNumber", "ASCENDING"));

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		System.out.println("END");
	}

	@Test
	public void testRetrieveSupplyRequestList_defect9926_Condition2()
			throws Exception {

		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("205529410");

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestDate", "ASCENDING");
		reqListContract.setSortCriteria(sortCriteria);

		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("serviceRequest.endDate", "02/12/2014 16:38:25");
		filterCriteria.put("requestType", Arrays.asList("BreakFix"));
		filterCriteria.put("serviceRequest.startDate", "01/28/2014 05:00:00");
		reqListContract.setFilterCriteria(filterCriteria);

		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> requests = result.getRequestList();

		System.out.println("Size: " + requests.size());
		System.out.println("Total: " + result.getTotalCount());

	}

	@Test
	public void testRetrieveRequestList_defect8217_CountMismatch()
			throws Exception {

		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("205529410");

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestNumber", "ASCENDING");
		reqListContract.setSortCriteria(sortCriteria);

		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("requestType", Arrays.asList(
				"Consumables Management", "Fleet Management", "BreakFix"));
		filterCriteria.put("assetId", "1-8QJO-2616");
		reqListContract.setFilterCriteria(filterCriteria);

		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> requests = result.getRequestList();

		System.out.println("Size: " + requests.size());
		System.out.println("Total: " + result.getTotalCount());

	}

	@Test
	public void testRetrieveServiceRequestHistoryList_defect8217_CountMismatch()
			throws Exception {
		ServiceRequestHistoryListContract contract2 = new ServiceRequestHistoryListContract();
		contract2.setLocale(new Locale("en_US"));
		contract2.setMdmLevel("Global");
		contract2.setAccountId("1-17EKMXJ");
		contract2.setMdmID("205529410");
		contract2.setAssetId("1-8QJO-2616");
		contract2.setServiceRequestNumber("1-18157322340");
		contract2.setNewQueryIndicator(true);
		contract2.setIncrement(40);
		contract2.setStartRecordNumber(0);
		contract2.setSessionHandle(crmSessionHandle);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestStatusDate", "ASCENDING");
		contract2.setSortCriteria(sortCriteria);

		AmindContractedServiceRequestService service2 = new AmindContractedServiceRequestService();
		SessionFactory sessionFactory = TestSessionFactories
				.newStatelessSessionFactory();
		service2.setSessionFactory(sessionFactory);

		ServiceRequestListResult result = service2
				.retrieveServiceRequestHistoryList(contract2);

		System.out.println("Size: " + result.getServiceRequests().size());
		System.out.println("Total count: " + result.getTotalCount());
	}

	@Test
	public void testRetrieveRequestList_defect11400() throws Exception {

		long t0 = System.currentTimeMillis();

		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setVendorFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("315000554");
		reqListContract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequestStatus", "Inprocess");
		filterMap.put("serviceRequest.endDate", "02/07/2014 20:50:46");
		filterMap.put("serviceRequest.startDate", "10/25/2013 05:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management",
				"Fleet Management", "BreakFix", "Fleet Management"));
		reqListContract.setFilterCriteria(filterMap);

		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "ASCENDING");
		reqListContract.setSortCriteria(sortMap);

		reqListContract.setStartRecordNumber(0);
		reqListContract.setIncrement(40);
		reqListContract.setNewQueryIndicator(true);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		System.out.println("Total count: " + result.getTotalCount());

		System.out.println("Exec time: " + (System.currentTimeMillis() - t0)
				/ 1000.0);

	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect11783() throws Exception {

		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setVendorFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("34508");

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestDate", "DESCENDING");
		reqListContract.setSortCriteria(sortCriteria);

		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("serviceRequest.endDate", "02/14/2014 19:50:07");
		filterCriteria.put("requestType", Arrays.asList(
				"Consumables Management", "Fleet Management", "BreakFix"));
		filterCriteria.put("serviceRequest.startDate", "01/30/2014 10:00:00");
		filterCriteria.put("serviceRequestNumber", "1-18172930881");
		reqListContract.setFilterCriteria(filterCriteria);

		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> requests = result.getRequestList();

		System.out.println("Size: " + requests.size());
		System.out.println("Total: " + result.getTotalCount());

	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect11783_2()
			throws Exception {

		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setVendorFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("34508");

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortCriteria);

		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("serviceRequest.endDate", "02/17/2014 14:01:12");
		filterCriteria.put("serviceRequest.startDate", "02/01/2014 18:30:00");
		filterCriteria.put("requestType", Arrays.asList(
				"Consumables Management", "Fleet Management", "BreakFix"));
		filterCriteria.put("serviceRequestNumber", "1-18313798611");
		reqListContract.setFilterCriteria(filterCriteria);

		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> requests = result.getRequestList();

		System.out.println("Size: " + requests.size());
		System.out.println("Total: " + result.getTotalCount());

	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect11285_2()
			throws Exception {

		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setVendorFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("63019");

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortCriteria);

		reqListContract.setIncrement(-1);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("serviceRequest.endDate", "02/17/2014 12:34:23");
		filterCriteria.put("serviceRequest.startDate", "02/01/2014 18:30:00");
		filterCriteria.put("requestType",
				Arrays.asList("Consumables Management"));
		// filterCriteria.put("serviceRequestNumber", "1-18313798611");
		reqListContract.setFilterCriteria(filterCriteria);

		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> requests = result.getRequestList();

		System.out.println("Size: " + requests.size());
		System.out.println("Total: " + result.getTotalCount());

		for (ServiceRequest serviceRequest : requests) {
			System.out.println("Service request number: "
					+ serviceRequest.getServiceRequestNumber());
		}

		for (ServiceRequest serviceRequest : requests) {
			System.out.println(serviceRequest.getAsset().getAssetId());
		}
	}

	@Test
	public void testRetrieveSupplyRequestList_defect11814() throws Exception {

		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setVendorFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("1139");

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortCriteria);

		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("serviceRequest.endDate", "02/17/2014 20:04:33");
		filterCriteria.put("serviceRequest.startDate", "02/02/2014 05:00:00");
		filterCriteria.put("requestType", Arrays.asList(
				"Consumables Management", "Fleet Management", "BreakFix"));
		filterCriteria.put("serviceRequestNumber", "1-18320709511");
		reqListContract.setFilterCriteria(filterCriteria);

		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> requests = result.getRequestList();

		System.out.println(requests.get(0).getServiceRequestStatus());
		System.out.println("Size: " + requests.size());
		System.out.println("Total: " + result.getTotalCount());

	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect11814() throws Exception {
		reqContract.setServiceRequestNumber("1-18320709511");
		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(true);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		ServiceRequest serviceRequest = result.getServiceRequest();
		Asset asset = serviceRequest.getAsset();

		System.out.println(serviceRequest.getServiceRequestStatus());
	}

	@Test
	public void testRetrieveSupplyRequestDetail_MyRequests() throws Exception {

		reqListContract.setContactId("1-73RMK3T");
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setVendorFlag(false);
		reqListContract.setShowAllFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("50801");

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortCriteria);

		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("serviceRequest.endDate", "02/17/2014 11:03:12");
		filterCriteria.put("requestType", Arrays.asList(
				"Consumables Management", "Fleet Management", "BreakFix"));
		filterCriteria.put("serviceRequest.startDate", "02/01/2014 18:30:00");
		reqListContract.setFilterCriteria(filterCriteria);

		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> requests = result.getRequestList();

		System.out.println("Size: " + requests.size());
		System.out.println("Total: " + result.getTotalCount());

	}

	@Test
	public void testRetrieveSupplyRequestDetail_ChangeManagement_MyRequests()
			throws Exception {

		reqListContract.setContactId("1-73RMK3T");
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setVendorFlag(false);
		reqListContract.setShowAllFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setChangeRequestFlag(true);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("50801");

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortCriteria);

		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("serviceRequest.endDate", "02/17/2014 11:05:43");
		filterCriteria.put("requestType", Arrays.asList("Fleet Management"));
		filterCriteria.put("serviceRequest.startDate", "02/01/2014 18:30:00");
		reqListContract.setFilterCriteria(filterCriteria);

		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> requests = result.getRequestList();

		System.out.println("Size: " + requests.size());
		System.out.println("Total: " + result.getTotalCount());

	}

	@Test
	public void testRetrieveSupplyRequestDetail_Hardware_MyRequests()
			throws Exception {

		reqListContract.setContactId("1-73RMK3T");
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setVendorFlag(false);
		reqListContract.setShowAllFlag(false);
		reqListContract.setHardwareRequestFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("50801");

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortCriteria);

		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("serviceRequest.endDate", "02/17/2014 11:06:04");
		filterCriteria.put("requestType", Arrays.asList("Fleet Management"));
		filterCriteria.put("serviceRequest.startDate", "02/01/2014 18:30:00");
		filterCriteria.put("area",
				Arrays.asList("HW Order", "Hardware-Ship and Install"));
		reqListContract.setFilterCriteria(filterCriteria);

		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> requests = result.getRequestList();

		System.out.println("Size: " + requests.size());
		System.out.println("Total: " + result.getTotalCount());

	}

	@Test
	public void testRetrieveSupplyRequestList_19() throws Exception {

		reqListContract.setContactId("1-73RMK3T");
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("50801");
		reqListContract.setAssetFavoriteFlag(true);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortCriteria);

		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("serviceRequest.endDate", "02/17/2014 11:06:59");
		filterCriteria.put("requestType", Arrays.asList("BreakFix"));
		filterCriteria.put("serviceRequest.startDate", "02/01/2014 18:30:00");
		reqListContract.setFilterCriteria(filterCriteria);

		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> requests = result.getRequestList();

		System.out.println("Size: " + requests.size());
		System.out.println("Total: " + result.getTotalCount());

		for (ServiceRequest serviceRequest : requests) {
			System.out.println(serviceRequest.getAsset().getAssetId());
		}

	}

	@Test
	public void testRetrieveRequestList_defect11914() throws Exception {

		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("078376928");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "02/20/2014 19:58:13");
		filterMap.put("serviceRequest.startDate", "02/05/2014 05:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management",
				"Fleet Management", "BreakFix", "Fleet Management"));
		filterMap.put("serviceRequestNumber", "1-18461992471");
		reqListContract.setFilterCriteria(filterMap);

		reqListContract
				.setSortCriteria((Map<String, Object>) new HashMap<String, Object>()
						.put("serviceRequestNumber", "DESCENDING"));

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		for (ServiceRequest serviceRequest : list) {
			System.out.println(serviceRequest.getAsset().getAssetId());
		}

		System.out.println("END");
	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect11914() throws Exception {
		reqContract.setServiceRequestNumber("1-18461992471");
		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(false);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		ServiceRequest serviceRequest = result.getServiceRequest();

		Asset asset = serviceRequest.getAsset();

		System.out.println("Asset id: " + asset.getAssetId());
	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect10342_3()
			throws Exception {
		reqContract.setServiceRequestNumber("1-18413224221");
		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(true);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		ServiceRequest serviceRequest = result.getServiceRequest();

		Asset asset = serviceRequest.getAsset();

		List<AccountContact> contacts = asset.getDeviceContact();

		System.out.println(contacts.size());

		for (AccountContact accountContact : contacts) {
			System.out.println("Job role: "
					+ accountContact.getDeviceContactType());
			System.out.println("Name: " + accountContact.getFirstName() + " "
					+ accountContact.getLastName());
			System.out.println("State: "
					+ accountContact.getAddress().getState());
			System.out
					.println("City: " + accountContact.getAddress().getCity());
			System.out.println("Region: "
					+ accountContact.getAddress().getRegion());
		}
	}

	@Test
	public void testRetrieveRequestList_defect11866_AllRequests()
			throws Exception {

		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("110899");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "02/19/2014 15:32:48");
		filterMap.put("serviceRequest.startDate", "01/28/2014 00:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management",
				"Fleet Management", "BreakFix", "Fleet Management"));
		filterMap.put("serviceRequestNumber", "1-17494882551");
		reqListContract.setFilterCriteria(filterMap);

		reqListContract
				.setSortCriteria((Map<String, Object>) new HashMap<String, Object>()
						.put("serviceRequestNumber", "DESCENDING"));

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		System.out.println("END");
	}

	@Test
	public void testRetrieveRequestList_defect11866_ChangeManagementRequests()
			throws Exception {

		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(true);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("110899");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setHardwareRequestsPermission(true);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "02/19/2014 15:37:50");
		filterMap.put("serviceRequest.startDate", "01/04/2014 05:00:00");
		filterMap.put("requestType", Arrays.asList("Fleet Management"));
		// filterMap.put("serviceRequestNumber", "1-17494882551");
		reqListContract.setFilterCriteria(filterMap);

		reqListContract
				.setSortCriteria((Map<String, Object>) new HashMap<String, Object>()
						.put("serviceRequestNumber", "DESCENDING"));

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		// int i = 0;
		// for (ServiceRequest serviceRequest : list) {
		// if (
		// serviceRequest.getArea().getValue().equalsIgnoreCase("Install")
		// &&
		// serviceRequest.getSubArea().getValue()
		// .equalsIgnoreCase("BAU")) {
		// i++;
		// System.out.println("Area: " + serviceRequest.getArea());
		// System.out.println("Sub Area: " + serviceRequest.getSubArea());
		// }
		// }
		//
		// System.out.println(i);

		for (ServiceRequest serviceRequest : list) {
			System.out.println(serviceRequest.getAsset().getInstallDate());
		}

		System.out.println("END");
	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect10830() throws Exception {
		reqContract.setServiceRequestNumber("1-18406057251");
		// reqContract.setServiceRequestNumber("1-16193125011");
		// reqContract.setServiceRequestNumber("1-16193125011");
		// reqContract.setServiceRequestNumber("1-18303835201");
		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(true);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		ServiceRequest serviceRequest = result.getServiceRequest();

		List<ServiceRequestActivity> activities = serviceRequest
				.getActivitywebUpdateActivities();

		System.out.println(activities.size());

		for (ServiceRequestActivity serviceRequestActivity : activities) {
			System.out.println("Status: "
					+ serviceRequestActivity.getActivityStatus());
		}
	}

	@Test
	public void testRetrieveSupplyRequestActivities_defect10483_2()
			throws Exception {

		reqContract.setServiceRequestNumber("1-18317760695");
		reqContract.setVisibilityRole("Customer");
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service
				.retrieveSupplyRequestActivities(reqContract);

		System.out
				.println(result == null || result.getServiceRequest() == null);

		List<ServiceRequestActivity> srs = result.getServiceRequest()
				.getActivitywebUpdateActivities();

		System.out.println(result.getServiceRequest().getArea());

		System.out.println(srs.size());

		for (ServiceRequestActivity serviceRequestActivity : srs) {
			System.out.println(serviceRequestActivity.getActivityStatus());
		}
	}

	@Test
	public void testRetrieveSupplyRequestDetail_46() throws Exception {
		reqContract.setServiceRequestNumber("1-18114725918");
		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(false);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		ServiceRequest serviceRequest = result.getServiceRequest();
		Asset asset = serviceRequest.getAsset();

		System.out.println(serviceRequest.getServiceRequestStatus());
	}

	@Test
	public void testRetrieveRequestList_defect11960() throws Exception {

		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("110899");
		reqListContract.setIncrement(10);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "02/28/2014 10:47:49");
		filterMap.put("serviceRequest.startDate", "02/13/2014 00:00:00");
		filterMap.put("serviceRequestStatus", "Inprocess");
		reqListContract.setFilterCriteria(filterMap);

		reqListContract
				.setSortCriteria((Map<String, Object>) new HashMap<String, Object>()
						.put("serviceRequestDate", "DESCENDING"));

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		System.out.println("END");
	}

    @Test
    public void testRetrieveSupplyRequestDetail_defect10287() throws Exception {
    	
    	reqContract.setServiceRequestNumber("1-16591857841");
    	reqContract.setVisibilityRole("Customer");
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	System.out.println("End");
    	for (Part parts : result.getServiceRequest().getAsset().getPartList()) {
			System.out.println("PartNumber: " + parts.getPartNumber());
			System.out.println("Quantity: " + parts.getOrderQuantity());
			System.out.println("Description: " + parts.getDescription());
		}
    }
    
    @Test
    public void testRetrieveSupplyRequestDetail_defect10342_2() throws Exception {
    	reqContract.setServiceRequestNumber("1-18727169421");
    	reqContract.setVisibilityRole("Customer");
    	reqContract.setMadcServiceRequestFlag(true);
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	System.out.println("Total " + result.getTotalCount());
    }
    
    @Test
    public void testRetrieveSupplyRequestDetail_Sandbox_defect12992() throws Exception {
//    	reqContract.setServiceRequestNumber("1-20107749593");
//    	reqContract.setServiceRequestNumber("1-19204438512"); //BreakFix
    	reqContract.setServiceRequestNumber("1-19295695678"); //MPS
    	reqContract.setMadcServiceRequestFlag(false);
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	System.out.println("helpdeskReferenceNumber: " + result.getServiceRequest().getHelpdeskReferenceNumber());
    	System.out.println("Total " + result.getTotalCount());
    }
    
    @Test
    public void testRetrieveSupplyRequestDetail_defect11950() throws Exception {
    	reqContract.setServiceRequestNumber("1-18582354081");
    	reqContract.setVisibilityRole("Customer");
    	reqContract.setMadcServiceRequestFlag(true);
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	
    	System.out.println("Addressline1: " + result.getServiceRequest().getAsset().getInstallAddress().getAddressLine1());
    	System.out.println("Total " + result.getTotalCount());
    }
    
    @Test
    public void testRetrieveSupplyRequestDetail_defect12036() throws Exception {
    	reqContract.setServiceRequestNumber("1-18855538351");
    	reqContract.setVisibilityRole("Customer");
    	reqContract.setMadcServiceRequestFlag(false);
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	
    	for (ServiceRequestOrderLineItem serviceRequestOrderLineItem : result.getServiceRequest().getPendingShipments()) {
    		System.out.println("PendingShipments: " + serviceRequestOrderLineItem.getName());
		}
    	System.out.println("Total " + result.getTotalCount());
    }
    
    @Test
    public void testRetrieveSupplyRequestDetail_defect12666() throws Exception {
    	reqContract.setServiceRequestNumber("1-19768209415");
    	reqContract.setVisibilityRole("Customer");
    	reqContract.setMadcServiceRequestFlag(true);
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	System.out.println("Total " + result.getTotalCount());
    }
    
    @Test
    public void testRetrieveSupplyRequestDetail_Sandbox_defect13173() throws Exception {
//    	reqContract.setServiceRequestNumber("1-20107749593");
//    	reqContract.setServiceRequestNumber("1-19204438512"); //BreakFix
    	reqContract.setServiceRequestNumber("1-19338809061"); //MPS
    	reqContract.setVisibilityRole("Employee");
    	reqContract.setMadcServiceRequestFlag(false);
    	reqContract.setIncrement(0);
    	reqContract.setStartRecordNumber(0);
    	reqContract.setNewQueryIndicator(false);
    	reqContract.setSessionHandle(crmSessionHandle);
    	
    	RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
    	System.out.println("helpdeskReferenceNumber: " + result.getServiceRequest().getHelpdeskReferenceNumber());
    	System.out.println("Total " + result.getTotalCount());
    }

	@Test
	public void testRetrieveSupplyRequestList_22() throws Exception {

		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setVendorFlag(false);
		reqListContract.setShowAllFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setContactId("1-73RMK3T");
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("50801");

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortCriteria);

		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("serviceRequest.endDate", "02/17/2014 11:07:52");
		filterCriteria.put("requestType",
				Arrays.asList("Consumables Management"));
		filterCriteria.put("serviceRequest.startDate", "02/01/2014 18:30:00");
		reqListContract.setFilterCriteria(filterCriteria);

		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> requests = result.getRequestList();

		for (ServiceRequest serviceRequest : requests) {
			System.out.println(serviceRequest.getServiceRequestNumber());
		}

		System.out.println("Size: " + requests.size());
		System.out.println("Total: " + result.getTotalCount());

	}

	@Test
	public void testRetrieveSupplyRequestList_26() throws Exception {

		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setVendorFlag(false);
		reqListContract.setShowAllFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setContactId("1-73RMK3T");
		// reqListContract.setChlNodeId("1-1UG0HUE");
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("50801");

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortCriteria);

		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("serviceRequest.endDate", "02/17/2014 11:40:27");
		filterCriteria.put("requestType",
				Arrays.asList("Consumables Management"));
		filterCriteria.put("serviceRequest.startDate", "02/01/2014 18:30:00");
		reqListContract.setFilterCriteria(filterCriteria);

		reqListContract.setSessionHandle(crmSessionHandle);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> requests = result.getRequestList();

		System.out.println("Size: " + requests.size());
		System.out.println("Total: " + result.getTotalCount());

	}

	@Test
	public void testRetrieveRequestList_defect12175() throws Exception {

		ExecutorService executor = Executors.newFixedThreadPool(2);
		List<Future<?>> list = new ArrayList<Future<?>>();

		executor.submit(new Runnable() {

			@Override
			public void run() {

				reqListContract.setVendorFlag(false);
				reqListContract.setAssetFavoriteFlag(false);
				reqListContract.setShowAllFlag(true);
				reqListContract.setChangeRequestFlag(false);
				reqListContract.setHardwareRequestFlag(false);
				reqListContract.setMdmLevel("Global");
				reqListContract.setMdmId("315000554");
				reqListContract.setIncrement(40);
				reqListContract.setStartRecordNumber(0);
				reqListContract.setSessionHandle(crmSessionHandle);
				reqListContract.setNewQueryIndicator(true);

				Map<String, Object> filterMap = new HashMap<String, Object>();
				filterMap.put("serviceRequest.endDate", "03/03/2014 23:20:02");
				filterMap
						.put("serviceRequest.startDate", "02/16/2014 10:00:00");
				filterMap.put("requestType", Arrays.asList(
						"Consumables Management", "Fleet Management",
						"BreakFix", "Fleet Management"));
				reqListContract.setFilterCriteria(filterMap);

				reqListContract
						.setSortCriteria((Map<String, Object>) new HashMap<String, Object>()
								.put("serviceRequestNumber", "ASCENDING"));

				RequestListResult result = null;
				try {
					result = service.retrieveRequestList(reqListContract);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				List<ServiceRequest> list = result.getRequestList();

				System.out.println("List size: " + list.size());
				System.out.println("Total count: " + result.getTotalCount());

			}
		}).get();

		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

		executor.submit(new Runnable() {

			@Override
			public void run() {

				reqListContract.setVendorFlag(false);
				reqListContract.setAssetFavoriteFlag(false);
				reqListContract.setShowAllFlag(true);
				reqListContract.setChangeRequestFlag(false);
				reqListContract.setHardwareRequestFlag(false);
				reqListContract.setMdmLevel("Global");
				reqListContract.setMdmId("315000554");
				reqListContract.setIncrement(40);
				reqListContract.setStartRecordNumber(40);
				reqListContract.setSessionHandle(crmSessionHandle);
				reqListContract.setNewQueryIndicator(false);

				Map<String, Object> filterMap = new HashMap<String, Object>();
				filterMap.put("serviceRequest.endDate", "03/03/2014 23:21:50");
				filterMap
						.put("serviceRequest.startDate", "02/16/2014 10:00:00");
				filterMap.put("requestType", Arrays.asList(
						"Consumables Management", "Fleet Management",
						"BreakFix", "Fleet Management"));
				reqListContract.setFilterCriteria(filterMap);

				reqListContract
						.setSortCriteria((Map<String, Object>) new HashMap<String, Object>()
								.put("serviceRequestNumber", "ASCENDING"));

				RequestListResult result = null;
				try {
					result = service.retrieveRequestList(reqListContract);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				List<ServiceRequest> list = result.getRequestList();

				System.out.println("List size: " + list.size());
				System.out.println("Total count: " + result.getTotalCount());

			}
		}).get();

		executor.shutdown();

		System.out.println("END");
	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect12196() throws Exception {
		reqContract.setServiceRequestNumber("1-19051132601");
		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(false);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		ServiceRequest serviceRequest = result.getServiceRequest();

		Asset asset = serviceRequest.getAsset();

		List<PageCounts> pageCountsList = serviceRequest.getPageCounts();

		System.out.println(pageCountsList.size());

		for (PageCounts pageCounts : pageCountsList) {
			System.out.println("Name: " + pageCounts.getName());
			System.out.println("Count: " + pageCounts.getCount());
		}

	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect12196_2()
			throws Exception {
		reqContract.setServiceRequestNumber("1-19090381151");
		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(false);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		ServiceRequest serviceRequest = result.getServiceRequest();

		Asset asset = serviceRequest.getAsset();

		List<PageCounts> pageCountsList = serviceRequest.getPageCounts();

		System.out.println(pageCountsList.size());

		for (PageCounts pageCounts : pageCountsList) {
			System.out.println("Name: " + pageCounts.getName());
			System.out.println("Count: " + pageCounts.getCount());
		}

	}

	@Test
	public void testRetrieveRequestList_defect11454() throws Exception {

		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("50336");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "03/05/2014 16:39:14");
		filterMap.put("serviceRequest.startDate", "02/18/2014 05:00:00");
		filterMap.put("requestType", Arrays.asList("Fleet Management"));
		reqListContract.setFilterCriteria(filterMap);

		reqListContract
				.setSortCriteria((Map<String, Object>) new HashMap<String, Object>()
						.put("serviceRequestDate", "DESCENDING"));

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		for (ServiceRequest SR : list) {
			System.out.println(SR.getArea());
			System.out.println(SR.getSubArea());
			System.out.println("-----------------");
		}

		System.out.println("END");
	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect12242() throws Exception {
		reqContract.setServiceRequestNumber("1-19110565521");
		reqContract.setVisibilityRole("Customer");
		reqContract.setIncrement(0);
		reqContract.setMadcServiceRequestFlag(true);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		List<ServiceRequestActivity> activities = result.getServiceRequest()
				.getActivitywebUpdateActivities();

		for (ServiceRequestActivity serviceRequestActivity : activities) {
			System.out.println(serviceRequestActivity.getActivityId());
			System.out.println(serviceRequestActivity.getActivityStatus());
			System.out.println(serviceRequestActivity.getActivityDescription());
			System.out.println("-----------------");
		}

	}

	@Test
	public void testRetrieveSupplyRequestDetail_dd() throws Exception {
		reqContract.setServiceRequestNumber("1-18447328831");
		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(false);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		ServiceRequest serviceRequest = result.getServiceRequest();

		System.out.println("Area: " + serviceRequest.getArea());
		System.out.println("First name: "
				+ serviceRequest.getPrimaryContact().getFirstName());
		System.out.println("Last name: "
				+ serviceRequest.getPrimaryContact().getFirstName());
		System.out.println("First name: "
				+ serviceRequest.getPrimaryContact().getLastName());
		System.out.println("Email address: "
				+ serviceRequest.getPrimaryContact().getEmailAddress());

		GenericAddress installAddress = serviceRequest.getInstallAddress();

		System.out.println("Install address name: "
				+ installAddress.getAddressName());
		System.out.println("Install address line 1: "
				+ installAddress.getAddressLine1());

		System.out.println("End");
	}

	@Test
	public void testRetrieveRequestList_defect12366() throws Exception {

		long t = System.currentTimeMillis();

		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("50801");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setHardwareRequestsPermission(false);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "03/12/2014 19:51:13");
		filterMap.put("serviceRequest.startDate", "02/25/2014 04:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management",
				"Fleet Management", "BreakFix"));
		// filterMap.put("serviceRequestNumber", "1-19111597781");
		reqListContract.setFilterCriteria(filterMap);

		reqListContract
				.setSortCriteria((Map<String, Object>) new HashMap<String, Object>()
						.put("serviceRequestNumber", "DESCENDING"));

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		System.out.println("Execution time: "
				+ ((System.currentTimeMillis() - t) / 1000.0) + " sec.");

		for (ServiceRequest serviceRequest : list) {
			System.out.println(serviceRequest.getServiceRequestNumber());
			System.out.println(serviceRequest.getArea());
			System.out.println(serviceRequest.getSubArea());
		}

		System.out.println("END");
	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect12232() throws Exception {
		reqContract.setServiceRequestNumber("1-19264198191");
		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(false);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		ServiceRequest serviceRequest = result.getServiceRequest();

		Asset asset = serviceRequest.getAsset();

		System.out.println("Chl node value: " + asset.getChlNodeValue());
		System.out.println("Chl node id: " + asset.getChlNodeId());

	}

	@Test
	public void testRetrieveRequestList_defect12362() throws Exception {

		long t = System.currentTimeMillis();

		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("315000554");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);

		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setHardwareRequestsPermission(true);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "03/13/2014 16:23:36");
		filterMap.put("serviceRequest.startDate", "02/26/2014 09:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management"));
		reqListContract.setFilterCriteria(filterMap);

		reqListContract
				.setSortCriteria((Map<String, Object>) new HashMap<String, Object>()
						.put("serviceAddress.addressLine1", "ASCENDING"));

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		System.out.println("Execution time: "
				+ ((System.currentTimeMillis() - t) / 1000.0) + " sec.");

		System.out.println("END");
	}

	@Test
	public void testRetrieveRequestList_LeftNavPanelIssue() throws Exception {

		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("63019");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		// reqListContract.setChangeRequestsPermission(true);
		// reqListContract.setHardwareRequestsPermission(true);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "03/14/2014 17:45:21");
		filterMap.put("serviceRequest.startDate", "02/27/2014 09:00:00");
		filterMap.put("requestType", Arrays.asList("BreakFix"));
		reqListContract.setFilterCriteria(filterMap);

		reqListContract
				.setSortCriteria((Map<String, Object>) new HashMap<String, Object>()
						.put("serviceRequestDate", "ASCENDING"));

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		for (ServiceRequest serviceRequest : list) {
			System.out.println(serviceRequest.getArea());
			System.out.println(serviceRequest.getSubArea());
			System.out.println("------------------");
		}

		System.out.println("END");
	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect10789() throws Exception {
		// reqContract.setServiceRequestNumber("1-18912462671");
		reqContract.setServiceRequestNumber("1-19294818911");
		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(true);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		ServiceRequest serviceRequest = result.getServiceRequest();

		Asset asset = serviceRequest.getAsset();

		List<AccountContact> accounts = asset.getDeviceContact();

		System.out.println("----------------");

		for (AccountContact accountContact : accounts) {
			GenericAddress address = accountContact.getAddress();
			System.out.println("Address name: " + address.getAddressName());
			System.out.println("Address line 1: " + address.getAddressLine1());
			System.out.println("Address id: " + address.getAddressId());
			System.out.println("Building: " + address.getPhysicalLocation1());
			System.out.println("Floor: " + address.getPhysicalLocation2());
			System.out.println("Office: " + address.getPhysicalLocation3());
			System.out.println("--------------");
		}

	}

	@Test
	public void testRetrieveSupplyRequestDetail_Test() throws Exception {
		reqContract.setServiceRequestNumber("1-19740504391");
		// reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(false);
		reqContract.setNewQueryIndicator(false);
		reqContract.setStartRecordNumber(0);
		reqContract.setIncrement(0);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		System.out.println(result.getServiceRequest().getParts() == null);
		ServiceRequest sr = result.getServiceRequest();
		for (ServiceRequestActivity email : result.getServiceRequest()
				.getEmailActivities()) {

			System.out.println(">> ACTIVITY ID  IS ??" + email.getActivityId());

			System.out.println(">> DESCRIPTION IS ??"
					+ email.getActivityDescription());
			System.out.println(">> COMMEMT IS ??" + email.getComment());
		}
	}

	@Test
	public void testRetrieveRequestList_10122() throws Exception {
		// reqListContract.setContactId("1-2HOPM1N");
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setVendorFlag(false);
		reqListContract.setChlNodeId("1-49L2DC1");
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);

		reqListContract.setMdmId("61630");
		reqListContract.setMdmLevel("Legal");
		reqListContract.setIncrement(40);
		reqListContract.setNewQueryIndicator(true);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);

		reqListContract.setSortCriteria((Map<String, Object>) MiscTest
				.newHashMap("serviceRequestNumber", "ASCENDING"));
		reqListContract.setFilterCriteria((Map<String, Object>) MiscTest
				.newHashMap("serviceRequest.startDate", "01/09/2014 10:00:00",
						"serviceRequest.endDate", "01/24/2014 15:47:29", Arrays
								.asList("Consumables Management",
										" Fleet Management", "BreakFix")));

		RequestListResult result = service.retrieveRequestList(reqListContract);
		System.out.println(result.getTotalCount());
		System.out.println(result.getRequestList().size());

	}

	@Test
	public void testRetrieveRequestList_defect11400_2() throws Exception {

		long t0 = System.currentTimeMillis();

		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setVendorFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("315000554");
		reqListContract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "03/12/2014 19:34:16");
		filterMap.put("serviceRequest.startDate", "02/25/2014 09:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management",
				"Fleet Management", "BreakFix", "Fleet Management"));
		reqListContract.setFilterCriteria(filterMap);

		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "ASCENDING");
		reqListContract.setSortCriteria(sortMap);

		reqListContract.setStartRecordNumber(0);
		reqListContract.setIncrement(40);
		reqListContract.setNewQueryIndicator(true);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		System.out.println("Total count: " + result.getTotalCount());

		System.out.println("Exec time: " + (System.currentTimeMillis() - t0)
				/ 1000.0);

	}

	@Test
	public void testRetrieveRequestList_QA_Defect11978_perfromance_Break_gdoc_7()
			throws Exception {
		reqListContract.setStartRecordNumber(0);
		reqListContract.setIncrement(40);
		reqListContract.setMdmId("428201529");
		reqListContract.setMdmLevel("Domestic");
		// reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "03/27/2014 21:24:25");
		filterMap.put("serviceRequest.startDate", "03/12/2014 04:00:00");
		// filterMap.put("serviceRequestNumber","1-19838552731");

		filterMap.put("requestType", Arrays.asList("BreakFix"));// "Consumables Management","Fleet Management","BreakFix"
		reqListContract.setFilterCriteria(filterMap);

		reqListContract.setNewQueryIndicator(true);
		reqListContract.setSessionHandle(crmSessionHandle);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);

		long t0 = System.currentTimeMillis();
		try {
			RequestListResult result = service
					.retrieveRequestList(reqListContract);
			System.out.println("TOTAL COUNT IS >>" + result.getTotalCount());
		} finally {
			System.out.printf("Exec time=%s sec. \n",
					(System.currentTimeMillis() - t0) / 1000.0);
		}
	}
	
	
	@Test
	public void testRetrieveRequestList_QA_Defect11978_perfromance_Break_gdoc_7_2()
			throws Exception {
		reqListContract.setStartRecordNumber(0);
		reqListContract.setIncrement(40);
		reqListContract.setMdmId("428201529");
		reqListContract.setMdmLevel("Domestic");
		// reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "03/27/2014 21:24:25");
		filterMap.put("serviceRequest.startDate", "03/12/2014 04:00:00");
		// filterMap.put("serviceRequestNumber","1-19838552731");

		filterMap.put("requestType", Arrays.asList("BreakFix"));// "Consumables Management","Fleet Management","BreakFix"
		reqListContract.setFilterCriteria(filterMap);

		reqListContract.setNewQueryIndicator(true);
		reqListContract.setSessionHandle(crmSessionHandle);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);

		long t0 = System.currentTimeMillis();
		try {
			RequestListResult result = service
					.retrieveRequestList(reqListContract);
			System.out.println("TOTAL COUNT IS >>" + result.getTotalCount());
		} finally {
			System.out.printf("Exec time=%s sec. \n",
					(System.currentTimeMillis() - t0) / 1000.0);
		}
	}
	
	@Test
	public void testRetrieveSupplyRequestDetail_defect12243() throws Exception {
		reqContract.setServiceRequestNumber("1-19110565521");
		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(true);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		ServiceRequest serviceRequest = result.getServiceRequest();

		List<ServiceRequestActivity> activities = serviceRequest.getActivitywebUpdateActivities();
		
		for (ServiceRequestActivity serviceRequestActivity : activities) {
			System.out.println(serviceRequestActivity.getActivitySerialNumber());
		}
		
		System.out.println("End");
	}
	
	
	@Test
	public void testRetrieveRequestList_HardwareRequests() throws Exception {

		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(true);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setHardwareRequestsPermission(true);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("009420159");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "04/03/2014 16:27:25");
		filterMap.put("serviceRequest.startDate", "03/19/2014 08:00:00");
		filterMap.put("area", Arrays.asList("HW Order","Hardware-Ship and Install"));
		filterMap.put("requestType", Arrays.asList("Fleet Management"));
		reqListContract.setFilterCriteria(filterMap);

		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_ChangeRequests() throws Exception {

		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(true);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setHardwareRequestsPermission(true);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("009420159");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "04/03/2014 16:26:43");
		filterMap.put("serviceRequest.startDate", "03/19/2014 08:00:00");
		filterMap.put("requestType", Arrays.asList("Fleet Management"));
		reqListContract.setFilterCriteria(filterMap);

		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("area", "ASCENDING");
		reqListContract.setSortCriteria(sortMap);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_ConsumableRequests() throws Exception {

		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setHardwareRequestsPermission(true);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("009420159");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "04/03/2014 16:28:27");
		filterMap.put("serviceRequest.startDate", "03/19/2014 08:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management"));
		reqListContract.setFilterCriteria(filterMap);

		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceAddress.addressLine1", "ASCENDING");
		reqListContract.setSortCriteria(sortMap);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_BreakFixRequests() throws Exception {

		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("009420159");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "04/03/2014 16:29:13");
		filterMap.put("serviceRequest.startDate", "03/19/2014 08:00:00");
		filterMap.put("requestType", Arrays.asList("BreakFix"));
		reqListContract.setFilterCriteria(filterMap);

		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestDate", "ASCENDING");
		reqListContract.setSortCriteria(sortMap);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		System.out.println("END");
	}
	
	
	@Test
	public void testRetrieveRequestList_AllRequests_AreaFilterIssue() throws Exception {
		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setHardwareRequestsPermission(true);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("009420159");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "04/04/2014 18:24:22");
		filterMap.put("serviceRequest.startDate", "03/20/2014 08:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management", "Fleet Management", "BreakFix", "Fleet Management"));
		filterMap.put("area", Arrays.asList("HW"));
		reqListContract.setFilterCriteria(filterMap);

		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		
		for (ServiceRequest serviceRequest : list) {
			System.out.println(serviceRequest.getArea());
		}

		System.out.println("END");
	}
	
	
	@Test
	public void testRetrieveRequestList_defect12665_HW() throws Exception {

		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(true);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setHardwareRequestsPermission(true);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("009420159");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "04/04/2014 19:43:31");
		filterMap.put("serviceRequest.startDate", "03/20/2014 08:00:00");
		filterMap.put("area", Arrays.asList("HW Order","Hardware-Ship and Install", "HW"));
		filterMap.put("requestType", Arrays.asList("Fleet Management"));
		reqListContract.setFilterCriteria(filterMap);

		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		System.out.println("END");
	}
	
	
	@Test
	public void testRetrieveSupplyRequestDetail_defect12780() throws Exception {
		reqContract.setServiceRequestNumber("1-20079651981");
		reqContract.setMadcServiceRequestFlag(false);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		ServiceRequest serviceRequest = result.getServiceRequest();

		GenericAddress address = serviceRequest.getServiceAddress();

		System.out.println("County: " + address.getCounty());
		System.out.println("District: " + address.getDistrict());
		System.out.println("Office number: " + address.getOfficeNumber());
		
	}
	
	
	@Test
	public void testRetrieveRequestList_defect12778() throws Exception {

		reqListContract.setContactId("1-80TDME");
		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(true);
		reqListContract.setShowAllFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setHardwareRequestsPermission(false);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("205529410");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "04/14/2014 01:27:31");
		filterMap.put("serviceRequest.startDate", "03/30/2014 04:00:00");
		filterMap.put("requestType", Arrays.asList("Fleet Management"));
		reqListContract.setFilterCriteria(filterMap);

		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("asset.serialNumber", "ASCENDING");
		reqListContract.setSortCriteria(sortMap);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		for (ServiceRequest serviceRequest : list) {
			System.out.println(serviceRequest.getServiceRequestNumber());
		}

		System.out.println("END");
	}
	
	@Test
	public void testRetrieveSupplyRequestDetail_defect12993() throws Exception {

		reqContract.setServiceRequestNumber("1-20197867131");
		reqContract.setMadcServiceRequestFlag(false);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		
		System.out.println(result.getServiceRequest().getHelpdeskReferenceNumber());
	}

	@Test
	public void testRetrieveSupplyRequestDetail_defect12992_2() throws Exception {

		reqContract.setServiceRequestNumber("1-20107749593");
		reqContract.setMadcServiceRequestFlag(false);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setNewQueryIndicator(false);
		reqContract.setSessionHandle(crmSessionHandle);

		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		
		System.out.println(result.getServiceRequest().getHelpdeskReferenceNumber());
		
		List<ServiceRequestOrderLineItem> shipmentOrderLines = result.getServiceRequest().getShipmentOrderLines();
		
		System.out.println(shipmentOrderLines == null);
		
		for (ServiceRequestOrderLineItem item : shipmentOrderLines) {
			System.out.println("------------");
		}
	}
	
	@Test
	public void testRetrieveRequestList_defect12778_2() throws Exception {

		reqListContract.setContactId("1-7CXV7CT");
		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(true);
		reqListContract.setShowAllFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setHardwareRequestsPermission(true);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("110899");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "04/25/2014 00:00:00");
		filterMap.put("serviceRequest.startDate", "01/25/2014 00:00:00");
//		filterMap.put("requestType", Arrays.asList("Consumables Management", "Fleet Management", "BreakFix", "Fleet Management"));
		filterMap.put("requestType", Arrays.asList("Fleet Management", "BreakFix", "Fleet Management"));
		reqListContract.setFilterCriteria(filterMap);

		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		for (ServiceRequest serviceRequest : list) {
			System.out.println(serviceRequest.getServiceRequestNumber());
		}

		System.out.println("END");
	}

	@Test
	public void testRetrieveRequestList_BRD14_06_05AddingTrackingnumberlinkinRequestHistory_1() throws Exception {

		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setHardwareRequestsPermission(true);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("009420159");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "04/25/2014 19:40:14");
		filterMap.put("serviceRequest.startDate", "04/10/2014 08:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management"));
		reqListContract.setFilterCriteria(filterMap);

		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceAddress.addressLine1", "ASCENDING");
		reqListContract.setSortCriteria(sortMap);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		System.out.println("END");
		
	}
	
	
	@Test
	public void testRetrieveRequestList_BRD14_06_05AddingTrackingnumberlinkinRequestHistory_2() throws Exception {

		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("009420159");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("assetId", "1-9BS1-2216");
		filterMap.put("requestType", Arrays.asList("Consumables Management"));
		reqListContract.setFilterCriteria(filterMap);

		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("expediteOrder", "ASCENDING");
		reqListContract.setSortCriteria(sortMap);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		System.out.println("END");
	}
	
    @Test
    public void testRetrieveRequestList_defect10813() throws Exception {
    	
    	reqListContract.setAssetFavoriteFlag(false);
    	reqListContract.setVendorFlag(false);
    	reqListContract.setShowAllFlag(true);
    	reqListContract.setMdmLevel("Legal");
    	reqListContract.setMdmId("63019");
    	reqListContract.setChangeRequestFlag(false);
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("serviceRequestDate", "DESCENDING");
    	reqListContract.setSortCriteria(sortCriteria);
    	
    	reqListContract.setIncrement(40);
    	reqListContract.setStartRecordNumber(0);
    	reqListContract.setNewQueryIndicator(true);
    	
    	Map<String, Object> filterCriteria = new HashMap<String, Object>();
    	filterCriteria.put("serviceRequest.endDate", "01/03/2014 22:41:29");
    	filterCriteria.put("serviceRequest.startDate", "09/20/2013 05:00:00");
    	filterCriteria.put("requestType", Arrays.asList("Consumables Management", "Fleet Management", "BreakFix"));
    	filterCriteria.put("serviceRequestNumber", "1-16830187002");
    	reqListContract.setFilterCriteria(filterCriteria);
    	
    	reqListContract.setSessionHandle(crmSessionHandle);
    	
    	RequestListResult result = service.retrieveRequestList(reqListContract);
    	
    	for (ServiceRequest sr : result.getRequestList()) {
    		System.out.println("Area: " + sr.getArea());
    		if("Install".equalsIgnoreCase(sr.getArea().toString())) {
    			System.out.println("HW SR Number: " + sr.getServiceRequestNumber());
    		}
    	}
    	
    	System.out.println("Size: " + result.getRequestList().size());
    	System.out.println("Total: " + result.getTotalCount());
    }
    
    @Test
    public void testRetrieveRequestList_defect11002_2() throws Exception {
    	reqListContract.setMdmLevel("Global");
    	reqListContract.setMdmId("023058159");
    	reqListContract.setChangeRequestFlag(false);
    	reqListContract.setIncrement(40);
    	reqListContract.setStartRecordNumber(0);
    	reqListContract.setNewQueryIndicator(true);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortCriteria);
    	Map<String, Object> filterCriteria = new HashMap<String, Object>();
    	filterCriteria.put("requestType", Arrays.asList("Consumables Management", "Fleet Management", "BreakFix"));
    	filterCriteria.put("assetId", "1-6W6F-17145");
    	reqListContract.setFilterCriteria(filterCriteria);
    	reqListContract.setSessionHandle(crmSessionHandle);
    	RequestListResult result = service.retrieveRequestList(reqListContract);
    	
    	System.out.println("Total: " + result.getTotalCount());
    }
    
    @Test
    public void testRetrieveRequestList_filter_defect10042() throws Exception {
    	
    	reqListContract.setAssetFavoriteFlag(false);
    	reqListContract.setVendorFlag(false);
    	reqListContract.setShowAllFlag(true);
    	reqListContract.setMdmLevel("Legal");
    	reqListContract.setMdmId("16442");
    	reqListContract.setChangeRequestFlag(false);
    	reqListContract.setHardwareRequestFlag(true);
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("serviceRequestNumber", "DESCENDING");
    	reqListContract.setSortCriteria(sortCriteria);
    	
    	reqListContract.setIncrement(40);
    	reqListContract.setStartRecordNumber(0);
    	reqListContract.setNewQueryIndicator(true);
    	
    	Map<String, Object> filterCriteria = new HashMap<String, Object>();
    	filterCriteria.put("serviceRequest.endDate", "11/18/2013 09:45:16");
    	filterCriteria.put("serviceRequest.startDate", "11/02/2013 13:00:00");
    	filterCriteria.put("requestType", Arrays.asList("Fleet Management"));
    	reqListContract.setFilterCriteria(filterCriteria);
    	
    	reqListContract.setSessionHandle(crmSessionHandle);
    	
    	RequestListResult result = service.retrieveRequestList(reqListContract);
    	
    	for (ServiceRequest sr : result.getRequestList()) {
//    		System.out.println("Area: " + sr.getServiceRequestNumber());
    		if("1-16433895071".equalsIgnoreCase(sr.getServiceRequestNumber().toString())) {
    			System.out.println("SR Number: " + sr.getServiceRequestNumber());
    		}
    	}
    	
    	System.out.println("Size: " + result.getRequestList().size());
    	System.out.println("Total: " + result.getTotalCount());
    }
    
    @Test
    public void testRetrieveRequestList_filter_defect10282() throws Exception {
    	
    	reqListContract.setAssetFavoriteFlag(false);
    	reqListContract.setVendorFlag(false);
    	reqListContract.setShowAllFlag(true);
    	reqListContract.setMdmLevel("Account");
    	reqListContract.setMdmId("2895");
    	reqListContract.setChangeRequestFlag(true);
    	reqListContract.setHardwareRequestFlag(false);
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("serviceRequestNumber", "DESCENDING");
    	reqListContract.setSortCriteria(sortCriteria);
    	
    	reqListContract.setIncrement(40);
    	reqListContract.setStartRecordNumber(0);
    	reqListContract.setNewQueryIndicator(true);
    	
    	Map<String, Object> filterCriteria = new HashMap<String, Object>();
    	filterCriteria.put("serviceRequest.endDate", "11/20/2013 11:07:44");
    	filterCriteria.put("serviceRequest.startDate", "08/06/2013 22:30:00");
    	filterCriteria.put("serviceRequestNumber", "1-16591857841");
    	filterCriteria.put("requestType", Arrays.asList("Fleet Management"));
    	reqListContract.setFilterCriteria(filterCriteria);
    	
    	reqListContract.setSessionHandle(crmSessionHandle);
    	
    	RequestListResult result = service.retrieveRequestList(reqListContract);
    	
    	for (ServiceRequest sr : result.getRequestList()) {
//    		System.out.println("Area: " + sr.getServiceRequestNumber());
    		if("1-16433895071".equalsIgnoreCase(sr.getServiceRequestNumber().toString())) {
    			System.out.println("SR Number: " + sr.getServiceRequestNumber());
    		}
    	}
    	
    	System.out.println("Size: " + result.getRequestList().size());
    	System.out.println("Total: " + result.getTotalCount());
    }
    
    @Test
    public void testRetrieveRequestList_filter_defect10769() throws Exception {
    	reqListContract.setMdmLevel("Legal");
    	reqListContract.setMdmId("16442");
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("serviceRequestNumber", "ASCENDING");
    	reqListContract.setSortCriteria(sortCriteria);
    	reqListContract.setIncrement(40);
    	reqListContract.setStartRecordNumber(0);
    	reqListContract.setNewQueryIndicator(true);
    	Map<String, Object> filterCriteria = new HashMap<String, Object>();
    	filterCriteria.put("assetId", "1-1KVEIME");
    	filterCriteria.put("coveredService", "coveredTest");
    	filterCriteria.put("requestType", Arrays.asList("Consumables Management", "Fleet Management", "BreakFix", "Fleet Management"));
    	reqListContract.setFilterCriteria(filterCriteria);
    	
    	reqListContract.setSessionHandle(crmSessionHandle);
    	RequestListResult result = service.retrieveRequestList(reqListContract);
    	
    	for (ServiceRequest sr : result.getRequestList()) {
			logger.debug("SR: " + sr.getServiceRequestNumber());
		}
    	System.out.println("Size: " + result.getRequestList().size());
    	System.out.println("Total: " + result.getTotalCount());
    }
    
	@Test
	public void testRetrieveRequestList_defect11454_ChangeManagement() throws Exception {
		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(true);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("110899");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "02/03/2014 19:38:55");
		filterMap.put("serviceRequest.startDate", "01/19/2014 05:00:00");
		filterMap.put("requestType", Arrays.asList("Fleet Management"));
		reqListContract.setFilterCriteria(filterMap);
		reqListContract
				.setSortCriteria((Map<String, Object>) new HashMap<String, Object>()
						.put("serviceRequestDate", "DESCENDING"));
		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}

	@Test
	public void testRetrieveRequestList_defect11454_Hardware() throws Exception {
		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(true);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("110899");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "02/03/2014 19:25:05");
		filterMap.put("serviceRequest.startDate", "10/21/2013 09:00:00");
		filterMap.put("area", Arrays.asList("HW Order", "Hardware-Ship and Install"));
		filterMap.put("requestType", Arrays.asList("Fleet Management"));
		reqListContract.setFilterCriteria(filterMap);
		reqListContract
				.setSortCriteria((Map<String, Object>) new HashMap<String, Object>()
						.put("serviceRequestNumber", "DESCENDING"));

		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
//		for (ServiceRequest serviceRequest : result.getRequestList()) {
//			System.out.println("Area: " + serviceRequest.getArea());
//		}
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_AllRequests() throws Exception {
		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("50801");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "02/12/2014 13:00:00");
		filterMap.put("serviceRequest.startDate", "01/27/2014 13:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management", "Fleet Management", "BreakFix", "Fleet Management"));
		reqListContract.setFilterCriteria(filterMap);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestNumber", "ASCENDING");
		reqListContract.setSortCriteria(sortCriteria );
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_changeRequest() throws Exception {
		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(true);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("50801");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "02/12/2014 13:00:00");
		filterMap.put("serviceRequest.startDate", "01/27/2014 13:00:00");
		filterMap.put("requestType", Arrays.asList("Fleet Management"));
		reqListContract.setFilterCriteria(filterMap);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortCriteria );
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_suppliesRequest() throws Exception {
		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("50801");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "02/12/2014 13:00:00");
		filterMap.put("serviceRequest.startDate", "01/27/2014 13:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management"));
		reqListContract.setFilterCriteria(filterMap);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortCriteria );
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_Prod_defect12778() throws Exception {
		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(true);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setChangeRequestsPermission(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setHardwareRequestsPermission(true);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("009420159");
		reqListContract.setContactId("1-8NGNYON");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "04/15/2014 20:38:20");
		filterMap.put("serviceRequest.startDate", "03/31/2014 08:00:00");
		filterMap.put("serviceRequestNumber", "1-18879983311");
		filterMap.put("area", "HW and Install");
		filterMap.put("requestType", Arrays.asList("BreakFix")); //Fleet Management, Consumables Management
		reqListContract.setFilterCriteria(filterMap);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestDate", "ASCENDING");
		reqListContract.setSortCriteria(sortCriteria );
		
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_hardware() throws Exception {
		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(true);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("50801");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "02/12/2014 13:00:00");
		filterMap.put("serviceRequest.startDate", "01/27/2014 13:00:00");
		filterMap.put("requestType", Arrays.asList("Fleet Management"));
		filterMap.put("area", Arrays.asList("HW Order", "Hardware-Ship and Install"));
		reqListContract.setFilterCriteria(filterMap);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortCriteria );
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_serviceRequest() throws Exception {
		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("50801");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "02/12/2014 13:00:00");
		filterMap.put("serviceRequest.startDate", "01/27/2014 13:00:00");
		filterMap.put("requestType", Arrays.asList("BreakFix"));
		reqListContract.setFilterCriteria(filterMap);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortCriteria );
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_defect12175_1() throws Exception {
		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("315000554");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "03/03/2014 23:20:02");
		filterMap.put("serviceRequest.startDate", "02/16/2014 10:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management", "Fleet Management", "BreakFix", "Fleet Management"));
		reqListContract.setFilterCriteria(filterMap);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestNumber", "ASCENDING");
		reqListContract.setSortCriteria(sortCriteria );
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_defect11454_NO_data() throws Exception {
		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("50336");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "03/05/2014 16:39:14");
		filterMap.put("serviceRequest.startDate", "02/18/2014 05:00:00");
		filterMap.put("requestType", Arrays.asList("Fleet Management"));
		reqListContract.setFilterCriteria(filterMap);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestNumber", "ASCENDING");
		reqListContract.setSortCriteria(sortCriteria );
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		for (ServiceRequest serviceRequest : list) {
			System.out.println("Area: " + serviceRequest.getArea());
		}
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_defect11454_data() throws Exception {
		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(true);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("50336");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "03/05/2014 16:39:14");
		filterMap.put("serviceRequest.startDate", "02/18/2014 05:00:00");
		filterMap.put("requestType", Arrays.asList("Fleet Management"));
		reqListContract.setFilterCriteria(filterMap);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestNumber", "ASCENDING");
		reqListContract.setSortCriteria(sortCriteria );
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		for (ServiceRequest serviceRequest : list) {
			System.out.println("Area: " + serviceRequest.getArea());
		}
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_defect12464() throws Exception {
		reqListContract.setVendorFlag(false);
		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setHardwareRequestsPermission(true);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("009420159");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "03/21/2014 19:53:44");
		filterMap.put("serviceRequest.startDate", "03/06/2014 09:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management", "Fleet Management", "BreakFix", "Fleet Management"));
		filterMap.put("serviceRequestNumber", "1-19320208981");
		reqListContract.setFilterCriteria(filterMap);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestNumber", "ASCENDING");
		reqListContract.setSortCriteria(sortCriteria );
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		for (ServiceRequest serviceRequest : list) {
			System.out.println("Area: " + serviceRequest.getArea());
		}
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_defect12367() throws Exception {
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("110899");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "03/24/2014 09:44:37");
		filterMap.put("serviceRequest.startDate", "03/08/2014 13:00:00");
		filterMap.put("requestType", Arrays.asList("BreakFix"));
		filterMap.put("serviceRequestNumber", "1-19723736741");
		reqListContract.setFilterCriteria(filterMap);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortCriteria );
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
	}
	
	@Test
	public void testRetrieveRequestList_Sandbox_defect12993() throws Exception {
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("205529410");
		reqListContract.setShowAllFlag(true);
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "04/24/2014 15:30:27");
		filterMap.put("serviceRequest.startDate", "04/09/2014 04:00:00");
		filterMap.put("requestType", Arrays.asList("BreakFix"));
		reqListContract.setFilterCriteria(filterMap);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortCriteria );
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		for (ServiceRequest serviceRequest : list) {
			System.out.println("HelpdeskReferenceNumber: " + serviceRequest.getHelpdeskReferenceNumber());
		}
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
	}
        
    @Test
    public void testRetrieveAccountContactDetail() throws Exception {
        reqContract.setServiceRequestNumber("1-9728924161");
        reqContract.setIncrement(10);
        RequestResult result = service.retrieveAccountContactDetail(reqContract);
        ServiceRequest sr = result.getServiceRequest();
        MiscTest.print("[AccountContact Details] ", sr.getContactInfoForDevice());
    }
    
	
	@Test
	public void testRetrieveRequestList_BRD14_06_05AddingTrackingnumberlinkinRequestHistory_3() throws Exception {

		reqListContract.setAssetFavoriteFlag(false);
		reqListContract.setVendorFlag(true);
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setVendorAccountId("1-4MH4XSN");
		reqListContract.setHardwareRequestsPermission(false);
		reqListContract.setChangeRequestsPermission(false);
		reqListContract.setMdmLevel("Siebel");
		reqListContract.setMdmId("1-4MH4XSN");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "04/25/2014 19:58:57");
		filterMap.put("serviceRequest.startDate", "04/10/2014 08:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management"));
		reqListContract.setFilterCriteria(filterMap);

		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceAddress.addressLine1", "ASCENDING");
		reqListContract.setSortCriteria(sortMap);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		System.out.println("END");
		
	}
	
	@Test
	public void testRetrieveSupplyRequestDetail_defect13824() throws Exception {
//		reqContract.setServiceRequestNumber("1-22301644188");
//		reqContract.setServiceRequestNumber("1-22596061761"); //ServiceActivityStatus
		reqContract.setServiceRequestNumber("1-22326891611"); 
		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(false);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		reqContract.setSessionHandle(crmSessionHandle);
		
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		System.out.println(result.getServiceRequest().getHelpdeskReferenceNumber());
	}
	
	@Test
	 public void testRetrieveSupplyRequestDetail_Child_SR() throws Exception {
	  reqContract.setSessionHandle(crmSessionHandle);
	  reqContract.setServiceRequestNumber("1-43827916551");
	  reqContract.setCreateChildSR(false);
	  reqContract.setVisibilityRole("Customer");
	  reqContract.setMadcServiceRequestFlag(true);
	  reqContract.setIncrement(0);
	  reqContract.setStartRecordNumber(0);
	  
	  RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
	  System.out.println(result.getServiceRequest().getParts() == null);
	}
	
	@Test
	public void testRetrieveSupplyRequestDetail_searchspecTest() throws Exception {
		reqContract.setSessionHandle(crmSessionHandle);
		reqContract.setServiceRequestNumber("1-51031162761");
		reqContract.setCreateChildSR(false);
		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(true);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		System.out.println(result.getServiceRequest().getParts() == null);
	}	
	
	@Test
	public void testRetrieveRequestList_LBS_Asset_History() throws Exception {

		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setHardwareRequestsPermission(true);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("315000554");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);

		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "01/07/2015 13:40:42");
		filterMap.put("serviceRequest.startDate", "12/22/2014 13:00:00");
		filterMap.put("assetId", Arrays.asList("1-5U0V-765"));
		filterMap.put("requestType", Arrays.asList("Fleet Management"));
		reqListContract.setFilterCriteria(filterMap);

		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);

		RequestListResult result = service.retrieveRequestList(reqListContract);

		List<ServiceRequest> list = result.getRequestList();

		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());

		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_Defect16195_SB1() throws Exception {
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("315000554");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "01/13/2015 19:03:52");
		filterMap.put("serviceRequest.startDate", "12/29/2014 05:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management", "Fleet Management", "BreakFix"));
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		RequestListResult result = service.retrieveRequestList(reqListContract);
		
		List<ServiceRequest> list = result.getRequestList();
		
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_Defect16309_SB() throws Exception {
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setMdmLevel("Siebel");
		reqListContract.setMdmId("1-ZIFBCQ");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "01/15/2015 05:59:41");
		filterMap.put("serviceRequest.startDate", "12/30/2014 18:30:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management", "Fleet Management", "BreakFix"));
		filterMap.put("assetId", Arrays.asList("1-K42FG12"));
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		RequestListResult result = service.retrieveRequestList(reqListContract);
		
		List<ServiceRequest> list = result.getRequestList();
		
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_Defect16692() throws Exception {
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("62117");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "02/12/2015 07:12:54");
		filterMap.put("serviceRequest.startDate", "01/27/2015 13:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management"));
		filterMap.put("webStatus", Arrays.asList("Submitted", "Inprocess", "Draft"));
		filterMap.put("assetId", "1-N1BR649");
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_Defect16692_2() throws Exception {
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("62117");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "02/17/2015 13:33:30");
		filterMap.put("serviceRequest.startDate", "02/01/2015 13:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management",  "Fleet Management", "BreakFix"));
		filterMap.put("assetId", "1-NXFS-104");
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_Defect16692_3() throws Exception {
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("62117");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "02/17/2015 13:33:30");
		filterMap.put("serviceRequest.startDate", "02/01/2015 13:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management",  "Fleet Management", "BreakFix"));
		filterMap.put("assetId", "1-NXFS-104");
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_SearchspecTest() throws Exception {
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("109994");
		reqListContract.setAccountId("1-531FGH0");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "02/17/2015 11:11:52");
		filterMap.put("serviceRequest.startDate", "02/01/2015 18:30:00");
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_SearchspecTest1() throws Exception {
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("109994");
		reqListContract.setAccountId("1-531FGH0");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "02/17/2015 11:11:52");
		filterMap.put("serviceRequest.startDate", "02/01/2015 18:30:00");
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_SearchspecTest2() throws Exception {
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(true);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setHardwareRequestsPermission(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("62117");
		reqListContract.setAssetId("1-N1BR64P");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "02/18/2015 11:31:30");
		filterMap.put("serviceRequest.startDate", "02/02/2015 13:00:00");
		filterMap.put("assetId", "1-N1BR64P");
		filterMap.put("requestType", Arrays.asList("Fleet Management"));
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	@Test
	public void testRetrieveRequestList_SearchspecTest3() throws Exception {
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(true);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setHardwareRequestsPermission(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("62117");
		reqListContract.setAssetId("1-NXFS-104");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "02/19/2015 04:03:39");
		filterMap.put("serviceRequest.startDate", "02/04/2015 05:00:00");
//		filterMap.put("assetId", "1-N1BR64P");
		filterMap.put("requestType", Arrays.asList("Fleet Management"));
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_ChangeRequestHistory() throws Exception {
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(true);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setHardwareRequestsPermission(true);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("17803");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "03/03/2015 11:41:06");
		filterMap.put("serviceRequest.startDate", "02/15/2015 13:00:00");
		filterMap.put("requestStatus", "Open");
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_ServiceRequestHistory() throws Exception {
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setChangeRequestsPermission(false);
		reqListContract.setHardwareRequestsPermission(false);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("17803");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "03/03/2015 12:56:16");
		filterMap.put("serviceRequest.startDate", "02/15/2015 13:00:00");
		filterMap.put("requestType", Arrays.asList("BreakFix"));
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_SuppliesRequestHistory() throws Exception {
		reqListContract.setShowAllFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setHardwareRequestsPermission(true);
		reqListContract.setMdmLevel("Legal");
		reqListContract.setMdmId("17803");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "03/03/2015 12:50:17");
		filterMap.put("serviceRequest.startDate", "02/15/2015 13:00:00");
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_HardwareRequestHistory() throws Exception {
		reqListContract.setShowAllFlag(true);
		reqListContract.setHardwareRequestFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setHardwareRequestsPermission(true);
		reqListContract.setAccountId("1-P7PYAK");
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("023058159");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "03/19/2015 22:32:55");
		filterMap.put("serviceRequest.startDate", "03/04/2015 04:00:00");
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_INC0102459() throws Exception {
		reqListContract.setShowAllFlag(true);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setHardwareRequestsPermission(false);
//		reqListContract.setAccountId("1-P7PYAK");
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("422416607");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "01/24/2015");
		filterMap.put("serviceRequest.startDate", "12/01/2014");
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		for (ServiceRequest serviceRequest : list) {
//			System.out.println("SR Status: " + serviceRequest.getServiceRequestStatus());
//			System.out.println(serviceRequest.getServiceRequestNumber());
		}
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_SuppliesRequest_defect17506_requestStatus() throws Exception {
		reqListContract.setShowAllFlag(true);
		reqListContract.setOpsPage(true);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setHardwareRequestsPermission(true);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("623331717");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "04/13/2015 07:40:35");
		filterMap.put("serviceRequest.startDate", "03/28/2015 13:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management"));
		filterMap.put("requestStatus", "draft");
		
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveSupplyRequestDetail_SuppliesRequestDetails_noFieldValues() throws Exception {
		reqContract.setSessionHandle(crmSessionHandle);
		reqContract.setServiceRequestNumber("1-475023871");
		reqContract.setCreateChildSR(false);
		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(false);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		System.out.println(result.getServiceRequest().getParts() == null);
	}	
	
	@Test
	public void testRetrieveSupplyRequestDetail_defect17434() throws Exception {
		reqContract.setSessionHandle(crmSessionHandle);
		reqContract.setServiceRequestNumber("1-53572896111");
		reqContract.setCreateChildSR(false);
		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(true);
		reqContract.setNewQueryIndicator(false);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		System.out.println(result.getServiceRequest().getParts() == null);
	}	
	
	@Test
	public void testRetrieveSupplyRequestDetail_defect17452() throws Exception {
		reqContract.setSessionHandle(crmSessionHandle);
		reqContract.setServiceRequestNumber("1-53661875860");
		reqContract.setCreateChildSR(false);
		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(false);
		reqContract.setNewQueryIndicator(false);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		System.out.println(result.getServiceRequest().getParts() == null);
	}	
	
	@Test
	public void testRetrieveRequestList_SuppliesRequest_defect17506_subStatus() throws Exception {
		reqListContract.setShowAllFlag(true);
		reqListContract.setOpsPage(true);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setHardwareRequestsPermission(true);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("623331717");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "04/13/2015 07:40:35");
		filterMap.put("serviceRequest.startDate", "03/28/2015 13:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management"));
		filterMap.put("subStatus", "portal");
		
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_SuppliesRequest_defect17506_severity() throws Exception {
		reqListContract.setShowAllFlag(true);
		reqListContract.setOpsPage(true);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setHardwareRequestsPermission(true);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("623331717");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "04/13/2015 07:40:35");
		filterMap.put("serviceRequest.startDate", "03/28/2015 13:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management"));
		filterMap.put("severity", "lexmark");
		
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_SuppliesRequest_defect17506_agreementNumber() throws Exception {
		reqListContract.setShowAllFlag(true);
		reqListContract.setOpsPage(true);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setHardwareRequestsPermission(true);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("623331717");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "04/13/2015 07:40:35");
		filterMap.put("serviceRequest.startDate", "03/28/2015 13:00:00");
		filterMap.put("requestType", Arrays.asList("Consumables Management"));
		filterMap.put("agreementNumber", "1-55");
		
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_ChangeRequest_defect17506_agreementNumber() throws Exception {
		reqListContract.setShowAllFlag(true);
		reqListContract.setOpsPage(true);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setChangeRequestFlag(true);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setHardwareRequestsPermission(true);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("623331717");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "04/13/2015 10:15:11");
		filterMap.put("serviceRequest.startDate", "03/28/2015 13:00:00");
		filterMap.put("requestType", Arrays.asList("Fleet Management"));
		filterMap.put("agreementNumber", "1-5");
		
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_HardwareRequest_defect17506_projectName() throws Exception {
		reqListContract.setShowAllFlag(true);
		reqListContract.setOpsPage(true);
		reqListContract.setHardwareRequestFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setHardwareRequestsPermission(true);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("623331717");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "04/13/2015 10:18:36");
		filterMap.put("serviceRequest.startDate", "03/28/2015 13:00:00");
		filterMap.put("requestType", Arrays.asList("Fleet Management"));
		filterMap.put("area", Arrays.asList("HW Order", "Hardware-Ship and Install"));
		filterMap.put("projectName", "lex");
		
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_HardwareRequest_defect17506_projectPhase() throws Exception {
		reqListContract.setShowAllFlag(true);
		reqListContract.setOpsPage(true);
		reqListContract.setHardwareRequestFlag(true);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setHardwareRequestsPermission(true);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("623331717");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "04/13/2015 10:18:36");
		filterMap.put("serviceRequest.startDate", "03/28/2015 13:00:00");
		filterMap.put("requestType", Arrays.asList("Fleet Management"));
		filterMap.put("area", Arrays.asList("HW Order", "Hardware-Ship and Install"));
		filterMap.put("projectPhase", "lex");
		
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_defect17885() throws Exception {
		reqListContract.setShowAllFlag(true);
		reqListContract.setOpsPage(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setHardwareRequestsPermission(false);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("315000554");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "05/21/2015");
		filterMap.put("serviceRequest.startDate", "02/20/2015");
//		filterMap.put("requestType", Arrays.asList("Fleet Management"));
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_defect18069() throws Exception {
		reqListContract.setShowAllFlag(true);
		reqListContract.setOpsPage(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setHardwareRequestsPermission(true);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("623331717");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "02/28/2015");
		filterMap.put("serviceRequest.startDate", "12/01/2014");
//		filterMap.put("requestType", Arrays.asList("Fleet Management"));
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_defect18208() throws Exception {
		reqListContract.setShowAllFlag(true);
		reqListContract.setOpsPage(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setHardwareRequestsPermission(false);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("623331717");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "06/19/2015 14:55:33");
		filterMap.put("serviceRequest.startDate", "06/03/2015 13:00:00");
//		filterMap.put("serviceRequestStatus", "Inprocess");
//		filterMap.put("requestType", Arrays.asList("Consumables Management", "Fleet Management", "BreakFix"));
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_defect18228() throws Exception {
		reqListContract.setShowAllFlag(true);
		reqListContract.setOpsPage(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setHardwareRequestsPermission(false);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("315000554");
		reqListContract.setIncrement(10);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "06/22/2015 09:51:23");
		filterMap.put("serviceRequest.startDate", "06/06/2015 18:30:00");
		filterMap.put("serviceRequestStatus", "Inprocess");
		filterMap.put("requestType", Arrays.asList("Consumables Management", "Fleet Management", "BreakFix"));
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_propertySet() throws Exception {
		reqListContract.setShowAllFlag(true);
		reqListContract.setOpsPage(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setChangeRequestFlag(true);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setHardwareRequestsPermission(true);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("623331717");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "06/25/2015 14:18:28");
		filterMap.put("serviceRequest.startDate", "06/10/2015 04:00:00");
//		filterMap.put("serviceRequestStatus", "Inprocess");
//		filterMap.put("requestType", Arrays.asList("Consumables Management", "Fleet Management", "BreakFix"));
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		RequestListResult result = service.retrieveRequestList(reqListContract);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_performance() throws Exception {
		reqListContract.setShowAllFlag(true);
		reqListContract.setOpsPage(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setHardwareRequestsPermission(false);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("315000554");
		reqListContract.setIncrement(10);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "06/25/2015 14:32:26");
		filterMap.put("serviceRequest.startDate", "06/10/2015 04:00:00");
		filterMap.put("serviceRequestStatus", "Inprocess");
		filterMap.put("requestType", Arrays.asList("Consumables Management", "Fleet Management", "BreakFix"));
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		long t0 = System.currentTimeMillis();
		RequestListResult result = service.retrieveRequestList(reqListContract);
		System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
		
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	@Test
	public void testRetrieveRequestList_performance_2() throws Exception {
		reqListContract.setShowAllFlag(true);
		reqListContract.setOpsPage(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setHardwareRequestsPermission(false);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("315000554");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "06/25/2015 15:19:43");
		filterMap.put("serviceRequest.startDate", "06/10/2015 04:00:00");
//		filterMap.put("serviceRequestStatus", "Inprocess");
//		filterMap.put("requestType", Arrays.asList("Consumables Management", "Fleet Management", "BreakFix"));
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		long t0 = System.currentTimeMillis();
		RequestListResult result = service.retrieveRequestList(reqListContract);
		System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
		
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_performance_3() throws Exception {
		reqListContract.setShowAllFlag(true);
		reqListContract.setOpsPage(false);
		reqListContract.setHardwareRequestFlag(false);
		reqListContract.setChangeRequestFlag(false);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setHardwareRequestsPermission(false);
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("315000554");
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "02/28/2015");
		filterMap.put("serviceRequest.startDate", "12/01/2014");
//		filterMap.put("serviceRequestStatus", "Inprocess");
//		filterMap.put("requestType", Arrays.asList("Consumables Management", "Fleet Management", "BreakFix"));
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		long t0 = System.currentTimeMillis();
		RequestListResult result = service.retrieveRequestList(reqListContract);
		System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
		
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("List size: " + list.size());
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_IOICDetails() throws Exception {
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("623331717");
		reqListContract.setAccountId("1-P7PY8H");
		reqListContract.setShowAllFlag(true);
		reqListContract.setHardwareRequestsPermission(true);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "08/14/2015 18:33:18");
		filterMap.put("serviceRequest.startDate", "07/30/2015 04:00:00");
		filterMap.put("asset.serialNumber", "12345");
		filterMap.put("requestType", Arrays.asList("Consumables Management", "Fleet Management", "BreakFix", "Fleet Management"));
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		long t0 = System.currentTimeMillis();
		RequestListResult result = service.retrieveRequestList(reqListContract);
		System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_IOICDetails_AllRequest() throws Exception {
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("205529410");
//		reqListContract.setAccountId("1-P7PY8H");
		reqListContract.setShowAllFlag(true);
		reqListContract.setHardwareRequestsPermission(false);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "10/19/2015 12:12:28");
		filterMap.put("serviceRequest.startDate", "10/03/2015 18:30:00");
//		filterMap.put("asset.serialNumber", "12345");
		filterMap.put("requestType", Arrays.asList("Consumables Management", "Fleet Management", "BreakFix"));
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		long t0 = System.currentTimeMillis();
		RequestListResult result = service.retrieveRequestList(reqListContract);
		System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_IOICDetails_Supplies() throws Exception {
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("205529410");
//		reqListContract.setAccountId("1-P7PY8H");
		reqListContract.setShowAllFlag(true);
		reqListContract.setHardwareRequestsPermission(false);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "10/19/2015 12:13:25");
		filterMap.put("serviceRequest.startDate", "10/03/2015 18:30:00");
//		filterMap.put("asset.serialNumber", "12345");
		filterMap.put("requestType", Arrays.asList("Consumables Management"));
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		long t0 = System.currentTimeMillis();
		RequestListResult result = service.retrieveRequestList(reqListContract);
		System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveRequestList_IOICDetails_ChangeManagement() throws Exception {
		reqListContract.setMdmLevel("Global");
		reqListContract.setMdmId("205529410");
//		reqListContract.setAccountId("1-P7PY8H");
		reqListContract.setShowAllFlag(true);
		reqListContract.setHardwareRequestsPermission(false);
		reqListContract.setChangeRequestsPermission(true);
		reqListContract.setChangeRequestFlag(true);
		reqListContract.setIncrement(40);
		reqListContract.setStartRecordNumber(0);
		reqListContract.setSessionHandle(crmSessionHandle);
		reqListContract.setNewQueryIndicator(true);
		
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("serviceRequest.endDate", "10/19/2015 12:14:02");
		filterMap.put("serviceRequest.startDate", "10/03/2015 18:30:00");
//		filterMap.put("asset.serialNumber", "12345");
		filterMap.put("requestType", Arrays.asList("Fleet Management"));
		reqListContract.setFilterCriteria(filterMap);
		
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);
		
		long t0 = System.currentTimeMillis();
		RequestListResult result = service.retrieveRequestList(reqListContract);
		System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
		List<ServiceRequest> list = result.getRequestList();
		System.out.println("Total count: " + result.getTotalCount());
		System.out.println("END");
	}

	@Test
	public void testRetrieveSupplyRequestDetail_HardwareRequestDetails() throws Exception {
		reqContract.setSessionHandle(crmSessionHandle);
		reqContract.setServiceRequestNumber("1-25355336721");
		reqContract.setCreateChildSR(false);
		reqContract.setVisibilityRole("Employee");
		reqContract.setMadcServiceRequestFlag(false);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		System.out.println(result.getServiceRequest().getParts() == null);
	}	
	
	@Test
	public void testRetrieveSupplyRequestDetail_SuppliesRequestDetails() throws Exception {
		reqContract.setSessionHandle(crmSessionHandle);
		reqContract.setServiceRequestNumber("1-52547483347");
		reqContract.setCreateChildSR(false);
		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(false);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		System.out.println(result.getServiceRequest().getParts() == null);
	}	
	
	@Test
	public void testRetrieveSupplyRequestDetail_ChangeRequestDetails() throws Exception {
		reqContract.setSessionHandle(crmSessionHandle);
		reqContract.setServiceRequestNumber("1-52727497421");
		reqContract.setCreateChildSR(false);
		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(true);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		System.out.println(result.getServiceRequest().getParts() == null);
	}	
	
	@Test
	public void testRetrieveSupplyRequestDetail_ServiceRequestDetails() throws Exception {
		reqContract.setSessionHandle(crmSessionHandle);
		reqContract.setServiceRequestNumber("1-52254684614");
		reqContract.setCreateChildSR(false);
//		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(false);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		System.out.println(result.getServiceRequest().getParts() == null);
	}	
	
	@Test
	public void testRetrieveSupplyRequestDetail_QA_defect17704() throws Exception {
		reqContract.setSessionHandle(crmSessionHandle);
//		reqContract.setServiceRequestNumber("1-46364176111");
		reqContract.setServiceRequestNumber("1-46364175963");
		reqContract.setCreateChildSR(false);
//		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(false);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		for (ServiceRequestActivity srActivity : result.getServiceRequest().getServicewebUpdateActivities()) {
			System.out.println("ActivitySR: " + srActivity.getActivitySerialNumber());
		}
	}
	
	@Test
	public void testRetrieveSupplyRequestDetail_QA_defect17709() throws Exception {
		reqContract.setSessionHandle(crmSessionHandle);
		reqContract.setServiceRequestNumber("1-65248627652");
		reqContract.setCreateChildSR(false);
		reqContract.setVisibilityRole("Employee");
		reqContract.setMadcServiceRequestFlag(false);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);

		System.out.println("OrderLineItems size: " + result.getServiceRequest().getOrders().get(0).getOrderLineItems().size());
	}	
	
	@Test
	public void testRetrieveSupplyRequestDetail_QA_defect17705() throws Exception {
		reqContract.setSessionHandle(crmSessionHandle);
		reqContract.setServiceRequestNumber("1-65248627652");
		reqContract.setCreateChildSR(false);
		reqContract.setVisibilityRole("Employee");
		reqContract.setMadcServiceRequestFlag(false);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		
		System.out.println(result.getServiceRequest().getOrders().get(0).getOrderType());
	}	
	
	@Test
	public void testRetrieveSupplyRequestDetail_QA_propertySet() throws Exception {
		reqContract.setSessionHandle(crmSessionHandle);
		reqContract.setServiceRequestNumber("1-65248627652");
		reqContract.setCreateChildSR(false);
		reqContract.setVisibilityRole("Employee");
		reqContract.setMadcServiceRequestFlag(false);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		
		System.out.println("");
	}	
	
	@Test
	public void testRetrieveSupplyRequestDetail_QA_defect17656() throws Exception {
		reqContract.setSessionHandle(crmSessionHandle);
		reqContract.setServiceRequestNumber("1-52727497421");
		reqContract.setCreateChildSR(false);
		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(true);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		
		System.out.println("");
	}	
	
	@Test
	public void testRetrieveSupplyRequestDetail_QA_15_7Integration() throws Exception {
		reqContract.setSessionHandle(crmSessionHandle);
		reqContract.setServiceRequestNumber("1-54347458101");
		reqContract.setCreateChildSR(false);
//		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(false);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		System.out.println("activity size: " + result.getServiceRequest().getActivitywebUpdateActivities().size());
		System.out.println("activity2 size: " + result.getServiceRequest().getServicewebUpdateActivities().size());
	}	
	
	@Test
	public void testRetrieveSupplyRequestDetail_serviceRequestETA() throws Exception {
		reqContract.setSessionHandle(crmSessionHandle);
		reqContract.setServiceRequestNumber("1-46813686349");
		reqContract.setCreateChildSR(false);
//		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(false);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		System.out.println("ETA: " + result.getServiceRequest().getServicewebUpdateActivities().get(0).getServiceRequestETA());
		System.out.println("DateETA: " + result.getServiceRequest().getServicewebUpdateActivities().get(0).getServiceRequestDateETA());
	}
	
	@Test
	public void testRetrieveSupplyRequestDetail_defect17184() throws Exception {
		reqContract.setSessionHandle(crmSessionHandle);
		reqContract.setServiceRequestNumber("1-54308035621");
		reqContract.setCreateChildSR(false);
		reqContract.setVisibilityRole("Employee");
		reqContract.setMadcServiceRequestFlag(true);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		System.out.println("X: " + result.getServiceRequest().getAsset().getInstallAddress().getCoordinatesXPreDebriefRFV());
		System.out.println("Y: " + result.getServiceRequest().getAsset().getInstallAddress().getCoordinatesYPreDebriefRFV());
	}	
	
	@Test
	public void testRetrieveSupplyRequestDetail_defect17917() throws Exception {
		reqContract.setSessionHandle(crmSessionHandle);
		reqContract.setServiceRequestNumber("1-45439349331");
		reqContract.setCreateChildSR(false);
//		reqContract.setVisibilityRole("Employee");
		reqContract.setMadcServiceRequestFlag(false);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
	}	
	
	@Test
	public void testRetrieveSupplyRequestDetail_defect16731() throws Exception {
		reqContract.setSessionHandle(crmSessionHandle);
		reqContract.setServiceRequestNumber("1-54963026627");
		reqContract.setCreateChildSR(false);
		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(true);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
	}	
	
	@Test
	public void testRetrieveSupplyRequestDetail_defect17706() throws Exception {
		reqContract.setSessionHandle(crmSessionHandle);
		reqContract.setServiceRequestNumber("1-46464109772");
		reqContract.setCreateChildSR(false);
//		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(false);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		for (ServiceRequestActivity srActivity : result.getServiceRequest().getServicewebUpdateActivities()) {
			System.out.println(srActivity.getActivityDescription());
			System.out.println(srActivity.getDeviceType());
		}
	}
	
	@Test
	public void testRetrieveSupplyRequestDetail_SRcreation() throws Exception {
		reqContract.setSessionHandle(crmSessionHandle);
		reqContract.setServiceRequestNumber("1-55874194561");
		reqContract.setCreateChildSR(false);
		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(true);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
		System.out.println();
	}	
	
	@Test
	public void testRetrieveSupplyRequestDetail_CriticalPathCR() throws Exception {
		reqContract.setSessionHandle(crmSessionHandle);
		reqContract.setServiceRequestNumber("1-54935230611");
		reqContract.setCreateChildSR(false);
		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(true);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
	}	
	
	@Test
	public void testRetrieveSupplyRequestDetail_CriticalPathCR_test() throws Exception {
		reqContract.setSessionHandle(crmSessionHandle);
		reqContract.setServiceRequestNumber("1-55981460901");
		reqContract.setCreateChildSR(false);
		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(true);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
	}	
	
	@Test
	public void testRetrieveSupplyRequestDetail_NoData() throws Exception {
		reqContract.setSessionHandle(crmSessionHandle);
		reqContract.setServiceRequestNumber("1-55874194521");
		reqContract.setCreateChildSR(false);
		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(true);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
	}	
	
	@Test
	public void testRetrieveSupplyRequestDetail_defect17198() throws Exception {
		reqContract.setSessionHandle(crmSessionHandle);
		reqContract.setServiceRequestNumber("1-55874194601");
		reqContract.setCreateChildSR(false);
		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(true);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
	}	
	
	@Test
	public void testRetrieveSupplyRequestDetail_coveredService() throws Exception {
		reqContract.setSessionHandle(crmSessionHandle);
		reqContract.setServiceRequestNumber("1-55574228103");
		reqContract.setCreateChildSR(false);
		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(true);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
	}	
	
	@Test
	public void testRetrieveSupplyRequestDetail_INC0136099_Error() throws Exception {
		reqContract.setSessionHandle(crmSessionHandle);
		reqContract.setServiceRequestNumber("1-82791375321");
		reqContract.setCreateChildSR(false);
//		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(false);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
	}	
	
	@Test
	public void testRetrieveSupplyRequestDetail_INC0157768() throws Exception {
		reqContract.setSessionHandle(crmSessionHandle);
		reqContract.setServiceRequestNumber("1-105920919851");
		reqContract.setCreateChildSR(false);
//		reqContract.setVisibilityRole("Customer");
		reqContract.setMadcServiceRequestFlag(false);
		reqContract.setIncrement(0);
		reqContract.setStartRecordNumber(0);
		RequestResult result = service.retrieveSupplyRequestDetail(reqContract);
	}	
	
	@Test
	public void testRetrieveMadcRequestList() throws Exception {
		RequestListContract contract = new RequestListContract();
		contract.setMdmId("1-ZIFBCQ");
		contract.setMdmLevel("Siebel");
		contract.setShowAllFlag(true);
		contract.setChangeRequestsPermission(true);
		contract.setNewQueryIndicator(true);
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setSessionHandle(crmSessionHandle);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("webStatus", Arrays.asList("Submitted", "Inprocess", "Draft"));
		filterCriteria.put("assetId", "1-DMX0-1791");
		contract.setFilterCriteria(filterCriteria);
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);		
		
		RequestListResult result = service.retrieveMadcRequestList(contract);
		System.out.println(result.getTotalCount());
	}
	
	@Test
	public void testRetrieveMadcRequestList_defect18256() throws Exception {
		RequestListContract contract = new RequestListContract();
		contract.setMdmId("275242212");
		contract.setMdmLevel("Global");
//		contract.setAssetId("1-MPBHU07");
		contract.setShowAllFlag(true);
		contract.setChangeRequestsPermission(true);
		contract.setNewQueryIndicator(true);
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setSessionHandle(crmSessionHandle);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("webStatus", Arrays.asList("Submitted", "Inprocess", "Draft", "Shipped"));
		filterCriteria.put("requestType", "Fleet Management");
		filterCriteria.put("assetId", "1-MPBHU07");
		contract.setFilterCriteria(filterCriteria);
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("serviceRequestNumber", "DESCENDING");
		reqListContract.setSortCriteria(sortMap);		
		
		RequestListResult result = service.retrieveMadcRequestList(contract);
		System.out.println(result.getTotalCount());
	}
}
