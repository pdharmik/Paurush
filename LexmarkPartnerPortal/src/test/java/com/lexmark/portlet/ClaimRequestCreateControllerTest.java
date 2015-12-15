package com.lexmark.portlet;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletSession;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
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
import com.lexmark.contract.FRUPartDetailContract;
import com.lexmark.contract.FRUPartListContract;
import com.lexmark.contract.GeographyCountryContract;
import com.lexmark.contract.GeographyStateContract;
import com.lexmark.contract.GlobalAssetDetailContract;
import com.lexmark.contract.PartnerAddressListContract;
import com.lexmark.contract.PartnerFavoriteAddressUpdateContract;
import com.lexmark.contract.PartnerIndirectAccountListContract;
import com.lexmark.contract.ProductImageContract;
import com.lexmark.contract.SiebelLOVListContract;
import com.lexmark.contract.TechnicianListContract;
import com.lexmark.contract.WarrantyClaimCreateContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Activity;
import com.lexmark.domain.Asset;
import com.lexmark.domain.Debrief;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.Part;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.ServicesUser;
import com.lexmark.form.ClaimRequestConfirmationForm;
import com.lexmark.form.CreateNewAddressForm;
import com.lexmark.result.FRUPartDetailResult;
import com.lexmark.result.FRUPartListResult;
import com.lexmark.result.GeographyListResult;
import com.lexmark.result.GlobalAssetDetailResult;
import com.lexmark.result.PartnerAddressListResult;
import com.lexmark.result.PartnerFavoriteAddressUpdateResult;
import com.lexmark.result.PartnerIndirectAccountListResult;
import com.lexmark.result.ProductImageResult;
import com.lexmark.result.SiebelLOVListResult;
import com.lexmark.result.TechnicianListResult;
import com.lexmark.result.WarrantyClaimCreateResult;
import com.lexmark.service.api.GeographyService;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.PartnerRequestsService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.util.TestUtil;
import com.lexmark.webservice.api.ClaimService;

public class ClaimRequestCreateControllerTest extends EasyMockSupport{
	private static Logger LOGGER = LogManager.getLogger(ClaimRequestCreateControllerTest.class);
	protected ClaimRequestCreateController controller;
	private PartnerRequestsService requestService;
	private ClaimService claimWebService;
    private GlobalService  globalService;
    private ServiceRequestLocale serviceRequestLocaleService;
	private GeographyService geographyService; 
	private ProductImageService productImageService;
	
	@Before
	public void setUp() throws Exception {
		requestService = createMock(PartnerRequestsService.class);
		globalService = createMock(GlobalService.class);
		serviceRequestLocaleService = createMock(ServiceRequestLocale.class);
		productImageService = createMock(ProductImageService.class);
		claimWebService = createMock(ClaimService.class);
		geographyService = createMock(GeographyService.class);
		controller = new ClaimRequestCreateController();
		TestUtil.setProperty(controller,"claimWebService",claimWebService);
		TestUtil.setProperty(controller,"globalService",globalService);
		TestUtil.setProperty(controller,"requestService",requestService);
		TestUtil.setProperty(controller,"productImageService",productImageService);
		TestUtil.setProperty(controller,"serviceRequestLocaleService",serviceRequestLocaleService);		
		TestUtil.setProperty(controller,"geographyService",geographyService);		
	}
	
	
	@Test
	public void testShowCreateClaimPageWhenNotMyPrinterFlagFalse() throws Exception{
		resetAll();
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		prepareRequest(request);
		request.setParameter("notMyPrinterFlag", "false");
		Model model = new ExtendedModelMap();
		ProductImageResult productImageResult = new ProductImageResult();
		SiebelLOVListResult siebelLOVListResult = new SiebelLOVListResult();
		PartnerIndirectAccountListResult partnerIndirectAccountListResult = new PartnerIndirectAccountListResult();
		List<Account> accountList = new ArrayList<Account>();
		partnerIndirectAccountListResult.setAccountList(accountList);
		Account account = new Account();
		accountList.add(account);
		GlobalAssetDetailResult detailResult = new GlobalAssetDetailResult();
		detailResult.setAsset(new Asset());
		expect(productImageService.retrieveProductImageUrl((ProductImageContract)anyObject(), (String)anyObject())).andReturn(productImageResult);
		expect(globalService.retrieveSiebelLOVList((SiebelLOVListContract)anyObject())).andReturn(siebelLOVListResult).anyTimes();
		expect(requestService.retrievePartnerIndirectAccountList((PartnerIndirectAccountListContract)anyObject())).andReturn(partnerIndirectAccountListResult);
		expect(requestService.retrieveGlobalAssetDetail((GlobalAssetDetailContract)anyObject())).andReturn(detailResult);
		replayAll();
		controller.showCreateClaimPage(model, request, response);
		assertNotNull(model.asMap().get("claimRequestConfirmationForm"));
		verifyAll();
	}
	
	
	
