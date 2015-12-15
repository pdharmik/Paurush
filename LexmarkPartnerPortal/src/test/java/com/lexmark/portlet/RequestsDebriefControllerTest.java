package com.lexmark.portlet;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.easymock.EasyMockSupport;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.portlet.MockActionRequest;
import org.springframework.mock.web.portlet.MockActionResponse;
import org.springframework.mock.web.portlet.MockPortletRequest;
import org.springframework.mock.web.portlet.MockRenderRequest;
import org.springframework.mock.web.portlet.MockRenderResponse;
import org.springframework.mock.web.portlet.MockResourceRequest;
import org.springframework.mock.web.portlet.MockResourceResponse;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.ActivityDebriefSubmitContract;
import com.lexmark.contract.ActivityDetailContract;
import com.lexmark.contract.LocalizedSiebelLOVListContract;
import com.lexmark.contract.LocalizedSiebelValueContract;
import com.lexmark.contract.ProductImageContract;
import com.lexmark.contract.SiebelLOVListContract;
import com.lexmark.contract.TechnicianListContract;
import com.lexmark.contract.ValidateInstalledPrinterSerialNumberContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Activity;
import com.lexmark.domain.Debrief;
import com.lexmark.domain.PartLineItem;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.enums.DebriefActionStatusEnum;
import com.lexmark.enums.ServiceTypeEnum;
import com.lexmark.form.RequestsDebriefForm;
import com.lexmark.result.ActivityDebriefSubmitResult;
import com.lexmark.result.ActivityDetailResult;
import com.lexmark.result.LocalizedSiebelLOVListResult;
import com.lexmark.result.LocalizedSiebelValueResult;
import com.lexmark.result.ProductImageResult;
import com.lexmark.result.SiebelLOVListResult;
import com.lexmark.result.TechnicianListResult;
import com.lexmark.result.ValidateInstalledPrinterSerialNumberResult;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.PartnerRequestsService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.service.impl.mock.PartnerDomainMockDataGenerator;
import com.lexmark.util.PortalSessionUtil;
import com.lexmark.util.TestUtil;
import com.lexmark.webservice.api.RequestService;

public class RequestsDebriefControllerTest extends EasyMockSupport{
	private static Logger logger = LogManager.getLogger(RequestsDebriefControllerTest.class);

	protected RequestsDebriefController controller;
	private PartnerRequestsService partnerRequestsService;
	private GlobalService globalService;
	private ServiceRequestLocale serviceRequestLocaleService;
	private ProductImageService productImageService;
	private RequestService requestWebService;
	
	@Before
	public void setUp() throws Exception {
		partnerRequestsService = createMock(PartnerRequestsService.class);
		globalService = createMock(GlobalService.class);
		serviceRequestLocaleService = createMock(ServiceRequestLocale.class);
		productImageService = createMock(ProductImageService.class);
		requestWebService = createMock(RequestService.class);
		
		controller = new RequestsDebriefController();
		
		TestUtil.setProperty(controller,"partnerRequestsService",partnerRequestsService);
		TestUtil.setProperty(controller,"globalService",globalService);
		TestUtil.setProperty(controller,"serviceRequestLocaleService",serviceRequestLocaleService);
		TestUtil.setProperty(controller,"productImageService",productImageService);
		TestUtil.setProperty(controller,"requestWebService",requestWebService);
	}
	
	@Test(expected=Exception.class)
	public void testShowRequestsDebriefPageActivityDetailResultNull() throws Exception {
		resetAll();
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		generateActivityIDAndServiceRequestId(request);
		expect(partnerRequestsService.retrieveActivityDetail((ActivityDetailContract)anyObject())).andReturn(null);
		replayAll();
		controller.showRequestsDebriefPage(model, request, response);
		verifyAll();
	}
	
	@Test(expected=Exception.class)
	public void testShowRequestsDebriefPageActivityNull() throws Exception {
		resetAll();
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		generateActivityIDAndServiceRequestId(request);
		ActivityDetailResult result = new ActivityDetailResult();
		expect(partnerRequestsService.retrieveActivityDetail((ActivityDetailContract)anyObject())).andReturn(result);
		replayAll();
		controller.showRequestsDebriefPage(model, request, response);
		verifyAll();
	}
	
