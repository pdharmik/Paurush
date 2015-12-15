package com.lexmark.portlet;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;

import org.easymock.EasyMockSupport;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.portlet.MockActionRequest;
import org.springframework.mock.web.portlet.MockActionResponse;
import org.springframework.mock.web.portlet.MockPortletRequest;
import org.springframework.mock.web.portlet.MockRenderRequest;
import org.springframework.mock.web.portlet.MockRenderResponse;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.ActivityDetailContract;
import com.lexmark.contract.LocalizedSiebelLOVListContract;
import com.lexmark.contract.LocalizedSiebelValueContract;
import com.lexmark.contract.ProductImageContract;
import com.lexmark.contract.RequestUpdateContract;
import com.lexmark.contract.SiebelLOVListContract;
import com.lexmark.domain.Activity;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.form.ServiceRequestDetailForm;
import com.lexmark.result.ActivityDetailResult;
import com.lexmark.result.LocalizedSiebelLOVListResult;
import com.lexmark.result.LocalizedSiebelValueResult;
import com.lexmark.result.ProductImageResult;
import com.lexmark.result.RequestUpdateResult;
import com.lexmark.result.SiebelLOVListResult;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.PartnerRequestsService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.service.impl.mock.PartnerDomainMockDataGenerator;
import com.lexmark.util.TestUtil;
import com.lexmark.webservice.api.RequestService;

public class RequestUpdateControllerTest extends EasyMockSupport{
	private PartnerRequestsService partnerRequestsService = null;
	private GlobalService globalService;
	private ServiceRequestLocale serviceRequestLocaleService;
	private RequestService requestWebService;
	private ProductImageService productImageService;
	private RequestUpdateController controller;
	
	@Before
	public void setUp() throws Exception {
		partnerRequestsService = createMock(PartnerRequestsService.class);
		globalService = createMock(GlobalService.class);
		serviceRequestLocaleService = createMock(ServiceRequestLocale.class);
		productImageService = createMock(ProductImageService.class);
		requestWebService = createMock(RequestService.class);
		
		controller = new RequestUpdateController();
		
		TestUtil.setProperty(controller,"partnerRequestsService",partnerRequestsService);
		TestUtil.setProperty(controller,"globalService",globalService);
		TestUtil.setProperty(controller,"serviceRequestLocaleService",serviceRequestLocaleService);
		TestUtil.setProperty(controller,"productImageService",productImageService);
		TestUtil.setProperty(controller,"requestWebService",requestWebService);
	}
	
	
	
	@Test(expected=Exception.class)
	public void testShowUpdateRequestPageActivityDetailResultNull() throws Exception {
		resetAll();
		expect(partnerRequestsService.retrieveActivityDetail((ActivityDetailContract)anyObject())).andReturn(null);
		replayAll();
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		generateActivityIDAndServiceRequestId(request);
		controller.showUpdateRequestPage(request, response, model);
		verifyAll();
	}
	
	@Test(expected=Exception.class)
	public void testShowUpdateRequestPageActivityNull() throws Exception {
		resetAll();
		ActivityDetailResult result = new ActivityDetailResult();
		expect(partnerRequestsService.retrieveActivityDetail((ActivityDetailContract)anyObject())).andReturn(result);
		replayAll();
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		generateActivityIDAndServiceRequestId(request);
		controller.showUpdateRequestPage(request, response, model);
		verifyAll();
	}
	
	@Test(expected=Exception.class)
	public void testShowUpdateRequestPageServiceRequestNull() throws Exception {
		resetAll();
		ActivityDetailResult result = new ActivityDetailResult();
		Activity activity = new Activity();
		result.setActivity(activity);
		expect(partnerRequestsService.retrieveActivityDetail((ActivityDetailContract)anyObject())).andReturn(result);
		replayAll();
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		generateActivityIDAndServiceRequestId(request);
		controller.showUpdateRequestPage(request, response, model);
		verifyAll();
	}
	