	@Test
	public void testShowCreateClaimPageWhenNotMyPrinterFlagFalseAndAssetHasPartnerAccount() throws Exception{
		resetAll();
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		prepareRequest(request);
		request.setParameter("notMyPrinterFlag", "false");
		Model model = new ExtendedModelMap();
		ProductImageResult productImageResult = new ProductImageResult();
		SiebelLOVListResult siebelLOVListResult = new SiebelLOVListResult();
		PartnerIndirectAccountListResult partnerIndirectAccountListResult = new PartnerIndirectAccountListResult();
		List<Account> accountList = new ArrayList<Account>();
		partnerIndirectAccountListResult.setAccountList(accountList);
		Account account = new Account();
		accountList.add(account);
		GlobalAssetDetailResult detailResult = new GlobalAssetDetailResult();
		Asset asset = new Asset();
		asset.setPartnerAccount(new Account());
		detailResult.setAsset(asset);
		expect(productImageService.retrieveProductImageUrl((ProductImageContract)anyObject(), (String)anyObject())).andReturn(productImageResult);
		expect(globalService.retrieveSiebelLOVList((SiebelLOVListContract)anyObject())).andReturn(siebelLOVListResult).anyTimes();
		expect(requestService.retrievePartnerIndirectAccountList((PartnerIndirectAccountListContract)anyObject())).andReturn(partnerIndirectAccountListResult);
		expect(requestService.retrieveGlobalAssetDetail((GlobalAssetDetailContract)anyObject())).andReturn(detailResult);
		replayAll();
		controller.showCreateClaimPage(model, request, response);
		assertNotNull(model.asMap().get("claimRequestConfirmationForm"));
		assertTrue(asset.getPartnerAccount().isOrderPartsFlag());
		verifyAll();
	}
	
	
	
	@Test
	public void testShowCreateClaimPageWhenNotMyPrinterFlagTrue() throws Exception{
		resetAll();
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		prepareRequest(request);
		request.setParameter("notMyPrinterFlag", "true");
		Model model = new ExtendedModelMap();
		ProductImageResult productImageResult = new ProductImageResult();
		SiebelLOVListResult siebelLOVListResult = new SiebelLOVListResult();
		PartnerIndirectAccountListResult partnerIndirectAccountListResult = new PartnerIndirectAccountListResult();
		List<Account> accountList = new ArrayList<Account>();
		partnerIndirectAccountListResult.setAccountList(accountList);
		Account account = new Account();
		accountList.add(account);
		expect(productImageService.retrieveProductImageUrl((ProductImageContract)anyObject(), (String)anyObject())).andReturn(productImageResult);
		expect(globalService.retrieveSiebelLOVList((SiebelLOVListContract)anyObject())).andReturn(siebelLOVListResult).anyTimes();
		expect(requestService.retrievePartnerIndirectAccountList((PartnerIndirectAccountListContract)anyObject())).andReturn(partnerIndirectAccountListResult);
		replayAll();
		controller.showCreateClaimPage(model, request, response);
		assertNotNull(model.asMap().get("claimRequestConfirmationForm"));
		ClaimRequestConfirmationForm form = (ClaimRequestConfirmationForm)model.asMap().get("claimRequestConfirmationForm");
		Asset asset = form.getAsset();
		assertNull(asset.getSerialNumber());
		assertNull(asset.getModelNumber());
		assertNull(asset.getProductTLI());
		assertNotNull(asset.getInstallAddress());
		verifyAll();
	}
	
	
	
