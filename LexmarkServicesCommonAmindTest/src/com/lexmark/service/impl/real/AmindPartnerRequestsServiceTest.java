package com.lexmark.service.impl.real;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;

import com.lexmark.contract.ActivityDetailContract;
import com.lexmark.contract.ActivityListContract;
import com.lexmark.contract.AssignedTechnicianUpdateContract;
import com.lexmark.contract.ClaimDetailContract;
import com.lexmark.contract.CustomerAccountListContract;
import com.lexmark.contract.FRUPartDetailContract;
import com.lexmark.contract.FRUPartListContract;
import com.lexmark.contract.GlobalAssetDetailContract;
import com.lexmark.contract.GlobalPartnerAssetListContract;
import com.lexmark.contract.OfflineModeAttachmentContract;
import com.lexmark.contract.OpenClaimListContract;
import com.lexmark.contract.PartnerAddressListContract;
import com.lexmark.contract.PartnerFavoriteAddressUpdateContract;
import com.lexmark.contract.PartnerIndirectAccountListContract;
import com.lexmark.contract.PartnerNotificationsContract;
import com.lexmark.contract.ServiceActivityHistoryDetailContract;
import com.lexmark.contract.ServiceActivityHistoryListContract;
import com.lexmark.contract.TechnicianListContract;
import com.lexmark.contract.ValidateInstalledPrinterSerialNumberContract;
import com.lexmark.contract.source.OrderAcceptContract;
import com.lexmark.contract.source.RequestAcceptContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Activity;
import com.lexmark.domain.ActivityNote;
import com.lexmark.domain.Asset;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.Debrief;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.Part;
import com.lexmark.domain.PartLineItem;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.ServicesUser;
import com.lexmark.result.ActivityDetailResult;
import com.lexmark.result.ActivityListResult;
import com.lexmark.result.AssignedTechnicianUpdateResult;
import com.lexmark.result.ClaimDetailResult;
import com.lexmark.result.CustomerAccountListResult;
import com.lexmark.result.DownloadClaimListResult;
import com.lexmark.result.DownloadRequestListResult;
import com.lexmark.result.FRUPartDetailResult;
import com.lexmark.result.FRUPartListResult;
import com.lexmark.result.GlobalAssetDetailResult;
import com.lexmark.result.GlobalPartnerAssetListResult;
import com.lexmark.result.OpenClaimListResult;
import com.lexmark.result.PartnerAddressListResult;
import com.lexmark.result.PartnerClaimCreateIdResult;
import com.lexmark.result.PartnerFavoriteAddressUpdateResult;
import com.lexmark.result.PartnerIndirectAccountListResult;
import com.lexmark.result.PartnerNotificationsResult;
import com.lexmark.result.ServiceActivityHistoryDetailResult;
import com.lexmark.result.ServiceActivityHistoryListResult;
import com.lexmark.result.TechnicianListResult;
import com.lexmark.result.ValidateInstalledPrinterSerialNumberResult;
import com.lexmark.result.source.RequestAcceptResult;
import com.lexmark.service.impl.real.domain.PartnerActivityDo;
import com.lexmark.service.impl.real.enums.ActivityStatus;
import com.lexmark.util.LangUtil;

public class AmindPartnerRequestsServiceTest extends AmindServiceTest {

	protected static final Log logger = LogFactory
			.getLog(AmindPartnerRequestsServiceTest.class);

	// PartnerRequestsService service;
	AmindPartnerRequestsService service;
	OrderAcceptContract contract;

	@Before
	public void setUp() {
		service = new AmindPartnerRequestsService();
		service.setStatelessSessionFactory(statelessSessionFactory);
		contract = new OrderAcceptContract();
	}

	// @Test
	// public void acceptServiceOrderRequestTest() throws Exception {
	// contract.setOrderLineItemId("1-2GOHCUU");
	// contract.setStatus("Order Accepted");
	// OrderAcceptResult result = service.acceptServiceOrderRequest(contract);
	// assertNotNull(result);
	// assertTrue("Result should be true", result.getResult());
	// }

	@Test
	public void testRetrieveActivityList() throws Exception {
		ActivityListContract listContract = new ActivityListContract();
		listContract.setSessionHandle(crmSessionHandle);
		listContract.setMdmId("23259");
		listContract.setMdmLevel("Legal");
		listContract.setStatus("Open");
		listContract.setEmployeeId("1-531BSKD");
		listContract.setQueryType("All");
		listContract.setServiceRequestType("All");
		listContract.setIncrement(40);
		listContract.setStartRecordNumber(0);
		listContract.setNewQueryIndicator(true);
		listContract.setChangeManagementFlag(true);
		ActivityListResult r = service.retrieveActivityList(listContract);
		MiscTest.print(r.getActivityList());
	}

	@Test
	public void testRetrieveActivityList_Defect2813() throws Exception {
		ActivityListContract listContract = new ActivityListContract();
		listContract.setSessionHandle(crmSessionHandle);
		listContract.setMdmId("018978783");
		listContract.setMdmLevel("Global");
		// listContract.setStatus("Open");
		listContract.setStatus(ActivityStatus.All.toString());
		listContract.setQueryType("Unassigned");
		listContract.setServiceRequestType("All");
		listContract.setNewQueryIndicator(true);
		ActivityListResult r = service.retrieveActivityList(listContract);
		MiscTest.print(r.getActivityList(), "activityId", "activityType");
	}

	@Test
	public void testRetrieveActivityList_Defect4281() throws Exception {
		ActivityListContract listContract = new ActivityListContract();
		listContract.setSessionHandle(crmSessionHandle);
		listContract.setMdmId("159489");
		listContract.setMdmLevel("Legal");
		// listContract.setStatus("Open");
		listContract.setStatus(ActivityStatus.All.toString());
		listContract.setQueryType("My");
		listContract.setEmployeeId("1-RJR0JG");
		listContract.setServiceRequestType("All");
		listContract.setNewQueryIndicator(true);
		ActivityListResult r = service.retrieveActivityList(listContract);
		// MiscTest.print(r.getActivityList(), "activityId", "activityType");
		MiscTest.print(r.getActivityList());
	}

	@Test
	public void query_Defect4281() throws Exception {
		MiscTest.sampleSiebelQuery(
				PartnerActivityDo.class,
				// "[LXK SW SP Legal Entity Number] = '159489' AND [LXK SW SP Account Level] = 'Siebel' AND [LXK R Tech Id] IS NULL  AND ([Status] = 'Cancelled Service'  OR [Status] = 'Pending Service Action' OR [Status] = 'Completed'  OR [Status] = 'Claim Submitted'  OR [Status] = 'Invalid Debrief' OR [Status] = 'Dispatched to SP' OR [Status] = 'Cancelled Parts without Visit') AND ([LXK MPS Override Offering] <> 'Consumables Order and Activity' AND [LXK MPS Override Offering] <> 'Consumables Order' AND [LXK MPS Override Offering] <> 'Consumables Activity' OR [LXK MPS Override Offering] IS NULl )"
				"[LXK SW SP Legal Entity Number] = '159489' AND [LXK SW SP Account Level] = 'Siebel' AND ([Status] = 'Cancelled Service'  OR [Status] = 'Pending Service Action' OR [Status] = 'Completed'  OR [Status] = 'Claim Submitted'  OR [Status] = 'Invalid Debrief' OR [Status] = 'Dispatched to SP' OR [Status] = 'Cancelled Parts without Visit') AND ([LXK MPS Override Offering] <> 'Consumables Order and Activity' AND [LXK MPS Override Offering] <> 'Consumables Order' AND [LXK MPS Override Offering] <> 'Consumables Activity' OR [LXK MPS Override Offering] IS NULl )",
				10);
	}

