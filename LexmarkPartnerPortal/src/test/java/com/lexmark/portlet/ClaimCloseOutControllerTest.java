package com.lexmark.portlet;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.PortletSession;

import org.easymock.EasyMockSupport;
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
import com.lexmark.contract.ClaimDebriefSubmitContract;
import com.lexmark.contract.ClaimDetailContract;
import com.lexmark.contract.LocalizedSiebelLOVListContract;
import com.lexmark.contract.LocalizedSiebelValueContract;
import com.lexmark.contract.ProductImageContract;
import com.lexmark.contract.SiebelLOVListContract;
import com.lexmark.contract.TechnicianListContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Activity;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.enums.ErrorCodeOneEnum;
import com.lexmark.enums.ErrorCodeTwoEnum;
import com.lexmark.form.ClaimDebriefForm;
import com.lexmark.result.ClaimDebriefSubmitResult;
import com.lexmark.result.ClaimDetailResult;
import com.lexmark.result.LocalizedSiebelLOVListResult;
import com.lexmark.result.LocalizedSiebelValueResult;
import com.lexmark.result.ProductImageResult;
import com.lexmark.result.SiebelLOVListResult;
import com.lexmark.result.TechnicianListResult;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.PartnerRequestsService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.service.impl.mock.PartnerDomainMockDataGenerator;
import com.lexmark.util.PortalSessionUtil;
import com.lexmark.util.TestUtil;
import com.lexmark.webservice.api.ClaimService;

public class ClaimCloseOutControllerTest extends EasyMockSupport{
	protected ClaimCloseOutController controller;
	private PartnerRequestsService partnerRequestsService;
	private GlobalService globalService;
	private ServiceRequestLocale serviceRequestLocale;
	private ProductImageService productImageService;
	private ClaimService claimWebService;
	