	@Test
	public void testShowCreateClaimPageWhenNotMyPrinterFlagTrueAssetIdNotNull() throws Exception{
		resetAll();
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		prepareRequest(request);
		request.setParameter("assetId", "123");
		request.setParameter("notMyPrinterFlag", "true");
		Model model = new ExtendedModelMap();
		ProductImageResult productImageResult = new ProductImageResult();
		SiebelLOVListResult siebelLOVListResult = new SiebelLOVListResult();
		PartnerIndirectAccountListResult partnerIndirectAccountListResult = new PartnerIndirectAccountListResult();
		List<Account> accountList = new ArrayList<Account>();
		partnerIndirectAccountListResult.setAccountList(accountList);
		Account account = new Account();
		accountList.add(account);
		GlobalAssetDetailResult detailResult = new GlobalAssetDetailResult();
		Asset asset = new Asset();
		asset.setPartnerAccount(new Account());
		detailResult.setAsset(asset);
		expect(productImageService.retrieveProductImageUrl((ProductImageContract)anyObject(), (String)anyObject())).andReturn(productImageResult);
		expect(globalService.retrieveSiebelLOVList((SiebelLOVListContract)anyObject())).andReturn(siebelLOVListResult).anyTimes();
		expect(requestService.retrievePartnerIndirectAccountList((PartnerIndirectAccountListContract)anyObject())).andReturn(partnerIndirectAccountListResult);
		expect(requestService.retrieveGlobalAssetDetail((GlobalAssetDetailContract)anyObject())).andReturn(detailResult);
		replayAll();
		controller.showCreateClaimPage(model, request, response);
		assertNotNull(model.asMap().get("claimRequestConfirmationForm"));
		assertTrue(asset.getPartnerAccount().isOrderPartsFlag());
		verifyAll();
	}
	
	
	@Test
	public void testShowCreateClaimPageWhenThrowException() throws Exception{
		resetAll();
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		prepareRequest(request);
		request.setParameter("notMyPrinterFlag", "false");
		Model model = new ExtendedModelMap();
		SiebelLOVListResult siebelLOVListResult = new SiebelLOVListResult();
		PartnerIndirectAccountListResult partnerIndirectAccountListResult = new PartnerIndirectAccountListResult();
		List<Account> accountList = new ArrayList<Account>();
		partnerIndirectAccountListResult.setAccountList(accountList);
		Account account = new Account();
		accountList.add(account);
		GlobalAssetDetailResult detailResult = new GlobalAssetDetailResult();
		expect(globalService.retrieveSiebelLOVList((SiebelLOVListContract)anyObject())).andReturn(siebelLOVListResult).anyTimes();
		expect(requestService.retrievePartnerIndirectAccountList((PartnerIndirectAccountListContract)anyObject())).andReturn(partnerIndirectAccountListResult);
		expect(requestService.retrieveGlobalAssetDetail((GlobalAssetDetailContract)anyObject())).andReturn(detailResult);
		replayAll();
		try{
			controller.showCreateClaimPage(model, request, response);
			fail("except an exception here");
		}
		catch(Exception e){
			assertTrue(true);
		}
		verifyAll();		
	}
	
	
	
	@Test
	public void testCreateClaimRequestWhenDuplicatedSubmit() throws Exception{
		resetAll();
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		ClaimRequestConfirmationForm claimForm = new ClaimRequestConfirmationForm();
		request.getPortletSession().setAttribute(LexmarkConstants.SUBMIT_TOKEN, 456L, request.getPortletSession().PORTLET_SCOPE);
		claimForm.setSubmitToken(123L);
		controller.createClaimRequest(model, request, response, claimForm);
		assertEquals(response.getRenderParameter("action"), "retrieveClaimThankYouPage");
	}

	
	@Test
	public void testCreateClaimRequestWhenNewCustomerAccountAndNullActivityServiceRequestAndNullTechnicianNameAndMinusOneAddress() throws Exception{
		resetAll();
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		prepareRequest(request);
		ClaimRequestConfirmationForm claimForm = new ClaimRequestConfirmationForm();	
		request.getPortletSession().setAttribute(LexmarkConstants.SUBMIT_TOKEN, 123L, request.getPortletSession().PORTLET_SCOPE);
		claimForm.setSubmitToken(123L);
		Activity activity = new Activity();
		claimForm.setActivity(activity);
		claimForm.setNewAccountName("new name");
		activity.setNewCustomerAccountFlag("true");
		GenericAddress address = new GenericAddress();
		address.setAddressId("-1");
		activity.setShipToAddress(address);		
		expect(claimWebService.createWarrantyClaim((WarrantyClaimCreateContract)anyObject())).andReturn(new WarrantyClaimCreateResult());
		replayAll();
		controller.createClaimRequest(model, request, response, claimForm);
		assertEquals(activity.getCustomerAccount().getAccountName(), "new name");
		assertEquals(response.getRenderParameter("action"), "retrieveClaimThankYouPage");
	}
	

	@Test
	public void testCreateClaimRequestWhenNewCustomerAccountAndNullActivityServiceRequestAndTechnicianNameAndMinusOneAddress() throws Exception{
		resetAll();
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		prepareRequest(request);
		ClaimRequestConfirmationForm claimForm = new ClaimRequestConfirmationForm();	
		request.getPortletSession().setAttribute(LexmarkConstants.SUBMIT_TOKEN, 123L, request.getPortletSession().PORTLET_SCOPE);
		claimForm.setSubmitToken(123L);
		Activity activity = new Activity();
		claimForm.setActivity(activity);
		claimForm.setNewAccountName("new name");
		claimForm.setTechnicianFullName("cao,yang/1234");
		activity.setNewCustomerAccountFlag("true");
		GenericAddress address = new GenericAddress();
		address.setAddressId("-1");
		activity.setShipToAddress(address);		
		expect(claimWebService.createWarrantyClaim((WarrantyClaimCreateContract)anyObject())).andReturn(new WarrantyClaimCreateResult());
		replayAll();
		controller.createClaimRequest(model, request, response, claimForm);
		assertEquals(claimForm.getActivity().getTechnician().getFirstName(), "yang");
		assertEquals(claimForm.getActivity().getTechnician().getLastName(), "cao");
		assertEquals(claimForm.getActivity().getTechnician().getContactId(), "1234");
		assertFalse(claimForm.getActivity().getTechnician().getNewContactFlag());
		assertTrue(claimForm.getActivity().getTechnician().getUpdateContactFlag());
		assertEquals(activity.getCustomerAccount().getAccountName(), "new name");
		assertEquals(response.getRenderParameter("action"), "retrieveClaimThankYouPage");
	}	
	
	
	