	@Test
	public void testRetrieveActivityDetail() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-4FU31H2");
		ActivityDetailResult r = service.retrieveActivityDetail(contract);
		System.out.println(str(r.getActivity()));
	}

	@Test
	public void testRetrieveActivityDetail_QA_20130218() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-11312286606");
		contract.setServiceRequestId("1-11312286606");
		// contract.setActivityId("9836858785");
		ActivityDetailResult r = service.retrieveActivityDetail(contract);
		System.out.println(str(r.getActivity()));
	}

	@Test
	public void h() throws Exception {
		ClaimDetailContract contract = new ClaimDetailContract();
		contract.setActivityId("1-4FU31H2");
		contract.setDebriefFlag(Boolean.TRUE);
		ClaimDetailResult r = service.retrieveClaimDetail(contract);
		System.out.println(str(r.getActivity()));
	}

	@Test
	public void testRetrieveFRUPart_QA() throws Exception {
		FRUPartDetailContract contract = new FRUPartDetailContract();
		contract.setPartnerOrg("mock-partnerOrg");
		contract.setPartNumber("mock-partNumber");
		contract.setModelNumber("mock-modelNumber");

		FRUPartDetailResult result = service.retrieveFRUPart(contract);
		MiscTest.print("", str(result.getPart()));
	}

	@Test
	public void testRetrieveFRUPart_QA_with() throws Exception {
		FRUPartDetailContract contract = new FRUPartDetailContract();
		contract.setPartnerOrg("mock-partnerOrg");
		contract.setPartNumber("mock-partNumber");
		contract.setModelNumber("mock-modelNumber");
		contract.setReplacementFlag(true);

		FRUPartDetailResult result = service.retrieveFRUPart(contract);
		MiscTest.print("", str(result.getPart()));
	}

	@Test
	public void testValidateInstalledPrinterSerialNumber() throws Exception {
		ValidateInstalledPrinterSerialNumberContract contract = new ValidateInstalledPrinterSerialNumberContract();
		// contract.setActivityId("mock-activityId");
		// contract.setServiceRequestId("mock-serviceRequestId");
		contract.setModelNumber("mock-ModelNumber");
		contract.setSerialNumber("mock-SerialNumber");
		ValidateInstalledPrinterSerialNumberResult result = service
				.validateInstalledPrinterSerialNumber(contract);
		System.out.println(result.getSuccess());

	}

	@Test
	public void testRetrievePartnerAddressList() throws Exception {
		PartnerAddressListContract contract = new PartnerAddressListContract();
		contract.setAccountID("mock-accountID");
		contract.setContactId("mock-contactId");
		contract.setFavoriteFlag(false);

		PartnerAddressListResult result = service
				.retrievePartnerAddressList(contract);
		MiscTest.print(result.getAddressList());
	}

	@Test
	public void testRetrievePartnerAddressList_withFavoriteFlag()
			throws Exception {
		PartnerAddressListContract contract = new PartnerAddressListContract();
		contract.setAccountID("mock-accountID");
		contract.setContactId("mock-contactId");
		contract.setFavoriteFlag(true);

		PartnerAddressListResult result = service
				.retrievePartnerAddressList(contract);
		MiscTest.print(result.getAddressList());
	}

	@Test
	public void testUpdateAssignedTechnician() throws Exception {
		AssignedTechnicianUpdateContract c = new AssignedTechnicianUpdateContract();
		c.setActivityId("mock-activityId");
		c.setEmployeeId("mock-employeeId");
		AssignedTechnicianUpdateResult r = service.updateAssignedTechnician(c);
		System.out.println("result = " + r.getResult());
	}

	@Test
	public void testRetrieveServiceActivityHistoryList() throws Exception {
		ServiceActivityHistoryListContract c = new ServiceActivityHistoryListContract();
		c.setAssetId("mock-assetId");
		ServiceActivityHistoryListResult r = service
				.retrieveServiceActivityHistoryList(c);
		MiscTest.print(r.getActivityList());
	}

	@Test
	public void testRetrieveServiceActivityHistoryDetail() throws Exception {
		ServiceActivityHistoryDetailContract c = new ServiceActivityHistoryDetailContract();
		c.setServiceRequestId("mock-serviceRequestId");
		ServiceActivityHistoryDetailResult r = service
				.retrieveServiceActivityHistoryDetail(c);
		System.out.println(str(r.getActivity()));
	}

	@Test
	public void testRetrieveOpenClaimList() throws Exception {
		OpenClaimListContract c = new OpenClaimListContract();
		c.setAssetId("mock-assetId");
		c.setMdmId("mock-mdmId");
		c.setMdmLevel("Global");
		OpenClaimListResult r = service.retrieveOpenClaimList(c);

		System.out.println("Size: " + r.getClaimList().size());
		System.out.println("Total count: " + r.getTotalCount());

		MiscTest.print(r.getClaimList());
	}

	@Test
	public void testRetrievePartnerIndirectAccountList_2() throws Exception {
		PartnerIndirectAccountListContract c = new PartnerIndirectAccountListContract();
		c.setMdmId("mock-mdmId");
		c.setMdmLevel("Legal");
		PartnerIndirectAccountListResult r = service
				.retrievePartnerIndirectAccountList(c);
		MiscTest.print(r.getAccountList());
	}
	
	@Test
	public void testRetrievePartnerIndirectAccountList() throws Exception {
		PartnerIndirectAccountListContract c = new PartnerIndirectAccountListContract();
		c.setMdmId("1-6ITI8RI");
		c.setMdmLevel("Siebel");
		PartnerIndirectAccountListResult r = service.retrievePartnerIndirectAccountList(c);
		System.out.println(r.getAccountList().get(0).getAddress().getPrimaryAddressLine2());
	}
	
	@Test
	public void testRetrievePartnerIndirectAccountList_() throws Exception {
		PartnerIndirectAccountListContract c = new PartnerIndirectAccountListContract();
		c.setMdmId("56627");
		c.setMdmLevel("Legal");
		PartnerIndirectAccountListResult r = service.retrievePartnerIndirectAccountList(c);
		System.out.println(r.getAccountList().get(0).getAddress().getPrimaryAddressLine2());
	}
	

	@Test
	public void testRetrieveGlobalAssetDetail() throws Exception {
		GlobalAssetDetailContract c = new GlobalAssetDetailContract();
		c.setAssetId("mock-assetId");
		GlobalAssetDetailResult r = service.retrieveGlobalAssetDetail(c);
		System.out.println(str(r.getAsset()));
	}
	
	@Test
	public void testRetrieveGlobalAssetDetail_INC0115558() throws Exception {
		GlobalAssetDetailContract c = new GlobalAssetDetailContract();
		c.setAssetId("1-PZRU-7037");
		GlobalAssetDetailResult r = service.retrieveGlobalAssetDetail(c);
		System.out.println(str(r.getAsset()));
	}

	@Test
	public void testRetrieveGlobalPartnerAssetList() throws Exception {
		GlobalPartnerAssetListContract c = new GlobalPartnerAssetListContract();
		c.setSerialNumber("mock-serialNumber");
		GlobalPartnerAssetListResult r = service
				.retrieveGlobalPartnerAssetList(c);
//		MiscTest.print(r.getAssetList());
	}
	
	@Test
	public void testRetrieveGlobalPartnerAssetList_defect18496() throws Exception {
		GlobalPartnerAssetListContract c = new GlobalPartnerAssetListContract();
		c.setSerialNumber("79G61YY");
		GlobalPartnerAssetListResult r = service.retrieveGlobalPartnerAssetList(c);
		MiscTest.print(r.getAssetList());
	}

	@Test
	public void testRetrieveCustomerAccountList() throws Exception {
		CustomerAccountListContract c = new CustomerAccountListContract();
		c.setMdmId("mock-mdmId");
		c.setMdmLevel("Global");
		CustomerAccountListResult r = service.retrieveCustomerAccountList(c);
		MiscTest.print(r.getAccountList());
	}

	@Test
	public void testRetrieveTechnicianList() throws Exception {
		TechnicianListContract c = new TechnicianListContract();
		c.setPartnerAccountId("mock-partnerAccountId");
		TechnicianListResult r = service.retrieveTechnicianList(c);
		MiscTest.print(r.getAccountContactList());
	}

	@Test
	public void testUpdatePartnerUserFavoriteAddress() throws Exception {
		PartnerFavoriteAddressUpdateContract c = new PartnerFavoriteAddressUpdateContract();
		c.setPartnerAccountId("mock-partnerAccountId");
		c.setContactId("mock-contactId");
		c.setFavoriteAddressId("mock-favoriteAddressId");
		PartnerFavoriteAddressUpdateResult r = service
				.updatePartnerUserFavoriteAddress(c);
		System.out.println("result = " + r.isResult());

	}

	@Test
	public void testRetrievePartnerNotifications() throws Exception {
		PartnerNotificationsContract c = new PartnerNotificationsContract();
		c.setServiceRequestId("mock-serviceRequestId");
		c.setEmailAddress("mock-emailAddress");
		PartnerNotificationsResult r = service.retrievePartnerNotifications(c);
		MiscTest.print(r.getServiceRequestActivityList());
	}

	/**
	 * @see com.lexmark.service.impl.real.partnerActivityService.AmindPartnerActivitySearchSpecUtil#buildActivityListSearchSpec(ActivityListContract,
	 *      com.amind.data.service.IDataManager, java.util.Map)
	 * @see com.lexmark.service.impl.real.domain.PartnerActivityDetailDo
	 * @see com.lexmark.service.impl.real.domain.PartnerActivityDetailDoTest#query1()
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testRetrieveDownloadRequestList() throws Exception {
		ActivityListContract c = new ActivityListContract();
		c.setStatus("mock-status");
		c.setQueryType("mock-queryType");
		c.setServiceRequestType("mock-serviceRequestType");
		c.setMdmId("mock-mdmId");
		c.setMdmLevel("Global");
		c.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
				"Activity.activityType", "filter-activityType-v1",
				"Activity.activityStatus", "filter-status-v1"));
		c.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"Activity.activityType", "ASCENDING",
				"Activity.activityStatus", "DESCENDING"));

		DownloadRequestListResult r = service.retrieveDownloadRequestList(c);

		System.out.println("Size: " + r.getActivityList().size());
		System.out.println("Total count: " + r.getTotalcount());

		// MiscTest.print(r.getActivityList());
	}

	@Test
	public void testBooleanOperations() throws Exception {
		assertTrue(false && false || true);
	}

	// TODO(Viktor) the same issue as in #testRetrieveDownloadRequestList()
	@Test
	public void testRetrieveDownloadClaimList() throws Exception {
		ActivityListContract c = new ActivityListContract();
		c.setStatus("mock-status");
		c.setQueryType("mock-queryType");
		c.setServiceRequestType("mock-serviceRequestType");
		c.setMdmId("mock-mdmId");
		c.setMdmLevel("Global");
		DownloadClaimListResult r = service.retrieveDownloadClaimList(c);
		MiscTest.print(r.getActivityList());
	}

	@Test
	public void testRetrieveCreateClaimId() throws Exception {
		String claimNumber = "mock-claimNumber";
		// String claimNumber = null;
		PartnerClaimCreateIdResult r = service
				.retrieveCreateClaimId(claimNumber);
		System.out.println("result = " + str(r));
	}

	@Test
	public void testRetrieveServiceActivityHistoryListNoData() throws Exception {
		ServiceActivityHistoryListContract contract = new ServiceActivityHistoryListContract();
		contract.setAssetId("1-GCVP-17");
		contract.setServiceRequestId("1-5SMXD8I");

		// contract.setAssetId("1-17X3WE4");
		// contract.setServiceRequestId("1-64K43Q3");

		ServiceActivityHistoryListResult result = service
				.retrieveServiceActivityHistoryList(contract);
		System.out.println(result.getActivityList().size());
		System.out.println(result.getTotalcount());

	}

	@Test
	public void testRetrieveCustomerAccountList_EmptyAccountList()
			throws Exception {
		CustomerAccountListContract contract = new CustomerAccountListContract();
		contract.setMdmId("19472");
		contract.setMdmLevel("Legal");

		CustomerAccountListResult result = service
				.retrieveCustomerAccountList(contract);

		System.out.println(result.getTotalCount());
		System.out.println(result.getAccountList().size());

		MiscTest.print(result.getAccountList());
	}

	@Test
	public void testRetrieveActivityDetail_BRD131021() throws Exception {

		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-5SE9EJI");
		// contract.setServiceRequestId("1-13336342491");
		contract.setServiceRequestId("1-12600646571");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		System.out.println(result.getActivity().getServicerComments());
		System.out.println(result.getActivity()
				.getServiceProviderReferenceNumber());

	}

	@Test
	public void testRetrieveCustomerAccountList_BRD131001() throws Exception {
		CustomerAccountListContract contract = new CustomerAccountListContract();

		contract.setMdmId("19472");
		contract.setMdmLevel("Legal");

		CustomerAccountListResult result = service
				.retrieveCustomerAccountList(contract);

		System.out.println(result.getAccountList().size());
		System.out.println(result.getTotalCount());
	}

	@Test
	public void testRetrieveActivityList_WaveFourIntegrations()
			throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus(ActivityStatus.All.toString());
		contract.setMdmLevel("Legal");
		contract.setMdmId("107998");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-5152VS9");
		contract.setServiceRequestType("All");
		contract.setEmployeeFlag(false);
		contract.setQueryType("All");
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("09/27/2013 04:00:00"));
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("08/21/2013 04:00:00"));
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveActivityList(contract);

		if (result.getActivityList() != null) {
			System.out.println("Size: " + result.getActivityList().size());
		}

		System.out.println(result.getActivityList() == null);

		System.out.println("Total count: " + result.getTotalcount());

	}

	@Test
	public void testRetrieveActivityList_PartnerPortalRequestTabThrowingException()
			throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus(ActivityStatus.Open.toString());
		contract.setMdmLevel("Legal");
		contract.setMdmId("23259");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-687WSZZ");
		contract.setServiceRequestType("All");
		contract.setEmployeeFlag(false);
		contract.setQueryType("All");
		contract.setChangeManagementFlag(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("10/22/2013 04:00:00"));
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("08/26/2013 04:00:00"));
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = null;

		try {
			result = service.retrieveActivityList(contract);
		} catch (Exception ex) {
			System.out.println(ex.getCause());
		}

		if (result.getActivityList() != null) {
			System.out.println("Size: " + result.getActivityList().size());
		}

		System.out.println(result.getActivityList() == null);

		System.out.println("Total count: " + result.getTotalcount());

	}

	@Test
	public void testRetrieveActivityDetail_defect9118() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-68H6QZ2");
		contract.setServiceRequestId("1-68H5NNR");
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		System.out.println(result.getActivity() == null);

		Activity activity = result.getActivity();

		List<PartLineItem> reccomendedParts = new ArrayList<PartLineItem>();

		for (PartLineItem part : reccomendedParts) {
			System.out.println("Carrier: " + part.getCarrier());
			System.out.println("Tracking #: " + part.getTrackingNumber());
		}
	}

	@Test
	public void testRetrieveActivityDetail_defect9118_2() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-68H7HHG");
		contract.setServiceRequestId("1-68H5NZB");
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		System.out.println(result.getActivity() == null);

		Activity activity = result.getActivity();

		List<PartLineItem> reccomendedParts = new ArrayList<PartLineItem>();

		for (PartLineItem part : reccomendedParts) {
			System.out.println("Carrier: " + part.getCarrier());
			System.out.println("Tracking #: " + part.getTrackingNumber());
		}
	}

	@Test
	public void testRetrieveActivityDetail_defect9118_3() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-628344N");
		contract.setServiceRequestId("1-626QVPH");
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		System.out.println(result.getActivity() == null);

		Activity activity = result.getActivity();

		List<PartLineItem> reccomendedParts = new ArrayList<PartLineItem>();

		for (PartLineItem part : reccomendedParts) {
			System.out.println("Carrier: " + part.getCarrier());
			System.out.println("Tracking #: " + part.getTrackingNumber());
		}
	}

	@Test
	public void testRetrieveActivityDetail_AlternatePhonePopulation()
			throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-5SLS8VC");
		contract.setServiceRequestId("1-5SL32GS");
		// contract.setActivityId("1-5SE9EJI");
		// contract.setServiceRequestId("1-5SE3L4B");
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		System.out.println(result.getActivity() == null);

		Activity activity = result.getActivity();

		if (activity != null) {
			ServiceRequest request = activity.getServiceRequest();
			if (request != null) {
				AccountContact primaryContact = request.getPrimaryContact();
				AccountContact secondaryContact = request.getSecondaryContact();
				System.out
						.println(primaryContact == null ? "Primary contact is null"
								: "Primary contact alternate phone: "
										+ primaryContact.getAlternatePhone());
				System.out
						.println(secondaryContact == null ? "Secondary contact is null"
								: "Secondary contact alternate phone: "
										+ secondaryContact.getAlternatePhone());
				System.out.println(primaryContact.getFirstName());
				System.out.println(primaryContact.getLastName());
				System.out.println(primaryContact.getWorkPhone());
			}
		}
	}

	@Test
	public void testRetrieveActivityDetail_defect9807() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-5PB5BMV");
		contract.setServiceRequestId("1-5P9N1M3");
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		System.out.println(result.getActivity() == null);
	}

	@Test
	public void testRetrieveActivityDetail_defect9844() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-5PB5BMV");
		contract.setServiceRequestId("1-5P9N1M3");
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		System.out.println(result.getActivity() == null);
	}

	@Test
	public void testRetrieveActivityList_Defect0000() throws Exception {
		ActivityListContract listContract = new ActivityListContract();
		listContract.setSessionHandle(crmSessionHandle);
		listContract.setMdmId("23259");
		listContract.setMdmLevel("Legal");
		listContract.setStatus("Open");
		listContract.setStatus(ActivityStatus.All.toString());
		listContract.setQueryType("All");
		listContract.setEmployeeId("1-687WSZZ");
		listContract.setServiceRequestType("All");
		listContract.setIncrement(40);
		listContract.setStartRecordNumber(0);
		listContract.setNewQueryIndicator(true);
		listContract
				.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
						"Activity.activityDate.endDate",
						LangUtil.convertStringToGMTDate("11/04/2013 08:00:00"),
						"Activity.activityDate.startDate",
						LangUtil.convertStringToGMTDate("10/04/2013 08:00:00")));
		listContract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
				"Activity.activityDate", "DESCENDING"));

		ActivityListResult r = service.retrieveActivityList(listContract);
		// MiscTest.print(r.getActivityList(), "activityId", "activityType");
		MiscTest.print(r.getActivityList());
	}

	@Test
	public void testRetrieveActivityList_defect9469() throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus(ActivityStatus.All.toString());
		contract.setMdmLevel("Legal");
		contract.setMdmId("23259");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("Claim Request");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-51NNZ0T");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("11/04/2013 23:30:00"));
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("10/04/2013 22:30:00"));
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveActivityList(contract);

		if (result.getActivityList() != null) {
			System.out.println("Size: " + result.getActivityList().size());
		}

		System.out.println(result.getActivityList() == null);

		System.out.println("Total count: " + result.getTotalcount());

		if (result.getActivityList() != null) {
			for (Activity activity : result.getActivityList()) {
				System.out.println("Status: "
						+ activity.getServiceRequest().getRequestType());
			}
		}
	}

	@Test
	public void testRetrieveActivityList_IssueInPartnerInstallDebriefListIntegration()
			throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Siebel");
		contract.setMdmId("1-NMCFPJ");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-7E1VKFD");
		contract.setServiceRequestType("MADCRequests");
		contract.setEmployeeFlag(false);
		contract.setQueryType("All");
		contract.setChangeManagementFlag(true);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveActivityList(contract);

		if (result.getActivityList() != null) {
			System.out.println("Size: " + result.getActivityList().size());
		}

		System.out.println(result.getActivityList() == null);

		System.out.println("Total count: " + result.getTotalcount());

		if (result.getActivityList() != null) {
			for (Activity activity : result.getActivityList()) {
				System.out.println("Status: "
						+ activity.getServiceRequest().getRequestType());
			}
		}
	}

	@Test
	public void testRetrieveActivityList_Wave4Integrations() throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Siebel");
		contract.setMdmId("1-NMCFPJ");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("Fleet Management");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-5TSGLON");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(true);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("11/13/2013 00:00:00"));
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("10/13/2013 00:00:00"));
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.serviceRequest.serviceRequestNumber",
				"DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveActivityList(contract);

		List<Activity> activities = result.getActivityList();

		for (Activity activity : activities) {
			ServiceRequest serviceRequest = activity.getServiceRequest();
			Asset asset = serviceRequest.getAsset();

			System.out.println("------------------");
			System.out
					.println("Store front name: " + asset.getStoreFrontName());
			System.out.println("Install date: " + asset.getInstallDate());
			System.out.println("Service Request End Date: "
					+ serviceRequest.getActualEndDate());
			System.out.println("Project name: "
					+ serviceRequest.getProjectName());
			System.out.println("Responce Metric: "
					+ activity.getResponseMetric());
			System.out.println("Status detail: "
					+ serviceRequest.getStatusDetail());
			System.out.println("Product line: " + asset.getProductLine());
			System.out.println("Product model: " + asset.getModelNumber());
			System.out.println("Product Tli: " + asset.getProductTLI());
			System.out.println("Serial number: " + asset.getSerialNumber());
		}
	}

	@Test
	public void testRetrieveActivityDetail_Wave4Intgerations() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-7GEU1G8");
		contract.setServiceRequestId("1-16232084933");
		contract.setDebriefFlag(false);

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		System.out.println(result.getActivity().getServiceRequest() == null);
	}

	@Test
	public void testRetrieveActivityList_Wave4Integrations_2() throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Siebel");
		contract.setMdmId("1-NMCFPJ");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-687WSZZ");
		contract.setServiceRequestType("Fleet Management");
		contract.setEmployeeFlag(false);
		contract.setQueryType("Pending");
		contract.setChangeManagementFlag(true);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("11/14/2013 18:30:00"));
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("10/14/2013 18:30:00"));
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.serviceRequest.serviceRequestNumber",
				"DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveActivityList(contract);

		List<Activity> activities = result.getActivityList();

		int a = 0;
		int i = 0;
		for (Activity activity : activities) {
			if (activity.getServiceRequest().getServiceRequestNumber()
					.equalsIgnoreCase("1-16232084933")) {
				a++;
				System.out.println("i = "
						+ i
						+ " SR: "
						+ activity.getServiceRequest()
								.getServiceRequestNumber());
			}

			i++;
		}

		System.out.println("Total count: " + result.getTotalcount());
		System.out.println("SRs count: " + a);
	}

	@Test
	public void testRetrieveActivityDetail_defect10293() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-7MWSY7F");
		contract.setServiceRequestId("1-16629075501");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		System.out.println(result.getActivity().getServiceRequest() == null);
	}

	@Test
	public void testAcceptRejectRequest() throws Exception {
		RequestAcceptContract contract = new RequestAcceptContract();
		contract.setStatus("Accepted");
		contract.setActivityId("1-7MWSY77");
		contract.setRequestNumber("1-16629075501");

		RequestAcceptResult result = service.acceptRejectRequest(contract);

		Boolean operationResult = result.getResult();

		System.out.println(operationResult);
	}

	@Test
	public void testRetrieveActivityList_defect10334() throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Siebel");
		contract.setMdmId("1-NMCFPJ");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("Fleet Management");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-73O5NHR");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(true);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("11/22/2013 00:00:00"));
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("10/22/2013 00:00:00"));
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.serviceRequest.serviceRequestNumber",
				"DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveActivityList(contract);

		List<Activity> activities = result.getActivityList();

		for (Activity activity : activities) {
			ServiceRequest serviceRequest = activity.getServiceRequest();
			Asset asset = serviceRequest.getAsset();

			System.out.println("------------------");
			System.out.println("Product Tli: " + asset.getProductTLI());
			System.out.println("Serial number: " + asset.getSerialNumber());
			System.out.println("Product line: " + asset.getProductLine());
		}
	}

	@Test
	public void testAcceptRejectRequest_defect10328() throws Exception {
		RequestAcceptContract contract = new RequestAcceptContract();
		contract.setStatus("Accepted");
		contract.setActivityId("1-7MWSY7F");
		contract.setRequestNumber("1-16629075501");

		RequestAcceptResult result = service.acceptRejectRequest(contract);

		Boolean operationResult = result.getResult();

		System.out.println(operationResult);
	}

	@Test
	public void testRetrieveActivityDetail_defect10315() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-7MWSY7F");
		contract.setServiceRequestId("1-16629075501");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		System.out
				.println(result.getAuthorizedAccounts().get(0).getAccountId());
	}

	@Test
	public void testRetrieveActivityDetail_defect10394() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-7MWSY7F");
		contract.setServiceRequestId("1-16629075501");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		Activity activity = result.getActivity();

		System.out.println(activity == null);

		Asset asset = activity.getServiceRequest().getAsset();

		List<Part> parts = asset.getPartList();

		System.out.println(parts.size());

		// System.out.println("Machine type model " +
		// asset.getMachineTypeModel());
		// System.out.println(asset.getPartList() == null ? "Part list is null"
		// : ("Part list size: " + asset.getPartList().size()));
		// System.out.println("Image URL: " + asset.getProductImageURL());
	}

	@Test
	public void testRetrieveActivityDetail_defect10395() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-7MWSY7F");
		contract.setServiceRequestId("1-16629075501");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		Activity activity = result.getActivity();

		ServiceRequest sr = activity.getServiceRequest();

		// System.out.println("Customer account address " +
		// sr.getAsset().getCustomerAccount() == null);
		System.out.println("Secondary contact first name: "
				+ sr.getSecondaryContact().getFirstName());
		System.out.println("Secondary contact last name: "
				+ sr.getSecondaryContact().getLastName());
		System.out.println("Secondary contact work phone: "
				+ sr.getSecondaryContact().getWorkPhone());
		System.out.println("Secondary contact alternate phone: "
				+ sr.getSecondaryContact().getAlternatePhone());
		System.out.println("Secondary contact email: "
				+ sr.getSecondaryContact().getEmailAddress());

		System.out.println("--------Primary------");
		System.out.println("Primary contact first name: "
				+ sr.getPrimaryContact().getFirstName());
		System.out.println("Primary contact last name: "
				+ sr.getPrimaryContact().getLastName());
		System.out.println("Primary contact work phone: "
				+ sr.getPrimaryContact().getWorkPhone());
		System.out.println("Primary contact alternate phone: "
				+ sr.getPrimaryContact().getAlternatePhone());
		System.out.println("Primary contact email: "
				+ sr.getPrimaryContact().getEmailAddress());
	}

	@Test
	public void testRetrieveActivityDetail_defect10391() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-7RE4O7A");
		contract.setServiceRequestId("1-16876968781");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		ServiceRequest sr = result.getActivity().getServiceRequest();

		System.out.println("Project phase: " + sr.getProjectPhase());
	}

	@Test
	public void testRetrieveActivityList_defect10118ActivityList()
			throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Legal");
		contract.setMdmId("23259");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("All");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-687WSZZ");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("12/04/2013 13:00:00"));
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("11/03/2013 13:00:00"));
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveActivityList(contract);

		List<Activity> activities = result.getActivityList();

		for (Activity activity : activities) {
			ServiceRequest serviceRequest = activity.getServiceRequest();
			Asset asset = serviceRequest.getAsset();

			System.out.println("Customer reporting name: "
					+ asset.getCustomerReportingName());
			System.out.println("Product line: " + asset.getProductLine());
			System.out.println("Product TLI: " + asset.getProductTLI());
		}
	}

	@Test
	public void testRetrieveClaimDetail_Defect10118ClaimDetail()
			throws Exception {
		ClaimDetailContract contract = new ClaimDetailContract();
		contract.setActivityId("1-77I2Y8R");
		contract.setServiceRequestId("1-77I2Y8F");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());

		ClaimDetailResult result = service.retrieveClaimDetail(contract);

		Activity activity = result.getActivity();
		ServiceRequest serviceRequest = activity.getServiceRequest();
		Asset asset = serviceRequest.getAsset();

		System.out.println("Product line: " + asset.getProductLine());
		System.out.println("Product TLI: " + asset.getProductTLI());
	}

	@Test
	public void testRetrieveActivityList_defect9335ClaimRequestCheckBox()
			throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("All");
		contract.setMdmLevel("Legal");
		contract.setMdmId("23259");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("Claim Request");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-7E1VKFD");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("12/05/2013 10:00:00"));
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("06/13/2013 09:00:00"));
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveActivityList(contract);

		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();

		System.out.println(activities == null ? "Null" : ("Size: " + activities
				.size()));
		System.out.println("Total count: " + totalCount);

		if (activities != null) {
			for (Activity activity : activities) {
				System.out.println("Service Request Type: "
						+ activity.getServiceRequest().getServiceRequestType());
			}
		}
	}

	@Test
	public void testRetrieveActivityList_defect9335ServiceRequestCheckBox()
			throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("All");
		contract.setMdmLevel("Legal");
		contract.setMdmId("23259");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("Service Request");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-7E1VKFD");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("12/05/2013 10:00:00"));
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("06/13/2013 09:00:00"));
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveActivityList(contract);

		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();

		System.out.println(activities == null ? "Null" : ("Size: " + activities
				.size()));
		System.out.println("Total count: " + totalCount);

		if (activities != null) {
			for (Activity activity : activities) {
				System.out.println("Service Request Type: "
						+ activity.getServiceRequest().getServiceRequestType());
			}
		}
	}

	@Test
	public void testRetrieveActivityDetail_defect10371() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		// contract.setActivityId("1-77MQTK7");
		// contract.setServiceRequestId("1-77MHYQ6");
		contract.setActivityId("1-767JKDT");
		contract.setServiceRequestId("1-75SJG55");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		Activity activity = result.getActivity();

		ServiceRequest sr = activity.getServiceRequest();

		System.out.println("--------Primary------");
		System.out.println("Primary contact first name: "
				+ sr.getPrimaryContact().getFirstName());
		System.out.println("Primary contact last name: "
				+ sr.getPrimaryContact().getLastName());
		System.out.println("Primary contact work phone: "
				+ sr.getPrimaryContact().getWorkPhone());
		System.out.println("Primary contact alternate phone: "
				+ sr.getPrimaryContact().getAlternatePhone());
		System.out.println("Primary contact email: "
				+ sr.getPrimaryContact().getEmailAddress());

		System.out.println();

		System.out.println("--------Secondary------");
		System.out.println("Secondary contact first name: "
				+ sr.getSecondaryContact().getFirstName());
		System.out.println("Secondary contact last name: "
				+ sr.getSecondaryContact().getLastName());
		System.out.println("Secondary contact work phone: "
				+ sr.getSecondaryContact().getWorkPhone());
		System.out.println("Secondary contact alternate phone: "
				+ sr.getSecondaryContact().getAlternatePhone());
		System.out.println("Secondary contact email: "
				+ sr.getSecondaryContact().getEmailAddress());

	}

	@Test
	public void testRetrieveActivityList_defect10027() throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Legal");
		contract.setMdmId("23259");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("All");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-689Y9XR");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.claimStatusDate",
				LangUtil.convertStringToGMTDate("12/03/2013 18:30:00"));
		filterCriteria.put("Activity.activitySubStatus", "Claim Accepted");
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveActivityList(contract);

		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();

		// System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + totalCount);
		System.out.println(result.isCountFlag());

		// for (Activity activity : activities) {
		// System.out.println(activity.getServiceRequest().getServiceRequestNumber());
		// }

	}

	@Test
	public void testRetrieveActivityList_Check() throws Exception {
		ActivityListContract listContract = new ActivityListContract();
		listContract.setSessionHandle(crmSessionHandle);
		listContract.setMdmId("23259");
		listContract.setMdmLevel("Legal");
		listContract.setStatus("Open");
		listContract.setCountFlag(true);
		listContract.setEmployeeId("1-7E1VKFD");
		listContract.setQueryType("All");
		listContract.setEmployeeFlag(false);
		listContract.setQueryType("All");
		listContract.setServiceRequestType("All");
		listContract.setIncrement(40);
		listContract.setStartRecordNumber(0);
		listContract.setNewQueryIndicator(true);
		listContract.setChangeManagementFlag(false);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		listContract.setSortCriteria(sortCriteria);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.claimStatusDate",
				LangUtil.convertStringToGMTDate("12/03/2013 05:00:00"));
		filterCriteria.put("Activity.activitySubStatus", "Claim Accepted");
		listContract.setFilterCriteria(filterCriteria);
		ActivityListResult r = service.retrieveActivityList(listContract);
		MiscTest.print(r.getActivityList());
	}

	@Test
	public void testAcceptRejectRequest_defect10383() throws Exception {
		RequestAcceptContract contract = new RequestAcceptContract();
		contract.setStatus("Accepted");
		contract.setActivityId("1-7F2SXPC");
		contract.setRequestNumber("1-16148668927");

		RequestAcceptResult result = service.acceptRejectRequest(contract);

		Boolean operationResult = result.getResult();

		System.out.println(operationResult);
	}

	@Test
	public void testAcceptRejectRequest_defect10470() throws Exception {
		RequestAcceptContract contract = new RequestAcceptContract();
		contract.setStatus("Accepted");
		contract.setActivityId("1-7F2SXPC");
		contract.setRequestNumber("1-16148668927");

		RequestAcceptResult result = service.acceptRejectRequest(contract);

		Boolean operationResult = result.getResult();

		System.out.println(operationResult);
	}

	@Test
	public void testRetrieveActivityDetail_defect10118() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-77NPJZS");
		contract.setServiceRequestId("1-77ISSA4");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		System.out.println("Customer reporting name: "
				+ result.getActivity().getServiceRequest().getAsset()
						.getCustomerReportingName());

	}

	@Test
	public void testRetrieveActivityDetail_defect10118_2() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-77BQJ5P");
		contract.setServiceRequestId("1-77BLJ6D");
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		Activity activity = result.getActivity();
		ServiceRequest serviceRequest = activity.getServiceRequest();
		Asset asset = serviceRequest.getAsset();

		System.out.println("Customer reporting name: "
				+ asset.getCustomerReportingName());

	}

	@Test
	public void testRetrieveActivityList_defect10027_2() throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Global");
		contract.setMdmId("118552798");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("All");
		contract.setCountFlag(true);
		// contract.setCountFlag(false);
		contract.setEmployeeId("1-7G9V69N");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.claimStatusDate",
				LangUtil.convertStringToGMTDate("11/09/2013 05:00:00"));
		filterCriteria.put("Activity.activitySubStatus", "Claim Accepted");
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveActivityList(contract);

		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();

		// System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + totalCount);
		System.out.println(result.isCountFlag());

		// for (Activity activity : activities) {
		// System.out.println(activity.getServiceRequest().getServiceRequestNumber());
		// }

	}

	@Test
	public void testRetrieveServiceActivityHistoryList_defect10773()
			throws Exception {
		ServiceActivityHistoryListContract contract = new ServiceActivityHistoryListContract();
		contract.setAssetId("1-7BCT-15184");
		contract.setServiceRequestId("1-7GBCHJ9");

		ServiceActivityHistoryListResult result = service
				.retrieveServiceActivityHistoryList(contract);

		System.out.println(result.getActivityList().size());
		System.out.println(result.getTotalcount());

	}

	@Test
	public void testAcceptRejectRequest_defect10891() throws Exception {
		RequestAcceptContract contract = new RequestAcceptContract();
		contract.setStatus("Rejected");
		contract.setActivityId("1-7F2SXPC");
		contract.setRequestNumber("1-16148668927");

		RequestAcceptResult result = service.acceptRejectRequest(contract);

		Boolean operationResult = result.getResult();

		System.out.println(operationResult);
	}

	@Test
	public void testRetrieveClaimDetail_Defect10118() throws Exception {
		ClaimDetailContract contract = new ClaimDetailContract();
		contract.setActivityId("1-7GLWP0W");
		contract.setServiceRequestId("1-7GLWP0F");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());

		ClaimDetailResult result = service.retrieveClaimDetail(contract);

		Activity activity = result.getActivity();
		ServiceRequest serviceRequest = activity.getServiceRequest();
		Asset asset = serviceRequest.getAsset();

		System.out.println("Customer reporting name: "
				+ asset.getCustomerReportingName());
	}

	@Test
	public void testRetrievePartnerAddressList_defect9334() throws Exception {
		PartnerAddressListContract contract = new PartnerAddressListContract();
		contract.setAccountID("1-JL69JH");
		contract.setContactId("1-7G9V69N");
		contract.setFavoriteFlag(false);

		PartnerAddressListResult result = service
				.retrievePartnerAddressList(contract);

		System.out.println(result.getAddressList().size());
	}

	@Test
	public void testAcceptRejectRequest_defect10383_NotAccepted()
			throws Exception {
		RequestAcceptContract contract = new RequestAcceptContract();
		contract.setStatus("Not Accepted");
		contract.setActivityId("1-7H0WIRK");
		contract.setRequestNumber("1-16266143038");

		RequestAcceptResult result = service.acceptRejectRequest(contract);

		Boolean operationResult = result.getResult();

		System.out.println(operationResult);
	}

	@Test
	public void testRetrieveActivityDetail_test() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-7506SXX");
		contract.setServiceRequestId("1-7506SX7");
		contract.setDebriefFlag(true);
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		System.out.println(result.getActivity().getServiceRequest() == null);
	}

	@Test
	public void testRetrieveActivityDetail_defect10963() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-7F2L0BF");
		contract.setServiceRequestId("1-16148651890");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		Asset asset = result.getActivity().getServiceRequest().getAsset();

		System.out.println("Ip address: " + asset.getIpAddress());
		System.out.println("Ip V6: " + asset.getIpV6());
		System.out.println("Gateway: " + asset.getGateway());
		System.out.println("Subnet mask: " + asset.getSubnet());

	}

	@Test
	public void testRetrieveActivityList_defect10088() throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Legal");
		contract.setMdmId("30145");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("Fleet Management");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-73O5NHR");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(true);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("01/06/2014 13:00:00"));
		filterCriteria.put("Activity.activitySubStatus", "Accepted");
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("12/06/2013 13:00:00"));
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.serviceRequest.asset.productLine",
				"DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveActivityList(contract);

		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();

		System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + totalCount);

		for (Activity activity : activities) {
			System.out.println(activity.getActivitySubStatus());
		}

	}

	@Test
	public void testRetrieveActivityList_defect10088_2() throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Legal");
		contract.setMdmId("30145");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("Fleet Management");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-73O5NHR");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(true);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(-1);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("01/06/2014 13:00:00"));
		// filterCriteria.put("Activity.serviceRequest.asset.activitynumber",
		// "1-7F2L0BF");
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("12/06/2013 13:00:00"));
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.serviceRequest.asset.serialNumber",
				"ASCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveActivityList(contract);

		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();

		System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + totalCount);

		for (Activity activity : activities) {
			// System.out.println(activity.getServiceRequest().getAsset().getSerialNumber());
			// System.out.println(activity.getServiceRequest().getAsset().getModelNumber());
			// System.out.println(activity.getServiceRequest().getAsset().getProductLine());
			System.out.println(activity.getServiceRequest().getAsset()
					.getProductTLI());
			// System.out.println(activity.getActivityStatus());
			// System.out.println("--------------");

			// System.out.println("Activity NO: " + activity.getActivityId());
			// System.out.println("PostCode: " +
			// (activity.getServiceRequest().getServiceAddress() == null ?
			// "null" :
			// activity.getServiceRequest().getServiceAddress().getPostalCode()));
			// System.out.println("Country: " +
			// (activity.getServiceRequest().getServiceAddress() == null ?
			// "null" :
			// activity.getServiceRequest().getServiceAddress().getCountry()));
			// System.out.println("--------------");
		}

	}

	@Test
	public void testRetrieveActivityList_defect10088_3() throws Exception {

		long t = System.currentTimeMillis();

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Legal");
		contract.setMdmId("30145");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("Fleet Management");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-73O5NHR");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(true);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(-1);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("01/09/2014 18:30:00"));
		filterCriteria.put("Activity.serviceRequest.asset.productModelNumber",
				"4");
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("12/09/2013 18:30:00"));
		// filterCriteria.put("Activity.serviceRequest.serviceRequestNumber",
		// "1-17072566145");
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.serviceRequest.serviceRequestNumber",
				"DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveActivityList(contract);

		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();

		System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + totalCount);

		Set<String> set = new HashSet<String>();

		// for (Activity activity : activities) {
		// // System.out.println("Parent: " +
		// activity.getServiceRequest().getAsset().getModelNumber());
		// if(!activity.getServiceRequest().getAsset().getPartList().isEmpty()
		// &&
		// activity.getServiceRequest().getAsset().getPartList().get(0).getModel()!=
		// null
		// &&
		// activity.getServiceRequest().getAsset().getPartList().get(0).getModel()
		// != "") {
		// set.add(activity.getServiceRequest().getAsset().getPartList().get(0).getModel());
		// // System.out.println("Child: " +
		// activity.getServiceRequest().getAsset().getPartList().get(0).getModel());
		// // System.out.println("--------------------");
		// }
		// }

		for (Activity activity : activities) {
			if (activity.getServiceRequest().getAsset() != null
					&& activity.getServiceRequest().getAsset()
							.getProductModelNumber() != null
					&& activity.getServiceRequest().getAsset()
							.getProductModelNumber() != "") {
				set.add(activity.getServiceRequest().getAsset()
						.getProductModelNumber());
				// System.out.println("Child: " +
				// activity.getServiceRequest().getAsset().getPartList().get(0).getModel());
				// System.out.println("--------------------");
			}
		}

		for (String productModel : set) {
			System.out.println(productModel);
		}

		// for (Activity activity : activities) {
		// List<Part> parts =
		// activity.getServiceRequest().getAsset().getPartList();
		// for (Part part : parts) {
		// System.out.println("Model number: " + part.getModel());
		// }
		//
		// System.out.println("------------");
		// }

		// for (Activity activity : activities) {
		// if(activity.getServiceRequest().getAsset().getModelNumber().contains("11"))
		// System.out.println("Serial number: " +
		// activity.getServiceRequest().getAsset().getModelNumber());
		// }

		// System.out.println("-------------------------");
		//
		// for(int i = 0; i<10; i++) {
		// System.out.println("Service request: " +
		// activities.get(i).getServiceRequest().getServiceRequestNumber());
		// System.out.println("Request type: " +
		// activities.get(i).getServiceRequest().getServiceRequestType());
		// System.out.println("Request type: " +
		// activities.get(i).getServiceRequest().getAsset().getPartList().get(0).getModel());
		// System.out.println("------------------------");
		// }

		// System.out.println("Exac time: " + (System.currentTimeMillis() - t) /
		// 1000.0);

		// for (Activity activity : activities) {
		//
		// Asset asset = activity.getServiceRequest().getAsset();
		//
		// List<Part> parts = asset.getPartList();
		//
		// for (Part part : parts) {
		// if(part.getModel().contains("11")) {
		// System.out.println("Model number: " + part.getModel());
		// }
		// }
		//
		// }

	}

	@Test
	public void testRetrievePartnerAddressList_Multithreading()
			throws Exception {

		long t = System.currentTimeMillis();

		// PartnerAddressListContract contract = new
		// PartnerAddressListContract();
		// contract.setAccountID("1-AJAO8Z");
		// contract.setContactId("1-7G9V69N");
		// contract.setFavoriteFlag(false);
		// contract.setIncrement(40);
		// contract.setStartRecordNumber(0);
		// contract.setNewQueryIndicator(true);
		// contract.setSessionHandle(crmSessionHandle);
		//
		// PartnerAddressListResult result =
		// service.retrievePartnerAddressList(contract);
		//
		// List<GenericAddress> addresses = result.getAddressList();
		// int totalCount = result.getTotalCount();
		//
		// System.out.println("Size: " + addresses.size());
		// System.out.println("Total count: " + totalCount);

		PartnerAddressListContract contract = new PartnerAddressListContract();
		contract.setAccountID("1-JL69JH");
		contract.setContactId("1-BGJ7-279");
		contract.setFavoriteFlag(false);
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

		PartnerAddressListResult result = service
				.retrievePartnerAddressList(contract);

		List<GenericAddress> addresses = result.getAddressList();
		int totalCount = result.getTotalCount();

		System.out.println("Size: " + addresses.size());
		System.out.println("Total count: " + totalCount);

		System.out.println("Exec time: " + (System.currentTimeMillis() - t)
				/ 1000.0);
	}

	@Test
	public void testRetrieveActivityDetail_defect11156() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-7WOM5LW");
		contract.setServiceRequestId("1-17213682419");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		System.out.println(result.getActivity().getServiceRequest() == null);
	}

	@Test
	public void testRetrieveActivityList_defect9335CustomerAccount()
			throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Global");
		contract.setMdmId("118552798");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("All");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-7G9V69N");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("01/04/2014 05:00:00"));
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("07/04/2013 05:00:00"));
		filterCriteria.put("Activity.customerAccount.accountName", "test");
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveActivityList(contract);

		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();

		System.out.println(activities == null ? "Null"
				: ("List size: " + activities.size()));
		System.out.println("Total count: " + totalCount);

		for (Activity activity : activities) {
			if (activity.getCustomerAccount().getAccountName().contains("test")) {
				System.out.println("Customer account name: "
						+ activity.getCustomerAccount().getAccountName());
			}
		}

		System.out.println("End");

	}

	@Test
	public void testRetrieveActivityList_defect9335MyRequests()
			throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Legal");
		contract.setMdmId("23259");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("All");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-687WSZZ");
		contract.setQueryType("My");
		contract.setChangeManagementFlag(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("01/17/2014 10:00:00"));
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("12/17/2013 10:00:00"));
		// filterCriteria.put("Activity.serviceRequest.serviceRequestNumber",
		// "1-17244953331");
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveActivityList(contract);

		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();

		System.out.println(activities == null ? "Null"
				: ("List size: " + activities.size()));
		System.out.println("Total count: " + totalCount);

		ServiceRequest sr = activities.get(0).getServiceRequest();

		System.out.println(sr.getServiceRequestNumber());

		System.out.println("End");

	}

	@Test
	public void testRetrievePartnerAddressList_defect11181() throws Exception {
		PartnerAddressListContract contract = new PartnerAddressListContract();
		contract.setAccountID("1-F683TZ");
		contract.setContactId("1-7PHPP0R");
		contract.setFavoriteFlag(false);
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("storeFrontName", "DESCENDING");
		contract.setSortCriteria(sortCriteria);

		PartnerAddressListResult result = service
				.retrievePartnerAddressList(contract);

		System.out.println(result.getAddressList().size());
		System.out.println(result.getTotalCount());
	}

	@Test
	public void testRetrieveActivityList_defect11198() throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("All");
		contract.setMdmLevel("Legal");
		contract.setMdmId("23259");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("Service Request");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-7PHPP0R");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("01/20/2014 10:00:00"));
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("11/01/2013 09:00:00"));
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveActivityList(contract);

		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();

		System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + totalCount);

	}

	@Test
	public void testRetrieveActivityList_defect9804() throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Global");
		contract.setMdmId("118552798");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("Service Request");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-7G9V69N");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("01/22/2014 10:00:00"));
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("08/08/2013 09:00:00"));
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveActivityList(contract);

		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();

		System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + totalCount);

	}

	@Test
	public void testOfflineMode() throws Exception {

		AmindPartnerHardwareInstallDebriefOfflineModeService offlineModeSerivice = new AmindPartnerHardwareInstallDebriefOfflineModeService();

		OfflineModeAttachmentContract contract = new OfflineModeAttachmentContract();
		contract.setActivityId("1-7UBQGRT");

		offlineModeSerivice.generateInstallationDoc(contract);

		System.out.println("End");
	}

	@Test
	public void testRetrieveClaimDetail_Defect11315() throws Exception {
		ClaimDetailContract contract = new ClaimDetailContract();
		contract.setActivityId("1-7Y6HO1Z");
		contract.setServiceRequestId("1-7Y6HO1H");
		contract.setDebriefFlag(true);
		contract.setServicesUser(new ServicesUser());

		ClaimDetailResult result = service.retrieveClaimDetail(contract);

		Activity activity = result.getActivity();
		Debrief debrief = activity.getDebrief();

		System.out.println("Date Service Requested: "
				+ debrief.getServiceRequestedDate());
		System.out.println("Service Provider Reference Number: "
				+ activity.getServiceProviderReferenceNumber());
	}

	@Test
	public void testRetrieveClaimDetail_Defect11315_2() throws Exception {
		ClaimDetailContract contract = new ClaimDetailContract();
		contract.setActivityId("1-7767P6Y");
		contract.setServiceRequestId("1-7767P6D");
		contract.setDebriefFlag(true);
		contract.setServicesUser(new ServicesUser());

		ClaimDetailResult result = service.retrieveClaimDetail(contract);

		Activity activity = result.getActivity();
		Debrief debrief = activity.getDebrief();

		TimeZone.setDefault(TimeZone.getTimeZone("EST"));

		System.out.println("Date Service Requested: "
				+ debrief.getServiceRequestedDate());
		// System.out.println("Service Provider Reference Number: " +
		// activity.getServiceProviderReferenceNumber());
	}

	@Test
	public void testRetrieveActivityList_defect11120() throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("All");
		contract.setMdmLevel("Legal");
		contract.setMdmId("30145");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("Fleet Management");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-73O5NHR");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(true);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("01/28/2014 10:00:00"));
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("12/28/2013 10:00:00"));
		// filterCriteria.put("Activity.activityType", "HW Install Change");
		filterCriteria.put("Activity.activityType", "HW Install");
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.serviceRequest.serviceRequestNumber",
				"DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveActivityList(contract);

		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();

		System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + totalCount);

		for (Activity activity : activities) {
			System.out.println("Activity Type: " + activity.getActivityType());
		}

	}

	@Test
	public void testRetrieveActivityList_NewIntegrations() throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("All");
		contract.setMdmLevel("Legal");
		contract.setMdmId("30145");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("Fleet Management");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-73O5NHR");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(true);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("01/28/2014 10:00:00"));
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("12/28/2013 10:00:00"));
		// filterCriteria.put("Activity.activityType", "HW Install Change");
		filterCriteria.put("Activity.activityType", "HW Install");
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.serviceRequest.serviceRequestNumber",
				"DESCENDING");
		contract.setSortCriteria(sortCriteria);

		AmindPartnerHardwareInstallDebriefOfflineModeService offlineModeSerice = new AmindPartnerHardwareInstallDebriefOfflineModeService();

		ActivityListResult result = offlineModeSerice
				.retrieveOfflineModeActivitiesList(contract);

		List<Activity> activities = result.getActivityList();

		System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + result.getTotalcount());

		for (Activity activity : activities) {
			System.out.println("Created: "
					+ activity.getOfflineModeAttachment().getCreated());
			System.out.println("Comments: "
					+ activity.getOfflineModeAttachment().getComments());
			System.out.println("----------------");
		}

		// for (Activity activity : activities) {
		// ServiceRequest serviceRequest = activity.getServiceRequest();
		// Asset asset = serviceRequest.getAsset();
		//
		// System.out.println("------------------");
		// System.out
		// .println("Store front name: " + asset.getStoreFrontName());
		// System.out.println("Install date: " + asset.getInstallDate());
		// System.out.println("Service Request End Date: "
		// + serviceRequest.getActualEndDate());
		// System.out.println("Project name: "
		// + serviceRequest.getProjectName());
		// System.out.println("Responce Metric: "
		// + activity.getResponseMetric());
		// System.out.println("Status detail: "
		// + serviceRequest.getStatusDetail());
		// System.out.println("Product line: " + asset.getProductLine());
		// System.out.println("Product model: " + asset.getModelNumber());
		// System.out.println("Product Tli: " + asset.getProductTLI());
		// System.out.println("Serial number: " + asset.getSerialNumber());
		// }
	}

	@Test
	public void testRetrieveActivityDetail_defect9118_4() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-7QBMOEJ");
		contract.setServiceRequestId("1-7QBEA7F");
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		System.out.println(result.getActivity() == null);

		Activity activity = result.getActivity();

		List<PartLineItem> orderPartList = activity.getOrderPartList();

		for (PartLineItem part : orderPartList) {
			System.out.println("Carrier: " + part.getShipmentCarrier());
			System.out.println("Tracking #: "
					+ part.getShipmentTrackingNumber());
			System.out.println("Status: " + part.getShipmentTrackingStatus());
			System.out.println("Date: " + part.getShipmentDate());
		}
	}

	@Test
	public void testRetrieveActivityList_11393() throws Exception {
		ActivityListContract listContract = new ActivityListContract();
		listContract.setSessionHandle(crmSessionHandle);
		listContract.setMdmId("118552798");
		listContract.setMdmLevel("Global");
		listContract.setStatus("Open");
		listContract.setCountFlag(true);
		listContract.setEmployeeId("1-7G9V69N");
		listContract.setQueryType("All");
		listContract.setEmployeeFlag(false);
		listContract.setQueryType("All");
		listContract.setServiceRequestType("All");
		listContract.setIncrement(40);
		listContract.setStartRecordNumber(0);
		listContract.setNewQueryIndicator(true);
		listContract.setChangeManagementFlag(false);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		listContract.setSortCriteria(sortCriteria);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.claimStatusDate",
				LangUtil.convertStringToGMTDate("01/19/2014 10:00:00"));
		filterCriteria.put("Activity.activitySubStatus", "Claim Accepted");
		listContract.setFilterCriteria(filterCriteria);
		ActivityListResult r = service.retrieveActivityList(listContract);
		System.out.println(r.getTotalcount());
		System.out.println(r.getActivityList().size());
	}

	@Test
	public void testRetrieveActivityDetail_newIntegraions() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-77BQJ5P");

		AmindPartnerHardwareInstallDebriefOfflineModeService offlineModeSerice = new AmindPartnerHardwareInstallDebriefOfflineModeService();

		ActivityDetailResult result = offlineModeSerice
				.retrieveOfflineModeActivityDetails(contract);

		Activity activity = result.getActivity();
		ServiceRequest serviceRequest = activity.getServiceRequest();
		Asset asset = serviceRequest.getAsset();

		System.out.println("Customer reporting name: "
				+ asset.getCustomerReportingName());

	}

	@Test
	public void testRetrieveActivityList_11393_2() throws Exception {
		ActivityListContract listContract = new ActivityListContract();
		listContract.setSessionHandle(crmSessionHandle);
		listContract.setMdmId("118552798");
		listContract.setMdmLevel("Global");
		listContract.setStatus("Open");
		listContract.setCountFlag(false);
		listContract.setEmployeeId("1-7G9V69N");
		listContract.setEmployeeFlag(false);
		listContract.setQueryType("All");
		listContract.setServiceRequestType("All");
		listContract.setIncrement(40);
		listContract.setStartRecordNumber(0);
		listContract.setNewQueryIndicator(true);
		listContract.setChangeManagementFlag(false);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		listContract.setSortCriteria(sortCriteria);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("02/01/2014 05:00:00"));
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("01/01/2014 05:00:00"));
		listContract.setFilterCriteria(filterCriteria);
		ActivityListResult r = service.retrieveActivityList(listContract);
		// System.out.println(">>FLAG FOR POP UP >>"+r.isCountFlag());
		System.out.println(r.getTotalcount());
		System.out.println(r.getActivityList().size());
	}

	@Test
	public void testRetrievePartnerAddressList_defect9334_2() throws Exception {
		PartnerAddressListContract contract = new PartnerAddressListContract();
		contract.setContactId("1-7PHPP0R");
		contract.setAccountID("1-BC68VN");
		contract.setFavoriteFlag(false);
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setNewQueryIndicator(true);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("storeFrontName", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		contract.setSessionHandle(crmSessionHandle);

		PartnerAddressListResult result = service
				.retrievePartnerAddressList(contract);

		List<GenericAddress> addresses = result.getAddressList();

		System.out.println("List size: " + addresses.size());
		System.out.println("Total count: " + result.getTotalCount());

		for (GenericAddress address : addresses) {
			System.out.println("--------------");
			System.out.println("Address line 1: " + address.getAddressLine1());
			System.out.println("Address name: " + address.getAddressName());
			System.out.println("State: " + address.getState());
			System.out.println("City: " + address.getState());
		}
	}

	@Test
	public void testRetrieveActivityList_defect11499() throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("All");
		contract.setMdmLevel("Global");
		contract.setMdmId("118552798");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("Service Request");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-7G9V69N");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(false);
		contract.setOfflineInstallDebrief(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("02/04/2014 10:00:00"));
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("09/10/2013 09:00:00"));
//		filterCriteria.put("Activity.activityType",
//				"Consumable SVC Parts & Install");
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveActivityList(contract);

		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();

		System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + totalCount);

	}

	@Test
	public void testRetrieveActivityDetail_AttachmentSource() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-819CI00");
		contract.setServiceRequestId("1-17488959870");
		contract.setDebriefFlag(true);
		contract.setServicesUser(new ServicesUser());

		AmindPartnerHardwareInstallDebriefOfflineModeService offlineModeSerice = new AmindPartnerHardwareInstallDebriefOfflineModeService();

		ActivityDetailResult result = offlineModeSerice
				.retrieveOfflineModeActivityDetails(contract);

		Activity activity = result.getActivity();

		List<Attachment> attachments = activity.getAttachmentList();

		System.out.println("Attachments size: " + attachments.size());

		System.out.println("--------------------------");

		for (Attachment attachment : attachments) {
			// System.out.println("File source path: " +
			// attachment.getFileSourcePath());
			System.out.println("Size: " + attachment.getSize());
			System.out.println("Attachment name: "
					+ attachment.getAttachmentName());
		}

		System.out.println("--------------------------");
	}

	@Test
	public void testRetrieveActivityDetail_DebriefMappingIssue()
			throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-84EXPFF");
		contract.setServiceRequestId("1-17650643035");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		Activity activity = result.getActivity();

		ServiceRequest serviceRequest = activity.getServiceRequest();

		Asset asset = serviceRequest.getAsset();

		Debrief debrief = activity.getDebrief();

		GenericAddress installAddress = asset.getInstallAddress();

		System.out.println("Project name: " + serviceRequest.getProjectName());
		System.out.println("Serial number: " + asset.getSerialNumber());
		System.out.println("Printer working condition: "
				+ debrief.getDeviceCondition());
		System.out.println("Customer device tag: " + asset.getDeviceTag());
		System.out
				.println("Building: " + installAddress.getPhysicalLocation1());
		System.out.println("Floor: " + installAddress.getPhysicalLocation2());
		System.out.println("Office: " + installAddress.getPhysicalLocation3());

	}

	@Test
	public void testRetrieveActivityList_MultiThreadsTest() throws Exception {

		ExecutorService executor = Executors.newFixedThreadPool(2);

		List<Future<?>> futures = new ArrayList<Future<?>>();

		futures.add(executor.submit(new Runnable() {

			@Override
			public void run() {
				ActivityListContract contract = new ActivityListContract();
				contract.setStatus("All");
				contract.setMdmLevel("Global");
				contract.setMdmId("118552798");
				contract.setEmployeeFlag(false);
				contract.setServiceRequestType("Service Request");
				contract.setCountFlag(false);
				contract.setEmployeeId("1-7G9V69N");
				contract.setQueryType("All");
				contract.setChangeManagementFlag(false);
				contract.setOfflineInstallDebrief(false);
				contract.setNewQueryIndicator(true);
				contract.setStartRecordNumber(0);
				contract.setIncrement(40);
				contract.setSessionHandle(crmSessionHandle);

				Map<String, Object> filterCriteria = new HashMap<String, Object>();
				filterCriteria.put("Activity.activityDate.endDate",
						LangUtil.convertStringToGMTDate("02/04/2014 10:00:00"));
				filterCriteria.put("Activity.activityDate.startDate",
						LangUtil.convertStringToGMTDate("09/10/2013 09:00:00"));
				filterCriteria.put("Activity.activityType",
						Arrays.asList("Consumable SVC Parts & Install"));
				contract.setFilterCriteria(filterCriteria);

				Map<String, Object> sortCriteria = new HashMap<String, Object>();
				sortCriteria.put("Activity.activityDate", "DESCENDING");
				contract.setSortCriteria(sortCriteria);

				ActivityListResult result = null;
				try {
					result = service.retrieveActivityList(contract);
				} catch (Exception e) {
					e.printStackTrace();
				}

				List<Activity> activities = result.getActivityList();
				int totalCount = result.getTotalcount();

				System.out.println("Global Size: " + activities.size());
				System.out.println("Global Total count: " + totalCount);
			}
		}));

		futures.add(executor.submit(new Runnable() {

			@Override
			public void run() {

				try {
					Thread.sleep(10000);
				} catch (Exception ex) {
					System.out.println(ex);
				}

				ActivityListContract contract = new ActivityListContract();
				contract.setStatus("All");
				contract.setMdmLevel("Legal");
				contract.setMdmId("23259");
				contract.setEmployeeFlag(false);
				contract.setServiceRequestType("Service Request");
				contract.setCountFlag(false);
				contract.setEmployeeId("1-7PHPP0R");
				contract.setQueryType("All");
				contract.setChangeManagementFlag(false);
				contract.setNewQueryIndicator(true);
				contract.setStartRecordNumber(0);
				contract.setIncrement(40);
				contract.setSessionHandle(crmSessionHandle);

				Map<String, Object> filterCriteria = new HashMap<String, Object>();
				filterCriteria.put("Activity.activityDate.endDate",
						LangUtil.convertStringToGMTDate("01/20/2014 10:00:00"));
				filterCriteria.put("Activity.activityDate.startDate",
						LangUtil.convertStringToGMTDate("11/01/2013 09:00:00"));
				contract.setFilterCriteria(filterCriteria);

				Map<String, Object> sortCriteria = new HashMap<String, Object>();
				sortCriteria.put("Activity.activityDate", "DESCENDING");
				contract.setSortCriteria(sortCriteria);

				ActivityListResult result = null;
				try {
					result = service.retrieveActivityList(contract);
				} catch (Exception e) {
					e.printStackTrace();
				}

				List<Activity> activities = result.getActivityList();
				int totalCount = result.getTotalcount();

				System.out.println("Legal Size: " + activities.size());
				System.out.println("Legal Total count: " + totalCount);
			}
		}));

		for (Future<?> future : futures) {
			future.get();
		}

		executor.shutdown();

		System.out.println("END");

	}

	@Test
	public void testRetrieveActivityList_NewIntegrations2() throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Legal");
		contract.setMdmId("30145");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("Fleet Management");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-73O5NHR");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(true);
		contract.setOfflineInstallDebrief(true);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("02/10/2014 18:30:00"));
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("01/10/2013 18:30:00"));
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.serviceRequest.serviceRequestNumber",
				"DESCENDING");
		contract.setSortCriteria(sortCriteria);

		AmindPartnerHardwareInstallDebriefOfflineModeService offlineModeSerice = new AmindPartnerHardwareInstallDebriefOfflineModeService();

		ActivityListResult result = offlineModeSerice
				.retrieveOfflineModeActivitiesList(contract);

		List<Activity> activities = result.getActivityList();

		System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + result.getTotalcount());

		for (Activity activity : activities) {
			System.out.println("Service request number: "
					+ activity.getServiceRequest().getServiceRequestNumber());
			System.out.println("Comments "
					+ activity.getOfflineModeAttachment().getComments());
			System.out.println("File name: "
					+ activity.getOfflineModeAttachment().getFileName());
			System.out.println("----------------");
		}

	}

	@Test
	public void testRetrieveActivityDetail_ActivityDetailsContract()
			throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-7WIZWOY");
		contract.setServiceRequestId("1-17198243794");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		Activity activity = result.getActivity();

		ServiceRequest serviceRequest = activity.getServiceRequest();

		Asset asset = serviceRequest.getAsset();

		List<PageCounts> pageCounts = asset.getPageCounts();
		String moveToAddress = asset.getMoveToAddressGrouped();
		List<Part> additionalParts = asset.getLastOrderPartList();
		List<Part> reccomendedParts = asset.getPartList();

		System.out.println("Page counts size: " + pageCounts.size());
		System.out.println("Move to address: " + moveToAddress);
		System.out.println(additionalParts == null);
		// System.out.println("Additional parts size: " +
		// additionalParts.size());
		System.out
				.println("Recommended parts size: " + reccomendedParts.size());
	}

	@Test
	public void testRetrieveActivityList_defect10088_3_OfflineModeTest()
			throws Exception {

		long t = System.currentTimeMillis();

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Legal");
		contract.setMdmId("30145");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("Fleet Management");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-73O5NHR");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(true);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(-1);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("01/09/2014 18:30:00"));
		filterCriteria.put("Activity.serviceRequest.asset.productModelNumber",
				"4");
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("12/09/2013 18:30:00"));
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.serviceRequest.serviceRequestNumber",
				"DESCENDING");
		contract.setSortCriteria(sortCriteria);

		AmindPartnerHardwareInstallDebriefOfflineModeService offlineService = new AmindPartnerHardwareInstallDebriefOfflineModeService();
		ActivityListResult result = offlineService
				.retrieveOfflineModeActivitiesList(contract);

		// ActivityListResult result = service.retrieveActivityList(contract);

		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();

		System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + totalCount);

		Set<String> set = new HashSet<String>();

		for (Activity activity : activities) {
			if (activity.getServiceRequest().getAsset() != null
					&& activity.getServiceRequest().getAsset()
							.getProductModelNumber() != null
					&& activity.getServiceRequest().getAsset()
							.getProductModelNumber() != "") {
				set.add(activity.getServiceRequest().getAsset()
						.getProductModelNumber());
			}
		}

		for (String productModel : set) {
			System.out.println(productModel);
		}

	}

	@Test
	public void testRetrieveActivityList_defect11671() throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Legal");
		contract.setMdmId("30145");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-73O5NHR");
		contract.setServiceRequestType("Fleet Management");
		contract.setEmployeeFlag(false);
		contract.setQueryType("All");
		contract.setChangeManagementFlag(true);
		contract.setOfflineInstallDebrief(false);
		contract.setNewQueryIndicator(true);
		contract.setIncrement(40);
		contract.setStartRecordNumber(0);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("02/11/2014 13:00:00"));
		// filterCriteria.put("Activity.activitySubStatus", "Accepted");
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("01/11/2014 13:00:00"));
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.serviceRequest.serviceAddress.officeNumber",
				"ASCENDING");
		contract.setSortCriteria(sortCriteria);

		// ActivityListResult result = service.retrieveActivityList(contract);
		AmindPartnerHardwareInstallDebriefOfflineModeService offlineService = new AmindPartnerHardwareInstallDebriefOfflineModeService();
		ActivityListResult result = offlineService
				.retrieveOfflineModeActivitiesList(contract);

		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();

		System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + totalCount);

		// for (Activity activity : activities) {
		// System.out.println(activity.getServiceRequest().getServiceAddress().getOfficeNumber());
		// }

	}

	@Test
	public void testRetrieveActivityOfflineModeDetail_PageCountsNotGettingPopulated()
			throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-81TKWW9");
		contract.setServiceRequestId("1-17523828041");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());

		AmindPartnerHardwareInstallDebriefOfflineModeService offlineModeSerice = new AmindPartnerHardwareInstallDebriefOfflineModeService();

		ActivityDetailResult result = offlineModeSerice
				.retrieveOfflineModeActivityDetails(contract);

		Activity activity = result.getActivity();
		ServiceRequest serviceRequest = activity.getServiceRequest();
		Asset asset = serviceRequest.getAsset();
	}

	@Test
	public void testRetrieveActivityDetail_PageCountsNotGettingPopulated()
			throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-81TKWW9");
		contract.setServiceRequestId("1-17523828041");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		Asset asset = result.getActivity().getServiceRequest().getAsset();

	}

	@Test
	public void testRetrieveActivityDetail_defect11682() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-89N615Z");
		contract.setServiceRequestId("1-89MXSTH");
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		Activity activity = result.getActivity();

		Asset asset = result.getActivity().getServiceRequest().getAsset();

		System.out.println("Notes: " + activity.getActivityNoteList().size());
	}

	@Test
	public void testAcceptRejectRequest_defect11802() throws Exception {
		RequestAcceptContract contract = new RequestAcceptContract();
		contract.setStatus("Accepted");
		contract.setActivityId("1-8EV24BK");
		contract.setRequestNumber("1-18309527083");

		RequestAcceptResult result = service.acceptRejectRequest(contract);

		Boolean operationResult = result.getResult();

		System.out.println(operationResult);
	}

	@Test
	public void testRetrieveActivityList_changesTest1() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Legal");
		contract.setMdmId("30145");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("Fleet Management");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-73O5NHR");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(true);
		contract.setOfflineInstallDebrief(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("02/21/2014 05:00:00"));
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("01/21/2014 05:00:00"));
		// filterCriteria.put("Activity.activityType", "HW Install");
		contract.setFilterCriteria(filterCriteria);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.serviceRequest.serviceRequestNumber",
				"DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveActivityList(contract);

		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();
		System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + totalCount);
	}

	@Test
	public void testOfflineMode_defect12092() throws Exception {

		AmindPartnerHardwareInstallDebriefOfflineModeService offlineModeSerivice = new AmindPartnerHardwareInstallDebriefOfflineModeService();

		OfflineModeAttachmentContract contract = new OfflineModeAttachmentContract();
		contract.setActivityId("1-8O6M9BV");

		offlineModeSerivice.generateInstallationDoc(contract);

		System.out.println("End");
	}
	
	@Test
	public void testOfflineMode_io() throws Exception {
		AmindPartnerHardwareInstallDebriefOfflineModeService offlineModeSerivice = new AmindPartnerHardwareInstallDebriefOfflineModeService();
		OfflineModeAttachmentContract contract = new OfflineModeAttachmentContract();
		contract.setActivityId("1-BJ7Y29B");
		offlineModeSerivice.generateInstallationDoc(contract);
		System.out.println("End");
	}

	@Test
	public void testRetrieveActivityDetail_defect12086() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-8MJYQ1N");
		contract.setServiceRequestId("1-18776539015");
		contract.setPageName("Debrief");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);
		// System.out.println("TLI: " +
		// result.getActivity().getServiceRequest().getAsset().getProductTLI());
		// System.out.println("PickUpAddress: " +
		// result.getActivity().getServiceRequest().getAsset().getPickupAddress());
		// System.out.println("Install Address: " +
		// result.getActivity().getServiceRequest().getAsset().getInstallAddress());

		// GenericAddress address =
		// result.getActivity().getServiceRequest().getAsset().getInstallAddress();
		// System.out.println(address.getAddressLine1());
		// System.out.println(address.getAddressName());
		// System.out.println(address.getCountry());

		System.out.println("Parts list size 2: "
				+ result.getActivity().getRecommendedPartList().size());

		logger.debug("END" + result);
	}

	@Test
	public void testRetrieveActivityDetail_defect11992() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-851QKVK");
		contract.setServiceRequestId("1-17708153849");
		contract.setPageName("Debrief");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());

		Activity activity = service.retrieveActivityDetail(contract)
				.getActivity();

		System.out.println("Parts list size 1: "
				+ activity.getRecommendedPartList().size());
	}

	@Test
	public void testRetrieveActivityDetail_MultiThreadTest() throws Exception {

		ExecutorService executor = Executors.newCachedThreadPool();

		List<Future<?>> futures = new ArrayList<Future<?>>();

		futures.add(executor.submit(new Runnable() {

			@Override
			public void run() {

				ActivityDetailContract contract = new ActivityDetailContract();
				contract.setActivityId("1-851QKVK");
				contract.setServiceRequestId("1-17708153849");
				contract.setPageName("Debrief");
				contract.setDebriefFlag(false);
				contract.setServicesUser(new ServicesUser());

				Activity activity = null;
				try {
					activity = service.retrieveActivityDetail(contract)
							.getActivity();
				} catch (Exception e) {
					e.printStackTrace();
				}

				System.out.println("Parts list size 1: "
						+ activity.getRecommendedPartList().size());

			}
		}));

		futures.add(executor.submit(new Runnable() {

			@Override
			public void run() {

				try {
					Thread.sleep(10000);
				} catch (Exception ex) {
					System.out.println(ex);
				}

				ActivityDetailContract contract = new ActivityDetailContract();
				contract.setActivityId("1-8MJYQ1N");
				contract.setServiceRequestId("1-18776539015");
				contract.setPageName("Debrief");
				contract.setDebriefFlag(false);
				contract.setServicesUser(new ServicesUser());

				ActivityDetailResult result = null;
				try {
					result = service.retrieveActivityDetail(contract);
				} catch (Exception e) {
					e.printStackTrace();
				}

				System.out.println("Parts list size 2: "
						+ result.getActivity().getRecommendedPartList().size());

			}
		}));

		for (Future<?> future : futures) {
			future.get();
		}

		executor.shutdown();

		System.out.println("END");

	}
	
	@Test
	public void testAcceptRejectRequest_defect12157() throws Exception {
		
		long t = System.currentTimeMillis();
		
		RequestAcceptContract contract = new RequestAcceptContract();
		contract.setStatus("Accepted");
		contract.setActivityId("1-8BMJVAO");
		contract.setRequestNumber("1-18095769161");

		RequestAcceptResult result = service.acceptRejectRequest(contract);

		Boolean operationResult = result.getResult();

		System.out.println(operationResult);
		
		System.out.println("Exec time: " + (System.currentTimeMillis() - t)/1000.0);
	}
	
	@Test
	public void testRetrieveActivityDetail_defect12014() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-89XAVOJ");
//		contract.setActivityId("1-89MHXN1");
		contract.setServiceRequestId("1-89XAVO1");
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		String serialNumber = result.getActivity().getServiceRequest().getAsset().getSerialNumber();
		
		AccountContact primaryContact = result.getActivity().getServiceRequest().getPrimaryContact();
		
		AccountContact secondaryContact = result.getActivity().getServiceRequest().getSecondaryContact();
		System.out.println("Serial number: " + serialNumber);
		System.out.println("---------------");
		System.out.println("Primary contact info:");
		System.out.println(primaryContact.getFirstName());
		System.out.println(primaryContact.getLastName());
		System.out.println(primaryContact.getEmailAddress());
		System.out.println(primaryContact.getWorkPhone());
		System.out.println("---------------");
		System.out.println("Secondary contact info:");
		System.out.println(secondaryContact.getFirstName());
		System.out.println(secondaryContact.getLastName());
		System.out.println(secondaryContact.getEmailAddress());
		System.out.println(secondaryContact.getWorkPhone());
		
	}
	
	
	
	@Test
	public void testRetrieveActivityDetail_defect12174() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-8QXM3X8");
		contract.setServiceRequestId("1-19042573379");
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		List<PageCounts> pageCountsList = result.getActivity().getServiceRequest().getAsset().getPageCounts();
		
		System.out.println("Size: " + pageCountsList.size());
		
		for (PageCounts pageCounts : pageCountsList) {
			System.out.println(pageCounts.getType());
		}
		
	}
	
	
	@Test
	public void testRetrieveActivityList_ChangeManagementRequestFilter() throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Global");
		contract.setMdmId("118552798");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("All");
//		contract.setServiceRequestType("Service Request");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-7G9V69N");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(false);
		contract.setOfflineInstallDebrief(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("03/07/2014 05:00:00"));
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("02/04/2014 05:00:00"));
		filterCriteria.put("Activity.serviceRequest.serviceRequestType","Fleet Management");
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveActivityList(contract);

		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();

		System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + totalCount);
		
		int i = 0;
		for (Activity activity : activities) {
			System.out.println(activity.getServiceRequest().getServiceRequestType());
			if(!"Claim Request".equalsIgnoreCase(activity.getServiceRequest().getServiceRequestType().getValue()) 
					&& !"Service Request".equalsIgnoreCase(activity.getServiceRequest().getServiceRequestType().getValue())) {
				i++;
			}
		}
		
		System.out.println("i = " + i);
		
	}
	
	@Test
	public void testRetrieveActivityDetail_defect12316() throws Exception {
		
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-8AGFVK2");
		contract.setServiceRequestId("1-18044553961");
		contract.setPageName("Debrief");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		System.out.println(result.getActivity() == null);

		Activity activity = result.getActivity();
		
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
		System.out.println(TimeZone.getDefault());
		System.out.println(activity.getCustomerRequestedResponseDate());
		System.out.println(activity.getExpectedCompletionDate());
		
		TimeZone.setDefault(TimeZone.getTimeZone("EST"));
		System.out.println(TimeZone.getDefault());
		System.out.println(activity.getCustomerRequestedResponseDate());
		System.out.println(activity.getExpectedCompletionDate());
	}
	
	
	@Test
	public void testRetrieveActivityList_defect12340() throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Global");
		contract.setMdmId("966331451");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("Service Request");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-73O5NHR");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(false);
		contract.setOfflineInstallDebrief(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("03/11/2014 04:00:00"));
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("02/08/2014 04:00:00"));
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveActivityList(contract);

		List<Activity> activities = result.getActivityList();
		
		int totalCount = result.getTotalcount();

		System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + totalCount);
		
		for (Activity activity : activities) {
			System.out.println(activity.getServiceRequest().getServiceRequestType());
		}

	}
	
	
	@Test
	public void testRetrieveActivityList_defect12453() throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Global");
		contract.setMdmId("966331451");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("All");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-73O5NHR");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(false);
		contract.setOfflineInstallDebrief(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(-1);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("03/22/2014 04:00:00"));
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("02/19/2014 04:00:00"));
		filterCriteria.put("Activity.serviceRequest.serviceRequestType", "Fleet Management");
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveActivityList(contract);

		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();

		System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + totalCount);

		for (Activity activity : activities) {
			System.out.println(activity.getServiceRequest().getServiceRequestType());
		}

	}
	
	
	@Test
	public void testRetrieveActivityList_defect12453_2() throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Global");
		contract.setMdmId("422524798");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("Service Request");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-73O5NHR");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(false);
		contract.setOfflineInstallDebrief(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("03/24/2014 18:30:00"));
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("02/21/2014 18:30:00 "));
		filterCriteria.put("Activity.serviceRequest.serviceRequestType", "Fleet Management");
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveActivityList(contract);

		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();

		System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + totalCount);

		for (Activity activity : activities) {
			System.out.println(activity.getServiceRequest().getServiceRequestType());
		}

	}

	@Test
	public void testRetrieveActivityDetail_defect11930() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-8QUMU9F");
		contract.setServiceRequestId("1-19037289459");
		contract.setPageName("Debrief");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());

		Activity activity = service.retrieveActivityDetail(contract).getActivity();

		List<Attachment> attachments = activity.getAttachmentList();
		
		for (Attachment attachment : attachments) {
			System.out.println("Visibility: " + attachment.getVisibility());
		}
	}
	
	
	@Test
	public void testRetrieveActivityDetail_defect12485() throws Exception {
		
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-8MD2CCB");
		contract.setServiceRequestId("1-18764416421");
		contract.setPageName("Debrief");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		System.out.println(result.getActivity() == null);

		Activity activity = result.getActivity();
		
		Debrief debrief = activity.getDebrief();
		
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
		System.out.println("Start date: " + debrief.getServiceStartDate());
		System.out.println("End date: " + debrief.getServiceEndDate());
		
		System.out.println("----------------");
		
		TimeZone.setDefault(TimeZone.getTimeZone("EST"));
		System.out.println("Start date: " + debrief.getServiceStartDate());
		System.out.println("End date: " + debrief.getServiceEndDate());
	}
	
	
	@Test
	public void testRetrieveActivityDetail_ProviderETADate() throws Exception {
		
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-8MD2CLR");
		contract.setServiceRequestId("1-18764416421");
		contract.setPageName("Debrief");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		System.out.println(result.getActivity() == null);

		Activity activity = result.getActivity();
		
		Debrief debrief = activity.getDebrief();
		
		System.out.println();
		
		System.out.println("GET");
		System.out.println("Service Provider ETA: " + activity.getServiceProviderETA());
		System.out.println("Service Provider ETA: " + activity.getEstimatedArrivalTime());
		
		System.out.println();
		
		System.out.println("GMT");
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
		System.out.println("Service Provider ETA: " + activity.getServiceProviderETA());
		System.out.println("Service Provider ETA: " + activity.getEstimatedArrivalTime());
		
		System.out.println("----------------");
		
		System.out.println("EST");
		TimeZone.setDefault(TimeZone.getTimeZone("EST"));
		System.out.println("Service Provider ETA: " + activity.getServiceProviderETA());
		System.out.println("Service Provider ETA: " + activity.getEstimatedArrivalTime());
		
		System.out.println();
	}
	
	
	@Test
	public void testRetrieveActivityDetail_defect12017() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-89XAVOJ");
		contract.setServiceRequestId("1-89XAVO1");
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		Asset asset = result.getActivity().getServiceRequest().getAsset();
		
		System.out.println("Fax connected: " + asset.isFaxConnected());
		
	}

    @Test
    public void testRetrieveActivityList_Defect11039() throws Exception {
    	ActivityListContract listContract = new  ActivityListContract();
    	listContract.setSessionHandle(crmSessionHandle);
    	listContract.setMdmId("30145");
    	listContract.setMdmLevel("Legal");
    	listContract.setQueryType("All");
    	listContract.setEmployeeId("1-73O5NHR");
    	listContract.setChangeManagementFlag(true);
    	listContract.setStatus("Open");
    	listContract.setStatus(ActivityStatus.All.toString());
    	listContract.setServiceRequestType("Fleet Management");
    	listContract.setIncrement(40);
    	listContract.setStartRecordNumber(0);
    	listContract.setNewQueryIndicator(true);
    	
    	Map<String, Object> filterCriteria = new HashMap<String, Object>();
    	filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("12/15/2013 05:00:00"));
    	filterCriteria.put("Activity.activityDate.endDate", LangUtil.convertStringToGMTDate("1/15/2013 05:00:00"));
    	filterCriteria.put("Activity.activitySubStatus", "Accepted");
    	filterCriteria.put("Activity.serviceRequest.asset.modelNumber", "4024");
    	listContract.setFilterCriteria(filterCriteria);
    	
    	Map<String, Object> sortCriteria =  new HashMap<String, Object>();
    	sortCriteria.put("Activity.activityDate", "ASCENDING");
    	listContract.setSortCriteria(sortCriteria);
    	
    	
    	ActivityListResult r = service.retrieveActivityList(listContract);
    	for (Activity activity : r.getActivityList()) {
    		logger.debug(activity.getServiceRequest().getAsset().getSerialNumber());
    	}
    	MiscTest.print(r.getActivityList());
    } 
    
    @Test
    public void testRetrieveActivityList_Defect11201() throws Exception {
    	ActivityListContract listContract = new  ActivityListContract();
    	listContract.setSessionHandle(crmSessionHandle);
    	listContract.setMdmId("30145");
    	listContract.setMdmLevel("Legal");
    	listContract.setQueryType("All");
    	listContract.setEmployeeId("1-73O5NHR");
    	listContract.setChangeManagementFlag(true);
    	listContract.setStatus("Open");
    	listContract.setStatus(ActivityStatus.All.toString());
    	listContract.setServiceRequestType("Fleet Management");
    	listContract.setIncrement(40);
    	listContract.setStartRecordNumber(0);
    	listContract.setNewQueryIndicator(true);
    	
    	Map<String, Object> filterCriteria = new HashMap<String, Object>();
    	filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("12/20/2013 13:00:00"));
    	filterCriteria.put("Activity.activityDate.endDate", LangUtil.convertStringToGMTDate("1/20/2014 13:00:00"));
    	listContract.setFilterCriteria(filterCriteria);
    	
    	Map<String, Object> sortCriteria =  new HashMap<String, Object>();
    	sortCriteria.put("Activity.activityStatus", "ASCENDING");
    	listContract.setSortCriteria(sortCriteria);
    	
    	
    	ActivityListResult r = service.retrieveActivityList(listContract);
    	for (Activity activity : r.getActivityList()) {
    		for (Part part : activity.getServiceRequest().getAsset().getPartList()) {
				logger.debug("PartName: " + part.getPartName());
				logger.debug("PartNumber: " + part.getPartNumber());
			}
    		
    	}
    	MiscTest.print(r.getActivityList());
    } 
    
    @Test
    public void testRetrieveActivityList_Defect11120() throws Exception {
    	ActivityListContract listContract = new  ActivityListContract();
    	listContract.setSessionHandle(crmSessionHandle);
    	listContract.setMdmId("30145");
    	listContract.setMdmLevel("Legal");
    	listContract.setQueryType("All");
    	listContract.setEmployeeId("1-73O5NHR");
    	listContract.setChangeManagementFlag(true);
    	listContract.setStatus("Open");
    	listContract.setStatus(ActivityStatus.All.toString());
    	listContract.setServiceRequestType("Fleet Management");
    	listContract.setIncrement(40);
    	listContract.setStartRecordNumber(0);
    	listContract.setNewQueryIndicator(true);
    	Map<String, Object> filterCriteria = new HashMap<String, Object>();
    	filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("12/28/2013 13:00:00"));
    	filterCriteria.put("Activity.activityDate.endDate", LangUtil.convertStringToGMTDate("1/28/2014 13:00:00"));
    	filterCriteria.put("Activity.activitySubStatus", "Pending SP Acknowledgement");
    	listContract.setFilterCriteria(filterCriteria);
    	Map<String, Object> sortCriteria =  new HashMap<String, Object>();
    	sortCriteria.put("Activity.serviceRequest.serviceRequestNumber", "ASCENDING");
    	listContract.setSortCriteria(sortCriteria);
    	
    	ActivityListResult r = service.retrieveActivityList(listContract);
    	for (Activity activity : r.getActivityList()) {
			logger.debug("getActivitySubStatus: " + activity.getActivitySubStatus());
		}
    	MiscTest.print(r.getActivityList());
    } 
    
    @Test
    public void testRetrieveActivityList_defect10948() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Legal");
		contract.setMdmId("30145");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("Fleet Management");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-73O5NHR");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(true);
		contract.setOfflineInstallDebrief(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("02/15/2014 05:00:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("01/15/2014 05:00:00"));
		filterCriteria.put("Activity.serviceRequest.serviceRequestNumber", "1-18115971952");
		contract.setFilterCriteria(filterCriteria);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.customerAccount.accountName", "ASCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveActivityList(contract);

		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();

		System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + totalCount);

		for (Activity activity : activities) {
			System.out.println("Service request number: " + activity.getServiceRequest().getServiceRequestNumber());
		}
	}
    
    @Test
    public void testRetrieveActivityList_defect11649() throws Exception {
    	ActivityListContract contract = new ActivityListContract();
    	contract.setStatus("Open");
    	contract.setMdmLevel("Legal");
    	contract.setMdmId("30145");
    	contract.setEmployeeFlag(false);
    	contract.setServiceRequestType("Fleet Management");
    	contract.setCountFlag(false);
    	contract.setEmployeeId("1-73O5NHR");
    	contract.setQueryType("All");
    	contract.setChangeManagementFlag(true);
    	contract.setOfflineInstallDebrief(false);
    	contract.setNewQueryIndicator(true);
    	contract.setStartRecordNumber(0);
    	contract.setIncrement(40);
    	contract.setSessionHandle(crmSessionHandle);
    	Map<String, Object> filterCriteria = new HashMap<String, Object>();
    	filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("02/11/2014 05:00:00"));
    	filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("01/11/2014 05:00:00"));
    	contract.setFilterCriteria(filterCriteria);
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("Activity.customerAccount.accountName", "ASCENDING");
    	contract.setSortCriteria(sortCriteria);
    	
    	ActivityListResult result = service.retrieveActivityList(contract);
    	
    	List<Activity> activities = result.getActivityList();
    	int totalCount = result.getTotalcount();
    	
    	System.out.println("Size: " + activities.size());
    	System.out.println("Total count: " + totalCount);
    	
    	for (Activity activity : activities) {
    		System.out.println("Service request number: " + activity.getServiceRequest().getServiceRequestNumber());
    	}
    }
    
    @Test
    public void testRetrieveActivityList_defect11881() throws Exception {
    	ActivityListContract contract = new ActivityListContract();
    	contract.setStatus("Open");
    	contract.setMdmLevel("Legal");
    	contract.setMdmId("30145");
    	contract.setEmployeeFlag(false);
    	contract.setServiceRequestType("Fleet Management");
    	contract.setCountFlag(false);
    	contract.setEmployeeId("1-73O5NHR");
    	contract.setQueryType("All");
    	contract.setChangeManagementFlag(true);
    	contract.setOfflineInstallDebrief(false);
    	contract.setNewQueryIndicator(true);
    	contract.setStartRecordNumber(0);
    	contract.setIncrement(40);
    	contract.setSessionHandle(crmSessionHandle);
    	Map<String, Object> filterCriteria = new HashMap<String, Object>();
    	filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("02/20/2014 05:00:00"));
    	filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("01/20/2014 05:00:00"));
    	filterCriteria.put("Activity.activitySubStatus", "Pending SP Acknowledgement");
    	contract.setFilterCriteria(filterCriteria);
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("Activity.serviceRequest.serviceRequestNumber", "DESCENDING");
    	contract.setSortCriteria(sortCriteria);
    	
    	ActivityListResult result = service.retrieveActivityList(contract);
    	
    	List<Activity> activities = result.getActivityList();
    	int totalCount = result.getTotalcount();
    	System.out.println("Size: " + activities.size());
    	System.out.println("Total count: " + totalCount);
    	for (Activity activity : activities) {
    		System.out.println("ActivitySubStatus: " + activity.getActivitySubStatus());
    	}
    }
    
    @Test
    public void testRetrieveActivityList_defect11931() throws Exception {
    	ActivityListContract contract = new ActivityListContract();
    	contract.setStatus("Open");
    	contract.setMdmLevel("Legal");
    	contract.setMdmId("30145");
    	contract.setEmployeeFlag(false);
    	contract.setServiceRequestType("Fleet Management");
    	contract.setCountFlag(false);
    	contract.setEmployeeId("1-73O5NHR");
    	contract.setQueryType("All");
    	contract.setChangeManagementFlag(true);
    	contract.setOfflineInstallDebrief(false);
    	contract.setNewQueryIndicator(true);
    	contract.setStartRecordNumber(0);
    	contract.setIncrement(40);
    	contract.setSessionHandle(crmSessionHandle);
    	Map<String, Object> filterCriteria = new HashMap<String, Object>();
    	filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("02/21/2014 05:00:00"));
    	filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("01/21/2014 05:00:00"));
    	filterCriteria.put("Activity.serviceRequest.asset.activitynumber", "1-8AQ709U");
    	contract.setFilterCriteria(filterCriteria);
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("Activity.serviceRequest.serviceRequestNumber", "DESCENDING");
    	contract.setSortCriteria(sortCriteria);
    	
    	ActivityListResult result = service.retrieveActivityList(contract);
    	
    	List<Activity> activities = result.getActivityList();
    	int totalCount = result.getTotalcount();
    	System.out.println("Size: " + activities.size());
    	System.out.println("Total count: " + totalCount);
    	for (Activity activity : activities) {
    		System.out.println("ProjectName: " + activity.getServiceRequest().getProjectName());
    	}
    }
    
    @Test
    public void testRetrieveActivityList_defect12093() throws Exception {
    	ActivityListContract contract = new ActivityListContract();
    	contract.setStatus("Open");
    	contract.setMdmLevel("Legal");
    	contract.setMdmId("30145");
    	contract.setEmployeeFlag(false);
    	contract.setServiceRequestType("Fleet Management");
    	contract.setCountFlag(false);
    	contract.setEmployeeId("1-73O5NHR");
    	contract.setQueryType("All");
    	contract.setChangeManagementFlag(true);
    	contract.setOfflineInstallDebrief(false);
    	contract.setNewQueryIndicator(true);
    	contract.setStartRecordNumber(0);
    	contract.setIncrement(40);
    	contract.setSessionHandle(crmSessionHandle);
    	Map<String, Object> filterCriteria = new HashMap<String, Object>();
    	filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("02/27/2014 18:30:00"));
    	filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("01/27/2014 18:30:00"));
    	contract.setFilterCriteria(filterCriteria);
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("Activity.serviceRequest.serviceRequestNumber", "DESCENDING");
    	contract.setSortCriteria(sortCriteria);
    	
    	ActivityListResult result = service.retrieveActivityList(contract);
    	
    	List<Activity> activities = result.getActivityList();
    	int totalCount = result.getTotalcount();
    	System.out.println("Size: " + activities.size());
    	System.out.println("Total count: " + totalCount);
    	for (Activity activity : activities) {
    		System.out.println("ResponseMetric: " + activity.getResponseMetric());
    		System.out.println("ProductLine: " + activity.getServiceRequest().getAsset().getProductLine());
    		System.out.println("ProductTLI: " + activity.getServiceRequest().getAsset().getProductTLI());
    	}
    }
    
    @Test
    public void testRetrieveActivityList_defect12120() throws Exception {
    	ActivityListContract contract = new ActivityListContract();
    	contract.setStatus("Open");
    	contract.setMdmLevel("Legal");
    	contract.setMdmId("30145");
    	contract.setEmployeeFlag(false);
    	contract.setServiceRequestType("Fleet Management");
    	contract.setCountFlag(false);
    	contract.setEmployeeId("1-2RQZOHP");
    	contract.setQueryType("All");
    	contract.setChangeManagementFlag(true);
    	contract.setOfflineInstallDebrief(false);
    	contract.setNewQueryIndicator(true);
    	contract.setStartRecordNumber(0);
    	contract.setIncrement(40);
    	contract.setSessionHandle(crmSessionHandle);
    	Map<String, Object> filterCriteria = new HashMap<String, Object>();
    	filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("02/28/2014 18:30:00"));
    	filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("01/28/2014 18:30:00"));
    	filterCriteria.put("Activity.serviceRequest.asset.activitynumber", "wa70");
    	contract.setFilterCriteria(filterCriteria);
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("Activity.serviceRequest.serviceRequestNumber", "DESCENDING");
    	contract.setSortCriteria(sortCriteria);
    	
    	ActivityListResult result = service.retrieveActivityList(contract);
    	
    	List<Activity> activities = result.getActivityList();
    	int totalCount = result.getTotalcount();
    	System.out.println("Size: " + activities.size());
    	System.out.println("Total count: " + totalCount);
    	for (Activity activity : activities) {
    		System.out.println("MachineTypeModel: " + activity.getServiceRequest().getAsset().getMachineTypeModel());
    		System.out.println("ProductTLI: " + activity.getServiceRequest().getAsset().getProductTLI());
    	}
    }
    
    @Test
    public void testRetrieveActivityList_defect12177() throws Exception {
    	ActivityListContract contract = new ActivityListContract();
    	contract.setStatus("Open");
    	contract.setMdmLevel("Global");
    	contract.setMdmId("422524798");
    	contract.setEmployeeFlag(false);
    	contract.setServiceRequestType("Fleet Management");
    	contract.setCountFlag(false);
    	contract.setEmployeeId("1-2RQZOHP");
    	contract.setQueryType("All");
    	contract.setChangeManagementFlag(true);
    	contract.setOfflineInstallDebrief(false);
    	contract.setNewQueryIndicator(true);
    	contract.setStartRecordNumber(0);
    	contract.setIncrement(40);
    	contract.setSessionHandle(crmSessionHandle);
    	Map<String, Object> filterCriteria = new HashMap<String, Object>();
    	filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("03/04/2014 18:30:00"));
    	filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("02/01/2014 18:30:00"));
    	filterCriteria.put("Activity.serviceRequest.asset.activitynumber", "fle3");
    	contract.setFilterCriteria(filterCriteria);
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("Activity.serviceRequest.serviceRequestNumber", "DESCENDING");
    	contract.setSortCriteria(sortCriteria);
    	
    	ActivityListResult result = service.retrieveActivityList(contract);
    	
    	List<Activity> activities = result.getActivityList();
    	int totalCount = result.getTotalcount();
    	System.out.println("Size: " + activities.size());
    	System.out.println("Total count: " + totalCount);
    	for (Activity activity : activities) {
    		System.out.println("MachineTypeModel: " + activity.getServiceRequest().getAsset().getMachineTypeModel());
    		System.out.println("ProductTLI: " + activity.getServiceRequest().getAsset().getProductTLI());
    	}
    }
    
    @Test
    public void testRetrieveActivityList_defect12529() throws Exception {
    	ActivityListContract contract = new ActivityListContract();
    	contract.setStatus("Open");
    	contract.setMdmLevel("Global");
    	contract.setMdmId("966331451");
    	contract.setEmployeeFlag(false);
    	contract.setServiceRequestType("Fleet Management");
    	contract.setCountFlag(false);
    	contract.setEmployeeId("1-73O5NHR");
    	contract.setQueryType("All");
    	contract.setChangeManagementFlag(true);
    	contract.setOfflineInstallDebrief(false);
    	contract.setNewQueryIndicator(true);
    	contract.setStartRecordNumber(0);
    	contract.setIncrement(40);
    	contract.setSessionHandle(crmSessionHandle);
    	Map<String, Object> filterCriteria = new HashMap<String, Object>();
    	filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("03/27/2014 18:30:00"));
    	filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("02/24/2014 18:30:00"));
    	filterCriteria.put("Activity.serviceRequest.asset.activitynumber", "1-946ZKV");
    	contract.setFilterCriteria(filterCriteria);
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("Activity.serviceRequest.serviceRequestNumber", "DESCENDING");
    	contract.setSortCriteria(sortCriteria);
    	
    	ActivityListResult result = service.retrieveActivityList(contract);
    	
    	List<Activity> activities = result.getActivityList();
    	int totalCount = result.getTotalcount();
    	System.out.println("Size: " + activities.size());
    	System.out.println("Total count: " + totalCount);
    	for (Activity activity : activities) {
			System.out.println("ServiceRequestDate: " + activity.getServiceRequest().getServiceRequestDate());
		}
    }
    
    
    @Test
    public void testRetrieveActivityList_defect12679() throws Exception {
    	ActivityListContract contract = new ActivityListContract();
    	contract.setStatus("Open");
    	contract.setMdmLevel("Legal");
    	contract.setMdmId("45185");
    	contract.setEmployeeFlag(false);
    	contract.setServiceRequestType("Fleet Management");
    	contract.setCountFlag(false);
    	contract.setEmployeeId("1-7GD8S5T");
    	contract.setQueryType("All");
    	contract.setChangeManagementFlag(true);
    	contract.setOfflineInstallDebrief(false);
    	contract.setNewQueryIndicator(true);
    	contract.setStartRecordNumber(0);
    	contract.setIncrement(40);
    	contract.setSessionHandle(crmSessionHandle);
    	Map<String, Object> filterCriteria = new HashMap<String, Object>();
    	filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("04/11/2014 04:00:00"));
    	filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("03/11/2014 04:00:00"));
    	filterCriteria.put("Activity.serviceRequest.asset.activitynumber", "1-99SHE8O");
    	contract.setFilterCriteria(filterCriteria);
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("Activity.serviceRequest.serviceRequestNumber", "DESCENDING");
    	contract.setSortCriteria(sortCriteria);
    	
    	ActivityListResult result = service.retrieveActivityList(contract);
    	
    	List<Activity> activities = result.getActivityList();
    	int totalCount = result.getTotalcount();
    	System.out.println("Size: " + activities.size());
    	System.out.println("Total count: " + totalCount);
    }
    
    @Test
    public void testRetrieveActivityList_changesTest() throws Exception {
    	ActivityListContract contract = new ActivityListContract();
    	contract.setStatus("Open");
    	contract.setMdmLevel("Legal");
    	contract.setMdmId("30145");
    	contract.setEmployeeFlag(false);
    	contract.setServiceRequestType("Fleet Management");
    	contract.setCountFlag(false);
    	contract.setEmployeeId("1-73O5NHR");
    	contract.setQueryType("All");
    	contract.setChangeManagementFlag(true);
    	contract.setOfflineInstallDebrief(false);
    	contract.setNewQueryIndicator(true);
    	contract.setStartRecordNumber(0);
    	contract.setIncrement(-1);
    	contract.setSessionHandle(crmSessionHandle);
    	Map<String, Object> filterCriteria = new HashMap<String, Object>();
    	filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("02/21/2014 05:00:00"));
    	filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("01/21/2014 05:00:00"));
    	filterCriteria.put("Activity.activityType", Arrays.asList("HW Install", "HW BAU Install", "HW MADC Install"));
//    	filterCriteria.put("Activity.activityType", Arrays.asList("HW Install"));
//    	filterCriteria.put("Activity.activityType", "HW Install");
    	contract.setFilterCriteria(filterCriteria);
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("Activity.serviceRequest.serviceRequestNumber", "DESCENDING");
    	contract.setSortCriteria(sortCriteria);
    	
    	ActivityListResult result = service.retrieveActivityList(contract);
    	
    	List<Activity> activities = result.getActivityList();
    	int totalCount = result.getTotalcount();
    	System.out.println("Size: " + activities.size());
    	System.out.println("Total count: " + totalCount); 
    	int i = 0;
    	for (Activity activity : activities) {
    		if (activity.getActivityType().getValue().equalsIgnoreCase("HW BAU Install") 
    				|| activity.getActivityType().getValue().equalsIgnoreCase("HW MADC Install")) {
				i++;
			}
		}
    	System.out.println("I count: " + i);
    }
    
    @Test
    public void testRetrieveActivityList_defect12124() throws Exception {
    	ActivityListContract contract = new ActivityListContract();
    	contract.setStatus("Open");
    	contract.setMdmLevel("Global");
    	contract.setMdmId("422524798");
    	contract.setEmployeeFlag(false);
    	contract.setServiceRequestType("Fleet Management");
    	contract.setCountFlag(false);
    	contract.setEmployeeId("1-7ZLX8AH");
    	contract.setQueryType("All");
    	contract.setChangeManagementFlag(true);
    	contract.setOfflineInstallDebrief(false);
    	contract.setNewQueryIndicator(true);
    	contract.setStartRecordNumber(0);
    	contract.setIncrement(40);
    	contract.setSessionHandle(crmSessionHandle);
    	Map<String, Object> filterCriteria = new HashMap<String, Object>();
    	filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("02/28/2014 05:00:00"));
    	filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("01/28/2014 05:00:00"));
    	filterCriteria.put("Activity.serviceRequest.asset.activitynumber", "1-8DO75I6");
    	contract.setFilterCriteria(filterCriteria);
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("Activity.activityDate", "DESCENDING");
    	contract.setSortCriteria(sortCriteria);
    	
    	ActivityListResult result = service.retrieveActivityList(contract);
    	
    	List<Activity> activities = result.getActivityList();
    	int totalCount = result.getTotalcount();
    	System.out.println("Size: " + activities.size());
    	System.out.println("Total count: " + totalCount); 
    	for (Activity activity : activities) {
			System.out.println("SR: " + activity.getServiceRequest().getAsset().getSerialNumber());
		}
    }
    
    @Test
    public void testRetrieveActivityList_defect12150() throws Exception {
    	ActivityListContract contract = new ActivityListContract();
    	contract.setStatus("Open");
    	contract.setMdmLevel("Legal");
    	contract.setMdmId("30145");
    	contract.setEmployeeFlag(false);
    	contract.setServiceRequestType("Fleet Management");
    	contract.setCountFlag(false);
    	contract.setEmployeeId("1-73O5NHR");
    	contract.setQueryType("All");
    	contract.setChangeManagementFlag(true);
    	contract.setOfflineInstallDebrief(false);
    	contract.setNewQueryIndicator(true);
    	contract.setStartRecordNumber(0);
    	contract.setIncrement(40);
    	contract.setSessionHandle(crmSessionHandle);
    	Map<String, Object> filterCriteria = new HashMap<String, Object>();
    	filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("03/03/2014 13:00:00"));
    	filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("01/31/2014 13:00:00"));
//    	filterCriteria.put("Activity.serviceRequest.asset.activitynumber", "1-8BMJVAH");
    	contract.setFilterCriteria(filterCriteria);
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("Activity.activityDate", "DESCENDING");
    	contract.setSortCriteria(sortCriteria);
    	
    	ActivityListResult result = service.retrieveActivityList(contract);
    	
    	List<Activity> activities = result.getActivityList();
    	int totalCount = result.getTotalcount();
    	System.out.println("Size: " + activities.size());
    	System.out.println("Total count: " + totalCount);
    	for (Activity activity : activities) {
    		System.out.println(activity.getActivityDate());
		}
    }
	
    @Test
    public void testRetrieveActivityList_Defect10089() throws Exception {
        ActivityListContract listContract = new  ActivityListContract();
        listContract.setSessionHandle(crmSessionHandle);
        listContract.setMdmId("23259");
        listContract.setMdmLevel("Legal");
        listContract.setStatus("Open");
        listContract.setStatus(ActivityStatus.All.toString());
        listContract.setQueryType("All");
        listContract.setEmployeeId("1-687WSZZ");
        listContract.setServiceRequestType("All");
        listContract.setIncrement(40);
        listContract.setStartRecordNumber(0);
        listContract.setNewQueryIndicator(true);
        listContract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
                "Activity.activityDate.endDate", LangUtil.convertStringToGMTDate("11/04/2013 08:00:00"),
                "Activity.activityDate.startDate",  LangUtil.convertStringToGMTDate("10/04/2013 08:00:00")
                ));
        listContract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
        		"Activity.activityId", "ASCENDING"));
        
        ActivityListResult r = service.retrieveActivityList(listContract);
        MiscTest.print(r.getActivityList(), "activityId", "activityType");
        for (Activity activity : r.getActivityList()) {
			System.out.println("Date: " + activity.getTechnician().getFirstName());
		}
        System.out.println("TotalCount: " + r.getTotalcount());
    } 
    
    @Test
    public void testRetrieveActivityList_Defect10387() throws Exception {
    	ActivityListContract listContract = new  ActivityListContract();
    	listContract.setSessionHandle(crmSessionHandle);
    	listContract.setMdmId("30145");
    	listContract.setMdmLevel("Legal");
    	listContract.setStatus("Open");
    	listContract.setStatus(ActivityStatus.All.toString());
    	listContract.setQueryType("All");
    	listContract.setEmployeeId("1-73O5NHR");
    	listContract.setServiceRequestType("All");
    	listContract.setIncrement(40);
    	listContract.setStartRecordNumber(0);
    	listContract.setNewQueryIndicator(true);
    	listContract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
    			"Activity.activityDate.endDate", LangUtil.convertStringToGMTDate("11/25/2013 13:00:00"),
    			"Activity.activityDate.startDate",  LangUtil.convertStringToGMTDate("10/25/2013 13:00:00")
    			));
    	listContract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
    			"Activity.activityId", "ASCENDING"));
    	
    	ActivityListResult r = service.retrieveActivityList(listContract);
    	MiscTest.print(r.getActivityList(), "activityId", "activityType");
    	for (Activity activity : r.getActivityList()) {
    		for (Part parts : activity.getServiceRequest().getAsset().getPartList()) {
				System.out.println("Description: " + parts.getDescription());
				System.out.println("Quantity: " + parts.getOrderQuantity());
				System.out.println("PartNumber: " + parts.getPartNumber());
			}
    	}
    	System.out.println("TotalCount: " + r.getTotalcount());
    } 
    
    
    @Test
    public void testRetrieveActivityList_Defect10386() throws Exception {
    	ActivityListContract listContract = new  ActivityListContract();
    	listContract.setSessionHandle(crmSessionHandle);
    	listContract.setMdmId("30145");
    	listContract.setMdmLevel("Legal");
    	listContract.setStatus("Open");
    	listContract.setStatus(ActivityStatus.All.toString());
    	listContract.setQueryType("All");
    	listContract.setEmployeeId("1-73O5NHR");
    	listContract.setServiceRequestType("All");
    	listContract.setIncrement(40);
    	listContract.setStartRecordNumber(0);
    	listContract.setNewQueryIndicator(true);
    	listContract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
    			"Activity.activityDate.endDate", LangUtil.convertStringToGMTDate("11/25/2013 13:00:00"),
    			"Activity.activityDate.startDate",  LangUtil.convertStringToGMTDate("10/25/2013 13:00:00"),
    			"Activity.serviceRequest.serviceRequestType", "Fleet Management"
    			));
    	listContract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
    			"Activity.activityId", "ASCENDING"));
    	
    	ActivityListResult r = service.retrieveActivityList(listContract);
    	MiscTest.print(r.getActivityList(), "activityId", "activityType");
    	for (Activity activity : r.getActivityList()) {
    		System.out.println("Request Type: " + activity.getServiceRequest().getServiceRequestType());
    	}
    	System.out.println("TotalCount: " + r.getTotalcount());
    }

   @Test
    public void testRetrieveActivityList_changesTest_offline() throws Exception {
    	ActivityListContract contract = new ActivityListContract();
    	contract.setStatus("Open");
    	contract.setMdmLevel("Legal");
    	contract.setMdmId("30145");
    	contract.setEmployeeFlag(false);
    	contract.setServiceRequestType("Fleet Management");
    	contract.setCountFlag(false);
    	contract.setEmployeeId("1-73O5NHR");
    	contract.setQueryType("All");
    	contract.setChangeManagementFlag(true);
    	contract.setOfflineInstallDebrief(false);
    	contract.setNewQueryIndicator(true);
    	contract.setStartRecordNumber(0);
    	contract.setIncrement(-1);
    	contract.setSessionHandle(crmSessionHandle);
    	Map<String, Object> filterCriteria = new HashMap<String, Object>();
    	filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("02/21/2014 05:00:00"));
    	filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("01/21/2014 05:00:00"));
    	filterCriteria.put("Activity.activityType", Arrays.asList("HW Install", "HW BAU Install", "HW MADC Install"));
//    	filterCriteria.put("Activity.activityType", Arrays.asList("HW Install"));
//    	filterCriteria.put("Activity.activityType", "HW Install");
    	contract.setFilterCriteria(filterCriteria);
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("Activity.serviceRequest.serviceRequestNumber", "DESCENDING");
    	contract.setSortCriteria(sortCriteria);
    	
    	AmindPartnerHardwareInstallDebriefOfflineModeService offlineService = new AmindPartnerHardwareInstallDebriefOfflineModeService();
    	offlineService.setStatelessSessionFactory(statelessSessionFactory);
    	
    	ActivityListResult result = offlineService.retrieveOfflineModeActivitiesList(contract);
    	
    	List<Activity> activities = result.getActivityList();
    	int totalCount = result.getTotalcount();
    	System.out.println("Size: " + activities.size());
    	System.out.println("Total count: " + totalCount); 
    	int i = 0;
    	for (Activity activity : activities) {
    		if (activity.getActivityType().getValue().equalsIgnoreCase("HW BAU Install") 
    				|| activity.getActivityType().getValue().equalsIgnoreCase("HW MADC Install")) {
    			i++;
    		}
    	}
    	System.out.println("I count: " + i);
    }
    
    @Test
    public void testRetrieveOfflineModeActivitiesList_defect12516() throws Exception {
    	ActivityListContract contract = new ActivityListContract();
    	contract.setStatus("Open");
    	contract.setMdmLevel("Global");
    	contract.setMdmId("966331451");
    	contract.setEmployeeFlag(false);
    	contract.setServiceRequestType("Fleet Management");
    	contract.setCountFlag(false);
    	contract.setEmployeeId("1-73O5NHR");
    	contract.setQueryType("All");
    	contract.setChangeManagementFlag(true);
    	contract.setOfflineInstallDebrief(true);
    	contract.setNewQueryIndicator(true);
    	contract.setStartRecordNumber(0);
    	contract.setIncrement(40);
    	contract.setSessionHandle(crmSessionHandle);
    	Map<String, Object> filterCriteria = new HashMap<String, Object>();
    	filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("03/27/2014 04:00:00"));
    	filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("02/24/2014 04:00:00"));
    	contract.setFilterCriteria(filterCriteria);
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("Activity.serviceRequest.serviceRequestNumber", "DESCENDING");
    	contract.setSortCriteria(sortCriteria);
    	
    	AmindPartnerHardwareInstallDebriefOfflineModeService offlineService = new AmindPartnerHardwareInstallDebriefOfflineModeService();
    	offlineService.setStatelessSessionFactory(statelessSessionFactory);
    	
    	ActivityListResult result = offlineService.retrieveOfflineModeActivitiesList(contract);
    	
    	List<Activity> activities = result.getActivityList();
    	int totalCount = result.getTotalcount();
    	System.out.println("Size: " + activities.size());
    	System.out.println("Total count: " + totalCount);
    	for (Activity activity : activities) {
    		for (Part part : activity.getServiceRequest().getAsset().getPartList()) {
				System.out.println("PartNumber: " + part.getPartNumber());
			}
		}
    }
    
    @Test
    public void testRetrieveOfflineModeActivitiesList_sort_filter() throws Exception {
    	ActivityListContract contract = new ActivityListContract();
    	contract.setStatus("Open");
    	contract.setMdmLevel("Global");
    	contract.setMdmId("966331451");
    	contract.setEmployeeFlag(false);
    	contract.setServiceRequestType("Fleet Management");
    	contract.setCountFlag(false);
    	contract.setEmployeeId("1-73O5NHR");
    	contract.setQueryType("All");
    	contract.setChangeManagementFlag(true);
    	contract.setOfflineInstallDebrief(false);
    	contract.setNewQueryIndicator(true);
    	contract.setStartRecordNumber(0);
    	contract.setIncrement(40);
    	contract.setSessionHandle(crmSessionHandle);
    	Map<String, Object> filterCriteria = new HashMap<String, Object>();
    	filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("04/01/2014 18:30:00"));
    	filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("03/01/2014 18:30:00"));
//    	filterCriteria.put("Activity.debriefStatus", "failed");
    	contract.setFilterCriteria(filterCriteria);
    	Map<String, Object> sortCriteria = new HashMap<String, Object>();
    	sortCriteria.put("Activity.debriefStatus", "ASCENDING");
//    	sortCriteria.put("Activity.activityType", "ASCENDING");
    	contract.setSortCriteria(sortCriteria);
    	
    	AmindPartnerHardwareInstallDebriefOfflineModeService offlineService = new AmindPartnerHardwareInstallDebriefOfflineModeService();
    	offlineService.setStatelessSessionFactory(statelessSessionFactory);
    	
    	ActivityListResult result = offlineService.retrieveOfflineModeActivitiesList(contract);
    	
    	List<Activity> activities = result.getActivityList();
    	int totalCount = result.getTotalcount();
    	System.out.println("Size: " + activities.size());
    	System.out.println("Total count: " + totalCount);
    	for (Activity activity : activities) {
    		System.out.println("DebriefStatus: " + activity.getDebriefStatus());
    	}
//    	for (Activity activity : activities) {
//    		System.out.println(activity.getActivityType());
//    	}
    }
    
    @Test
    public void testRetrieveActivityList_Defect10388() throws Exception {
    	ActivityListContract listContract = new  ActivityListContract();
    	listContract.setSessionHandle(crmSessionHandle);
    	listContract.setMdmId("1-NMCFPJ");
    	listContract.setMdmLevel("Siebel");
    	listContract.setStatus("Open");
    	listContract.setStatus(ActivityStatus.All.toString());
    	listContract.setQueryType("All");
    	listContract.setEmployeeId("1-73O5NHR");
    	listContract.setServiceRequestType("All");
    	listContract.setChangeManagementFlag(true);
    	listContract.setIncrement(40);
    	listContract.setStartRecordNumber(0);
    	listContract.setNewQueryIndicator(true);
    	listContract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
    			"Activity.activityDate.endDate", LangUtil.convertStringToGMTDate("11/25/2013 13:00:00"),
    			"Activity.activityDate.startDate",  LangUtil.convertStringToGMTDate("10/25/2013 13:00:00")
    			));
    	listContract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
    			"Activity.activityId", "ASCENDING"));
    	
    	ActivityListResult r = service.retrieveActivityList(listContract);
    	MiscTest.print(r.getActivityList(), "activityId", "activityType");
//    	for (Activity activity : r.getActivityList()) {
//    		System.out.println("StoreFromName: " + activity.getServiceRequest().getAsset().getStoreFrontName());
//    	}
    	for (Activity activity : r.getActivityList()) {
    		System.out.println("StoreFromName: " + activity.getServiceAddress().getStoreFrontName());
    	}
    	System.out.println("TotalCount: " + r.getTotalcount());
    }
    
    @Test
    public void testRetrieveActivityList_Defect10389() throws Exception {
    	ActivityListContract listContract = new  ActivityListContract();
    	listContract.setSessionHandle(crmSessionHandle);
    	listContract.setMdmId("30145");
    	listContract.setMdmLevel("Legal");
    	listContract.setStatus("Open");
    	listContract.setStatus(ActivityStatus.All.toString());
    	listContract.setQueryType("All");
    	listContract.setEmployeeId("1-73O5NHR");
    	listContract.setServiceRequestType("Fleet Management");
    	listContract.setChangeManagementFlag(true);
    	listContract.setIncrement(40);
    	listContract.setStartRecordNumber(0);
    	listContract.setNewQueryIndicator(true);
    	listContract.setFilterCriteria((Map<String, Object>) MiscTest.newHashMap(
    			"Activity.activityDate.endDate", LangUtil.convertStringToGMTDate("12/19/2013 18:30:00"),
    			"Activity.activityDate.startDate",  LangUtil.convertStringToGMTDate("11/19/2013 18:30:00")
    			));
    	listContract.setSortCriteria((Map<String, Object>) MiscTest.newHashMap(
    			"Activity.serviceRequest.serviceRequestNumber", "DESCENDING"));
    	
    	ActivityListResult r = service.retrieveActivityList(listContract);
    	MiscTest.print(r.getActivityList(), "activityId", "activityType");
    	for (Activity activity : r.getActivityList()) {
    		for (Part parts : activity.getServiceRequest().getAsset().getPartList()) {
				System.out.println("Model: " + parts.getModel());
			}
    	}
    	System.out.println("TotalCount: " + r.getTotalcount());
    }
    
	
	@Test
	public void testRetrieveActivityList_CommitedDateIssue() throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Legal");
		contract.setMdmId("2809");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("Fleet Management");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-7ZLX8AH");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(true);
		contract.setOfflineInstallDebrief(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("04/02/2014 04:00:00"));
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("03/02/2014 04:00:00"));
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate","DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveActivityList(contract);

		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();

		System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + totalCount);

		for (Activity activity : activities) {
			System.out.println(activity.getCustomerRequestedResponseDate());
		}

	}

    @Test
    public void testRetrieveClaimDetail_defect11315() throws Exception {
    	ClaimDetailContract contract = new ClaimDetailContract();
    	contract.setActivityId("1-8NR6I2Q");
    	contract.setServiceRequestId("1-8NOGJI3");
    	contract.setDebriefFlag(Boolean.FALSE);
    	ClaimDetailResult r = service.retrieveClaimDetail(contract);
    	System.out.println(str(r.getActivity())); 
    	for (Attachment attachment : r.getActivity().getAttachmentList()) {
			System.out.println("AttachmentName: " + attachment.getAttachmentName());
			System.out.println("AttachmentSource: " + attachment.getFileSourcePath());
		}
    }
    
    @Test
    public void testRetrieveClaimDetail_defect12386() throws Exception {
    	ClaimDetailContract contract = new ClaimDetailContract();
    	contract.setActivityId("1-9BRW69A");
    	contract.setServiceRequestId("1-9BRF531");
    	contract.setDebriefFlag(Boolean.FALSE);
    	ClaimDetailResult r = service.retrieveClaimDetail(contract);
    	System.out.println(str(r.getActivity())); 
    }

    @Test
    public void testRetrieveFRUPartList_QA() throws Exception {
        FRUPartListContract contract = new FRUPartListContract();
        contract.setModelNumber("5061-430");
        FRUPartListResult result = service.retrieveFRUPartList(contract);
        MiscTest.print(result.getPartList());
    }
    
    @Test
    public void testRetrieveFRUPartList_QA_defect12418() throws Exception {
    	FRUPartListContract contract = new FRUPartListContract();
    	contract.setModelNumber("4024-110");
    	contract.setHardwarePartsRequest(true);
    	FRUPartListResult result = service.retrieveFRUPartList(contract);
    	MiscTest.print(result.getPartList());
    }
    
    @Test
    public void testRetrieveFRUPartList_Sandbox_noPartList() throws Exception {
    	FRUPartListContract contract = new FRUPartListContract();
    	contract.setModelNumber("7014-6R6");
    	contract.setHardwarePartsRequest(true);
    	FRUPartListResult result = service.retrieveFRUPartList(contract);
    	MiscTest.print(result.getPartList());
    }
    
    @Test
    public void testRetrieveFRUPartList_defect11739() throws Exception {
    	FRUPartListContract contract = new FRUPartListContract();
    	contract.setModelNumber("7013-436");
    	contract.setHardwarePartsRequest(true);
    	FRUPartListResult result = service.retrieveFRUPartList(contract);
    	for (Part part : result.getPartList()) {
			System.out.println("PrinterType: " + part.isTypePrinter());
		}
    	MiscTest.print(result.getPartList());
    }

    @Test
    public void testRetrieveActivityDetail_Sandbox_defect13078() throws Exception {
    	ActivityDetailContract contract = new ActivityDetailContract();
    	contract.setActivityId("1-9D8C0S5");
    	contract.setServiceRequestId("1-9D87FXN");
    	contract.setDebriefFlag(false);
    	contract.setServicesUser(new ServicesUser());
    	
    	ActivityDetailResult result = service.retrieveActivityDetail(contract);
    	System.out.println("DeviceTag: " + result.getActivity().getServiceRequest().getAsset().getDeviceTag());
    	logger.debug("END" + result);
    }
	
	@Test
	public void testOfflineMode_Exception() throws Exception {

		AmindPartnerHardwareInstallDebriefOfflineModeService offlineModeSerivice = new AmindPartnerHardwareInstallDebriefOfflineModeService();

		OfflineModeAttachmentContract contract = new OfflineModeAttachmentContract();
		contract.setActivityId("1-80FR7RE");

		offlineModeSerivice.generateInstallationDoc(contract);

		System.out.println("End");
	}
	
	
	@Test
	public void testRetrieveClaimDetail_Defect12836() throws Exception {
		ClaimDetailContract contract = new ClaimDetailContract();
		contract.setActivityId("1-9BRW69A");
		contract.setServiceRequestId("1-9BRF531");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());

		ClaimDetailResult result = service.retrieveClaimDetail(contract);

		Activity activity = result.getActivity();
		
		List<PartLineItem> orderPartList = activity.getOrderPartList();
		
		for (PartLineItem partLineItem : orderPartList) {
			GenericAddress shipToAddress = partLineItem.getShipToAdress();
			System.out.println("Address line 1: " + shipToAddress.getAddressLine1());
			System.out.println("Address line 2: " + shipToAddress.getAddressLine2());
			System.out.println("Address line 3: " + shipToAddress.getAddressLine3());
			System.out.println("City: " + shipToAddress.getCity());
			System.out.println("State: " + shipToAddress.getState());
			System.out.println("Province: " + shipToAddress.getProvince());
			System.out.println("Postal code: " + shipToAddress.getPostalCode());
			System.out.println("Country: " + shipToAddress.getCountry());
		}
		
		System.out.println("END");
		
	}
	
	
	@Test
	public void testRetrieveActivityDetail_defect12889() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-9A06BSE");
		contract.setServiceRequestId("1-99ZX2ER");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		Activity activity = result.getActivity();
		
		AccountContact technician = activity.getTechnician();
		
		System.out.println("First name: " + technician.getFirstName());
		System.out.println("Last name: " + technician.getLastName());

	}
	
	
	@Test
	public void testRetrievePartnerAddressList_defect_critical()
			throws Exception {
		PartnerAddressListContract contract = new PartnerAddressListContract();
		contract.setAccountID("1-F683TZ");
		contract.setContactId("1-6OE8SH9");
		contract.setFavoriteFlag(false);
		contract.setNewQueryIndicator(true);
		
//		Map<String, Object> filterCriteria = new HashMap<String, Object>();
//		filterCriteria.put("postalCode", "L4B");
//		contract.setFilterCriteria(filterCriteria);
		
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("storeFrontName", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		
		contract.setSessionHandle(crmSessionHandle);
		PartnerAddressListResult result = service
				.retrievePartnerAddressList(contract);
		
		List<GenericAddress> addresses = result.getAddressList();
		
		for (GenericAddress genericAddress : addresses) {
			System.out.println(genericAddress.getPostalCode());
		}
		
	}
	
	@Test
	public void testRetrievePartnerAddressList_TotalCount_Production() throws Exception {
		PartnerAddressListContract contract = new PartnerAddressListContract();
		contract.setAccountID("1-HOZ0YV");
		contract.setContactId("1-3WXZU0X");
		contract.setFavoriteFlag(false);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("storeFrontName", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		contract.setNewQueryIndicator(false);
		contract.setStartRecordNumber(40);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		
		PartnerAddressListResult result = service.retrievePartnerAddressList(contract);
		System.out.println("List size: " + result.getAddressList().size());
		System.out.println("TotalCount: " + result.getTotalCount());
	}
	
	@Test
	public void testRetrievePartnerAddressList_defect16759() throws Exception {
		PartnerAddressListContract contract = new PartnerAddressListContract();
		contract.setAccountID("undefined");
		contract.setContactId("1-7G9V69N");
		contract.setFavoriteFlag(false);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("storeFrontName", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		
		PartnerAddressListResult result = service.retrievePartnerAddressList(contract);
		System.out.println("List size: " + result.getAddressList().size());
		System.out.println("TotalCount: " + result.getTotalCount());
	}
	
	@Test
	public void testRetrieveActivityDetail_defect13043() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-9C4Q6B5");
		contract.setServiceRequestId("1-20324370287");
		contract.setPageName("Debrief");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		Asset asset = result.getActivity().getServiceRequest().getAsset();
		
		System.out.println("moveToAddress: " + asset.getMoveToAddressGrouped());
		
	}
	
	@Test
	public void testRetrieveActivityDetail_defect13042() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-92VULYG");
		contract.setServiceRequestId("1-19764697170");
		contract.setPageName("Debrief");	
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		Activity activity = result.getActivity();
		
		AccountContact technician = activity.getTechnician();
		
		System.out.println("First name: " + technician.getFirstName());
		System.out.println("Last name: " + technician.getLastName());
		
		System.out.println("Technician name: " + technician.getTechnicianName());

	}
	
	
	@Test
	public void testRetrieveActivityDetail_defect12158() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-978HP0W");
		contract.setServiceRequestId("1-20006333491");
		contract.setPageName("Debrief");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		Activity activity = result.getActivity();
		
		System.out.println(activity == null);

	}
	
	@Test
	public void testRetrieveActivityDetail_defect11599() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-978HP0W");
		contract.setServiceRequestId("1-20006333491");
		contract.setPageName("Debrief");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());

		ActivityDetailResult result = service.retrieveActivityDetail(contract);

		Activity activity = result.getActivity();
		
		System.out.println(activity == null);

	}
	
	@Test
	public void testRetrieveActivityDetail_defect_xyCoordinates() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-NTL4943");
		contract.setServiceRequestId("1-51851431001");
		contract.setPageName("Debrief");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());
		ActivityDetailResult result = service.retrieveActivityDetail(contract);
		Activity activity = result.getActivity();
		System.out.println(activity == null);
	}
	
	@Test
	public void testRetrieveActivityDetail_defect16824() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-NFLR26F");
		contract.setServiceRequestId("1-51003238453");
		contract.setPageName("Debrief");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());
		ActivityDetailResult result = service.retrieveActivityDetail(contract);
		Activity activity = result.getActivity();
		System.out.println(activity == null);
	}
	
	@Test
	public void testRetrieveActivityDetail_defect17438() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-OS42R77");
		contract.setServiceRequestId("1-53902816921");
		contract.setPageName("Debrief");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());
		ActivityDetailResult result = service.retrieveActivityDetail(contract);
		Activity activity = result.getActivity();
		System.out.println("deviceTag: " + activity.getServiceRequest().getAsset().getDeviceTag());
	}
	
	@Test
	public void testRetrieveActivityDetail_INC0106003() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-UEII8GN");
		contract.setServiceRequestId("1-UEEZMLR");
//		contract.setPageName("Debrief");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());
		ActivityDetailResult result = service.retrieveActivityDetail(contract);
		Activity activity = result.getActivity();
		System.out.println("getCustomerRequestedResponseDate" + activity.getCustomerRequestedResponseDate());
		System.out.println("getResolutionDate" + activity.getResolutionDate());
	}
	
	@Test
	public void testRetrieveActivityDetail_PartStatus_prod() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-UAD9CEU");
		contract.setServiceRequestId("1-UACQ4E7");
//		contract.setPageName("Debrief");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());
		ActivityDetailResult result = service.retrieveActivityDetail(contract);
		Activity activity = result.getActivity();
		for (PartLineItem lineItem : activity.getOrderPartList()) {
			System.out.println("partStatus: " + lineItem.getPartStatus());
		}
	}
	
	@Test
	public void testRetrieveActivityDetail_NoData() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-P52731L");
		contract.setServiceRequestId("1-54672325837");
		contract.setPageName("Debrief");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());
		ActivityDetailResult result = service.retrieveActivityDetail(contract);
		System.out.println(result.getActivity().getActivityId());
	}
	
	@Test
	public void testRetrieveActivityDetail_CriticalPath() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-PPCUIB0");
		contract.setServiceRequestId("1-55949801946");
		contract.setPageName("Debrief");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());
		ActivityDetailResult result = service.retrieveActivityDetail(contract);
		System.out.println(result.getActivity().getActivityId());
		System.out.println("DeinstAssetTag: " + result.getActivity().getServiceRequest().getAsset().getDeinstAssetTag());
		System.out.println("DeviceCondition: " + result.getActivity().getServiceRequest().getAsset().getDeviceCondition());
	}
	
	@Test
	public void testRetrieveActivityDetail_CriticalPath_assetTag() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-PPCUI9N");
		contract.setServiceRequestId("1-55949801946");
		contract.setPageName("Debrief");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());
		ActivityDetailResult result = service.retrieveActivityDetail(contract);
		System.out.println("DeinstAssetTag: " + result.getActivity().getServiceRequest().getAsset().getDeinstAssetTag());
		System.out.println("DeviceCondition: " + result.getActivity().getServiceRequest().getAsset().getDeviceCondition());
		System.out.println("AssetTag: " + result.getActivity().getServiceRequest().getAsset().getAssetTag());
	}
	
	@Test
	public void testRetrieveActivityDetail_INC0115179() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-W0QKT8L");
		contract.setServiceRequestId("1-W0L640D");
//		contract.setPageName("Debrief");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());
		ActivityDetailResult result = service.retrieveActivityDetail(contract);
		for (PartLineItem lineItem : LangUtil.notNull(result.getActivity().getOrderPartList())) {
			System.out.println("PartNumber: " + lineItem.getPartNumber());
			System.out.println("PartStatus: " + lineItem.getPartStatus());
		}
		System.out.println(result.getActivity().getActivityId());
	}
	
	@Test
	public void testRetrieveActivityDetail_attachment() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-OV1R89V");
		contract.setServiceRequestId("1-54122645631");
		contract.setPageName("Debrief");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());
		ActivityDetailResult result = service.retrieveActivityDetail(contract);
		for (Attachment attachment : result.getActivity().getAttachmentList()) {
			System.out.println(attachment.getAttachmentName());
		}
	}
	
	@Test
	public void testRetrieveActivityDetail_defect17940() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-PBXHTPA");
		contract.setServiceRequestId("1-55117637041");
		contract.setPageName("Debrief");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());
		ActivityDetailResult result = service.retrieveActivityDetail(contract);
		System.out.println("DeviceCondition: " + result.getActivity().getDebrief().getDeviceCondition());
	}
	
	@Test
	public void testRetrieveActivityDetail_defect18312() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-PEJ6Q0E");
		contract.setServiceRequestId("1-PEI768E");
		contract.setPageName("Debrief");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());
		ActivityDetailResult result = service.retrieveActivityDetail(contract);
		for (PartLineItem partLineItem : result.getActivity().getOrderPartList()) {
			System.out.println(partLineItem.getPartName());
			System.out.println(partLineItem.getPartNumber());
			System.out.println(partLineItem.getExpedite());
		}
		System.out.println();
	}
	
	@Test
	public void testRetrieveActivityDetail_defect18421() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-M8UN07V");
		contract.setServiceRequestId("1-M5JR3DS");
		contract.setPageName("Debrief");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());
		ActivityDetailResult result = service.retrieveActivityDetail(contract);
		System.out.println(result.getActivity().getDebrief().getDeviceCondition().getValue());
	}
	
	@Test
	public void testRetrieveActivityDetail_defect11739() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-P209PEO");
		contract.setServiceRequestId("1-54540133748");
		contract.setPageName("Debrief");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());
		ActivityDetailResult result = service.retrieveActivityDetail(contract);
		System.out.println(result.getActivity().getDebrief().getDeviceCondition().getValue());
	}
	
	@Test
	public void testRetrieveActivityDetail_LBS1_5() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-PMB23FJ");
		contract.setServiceRequestId("1-55761617751");
		contract.setPageName("Debrief");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());
		ActivityDetailResult result = service.retrieveActivityDetail(contract);
		System.out.println("LevelOfDetails: " + result.getActivity().getServiceRequest().getAsset().getInstallAddress().getLevelOfDetails());
	}
	
	@Test
	public void testRetrieveActivityDetail_Notes() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-PMB23FJ");
		contract.setServiceRequestId("1-55761617751");
		contract.setPageName("Debrief");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());
		ActivityDetailResult result = service.retrieveActivityDetail(contract);
		for (ActivityNote note : result.getActivity().getActivityNoteList()) {
			System.out.println(note.getNoteId());
		}
		System.out.println(result.getActivity().getActivityNoteList().size());
		
	}
	
	@Test
	public void testRetrieveActivityDetail_pdfDownloadDdata() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-QNW1R6I");
		contract.setServiceRequestId("1-QNVSRL1");
//		contract.setPageName("Debrief");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());
		ActivityDetailResult result = service.retrieveActivityDetail(contract);
		System.out.println(result.getActivity().getActivityId());
	}
	
	@Test
	public void testRetrieveActivityDetail_recommendedParts() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-153F5SGV");
		contract.setServiceRequestId("1-153DJYS5");
//		contract.setPageName("Debrief");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());
		ActivityDetailResult result = service.retrieveActivityDetail(contract);
		for (PartLineItem partLineItem : result.getActivity().getRecommendedPartList()) {
			System.out.println("Part Number: " + partLineItem.getPartNumber());
			System.out.println("Part Name: " + partLineItem.getPartName());
		}
		System.out.println("-------------------");
		for (PartLineItem orderPart : result.getActivity().getOrderPartList()) {
			System.out.println("Part Number: " + orderPart.getPartNumber());
			System.out.println("Part Name: " + orderPart.getPartName());
		}
		System.out.println();
	}
	
	@Test
	public void testRetrieveActivityDetail_defect19899() throws Exception {
		ActivityDetailContract contract = new ActivityDetailContract();
		contract.setActivityId("1-RP62K17");
		contract.setServiceRequestId("1-60295056819");
		contract.setPageName("Debrief");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());
		ActivityDetailResult result = service.retrieveActivityDetail(contract);
		System.out.println(result.getActivity().getServiceRequest().getAsset().getInstallAddress().getCoordinatesXPreDebriefRFV());
		System.out.println(result.getActivity().getServiceRequest().getAsset().getInstallAddress().getCoordinatesYPreDebriefRFV());
		System.out.println();
	}
	
	@Test
	public void testRetrieveActivityList_defect12032() throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Siebel");
		contract.setMdmId("1-NMCFPJ");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("Fleet Management");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-93XQ2W1");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(true);
		contract.setOfflineInstallDebrief(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(-1);
		contract.setSessionHandle(crmSessionHandle);


		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.serviceRequest.serviceRequestNumber", "DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveActivityList(contract);

		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();

		System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + totalCount);

		for (Activity activity : activities) {
			System.out.println(activity.getActivityId());
		}

		System.out.println("End");
		
	}
	
	
	@Test
	public void testRetrieveActivityList_defect13042() throws Exception {

		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Legal");
		contract.setMdmId("30145");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("Fleet Management");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-93XQ2W1");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(true);
		contract.setOfflineInstallDebrief(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);

		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",
				LangUtil.convertStringToGMTDate("04/26/2014 04:00:00"));
		filterCriteria.put("Activity.activityDate.startDate",
				LangUtil.convertStringToGMTDate("03/26/2014 04:00:00"));
		filterCriteria.put("Activity.serviceRequest.asset.activitynumber", "1-9D3N12J");
		contract.setFilterCriteria(filterCriteria);

		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.serviceRequest.serviceRequestNumber", "DESCENDING");
		contract.setSortCriteria(sortCriteria);

		ActivityListResult result = service.retrieveActivityList(contract);

		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();

		System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + totalCount);

		for (Activity activity : activities) {
			System.out.println(activity.getTechnician().getTechnicianName());
		}

		System.out.println("End");
		
	}
	
	@Test
	public void testRetrieveActivityList_defect14589() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Legal");
		contract.setMdmId("60067");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("Fleet Management");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-7G9V69N");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(true);
		contract.setOfflineInstallDebrief(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("07/30/2014 18:30:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("06/29/2014 18:30:00"));
		contract.setFilterCriteria(filterCriteria);
		
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.serviceRequest.serviceRequestNumber", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		
		ActivityListResult result = service.retrieveActivityList(contract);
		
		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();
		
		System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + totalCount);
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveActivityList_defect13596() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setQueryType("All");
		contract.setMdmLevel("Siebel");
		contract.setMdmId("1-1CAD58Y");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("Fleet Management");
		contract.setRequestGridLoading(true);
		contract.setCountFlag(false);
		contract.setEmployeeId("1-CM1FOJX");
		contract.setChangeManagementFlag(true);
		contract.setMassUploadRequest(true);
		contract.setOfflineInstallDebrief(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("02/20/2015 05:00:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("01/20/2015 05:00:00"));
		contract.setFilterCriteria(filterCriteria);
		
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.serviceRequest.serviceRequestNumber", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		
		ActivityListResult result = service.retrieveActivityList(contract);
		
		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();
		
		System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + totalCount);
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveActivityList_defect14589_second() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setMdmLevel("Siebel");
		contract.setMdmId("1-HOZ0YV");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("Fleet Management");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-7G9V69N");
		contract.setQueryType("All");
		contract.setChangeManagementFlag(true);
		contract.setMassUploadRequest(true);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("07/30/2014 18:30:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("06/29/2014 18:30:00"));
		contract.setFilterCriteria(filterCriteria);
		
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.serviceRequest.serviceRequestNumber", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		
		ActivityListResult result = service.retrieveActivityList(contract);
		
		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();
		
		System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + totalCount);
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveActivityList_defect12075() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setQueryType("All");
		contract.setMdmLevel("Global");
		contract.setMdmId("118552798");
		contract.setEmployeeFlag(false);
		contract.setServiceRequestType("Fleet Management");
		contract.setCountFlag(false);
		contract.setEmployeeId("1-7G9V69N");
		contract.setChangeManagementFlag(true);
		contract.setMassUploadRequest(true);
		contract.setRequestGridLoading(true);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("01/27/2015 18:30:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("06/30/2014 18:30:00"));
		contract.setFilterCriteria(filterCriteria);
		
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.serviceRequest.serviceRequestNumber", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		
		ActivityListResult result = service.retrieveActivityList(contract);
		
		List<Activity> activities = result.getActivityList();
		int totalCount = result.getTotalcount();
		
		for (Activity activity : activities) {
			System.out.println("FirstName: " + activity.getServiceRequest().getMassUploadContact().getFirstName());
		}
		
		System.out.println("Size: " + activities.size());
		System.out.println("Total count: " + totalCount);
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveActivityList_ParentSR_filter() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setQueryType("All");
		contract.setMdmLevel("Global");
		contract.setMdmId("118552798");
		contract.setServiceRequestType("All");
		contract.setServiceRequestType("Fleet Management");
		contract.setEmployeeId("1-7G9V69N");
		contract.setCurrentTimeStamp(LangUtil.convertStringToGMTDate("02/10/2015 05:57:30"));
		contract.setEmployeeFlag(false);
		contract.setCountFlag(false);
		contract.setRequestGridLoading(true);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("02/10/2015 18:30:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("01/10/2014 18:30:00"));
		filterCriteria.put("Activity.parentSRNum", "1-47612642231");
		contract.setFilterCriteria(filterCriteria);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.parentSRNum", "ASCENDING");
		contract.setSortCriteria(sortCriteria);
		ActivityListResult result = service.retrieveActivityList(contract);
		
		List<Activity> activities = result.getActivityList();
		for (Activity activity : activities) {
			System.out.println(activity.getParentSRNum());
		}
		int totalCount = result.getTotalcount();
		System.out.println("Total count: " + totalCount);
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveActivityList_Prod_defect17457() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setQueryType("All");
		contract.setMdmLevel("Global");
		contract.setMdmId("399007207");
		contract.setServiceRequestType("Fleet Management");
		contract.setEmployeeId("1-16X93QX");
		contract.setEmployeeFlag(false);
		contract.setCountFlag(false);
		contract.setRequestGridLoading(true);
		contract.setChangeManagementFlag(true);
		contract.setMassUploadRequest(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("04/08/2015 04:00:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("03/08/2015 04:00:00"));
		contract.setFilterCriteria(filterCriteria);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.serviceRequest.serviceRequestNumber", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		ActivityListResult result = service.retrieveActivityList(contract);
		
		int totalCount = result.getTotalcount();
		System.out.println("Total count: " + totalCount);
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveActivityList_Prod_defect17457_massUpload() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setQueryType("All");
		contract.setMdmLevel("Siebel");
		contract.setMdmId("1-1CAD58R");
		contract.setServiceRequestType("Fleet Management");
		contract.setEmployeeId("1-16X93QX");
		contract.setEmployeeFlag(false);
		contract.setCountFlag(false);
		contract.setRequestGridLoading(true);
		contract.setChangeManagementFlag(true);
		contract.setMassUploadRequest(true);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("04/08/2015 04:00:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("03/08/2015 04:00:00"));
		contract.setFilterCriteria(filterCriteria);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.serviceRequest.serviceRequestNumber", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		ActivityListResult result = service.retrieveActivityList(contract);
		
		int totalCount = result.getTotalcount();
		System.out.println("Total count: " + totalCount);
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveActivityList_Prod_defect17457_downloadTemplate() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setQueryType("All");
		contract.setMdmLevel("Siebel");
		contract.setMdmId("1-1CAD58R");
		contract.setServiceRequestType("Fleet Management");
		contract.setEmployeeId("1-16X93QX");
		contract.setEmployeeFlag(false);
		contract.setCountFlag(false);
		contract.setRequestGridLoading(true);
		contract.setChangeManagementFlag(true);
		contract.setMassUploadRequest(true);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(-1);
		contract.setSessionHandle(crmSessionHandle);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("04/08/2015 04:00:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("03/08/2015 04:00:00"));
		contract.setFilterCriteria(filterCriteria);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.serviceRequest.serviceRequestNumber", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		ActivityListResult result = service.retrieveActivityList(contract);
		
		int totalCount = result.getTotalcount();
		System.out.println("Total count: " + totalCount);
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveActivityList_QA_defect17542() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("All");
		contract.setQueryType("All");
		contract.setMdmLevel("Global");
		contract.setMdmId("118552798");
		contract.setServiceRequestType("All");
		contract.setEmployeeId("1-7G9V69N");
		contract.setCurrentTimeStamp(LangUtil.convertStringToGMTDate("04/15/2015 16:59:09"));
//		contract.setCreateChildSR("true");
		contract.setEmployeeFlag(false);
		contract.setCountFlag(false);
		contract.setRequestGridLoading(true);
		contract.setChangeManagementFlag(false);
		contract.setMassUploadRequest(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(-1);
		contract.setSessionHandle(crmSessionHandle);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("04/16/2015 04:00:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("03/16/2015 04:00:00"));
		filterCriteria.put("Activity.serviceRequest.serviceRequestNumber", "1-46715989385");
		contract.setFilterCriteria(filterCriteria);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		ActivityListResult result = service.retrieveActivityList(contract);
		
		int totalCount = result.getTotalcount();
		System.out.println("Total count: " + totalCount);
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveActivityList_Prod_INC0104313() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("All");
		contract.setQueryType("All");
		contract.setMdmLevel("Legal");
		contract.setMdmId("49611");
		contract.setServiceRequestType("All");
		contract.setEmployeeId("1-A2TIH1J");
//		contract.setCurrentTimeStamp(LangUtil.convertStringToGMTDate("04/15/2015 16:59:09"));
		contract.setEmployeeFlag(false);
		contract.setCountFlag(false);
		contract.setRequestGridLoading(true);
		contract.setChangeManagementFlag(false);
		contract.setMassUploadRequest(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("05/7/2015 18:30:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("04/6/2015 18:30:00"));
		filterCriteria.put("Activity.serviceRequest.serviceRequestNumber", "1-65185080761");
		contract.setFilterCriteria(filterCriteria);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		ActivityListResult result = service.retrieveActivityList(contract);
		
		int totalCount = result.getTotalcount();
		System.out.println("Total count: " + totalCount);
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveActivityList_serviceProviderETA() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("All");
		contract.setQueryType("All");
		contract.setMdmLevel("Global");
		contract.setMdmId("690535281");
		contract.setServiceRequestType("Service Request");
		contract.setEmployeeId("1-7G9V69N");
//		contract.setCurrentTimeStamp(LangUtil.convertStringToGMTDate("04/15/2015 16:59:09"));
		contract.setEmployeeFlag(false);
		contract.setCountFlag(false);
		contract.setRequestGridLoading(false);
		contract.setChangeManagementFlag(false);
		contract.setMassUploadRequest(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("12/31/2014 18:30:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("11/30/2014 18:30:00"));
		filterCriteria.put("Activity.serviceRequest.serviceRequestNumber", "1-46813686349");
		contract.setFilterCriteria(filterCriteria);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		ActivityListResult result = service.retrieveActivityList(contract);
		
		for (Activity activity : result.getActivityList()) {
			System.out.println("ETA: " + activity.getServiceProviderETA());
		}
		int totalCount = result.getTotalcount();
		System.out.println("Total count: " + totalCount);
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveActivityList_ChangeManagementRequests() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setQueryType("All");
		contract.setMdmLevel("Legal");
		contract.setMdmId("2174");
		contract.setServiceRequestType("Fleet Management");
		contract.setEmployeeId("1-CN6RHBP");
//		contract.setCurrentTimeStamp(LangUtil.convertStringToGMTDate("04/15/2015 16:59:09"));
		contract.setEmployeeFlag(false);
		contract.setCountFlag(false);
		contract.setRequestGridLoading(true);
		contract.setChangeManagementFlag(true);
		contract.setMassUploadRequest(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("6/2/2015 18:30:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("5/2/2015 18:30:00"));
//		filterCriteria.put("Activity.serviceRequest.serviceRequestNumber", "1-46813686349");
		contract.setFilterCriteria(filterCriteria);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.serviceRequest.asset.activitynumber", "ASCENDING");
		contract.setSortCriteria(sortCriteria);
		ActivityListResult result = service.retrieveActivityList(contract);
		int totalCount = result.getTotalcount();
		System.out.println("Total count: " + totalCount);
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveActivityList_ChildSRfilter() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setQueryType("All");
		contract.setMdmLevel("Global");
		contract.setMdmId("118552798");
		contract.setServiceRequestType("All");
		contract.setEmployeeId("1-7G9V69N");
//		contract.setCurrentTimeStamp(LangUtil.convertStringToGMTDate("6/1/2015 21:21:18"));
		contract.setStatusLastUpdate("6/9/2015 19:58:21");
		contract.setEmployeeFlag(false);
		contract.setCountFlag(false);
		contract.setRequestGridLoading(true);
		contract.setCreateChildSR("true");
		contract.setChangeManagementFlag(false);
		contract.setMassUploadRequest(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(-1);
		contract.setSessionHandle(crmSessionHandle);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("6/10/2015 04:00:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("5/10/2015 04:00:00"));
//		filterCriteria.put("Activity.serviceRequest.serviceRequestNumber", "1-46813686349");
		contract.setFilterCriteria(filterCriteria);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		ActivityListResult result = service.retrieveActivityList(contract);
		int totalCount = result.getTotalcount();
		System.out.println("Total count: " + totalCount);
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveActivityList_CriticalPath() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setQueryType("All");
		contract.setMdmLevel("Legal");
		contract.setMdmId("17051");
		contract.setServiceRequestType("Fleet Management");
		contract.setEmployeeId("1-NFYUKO5");
//		contract.setCurrentTimeStamp(LangUtil.convertStringToGMTDate("6/1/2015 21:21:18"));
//		contract.setStatusLastUpdate("6/9/2015 19:58:21");
		contract.setEmployeeFlag(false);
		contract.setCountFlag(false);
		contract.setRequestGridLoading(true);
//		contract.setCreateChildSR("true");
		contract.setChangeManagementFlag(true);
		contract.setMassUploadRequest(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("06/02/2015 18:30:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("1/02/2015 18:30:00"));
//		filterCriteria.put("Activity.serviceRequest.serviceRequestNumber", "1-46813686349");
		contract.setFilterCriteria(filterCriteria);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		ActivityListResult result = service.retrieveActivityList(contract);
		int totalCount = result.getTotalcount();
		System.out.println("Total count: " + totalCount);
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveActivityList_defect18131() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setQueryType("All");
		contract.setMdmLevel("Global");
		contract.setMdmId("118552798");
		contract.setServiceRequestType("All");
		contract.setEmployeeId("1-7G9V69N");
		contract.setCurrentTimeStamp(LangUtil.convertStringToGMTDate("6/16/2015 17:21:23"));
//		contract.setStatusLastUpdate("6/9/2015 19:58:21");
		contract.setEmployeeFlag(false);
		contract.setCountFlag(false);
		contract.setRequestGridLoading(true);
//		contract.setCreateChildSR("true");
		contract.setChangeManagementFlag(true);
		contract.setMassUploadRequest(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("06/17/2015 04:00:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("05/17/2015 04:00:00"));
		filterCriteria.put("Activity.serviceRequest.serviceRequestNumber", "1-55665558881");
		contract.setFilterCriteria(filterCriteria);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		ActivityListResult result = service.retrieveActivityList(contract);
		int totalCount = result.getTotalcount();
		System.out.println("Total count: " + totalCount);
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveActivityList_defect18230() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setQueryType("All");
		contract.setMdmLevel("Global");
		contract.setMdmId("118552798");
		contract.setServiceRequestType("Claim Request");
		contract.setEmployeeId("1-7G9V69N");
		contract.setCurrentTimeStamp(LangUtil.convertStringToGMTDate("6/22/2015 09:23:05"));
//		contract.setStatusLastUpdate("6/9/2015 19:58:21");
		contract.setEmployeeFlag(false);
		contract.setCountFlag(false);
		contract.setRequestGridLoading(true);
//		contract.setCreateChildSR("true");
		contract.setChangeManagementFlag(false);
		contract.setMassUploadRequest(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("05/1/2015  18:30:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("03/31/2015 18:30:00"));
		filterCriteria.put("Activity.serviceRequest.serviceRequestNumber", "1-55665558881");
		contract.setFilterCriteria(filterCriteria);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		ActivityListResult result = service.retrieveActivityList(contract);
		int totalCount = result.getTotalcount();
		System.out.println("Total count: " + totalCount);
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveActivityList_INC0115558() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Closed");
		contract.setQueryType("All");
		contract.setMdmLevel("Siebel");
		contract.setMdmId("1-4M86U7D");
		contract.setServiceRequestType("All");
		contract.setEmployeeId("1-4RLCDT5");
//		contract.setCurrentTimeStamp(LangUtil.convertStringToGMTDate("6/22/2015 09:23:05"));
//		contract.setStatusLastUpdate("6/9/2015 19:58:21");
		contract.setEmployeeFlag(false);
		contract.setCountFlag(false);
		contract.setRequestGridLoading(false);
//		contract.setCreateChildSR("true");
		contract.setChangeManagementFlag(false);
		contract.setMassUploadRequest(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("06/18/2015 18:30:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("12/19/2014 18:30:00"));
		filterCriteria.put("Activity.serviceRequest.serviceRequestNumber", "1-68828307625");
		contract.setFilterCriteria(filterCriteria);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		ActivityListResult result = service.retrieveActivityList(contract);
		int totalCount = result.getTotalcount();
		System.out.println("Total count: " + totalCount);
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveActivityList_defect18201() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setQueryType("All");
		contract.setMdmLevel("Global");
		contract.setMdmId("118552798");
		contract.setServiceRequestType("Service Request");
		contract.setEmployeeId("1-7G9V69N");
		contract.setCurrentTimeStamp(LangUtil.convertStringToGMTDate("6/19/2015 09:52:08"));
//		contract.setStatusLastUpdate("6/9/2015 19:58:21");
		contract.setEmployeeFlag(false);
		contract.setCountFlag(false);
		contract.setRequestGridLoading(true);
//		contract.setCreateChildSR("true");
		contract.setChangeManagementFlag(false);
		contract.setMassUploadRequest(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("06/19/2015 18:30:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("05/19/2015 18:30:00"));
		contract.setFilterCriteria(filterCriteria);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		ActivityListResult result = service.retrieveActivityList(contract);
		int totalCount = result.getTotalcount();
		System.out.println("Total count: " + totalCount);
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveActivityList_defect18201_2() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setQueryType("All");
		contract.setMdmLevel("Global");
		contract.setMdmId("118552798");
		contract.setServiceRequestType("Fleet Management");
		contract.setEmployeeId("1-7G9V69N");
//		contract.setCurrentTimeStamp(LangUtil.convertStringToGMTDate("6/19/2015 09:52:08"));
//		contract.setStatusLastUpdate("6/9/2015 19:58:21");
		contract.setEmployeeFlag(false);
		contract.setCountFlag(false);
		contract.setRequestGridLoading(true);
//		contract.setCreateChildSR("true");
		contract.setChangeManagementFlag(true);
		contract.setMassUploadRequest(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("06/30/2015 04:00:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("05/30/2015 04:00:00"));
		contract.setFilterCriteria(filterCriteria);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityType", "ASCENDING");
		contract.setSortCriteria(sortCriteria);
		ActivityListResult result = service.retrieveActivityList(contract);
		int totalCount = result.getTotalcount();
		System.out.println("Total count: " + totalCount);
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveActivityList_defect18201_3() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setQueryType("All");
		contract.setMdmLevel("Global");
		contract.setMdmId("118552798");
		contract.setServiceRequestType("All");
		contract.setEmployeeId("1-7G9V69N");
		contract.setCurrentTimeStamp(LangUtil.convertStringToGMTDate("6/30/2015 20:13:33 "));
//		contract.setStatusLastUpdate("6/9/2015 19:58:21");
		contract.setEmployeeFlag(false);
		contract.setCountFlag(false);
		contract.setRequestGridLoading(true);
//		contract.setCreateChildSR("true");
		contract.setChangeManagementFlag(false);
		contract.setMassUploadRequest(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("06/30/2015 04:00:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("05/30/2015 04:00:00"));
		filterCriteria.put("Activity.serviceRequest.serviceRequestType", "Fleet Management");
		contract.setFilterCriteria(filterCriteria);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		ActivityListResult result = service.retrieveActivityList(contract);
		int totalCount = result.getTotalcount();
		System.out.println("Total count: " + totalCount);
		System.out.println("End");
	}
	
	
	@Test
	public void testRetrieveActivityList_INC0119048() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setQueryType("All");
		contract.setMdmLevel("Legal");
		contract.setMdmId("35437");
		contract.setServiceRequestType("All");
		contract.setEmployeeId("1-3RFMYQ9");
//		contract.setCurrentTimeStamp(LangUtil.convertStringToGMTDate("6/19/2015 09:52:08"));
//		contract.setStatusLastUpdate("6/9/2015 19:58:21");
		contract.setEmployeeFlag(false);
		contract.setCountFlag(false);
		contract.setRequestGridLoading(true);
//		contract.setCreateChildSR("true");
		contract.setChangeManagementFlag(false);
		contract.setMassUploadRequest(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("06/24/2015 18:30:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("05/24/2015 18:30:00"));
		contract.setFilterCriteria(filterCriteria);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		ActivityListResult result = service.retrieveActivityList(contract);
		int totalCount = result.getTotalcount();
		System.out.println("Total count: " + totalCount);
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveActivityList_INC0119048_2() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setQueryType("All");
		contract.setMdmLevel("Global");
		contract.setMdmId("118552798");
		contract.setServiceRequestType("All");
		contract.setEmployeeId("1-7G9V69N");
		contract.setCurrentTimeStamp(LangUtil.convertStringToGMTDate("6/25/2015 09:20:32"));
//		contract.setStatusLastUpdate("6/9/2015 19:58:21");
		contract.setEmployeeFlag(false);
		contract.setCountFlag(false);
		contract.setRequestGridLoading(true);
//		contract.setCreateChildSR("true");
		contract.setChangeManagementFlag(false);
		contract.setMassUploadRequest(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("06/25/2015 18:30:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("05/25/2015 18:30:00"));
		contract.setFilterCriteria(filterCriteria);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		ActivityListResult result = service.retrieveActivityList(contract);
		int totalCount = result.getTotalcount();
		System.out.println("Total count: " + totalCount);
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveActivityList_DataCheck() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("All");
		contract.setQueryType("All");
		contract.setMdmLevel("Global");
		contract.setMdmId("118552798");
		contract.setServiceRequestType("Fleet Management");
		contract.setEmployeeId("1-7G9V69N");
//		contract.setCurrentTimeStamp(LangUtil.convertStringToGMTDate("6/25/2015 09:20:32"));
//		contract.setStatusLastUpdate("6/9/2015 19:58:21");
		contract.setEmployeeFlag(false);
		contract.setCountFlag(false);
		contract.setRequestGridLoading(true);
//		contract.setCreateChildSR("true");
		contract.setChangeManagementFlag(true);
		contract.setMassUploadRequest(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("06/26/2015 18:30:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("05/26/2015 18:30:00"));
		contract.setFilterCriteria(filterCriteria);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		ActivityListResult result = service.retrieveActivityList(contract);
		int totalCount = result.getTotalcount();
		for (Activity activity : result.getActivityList()) {
			System.out.println("ActivityType: " + activity.getActivityType().getValue());
		}
		System.out.println("Total count: " + totalCount);
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveActivityList_Performance1() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setQueryType("All");
		contract.setMdmLevel("Legal");
		contract.setMdmId("17051");
		contract.setServiceRequestType("All");
		contract.setEmployeeId("1-NZKVU89");
//		contract.setCurrentTimeStamp(LangUtil.convertStringToGMTDate("6/25/2015 09:20:32"));
//		contract.setStatusLastUpdate("6/9/2015 19:58:21");
		contract.setEmployeeFlag(false);
		contract.setCountFlag(true);
		contract.setRequestGridLoading(false);
//		contract.setCreateChildSR("true");
		contract.setChangeManagementFlag(false);
		contract.setMassUploadRequest(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
//		filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("06/26/2015 18:30:00"));
//		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("05/26/2015 18:30:00"));
		filterCriteria.put("Activity.claimStatusDate", LangUtil.convertStringToGMTDate("06/15/2015 04:00:00"));
		filterCriteria.put("Activity.activitySubStatus", "Claim Accepted");
		contract.setFilterCriteria(filterCriteria);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		
		long t0 = System.currentTimeMillis();
		ActivityListResult result = service.retrieveActivityList(contract);
		System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
		
		int totalCount = result.getTotalcount();
		System.out.println("Total count: " + totalCount);
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveActivityList_Performance2() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setQueryType("All");
		contract.setMdmLevel("Legal");
		contract.setMdmId("17051");
		contract.setServiceRequestType("All");
		contract.setEmployeeId("1-NZKVU89");
//		contract.setCurrentTimeStamp(LangUtil.convertStringToGMTDate("6/25/2015 09:20:32"));
//		contract.setStatusLastUpdate("6/9/2015 19:58:21");
		contract.setEmployeeFlag(false);
		contract.setCountFlag(false);
		contract.setRequestGridLoading(false);
//		contract.setCreateChildSR("true");
		contract.setChangeManagementFlag(false);
		contract.setMassUploadRequest(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("06/26/2015 04:00:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("12/27/2014 04:00:00"));
//		filterCriteria.put("Activity.claimStatusDate", LangUtil.convertStringToGMTDate("06/15/2015 04:00:00"));
//		filterCriteria.put("Activity.activitySubStatus", "Claim Accepted");
		contract.setFilterCriteria(filterCriteria);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		
		long t0 = System.currentTimeMillis();
		ActivityListResult result = service.retrieveActivityList(contract);
		System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
		
		int totalCount = result.getTotalcount();
		System.out.println("Total count: " + totalCount);
		System.out.println("End");
	} 
	
	@Test
	public void testRetrieveActivityList_INC0119015() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setQueryType("All");
		contract.setMdmLevel("Siebel");
		contract.setMdmId("1-TMVC22S");
		contract.setServiceRequestType("All");
		contract.setEmployeeId("1-8UHNP9T");
//		contract.setCurrentTimeStamp(LangUtil.convertStringToGMTDate("6/25/2015 09:20:32"));
//		contract.setStatusLastUpdate("6/9/2015 19:58:21");
		contract.setEmployeeFlag(false);
		contract.setCountFlag(false);
		contract.setRequestGridLoading(false);
//		contract.setCreateChildSR("true");
		contract.setChangeManagementFlag(false);
		contract.setMassUploadRequest(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("06/29/2015 18:30:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("05/29/2015 18:30:00"));
		filterCriteria.put("Activity.serviceRequest.serviceRequestNumber", "1-69309453861");
		contract.setFilterCriteria(filterCriteria);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		
		long t0 = System.currentTimeMillis();
		ActivityListResult result = service.retrieveActivityList(contract);
		System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
		
		int totalCount = result.getTotalcount();
		System.out.println("Total count: " + totalCount);
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveActivityList_defect18291() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("All");
		contract.setQueryType("All");
		contract.setMdmLevel("Legal");
		contract.setMdmId("43386");
		contract.setServiceRequestType("All");
		contract.setEmployeeId("1-BU035MZ");
//		contract.setCurrentTimeStamp(LangUtil.convertStringToGMTDate("6/25/2015 09:20:32"));
//		contract.setStatusLastUpdate("6/9/2015 19:58:21");
		contract.setEmployeeFlag(false);
		contract.setCountFlag(false);
		contract.setRequestGridLoading(true);
//		contract.setCreateChildSR("true");
		contract.setChangeManagementFlag(false);
		contract.setMassUploadRequest(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("06/29/2015 18:30:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("05/29/2015 18:30:00"));
		filterCriteria.put("Activity.serviceRequest.serviceRequestNumber", "1-56065556121");
		contract.setFilterCriteria(filterCriteria);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		
		long t0 = System.currentTimeMillis();
		ActivityListResult result = service.retrieveActivityList(contract);
		System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
		
		int totalCount = result.getTotalcount();
		System.out.println("Total count: " + totalCount);
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveActivityList_DecommissionActivitiesMissing() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Open");
		contract.setQueryType("All");
		contract.setMdmLevel("Global");
		contract.setMdmId("118552798");
		contract.setServiceRequestType("Fleet Management");
		contract.setEmployeeId("1-16X93QX");
//		contract.setCurrentTimeStamp(LangUtil.convertStringToGMTDate("6/25/2015 09:20:32"));
//		contract.setStatusLastUpdate("6/9/2015 19:58:21");
		contract.setEmployeeFlag(false);
		contract.setCountFlag(false);
		contract.setRequestGridLoading(true);
//		contract.setCreateChildSR("true");
		contract.setChangeManagementFlag(false);
		contract.setMassUploadRequest(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("07/02/2015 04:00:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("06/01/2015 04:00:00"));
//		filterCriteria.put("Activity.serviceRequest.serviceRequestNumber", "1-56065556121");
		contract.setFilterCriteria(filterCriteria);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		
		long t0 = System.currentTimeMillis();
		ActivityListResult result = service.retrieveActivityList(contract);
		System.out.printf("Exec time=%s sec. \n", (System.currentTimeMillis() - t0) / 1000.0);
		
		int totalCount = result.getTotalcount();
		System.out.println("Total count: " + totalCount);
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveActivityList_ActualFailureCode() throws Exception {
		ActivityListContract contract = new ActivityListContract();
		contract.setStatus("Closed");
		contract.setQueryType("All");
		contract.setMdmLevel("Legal");
		contract.setMdmId("34143");
		contract.setServiceRequestType("Service Request");
		contract.setEmployeeId("1-46DUCFV");
//		contract.setCurrentTimeStamp(LangUtil.convertStringToGMTDate("6/25/2015 09:20:32"));
//		contract.setStatusLastUpdate("6/9/2015 19:58:21");
		contract.setEmployeeFlag(false);
		contract.setCountFlag(false);
		contract.setRequestGridLoading(true);
//		contract.setCreateChildSR("true");
		contract.setChangeManagementFlag(false);
		contract.setMassUploadRequest(false);
		contract.setNewQueryIndicator(true);
		contract.setStartRecordNumber(0);
		contract.setIncrement(40);
		contract.setSessionHandle(crmSessionHandle);
		Map<String, Object> filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("Activity.activityDate.endDate",	LangUtil.convertStringToGMTDate("08/31/2015 18:30:00"));
		filterCriteria.put("Activity.activityDate.startDate", LangUtil.convertStringToGMTDate("02/28/2015 18:30:00"));
		filterCriteria.put("Activity.serviceRequest.serviceRequestNumber", "1-52319316214");
		contract.setFilterCriteria(filterCriteria);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("Activity.activityDate", "DESCENDING");
		contract.setSortCriteria(sortCriteria);
		
		ActivityListResult result = service.retrieveActivityList(contract);
		for (Activity activity : result.getActivityList()) {
			System.out.println("ActualFailureCode: " + activity.getDebrief().getActualFailureCode());
		}
		
		int totalCount = result.getTotalcount();
		System.out.println("Total count: " + totalCount);
		System.out.println("End");
	}
	
	@Test
	public void testRetrieveClaimDetail_BRD14_06_03ShowLexmarkReviewFlaginClaimUpdatePage() throws Exception {
		ClaimDetailContract contract = new ClaimDetailContract();
		contract.setActivityId("1-97IJ14E");
		contract.setServiceRequestId("1-97GAQ0X");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());

		ClaimDetailResult result = service.retrieveClaimDetail(contract);
		Activity activity = result.getActivity();
		System.out.println(activity == null);
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveClaimDetail_defect16945() throws Exception {
		ClaimDetailContract contract = new ClaimDetailContract();
		contract.setActivityId("1-LIR0Y0M");
		contract.setServiceRequestId("1-LIR0Y01");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());
		
		ClaimDetailResult result = service.retrieveClaimDetail(contract);
		Activity activity = result.getActivity();
		System.out.println(activity == null);
		System.out.println("END");
	}
	
	@Test
	public void testRetrieveClaimDetail_DisplayWarning() throws Exception {
		ClaimDetailContract contract = new ClaimDetailContract();
		contract.setActivityId("1-RLG8NQ7");
		contract.setServiceRequestId("1-RLG8NPV");
		contract.setDebriefFlag(false);
		contract.setServicesUser(new ServicesUser());
		
		ClaimDetailResult result = service.retrieveClaimDetail(contract);
		Activity activity = result.getActivity();
		System.out.println("DisplayWarning: " + activity.getDisplayWarning());
		System.out.println("END");
	}
	
}