	@Test(expected=Exception.class)
	public void testShowRequestsDebriefPageServiceRequestNull() throws Exception {
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
		controller.showRequestsDebriefPage(model, request, response);
		verifyAll();
	}
	
	@Test(expected=Exception.class)
	public void testShowRequestsDebriefPageAssetNull() throws Exception {
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
		controller.showRequestsDebriefPage(model, request, response);
		verifyAll();
	}
	@Test(expected=Exception.class)
	public void testShowRequestsDebriefPageActivityTypeNull() throws Exception {
		resetAll();
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		generateActivityIDAndServiceRequestId(request);
		ActivityDetailResult result = new ActivityDetailResult();
		Activity activity = PartnerDomainMockDataGenerator.getActivity(0);
		activity.setActivityType(null);
		result.setActivity(activity);
		expect(partnerRequestsService.retrieveActivityDetail((ActivityDetailContract)anyObject())).andReturn(result);
		mockRetrieveTechnicianList(partnerRequestsService);
		mockRetrieveSiebelLOVList(globalService);
		mockRetrieveProductImageUrl(productImageService);
		mockRetrieveLocalizedSiebelValue(serviceRequestLocaleService);
		mockRetrieveLocalizedSiebelLOVList(serviceRequestLocaleService);
		replayAll();
		controller.showRequestsDebriefPage(model, request, response);
		verifyAll();
	}
	@Test
	public void testShowRequestsDebriefPageA() throws Exception {
		resetAll();
		MockRenderRequest request = new MockRenderRequest();
		generateActivityIDAndServiceRequestId(request);
		request.setParameter(LexmarkConstants.TIMEZONE_OFFSET,"10");
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		ActivityDetailResult result = new ActivityDetailResult();
		Activity activity = PartnerDomainMockDataGenerator.getActivity(0);
		activity.getActivityType().setValue("getActivityType");
		PortalSessionUtil.setAllowAdditionalPaymentRequestFlag(request.getPortletSession(),false);
		AccountContact technician = new AccountContact();
		technician.setContactId("contactId");
		activity.setTechnician(technician);
		activity.getServiceRequest().getAsset().setIpAddress(null);
		activity.getServiceRequest().getAsset().setMacAddress("MacAddress");
		result.setActivity(activity);
		expect(partnerRequestsService.retrieveActivityDetail((ActivityDetailContract)anyObject())).andReturn(result);
		mockRetrieveTechnicianList(partnerRequestsService);
		mockRetrieveSiebelLOVList(globalService);
		mockRetrieveProductImageUrl(productImageService);
		mockRetrieveLocalizedSiebelValue(serviceRequestLocaleService);
		mockRetrieveLocalizedSiebelLOVList(serviceRequestLocaleService);
		replayAll();
		controller.showRequestsDebriefPage(model, request, response);
		RequestsDebriefForm requestsDebriefForm = (RequestsDebriefForm)model.asMap().get("requestsDebriefForm");
		Assert.assertNull(requestsDebriefForm.getAssetList());
		Assert.assertFalse(requestsDebriefForm.isShowAdditionalPaymentRequestListFlag());
		Assert.assertTrue(requestsDebriefForm.isNetworkConnectedFlag());
		Assert.assertFalse(requestsDebriefForm.getActivity().getTechnician().getNewContactFlag());
		verifyAll();
	}
	