	@Test
	public void testCreateClaimRequestWhenOldCustomerAccountAndOtheTechnicianNameAndNullActivityServiceRequestAndNullTechnicianNameAndMinusOneAddress() throws Exception{
		resetAll();
		MockActionRequest request = new MockActionRequest();
		MockActionResponse response = new MockActionResponse();
		Model model = new ExtendedModelMap();
		prepareRequest(request);
		ClaimRequestConfirmationForm claimForm = new ClaimRequestConfirmationForm();	
		request.getPortletSession().setAttribute(LexmarkConstants.SUBMIT_TOKEN, 123L, request.getPortletSession().PORTLET_SCOPE);
		claimForm.setSubmitToken(123L);
		claimForm.setServiceStartDate("11/11/2011 11:11");
		claimForm.setServiceEndDate("12/12/2012 11:11");
		claimForm.setTimezoneOffset(8);
		claimForm.setTechnicianFullName("other");
		Activity activity = new Activity();
		Debrief debrief = new Debrief();
		claimForm.setActivity(activity);
		claimForm.setNewAccountName("new name");
		activity.setNewCustomerAccountFlag("false");
		activity.setDebrief(debrief);
		ServiceRequest serviceRequest = new ServiceRequest();
		activity.setServiceRequest(serviceRequest);
		Account customerAccount = new Account();
		GenericAddress customerAccountAddress = new GenericAddress();
		customerAccountAddress.setAddressId("123");
		customerAccount.setAddress(customerAccountAddress);
		activity.setCustomerAccount(customerAccount);
		GenericAddress address = new GenericAddress();
		address.setAddressId("-1");
		activity.setShipToAddress(address);	
		AccountContact technician = new AccountContact();
		activity.setTechnician(technician);
		expect(claimWebService.createWarrantyClaim((WarrantyClaimCreateContract)anyObject())).andReturn(new WarrantyClaimCreateResult());
		replayAll();
		controller.createClaimRequest(model, request, response, claimForm);
		
		assertEquals(claimForm.getActivity().getServiceRequest().getServiceAddress().getAddressId(), "123");
		assertEquals(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(claimForm.getActivity().getDebrief().getServiceStartDate()), "2011-11-11 07:11:00");
		assertEquals(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(claimForm.getActivity().getDebrief().getServiceEndDate()), "2012-12-12 07:11:00");
		assertTrue(claimForm.getActivity().getTechnician().getNewContactFlag());
		assertFalse(claimForm.getActivity().getTechnician().getUpdateContactFlag());
		assertEquals(response.getRenderParameter("action"), "retrieveClaimThankYouPage");
	}
	
	
	@Test
	public void testShowUpdateNotePopupWhenHasNoteDateAndNoteAutherAndNonUpdateFlag(){
		resetAll();
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		prepareRequest(request);
		request.setParameter("noteDate", "11/11/2011");
		request.setParameter("noteAuthor", "canyang");
		request.setParameter("noteDetailId", "123");
		request.setParameter("rowId", "id123");
		request.setParameter("handleGridFlag", "noupdate");
		controller.showUpdateNotePopup(model, request, response);
		assertEquals(model.asMap().get("handleGridFlag"), "noupdate");
		assertEquals(model.asMap().get("noteDate"), "11/11/2011");
		assertEquals(model.asMap().get("noteAuthor"), "canyang");
		assertEquals(model.asMap().get("noteDetailId"), "123");
		assertNull(model.asMap().get("rowId"));
	}
	
	
	@Test
	public void testShowUpdateNotePopupWhenEmptyNoteDateAndEmptyNoteAutherAndUpdateFlag(){
		resetAll();
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		prepareRequest(request);
		request.setParameter("noteDetailId", "123");
		request.setParameter("rowId", "id123");
		request.setParameter("handleGridFlag", "update");
		controller.showUpdateNotePopup(model, request, response);
		
		assertEquals(model.asMap().get("handleGridFlag"), "update");
		assertNotNull(model.asMap().get("noteDate"));
		assertEquals(model.asMap().get("noteAuthor"), "yang cao");
		assertEquals(model.asMap().get("noteDetailId"), "123");
		assertEquals(model.asMap().get("rowId"), "id123");
	}
	
	
	
