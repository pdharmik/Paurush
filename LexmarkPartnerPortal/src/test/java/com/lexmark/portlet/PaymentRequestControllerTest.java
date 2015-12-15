package com.lexmark.portlet;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

import org.easymock.EasyMockSupport;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.portlet.MockRenderRequest;
import org.springframework.mock.web.portlet.MockRenderResponse;
import org.springframework.mock.web.portlet.MockResourceRequest;
import org.springframework.mock.web.portlet.MockResourceResponse;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.LocalizedSiebelLOVListContract;
import com.lexmark.contract.LocalizedSiebelValueContract;
import com.lexmark.contract.PartnerAgreementListContract;
import com.lexmark.contract.PaymentListContract;
import com.lexmark.contract.PaymentRequestListContract;
import com.lexmark.contract.UserGridSettingContract;
import com.lexmark.domain.Activity;
import com.lexmark.domain.Payment;
import com.lexmark.domain.ServicesUser;
import com.lexmark.result.LocalizedSiebelLOVListResult;
import com.lexmark.result.LocalizedSiebelValueResult;
import com.lexmark.result.PartnerAgreementListResult;
import com.lexmark.result.PaymentListResult;
import com.lexmark.result.PaymentRequestListResult;
import com.lexmark.result.UserGridSettingResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.PaymentsService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.service.api.UserGridSettingService;
import com.lexmark.service.impl.mock.PartnerDomainMockDataGenerator;
import com.lexmark.util.PortalSessionUtil;
import com.lexmark.util.TestUtil;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.PortalUtil;

public class PaymentRequestControllerTest extends EasyMockSupport {
	private static final String KEY_PAYMENTS_DOWN_LOAD_CONTRACT = "paymentsDownLoadContract";
	private static final String KEY_PAYMENT_REQUEST_DOWN_LOAD_CONTRACT = "paymentRequestDownLoadContract";
	
	private PaymentRequestController controller;
	private GlobalService globalService;
	private ServiceRequestLocale serviceRequestLocaleService;
	private PaymentsService paymentsService;
	private GridSettingController gridSettingController;
	private UserGridSettingService userGridSettingService;
	private Portal portal;
	
	@Before
	public void setUp() throws Exception {
		paymentsService = createMock(PaymentsService.class);
		globalService = createMock(GlobalService.class);
		serviceRequestLocaleService = createMock(ServiceRequestLocale.class);
		userGridSettingService = createMock(UserGridSettingService.class);
		portal = createMock(Portal.class);
		
		gridSettingController = new GridSettingController();	
		controller = new PaymentRequestController();
		
		TestUtil.setProperty(gridSettingController,"userGridSettingService",userGridSettingService);
		TestUtil.setProperty(controller,"serviceRequestLocaleService",serviceRequestLocaleService);
		TestUtil.setProperty(controller,"paymentsService",paymentsService);
		TestUtil.setProperty(controller,"globalService",globalService);
		TestUtil.setProperty(controller,"gridSettingController",gridSettingController);
	}
	
	@Test
	public void testShowPaymentRequestList() throws Exception{
		resetAll();
		mockRetrieveLocalizedSiebelValue(serviceRequestLocaleService);
		mockRetrieveLocalizedSiebelLOVList(serviceRequestLocaleService);
		mockRetrieveUserGridSettings(userGridSettingService);
		PartnerAgreementListResult result = new PartnerAgreementListResult();
		result.setPartnerAgreementList(PartnerDomainMockDataGenerator.getPartnerAgreements());
		expect(globalService.retrievePartnerAgreementList((PartnerAgreementListContract)anyObject())).andReturn(result);
		replayAll();
		MockRenderRequest request = new MockRenderRequest();
		addMdmIdAndMdmLevelInSession(request.getPortletSession());
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		controller.showPaymentRequestList(request, response, model);
		Assert.assertNotNull(model.asMap().get("partnerAgreementsXML"));
		Assert.assertNotNull(model.asMap().get("paymentRequestStatusMap"));
		Assert.assertNotNull(model.asMap().get("paymentStatusMap"));
		verifyAll();
	}
	
	@Test
	public void testGetPaymentRequestList() throws Exception{
		resetAll();
		CrmSessionHandle handle = new CrmSessionHandle(){};
		mockInitAndReleaseCrmSessionHandle(handle);
		PaymentRequestListResult result = new PaymentRequestListResult();
		List<Activity> paymentRequestList = new ArrayList<Activity>();
		paymentRequestList.add(PartnerDomainMockDataGenerator.getActivity(0));
		paymentRequestList.add(PartnerDomainMockDataGenerator.getActivity(1));
		result.setPaymentRequestList(paymentRequestList);
		expect(paymentsService.retrievePaymentRequestList((PaymentRequestListContract)anyObject())).andReturn(result);
		mockRetrieveLocalizedSiebelValue(serviceRequestLocaleService);
		mockRetrieveLocalizedSiebelLOVList(serviceRequestLocaleService);
		replayAll();
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter("timezoneOffset","10");
		addMdmIdAndMdmLevelInSession(request.getPortletSession());
		MockResourceResponse response = new MockResourceResponse();
		Model model = new ExtendedModelMap();
		PortalSessionUtil.setSiebelCrmSessionHandle(request,handle);
		controller.getPaymentRequestList(request, response, model);
		Assert.assertNotNull(model.asMap().get("activityList"));
		verifyAll();
	}
	
