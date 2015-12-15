package com.lexmark.portlet;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.portlet.MockPortletSession;
import org.springframework.mock.web.portlet.MockRenderRequest;
import org.springframework.mock.web.portlet.MockRenderResponse;
import org.springframework.mock.web.portlet.MockResourceRequest;
import org.springframework.mock.web.portlet.MockResourceResponse;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.constants.LexmarkPPConstants;
import com.lexmark.contract.ActivityListContract;
import com.lexmark.contract.ClaimDetailContract;
import com.lexmark.contract.LocalizedSiebelLOVListContract;
import com.lexmark.contract.LocalizedSiebelValueContract;
import com.lexmark.contract.PartnerNotificationsContract;
import com.lexmark.contract.ProductImageContract;
import com.lexmark.contract.ServiceActivityHistoryDetailContract;
import com.lexmark.contract.ServiceActivityHistoryListContract;
import com.lexmark.contract.SiebelLOVListContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.Activity;
import com.lexmark.domain.Asset;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.ServicesUser;
import com.lexmark.form.RequestListForm;
import com.lexmark.result.ActivityListResult;
import com.lexmark.result.ClaimDetailResult;
import com.lexmark.result.DownloadClaimListResult;
import com.lexmark.result.DownloadRequestListResult;
import com.lexmark.result.LdapUserDataResult;
import com.lexmark.result.LocalizedSiebelLOVListResult;
import com.lexmark.result.LocalizedSiebelValueResult;
import com.lexmark.result.PartnerNotificationsResult;
import com.lexmark.result.ProductImageResult;
import com.lexmark.result.ServiceActivityHistoryDetailResult;
import com.lexmark.result.ServiceActivityHistoryListResult;
import com.lexmark.result.SiebelLOVListResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.PartnerRequestsService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.service.impl.real.enums.QueryType;
import com.lexmark.util.PortalSessionUtil;
import com.lexmark.util.TestUtil;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.PortalUtil;

public class RequestControllerTest extends EasyMockSupport {

	private RequestController requestController = null;

	private PartnerRequestsService partnerRequestsService = null;
	private GlobalService globalService = null;
	private ServiceRequestLocale serviceRequestLocaleService = null;
	private ProductImageService productImageService = null;
	private GridSettingController gridSettingController = null;

	private static List<ListOfValues> requestStatusList = null;
	private static List<ListOfValues> requestTypeList = null;
	private static List<ListOfValues> serviceTypeList = null;
	private static List<ListOfValues> claimRequestStatusList = null;
	private static List<ListOfValues> serviceRequestStatusList = null;
	private static List<ListOfValues> requestStatusDetailList = null;
	private static List<ListOfValues> claimRequestStatusDetailList = null;
	private static List<ListOfValues> serviceRequestStatusDetailList = null;