	@Test(expected=Exception.class)
	public void testShowUpdateRequestPageAssetNull() throws Exception {
		resetAll();
		ActivityDetailResult result = new ActivityDetailResult();
		Activity activity = new Activity();
		result.setActivity(activity);
		ServiceRequest serviceRequest = new ServiceRequest();
		activity.setServiceRequest(serviceRequest);
		expect(partnerRequestsService.retrieveActivityDetail((ActivityDetailContract)anyObject())).andReturn(result);
		replayAll();
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		generateActivityIDAndServiceRequestId(request);
		controller.showUpdateRequestPage(request, response, model);
		verifyAll();
	}
	
	@Test
	public void testShowUpdateRequestPageA() throws Exception {
		resetAll();
		ActivityDetailResult result = new ActivityDetailResult();
		Activity activity = getDefalutActivity(true);
		result.setActivity(activity);
		expect(partnerRequestsService.retrieveActivityDetail((ActivityDetailContract)anyObject())).andReturn(result);
		mockRetrieveSiebelLOVList(globalService);
		mockRetrieveProductImageUrl(productImageService);
		mockRetrieveLocalizedSiebelValue(serviceRequestLocaleService);
		mockRetrieveLocalizedSiebelLOVList(serviceRequestLocaleService);
		replayAll();
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		generateActivityIDAndServiceRequestId(request);
		TestUtil.initSession(request.getPortletSession());
		controller.showUpdateRequestPage(request, response, model);
		verifyAll();
		ServiceRequestDetailForm serviceRequestDetailForm = (ServiceRequestDetailForm)model.asMap().get("serviceRequestDetailForm");
		Assert.assertNotNull(serviceRequestDetailForm);
		Assert.assertNotNull(serviceRequestDetailForm.getRecommendedPartsXML());
		Assert.assertNotNull(serviceRequestDetailForm.getTechnicianInformationListXML());
	}
	
	@Test
	public void testShowUpdateRequestPageB() throws Exception {
		resetAll();
		ActivityDetailResult result = new ActivityDetailResult();
		Activity activity = getDefalutActivity(false);
		result.setActivity(activity);
		expect(partnerRequestsService.retrieveActivityDetail((ActivityDetailContract)anyObject())).andReturn(result);
		mockRetrieveSiebelLOVList(globalService);
		mockRetrieveProductImageUrl(productImageService);
		mockRetrieveLocalizedSiebelValue(serviceRequestLocaleService);
		mockRetrieveLocalizedSiebelLOVList(serviceRequestLocaleService);
		replayAll();
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		generateActivityIDAndServiceRequestId(request);
		TestUtil.initSession(request.getPortletSession());
		controller.showUpdateRequestPage(request, response, model);
		verifyAll();
		ServiceRequestDetailForm serviceRequestDetailForm = (ServiceRequestDetailForm)model.asMap().get("serviceRequestDetailForm");
		Assert.assertNotNull(serviceRequestDetailForm);
	}
	
	@Test
	public void testShowUpdateNotePopupA() throws Exception {
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		request.setParameter("noteDetail","noteDetail");
		request.setParameter("rowId","rowId");
		request.setParameter("handleGridFlag","update");
		TestUtil.initSession(request.getPortletSession());
		String returnStr = controller.showUpdateNotePopup(model, request, response);
		Assert.assertTrue("claims/updateNote".equals(returnStr));
		Assert.assertTrue("noteDetail".equals(model.asMap().get("noteDetail")));
		Assert.assertTrue("rowId".equals(model.asMap().get("rowId")));
		Assert.assertNotNull(model.asMap().get("noteDate"));
		Assert.assertNotNull(model.asMap().get("noteAuthor"));
	}
	
	@Test
	public void testUpdateRequestDetailDuplicatedSubmitA() throws Exception {
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		ServiceRequestDetailForm form = createServiceRequestDetailForm();
		form.setRequestFromPage("requestsListView");
		controller.updateRequestDetail(model, request, response, form);
		Assert.assertTrue("".equals(response.getRenderParameter("action")));
	}
	
	@Test
	public void testUpdateRequestDetailDuplicatedSubmitB() throws Exception {
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		ServiceRequestDetailForm form = createServiceRequestDetailForm();
		form.setRequestFromPage("requestsDetailView");
		controller.updateRequestDetail(model, request, response, form);
		Assert.assertTrue("showRequestDetailPage".equals(response.getRenderParameter("action")));
	}
	