	@Before
	public void setUp() throws Exception {
		partnerRequestsService = createMock(PartnerRequestsService.class);
		globalService = createMock(GlobalService.class);
		serviceRequestLocale = createMock(ServiceRequestLocale.class);
		productImageService = createMock(ProductImageService.class);
		claimWebService = createMock(ClaimService.class);
		
		controller = new ClaimCloseOutController();
		
		TestUtil.setProperty(controller,"claimWebService",claimWebService);
		TestUtil.setProperty(controller,"globalService",globalService);
		TestUtil.setProperty(controller,"partnerRequestsService",partnerRequestsService);
		TestUtil.setProperty(controller,"productImageService",productImageService);
		TestUtil.setProperty(controller,"serviceRequestLocaleService",serviceRequestLocale);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testShowCloseOutClaimPageClaimDetailResultNull() throws Exception {
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		generateActivityIDAndServiceRequestId(request);
		resetAll();
		expect(partnerRequestsService.retrieveClaimDetail((ClaimDetailContract)anyObject())).andReturn(null);
		replayAll();
		controller.showCloseOutClaimPage(model, request, response);
		verifyAll();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testShowCloseOutClaimPageActivityNull() throws Exception {
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		generateActivityIDAndServiceRequestId(request);
		resetAll();
		ClaimDetailResult claimDetailResult = new ClaimDetailResult();
		expect(partnerRequestsService.retrieveClaimDetail((ClaimDetailContract)anyObject())).andReturn(claimDetailResult);
		replayAll();
		controller.showCloseOutClaimPage(model, request, response);
		verifyAll();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testShowCloseOutClaimPageServiceRequestNull() throws Exception {
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		generateActivityIDAndServiceRequestId(request);
		resetAll();
		ClaimDetailResult claimDetailResult = new ClaimDetailResult();
		Activity activity = new Activity();
		claimDetailResult.setActivity(activity);
		expect(partnerRequestsService.retrieveClaimDetail((ClaimDetailContract)anyObject())).andReturn(claimDetailResult);
		replayAll();
		controller.showCloseOutClaimPage(model, request, response);
		verifyAll();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testShowCloseOutClaimPageAssetNull() throws Exception {
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		generateActivityIDAndServiceRequestId(request);
		resetAll();
		ClaimDetailResult claimDetailResult = new ClaimDetailResult();
		Activity activity = new Activity();
		claimDetailResult.setActivity(activity);
		ServiceRequest serviceRequest = new ServiceRequest();
		activity.setServiceRequest(serviceRequest);
		expect(partnerRequestsService.retrieveClaimDetail((ClaimDetailContract)anyObject())).andReturn(claimDetailResult);
		replayAll();
		controller.showCloseOutClaimPage(model, request, response);
		verifyAll();
	}
	
	@Test
	public void testShowCloseOutClaimPageSuccess() throws Exception {
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		generateActivityIDAndServiceRequestId(request);
		resetAll();
		ClaimDetailResult claimDetailResult = new ClaimDetailResult();
		Activity activity = PartnerDomainMockDataGenerator.getActivity(0);
		claimDetailResult.setActivity(activity);
		expect(partnerRequestsService.retrieveClaimDetail((ClaimDetailContract)anyObject())).andReturn(claimDetailResult);
		mockRetrieveTechnicianList(partnerRequestsService);
		mockRetrieveSiebelLOVList(globalService);
		mockRetrieveProductImageUrl(productImageService);
		mockRetrieveLocalizedSiebelValue(serviceRequestLocale);
		mockRetrieveLocalizedSiebelLOVList(serviceRequestLocale);
		replayAll();
		controller.showCloseOutClaimPage(model, request, response);
		verifyAll();
		assertNotNull(model.asMap().get("claimDebriefForm"));
	}
	
	@Test
	public void testTechnicianNewContactFlagTrue() throws Exception {
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		generateActivityIDAndServiceRequestId(request);
		resetAll();
		ClaimDetailResult claimDetailResult = new ClaimDetailResult();
		Activity activity = PartnerDomainMockDataGenerator.getActivity(0);
		claimDetailResult.setActivity(activity);
		AccountContact technician = new AccountContact();
		technician.setFirstName("firstName");
		technician.setLastName("lastName");
		activity.setTechnician(technician);
		expect(partnerRequestsService.retrieveClaimDetail((ClaimDetailContract)anyObject())).andReturn(claimDetailResult);
		mockRetrieveTechnicianList(partnerRequestsService);
		mockRetrieveSiebelLOVList(globalService);
		mockRetrieveProductImageUrl(productImageService);
		mockRetrieveLocalizedSiebelValue(serviceRequestLocale);
		mockRetrieveLocalizedSiebelLOVList(serviceRequestLocale);
		replayAll();
		controller.showCloseOutClaimPage(model, request, response);
		verifyAll();
		ClaimDebriefForm claimDebriefForm = (ClaimDebriefForm)model.asMap().get("claimDebriefForm");
		assertTrue(claimDebriefForm.getActivity().getTechnician().getNewContactFlag());
	}
	
	@Test
	public void testTechnicianNewContactFlagFalse() throws Exception {
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		generateActivityIDAndServiceRequestId(request);
		resetAll();
		ClaimDetailResult claimDetailResult = new ClaimDetailResult();
		Activity activity = PartnerDomainMockDataGenerator.getActivity(0);
		claimDetailResult.setActivity(activity);
		AccountContact technician = new AccountContact();
		technician.setContactId("123");
		activity.setTechnician(technician);
		expect(partnerRequestsService.retrieveClaimDetail((ClaimDetailContract)anyObject())).andReturn(claimDetailResult);
		mockRetrieveTechnicianList(partnerRequestsService);
		mockRetrieveSiebelLOVList(globalService);
		mockRetrieveProductImageUrl(productImageService);
		mockRetrieveLocalizedSiebelValue(serviceRequestLocale);
		mockRetrieveLocalizedSiebelLOVList(serviceRequestLocale);
		replayAll();
		controller.showCloseOutClaimPage(model, request, response);
		verifyAll();
		ClaimDebriefForm claimDebriefForm = (ClaimDebriefForm)model.asMap().get("claimDebriefForm");
		assertTrue(!claimDebriefForm.getActivity().getTechnician().getNewContactFlag());
	}
	@Test
	public void testAllowAdditionalPaymentRequestFlagTrue() throws Exception {
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		PortletSession session = request.getPortletSession();
		PortalSessionUtil.setAllowAdditionalPaymentRequestFlag(session,true);
		generateActivityIDAndServiceRequestId(request);
		resetAll();
		mockRetrieveClaimDetail(partnerRequestsService);
		mockRetrieveTechnicianList(partnerRequestsService);
		mockRetrieveSiebelLOVList(globalService);
		mockRetrieveProductImageUrl(productImageService);
		mockRetrieveLocalizedSiebelValue(serviceRequestLocale);
		mockRetrieveLocalizedSiebelLOVList(serviceRequestLocale);
		replayAll();
		controller.showCloseOutClaimPage(model, request, response);
		verifyAll();
		ClaimDebriefForm claimDebriefForm = (ClaimDebriefForm)model.asMap().get("claimDebriefForm");
		assertTrue(claimDebriefForm.isShowAdditionalPaymentRequestListFlag());
	}
	@Test
	public void testAllowAdditionalPaymentRequestFlagFalse() throws Exception {
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		PortletSession session = request.getPortletSession();
		PortalSessionUtil.setAllowAdditionalPaymentRequestFlag(session,false);
		generateActivityIDAndServiceRequestId(request);
		resetAll();
		mockRetrieveClaimDetail(partnerRequestsService);
		mockRetrieveTechnicianList(partnerRequestsService);
		mockRetrieveSiebelLOVList(globalService);
		mockRetrieveProductImageUrl(productImageService);
		mockRetrieveLocalizedSiebelValue(serviceRequestLocale);
		mockRetrieveLocalizedSiebelLOVList(serviceRequestLocale);
		replayAll();
		controller.showCloseOutClaimPage(model, request, response);
		verifyAll();
		ClaimDebriefForm claimDebriefForm = (ClaimDebriefForm)model.asMap().get("claimDebriefForm");
		assertTrue(!claimDebriefForm.isShowAdditionalPaymentRequestListFlag());
	}
	
	@Test
	public void testCancelClaimUpdateReturnRequestListView() throws Exception {
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		generateActivityIDAndServiceRequestId(request);
		request.setParameter("timezoneOffset", "1");
		request.setParameter("fromPage", "requestListView");
		controller.cancelClaimUpdate(model, request, response);
		assertNull(response.getRenderParameter("action"));
	}
	
	@Test
	public void testCancelClaimUpdateReturnClaimDetailView() throws Exception {
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		generateActivityIDAndServiceRequestId(request);
		request.setParameter("timezoneOffset", "1");
		request.setParameter("fromPage", "claimDetailView");
		controller.cancelClaimUpdate(model, request, response);
		String returnPage = response.getRenderParameter("action");
		assertTrue("retrieveClaimDetail".equals(returnPage));
	}
	
	
	@Test
	public void testRetrieveErrorCode2ByCode1() throws Exception {
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		Model model = new ExtendedModelMap();
		resetAll();
		SiebelLOVListResult result = new SiebelLOVListResult();
		List<ListOfValues> list = new ArrayList<ListOfValues>();
		ListOfValues listOfValues = new ListOfValues();
		listOfValues.setValue(ErrorCodeTwoEnum.CONTINUOUS_FEED_FOUR.getValue());
		list.add(listOfValues);
		
		listOfValues = new ListOfValues();
		listOfValues.setValue(ErrorCodeTwoEnum.CONTINUOUS_FEED_FIVE.getValue());
		list.add(listOfValues);
		result.setLovList(list);
		expect(globalService.retrieveSiebelLOVList((SiebelLOVListContract)anyObject())).andReturn(result);
		LocalizedSiebelLOVListResult localizedSiebelLOVListResult = new LocalizedSiebelLOVListResult();
		localizedSiebelLOVListResult.setLocalizedSiebelLOVList(list);
		expect(serviceRequestLocale.retrieveLocalizedSiebelLOVList((LocalizedSiebelLOVListContract) anyObject())).andReturn(localizedSiebelLOVListResult).anyTimes();
		replayAll();
		controller.retrieveErrorCode2ByCode1(ErrorCodeOneEnum.PAPER_FEED_TYPE_TWO.getValue(), ErrorCodeTwoEnum.CONTINUOUS_FEED_FIVE.getValue(), request, response, model);
		verifyAll();
		Pattern pattern = Pattern.compile(ErrorCodeTwoEnum.CONTINUOUS_FEED_FIVE.getValue());
		Matcher matcher = pattern.matcher(response.getContentAsString());
		assertTrue(matcher.find());
	}
	
	@Test
	public void testRetrieveErrorCode2ByCode1Exception() throws Exception {
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		Model model = new ExtendedModelMap();
		resetAll();
		Exception e = new Exception("RetrieveErrorCode2_error");
		expect(globalService.retrieveSiebelLOVList((SiebelLOVListContract)anyObject())).andThrow(e);
		replayAll();
		controller.retrieveErrorCode2ByCode1(ErrorCodeOneEnum.PAPER_FEED_TYPE_TWO.getValue(), ErrorCodeTwoEnum.CONTINUOUS_FEED_FIVE.getValue(), request, response, model);
		verifyAll();
	}
	@Test
	public void testSubmitClaimDebriefSuccessFromCalaimDetail() throws Exception{
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		TestUtil.initSession(request.getPortletSession());
		ClaimDebriefForm claim = new ClaimDebriefForm();
		setClaimDebriefForm(claim, "claimDetailView");
		claim.refreshSubmitToken(request);
		ClaimDebriefSubmitResult result = new ClaimDebriefSubmitResult();
		result.setSuccess(true);
		resetAll();
		expect(claimWebService.submitClaimDebrief((ClaimDebriefSubmitContract)anyObject())).andReturn(result);
		replayAll();
		controller.submitClaimDebrief(claim, model, request, response);
		verifyAll();
		assertTrue("retrieveClaimDetail".equals(response.getRenderParameter("action")));
	}
	@Test
	public void testSubmitClaimDebriefSuccessFromrequestList() throws Exception{
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		TestUtil.initSession(request.getPortletSession());
		ClaimDebriefForm claim = new ClaimDebriefForm();
		setClaimDebriefForm(claim, "requestListView");
		claim.refreshSubmitToken(request);
		ClaimDebriefSubmitResult result = new ClaimDebriefSubmitResult();
		result.setSuccess(true);
		resetAll();
		expect(claimWebService.submitClaimDebrief((ClaimDebriefSubmitContract)anyObject())).andReturn(result);
		replayAll();
		controller.submitClaimDebrief(claim, model, request, response);
		verifyAll();
	}
	@Test
	public void testSubmitClaimDebriefFailed() throws Exception{
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		TestUtil.initSession(request.getPortletSession());
		ClaimDebriefForm claim = new ClaimDebriefForm();
		setClaimDebriefForm(claim, "claimDetailView");
		claim.refreshSubmitToken(request);
		controller.submitClaimDebrief(claim, model, request, response);
		assertTrue("showCloseOutClaimPage".equals(response.getRenderParameter("action")));
	}
//	@Test
//	public void testSubmitClaimDebriefException() throws Exception{
//		MockActionRequest request = new MockActionRequest();
//		MockActionResponse response = new MockActionResponse();
//		Model model = new ExtendedModelMap();
//		TestUtil.initSession(request.getPortletSession());
//		ClaimDebriefForm claim = new ClaimDebriefForm();
//		claim.setActivity(PartnerDomainMockDataGenerator.getActivity(1));
//		claim.setServiceStartDate("10/11/2011 10:10");
//		claim.setServiceEndDate("10/12/2011 10:10");
//		claim.setTimezoneOffset("-8");
//		claim.setFromPage("requestListView");
//		
//		claim.refreshSubmitToken(request);
//		ClaimDebriefSubmitResult result = new ClaimDebriefSubmitResult();
//		result.setSuccess(true);
//		resetAll();
//		Exception e = new Exception("closeOutClaimException");
//		expect(claimWebService.submitClaimDebrief((ClaimDebriefSubmitContract)anyObject())).andThrow(e);
//		replayAll();
//		controller.submitClaimDebrief(claim, model, request, response);
//		verifyAll();
//	}
	
	@Test
	public void testSubmitClaimDebriefDuplicatedSubmit() throws Exception{
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		TestUtil.initSession(request.getPortletSession());
		ClaimDebriefForm claim = new ClaimDebriefForm();
		setClaimDebriefForm(claim, "claimDetailView");
		PortalSessionUtil.createSubmitToken(request);
		controller.submitClaimDebrief(claim, model, request, response);
	}
	
	@Test
	public void testSomething() throws Exception{
		ClaimDebriefSubmitContract contract = new ClaimDebriefSubmitContract();
		resetAll();
		expect(claimWebService.submitClaimDebrief((ClaimDebriefSubmitContract)anyObject())).andReturn(new ClaimDebriefSubmitResult());
		replayAll();
		claimWebService.submitClaimDebrief(contract);
		verifyAll();
	}
	
	private void generateActivityIDAndServiceRequestId(MockPortletRequest request){
		Activity activity = PartnerDomainMockDataGenerator.getActivity(1);
		request.setParameter("activityId", activity.getActivityId());
		request.setParameter("serviceRequestId", activity.getServiceRequest().getId());
		request.setParameter(LexmarkConstants.TIMEZONE_OFFSET, "1");
	}
	private void mockRetrieveTechnicianList(PartnerRequestsService partnerRequestsService)throws Exception{
		TechnicianListResult technicianListResult = new TechnicianListResult();
		List<AccountContact> accountContactList = PartnerDomainMockDataGenerator.getAccountContactList();
		technicianListResult.setAccountContactList(accountContactList);
		expect(partnerRequestsService.retrieveTechnicianList((TechnicianListContract)anyObject())).andReturn(technicianListResult);
	}
	private void mockRetrieveClaimDetail(PartnerRequestsService partnerRequestsService)throws Exception{
		ClaimDetailResult claimDetailResult = new ClaimDetailResult();
		Activity activity = PartnerDomainMockDataGenerator.getActivity(0);
		claimDetailResult.setActivity(activity);
		expect(partnerRequestsService.retrieveClaimDetail((ClaimDetailContract)anyObject())).andReturn(claimDetailResult);
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
	
	
	
	private void setClaimDebriefForm(ClaimDebriefForm claim, String fromPage){
		claim.setActivity(PartnerDomainMockDataGenerator.getActivity(1));
		claim.setServiceStartDate("10/11/2011 10:10");
		claim.setServiceEndDate("10/12/2011 10:10");
		claim.setTimezoneOffset(-8);
		claim.setFromPage(fromPage);
	}

}