	@BeforeClass
	public static void prepareData() {
		ListOfValues requestStatus1 = new ListOfValues();
		requestStatus1.setName("Close");
		requestStatus1.setValue("Close");
		ListOfValues requestStatus2 = new ListOfValues();
		requestStatus2.setName("Open");
		requestStatus2.setValue("Open");
		requestStatusList = Arrays.asList(requestStatus1, requestStatus2);

		ListOfValues requestType1 = new ListOfValues();
		requestType1.setName("claim request");
		requestType1.setValue("claim request");
		ListOfValues requestType2 = new ListOfValues();
		requestType2.setName("service request");
		requestType2.setValue("service request");
		requestTypeList = Arrays.asList(requestType1, requestType2);

		ListOfValues serviceType1 = new ListOfValues();
		serviceType1.setName("Exchange of Device");
		serviceType1.setValue("Exchange of Device");
		ListOfValues serviceType2 = new ListOfValues();
		serviceType2.setName("Maintenance Kit and Install");
		serviceType2.setValue("Maintenance Kit and Install");
		serviceTypeList = Arrays.asList(serviceType1, serviceType2);

		ListOfValues claimRequestStatus1 = new ListOfValues();
		claimRequestStatus1.setName("Cancelled Service");
		claimRequestStatus1.setValue("Cancelled Service");
		ListOfValues claimRequestStatus2 = new ListOfValues();
		claimRequestStatus2.setName("Claim Submitted");
		claimRequestStatus2.setValue("Claim Submitted");
		claimRequestStatusList = Arrays.asList(claimRequestStatus1, claimRequestStatus2);

		ListOfValues serviceRequestStatus1 = new ListOfValues();
		serviceRequestStatus1.setName("Cancelled Service");
		serviceRequestStatus1.setValue("Cancelled Service");
		ListOfValues serviceRequestStatus2 = new ListOfValues();
		serviceRequestStatus2.setName("Completed");
		serviceRequestStatus2.setValue("Completed");
		serviceRequestStatusList = Arrays.asList(serviceRequestStatus1, serviceRequestStatus2);

		claimRequestStatusList = new ArrayList<ListOfValues>();
		claimRequestStatusList.addAll(claimRequestStatusList);
		claimRequestStatusList.addAll(serviceRequestStatusList);

		ListOfValues claimRequestStatusDetail1 = new ListOfValues();
		claimRequestStatusDetail1.setName("Accepted");
		claimRequestStatusDetail1.setValue("Accepted");
		ListOfValues claimRequestStatusDetail2 = new ListOfValues();
		claimRequestStatusDetail2.setName("In process");
		claimRequestStatusDetail2.setValue("In process");
		claimRequestStatusDetailList = Arrays.asList(claimRequestStatusDetail1, claimRequestStatusDetail2);

		ListOfValues serviceRequestStatusDetail1 = new ListOfValues();
		serviceRequestStatusDetail1.setName("Accepted");
		serviceRequestStatusDetail1.setValue("Accepted");
		ListOfValues serviceRequestStatusDetail2 = new ListOfValues();
		serviceRequestStatusDetail2.setName("Action Incomplete");
		serviceRequestStatusDetail2.setValue("Action Incomplete");
		serviceRequestStatusDetailList = Arrays.asList(serviceRequestStatusDetail1, serviceRequestStatusDetail2);

		requestStatusDetailList = new ArrayList<ListOfValues>();
		requestStatusDetailList.addAll(claimRequestStatusDetailList);
		requestStatusDetailList.addAll(serviceRequestStatusDetailList);
	}

	@Before
	public void setUp() throws Exception {
		requestController = new RequestController();

		partnerRequestsService = createMock(PartnerRequestsService.class);
		globalService = createMock(GlobalService.class);
		serviceRequestLocaleService = createMock(ServiceRequestLocale.class);
		productImageService = createMock(ProductImageService.class);
		gridSettingController = new GridSettingController();

		TestUtil.setProperty(requestController, "partnerRequestsService", partnerRequestsService);
		TestUtil.setProperty(requestController, "globalService", globalService);
		TestUtil.setProperty(requestController, "serviceRequestLocaleService", serviceRequestLocaleService);
		TestUtil.setProperty(requestController, "productImageService", productImageService);
		TestUtil.setProperty(requestController, "gridSettingController", gridSettingController);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = IllegalArgumentException.class)
	public void testShowRequestListUserRolesNull() throws Exception {
		requestController.showRequestList(new MockRenderRequest(), new MockRenderResponse(), new ExtendedModelMap());
	}

