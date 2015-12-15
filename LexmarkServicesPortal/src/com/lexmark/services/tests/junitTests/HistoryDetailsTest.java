/**
 * Copyright@ 2013. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: HistoryDetailsTest.java
 * Package     		: com.lexmark.services.tests.junitTests
 * Creation Date 	: 12th July 2013
 *
 * Modification History:
 *
 * --------------------------------------------------------------------------
 * Author 				Date				Version  		Comments
 * --------------------------------------------------------------------------
 * wipro			12th July 2013 		     1.0             Initial Version
 
 *
 */


package com.lexmark.services.tests.junitTests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.portlet.MockActionRequest;
import org.springframework.mock.web.portlet.MockActionResponse;
import org.springframework.mock.web.portlet.MockPortletSession;
import org.springframework.mock.web.portlet.MockRenderRequest;
import org.springframework.mock.web.portlet.MockRenderResponse;
import org.springframework.mock.web.portlet.MockResourceRequest;
import org.springframework.mock.web.portlet.MockResourceResponse;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.Model;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.LocalizedPageCountNameContract;
import com.lexmark.contract.RequestListContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.Part;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.ServicesUser;
import com.lexmark.framework.exception.LGSDBException;
import com.lexmark.result.LocalizedPageCountNameResult;
import com.lexmark.result.LocalizedSiebelLOVListResult;
import com.lexmark.result.RequestListResult;
import com.lexmark.result.RequestResult;
import com.lexmark.result.ServiceRequestListResult;
import com.lexmark.service.impl.mock.GlobalServiceImpl;
import com.lexmark.service.impl.mock.ProductImageServiceImpl;
import com.lexmark.service.impl.mock.ServiceRequestLocaleImpl;
import com.lexmark.service.impl.mock.ServiceRequestServiceImpl;
import com.lexmark.service.impl.real.AmindAttachmentService;
import com.lexmark.service.impl.real.AmindRequestTypeService;
import com.lexmark.service.impl.real.jdbc.GeographyServiceImpl;
import com.lexmark.services.form.RequestDetailForm;
import com.lexmark.services.portlet.SharedPortletController;
import com.lexmark.services.portlet.common.CommonController;
import com.lexmark.services.portlet.common.HistoryController;

/**.
 * This Class is designed to test the history and details methods of HistoryController.java
 * @author wipro
 * 
 */

public class HistoryDetailsTest {
	private HistoryController target;
	private Model mockModel;
	private MockRenderRequest renderRequest;
	private MockRenderResponse renderResponse;
	private HashMap<String, String> serviceRequestsTypeMap = new HashMap<String, String>();
	private MockActionRequest actionrequest;
	private MockActionResponse actionresponse;
	private MockResourceRequest resourceReq;
	private MockResourceResponse resourceRes;
	private MockPortletSession mockSession;
	private Map<String, Object> ldapUserData;
	private Map<String, String> accDetails;
	private ServicesUser servicesUser;
	private Map<String, String> userAccessMapForSr = new HashMap<String, String>();
	private ListOfValues lov = new ListOfValues();
	private RequestDetailForm requestform = new RequestDetailForm();
	private List<String> USERROLES = new ArrayList<String>();
	private LocalizedSiebelLOVListResult lovRes = new LocalizedSiebelLOVListResult();
	private List<ListOfValues> lovList = new ArrayList<ListOfValues>();
	private RequestResult reqResult = new RequestResult();
	private Map<String, String> historyPageMap = new HashMap<String, String>();
	private RequestListContract downLoadContract;
	private Map<String, Object> filterCriteria = new HashMap<String, Object>();