	@Test
	public void testUpdateRequestDetailA() throws Exception{
		resetAll();
		RequestUpdateResult result = new RequestUpdateResult();
		result.setSuccess(true);
		expect(requestWebService.updateRequest((RequestUpdateContract)anyObject())).andReturn(result);
		replayAll();
		
		MockActionRequest request = new MockActionRequest();
		generateActivityIDAndServiceRequestId(request);
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		TestUtil.initSession(request.getPortletSession());
		ServiceRequestDetailForm form = createServiceRequestDetailForm();
		form.setRequestFromPage("requestsDetailView");
		form.refreshSubmitToken(request);
		form.setTechnicianFullName("firstName,LastName/id");
		form.getActivity().setRepairCompleteFlag(true);
		
		controller.updateRequestDetail(model, request, response , form);
		
		verifyAll();
		Activity activity = form.getActivity();
		Assert.assertFalse(activity.getTechnician().getNewContactFlag());
		Assert.assertTrue(activity.getTechnician().getUpdateContactFlag());
		Assert.assertNotNull(model.asMap().get("serviceSuccessMessages"));
		Assert.assertTrue("showRequestDetailPage".equals(response.getRenderParameter("action")));
	}
	
	@Test
	public void testUpdateRequestDetailB() throws Exception{
		resetAll();
		RequestUpdateResult result = new RequestUpdateResult();
		result.setSuccess(true);
		expect(requestWebService.updateRequest((RequestUpdateContract)anyObject())).andReturn(result);
		replayAll();
		
		MockActionRequest request = new MockActionRequest();
		generateActivityIDAndServiceRequestId(request);
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		TestUtil.initSession(request.getPortletSession());
		ServiceRequestDetailForm form = createServiceRequestDetailForm();
		form.setRequestFromPage("requestsListView");
		form.refreshSubmitToken(request);
		form.setTechnicianFullName("other");
		form.getActivity().setRepairCompleteFlag(true);
		
		controller.updateRequestDetail(model, request, response , form);
		
		verifyAll();
		Activity activity = form.getActivity();
		Assert.assertTrue(activity.getTechnician().getNewContactFlag());
		Assert.assertFalse(activity.getTechnician().getUpdateContactFlag());
		Assert.assertNotNull(model.asMap().get("serviceSuccessMessages"));
		Assert.assertTrue("".equals(response.getRenderParameter("action")));
	}
	
	@Test
	public void testUpdateRequestDetailFailed() throws Exception{
		resetAll();
		RequestUpdateResult result = new RequestUpdateResult();
		result.setSuccess(false);
		expect(requestWebService.updateRequest((RequestUpdateContract)anyObject())).andReturn(result);
		replayAll();
		
		MockActionRequest request = new MockActionRequest();
		generateActivityIDAndServiceRequestId(request);
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		TestUtil.initSession(request.getPortletSession());
		ServiceRequestDetailForm form = createServiceRequestDetailForm();
		form.setRequestFromPage("requestsListView");
		form.refreshSubmitToken(request);
		form.setTechnicianFullName("other");
		form.getActivity().setRepairCompleteFlag(true);
		
		controller.updateRequestDetail(model, request, response , form);
		
		verifyAll();
		Assert.assertNotNull(model.asMap().get("serviceErrors"));
	}
	
	@Test
	public void testCancelRequestUpdateA() throws Exception {
		MockActionRequest request = new MockActionRequest();
		request.setParameter(LexmarkConstants.TIMEZONE_OFFSET,"10");
		generateActivityIDAndServiceRequestId(request);
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		ServiceRequestDetailForm form = createServiceRequestDetailForm();
		form.setRequestFromPage("requestsDetailView");
		controller.cancelRequestUpdate(model, request, response , form);
		Assert.assertTrue("showRequestDetailPage".equals(response.getRenderParameter("action")));
	}
	
	@Test
	public void testCancelRequestUpdateB() throws Exception {
		MockActionRequest request = new MockActionRequest();
		request.setParameter(LexmarkConstants.TIMEZONE_OFFSET,"10");
		generateActivityIDAndServiceRequestId(request);
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		ServiceRequestDetailForm form = createServiceRequestDetailForm();
		form.setRequestFromPage("requestsListView");
		controller.cancelRequestUpdate(model, request, response , form);
		Assert.assertTrue("".equals(response.getRenderParameter("action")));
	}
	