	@Test
	public void testShowRequestsDebriefPageB() throws Exception {
		resetAll();
		MockRenderRequest request = new MockRenderRequest();
		generateActivityIDAndServiceRequestId(request);
		request.setParameter(LexmarkConstants.TIMEZONE_OFFSET,"10");
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		ActivityDetailResult result = new ActivityDetailResult();
		Activity activity = PartnerDomainMockDataGenerator.getActivity(0);
		activity.getActivityType().setValue(ServiceTypeEnum.ONSITE_EXCHANGE.getValue());
		List<PartLineItem> orderPartList = PartnerDomainMockDataGenerator.getPartLineItemListWithoutRandom();
		activity.setOrderPartList(orderPartList);
		PortalSessionUtil.setAllowAdditionalPaymentRequestFlag(request.getPortletSession(),true);
		AccountContact technician = new AccountContact();
		technician.setLastName("lastName");
		activity.setTechnician(technician);
		result.setActivity(activity);
		expect(partnerRequestsService.retrieveActivityDetail((ActivityDetailContract)anyObject())).andReturn(result);
		mockRetrieveTechnicianList(partnerRequestsService);
		mockRetrieveSiebelLOVList(globalService);
		mockRetrieveProductImageUrl(productImageService);
		mockRetrieveLocalizedSiebelValue(serviceRequestLocaleService);
		mockRetrieveLocalizedSiebelLOVList(serviceRequestLocaleService);
		replayAll();
		controller.showRequestsDebriefPage(model, request, response);
		RequestsDebriefForm requestsDebriefForm = (RequestsDebriefForm)model.asMap().get("requestsDebriefForm");
		Assert.assertNotNull(requestsDebriefForm.getAssetList());
		Assert.assertTrue(requestsDebriefForm.isShowAdditionalPaymentRequestListFlag());
		Assert.assertTrue(requestsDebriefForm.getActivity().getTechnician().getNewContactFlag());
		verifyAll();
	}
	
	
	@Test
	public void testSubmitRequestDebriefDuplicatedSubmit() throws Exception{
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		TestUtil.initSession(request.getPortletSession());
		RequestsDebriefForm claim = new RequestsDebriefForm();
		setReuqestDebriefForm(claim, "requestDetailView");
		controller.submitRequestsDebrief(claim, model, request, response);
		Assert.assertTrue(claim.getActivity().getServiceRequest().getId().equals(response.getRenderParameter("serviceRequestId")));
		Assert.assertTrue(claim.getActivity().getActivityId().equals(response.getRenderParameter("activityId")));
		Assert.assertTrue("showRequestDetailPage".equals(response.getRenderParameter("action")));
	}
	
	
	@Test
	public void testSubmitRequestDebriefA() throws Exception{
		resetAll();
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		TestUtil.initSession(request.getPortletSession());
		RequestsDebriefForm requestsDebriefForm = new RequestsDebriefForm();
		setReuqestDebriefForm(requestsDebriefForm, "requestDetailView");
		requestsDebriefForm.refreshSubmitToken(request);
		requestsDebriefForm.setTechnicianFullName("firstName,LastName/id");
		requestsDebriefForm.getActivity().setRepairCompleteFlag(true);
		
		ActivityDebriefSubmitResult result = new ActivityDebriefSubmitResult();
		result.setSuccess(true);
		expect(requestWebService.submitActivityDebrief((ActivityDebriefSubmitContract)anyObject())).andReturn(result);
		replayAll();
		controller.submitRequestsDebrief(requestsDebriefForm, model, request, response);
		
		AccountContact contact = requestsDebriefForm.getActivity().getTechnician();
		Assert.assertFalse(contact.getNewContactFlag());
		Assert.assertTrue(contact.getUpdateContactFlag());
		Assert.assertTrue(DebriefActionStatusEnum.INCOMPLETE.getValue().equals(requestsDebriefForm.getActivity().getDebrief().getDebriefActionStatus()));
		Assert.assertTrue("showRequestDetailPage".equals(response.getRenderParameter("action")));
		verifyAll();
	}
	
	@Test
	public void testSubmitRequestDebriefB() throws Exception{
		resetAll();
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		TestUtil.initSession(request.getPortletSession());
		RequestsDebriefForm requestsDebriefForm = new RequestsDebriefForm();
		setReuqestDebriefForm(requestsDebriefForm, "requestListView");
		requestsDebriefForm.refreshSubmitToken(request);
		requestsDebriefForm.setTechnicianFullName("other");
		requestsDebriefForm.getActivity().setRepairCompleteFlag(false);
		
		ActivityDebriefSubmitResult result = new ActivityDebriefSubmitResult();
		result.setSuccess(true);
		expect(requestWebService.submitActivityDebrief((ActivityDebriefSubmitContract)anyObject())).andReturn(result);
		replayAll();
		controller.submitRequestsDebrief(requestsDebriefForm, model, request, response);
		
		AccountContact contact = requestsDebriefForm.getActivity().getTechnician();
		Assert.assertFalse(contact.getUpdateContactFlag());
		Assert.assertTrue(contact.getNewContactFlag());
		Assert.assertTrue(DebriefActionStatusEnum.COMPLETE.getValue().equals(requestsDebriefForm.getActivity().getDebrief().getDebriefActionStatus()));
		Assert.assertNull(response.getRenderParameter("action"));
		verifyAll();
	}
	
