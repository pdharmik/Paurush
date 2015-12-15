package com.lexmark.portlet;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;

import javax.portlet.PortletSession;

import org.easymock.EasyMockSupport;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.portlet.MockPortletRequest;
import org.springframework.mock.web.portlet.MockRenderRequest;
import org.springframework.mock.web.portlet.MockRenderResponse;
import org.springframework.mock.web.portlet.MockResourceRequest;
import org.springframework.mock.web.portlet.MockResourceResponse;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.ActivityDetailContract;
import com.lexmark.contract.LocalizedSiebelLOVListContract;
import com.lexmark.contract.LocalizedSiebelValueContract;
import com.lexmark.contract.PartnerNotificationsContract;
import com.lexmark.contract.ProductImageContract;
import com.lexmark.contract.ServiceActivityHistoryListContract;
import com.lexmark.domain.Activity;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.form.ServiceRequestDetailForm;
import com.lexmark.result.ActivityDetailResult;
import com.lexmark.result.LocalizedSiebelLOVListResult;
import com.lexmark.result.LocalizedSiebelValueResult;
import com.lexmark.result.PartnerNotificationsResult;
import com.lexmark.result.ProductImageResult;
import com.lexmark.result.ServiceActivityHistoryListResult;
import com.lexmark.service.api.PartnerRequestsService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.service.impl.mock.PartnerDomainMockDataGenerator;
import com.lexmark.util.PortalSessionUtil;
import com.lexmark.util.TestUtil;

public class ServiceRequestControllerTest extends EasyMockSupport{
	private ServiceRequestController controller;
	private PartnerRequestsService partnerRequestsService;
	private ServiceRequestLocale serviceRequestLocaleService;
	private ProductImageService productImageService;
	
	@Before
	public void setUp() throws Exception {
		partnerRequestsService = createMock(PartnerRequestsService.class);
		serviceRequestLocaleService = createMock(ServiceRequestLocale.class);
		productImageService = createMock(ProductImageService.class);
		
		controller = new ServiceRequestController();
		
		TestUtil.setProperty(controller,"partnerRequestsService",partnerRequestsService);
		TestUtil.setProperty(controller,"serviceRequestLocaleService",serviceRequestLocaleService);
		TestUtil.setProperty(controller,"productImageService",productImageService);
	}
	
	@Test(expected=Exception.class)
	public void testshowRequestDetailPageActivityDetailResultNull() throws Exception {
		resetAll();
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		generateActivityIDAndServiceRequestId(request);
		expect(partnerRequestsService.retrieveActivityDetail((ActivityDetailContract)anyObject())).andReturn(null);
		replayAll();
		controller.showRequestDetailPage(model, request, response);
		verifyAll();
	}
	
	@Test(expected=Exception.class)
	public void testshowRequestDetailPageActivityNull() throws Exception {
		resetAll();
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		generateActivityIDAndServiceRequestId(request);
		ActivityDetailResult result = new ActivityDetailResult();
		expect(partnerRequestsService.retrieveActivityDetail((ActivityDetailContract)anyObject())).andReturn(result);
		replayAll();
		controller.showRequestDetailPage(model, request, response);
		verifyAll();
	}
	
	@Test(expected=Exception.class)
	public void testshowRequestDetailPageServiceRequestNull() throws Exception {
		resetAll();
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		generateActivityIDAndServiceRequestId(request);
		ActivityDetailResult result = new ActivityDetailResult();
		Activity activity = new Activity();
		result.setActivity(activity);
		expect(partnerRequestsService.retrieveActivityDetail((ActivityDetailContract)anyObject())).andReturn(result);
		replayAll();
		controller.showRequestDetailPage(model, request, response);
		verifyAll();
	}
	
	@Test(expected=Exception.class)
	public void testshowRequestDetailPageAssetNull() throws Exception {
		resetAll();
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		generateActivityIDAndServiceRequestId(request);
		ActivityDetailResult result = new ActivityDetailResult();
		Activity activity = new Activity();
		result.setActivity(activity);
		ServiceRequest serviceRequest = new ServiceRequest();
		activity.setServiceRequest(serviceRequest);
		expect(partnerRequestsService.retrieveActivityDetail((ActivityDetailContract)anyObject())).andReturn(result);
		replayAll();
		controller.showRequestDetailPage(model, request, response);
		verifyAll();
	}
	