	@Test
	public void testSetTechnicianInformationWhenEmptyAccountId() throws Exception{
		resetAll();
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		TechnicianListResult technicianListResult = new TechnicianListResult();
		List<AccountContact> accountContactList = new ArrayList<AccountContact>();
		AccountContact contact1 = new AccountContact();
		contact1.setLastName("cao1");
		contact1.setFirstName("yang1");
		contact1.setContactId("id1");
		accountContactList.add(contact1);
		AccountContact contact2 = new AccountContact();
		contact2.setLastName("cao2");
		contact2.setFirstName("yang2");
		contact2.setContactId("id2");
		accountContactList.add(contact2);		
		technicianListResult.setAccountContactList(accountContactList);
		expect(requestService.retrieveTechnicianList((TechnicianListContract)anyObject())).andReturn(technicianListResult);
		replayAll();
		controller.setTechnicianInformation("", request, response);
		String content = response.getContentAsString();
		assertTrue(content.contains("<message>success</message>"));
		assertTrue(content.contains("<data>\"}\"</data>"));
	}
	
	
	
	@Test
	public void testSetTechnicianInformationWhenNotEmptyAccountId() throws Exception{
		resetAll();
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		TechnicianListResult technicianListResult = new TechnicianListResult();
		List<AccountContact> accountContactList = new ArrayList<AccountContact>();
		AccountContact contact1 = new AccountContact();
		contact1.setLastName("cao1");
		contact1.setFirstName("yang1");
		contact1.setContactId("id1");
		accountContactList.add(contact1);
		AccountContact contact2 = new AccountContact();
		contact2.setLastName("cao2");
		contact2.setFirstName("yang2");
		contact2.setContactId("id2");
		accountContactList.add(contact2);		
		technicianListResult.setAccountContactList(accountContactList);
		expect(requestService.retrieveTechnicianList((TechnicianListContract)anyObject())).andReturn(technicianListResult);
		replayAll();
		controller.setTechnicianInformation("accountId", request, response);
		String content = response.getContentAsString();
		assertTrue(content.contains("<message>success</message>"));
		assertTrue(content.contains("<data>\"}cao1, yang1/id1}cao2, yang2/id2}\"</data>"));
	}
	
	
	
	@Test
	public void testSetTechnicianInformationWhenException() throws Exception{
		resetAll();
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		expect(requestService.retrieveTechnicianList((TechnicianListContract)anyObject())).andThrow(new Exception(""));
		replayAll();
		controller.setTechnicianInformation("accountId", request, response);
		String content = response.getContentAsString();
		assertTrue(content.contains("<message>Failed to retrieve technician list.</message>"));
	}
	
	
	@Test
	public void testSetShiptoAddressWhenEmptyAccountList() throws Exception{
		resetAll();
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		prepareRequest(request);
		PartnerIndirectAccountListResult result = new PartnerIndirectAccountListResult();
		expect(requestService.retrievePartnerIndirectAccountList((PartnerIndirectAccountListContract)anyObject())).andReturn(result);
		replayAll();
		controller.setShiptoAddress("", request, response);
		String content = response.getContentAsString();
		assertTrue(content.contains("<message>Failed to retrieve ship-to addresses.</message>"));
	}
	
	
	@Test
	public void testSetShiptoAddressWhenHasAccountListAndEmptyAccountId() throws Exception{
		resetAll();
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		prepareRequest(request);
		PartnerIndirectAccountListResult result = new PartnerIndirectAccountListResult();
		List<Account> accountList = new ArrayList<Account>();
		Account account = new Account();
		accountList.add(account);
		result.setAccountList(accountList);
		expect(requestService.retrievePartnerIndirectAccountList((PartnerIndirectAccountListContract)anyObject())).andReturn(result);
		replayAll();
		controller.setShiptoAddress("", request, response);
		String content = response.getContentAsString();
		assertTrue(content.contains("<message>success</message>"));
		assertTrue(content.contains("<data>\"/\"</data>"));
	}
	
	
	
	@Test
	public void testSetShiptoAddressWhenHasAccountListAndAccountId() throws Exception{
		resetAll();
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		prepareRequest(request);
		PartnerIndirectAccountListResult result = new PartnerIndirectAccountListResult();
		List<Account> accountList = new ArrayList<Account>();
		Account account = new Account();
		account.setAccountId("id1");
		GenericAddress address = new GenericAddress();
		address.setAddressId("addId");
		address.setAddressLine1("line1");
		address.setAddressLine2("line2");
		address.setCity("hz");
		account.setAddress(address);
		accountList.add(account);
		result.setAccountList(accountList);
		expect(requestService.retrievePartnerIndirectAccountList((PartnerIndirectAccountListContract)anyObject())).andReturn(result);
		replayAll();
		controller.setShiptoAddress("id1", request, response);
		String content = response.getContentAsString();
		assertTrue(content.contains("<message>success</message>"));
		assertTrue(content.contains("<data>\"false/addId/null/line1/line2/null/hz/null/null/null/null/null/\"</data>"));
	}
	
	
	