	/**
	 * @throws Exception 
	 */
	@Before
	public void setUp() throws Exception {
		/**
		 * Autowiring and resource binding
		 **/
		target = new HistoryController();
		CommonController commoncontroller =new CommonController();
		
		ReflectionTestUtils.setField(commoncontroller, "globalService",new GlobalServiceImpl());
		ReflectionTestUtils.setField(commoncontroller,"sharedPortletController", new SharedPortletController());
		ReflectionTestUtils.setField(commoncontroller,"serviceRequestService", new ServiceRequestServiceImpl());
		ReflectionTestUtils.setField(commoncontroller,"geographyService", new GeographyServiceImpl());
		ReflectionTestUtils.setField(commoncontroller,"serviceRequestsTypeMap", new HashMap<String, String>());

		ReflectionTestUtils.setField(target, "commonController",commoncontroller);
		ReflectionTestUtils.setField(target, "globalService",new GlobalServiceImpl());
		ReflectionTestUtils.setField(target, "requestTypeService",new AmindRequestTypeService());
		ReflectionTestUtils.setField(target, "serviceRequestLocaleService",new ServiceRequestLocaleImpl());
		ReflectionTestUtils.setField(target, "attachmentService",new AmindAttachmentService());
		ReflectionTestUtils.setField(target, "sharedPortletController",new SharedPortletController());
		ReflectionTestUtils.setField(target, "serviceRequestService",new ServiceRequestServiceImpl());
		ReflectionTestUtils.setField(target, "productImageService",new ProductImageServiceImpl());
		/**
		 * Autowiring and resource binding end
		 **/

		mockModel = createMock(Model.class);
		renderRequest = new MockRenderRequest();
		renderResponse = new MockRenderResponse();
		actionrequest = new MockActionRequest();
		actionresponse = new MockActionResponse();
		resourceReq = new MockResourceRequest();
		resourceRes = new MockResourceResponse();
		mockSession = new MockPortletSession();
		renderRequest.addLocale(Locale.US);
		resourceReq.addLocale(Locale.US);
		ldapUserData = new HashMap<String, Object>();

		/**
		 * ldap Details Information
		 **/
		ldapUserData.put(LexmarkConstants.MDMID, "Mdmid");
		ldapUserData.put(LexmarkConstants.MDMLEVEL, "MdmLevel");
		ldapUserData.put(LexmarkConstants.CONTACTID, "ContactId");
		ldapUserData.put(LexmarkConstants.USERNUMBER, "User Name");
		ldapUserData.put(LexmarkConstants.FIRSTNAME, "First Name");
		ldapUserData.put(LexmarkConstants.LASTNAME, "Last Name");
		ldapUserData.put(LexmarkConstants.WORKPHONE, "0123456789");
		ldapUserData.put(LexmarkConstants.LANGUAGE, "English");
		ldapUserData.put(LexmarkConstants.COUNTRY, "india");
		ldapUserData.put(LexmarkConstants.EMAIL, "abc@xyx.com");
		ldapUserData.put(LexmarkConstants.USERSEGMENT, "xxx Usersegment");
		ldapUserData.put(LexmarkConstants.SHORTID, "sort id");
		ldapUserData.put(LexmarkConstants.COMPANYNAME, "company name");

		/**
		 * ldap Details Information End
		 **/

		servicesUser = new ServicesUser();
		servicesUser.setEmailAddress("xyz@abx.com");
		servicesUser.setUserNumber("123userNumber");
		accDetails = new HashMap<String, String>();
		/**
		 * Account Details Information
		 **/
		servicesUser.setMdmId("1234");
		servicesUser.setMdmLevel("L1");
		accDetails.put("accountId", "1-28FWPH");
		accDetails.put("accountName", "AT&T Corporation");
		accDetails.put("accountOrganization", "Wipro");
		accDetails.put("agreementId", "Aggreement12345");
		accDetails.put("agreementName", "agreementName122121");
		accDetails.put("country", "India");

		/**
		 * Account Details Information End
		 **/

		ServiceRequest serviceRequest = new ServiceRequest();
		AccountContact accountContact = new AccountContact();
		GenericAddress genericAddress = new GenericAddress();
		Part part = new Part();

		/**
		 * Generic Address and other page related details
		 **/

		genericAddress.setAddressId("addressid");
		genericAddress.setAddressLine1("line1");
		genericAddress.setAddressLine2("line2");
		genericAddress.setAddressLine3("line3");
		genericAddress.setAddressLine4("line4");
		genericAddress.setAddressName("addressname");
		genericAddress.setCity("city");
		genericAddress.setCountry("country");
		genericAddress.setNewAddressFlag("addressflag");
		genericAddress.setPhysicalLocation1("building");
		genericAddress.setPhysicalLocation2("floor");
		genericAddress.setPhysicalLocation3("office");
		genericAddress.setPostalCode("123456");
		genericAddress.setProvince("province");
		genericAddress.setStoreFrontName("storeFrontName");
		genericAddress.setUserFavorite(true);
		accountContact.setAddress(genericAddress);
		accountContact.setAlternatePhone("123124234234");
		accountContact.setContactId("122131");
		accountContact.setCountry("country");
		accountContact.setDepartment("department");
		accountContact.setEmailAddress("tanmoy@gmail.com");
		accountContact.setFirstName("firstName");
		accountContact.setLastName("lastName");
		accountContact.setManageContactFlag(true);
		accountContact.setMiddleName("middleName");
		accountContact.setNewContactFlag(true);
		accountContact.setShortId("ta123");
		accountContact.setWorkPhone("23423423423");
		accountContact.setUpdateContactFlag(true);
		accountContact.setUserFavorite(true);
		part.setPartId("12312312");
		part.setStatus("Authorized");
		part.setPartName("printer");
		part.setPartNumber("123456");
		List<Part> lp = new ArrayList<Part>();
		lp.add(part);
		serviceRequest.setParts(lp);
		serviceRequest.setRequestor(accountContact);
		serviceRequest.setPrimaryContact(accountContact);
		serviceRequest.setSecondaryContact(accountContact);
		lov.setId("123");
		lov.setLanguage("english");
		lov.setLanguageName("en");
		lov.setName("tanmoy");
		lov.setType("add");
		lov.setValue("change request");
		serviceRequest.setArea(lov);
		serviceRequest.setSubArea(lov);
		reqResult.setServiceRequest(serviceRequest);
		requestform.setServiceRequest(serviceRequest);
		userAccessMapForSr.put("partner", "partner");
		userAccessMapForSr.put("Customer", "Customer");
		USERROLES.add("partner");
		USERROLES.add("Customer");
		historyPageMap.put("ALL_REQUESTS", "ALL_REQUESTS");
		historyPageMap.put("SUPPLY_REQUESTS", "SUPPLY_REQUESTS");
		historyPageMap.put("CHANGE_REQUESTS", "CHANGE_REQUESTS");
		historyPageMap.put("SERVICE_REQUESTS", "SERVICE_REQUESTS");

		/**
		 * Generic Address and other page related details end
		 **/

		/**
		 * Binding attributes with session and requests
		 **/
		ReflectionTestUtils.setField(target, "historyPageMap", historyPageMap);
		mockSession.setAttribute(LexmarkConstants.LDAP_USER_DATA, ldapUserData,
				mockSession.APPLICATION_SCOPE);
		mockSession.setAttribute("accountCurrentDetails", accDetails,
				mockSession.APPLICATION_SCOPE);
		mockSession.setAttribute(LexmarkConstants.SERVICES_USER, servicesUser,
				mockSession.APPLICATION_SCOPE);
		mockSession.setAttribute("requestform", requestform,
				mockSession.APPLICATION_SCOPE);
		mockSession.setAttribute("userAccessMapForSr", userAccessMapForSr,
				mockSession.APPLICATION_SCOPE);
		mockSession.setAttribute("USERROLES", USERROLES,
				mockSession.APPLICATION_SCOPE);
		downLoadContract = new RequestListContract();
		downLoadContract.setMdmId("121312312");
		downLoadContract.setMdmLevel("siebel");
		downLoadContract.setAssetFavoriteFlag(false);
		downLoadContract.setFilterCriteria(filterCriteria);
		mockSession.setAttribute("downLoadContract", downLoadContract);
		actionrequest.setSession(mockSession);
		renderRequest.setSession(mockSession);
		resourceReq.setSession(mockSession);
		/**
		 * Binding attributes with session and requests end
		 **/

	}

	
	/**
	 * @param mockModelClass 
	 * @return Model 
	 */
	private Model createMock(Class<Model> mockModelClass) {

		return new Model() {

			@Override
			public Model mergeAttributes(Map<String, ?> arg0) {
				return null;
			}

			@Override
			public boolean containsAttribute(String arg0) {
				return false;
			}

			@Override
			public Map<String, Object> asMap() {
				return null;
			}

			@Override
			public Model addAttribute(String arg0, Object arg1) {
				return null;
			}

			@Override
			public Model addAttribute(Object arg0) {
				return null;
			}

			@Override
			public Model addAllAttributes(Map<String, ?> arg0) {
				return null;
			}

			@Override
			public Model addAllAttributes(Collection<?> arg0) {
				return null;
			}
		};
	}
	/**
	 * Destroying objects
	 **/
	/**
	 * @throws Exception 
	 */
	@After
	public void tearDown() throws Exception {

		mockModel = null;
		mockSession.clearAttributes();
		actionrequest = null;
		renderRequest = null;
		resourceReq = null;
	}
	
