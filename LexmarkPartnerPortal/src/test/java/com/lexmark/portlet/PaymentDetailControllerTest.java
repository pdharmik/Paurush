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
import com.lexmark.contract.PaymentDetailsContract;
import com.lexmark.contract.PaymentLineItemDetailsContract;
import com.lexmark.contract.UserGridSettingContract;
import com.lexmark.domain.Activity;
import com.lexmark.domain.Payment;
import com.lexmark.form.PaymentDetailForm;
import com.lexmark.result.PaymentDetailsResult;
import com.lexmark.result.PaymentLineItemDetailsResult;
import com.lexmark.result.UserGridSettingResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.PaymentsService;
import com.lexmark.service.api.UserGridSettingService;
import com.lexmark.service.impl.mock.PartnerDomainMockDataGenerator;
import com.lexmark.util.PortalSessionUtil;
import com.lexmark.util.TestUtil;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.PortalUtil;

public class PaymentDetailControllerTest extends EasyMockSupport{
	private PaymentDetailController controller;
	private PaymentsService paymentService;
	private GlobalService globalService;
	private GridSettingController gridSettingController;
	private UserGridSettingService userGridSettingService;
	private Portal portal;
	
	@Before
	public void setUp() throws Exception {
		paymentService = createMock(PaymentsService.class);
		globalService = createMock(GlobalService.class);
		userGridSettingService = createMock(UserGridSettingService.class);
		portal = createMock(Portal.class);
			
		controller = new PaymentDetailController();
		gridSettingController = new GridSettingController();
		
		TestUtil.setProperty(controller,"paymentService",paymentService);
		TestUtil.setProperty(controller,"globalService",globalService);
		TestUtil.setProperty(controller,"gridSettingController",gridSettingController);
		TestUtil.setProperty(gridSettingController,"userGridSettingService",userGridSettingService);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testShowPaymentDetailResultNull()throws Exception{
		resetAll();
		expect(paymentService.retrievePaymentDetails((PaymentDetailsContract)anyObject())).andReturn(null);
		replayAll();
		String paymentId = "paymentId";
		MockRenderRequest request = new MockRenderRequest();
		request.setParameter(LexmarkConstants.TIMEZONE_OFFSET, "1");
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		controller.showPaymentDetail(model, request, response, paymentId);
		verifyAll();
	}
	
	@Test
	public void testShowPaymentDetail()throws Exception{
		resetAll();
		Payment payment = PartnerDomainMockDataGenerator.getPaymentsDetail(0);
		PaymentDetailsResult result = new PaymentDetailsResult();
		result.setPayment(payment);
		expect(paymentService.retrievePaymentDetails((PaymentDetailsContract)anyObject())).andReturn(result);
		mockRetrieveUserGridSettings(userGridSettingService);
		replayAll();
		String paymentId = "paymentId";
		MockRenderRequest request = new MockRenderRequest();
		request.setParameter(LexmarkConstants.TIMEZONE_OFFSET, "1");
		MockRenderResponse response = new MockRenderResponse();
		PortletSession session = request.getPortletSession();
		PortalSessionUtil.setAllowAdditionalPaymentRequestFlag(session, true);
		Model model = new ExtendedModelMap();
		controller.showPaymentDetail(model, request, response, paymentId);
		
		PaymentDetailForm form = (PaymentDetailForm)model.asMap().get("paymentDetail");
		
		Assert.assertTrue(form.isAllowAdditionalPaymentRequest());
		Assert.assertTrue(payment.getPaymentId().equals(form.getPayment().getPaymentId()));
		verifyAll();
	}
	
	@Test
	public void testGetPaymentLineItemListException()throws Exception{
		resetAll();
		CrmSessionHandle handle = new CrmSessionHandle(){};
		expect(paymentService.retreivePaymentLineItemList((PaymentLineItemDetailsContract)anyObject())).andThrow(new Exception());
		mockInitAndReleaseCrmSessionHandle(handle);
		replayAll();
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter(LexmarkConstants.TIMEZONE_OFFSET, "1");
		PortalSessionUtil.setSiebelCrmSessionHandle(request,handle);
		MockResourceResponse response = new MockResourceResponse();
		Model model = new ExtendedModelMap();
		String returnResult = controller.getPaymentLineItemList(request, response, model);
		Assert.assertNull(returnResult);
		verifyAll();
	}
	
	@Test
	public void testGetPaymentLineItemList()throws Exception{
		resetAll();
		CrmSessionHandle handle = new CrmSessionHandle(){};
		PaymentLineItemDetailsResult result = new PaymentLineItemDetailsResult();
		result.setActivities(new ArrayList<Activity>());
		result.setTotalCount(10);
		expect(paymentService.retreivePaymentLineItemList((PaymentLineItemDetailsContract)anyObject())).andReturn(result);
		mockInitAndReleaseCrmSessionHandle(handle);
		replayAll();
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter(LexmarkConstants.TIMEZONE_OFFSET, "1");
		request.setParameter("paymentId","paymentId");
		request.setParameter(LexmarkConstants.TIMEZONE_OFFSET, "1");
		PortalSessionUtil.setSiebelCrmSessionHandle(request,handle);
		MockResourceResponse response = new MockResourceResponse();
		Model model = new ExtendedModelMap();
		String returnResult = controller.getPaymentLineItemList(request, response, model);
		Assert.assertNotNull(model.asMap().get("paymentLineItemList"));
		Assert.assertTrue("paymentId".equals(model.asMap().get("paymentId")));
		Assert.assertTrue("payment/xml/paymentLineItemListXML".equals(returnResult));
		verifyAll();
	}
	
	@Test
	public void testPrintPaymentDetails()throws Exception{
		String returnResult = controller.printPaymentDetails();
		Assert.assertTrue("payment/paymentDetailPrint".equals(returnResult));
	}
	
	@Test
	public void testDownPaymentDetailPdfURL()throws Exception{
		resetAll();
		Payment payment = PartnerDomainMockDataGenerator.getPaymentsDetail(0);
		PaymentDetailsResult result = new PaymentDetailsResult();
		result.setPayment(payment);
		expect(paymentService.retrievePaymentDetails((PaymentDetailsContract)anyObject())).andReturn(result);
		PaymentLineItemDetailsResult paymentLineItemDetailsResult = new PaymentLineItemDetailsResult();
		List<Activity> activities = new ArrayList<Activity>();
		activities.add(PartnerDomainMockDataGenerator.getActivity(0));
		activities.add(PartnerDomainMockDataGenerator.getActivity(1));
		paymentLineItemDetailsResult.setActivities(activities);
		expect(paymentService.retreivePaymentLineItemList((PaymentLineItemDetailsContract)anyObject())).andReturn(paymentLineItemDetailsResult);
		CrmSessionHandle handle = new CrmSessionHandle(){};
		mockInitAndReleaseCrmSessionHandle(handle);
		replayAll();
		String paymentId = "paymentId";
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter(LexmarkConstants.TIMEZONE_OFFSET, "1");
		request.setParameter("paymentId",paymentId);
		MockResourceResponse response = new MockResourceResponse();
		Model model = new ExtendedModelMap();
		controller.downPaymentDetailPdfURL(model, request, response);
		verifyAll();
	}
	
	private void mockRetrieveUserGridSettings(UserGridSettingService userGridSettingService)throws Exception{
		UserGridSettingResult userGridSettingResult = new UserGridSettingResult();
		expect(userGridSettingService.retrieveUserGridSettings((UserGridSettingContract)anyObject())).andReturn(userGridSettingResult).anyTimes();
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