	@Test
	public void testRetrieveOrganizationIdWhenEmptyPartnerAccountsAndEmptyAccountId() throws Exception{
		resetAll();
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		prepareRequest(request);	
		PartnerIndirectAccountListResult result = new PartnerIndirectAccountListResult();
		expect(requestService.retrievePartnerIndirectAccountList((PartnerIndirectAccountListContract)anyObject())).andReturn(result);
		replayAll();
		controller.retrieveOrganizationId("", request, response);
		String content = response.getContentAsString();
		assertTrue(content.contains("<message>success</message>"));
		assertTrue(content.contains("<data>\"\"</data>"));		
	}
	
	
	@Test
	public void testRetrieveOrganizationIdWhenEmptyPartnerAccountsAndNonEmptyAccountId() throws Exception{
		resetAll();
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		prepareRequest(request);	
		PartnerIndirectAccountListResult result = new PartnerIndirectAccountListResult();
		expect(requestService.retrievePartnerIndirectAccountList((PartnerIndirectAccountListContract)anyObject())).andReturn(result);
		replayAll();
		controller.retrieveOrganizationId("accountId", request, response);
		String content = response.getContentAsString();
		assertTrue(content.contains("<message>success</message>"));
		assertTrue(content.contains("<data>\"\"</data>"));		
	}
	
	
	@Test
	public void testRetrieveOrganizationIdWhenHasPartnerAccountsAndNonEmptyAccountId() throws Exception{
		resetAll();
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		prepareRequest(request);	
		PartnerIndirectAccountListResult result = new PartnerIndirectAccountListResult();
		List<Account> accountList = new ArrayList<Account>();
		Account account = new Account();
		account.setAccountId("accountId");
		account.setOrganizationID("orgId");
		accountList.add(account);
		result.setAccountList(accountList);
		expect(requestService.retrievePartnerIndirectAccountList((PartnerIndirectAccountListContract)anyObject())).andReturn(result);
		replayAll();
		controller.retrieveOrganizationId("accountId", request, response);
		String content = response.getContentAsString();
		assertTrue(content.contains("<message>success</message>"));
		assertTrue(content.contains("<data>\"orgId\"</data>"));		
	}
	
	
	@Test
	public void testShowPartAndToolPage(){
		resetAll();
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		prepareRequest(request);
		controller.showPartAndToolPage(request, response, model);
		assertEquals(model.asMap().get("showPartList"), "http://localhost/mockportlet?resourceID=showPartList");
	}
	
	
	
	@Test
	public void testShowPartListWhenThrowException() throws Exception{
		resetAll();
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		prepareRequest(request);	
		expect(requestService.retrieveFRUPartList((FRUPartListContract)anyObject())).andThrow(new Exception("exception"));
		replayAll();
		try{
			controller.showPartList(request, response);
			fail("an exception expected here");
		}
		catch(Exception e){
			assertTrue(true);
		}
	}
	
	
	
	@Test
	public void testShowPartList() throws Exception{
		resetAll();
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		prepareRequest(request);	
		FRUPartListResult result = new FRUPartListResult();
		List<Part> partList = new ArrayList<Part>();
		Part part = new Part();
		part.setPartId("partId");
		part.setPartName("partName");
		part.setPartNumber("partNumber");
		partList.add(part);
		result.setPartList(partList);
		expect(requestService.retrieveFRUPartList((FRUPartListContract)anyObject())).andReturn(result);
		replayAll();
		controller.showPartList(request, response);
		String content = response.getContentAsString();
		assertTrue(content.contains("<cell><![CDATA[partNumber]]></cell>"));
		assertTrue(content.contains("<cell><![CDATA[partName ]]></cell>"));
		assertTrue(content.contains("<cell><![CDATA[false]]></cell>"));
	}
	
	
	@Test
	public void testRetrievePartWhenThrowException() throws Exception{
		resetAll();
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		Model model = new ExtendedModelMap();
		prepareRequest(request);	
		expect(requestService.retrieveFRUPart((FRUPartDetailContract)anyObject())).andThrow(new Exception("exception"));
		replayAll();
		controller.retrievePart(request, response, model);
		String content = response.getContentAsString();
		assertTrue(content.contains("<message>retrieve Part unsuccessful.</message>"));
	}
	
	
	
