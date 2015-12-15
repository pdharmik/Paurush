package com.lexmark.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletSession;

import org.junit.Test;
import org.springframework.mock.web.portlet.MockActionRequest;
import org.springframework.mock.web.portlet.MockPortletRequest;
import org.springframework.mock.web.portlet.MockResourceRequest;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.ActivityDebriefSubmitContract;
import com.lexmark.contract.ActivityDetailContract;
import com.lexmark.contract.ActivityListContract;
import com.lexmark.contract.AssignedTechnicianUpdateContract;
import com.lexmark.contract.BulkUploadStatusContract;
import com.lexmark.contract.ClaimDebriefSubmitContract;
import com.lexmark.contract.ClaimDetailContract;
import com.lexmark.contract.ClaimUpdateContract;
import com.lexmark.contract.ContactInformationContract;
import com.lexmark.contract.CustomerAccountListContract;
import com.lexmark.contract.FRUPartDetailContract;
import com.lexmark.contract.FRUPartListContract;
import com.lexmark.contract.GlobalAssetDetailContract;
import com.lexmark.contract.GlobalPartnerAssetListContract;
import com.lexmark.contract.LdapUserDataContract;
import com.lexmark.contract.LocalizedSiebelLOVListContract;
import com.lexmark.contract.LocalizedSiebelValueContract;
import com.lexmark.contract.OpenClaimListContract;
import com.lexmark.contract.PartnerAddressListContract;
import com.lexmark.contract.PartnerAgreementListContract;
import com.lexmark.contract.PartnerFavoriteAddressUpdateContract;
import com.lexmark.contract.PartnerIndirectAccountListContract;
import com.lexmark.contract.PaymentDetailsContract;
import com.lexmark.contract.PaymentLineItemDetailsContract;
import com.lexmark.contract.PaymentListContract;
import com.lexmark.contract.PaymentRequestListContract;
import com.lexmark.contract.ServiceActivityHistoryDetailContract;
import com.lexmark.contract.ServiceActivityHistoryListContract;
import com.lexmark.contract.ServicesUserContract;
import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.contract.SiebelLOVListContract;
import com.lexmark.contract.TechnicianListContract;
import com.lexmark.contract.ValidateInstalledPrinterSerialNumberContract;
import com.lexmark.contract.WarrantyClaimCreateContract;
import com.lexmark.domain.Activity;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.ServicesUser;
import com.lexmark.enums.ServiceRequestTypeEnum;
import com.lexmark.service.impl.real.enums.QueryType;


public class ContractFactoryTest {
	@Test
	public void testCrateOpenClaimListContract(){
		String assetId = "assetId";
		MockActionRequest request = new MockActionRequest();
		PortletSession session = request.getPortletSession();
		addMdmIdAndMdmLevelInSession(session);		
		OpenClaimListContract contract = ContractFactory.crateOpenClaimListContract(assetId,request);
		
		assertTrue("assetId".equals(contract.getAssetId()));
		assertTrue("mdmId".equals(contract.getMdmId()));
		assertTrue("mdmLevel".equals(contract.getMdmLevel()));
	}
	
	@Test
	public void testGetSiebelAccountListContract(){
		MockActionRequest request = new MockActionRequest();
		PortletSession session = request.getPortletSession();
		addMdmIdAndMdmLevelInSession(session);
		
		SiebelAccountListContract contract = ContractFactory.getSiebelAccountListContract(request);
		
		assertTrue("mdmId".equals(contract.getMdmId()));
		assertTrue("mdmLevel".equals(contract.getMdmLevel()));
		assertTrue(contract.isNewQueryIndicator());
	}
	
	@Test
	public void testCreateGlobalPartnerAssetListContract(){
		String serialNumber = "serialNumber";		
		GlobalPartnerAssetListContract contract = ContractFactory.createGlobalPartnerAssetListContract(serialNumber);
		assertTrue("serialNumber".equals(contract.getSerialNumber()));
	}	
	
