package com.lexmark.portlet;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;

import java.util.List;

import org.easymock.EasyMockSupport;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.portlet.MockActionRequest;
import org.springframework.mock.web.portlet.MockActionResponse;
import org.springframework.mock.web.portlet.MockRenderRequest;
import org.springframework.mock.web.portlet.MockRenderResponse;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.ClaimDetailContract;
import com.lexmark.contract.ClaimUpdateContract;
import com.lexmark.contract.LocalizedSiebelLOVListContract;
import com.lexmark.contract.LocalizedSiebelValueContract;
import com.lexmark.contract.ProductImageContract;
import com.lexmark.contract.SiebelLOVListContract;
import com.lexmark.contract.TechnicianListContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Activity;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.form.ClaimDetailForm;
import com.lexmark.result.ClaimDetailResult;
import com.lexmark.result.ClaimUpdateResult;
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
import com.lexmark.util.TestUtil;
import com.lexmark.webservice.api.ClaimService;

public class ClaimUpdateControllerTest extends EasyMockSupport{
	private PartnerRequestsService partnerRequestsService;
	private GlobalService globalService;
	private ServiceRequestLocale serviceRequestLocaleService;
	private ProductImageService productImageService;
	private ClaimService claimWebService;
	private ClaimUpdateController controller;
	
	@Before
	public void setUp() throws Exception {
		partnerRequestsService = createMock(PartnerRequestsService.class);
		globalService = createMock(GlobalService.class);
		serviceRequestLocaleService = createMock(ServiceRequestLocale.class);
		productImageService = createMock(ProductImageService.class);
		claimWebService = createMock(ClaimService.class);
		
		controller = new ClaimUpdateController();
		
		TestUtil.setProperty(controller,"claimWebService",claimWebService);
		TestUtil.setProperty(controller,"globalService",globalService);
		TestUtil.setProperty(controller,"partnerRequestsService",partnerRequestsService);
		TestUtil.setProperty(controller,"productImageService",productImageService);
		TestUtil.setProperty(controller,"serviceRequestLocaleService",serviceRequestLocaleService);
	}
	
	@Test
	public void testShowClaimUpdatePageA() throws Exception{
		resetAll();
		ClaimDetailResult claimDetailResult = new ClaimDetailResult();
		Activity activity = getDefalutActivity(true);
		activity.getPartnerAccount().setAllowAdditionalPaymentRequestFlag(true);
		claimDetailResult.setActivity(activity);
		expect(partnerRequestsService.retrieveClaimDetail((ClaimDetailContract)anyObject())).andReturn(claimDetailResult);
		mockRetrieveSiebelLOVList(globalService);
		mockRetrieveLocalizedSiebelValue(serviceRequestLocaleService);
		mockRetrieveLocalizedSiebelLOVList(serviceRequestLocaleService);
		mockRetrieveProductImageUrl(productImageService);
		replayAll();
		MockRenderRequest request = new MockRenderRequest();
		request.setParameter("fromPage","fromPage");
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		controller.showClaimUpdatePage(model, request, response);
		verifyAll();
		Assert.assertTrue((Boolean)model.asMap().get("showRequestedPartsFlag"));
		Assert.assertTrue((Boolean)model.asMap().get("showOrderPartsFlag"));
		Assert.assertTrue((Boolean)model.asMap().get("showReturnPartsFlag"));
		Assert.assertTrue((Boolean)model.asMap().get("showAdditionalPaymentRequestListFlag"));
		ClaimDetailForm claimDetailForm = (ClaimDetailForm)model.asMap().get("claimDetailFormForUpdate");
		Assert.assertNotNull(claimDetailForm);
		Assert.assertNotNull(model.asMap().get("orderPartListXML"));
		Assert.assertNotNull(model.asMap().get("downServiceHistoryURL"));
		Assert.assertTrue("fromPage".equals(claimDetailForm.getFromPage()));
	}