	private void mockRetrieveSiebelLOVList(GlobalService globalService)throws Exception{
		SiebelLOVListResult listResult = new SiebelLOVListResult();
		expect(globalService.retrieveSiebelLOVList((SiebelLOVListContract) anyObject())).andReturn(listResult).anyTimes();
	}
	
	private void mockRetrieveProductImageUrl(ProductImageService productImageService)throws Exception{
		ProductImageResult productImageResult = new ProductImageResult();
		String productImageUrl = "https://tportal.lexmark.com/LexmarkServicesPortal/images/printer_na_color.png";
		productImageResult.setProductImageUrl(productImageUrl);
		expect(productImageService.retrieveProductImageUrl ((ProductImageContract) anyObject())).andReturn(productImageResult).anyTimes();
		expect(productImageService.retrieveProductImageUrl ((ProductImageContract) anyObject(),(String)anyObject())).andReturn(productImageResult).anyTimes();
	}
	
	private void mockRetrieveLocalizedSiebelValue(ServiceRequestLocale serviceRequestLocale)throws Exception{
		LocalizedSiebelValueResult localizedSiebelValueResult = new LocalizedSiebelValueResult();
		expect(serviceRequestLocale.retrieveLocalizedSiebelValue((LocalizedSiebelValueContract) anyObject())).andReturn(localizedSiebelValueResult).anyTimes();
	}
	
	private void mockRetrieveLocalizedSiebelLOVList(ServiceRequestLocale serviceRequestLocale)throws Exception{
		LocalizedSiebelLOVListResult localizedSiebelLOVListResult = new LocalizedSiebelLOVListResult();
		expect(serviceRequestLocale.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract) anyObject())).andReturn(localizedSiebelLOVListResult).anyTimes();
	}
	
	private Activity getDefalutActivity(boolean flag){
		Activity activity = PartnerDomainMockDataGenerator.getActivity(0);
		if(flag){
			activity.setOrderPartList(PartnerDomainMockDataGenerator.getPartLineItemListWithoutRandom());
			activity.setPendingPartList(PartnerDomainMockDataGenerator.getPartLineItemListWithoutRandom());
			activity.setReturnPartList(PartnerDomainMockDataGenerator.getPartLineItemListWithoutRandom());
			activity.setRecommendedPartList(PartnerDomainMockDataGenerator.getRecommendedPartLineItemListForTesting());
			activity.setAdditionalPaymentRequestList(PartnerDomainMockDataGenerator.getAddtionalPaymentRequestListForTesting());
			activity.setServiceInstructionList(PartnerDomainMockDataGenerator.getServiceInstructionListForTesting());
			activity.setDebrief(PartnerDomainMockDataGenerator.getDebriefForTesting());
			activity.setServiceInstructionList(PartnerDomainMockDataGenerator.getServiceInstructionListForTesting());
		}else{
			activity.setOrderPartList(null);
			activity.setPendingPartList(null);
			activity.setReturnPartList(null);
			activity.setRecommendedPartList(null);
			activity.setAdditionalPaymentRequestList(null);
			activity.setServiceInstructionList(null);
			activity.setServiceInstructionList(null);
		}
		return activity;
	}
	
	private void generateActivityIDAndServiceRequestId(MockPortletRequest request){
		Activity activity = PartnerDomainMockDataGenerator.getActivity(1);
		request.setParameter("activityId", activity.getActivityId());
		request.setParameter("serviceRequestId", activity.getServiceRequest().getId());
		request.setParameter(LexmarkConstants.TIMEZONE_OFFSET, "1");
	}
	
	private ServiceRequestDetailForm createServiceRequestDetailForm(){
		ServiceRequestDetailForm form = new ServiceRequestDetailForm();
		form.setActivity(PartnerDomainMockDataGenerator.getActivity(1));
		form.setTimezoneOffset(-8);
		form.setCustomerRequestedResponseStr("10/11/2011 10:10");
		form.setEstimatedTimeofArrivalStr("10/11/2011 10:10");
		form.setStatusAsOfStr("10/11/2011 10:10");
		return form;
	}
	
}