	@Test
	public void testGetPaymentRequestListException() throws Exception{
		resetAll();
		CrmSessionHandle handle = new CrmSessionHandle(){};
		mockInitAndReleaseCrmSessionHandle(handle);
		expect(paymentsService.retrievePaymentRequestList((PaymentRequestListContract)anyObject())).andThrow(new Exception());
		mockRetrieveLocalizedSiebelValue(serviceRequestLocaleService);
		mockRetrieveLocalizedSiebelLOVList(serviceRequestLocaleService);
		replayAll();
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter("timezoneOffset","10");
		addMdmIdAndMdmLevelInSession(request.getPortletSession());
		MockResourceResponse response = new MockResourceResponse();
		Model model = new ExtendedModelMap();
		PortalSessionUtil.setSiebelCrmSessionHandle(request,handle);
		Assert.assertNull(controller.getPaymentRequestList(request, response, model));
		verifyAll();
	}
	
	@Test
	public void testShowPaymentList() throws Exception{
		resetAll();
		mockRetrieveLocalizedSiebelValue(serviceRequestLocaleService);
		mockRetrieveLocalizedSiebelLOVList(serviceRequestLocaleService);
		mockRetrieveUserGridSettings(userGridSettingService);
		PartnerAgreementListResult result = new PartnerAgreementListResult();
		result.setPartnerAgreementList(PartnerDomainMockDataGenerator.getPartnerAgreements());
		expect(globalService.retrievePartnerAgreementList((PartnerAgreementListContract)anyObject())).andReturn(result);
		replayAll();
		MockRenderRequest request = new MockRenderRequest();
		addMdmIdAndMdmLevelInSession(request.getPortletSession());
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		String returnStr = controller.showPaymentList(request, response, model);
		Assert.assertNotNull(model.asMap().get("partnerAgreementsXML"));
		Assert.assertTrue("payment/paymentListView".equals(returnStr));
		verifyAll();
	}
	
	@Test
	public void testGetPaymentList() throws Exception{
		resetAll();
		CrmSessionHandle handle = new CrmSessionHandle(){};
		mockInitAndReleaseCrmSessionHandle(handle);
		PaymentListResult result = new PaymentListResult();
		List<Payment> paymentList = new ArrayList<Payment>();
		paymentList.add(PartnerDomainMockDataGenerator.getPaymentsDetail(0));
		paymentList.add(PartnerDomainMockDataGenerator.getPaymentsDetail(1));
		result.setPaymentList(paymentList);
		expect(paymentsService.retreivePaymentList((PaymentListContract)anyObject())).andReturn(result);
		mockRetrieveLocalizedSiebelValue(serviceRequestLocaleService);
		mockRetrieveLocalizedSiebelLOVList(serviceRequestLocaleService);
		replayAll();
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter("timezoneOffset","10");
		addMdmIdAndMdmLevelInSession(request.getPortletSession());
		MockResourceResponse response = new MockResourceResponse();
		Model model = new ExtendedModelMap();
		PortalSessionUtil.setSiebelCrmSessionHandle(request,handle);
		controller.getPaymentList(request, response, model);
		Assert.assertNotNull(model.asMap().get("paymentList"));
		verifyAll();
	}
	
	@Test
	public void testGetPaymentListException() throws Exception{
		resetAll();
		CrmSessionHandle handle = new CrmSessionHandle(){};
		mockInitAndReleaseCrmSessionHandle(handle);
		expect(paymentsService.retreivePaymentList((PaymentListContract)anyObject())).andThrow(new Exception());
		mockRetrieveLocalizedSiebelValue(serviceRequestLocaleService);
		mockRetrieveLocalizedSiebelLOVList(serviceRequestLocaleService);
		replayAll();
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter("timezoneOffset","10");
		addMdmIdAndMdmLevelInSession(request.getPortletSession());
		MockResourceResponse response = new MockResourceResponse();
		Model model = new ExtendedModelMap();
		PortalSessionUtil.setSiebelCrmSessionHandle(request,handle);
		Assert.assertNull(controller.getPaymentList(request, response, model));
		verifyAll();
	}
	