	@Test
	public void testCreateSiebelLOVListContract(){
		String lovName = "lovName";
		String errorCode1 = "errorCode1";		
		SiebelLOVListContract contract = ContractFactory.createSiebelLOVListContract(lovName,errorCode1);
		assertTrue("lovName".equals(contract.getLovName()));
		assertTrue("errorCode1".equals(contract.getErrorCode1()));
	}	
	
	@Test
	public void testCreateTechnicianListContract(){
		String  partnerAccountId = "partnerAccountId";	 
		TechnicianListContract contract = ContractFactory.createTechnicianListContract(partnerAccountId);
		assertTrue("partnerAccountId".equals(contract.getPartnerAccountId()));
	}	
	
	@Test
	public void testCreateLocalizedSiebelValueContract(){
		ListOfValues lov = new ListOfValues();
		lov.setValue("lovValue");
		lov.setType("lovType");
		Locale locale = Locale.ENGLISH;
		LocalizedSiebelValueContract contract = ContractFactory.createLocalizedSiebelValueContract(lov, locale);
		assertTrue("lovValue".equals(contract.getLovValue()));
		assertTrue("lovType".equals(contract.getLovListName()));
		assertTrue(LocaleUtil.getSupportLocaleCode(locale).equals(contract.getLocaleName()));
	}
	
	@Test
	public void testIndirectPartnerCreateActivityListContract(){
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter("timezoneOffset","2");
		request.setParameter("beginDate","10/11/2011 10:10");
		request.setParameter("endDate","10/11/2011 10:10");
		PortletSession session = request.getPortletSession();
		session.setAttribute("flag.indirectPartnerFlag", true, PortletSession.APPLICATION_SCOPE);
		session.setAttribute("flag.directPartnerFlag", false, PortletSession.APPLICATION_SCOPE);
		addMdmIdAndMdmLevelInSession(session);
		List<String> userRoles = new ArrayList<String>();
		session.setAttribute(LexmarkConstants.USERROLES_PHASE2, userRoles, PortletSession.APPLICATION_SCOPE);
		ActivityListContract contract = ContractFactory.createActivityListContract(request);
		assertTrue(ServiceRequestTypeEnum.CLAIM_REQUEST.getValue().equals(contract.getServiceRequestType()));
		assertTrue("All".equals(contract.getStatus()));
		assertTrue("mdmId".equals(contract.getMdmId()));
		assertTrue("mdmLevel".equals(contract.getMdmLevel()));
		assertNotNull(contract.getFilterCriteria().get("Activity.activityDate.startDate"));
		assertNotNull(contract.getFilterCriteria().get("Activity.activityDate.endDate"));
	}
	