	/**
	 * Destroying objects end
	 **/
	
	
	/**
	 * Test Cases Start
	 * 
	 **/
	/**
	 * @throws Exception 
	 * @throws LGSDBException 
	 */
	@Test
	public void testShowCMHistoryPage() throws Exception, LGSDBException {
        
		renderRequest.setParameter("timeZoneOffset"," 7.7f");
		for (int i = 0; i < 4; i++) {
			if (i == 0) {
				renderRequest.setParameter("requestTypeStr", "SUPPLY_REQUESTS");
				String output = target.showCMHistoryPage(mockModel,
						renderRequest, renderResponse);
				assertEquals("OK", "lgsHistoryDetails/allRequestHistoryHome",
						output);
			} else if (i == 1) {
				renderRequest.setParameter("requestTypeStr", "CHANGE_REQUESTS");
				String output = target.showCMHistoryPage(mockModel,
						renderRequest, renderResponse);
				assertEquals("OK", "lgsHistoryDetails/allRequestHistoryHome",
						output);
			} else if (i == 2) {
				renderRequest
						.setParameter("requestTypeStr", "SERVICE_REQUESTS");
				String output = target.showCMHistoryPage(mockModel,
						renderRequest, renderResponse);
				assertEquals("OK", "lgsHistoryDetails/allRequestHistoryHome",
						output);
			} else if (i == 3) {
				renderRequest.setParameter("requestTypeStr", "ALL_REQUESTS");
				String output = target.showCMHistoryPage(mockModel,
						renderRequest, renderResponse);
				assertEquals("OK", "lgsHistoryDetails/allRequestHistoryHome",
						output);
			}
		}

		for (int i = 0; i < 4; i++) {
			if (i == 0) {
				renderRequest.setParameter("requestTypeStr", "SUPPLY_REQUESTS");
				renderRequest.setParameter("serviceRequestNumber", "123245345");
				renderRequest.setParameter("requestType","Consumables Management");
				String output = target.showCMHistoryPage(mockModel,
						renderRequest, renderResponse);
				assertEquals("OK", "lgsHistoryDetails/supplyRequestDetails",
						output);
			} else if (i == 1) {
				renderRequest.setParameter("requestTypeStr", "CHANGE_REQUESTS");
				renderRequest.setParameter("serviceRequestNumber", "123245345");
				renderRequest.setParameter("requestType", "Fleet Management");
				String output = target.showCMHistoryPage(mockModel,
						renderRequest, renderResponse);
				assertEquals("OK", "lgsHistoryDetails/changeRequestDetails",
						output);
			} else if (i == 2) {
				renderRequest
						.setParameter("requestTypeStr", "SERVICE_REQUESTS");
				renderRequest.setParameter("serviceRequestNumber", "123245345");
				String output = target.showCMHistoryPage(mockModel,
						renderRequest, renderResponse);
				//assertEquals("OK", "lgsHistoryDetails/supplyRequestDetails",
					//	output);
				assertEquals("OK", "lgsHistoryDetails/changeRequestDetails",
							output);
				
			} else if (i == 3) {
				renderRequest.setParameter("requestTypeStr", "ALL_REQUESTS");
				renderRequest.setParameter("serviceRequestNumber", "123245345");
				String output = target.showCMHistoryPage(mockModel,
						renderRequest, renderResponse);
				assertEquals("OK", "lgsHistoryDetails/changeRequestDetails",
						output);
			}
		}

	}