	@Test(expected=Exception.class)
	public void testshowRequestDetailPageActivitySubNull() throws Exception {
		resetAll();
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		generateActivityIDAndServiceRequestId(request);
		ActivityDetailResult result = new ActivityDetailResult();
		Activity activity = PartnerDomainMockDataGenerator.getActivity(0);
		result.setActivity(activity);
		activity.setActivitySubStatus(null);
		expect(partnerRequestsService.retrieveActivityDetail((ActivityDetailContract)anyObject())).andReturn(result);
		mockRetrieveProductImageUrl(productImageService);
		mockRetrieveLocalizedSiebelValue(serviceRequestLocaleService);
		mockRetrieveLocalizedSiebelLOVList(serviceRequestLocaleService);
		replayAll();
		controller.showRequestDetailPage(model, request, response);
		verifyAll();
	}
	
	@Test
	public void testshowRequestDetailPageA() throws Exception {
		resetAll();
		MockRenderRequest request = new MockRenderRequest();
		String from = "paymentDetail";
		String paymentId = "paymentId";
		request.setParameter("from",from);
		request.setParameter("paymentId",paymentId);
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		generateActivityIDAndServiceRequestId(request);
		
		ActivityDetailResult result = new ActivityDetailResult();
		Activity activity = PartnerDomainMockDataGenerator.getActivity(0);
		activity.setOrderPartList(PartnerDomainMockDataGenerator.getPartLineItemListWithoutRandom());
		activity.setReturnPartList(PartnerDomainMockDataGenerator.getPartLineItemListWithoutRandom());
		activity.setRecommendedPartList(PartnerDomainMockDataGenerator.getRecommendedPartLineItemListForTesting());
		activity.setAdditionalPaymentRequestList(PartnerDomainMockDataGenerator.getAddtionalPaymentRequestListForTesting());
		activity.setServiceInstructionList(PartnerDomainMockDataGenerator.getServiceInstructionListForTesting());
		activity.setDebrief(PartnerDomainMockDataGenerator.getDebriefForTesting());
		
		result.setActivity(activity);
		
		expect(partnerRequestsService.retrieveActivityDetail((ActivityDetailContract)anyObject())).andReturn(result);
		mockRetrieveProductImageUrl(productImageService);
		mockRetrieveLocalizedSiebelValue(serviceRequestLocaleService);
		mockRetrieveLocalizedSiebelLOVList(serviceRequestLocaleService);
		replayAll();
		controller.showRequestDetailPage(model, request, response);
		ServiceRequestDetailForm form = (ServiceRequestDetailForm)model.asMap().get("serviceRequestDetailForm");
		String returnURL = (String)model.asMap().get("returnURL");
		assetStringExist(returnURL,paymentId);
		Assert.assertNotNull(form.getAdditionalPaymentRequestsXML());
		verifyAll();
	}
	
	@Test
	public void testshowRequestDetailPageB() throws Exception {
		resetAll();
		MockRenderRequest request = new MockRenderRequest();
		String from = "paymentDetail";
		request.setParameter("from",from);
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		generateActivityIDAndServiceRequestId(request);
		
		ActivityDetailResult result = new ActivityDetailResult();
		Activity activity = PartnerDomainMockDataGenerator.getActivity(0);
		activity.setOrderPartList(null);
		activity.setReturnPartList(null);
		activity.setRecommendedPartList(null);
		activity.setAdditionalPaymentRequestList(null);
		activity.setServiceInstructionList(null);
		activity.setDebrief(null);
		
		result.setActivity(activity);
		
		expect(partnerRequestsService.retrieveActivityDetail((ActivityDetailContract)anyObject())).andReturn(result);
		mockRetrieveProductImageUrl(productImageService);
		mockRetrieveLocalizedSiebelValue(serviceRequestLocaleService);
		mockRetrieveLocalizedSiebelLOVList(serviceRequestLocaleService);
		replayAll();
		controller.showRequestDetailPage(model, request, response);
		ServiceRequestDetailForm form = (ServiceRequestDetailForm)model.asMap().get("serviceRequestDetailForm");
		Assert.assertNotNull(form);
		verifyAll();
	}
	
	@Test
	public void testShowPrintServiceRequestDetailPage(){
		String result = controller.showPrintServiceRequestDetailPage();
		Assert.assertTrue("serviceRequest/requestsDetailPrintView".equals(result));
	}
	