	@Test
	public void testDirectPartnerCreateActivityListContract(){
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter("queryType",QueryType.Unassigned.toString());
		request.setParameter("requestType",ServiceRequestTypeEnum.SERVICE_REQUEST.getValue());
		request.setParameter("status","status");
		request.setParameter("timezoneOffset","2");
		PortletSession session = request.getPortletSession();
		addMdmIdAndMdmLevelInSession(session);
		session.setAttribute("flag.indirectPartnerFlag", false, PortletSession.APPLICATION_SCOPE);
		session.setAttribute("flag.directPartnerFlag", true, PortletSession.APPLICATION_SCOPE);
		List<String> userRoles = new ArrayList<String>();
		session.setAttribute(LexmarkConstants.USERROLES_PHASE2, userRoles, PortletSession.APPLICATION_SCOPE);
		ActivityListContract contract = ContractFactory.createActivityListContract(request);
		assertTrue(ServiceRequestTypeEnum.SERVICE_REQUEST.getValue().equals(contract.getServiceRequestType()));
		assertTrue("status".equals(contract.getStatus()));
		assertTrue("mdmId".equals(contract.getMdmId()));
		assertTrue("mdmLevel".equals(contract.getMdmLevel()));
		assertNull(contract.getFilterCriteria().get("Activity.activityDate.startDate"));
		assertNull(contract.getFilterCriteria().get("Activity.activityDate.endDate"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDateExceptionCreateActivityListContract(){
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter("timezoneOffset","2");
		request.setParameter("queryType",QueryType.Unassigned.toString());
		request.setParameter("status","status");
		request.setParameter("beginDate","errordate");
		request.setParameter("endDate","errordate");
		PortletSession session = request.getPortletSession();
		session.setAttribute("flag.indirectPartnerFlag", false, PortletSession.APPLICATION_SCOPE);
		session.setAttribute("flag.directPartnerFlag", true, PortletSession.APPLICATION_SCOPE);
		ActivityListContract contract = ContractFactory.createActivityListContract(request);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDateExceptionCreatePaymentRequestListContract(){
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter("timezoneOffset","2");
		request.setParameter("beginDate","errordate");
		request.setParameter("endDate","errordate");
		PortletSession session = request.getPortletSession();
		session.setAttribute("flag.indirectPartnerFlag", false, PortletSession.APPLICATION_SCOPE);
		session.setAttribute("flag.directPartnerFlag", true, PortletSession.APPLICATION_SCOPE);
		PaymentRequestListContract contract = ContractFactory.createPaymentRequestListContract(request);
	}
	
	@Test
	public void testcreatePaymentRequestListContractA(){
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter("timezoneOffset","2");
		request.setParameter("paymentStatus","paymentStatus");
		request.setParameter("partnerAgreement","errordate");
		PortletSession session = request.getPortletSession();
		addMdmIdAndMdmLevelInSession(session);
		PaymentRequestListContract contract = ContractFactory.createPaymentRequestListContract(request);
		assertTrue("mdmId".equals(contract.getMdmId()));
		assertTrue("mdmLevel".equals(contract.getMdmLevel()));
		assertNull(contract.getFilterCriteria().get("Activity.startDate"));
		assertNull(contract.getFilterCriteria().get("Activity.endDate"));
		assertTrue("paymentStatus".equals(contract.getPaymentStatus()));
		assertNotNull(contract.getFilterCriteria().get("Activity.partnerAgreementName"));
	}
	
	@Test
	public void testcreatePaymentRequestListContractB(){
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter("timezoneOffset","2");
		request.setParameter("beginDate","10/11/2011 10:10");
		request.setParameter("endDate","10/11/2011 10:10");
		PortletSession session = request.getPortletSession();
		addMdmIdAndMdmLevelInSession(session);
		PaymentRequestListContract contract = ContractFactory.createPaymentRequestListContract(request);
		assertTrue("mdmId".equals(contract.getMdmId()));
		assertTrue("mdmLevel".equals(contract.getMdmLevel()));
		assertNotNull(contract.getFilterCriteria().get("Activity.startDate"));
		assertNotNull(contract.getFilterCriteria().get("Activity.endDate"));
		assertTrue("All".equals(contract.getPaymentStatus()));
		assertNull(contract.getFilterCriteria().get("Activity.partnerAgreementName"));
	}
	
	@Test
	public void testCreateClaimDetailContract(){
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter("activityId","activityId");
		request.setParameter("serviceRequestId","serviceRequestId");
		ClaimDetailContract contract = ContractFactory.createClaimDetailContract(request);
		assertTrue("activityId".equals(contract.getActivityId()));
		assertTrue("serviceRequestId".equals(contract.getServiceRequestId()));
	}
	
	@Test
	public void testCreateGlobalAssetDetailContract(){
		String assetId = "assetId";
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter("activityId","activityId");
		request.setParameter("serviceRequestId","serviceRequestId");
		PortletSession session = request.getPortletSession();
		addMdmIdAndMdmLevelInSession(session);
		GlobalAssetDetailContract contract = ContractFactory.createGlobalAssetDetailContract(assetId, request);
		assertTrue("assetId".equals(contract.getAssetId()));
		assertTrue("mdmId".equals(contract.getMdmId()));
		assertTrue("mdmLevel".equals(contract.getMdmLevel()));
	}
	
	@Test
	public void testCreateCustomerAccountListContract(){
		MockResourceRequest request = new MockResourceRequest();
		PortletSession session = request.getPortletSession();
		addMdmIdAndMdmLevelInSession(session);
		CustomerAccountListContract contract = ContractFactory.createCustomerAccountListContract(request);
		assertTrue("mdmId".equals(contract.getMdmId()));
		assertTrue("mdmLevel".equals(contract.getMdmLevel()));
	}
	
	@Test
	public void testCreateFRUPartDetailContractReplacementFlagTrue(){
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter("partNumber","partNumber");
		request.setParameter("modelNumber","modelNumber");
		request.setParameter("organizationId","organizationId");
		request.setParameter("replacementFlag","true");
		FRUPartDetailContract contract = ContractFactory.createFRUPartDetailContract(request);
		assertTrue("partNumber".equals(contract.getPartNumber()));
		assertTrue("modelNumber".equals(contract.getModelNumber()));
		assertTrue("organizationId".equals(contract.getPartnerOrg()));
		assertTrue(contract.isReplacementFlag());
	}
	
	@Test
	public void testCreateFRUPartDetailContractReplacementFlagFalse(){
		MockResourceRequest request = new MockResourceRequest();
		FRUPartDetailContract contract = ContractFactory.createFRUPartDetailContract(request);
		assertTrue(!contract.isReplacementFlag());
	}
	
	@Test
	public void testCreateFRUPartListContract(){
		String modelNumber = "modelNumber";
		FRUPartListContract contract = ContractFactory.createFRUPartListContract(modelNumber);
		assertTrue("modelNumber".equals(contract.getModelNumber()));
	}
	
	@Test
	public void testGetServicesUserContract(){
		String userNumber = "userNumber";
		String emailAddress = "emailAddress";
		ServicesUserContract contract = ContractFactory.getServicesUserContract(userNumber, emailAddress);
		assertTrue("userNumber".equals(contract.getUserNumber()));
		assertTrue("emailAddress".equals(contract.getEmailAddress()));
	}
	
	@Test
	public void testGetLdapUserDataContract(){
		String emailAddress = "emailAddress";
		LdapUserDataContract contract = ContractFactory.getLdapUserDataContract(emailAddress);
		assertTrue("emailAddress".equals(contract.getEmailAddress()));
	}
	
	@Test
	public void testCreateServiceActivityHistoryListContract(){
		String assetId = "assetId";
		String serviceRequestId = "serviceRequestId";
		ServiceActivityHistoryListContract contract = ContractFactory.createServiceActivityHistoryListContract(assetId, serviceRequestId);
		assertTrue("assetId".equals(contract.getAssetId()));
		assertTrue("serviceRequestId".equals(contract.getServiceRequestId()));
	}
	
	@Test
	public void testCreatePartnerAddressListContract(){
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter("favoriteFlag","true");
		request.setParameter("accountId","accountId");
		PartnerAddressListContract contract = ContractFactory.createPartnerAddressListContract(request);
		assertTrue("accountId".equals(contract.getAccountID()));
		assertTrue(contract.isFavoriteFlag());
	}
	
	@Test
	public void testCreatePartnerFavoriteAddressUpdateContract(){
		MockResourceRequest request = new MockResourceRequest();
		PartnerFavoriteAddressUpdateContract contract = ContractFactory.createPartnerFavoriteAddressUpdateContract(request);
		assertNull(contract.getContactId());
	}
	
	@Test
	public void testCreatePartnerIndirectAccountListContract(){
		MockResourceRequest request = new MockResourceRequest();
		PortletSession session = request.getPortletSession();
		addMdmIdAndMdmLevelInSession(session);
		PartnerIndirectAccountListContract contract = ContractFactory.createPartnerIndirectAccountListContract(request);
		assertTrue("mdmId".equals(contract.getMdmId()));
		assertTrue("mdmLevel".equals(contract.getMdmLevel()));
	}
	
	@Test
	public void testCreateAssignedTechnicianUpdateContract(){
		MockResourceRequest request = new MockResourceRequest();
		String activityId = "activityId";
		AssignedTechnicianUpdateContract contract = ContractFactory.createAssignedTechnicianUpdateContract(activityId,request);
		assertTrue("activityId".equals(contract.getActivityId()));
	}
	
	@Test
	public void testCreateActivityDetailContract(){
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter("serviceRequestId","serviceRequestId");
		request.setParameter("activityId","activityId");
		ActivityDetailContract contract = ContractFactory.createActivityDetailContract(request);
		assertTrue("activityId".equals(contract.getActivityId()));
		assertTrue("serviceRequestId".equals(contract.getServiceRequestId()));
	}
	
	@Test
	public void testCreateServiceActivityHistoryDetailContract(){
		String serviceRequestId = "serviceRequestId";
		String activityId = "activityId";
		ServiceActivityHistoryDetailContract contract = ContractFactory.createServiceActivityHistoryDetailContract(activityId, serviceRequestId);
		assertTrue("activityId".equals(contract.getActivityId()));
		assertTrue("serviceRequestId".equals(contract.getServiceRequestId()));
	}
	
	@Test
	public void testCreateClaimDebriefSubmitContract(){
		Activity activity = new Activity();
		activity.setActivityId("activityId");
		ClaimDebriefSubmitContract contract = ContractFactory.createClaimDebriefSubmitContract(activity);
		assertTrue("activityId".equals(contract.getActivity().getActivityId()));
	}
	
	@Test
	public void testCreateWarrantyClaimContract(){
		MockActionRequest request = new MockActionRequest();
		PortletSession session = request.getPortletSession();
		addMdmIdAndMdmLevelInSession(session);
		Activity activity = new Activity();
		activity.setActivityId("activityId");
		WarrantyClaimCreateContract contract = ContractFactory.createWarrantyClaimContract(request,activity);
		assertTrue("activityId".equals(contract.getActivity().getActivityId()));
		assertTrue("mdmId".equals(contract.getMdmId()));
		assertTrue("mdmLevel".equals(contract.getMdmLevel()));
	}
	
	@Test
	public void testCreateClaimUpdateContract(){
		MockActionRequest request = new MockActionRequest();
		PortletSession session = request.getPortletSession();
		addMdmIdAndMdmLevelInSession(session);
		Activity activity = new Activity();
		activity.setActivityId("activityId");
		ClaimUpdateContract contract = ContractFactory.createClaimUpdateContract(request,activity);
		assertTrue("activityId".equals(contract.getActivity().getActivityId()));
		assertTrue("mdmId".equals(contract.getMdmId()));
		assertTrue("mdmLevel".equals(contract.getMdmLevel()));
	}
	
	@Test
	public void testCreatePaymentDetailsContract(){
		String paymentId = "paymentId";
		PaymentDetailsContract contract = ContractFactory.
				createPaymentDetailsContract(paymentId,	new MockPortletRequest());
		assertTrue("paymentId".equals(contract.getPaymentId()));
	}
	
	@Test
	public void testCreatePaymentLineItemDetailsContract(){
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter("paymentId","paymentId");
		PaymentLineItemDetailsContract contract = ContractFactory.createPaymentLineItemDetailsContract(request);
		assertTrue("paymentId".equals(contract.getPaymentId()));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDateExceptionCreatePaymentListContract(){
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter("beginDate","errordate");
		request.setParameter("endDate","errordate");
		request.setParameter("timezoneOffset","2");
		PaymentListContract contract = ContractFactory.createPaymentListContract(request);
	}
	
	@Test
	public void testCreatePaymentListContractA(){
		MockResourceRequest request = new MockResourceRequest();
		PortletSession session = request.getPortletSession();
		addMdmIdAndMdmLevelInSession(session);
		request.setParameter("beginDate","10/11/2011 10:10");
		request.setParameter("endDate","10/11/2011 10:10");
		request.setParameter("timezoneOffset","2");
		request.setParameter("partnerAgreement","partnerAgreement");
		PaymentListContract contract = ContractFactory.createPaymentListContract(request);
		assertTrue("mdmId".equals(contract.getMdmId()));
		assertTrue("mdmLevel".equals(contract.getMdmLevel()));
		assertTrue("partnerAgreement".equals(contract.getFilterCriteria().get("Payment.partnerAgreement")));
		assertNotNull(contract.getFilterCriteria().get("Payment.startDate"));
		assertNotNull(contract.getFilterCriteria().get("Payment.endDate"));
	}
	
	@Test
	public void testCreatePaymentListContractB(){
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter("timezoneOffset","2");
		PortletSession session = request.getPortletSession();
		addMdmIdAndMdmLevelInSession(session);
		PaymentListContract contract = ContractFactory.createPaymentListContract(request);
		assertTrue("mdmId".equals(contract.getMdmId()));
		assertTrue("mdmLevel".equals(contract.getMdmLevel()));
		assertNull(contract.getFilterCriteria().get("Payment.partnerAgreement"));
		assertNull(contract.getFilterCriteria().get("Payment.startDate"));
		assertNull(contract.getFilterCriteria().get("Payment.endDate"));
	}
	
	@Test
	public void testCreatePartnerAgreementListContract(){
		MockResourceRequest request = new MockResourceRequest();
		addMdmIdAndMdmLevelInSession(request.getPortletSession());
		PartnerAgreementListContract contract = ContractFactory.createPartnerAgreementListContract(request);
		assertTrue("mdmId".equals(contract.getMdmId()));
		assertTrue("mdmLevel".equals(contract.getMdmLevel()));
	}
	
	@Test
	public void testCreateValidateInstalledPrinterSerialNumberContract(){
		MockResourceRequest request = new MockResourceRequest();
		request.setParameter("activityId","activityId");
		request.setParameter("modelNumber","modelNumber");
		request.setParameter("serialNumber","serialNumber");
		request.setParameter("serviceRequestId","serviceRequestId");
		ValidateInstalledPrinterSerialNumberContract contract = ContractFactory.createValidateInstalledPrinterSerialNumberContract(request);
		assertTrue("activityId".equals(contract.getActivityId()));
		assertTrue("modelNumber".equals(contract.getModelNumber()));
		assertTrue("serialNumber".equals(contract.getSerialNumber()));
		assertTrue("serviceRequestId".equals(contract.getServiceRequestId()));
	}
	
	@Test
	public void testCreateActivityDebriefSubmitContract(){
		Activity activity = new Activity();
		activity.setActivityId("activityId");
		ActivityDebriefSubmitContract contract = ContractFactory.createActivityDebriefSubmitContract(activity);
		assertTrue("activityId".equals(contract.getActivity().getActivityId()));
	}
	
	@Test
	public void testGetContactInformationContract() throws Exception{
		MockResourceRequest request = new MockResourceRequest();
		addMdmIdAndMdmLevelInSession(request.getPortletSession());
		ContactInformationContract contract = ContractFactory.getContactInformationContract(request);
		assertTrue("mdmId".equals(contract.getMdmId()));
		assertTrue("mdmLevel".equals(contract.getMdmLevel()));
	}
	
	@Test
	public void testCreateLocalizedSiebelLOVListContract() throws Exception{
		
		String lovType = "lovType";
		String partnerType = "partnerType";
		Locale locale = Locale.ENGLISH;
		LocalizedSiebelLOVListContract contract = ContractFactory.createLocalizedSiebelLOVListContract(lovType, partnerType, locale);
		assertTrue("lovType".equals(contract.getLovListName()));
		assertTrue("partnerType".equals(contract.getPartnerType()));
		assertTrue(LocaleUtil.getSupportLocaleCode(locale).equals(contract.getLocaleName()));
		
	}
	
	@Test
	public void testGetBulkUploadStatusContract() throws Exception{
		BulkUploadStatusContract contract = ContractFactory.getBulkUploadStatusContract();
		assertNotNull(contract);
	}
	
	
	private void addMdmIdAndMdmLevelInSession(PortletSession session){
		ServicesUser serviceUser = new ServicesUser();
		serviceUser.setMdmId("mdmId");
		serviceUser.setMdmLevel("mdmLevel");
		session.setAttribute(LexmarkConstants.SERVICES_USER_PHASE2,serviceUser, PortletSession.APPLICATION_SCOPE);
	}
	
}