	@Test
	public void testShowClaimUpdatePageB() throws Exception{
		resetAll();
		ClaimDetailResult claimDetailResult = new ClaimDetailResult();
		Activity activity = getDefalutActivity(false);
		activity.getPartnerAccount().setAllowAdditionalPaymentRequestFlag(false);
		claimDetailResult.setActivity(activity);
		expect(partnerRequestsService.retrieveClaimDetail((ClaimDetailContract)anyObject())).andReturn(claimDetailResult);
		mockRetrieveSiebelLOVList(globalService);
		mockRetrieveLocalizedSiebelValue(serviceRequestLocaleService);
		mockRetrieveLocalizedSiebelLOVList(serviceRequestLocaleService);
		mockRetrieveProductImageUrl(productImageService);
		replayAll();
		MockRenderRequest request = new MockRenderRequest();
		request.setParameter("fromPage","fromPage");
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		controller.showClaimUpdatePage(model, request, response);
		verifyAll();
		Assert.assertFalse((Boolean)model.asMap().get("showRequestedPartsFlag"));
		Assert.assertFalse((Boolean)model.asMap().get("showOrderPartsFlag"));
		Assert.assertFalse((Boolean)model.asMap().get("showReturnPartsFlag"));
		Assert.assertFalse((Boolean)model.asMap().get("showAdditionalPaymentRequestListFlag"));
		ClaimDetailForm claimDetailForm = (ClaimDetailForm)model.asMap().get("claimDetailFormForUpdate");
		Assert.assertNotNull(claimDetailForm);
		Assert.assertNotNull(model.asMap().get("orderPartListXML"));
		Assert.assertNotNull(model.asMap().get("downServiceHistoryURL"));
		Assert.assertTrue("fromPage".equals(claimDetailForm.getFromPage()));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testShowClaimUpdatePageClaimDetailResultNull() throws Exception {
		resetAll();
		expect(partnerRequestsService.retrieveClaimDetail((ClaimDetailContract)anyObject())).andReturn(null);
		replayAll();
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		controller.showClaimUpdatePage(model, request, response);
		verifyAll();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testShowClaimUpdatePageActivityNull() throws Exception {
		resetAll();
		ClaimDetailResult claimDetailResult = new ClaimDetailResult();
		expect(partnerRequestsService.retrieveClaimDetail((ClaimDetailContract)anyObject())).andReturn(claimDetailResult);
		replayAll();
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		controller.showClaimUpdatePage(model, request, response);
		verifyAll();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testShowClaimUpdatePageServiceRequestNull() throws Exception {
		resetAll();
		ClaimDetailResult claimDetailResult = new ClaimDetailResult();
		Activity activity = new Activity();
		claimDetailResult.setActivity(activity);
		expect(partnerRequestsService.retrieveClaimDetail((ClaimDetailContract)anyObject())).andReturn(claimDetailResult);
		replayAll();
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		controller.showClaimUpdatePage(model, request, response);
		verifyAll();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testShowClaimUpdatePageAssetNull() throws Exception {
		resetAll();
		ClaimDetailResult claimDetailResult = new ClaimDetailResult();
		Activity activity = new Activity();
		claimDetailResult.setActivity(activity);
		ServiceRequest serviceRequest = new ServiceRequest();
		activity.setServiceRequest(serviceRequest);
		expect(partnerRequestsService.retrieveClaimDetail((ClaimDetailContract)anyObject())).andReturn(claimDetailResult);
		replayAll();
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		controller.showClaimUpdatePage(model, request, response);
		verifyAll();
	}
	
	@Test
	public void testSubmitClaimUpdateDuplicatedSubmitA() throws Exception{
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		TestUtil.initSession(request.getPortletSession());
		ClaimDetailForm claim = createClaimDetailForm();
		claim.setFromPage("claimDetailView");
		controller.submitClaimUpdate(model, request, response ,claim);
		Assert.assertTrue("retrieveClaimDetail".equals(response.getRenderParameter("action")));
	}
	
	@Test
	public void testSubmitClaimUpdateDuplicatedSubmitB() throws Exception{
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		TestUtil.initSession(request.getPortletSession());
		ClaimDetailForm claim = createClaimDetailForm();
		claim.setFromPage("createClaimMultipleClaimView");
		controller.submitClaimUpdate(model, request, response ,claim);
		Assert.assertTrue("showGlobalPartnerAssetSectionView".equals(response.getRenderParameter("action")));
	}
	
	@Test
	public void testSubmitClaimUpdateDuplicatedSubmitC() throws Exception{
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		TestUtil.initSession(request.getPortletSession());
		ClaimDetailForm claim = createClaimDetailForm();
		claim.setFromPage("requestList");
		controller.submitClaimUpdate(model, request, response ,claim);
		Assert.assertNull(response.getRenderParameter("action"));
	}
	
	
	@Test
	public void testSubmitClaimUpdateA() throws Exception{
		resetAll();
		ClaimUpdateResult claimUpdateResult = new ClaimUpdateResult();
		claimUpdateResult.setSuccess(true);
		expect(claimWebService.updateWarrantyClaim((ClaimUpdateContract)anyObject())).andReturn(claimUpdateResult);
		replayAll();
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		TestUtil.initSession(request.getPortletSession());
		ClaimDetailForm claimForm = createClaimDetailForm();
		Activity activity = claimForm.getClaimDetail();
		activity.setTechnician(null);
		claimForm.setTechnicianFullName("other");
		claimForm.setFromPage("claimDetailView");
		claimForm.getClaimDetail().setTechnician(null);
		claimForm.refreshSubmitToken(request);
		controller.submitClaimUpdate(model, request, response ,claimForm);
		verifyAll();
		Assert.assertTrue(activity.getTechnician().getNewContactFlag());
		Assert.assertFalse(activity.getTechnician().getUpdateContactFlag());
		Assert.assertNotNull(model.asMap().get("serviceSuccessMessages"));
		Assert.assertTrue("retrieveClaimDetail".equals(response.getRenderParameter("action")));
	}
	
	@Test
	public void testSubmitClaimUpdateB() throws Exception{
		resetAll();
		ClaimUpdateResult claimUpdateResult = new ClaimUpdateResult();
		claimUpdateResult.setSuccess(true);
		expect(claimWebService.updateWarrantyClaim((ClaimUpdateContract)anyObject())).andReturn(claimUpdateResult);
		replayAll();
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		TestUtil.initSession(request.getPortletSession());
		ClaimDetailForm claimForm = createClaimDetailForm();
		Activity activity = claimForm.getClaimDetail();
		claimForm.setTechnicianFullName("firstName,LastName/id");
		claimForm.setFromPage("createClaimMultipleClaimView");
		claimForm.getClaimDetail().setTechnician(null);
		claimForm.refreshSubmitToken(request);
		controller.submitClaimUpdate(model, request, response ,claimForm);
		verifyAll();
		Assert.assertFalse(activity.getTechnician().getNewContactFlag());
		Assert.assertTrue(activity.getTechnician().getUpdateContactFlag());
		Assert.assertNotNull(model.asMap().get("serviceSuccessMessages"));
		Assert.assertTrue("showGlobalPartnerAssetSectionView".equals(response.getRenderParameter("action")));
	}
	
	@Test
	public void testSubmitClaimUpdateC() throws Exception{
		resetAll();
		ClaimUpdateResult claimUpdateResult = new ClaimUpdateResult();
		claimUpdateResult.setSuccess(true);
		expect(claimWebService.updateWarrantyClaim((ClaimUpdateContract)anyObject())).andReturn(claimUpdateResult);
		replayAll();
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		TestUtil.initSession(request.getPortletSession());
		ClaimDetailForm claimForm = createClaimDetailForm();
		Activity activity = claimForm.getClaimDetail();
		claimForm.setTechnicianFullName("firstName,LastName/id");
		claimForm.setFromPage("requestList");
		claimForm.getClaimDetail().setTechnician(null);
		claimForm.refreshSubmitToken(request);
		controller.submitClaimUpdate(model, request, response ,claimForm);
		verifyAll();
		Assert.assertFalse(activity.getTechnician().getNewContactFlag());
		Assert.assertTrue(activity.getTechnician().getUpdateContactFlag());
		Assert.assertNotNull(model.asMap().get("serviceSuccessMessages"));
		Assert.assertNull(response.getRenderParameter("action"));
	}
	
	@Test
	public void testSubmitClaimUpdateFailed() throws Exception{
		resetAll();
		ClaimUpdateResult claimUpdateResult = new ClaimUpdateResult();
		claimUpdateResult.setSuccess(false);
		expect(claimWebService.updateWarrantyClaim((ClaimUpdateContract)anyObject())).andReturn(claimUpdateResult);
		replayAll();
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		TestUtil.initSession(request.getPortletSession());
		ClaimDetailForm claimForm = createClaimDetailForm();
		claimForm.setTechnicianFullName("firstName,LastName/id");
		claimForm.setFromPage("requestList");
		claimForm.getClaimDetail().setTechnician(null);
		claimForm.refreshSubmitToken(request);
		controller.submitClaimUpdate(model, request, response ,claimForm);
		verifyAll();
		Assert.assertNotNull(model.asMap().get("serviceErrors"));
	}
	
	@Test
	public void testCancelClaimUpdateA() throws Exception{
		MockActionRequest request = new MockActionRequest();
		request.setParameter(LexmarkConstants.TIMEZONE_OFFSET,"10");
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		ClaimDetailForm claim = createClaimDetailForm();
		claim.setFromPage("claimDetailView");
		controller.cancelClaimUpdate(model, request, response ,claim);
		Assert.assertTrue("retrieveClaimDetail".equals(response.getRenderParameter("action")));
	}
	
	@Test
	public void testCancelClaimUpdateB() throws Exception{
		MockActionRequest request = new MockActionRequest();
		request.setParameter(LexmarkConstants.TIMEZONE_OFFSET,"10");
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		ClaimDetailForm claim = createClaimDetailForm();
		claim.setFromPage("createClaimMultipleClaimView");
		controller.cancelClaimUpdate(model, request, response ,claim);
		Assert.assertTrue("showGlobalPartnerAssetSectionView".equals(response.getRenderParameter("action")));
	}
	
	@Test
	public void testCancelClaimUpdateC() throws Exception{
		MockActionRequest request = new MockActionRequest();
		request.setParameter(LexmarkConstants.TIMEZONE_OFFSET,"10");
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		ClaimDetailForm claim = createClaimDetailForm();
		claim.setFromPage("requestList");
		controller.cancelClaimUpdate(model, request, response ,claim);
		Assert.assertNull(response.getRenderParameter("action"));
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
	
	private ClaimDetailForm createClaimDetailForm(){
		ClaimDetailForm claimDetailForm = new ClaimDetailForm();
		claimDetailForm.setClaimDetail(PartnerDomainMockDataGenerator.getActivity(1));
		claimDetailForm.setTimezoneOffset(-8);
		return claimDetailForm;
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
		}else{
			activity.setOrderPartList(null);
			activity.setPendingPartList(null);
			activity.setReturnPartList(null);
			activity.setRecommendedPartList(null);
			activity.setAdditionalPaymentRequestList(null);
			activity.setServiceInstructionList(null);
		}
		return activity;
	}
}