	@Test
	public void testRetrievePartWhenEmptyPart() throws Exception{
		resetAll();
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		Model model = new ExtendedModelMap();
		FRUPartDetailResult result = new FRUPartDetailResult();
		prepareRequest(request);	
		expect(requestService.retrieveFRUPart((FRUPartDetailContract)anyObject())).andReturn(result);
		replayAll();
		controller.retrievePart(request, response, model);
		String content = response.getContentAsString();
		assertTrue(content.contains("<message>retrieved Part successfully.</message>"));
		assertTrue(content.contains("<data>\"\"</data>"));
	}
	
	
	
	@Test
	public void testRetrievePartWhenNonEmptyPart() throws Exception{
		resetAll();
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		Model model = new ExtendedModelMap();
		FRUPartDetailResult result = new FRUPartDetailResult();
		Part part = new Part();
		part.setPartId("partId");
		part.setPartName("partName");
		part.setPartNumber("partNumber");
		result.setPart(part);
		prepareRequest(request);	
		expect(requestService.retrieveFRUPart((FRUPartDetailContract)anyObject())).andReturn(result);
		replayAll();
		controller.retrievePart(request, response, model);
		String content = response.getContentAsString();
		assertTrue(content.contains("<message>retrieved Part successfully.</message>"));
		assertTrue(content.contains("<data>\"partNumber///partName///false///null///partId\"</data>"));
	}
	
	
	
	@Test
	public void testRetrieveServiceAddressListWhenFavoriteFlagIsTrue() throws Exception{
		resetAll();
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		Model model = new ExtendedModelMap();
		request.setParameter("favoriteFlag", "true");
		request.setParameter("accountId", "id");
		PartnerAddressListResult result = new PartnerAddressListResult();
		List<GenericAddress> addressList  = new ArrayList<GenericAddress>();
		GenericAddress address = new GenericAddress();
		address.setAddressLine1("line1");
		address.setAddressLine2("line2");
		address.setAddressId("addressId");
		address.setUserFavorite(true);
		addressList.add(address);
		result.setAddressList(addressList);
		expect(requestService.retrievePartnerAddressList((PartnerAddressListContract)anyObject())).andReturn(result);
		replayAll();
		controller.retrieveServiceAddressList(request, response, model);
		String content = response.getContentAsString();
		assertTrue(content.contains("<cell><![CDATA[addressId]]></cell>"));
		assertTrue(content.contains("<cell><![CDATA[line1]]></cell>"));
		assertTrue(content.contains("<cell><![CDATA[line2]]></cell>"));
	}
	
	

	@Test
	public void testRetrieveServiceAddressListWhenFavoriteFlagIsFalse() throws Exception{
		resetAll();
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		Model model = new ExtendedModelMap();
		request.setParameter("favoriteFlag", "false");
		request.setParameter("accountId", "id");
		PartnerAddressListResult result = new PartnerAddressListResult();
		List<GenericAddress> addressList  = new ArrayList<GenericAddress>();
		GenericAddress address = new GenericAddress();
		address.setAddressLine1("line1");
		address.setAddressLine2("line2");
		address.setAddressId("addressId");
		address.setUserFavorite(true);
		addressList.add(address);
		result.setAddressList(addressList);
		expect(requestService.retrievePartnerAddressList((PartnerAddressListContract)anyObject())).andReturn(result);
		replayAll();
		controller.retrieveServiceAddressList(request, response, model);
		String content = response.getContentAsString();
		assertTrue(content.contains("<cell><![CDATA[addressId]]></cell>"));
		assertTrue(content.contains("<cell><![CDATA[line1]]></cell>"));
		assertTrue(content.contains("<cell><![CDATA[line2]]></cell>"));
	}
	
	
	@Test
	public void testSetPartnerAddressFavouriteFlagWhenResultIsTrue() throws Exception{
		resetAll();
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		PartnerFavoriteAddressUpdateResult result = new PartnerFavoriteAddressUpdateResult();
		result.setResult(true);
		expect(requestService.updatePartnerUserFavoriteAddress((PartnerFavoriteAddressUpdateContract)anyObject())).andReturn(result);
		replayAll();
		controller.setPartnerAddressFavouriteFlag("addressId", "accountId", true, request, response);
		String content = response.getContentAsString();
		assertTrue(content.contains("<message>Set Partner address favorite flag successfully.</message>"));
		assertTrue(content.contains("<data>\"addressId\"</data>"));		
	}
	
	
	