	@Test
	public void testShowRequestListSuccessIndirectPartner() throws Exception {
		MockRenderRequest request = new MockRenderRequest();
		MockPortletSession session = new MockPortletSession();
		PortalSessionUtil.setIndirectPartnerFlag(session, true);
		PortalSessionUtil.setCreateClaimFlag(session, true);
		LdapUserDataResult result = new LdapUserDataResult();
		result.setUserRoles(Arrays.asList(LexmarkConstants.ROLE_SERVICE_MANAGER,
				LexmarkConstants.ROLE_SERVICE_TECHNICIAN));
		result.setUserSegment("employee");
		PortalSessionUtil.setLdapUserData(session, result);
		request.setSession(session);

		LocalizedSiebelLOVListResult requestTypeLOVListResult = new LocalizedSiebelLOVListResult();
		requestTypeLOVListResult.setLocalizedSiebelLOVList(requestTypeList);

		LocalizedSiebelLOVListResult requestStatusLOVListResult = new LocalizedSiebelLOVListResult();
		requestStatusLOVListResult.setLocalizedSiebelLOVList(requestStatusList);

		LocalizedSiebelLOVListResult serviceTypeLOVListResult = new LocalizedSiebelLOVListResult();
		serviceTypeLOVListResult.setLocalizedSiebelLOVList(serviceTypeList);

		LocalizedSiebelLOVListResult requestStatusDetailLOVListResult = new LocalizedSiebelLOVListResult();
		requestStatusDetailLOVListResult.setLocalizedSiebelLOVList(requestStatusDetailList);

		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract) anyObject()))
				.andReturn(requestTypeLOVListResult);
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract) anyObject()))
				.andReturn(requestStatusLOVListResult);
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract) anyObject()))
				.andReturn(serviceTypeLOVListResult);
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract) anyObject()))
				.andReturn(requestStatusDetailLOVListResult);

		replayAll();

		ExtendedModelMap model = new ExtendedModelMap();
		requestController.showRequestList(request, new MockRenderResponse(), model);
		RequestListForm requestListForm = (RequestListForm) model.get("requestListForm");
		assertTrue(requestListForm.isCreateClaimFlag());
		assertNull(requestListForm.getServiceRequestStatusDetailMap());
		assertNull(requestListForm.getServiceRequestStatusMap());
		assertNull(requestListForm.getClaimRequestStatusDetailMap());
		assertNull(requestListForm.getClaimRequestStatusMap());

		verifyAll();
	}

	@Test
	public void testShowRequestListSuccessDirectPartner() throws Exception {
		MockRenderRequest request = new MockRenderRequest();
		MockPortletSession session = new MockPortletSession();
		PortalSessionUtil.setDirectPartnerFlag(session, true);
		LdapUserDataResult result = new LdapUserDataResult();
		result.setUserRoles(Arrays.asList(LexmarkConstants.ROLE_SERVICE_MANAGER,
				LexmarkConstants.ROLE_SERVICE_TECHNICIAN));
		result.setUserSegment("employee");
		PortalSessionUtil.setLdapUserData(session, result);
		request.setSession(session);

		LocalizedSiebelLOVListResult requestTypeLOVListResult = new LocalizedSiebelLOVListResult();
		requestTypeLOVListResult.setLocalizedSiebelLOVList(requestTypeList);

		LocalizedSiebelLOVListResult requestStatusLOVListResult = new LocalizedSiebelLOVListResult();
		requestStatusLOVListResult.setLocalizedSiebelLOVList(requestStatusList);

		LocalizedSiebelLOVListResult serviceTypeLOVListResult = new LocalizedSiebelLOVListResult();
		serviceTypeLOVListResult.setLocalizedSiebelLOVList(serviceTypeList);

		LocalizedSiebelLOVListResult requestStatusDetailLOVListResult = new LocalizedSiebelLOVListResult();
		requestStatusDetailLOVListResult.setLocalizedSiebelLOVList(requestStatusDetailList);

		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract) anyObject()))
				.andReturn(requestTypeLOVListResult);
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract) anyObject()))
				.andReturn(requestStatusLOVListResult);
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract) anyObject()))
				.andReturn(serviceTypeLOVListResult);
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract) anyObject()))
				.andReturn(requestStatusDetailLOVListResult);

		replayAll();

		ExtendedModelMap model = new ExtendedModelMap();
		requestController.showRequestList(request, new MockRenderResponse(), model);
		RequestListForm requestListForm = (RequestListForm) model.get("requestListForm");
		assertFalse(requestListForm.isCreateClaimFlag());
		assertNull(requestListForm.getServiceRequestStatusDetailMap());
		assertNull(requestListForm.getServiceRequestStatusMap());
		assertNull(requestListForm.getClaimRequestStatusDetailMap());
		assertNull(requestListForm.getClaimRequestStatusMap());

		verifyAll();
	}

	@Test
	public void testShowRequestListSuccessBothIndirectPartnerAndDirectPartner() throws Exception {
		MockRenderRequest request = new MockRenderRequest();
		MockPortletSession session = new MockPortletSession();
		PortalSessionUtil.setIndirectPartnerFlag(session, true);
		PortalSessionUtil.setDirectPartnerFlag(session, true);
		PortalSessionUtil.setCreateClaimFlag(session, true);
		LdapUserDataResult result = new LdapUserDataResult();
		result.setUserRoles(Arrays.asList(LexmarkConstants.ROLE_SERVICE_MANAGER,
				LexmarkConstants.ROLE_SERVICE_TECHNICIAN));
		result.setUserSegment("employee");
		PortalSessionUtil.setLdapUserData(session, result);
		request.setSession(session);

		LocalizedSiebelLOVListResult requestTypeLOVListResult = new LocalizedSiebelLOVListResult();
		requestTypeLOVListResult.setLocalizedSiebelLOVList(requestTypeList);

		LocalizedSiebelLOVListResult requestStatusLOVListResult = new LocalizedSiebelLOVListResult();
		requestStatusLOVListResult.setLocalizedSiebelLOVList(requestStatusList);

		LocalizedSiebelLOVListResult serviceTypeLOVListResult = new LocalizedSiebelLOVListResult();
		serviceTypeLOVListResult.setLocalizedSiebelLOVList(serviceTypeList);

		LocalizedSiebelLOVListResult requestStatusDetailLOVListResult = new LocalizedSiebelLOVListResult();
		requestStatusDetailLOVListResult.setLocalizedSiebelLOVList(requestStatusDetailList);

		LocalizedSiebelLOVListResult claimRequestStatusLOVListResult = new LocalizedSiebelLOVListResult();
		claimRequestStatusLOVListResult.setLocalizedSiebelLOVList(claimRequestStatusList);

		LocalizedSiebelLOVListResult serviceRequestStatusLOVListResult = new LocalizedSiebelLOVListResult();
		serviceRequestStatusLOVListResult.setLocalizedSiebelLOVList(serviceRequestStatusList);

		LocalizedSiebelLOVListResult claimRequestStatusDetailLOVListResult = new LocalizedSiebelLOVListResult();
		claimRequestStatusDetailLOVListResult.setLocalizedSiebelLOVList(claimRequestStatusDetailList);

		LocalizedSiebelLOVListResult serviceRequestStatusDetailLOVListResult = new LocalizedSiebelLOVListResult();
		serviceRequestStatusDetailLOVListResult.setLocalizedSiebelLOVList(serviceRequestStatusDetailList);

		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract) anyObject()))
				.andReturn(requestTypeLOVListResult);
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract) anyObject()))
				.andReturn(requestStatusLOVListResult);
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract) anyObject()))
				.andReturn(serviceTypeLOVListResult);
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract) anyObject()))
				.andReturn(requestStatusDetailLOVListResult);
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract) anyObject()))
				.andReturn(claimRequestStatusLOVListResult);
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract) anyObject()))
				.andReturn(serviceRequestStatusLOVListResult);
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract) anyObject()))
				.andReturn(claimRequestStatusDetailLOVListResult);
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract) anyObject()))
				.andReturn(serviceRequestStatusDetailLOVListResult);

		replayAll();

		ExtendedModelMap model = new ExtendedModelMap();
		requestController.showRequestList(request, new MockRenderResponse(), model);
		RequestListForm requestListForm = (RequestListForm) model.get("requestListForm");
		assertTrue(requestListForm.isCreateClaimFlag());
		assertNotNull(requestListForm.getServiceRequestStatusDetailMap());
		assertNotNull(requestListForm.getServiceRequestStatusMap());
		assertNotNull(requestListForm.getClaimRequestStatusDetailMap());
		assertNotNull(requestListForm.getClaimRequestStatusMap());

		verifyAll();
	}

	@Test
	public void testGetRequestListSiebeiException() throws Exception {
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter("queryType", QueryType.My.toString());
		request.setParameter("requestType", "Service Request");
		request.setParameter("status", "All");
		request.setParameter("timezoneOffset", "8");

		MockPortletSession session = new MockPortletSession();
		PortalSessionUtil.setDirectPartnerFlag(session, true);

		LdapUserDataResult result = new LdapUserDataResult();
		result.setUserRoles(Arrays.asList(LexmarkConstants.ROLE_SERVICE_MANAGER,
				LexmarkConstants.ROLE_SERVICE_TECHNICIAN));
		result.setUserSegment("employee");
		PortalSessionUtil.setLdapUserData(session, result);

		session.setAttribute(LexmarkConstants.SERVICES_USER_PHASE2, new ServicesUser(),
				PortletSession.APPLICATION_SCOPE);
		request.setSession(session);

		expect(globalService.initCrmSessionHandle((CrmSessionHandle) anyObject())).andReturn(new CrmSessionHandle() {
		}).once();

		expect(partnerRequestsService.retrieveActivityList((ActivityListContract) anyObject())).andThrow(
				new Exception());

		globalService.releaseSessionHandle((CrmSessionHandle) anyObject());
		EasyMock.expectLastCall().once();
		LocalizedSiebelLOVListResult lovListResult = new LocalizedSiebelLOVListResult();
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract) anyObject()))
				.andReturn(lovListResult).anyTimes();

		// Mock value for PortalUtil#getHttpServletRequest
		final Portal portal = createMock(Portal.class);
		MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
		expect(portal.getHttpServletRequest((PortletRequest) anyObject())).andReturn(httpServletRequest).anyTimes();
		new PortalUtil().setPortal(portal);

		replayAll();

		final Model model = new ExtendedModelMap();
		MockResourceResponse response = new MockResourceResponse();
		requestController.getRequestList(request, response, model);
		assertTrue(response.getContentAsString().indexOf("General Error") != -1);

		verifyAll();
	}

	@Test
	public void testGetRequestListEmptyActivityList() throws Exception {
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter("queryType", QueryType.Unassigned.toString());
		request.setParameter("requestType", "Claim Request");
		request.setParameter("status", "All");
		request.setParameter("timezoneOffset", "8");

		MockPortletSession session = new MockPortletSession();
		PortalSessionUtil.setDirectPartnerFlag(session, true);

		LdapUserDataResult result = new LdapUserDataResult();
		result.setUserRoles(Arrays.asList(LexmarkConstants.ROLE_SERVICE_MANAGER,
				LexmarkConstants.ROLE_SERVICE_TECHNICIAN));
		result.setUserSegment("employee");
		PortalSessionUtil.setLdapUserData(session, result);

		LocalizedSiebelLOVListResult lovListResult = new LocalizedSiebelLOVListResult();
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract) anyObject()))
				.andReturn(lovListResult).anyTimes();

		replayAll();

		session.setAttribute(LexmarkConstants.SERVICES_USER_PHASE2, new ServicesUser(),
				PortletSession.APPLICATION_SCOPE);
		request.setSession(session);

		final Model model = new ExtendedModelMap();
		MockResourceResponse response = new MockResourceResponse();
		requestController.getRequestList(request, response, model);

		assertTrue(((List) model.asMap().get("activityList")).isEmpty());

		verifyAll();
	}

	@Test
	public void testGetRequestListHasActivityList() throws Exception {
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter("queryType", QueryType.My.toString());
		request.setParameter("requestType", "Claim Request");
		request.setParameter("status", "All");
		request.setParameter("timezoneOffset", "8");

		MockPortletSession session = new MockPortletSession();
		PortalSessionUtil.setDirectPartnerFlag(session, true);

		LdapUserDataResult result = new LdapUserDataResult();
		result.setUserRoles(Arrays.asList(LexmarkConstants.ROLE_SERVICE_MANAGER,
				LexmarkConstants.ROLE_SERVICE_TECHNICIAN));
		result.setUserSegment("employee");
		PortalSessionUtil.setLdapUserData(session, result);

		LocalizedSiebelLOVListResult lovListResult = new LocalizedSiebelLOVListResult();
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract) anyObject()))
				.andReturn(lovListResult).anyTimes();

		expect(globalService.initCrmSessionHandle((CrmSessionHandle) anyObject())).andReturn(new CrmSessionHandle() {
		}).once();

		ActivityListResult activityListResult = new ActivityListResult();
		activityListResult.setActivityList(Arrays.asList(new Activity(), new Activity(), new Activity()));
		expect(partnerRequestsService.retrieveActivityList((ActivityListContract) anyObject())).andReturn(
				activityListResult).once();

		globalService.releaseSessionHandle((CrmSessionHandle) anyObject());
		EasyMock.expectLastCall().once();

		replayAll();

		session.setAttribute(LexmarkConstants.SERVICES_USER_PHASE2, new ServicesUser(),
				PortletSession.APPLICATION_SCOPE);
		request.setSession(session);

		final Model model = new ExtendedModelMap();
		MockResourceResponse response = new MockResourceResponse();
		requestController.getRequestList(request, response, model);

		assertEquals(((List) model.asMap().get("activityList")).size(), 3);

		verifyAll();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRetrieveClaimDetailNullClaimDetailResult() throws Exception {
		final Model model = new ExtendedModelMap();
		requestController.retrieveClaimDetail(model, new MockRenderRequest(), new MockRenderResponse());
		verifyAll();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRetrieveClaimDetailNullActivity() throws Exception {
		final Model model = new ExtendedModelMap();

		expect(partnerRequestsService.retrieveClaimDetail((ClaimDetailContract) anyObject())).andReturn(
				new ClaimDetailResult());

		replayAll();

		requestController.retrieveClaimDetail(model, new MockRenderRequest(), new MockRenderResponse());

		verifyAll();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRetrieveClaimDetailNullServiceRequest() throws Exception {
		final Model model = new ExtendedModelMap();

		ClaimDetailResult claimDetailResult = new ClaimDetailResult();
		claimDetailResult.setActivity(new Activity());
		expect(partnerRequestsService.retrieveClaimDetail((ClaimDetailContract) anyObject())).andReturn(
				claimDetailResult);

		replayAll();

		requestController.retrieveClaimDetail(model, new MockRenderRequest(), new MockRenderResponse());

		verifyAll();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRetrieveClaimDetailNullActivityStatus() throws Exception {
		final Model model = new ExtendedModelMap();

		ClaimDetailResult claimDetailResult = new ClaimDetailResult();
		Activity activity = new Activity();
		ServiceRequest serviceRequest = new ServiceRequest();
		serviceRequest.setAsset(new Asset());
		activity.setServiceRequest(serviceRequest);
		claimDetailResult.setActivity(activity);

		expect(partnerRequestsService.retrieveClaimDetail((ClaimDetailContract) anyObject())).andReturn(
				claimDetailResult);
		expect(productImageService.retrieveProductImageUrl((ProductImageContract) anyObject(), (String) anyObject()))
				.andReturn(new ProductImageResult());

		replayAll();

		requestController.retrieveClaimDetail(model, new MockRenderRequest(), new MockRenderResponse());

		verifyAll();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRetrieveClaimDetailNullActivitySubStatus() throws Exception {
		final Model model = new ExtendedModelMap();

		ClaimDetailResult claimDetailResult = new ClaimDetailResult();
		Activity activity = new Activity();
		ServiceRequest serviceRequest = new ServiceRequest();
		serviceRequest.setAsset(new Asset());
		activity.setServiceRequest(serviceRequest);
		activity.setActivityStatus(requestStatusList.get(0));
		claimDetailResult.setActivity(activity);

		expect(partnerRequestsService.retrieveClaimDetail((ClaimDetailContract) anyObject())).andReturn(
				claimDetailResult);
		expect(productImageService.retrieveProductImageUrl((ProductImageContract) anyObject(), (String) anyObject()))
				.andReturn(new ProductImageResult());
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelValue((LocalizedSiebelValueContract) anyObject()))
				.andReturn(new LocalizedSiebelValueResult()).anyTimes();

		replayAll();

		requestController.retrieveClaimDetail(model, new MockRenderRequest(), new MockRenderResponse());

		verifyAll();
	}

	@Test
	public void testRetrieveClaimDetailSuccess() throws Exception {
		final ExtendedModelMap model = new ExtendedModelMap();

		ClaimDetailResult claimDetailResult = new ClaimDetailResult();
		Activity activity = new Activity();
		ServiceRequest serviceRequest = new ServiceRequest();
		serviceRequest.setServiceRequestType(requestTypeList.get(0));
		serviceRequest.setAsset(new Asset());
		activity.setServiceRequest(serviceRequest);
		activity.setActivityStatus(requestStatusList.get(0));
		activity.setActivitySubStatus(requestStatusDetailList.get(0));
		claimDetailResult.setActivity(activity);

		expect(partnerRequestsService.retrieveClaimDetail((ClaimDetailContract) anyObject())).andReturn(
				claimDetailResult);
		expect(productImageService.retrieveProductImageUrl((ProductImageContract) anyObject(), (String) anyObject()))
				.andReturn(new ProductImageResult());
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelValue((LocalizedSiebelValueContract) anyObject()))
				.andReturn(new LocalizedSiebelValueResult()).anyTimes();

		replayAll();

		MockRenderRequest request = new MockRenderRequest();
		request.setParameter(LexmarkConstants.TIMEZONE_OFFSET, "8");
		requestController.retrieveClaimDetail(model, request, new MockRenderResponse());

		assertNotNull(model.get("claimDetailForm"));
		verifyAll();
	}

	@Test
	public void testDownloadRequestsURL() throws Exception {
		final ExtendedModelMap model = new ExtendedModelMap();

		expect(globalService.initCrmSessionHandle((CrmSessionHandle) anyObject())).andReturn(new CrmSessionHandle() {
		}).once();
		ActivityListResult activityListResult = new ActivityListResult();
		activityListResult.setActivityList(new ArrayList<Activity>());
		expect(partnerRequestsService.retrieveActivityList((ActivityListContract) anyObject())).andReturn(
				activityListResult).once();
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelValue((LocalizedSiebelValueContract) anyObject()))
				.andReturn(new LocalizedSiebelValueResult()).anyTimes();
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract) anyObject()))
				.andReturn(new LocalizedSiebelLOVListResult()).anyTimes();
		globalService.releaseSessionHandle((CrmSessionHandle) anyObject());
		EasyMock.expectLastCall().once();

		// Mock value for PortalUtil#getHttpServletRequest
		final Portal portal = createMock(Portal.class);
		MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
		expect(portal.getHttpServletRequest((PortletRequest) anyObject())).andReturn(httpServletRequest).anyTimes();
		new PortalUtil().setPortal(portal);

		replayAll();

		MockResourceRequest request = new MockResourceRequest();
		request.setParameter("downloadType", "CSV");
		request.setParameter(LexmarkConstants.TIMEZONE_OFFSET, "8");
		PortletSession session = request.getPortletSession();
		session.setAttribute(LexmarkPPConstants.SESSION_KEY_REQUESTS_DOWNLOAD_CONTRACT, new ActivityListContract());

		MockResourceResponse response = new MockResourceResponse();
		requestController.downloadRequestsURL(request, response, model);

		verifyAll();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testShowServiceHistoryPageNullServiceRequest() throws Exception {
		final ExtendedModelMap model = new ExtendedModelMap();
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract) anyObject()))
				.andReturn(new LocalizedSiebelLOVListResult()).anyTimes();

		ServiceActivityHistoryDetailResult result = new ServiceActivityHistoryDetailResult();
		result.setActivity(new Activity());
		expect(
				partnerRequestsService
						.retrieveServiceActivityHistoryDetail((ServiceActivityHistoryDetailContract) anyObject()))
				.andReturn(result);

		expect(globalService.retrieveSiebelLOVList((SiebelLOVListContract) anyObject())).andReturn(
				new SiebelLOVListResult()).anyTimes();

		replayAll();
		MockRenderRequest request = new MockRenderRequest();
		request.setParameter(LexmarkConstants.TIMEZONE_OFFSET, "1");
		requestController.showServiceHistoryPage("132465", "465749879", request, model);

		verifyAll();
	}

	@Test
	public void testShowServiceHistoryPage() throws Exception {
		final ExtendedModelMap model = new ExtendedModelMap();
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract) anyObject()))
				.andReturn(new LocalizedSiebelLOVListResult()).anyTimes();
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelValue((LocalizedSiebelValueContract) anyObject()))
				.andReturn(new LocalizedSiebelValueResult()).anyTimes();
		expect(productImageService.retrieveProductImageUrl((ProductImageContract) anyObject(), (String) anyObject()))
				.andReturn(new ProductImageResult());

		ServiceActivityHistoryDetailResult result = new ServiceActivityHistoryDetailResult();
		Activity activity = new Activity();
		ServiceRequest serviceRequest = new ServiceRequest();
		serviceRequest.setAsset(new Asset());
		activity.setServiceRequest(serviceRequest);
		activity.setActivityStatus(requestStatusList.get(0));
		result.setActivity(activity);
		expect(
				partnerRequestsService
						.retrieveServiceActivityHistoryDetail((ServiceActivityHistoryDetailContract) anyObject()))
				.andReturn(result);

		expect(globalService.retrieveSiebelLOVList((SiebelLOVListContract) anyObject())).andReturn(
				new SiebelLOVListResult()).anyTimes();

		replayAll();
		MockRenderRequest request = new MockRenderRequest();
		request.setParameter(LexmarkConstants.TIMEZONE_OFFSET, "1");
		requestController.showServiceHistoryPage("132465", "465749879", request, model);

		verifyAll();
	}

	@Test
	public void testGetServiceHistoryListXML() throws Exception {
		final ExtendedModelMap model = new ExtendedModelMap();
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract) anyObject()))
				.andReturn(new LocalizedSiebelLOVListResult()).anyTimes();
		expect(
				partnerRequestsService
						.retrieveServiceActivityHistoryList((ServiceActivityHistoryListContract) anyObject()))
				.andReturn(new ServiceActivityHistoryListResult()).once();

		replayAll();
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter(LexmarkConstants.TIMEZONE_OFFSET, "1");
		requestController.getServiceHistoryListXML(model, request, new MockResourceResponse(),
				"1465", "14");

		assertNotNull(model.get("activityList"));
		verifyAll();
	}

	@Test
	public void testShowEmailNotificationsURL() throws Exception {
		final ExtendedModelMap model = new ExtendedModelMap();

		expect(partnerRequestsService.retrievePartnerNotifications((PartnerNotificationsContract) anyObject()))
				.andReturn(new PartnerNotificationsResult());

		replayAll();

		requestController.showEmailNotificationsURL(model, new MockResourceRequest(), new MockResourceResponse());

		verifyAll();
	}

	@Test
	public void testDownClaimDetailPdfURL() throws Exception {
		final ExtendedModelMap model = new ExtendedModelMap();

		ClaimDetailResult claimDetailResult = new ClaimDetailResult();
		Activity activity = new Activity();
		Account account = new Account();
		account.setAccountName("damn name");
		activity.setActualFailureCode(new ListOfValues());
		activity.setCustomerAccount(account);
		ServiceRequest serviceRequest = new ServiceRequest();
		serviceRequest.setAsset(new Asset());
		activity.setServiceRequest(serviceRequest);
		activity.setActivityStatus(requestStatusList.get(0));
		claimDetailResult.setActivity(activity);
		expect(partnerRequestsService.retrieveClaimDetail((ClaimDetailContract) anyObject())).andReturn(
				claimDetailResult).once();
		expect(productImageService.retrieveProductImageUrl((ProductImageContract) anyObject(), (String) anyObject()))
				.andReturn(new ProductImageResult()).once();
		expect(partnerRequestsService.retrievePartnerNotifications((PartnerNotificationsContract) anyObject()))
				.andReturn(new PartnerNotificationsResult()).once();
		LocalizedSiebelLOVListResult requestTypeLOVListResult = new LocalizedSiebelLOVListResult();
		requestTypeLOVListResult.setLocalizedSiebelLOVList(requestTypeList);
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract) anyObject()))
				.andReturn(requestTypeLOVListResult).anyTimes();
		expect(
				partnerRequestsService
						.retrieveServiceActivityHistoryList((ServiceActivityHistoryListContract) anyObject()))
				.andReturn(new ServiceActivityHistoryListResult()).once();
		expect(serviceRequestLocaleService.retrieveLocalizedSiebelValue((LocalizedSiebelValueContract) anyObject()))
				.andReturn(new LocalizedSiebelValueResult()).anyTimes();

		replayAll();

		requestController.downClaimDetailPdfURL(model, new MockResourceRequest(), new MockResourceResponse());

		verifyAll();
	}

	@Test
	public void testDownloadClaimsRequestsViewDownloadRequest() throws Exception {
		final ExtendedModelMap model = new ExtendedModelMap();
		expect(globalService.initCrmSessionHandle((CrmSessionHandle) anyObject())).andReturn(new CrmSessionHandle() {
		}).once();
		expect(partnerRequestsService.retrieveDownloadRequestList((ActivityListContract) anyObject())).andReturn(
				new DownloadRequestListResult()).once();
		globalService.releaseSessionHandle((CrmSessionHandle) anyObject());
		EasyMock.expectLastCall().once();

		// Mock value for PortalUtil#getHttpServletRequest
		final Portal portal = createMock(Portal.class);
		MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
		expect(portal.getHttpServletRequest((PortletRequest) anyObject())).andReturn(httpServletRequest).anyTimes();
		new PortalUtil().setPortal(portal);

		replayAll();

		MockResourceRequest request = new MockResourceRequest();
		request.getPortletSession().setAttribute(LexmarkPPConstants.SESSION_KEY_REQUESTS_DOWNLOAD_CONTRACT,
				new ActivityListContract());
		request.setParameter(LexmarkConstants.TIMEZONE_OFFSET, "8");
		request.setParameter("reportType", "downloadRequest");
		requestController.downloadClaimsRequestsView(request, new MockResourceResponse(), model);

		verifyAll();
	}

	@Test
	public void testDownloadClaimsRequestsViewDownloadClaim() throws Exception {
		final ExtendedModelMap model = new ExtendedModelMap();
		expect(globalService.initCrmSessionHandle((CrmSessionHandle) anyObject())).andReturn(new CrmSessionHandle() {
		}).once();
		expect(partnerRequestsService.retrieveDownloadClaimList((ActivityListContract) anyObject())).andReturn(
				new DownloadClaimListResult()).once();
		globalService.releaseSessionHandle((CrmSessionHandle) anyObject());
		EasyMock.expectLastCall().once();

		// Mock value for PortalUtil#getHttpServletRequest
		final Portal portal = createMock(Portal.class);
		MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
		expect(portal.getHttpServletRequest((PortletRequest) anyObject())).andReturn(httpServletRequest).anyTimes();
		new PortalUtil().setPortal(portal);

		replayAll();

		MockResourceRequest request = new MockResourceRequest();
		request.getPortletSession().setAttribute(LexmarkPPConstants.SESSION_KEY_REQUESTS_DOWNLOAD_CONTRACT,
				new ActivityListContract());
		request.setParameter(LexmarkConstants.TIMEZONE_OFFSET, "8");
		request.setParameter("reportType", "downloadClaim");
		requestController.downloadClaimsRequestsView(request, new MockResourceResponse(), model);

		verifyAll();
	}
}