	@Test
	public void testExportPaymentRequests() throws Exception{
		resetAll();
		CrmSessionHandle handle = new CrmSessionHandle(){};
		mockInitAndReleaseCrmSessionHandle(handle);
		PaymentRequestListResult result = new PaymentRequestListResult();
		List<Activity> paymentRequestList = new ArrayList<Activity>();
		paymentRequestList.add(PartnerDomainMockDataGenerator.getActivity(0));
		paymentRequestList.add(PartnerDomainMockDataGenerator.getActivity(1));
		result.setPaymentRequestList(paymentRequestList);
		expect(paymentsService.retrievePaymentRequestList((PaymentRequestListContract)anyObject())).andReturn(result);
		mockRetrieveLocalizedSiebelValue(serviceRequestLocaleService);
		mockRetrieveLocalizedSiebelLOVList(serviceRequestLocaleService);
		replayAll();
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter("timezoneOffset","10");
		request.setParameter("downloadType","PDF");
		PortletSession session = request.getPortletSession();
		PaymentRequestListContract contract = new PaymentRequestListContract();
		session.setAttribute(KEY_PAYMENT_REQUEST_DOWN_LOAD_CONTRACT,contract);
		MockResourceResponse response = new MockResourceResponse();
		Model model = new ExtendedModelMap();
		PortalSessionUtil.setSiebelCrmSessionHandle(request,handle);
		controller.exportPaymentRequests(request, response, model);
		verifyAll();
	}
	
	
	@Test
	public void testShowPaymentRequestPrintPage(){
		String returnStr = controller.showPaymentRequestPrintPage();
		Assert.assertTrue("payment/paymentRequestPrint".equals(returnStr));
	}
	
	@Test
	public void testExportPayments() throws Exception{
		resetAll();
		CrmSessionHandle handle = new CrmSessionHandle(){};
		mockInitAndReleaseCrmSessionHandle(handle);
		PaymentListResult result = new PaymentListResult();
		List<Payment> paymentList = new ArrayList<Payment>();
		paymentList.add(PartnerDomainMockDataGenerator.getPaymentsDetail(0));
		paymentList.add(PartnerDomainMockDataGenerator.getPaymentsDetail(1));
		result.setPaymentList(paymentList);
		expect(paymentsService.retreivePaymentList((PaymentListContract)anyObject())).andReturn(result);
		mockRetrieveLocalizedSiebelValue(serviceRequestLocaleService);
		mockRetrieveLocalizedSiebelLOVList(serviceRequestLocaleService);
		replayAll();
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter("timezoneOffset","10");
		request.setParameter("downloadType","PDF");
		PortletSession session = request.getPortletSession();
		PaymentListContract contract = new PaymentListContract();
		session.setAttribute(KEY_PAYMENTS_DOWN_LOAD_CONTRACT,contract);
		MockResourceResponse response = new MockResourceResponse();
		Model model = new ExtendedModelMap();
		PortalSessionUtil.setSiebelCrmSessionHandle(request,handle);
		controller.exportPayments(request, response, model);
		verifyAll();
	}
	
	@Test
	public void testPrintPayments(){
		String returnStr = controller.showPaymentsPrintPage();
		Assert.assertTrue("payment/paymentListPrint".equals(returnStr));
	}
	
	private void mockRetrieveLocalizedSiebelValue(ServiceRequestLocale serviceRequestLocale)throws Exception{
		LocalizedSiebelValueResult localizedSiebelValueResult = new LocalizedSiebelValueResult();
		expect(serviceRequestLocale.retrieveLocalizedSiebelValue((LocalizedSiebelValueContract) anyObject())).andReturn(localizedSiebelValueResult).anyTimes();
	}
	private void mockRetrieveLocalizedSiebelLOVList(ServiceRequestLocale serviceRequestLocale)throws Exception{
		LocalizedSiebelLOVListResult localizedSiebelLOVListResult = new LocalizedSiebelLOVListResult();
		expect(serviceRequestLocale.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract) anyObject())).andReturn(localizedSiebelLOVListResult).anyTimes();
	}
	private void mockRetrieveUserGridSettings(UserGridSettingService userGridSettingService)throws Exception{
		UserGridSettingResult userGridSettingResult = new UserGridSettingResult();
		expect(userGridSettingService.retrieveUserGridSettings((UserGridSettingContract)anyObject())).andReturn(userGridSettingResult).anyTimes();
	}
	private void addMdmIdAndMdmLevelInSession(PortletSession session){
		ServicesUser serviceUser = new ServicesUser();
		serviceUser.setMdmId("mdmId");
		serviceUser.setMdmLevel("mdmLevel");
		session.setAttribute(LexmarkConstants.SERVICES_USER_PHASE2,serviceUser, PortletSession.APPLICATION_SCOPE);
	}
	private void mockInitAndReleaseCrmSessionHandle(CrmSessionHandle handle){
		new PortalUtil().setPortal(portal);
		MockHttpServletRequest request = new MockHttpServletRequest();
		expect(portal.getHttpServletRequest((PortletRequest) anyObject())).andReturn(request).anyTimes();
		expect(globalService.initCrmSessionHandle((CrmSessionHandle) anyObject())).andReturn(handle).anyTimes();
		globalService.releaseSessionHandle((CrmSessionHandle) anyObject());
		expectLastCall().anyTimes();
	}
	
}