	@Test
	public void testSetPartnerAddressFavouriteFlagWhenResultIsFalse() throws Exception{
		resetAll();
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		PartnerFavoriteAddressUpdateResult result = new PartnerFavoriteAddressUpdateResult();
		result.setResult(false);
		expect(requestService.updatePartnerUserFavoriteAddress((PartnerFavoriteAddressUpdateContract)anyObject())).andReturn(result);
		replayAll();
		controller.setPartnerAddressFavouriteFlag("addressId", "accountId", true, request, response);
		String content = response.getContentAsString();
		assertTrue(content.contains("<message>Set Partner address favorite flag failed.</message>"));
		assertTrue(content.contains("<data>\"addressId\"</data>"));		
	}
	
	
	@Test
	public void testSetPartnerAddressFavouriteFlagWhenThrowsException() throws Exception{
		resetAll();
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		expect(requestService.updatePartnerUserFavoriteAddress((PartnerFavoriteAddressUpdateContract)anyObject())).andThrow(new Exception());
		replayAll();
		controller.setPartnerAddressFavouriteFlag("addressId", "accountId", true, request, response);
		String content = response.getContentAsString();
		assertTrue(content.contains("<message>Set Partner address favorite flag failed.</message>"));
		assertTrue(content.contains("<data>\"addressId\"</data>"));		
	}
	
	
	
	@Test
	public void testShowCreateNewAddressPageWhenThrowException() throws Exception{
		resetAll();
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		Model model = new ExtendedModelMap();
		GeographyListResult countryListResult = null;
		expect(geographyService.getCountryDetails()).andReturn(countryListResult);
		replayAll();
		try{
			controller.showCreateNewAddressPage(request, response, model);
			fail("an exception is expected here");
		}
		catch(Exception e){
			assertTrue(true);
		}
	}
	
	
	
	@Test
	public void testShowCreateNewAddressPage() throws Exception{
		resetAll();
		MockRenderRequest request = new MockRenderRequest();
		MockRenderResponse response = new MockRenderResponse();
		prepareRequest(request);
		Model model = new ExtendedModelMap();
		GeographyListResult countryListResult = new GeographyListResult();
		List<GeographyCountryContract> countryList = new ArrayList<GeographyCountryContract>();
		GeographyCountryContract country = new GeographyCountryContract();
		country.setCountryCode("code");
		country.setCountryName("name");
		countryList.add(country);
		countryListResult.setCountryList(countryList);
		expect(geographyService.getCountryDetails()).andReturn(countryListResult);
		replayAll();
		controller.showCreateNewAddressPage(request, response, model);
		assertNotNull(model.asMap().get("stateData"));
		assertNotNull(model.asMap().get("createNewAddressForm"));
		assertEquals(((CreateNewAddressForm)model.asMap().get("createNewAddressForm")).getCountries().get(0), country);
	}
	
	
	
	@Test
	public void testGetStateWhenEmptyCountryCode() throws Exception{
		resetAll();
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		prepareRequest(request);
		Model model = new ExtendedModelMap();
		controller.getState(request, response, model);
		String content = response.getContentAsString();
		assertTrue(content.contains("<select id=\"state\" onChange=\"removeErrMsg();\"><option value=\"\">Please Select</option></select>"));
	}
	
	
	@Test
	public void testGetStateWhenNonEmptyCountryCode() throws Exception{
		resetAll();
		MockResourceRequest request = new MockResourceRequest();
		MockResourceResponse response = new MockResourceResponse();
		request.setParameter("countryCode", "countryCode");
		prepareRequest(request);
		Model model = new ExtendedModelMap();
		GeographyListResult countryListResult = new GeographyListResult();
		List<GeographyStateContract> states = new ArrayList<GeographyStateContract>();
		GeographyStateContract state = new GeographyStateContract();
		state.setStateCode("stateCode");
		state.setStateName("stateName");
		states.add(state);
		countryListResult.setStateList(states);
		expect(geographyService.getStateDetails((String)anyObject())).andReturn(countryListResult);
		replayAll();
		controller.getState(request, response, model);
		String content = response.getContentAsString();
		LOGGER.info(content);
		assertTrue(content.contains("<select id=\"state\" onChange=\"removeErrMsg();\"><option value=\"\">Please Select</option><option value=\"stateCode\">stateName</option></select>"));
	}
	

	private void prepareRequest(MockPortletRequest request) {
		request.addLocale(Locale.US);
		Map<String, Object> ldapUserData = new HashMap<String, Object>();
		ldapUserData.put(LexmarkConstants.LASTNAME, "cao");
		ldapUserData.put(LexmarkConstants.FIRSTNAME, "yang");
		PortletSession session = request.getPortletSession();
		ServicesUser servicesUser = new ServicesUser();
		session.setAttribute(LexmarkConstants.SERVICES_USER_PHASE2, servicesUser, session.APPLICATION_SCOPE);
		session.setAttribute("omniture.createClaimStart", Calendar.getInstance(), session.APPLICATION_SCOPE);
		session.setAttribute(LexmarkConstants.LDAP_USER_DATA_PHASE2, ldapUserData, session.APPLICATION_SCOPE);
	}
}