	@Test
	public void testDownServiceRequestDetailPdfURL() throws Exception{
		resetAll();
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter(LexmarkConstants.TIMEZONE_OFFSET,"2");
		MockResourceResponse response = new MockResourceResponse();
		PortletSession session = request.getPortletSession();
		PortalSessionUtil.setAllowAdditionalPaymentRequestFlag(session, true);
		Model model = new ExtendedModelMap();
		ActivityDetailResult result = new ActivityDetailResult();
		Activity activity = PartnerDomainMockDataGenerator.getActivity(0);
		activity.setOrderPartList(PartnerDomainMockDataGenerator.getPartLineItemListWithoutRandom());
		activity.setReturnPartList(PartnerDomainMockDataGenerator.getPartLineItemListWithoutRandom());
		activity.setRecommendedPartList(PartnerDomainMockDataGenerator.getRecommendedPartLineItemListForTesting());
		activity.setAdditionalPaymentRequestList(PartnerDomainMockDataGenerator.getAddtionalPaymentRequestListForTesting());
		activity.setServiceInstructionList(PartnerDomainMockDataGenerator.getServiceInstructionListForTesting());
		activity.setDebrief(PartnerDomainMockDataGenerator.getDebriefForTesting());
		
		result.setActivity(activity);
		
		expect(partnerRequestsService.retrieveActivityDetail((ActivityDetailContract)anyObject())).andReturn(result);
		mockRetrievePartnerNotifications(partnerRequestsService);
		mockRetrieveServiceActivityHistoryList(partnerRequestsService);
		mockRetrieveProductImageUrl(productImageService);
		mockRetrieveLocalizedSiebelValue(serviceRequestLocaleService);
		mockRetrieveLocalizedSiebelLOVList(serviceRequestLocaleService);
		replayAll();
		controller.downServiceRequestDetailPdfURL(model, request, response);
		verifyAll();
	}
	
	private void generateActivityIDAndServiceRequestId(MockPortletRequest request){
		Activity activity = PartnerDomainMockDataGenerator.getActivity(1);
		request.setParameter("activityId", activity.getActivityId());
		request.setParameter("serviceRequestId", activity.getServiceRequest().getId());
		request.setParameter(LexmarkConstants.TIMEZONE_OFFSET, "1");
	}
	private void mockRetrieveProductImageUrl(ProductImageService productImageService)throws Exception{
		ProductImageResult productImageResult = new ProductImageResult();
		String productImageUrl = "https://tportal.lexmark.com/LexmarkServicesPortal/images/printer_na_color.png";
		productImageResult.setProductImageUrl(productImageUrl);
		expect(productImageService.retrieveProductImageUrl ((ProductImageContract) anyObject())).andReturn(productImageResult).anyTimes();
		expect(productImageService.retrieveProductImageUrl ((ProductImageContract) anyObject(),(String)anyObject())).andReturn(productImageResult).anyTimes();
	}
	private void mockRetrievePartnerNotifications(PartnerRequestsService partnerRequestsService) throws Exception{
		PartnerNotificationsResult partnerNotificationsResult = new PartnerNotificationsResult();
		expect(partnerRequestsService.retrievePartnerNotifications((PartnerNotificationsContract) anyObject())).andReturn(partnerNotificationsResult).anyTimes();
	}
	private void mockRetrieveServiceActivityHistoryList(PartnerRequestsService partnerRequestsService) throws Exception{
		ServiceActivityHistoryListResult serviceActivityHistoryListResult = new ServiceActivityHistoryListResult();
		expect(partnerRequestsService.retrieveServiceActivityHistoryList((ServiceActivityHistoryListContract) anyObject())).andReturn(serviceActivityHistoryListResult).anyTimes();
	}
	private void mockRetrieveLocalizedSiebelValue(ServiceRequestLocale serviceRequestLocale)throws Exception{
		LocalizedSiebelValueResult localizedSiebelValueResult = new LocalizedSiebelValueResult();
		expect(serviceRequestLocale.retrieveLocalizedSiebelValue((LocalizedSiebelValueContract) anyObject())).andReturn(localizedSiebelValueResult).anyTimes();
	}
	private void mockRetrieveLocalizedSiebelLOVList(ServiceRequestLocale serviceRequestLocale)throws Exception{
		LocalizedSiebelLOVListResult localizedSiebelLOVListResult = new LocalizedSiebelLOVListResult();
		expect(serviceRequestLocale.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract) anyObject())).andReturn(localizedSiebelLOVListResult).anyTimes();
	}
	
	private void assetStringExist(String result, String value) {
		Assert.assertTrue(result.indexOf(value)>0);
	}	
	
}