	@Test
	public void testSubmitRequestDebriefFailed() throws Exception{
		resetAll();
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		TestUtil.initSession(request.getPortletSession());
		RequestsDebriefForm requestsDebriefForm = new RequestsDebriefForm();
		setReuqestDebriefForm(requestsDebriefForm, "requestListView");
		requestsDebriefForm.refreshSubmitToken(request);
		ActivityDebriefSubmitResult result = new ActivityDebriefSubmitResult();
		result.setSuccess(false);
		expect(requestWebService.submitActivityDebrief((ActivityDebriefSubmitContract)anyObject())).andReturn(result);
		replayAll();
		controller.submitRequestsDebrief(requestsDebriefForm, model, request, response);
		
		Assert.assertTrue("showRequestsDebriefPage".equals(response.getRenderParameter("action")));
		verifyAll();
	}
	
	@Test
	public void testValidateInstalledPrinterSerialNumber() throws Exception{
		resetAll();
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		ValidateInstalledPrinterSerialNumberResult result = new ValidateInstalledPrinterSerialNumberResult();
		result.setSuccess(true);
		expect(partnerRequestsService.validateInstalledPrinterSerialNumber((ValidateInstalledPrinterSerialNumberContract)anyObject())).andReturn(result);
		replayAll();
		controller.validateInstalledPrinterSerialNumber(request, response);
		assetStringExist(response.getContentAsString(),"succeed=\"true\"");
		verifyAll();
	}
	
	@Test
	public void testValidateInstalledPrinterSerialNumberException() throws Exception{
		resetAll();
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		ValidateInstalledPrinterSerialNumberResult result = new ValidateInstalledPrinterSerialNumberResult();
		result.setSuccess(true);
		expect(partnerRequestsService.validateInstalledPrinterSerialNumber((ValidateInstalledPrinterSerialNumberContract)anyObject())).andThrow(new Exception());
		replayAll();
		controller.validateInstalledPrinterSerialNumber(request, response);
		logger.info(response.getContentAsString());
		assetStringExist(response.getContentAsString(),"succeed=\"false\"");
		verifyAll();
	}

	private void generateActivityIDAndServiceRequestId(MockPortletRequest request){
		Activity activity = PartnerDomainMockDataGenerator.getActivity(1);
		request.setParameter("activityId", activity.getActivityId());
		request.setParameter("serviceRequestId", activity.getServiceRequest().getId());
	}
	private void mockRetrieveTechnicianList(PartnerRequestsService partnerRequestsService)throws Exception{
		TechnicianListResult technicianListResult = new TechnicianListResult();
		List<AccountContact> accountContactList = PartnerDomainMockDataGenerator.getAccountContactList();
		technicianListResult.setAccountContactList(accountContactList);
		expect(partnerRequestsService.retrieveTechnicianList((TechnicianListContract)anyObject())).andReturn(technicianListResult);
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
	
	private void setReuqestDebriefForm(RequestsDebriefForm requestsDebriefForm, String fromPage){
		Activity activity = PartnerDomainMockDataGenerator.getActivity(1);
		if(activity.getDebrief() == null){
			activity.setDebrief(new Debrief());
		}
		
		if(activity.getTechnician() == null){
			activity.setTechnician(new AccountContact());
		}
		requestsDebriefForm.setActivity(PartnerDomainMockDataGenerator.getActivity(1));
		requestsDebriefForm.setServiceStartDate("10/11/2011 10:10");
		requestsDebriefForm.setServiceEndDate("10/12/2011 10:10");
		requestsDebriefForm.setTimezoneOffset(-8);
		requestsDebriefForm.setFromPage(fromPage);
	}
	private void assetStringExist(String result, String value) {
		Assert.assertTrue(result.indexOf(value)>0);
	}	
}