	/** 
	 * @throws Exception 
	 */
	@Test
	public void testShowHistoryGrid() throws Exception {

		for (int i = 0; i < 4; i++) {
			if (i == 0) {
				renderRequest
						.setParameter("requestTypePage", "SUPPLY_REQUESTS");
				renderRequest.setParameter("gridType", "SUPPLY_REQUESTS");
				String output = target.showHistoryGrid(mockModel,
						renderRequest, renderResponse);
				assertEquals("OK", "lgsHistoryDetails/SUPPLY_REQUESTS", output);
			} else if (i == 1) {
				renderRequest
						.setParameter("requestTypePage", "CHANGE_REQUESTS");
				renderRequest.setParameter("gridType", "CHANGE_REQUESTS");
				String output = target.showHistoryGrid(mockModel,
						renderRequest, renderResponse);
				assertEquals("OK", "lgsHistoryDetails/CHANGE_REQUESTS", output);
			} else if (i == 2) {
				renderRequest.setParameter("requestTypePage",
						"SERVICE_REQUESTS");
				renderRequest.setParameter("gridType", "SERVICE_REQUESTS");
				String output = target.showHistoryGrid(mockModel,
						renderRequest, renderResponse);
				assertEquals("OK", "serviceRequest/serviceRequestListPage",
						output);
			} else if (i == 3) {
				renderRequest.setParameter("requestTypePage", "ALL_REQUESTS");
				renderRequest.setParameter("gridType", "ALL_REQUESTS");
				String output = target.showHistoryGrid(mockModel,
						renderRequest, renderResponse);
				assertEquals("OK", "lgsHistoryDetails/ALL_REQUESTS", output);
			}
		}

	}

	/**
	 * @throws Exception 
	 */
	@Test
	public void testDownloadHistoryList() throws Exception {

		for (int i = 0; i < 4; i++) {
			if (i == 0) {
				resourceReq.setParameter("pageType", "SUPPLY_REQUESTS");
				resourceReq.setParameter("downloadType", "csv");
				target.downloadHistoryList(resourceReq, resourceRes, mockModel);
				
				resourceReq.setParameter("downloadType", "pdf");
				target.downloadHistoryList(resourceReq, resourceRes, mockModel);
				

			} else if (i == 1) {
				renderRequest.setParameter("downloadType", "CHANGE_REQUESTS");
				renderRequest.setParameter("pageType", "CHANGE_REQUESTS");
				target.downloadHistoryList(resourceReq, resourceRes, mockModel);
				
			} else if (i == 2) {
				renderRequest.setParameter("downloadType", "SERVICE_REQUESTS");
				renderRequest.setParameter("pageType", "SERVICE_REQUESTS");
				target.downloadHistoryList(resourceReq, resourceRes, mockModel);
			

			} else if (i == 3) {
				renderRequest.setParameter("downloadType", "ALL_REQUESTS");
				renderRequest.setParameter("pageType", "ALL_REQUESTS");
				target.downloadHistoryList(resourceReq, resourceRes, mockModel);
				

			}
		}

	}

	/**
	 * @throws Exception 
	 */
	@Test
	public void testRetrieveServiceRequestHistoryList() throws Exception {

		resourceReq.setParameter("assetRowId", "2");
		resourceReq.setParameter("accountRowId", "3");
		resourceReq.setParameter("serviceRequestNumber", "1-22323232");
		resourceReq.setParameter("timeZoneOffset", "5.5");
		target.retrieveServiceRequestHistoryList(resourceReq, resourceRes,
				mockModel);
	}

	/**
	 * @throws Exception 
	 */
	@Test
	public void testRetrieveServiceAssociatedRequestHistoryList()
			throws Exception {

		resourceReq.setParameter("serviceRequestNumber", "1-22323232");
		resourceReq.setParameter("timeZoneOffset"," 7.7f");
		target.retrieveServiceAssociatedRequestHistoryList(resourceReq,resourceRes, mockModel);
	}

	/**
	 * Test Cases End
	 **/
	

	
	/**
	 * @param contract 
	 * @return RequestListResult 
	 */
	public static RequestListResult retrieveRequestList(RequestListContract contract) {
		
		RequestListResult serviceRequestListResult = new RequestListResult();
		serviceRequestListResult.setTotalCount(7);
		//List<ServiceRequest> serviceRequests = serviceRequestListResult.getRequestList();
		return serviceRequestListResult;
	}


	/**
	 * @return ServiceRequestListResult 
	 */
	public ServiceRequestListResult retrieveAssociatedServiceRequestList() {
		
		ServiceRequest serviceRequest = new ServiceRequest();
		serviceRequest.setServiceRequestNumber("NUMBER");
		serviceRequest.setServiceRequestDate(new Date(07/16/2013));
		
		ListOfValues listOfValues = new ListOfValues();
		listOfValues.setValue("Consumables");
		serviceRequest.setServiceRequestType(listOfValues);
		
		List<ServiceRequest> serviceRequestList = new ArrayList<ServiceRequest>();
		serviceRequestList.add(serviceRequest);
		ServiceRequestListResult serviceRequestListResult = new  ServiceRequestListResult();
		serviceRequestListResult.setTotalCount(3);
		serviceRequestListResult.setServiceRequests(serviceRequestList);
		
		return serviceRequestListResult;
	}


	/**
	 * @param LOVMap 
	 * @return Map<String, String> 
	 */
	public static Map<String, String> retrieveLocalizedLOVMap(Map<String, String> LOVMap ) {
		Map<String, String> requestTypeLOVMap = new LinkedHashMap<String, String>();
		Map<String, String> requestAreaLOVMap = new LinkedHashMap<String, String>(); 
		if(LOVMap.equals(requestTypeLOVMap)){
		requestTypeLOVMap.put("requestTypeLOVMap", "requestTypeLOVMap");
		return requestTypeLOVMap;
		}
		else{
		requestAreaLOVMap.put("requestAreaLOVMap", "requestAreaLOVMap");
		return requestAreaLOVMap;
		}
	}
	
	/**
	 * @param contract 
	 * @return LocalizedPageCountNameResult 
	 */
	public LocalizedPageCountNameResult retrieveLocalizedPageCountName(
			LocalizedPageCountNameContract contract) {
		LocalizedPageCountNameResult result = new LocalizedPageCountNameResult();
		result.setLocalizedValue("LocalizedValue");
		return result;
	}
	
}
