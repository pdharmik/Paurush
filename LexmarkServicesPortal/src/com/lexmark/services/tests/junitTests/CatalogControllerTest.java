/**
 * Copyright@ 2013. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: CatalogControllerTest.java
 * Package     		: com.lexmark.services.tests.junitTests
 * Creation Date 	: 14th July 2013
 *
 * Modification History:
 *
 * --------------------------------------------------------------------------
 * Author 				Date				Version  		Comments
 * --------------------------------------------------------------------------
 * wipro			14th July 2013 		     1.0             Initial Version
 
 *
 */


package com.lexmark.services.tests.junitTests;

import static org.junit.Assert.assertEquals;

import java.beans.PropertyEditor;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.fileupload.FileItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.mock.web.portlet.MockActionRequest;
import org.springframework.mock.web.portlet.MockActionResponse;
import org.springframework.mock.web.portlet.MockPortletSession;
import org.springframework.mock.web.portlet.MockRenderRequest;
import org.springframework.mock.web.portlet.MockRenderResponse;
import org.springframework.mock.web.portlet.MockResourceRequest;
import org.springframework.mock.web.portlet.MockResourceResponse;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.RequestListContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.FileObject;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.OrderPart;
import com.lexmark.domain.Part;
import com.lexmark.domain.Price;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.ServicesUser;
import com.lexmark.result.LocalizedSiebelLOVListResult;
import com.lexmark.result.PriceResult;
import com.lexmark.result.RequestResult;
import com.lexmark.service.impl.mock.ContactServiceImpl;
import com.lexmark.service.impl.mock.GlobalServiceImpl;
import com.lexmark.service.impl.mock.OrderSuppliesCatalogServiceImpl;
import com.lexmark.service.impl.mock.ProductImageServiceImpl;
import com.lexmark.service.impl.mock.ServiceRequestServiceImpl;
import com.lexmark.service.impl.real.AmindRequestTypeService;
import com.lexmark.service.impl.real.jdbc.GeographyServiceImpl;
import com.lexmark.services.form.AttachmentForm;
import com.lexmark.services.form.CatalogDetailPageForm;
import com.lexmark.services.form.FileUploadForm;
import com.lexmark.services.form.RequestDetailForm;
import com.lexmark.services.form.validator.CatalogDetailPageFormValidator;
import com.lexmark.services.form.validator.CommonValidator;
import com.lexmark.services.form.validator.FileUploadFormValidator;
import com.lexmark.services.portlet.OrderSuppliesCatalogController;
import com.lexmark.services.portlet.SharedPortletController;
import com.lexmark.services.portlet.common.CommonController;
import com.lexmark.services.portlet.common.ContactController;
import com.lexmark.services.webService.CreateConsumableRequestImpl;
import com.lexmark.services.webService.om.RetrievePriceServiceImpl;
import com.lexmark.services.webService.om.RetrieveTaxServiceImpl;

/**.
 * This Class is designed to test the Asset Catalog details methods of OrderSuppliesCatalogController.java
 * @author wipro
 * 
 */

public class CatalogControllerTest{
	private OrderSuppliesCatalogController target;
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
	private Map<String, String> userAccessMapForSr = new HashedMap();
	private ListOfValues lov = new ListOfValues();
	private RequestDetailForm requestform = new RequestDetailForm();
	private List<String> USERROLES = new ArrayList<String>();
	private LocalizedSiebelLOVListResult lovRes = new LocalizedSiebelLOVListResult();
	private List<ListOfValues> lovList = new ArrayList<ListOfValues>();
	private RequestResult reqResult = new RequestResult();
	private RequestListContract downLoadContract;
	private Map<String, Object> filterCriteria = new HashMap<String, Object>();
	private BindingResult br;
	List<Attachment> attachment ;
	FileItem fileItem;
	/**
	 * @throws Exception 
	 */
	@Before
	public void setUp() throws Exception {
		/**
		 * Autowiring and resource binding
		 **/
		target = new OrderSuppliesCatalogController();
		CommonController commoncontroller =new CommonController();
		ContactController commonContact = new ContactController();
		ReflectionTestUtils.setField(commonContact, "globalService",new GlobalServiceImpl());
		ReflectionTestUtils.setField(commonContact, "commonValidator",new CommonValidator());
		ReflectionTestUtils.setField(commonContact, "contactService",new ContactServiceImpl());
		
		ReflectionTestUtils.setField(commoncontroller, "globalService",new GlobalServiceImpl());
		ReflectionTestUtils.setField(commoncontroller,"sharedPortletController", new SharedPortletController());
		ReflectionTestUtils.setField(commoncontroller,"serviceRequestService", new ServiceRequestServiceImpl());
		ReflectionTestUtils.setField(commoncontroller,"geographyService", new GeographyServiceImpl());
		ReflectionTestUtils.setField(commoncontroller,"serviceRequestsTypeMap", new HashMap<String, String>());
		ReflectionTestUtils.setField(commoncontroller,"contactController", commonContact);
		ReflectionTestUtils.setField(target, "commonController",commoncontroller);
		ReflectionTestUtils.setField(target, "globalService",new GlobalServiceImpl());
		ReflectionTestUtils.setField(target, "sharedPortletController",new SharedPortletController());
		ReflectionTestUtils.setField(target, "productImageService",new ProductImageServiceImpl());
		ReflectionTestUtils.setField(target, "orderSuppliesCatalogService",new OrderSuppliesCatalogServiceImpl());
		ReflectionTestUtils.setField(target, "createConsumableRequest",new CreateConsumableRequestImpl());
		ReflectionTestUtils.setField(target, "fileUploadFormValidator",new FileUploadFormValidator());
		ReflectionTestUtils.setField(target, "catalogDetailPageFormValidator",new CatalogDetailPageFormValidator());
		ReflectionTestUtils.setField(target, "serviceRequestService",new ServiceRequestServiceImpl());
		ReflectionTestUtils.setField(target, "requestTypeService",new AmindRequestTypeService());
		ReflectionTestUtils.setField(target, "retrievePriceService",new RetrievePriceServiceImpl());
		ReflectionTestUtils.setField(target, "retrieveTaxService",new RetrieveTaxServiceImpl());
		
		
		/**
		 * Autowiring and resource binding end
		 **/

		mockModel = createMock(Model.class);
		br= new BindingResult() {
			
			@Override
			public void setNestedPath(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void rejectValue(String arg0, String arg1, Object[] arg2, String arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void rejectValue(String arg0, String arg1, String arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void rejectValue(String arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void reject(String arg0, Object[] arg1, String arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void reject(String arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void reject(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void pushNestedPath(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void popNestedPath() throws IllegalStateException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean hasGlobalErrors() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean hasFieldErrors(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean hasFieldErrors() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean hasErrors() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public String getObjectName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getNestedPath() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public List<ObjectError> getGlobalErrors() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getGlobalErrorCount() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public ObjectError getGlobalError() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object getFieldValue(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Class getFieldType(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public List<FieldError> getFieldErrors(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public List<FieldError> getFieldErrors() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getFieldErrorCount(String arg0) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public int getFieldErrorCount() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public FieldError getFieldError(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public FieldError getFieldError() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getErrorCount() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public List<ObjectError> getAllErrors() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void addAllErrors(Errors arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public String[] resolveMessageCodes(String arg0, String arg1) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void recordSuppressedField(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public Object getTarget() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String[] getSuppressedFields() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object getRawFieldValue(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public PropertyEditorRegistry getPropertyEditorRegistry() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Map<String, Object> getModel() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public PropertyEditor findEditor(String arg0, Class arg1) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void addError(ObjectError arg0) {
				// TODO Auto-generated method stub
				
			}
		};
		renderRequest = new MockRenderRequest();
		renderResponse = new MockRenderResponse();
		actionrequest = new MockActionRequest();
		actionresponse = new MockActionResponse();
		resourceReq = new MockResourceRequest();
		resourceRes = new MockResourceResponse();
		mockSession = new MockPortletSession();
		renderRequest.addLocale(Locale.US);
		resourceReq.addLocale(Locale.US);
		ldapUserData = new HashedMap();

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
		accDetails = new HashedMap();
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
		accountContact.setEmailAddress("wipro@gmail.com");
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
		attachment = new ArrayList<Attachment>();

		/**
		 * Generic Address and other page related details end
		 **/

		/**
		 * Binding attributes with session and requests
		 **/
		
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
	 **/
	/**
	 * @throws Exception 
	 */
	@Test
	public void testShowCatalogOrderList() throws Exception {
		renderRequest.setParameter("requestNumber", "1-1234567890");
		renderRequest.setParameter("relatedServiceRequestNumber", "1-9876543210");
		renderRequest.setParameter("pageFrom", "update");
		
		ServiceRequest serviceRequest = new ServiceRequest();
		serviceRequest.setAccountId("12345");
		serviceRequest.setAccountName("name");
		AccountContact requestor = new AccountContact();
		requestor.setContactId("123456");
		if(requestor == null){
			 
		}
		serviceRequest.setRequestor(requestor);
		AccountContact primaryContact = new AccountContact();
		primaryContact.setContactId("123456789");
		if(primaryContact == null){
			 
		}
		serviceRequest.setPrimaryContact(primaryContact);
		GenericAddress serviceAddress = new GenericAddress();
		serviceAddress.setAddressId("sgfdu zsjhgfj");
		serviceRequest.setServiceAddress(serviceAddress);
        List<ServiceRequest> service = new ArrayList<ServiceRequest>();
        service.add(serviceRequest);
        mockSession.setAttribute("serviceRequest", serviceRequest);
        renderRequest.setSession(mockSession);
    	
		for(int i =0;i<2;i++){
			if(i==0){
			renderRequest.setParameter("reqStatus", "Inprocess");
			String output= target.showCatalogOrderList(renderRequest, renderResponse, mockModel);
			assertEquals("OK", "ordermanagement/catalogOrder/viewCatalogOrderList", output);
			}
			if(i==1){
				renderRequest.setParameter("reqStatus", "draft");
				String output= target.showCatalogOrderList(renderRequest, renderResponse, mockModel);
				assertEquals("OK", "ordermanagement/catalogOrder/catalogDetail", output);
			}
		}
		
	}
	
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void testGetProductType() throws Exception {
		Map<String,String> catalogDetails= new HashMap<String, String>();
		catalogDetails.put("soldToNumber", "123123");
		catalogDetails.put("paymentType", "pay now");
		catalogDetails.put("billToAddress", "21st jump street");
		mockSession.setAttribute("catalogCurrentDetails", catalogDetails,mockSession.APPLICATION_SCOPE);
		mockSession.setAttribute("agreementId", "123456");
		resourceReq.setSession(mockSession);
		target.getProductType(resourceReq, resourceRes);
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void testGetProductModel() throws Exception {
		resourceReq.setParameter("productType", "Asset");
		mockSession.setAttribute("agreementId", "123456");
		resourceReq.setSession(mockSession);
		target.getProductModel(resourceReq, resourceRes);
	}
	

	/**
	 * @throws Exception 
	 */
	@Test
	public void testGetPartType() throws Exception {
		resourceReq.setParameter("productType", "Asset");
		resourceReq.setParameter("productModel", "D1000");
		mockSession.setAttribute("agreementId", "123456");
		accDetails.put("splitterFlag", "true");
		Map<String,String> catalogDetails= new HashMap<String, String>();
		catalogDetails.put("soldToNumber", "123123");
		catalogDetails.put("paymentType", "pay now");
		catalogDetails.put("billToAddress", "21st jump street");
		mockSession.setAttribute("catalogCurrentDetails", catalogDetails,mockSession.APPLICATION_SCOPE);
		mockSession.setAttribute("accountCurrentDetails", accDetails,mockSession.APPLICATION_SCOPE);
		resourceReq.setSession(mockSession);
		target.getPartType(resourceReq, resourceRes);
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void testShowCatalogSelectionPage() throws Exception {
		
		String output =target.showCatalogSelectionPage(mockModel, renderRequest,renderResponse );
		assertEquals("OK", "ordermanagement/catalogOrder/viewCatalogOrderList", output);
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void testRetriveCatalogList() throws Exception {
		Map<String,String> catalogDetails= new HashMap<String, String>();
		Map<String,Boolean> catalogFinalFlags= new HashMap<String, Boolean>();
		catalogDetails.put("soldToNumber", "123123");
		catalogDetails.put("paymentType", "pay now");
		catalogDetails.put("billToAddress", "21st jump street");
		accDetails.put("splitterFlag", "true");
		catalogFinalFlags.put("finalShowPriceFlag", true);
		resourceReq.setParameter("partNumber", "12345");
		resourceReq.setParameter("partType", "printer");
		resourceReq.setParameter("productModel", "d-1000");
		resourceReq.setParameter("productType", "print-d-100");
		mockSession.setAttribute("agreementId", "123456");
		mockSession.setAttribute("catalogFinalFlags", catalogFinalFlags,mockSession.APPLICATION_SCOPE);
		mockSession.setAttribute("catalogCurrentDetails", catalogDetails,mockSession.APPLICATION_SCOPE);
		mockSession.setAttribute("accountCurrentDetails", accDetails,mockSession.APPLICATION_SCOPE);
		resourceReq.setSession(mockSession);
		

		Price price = new Price();
		price.setPrice("67");
		List<Price> priceInputList = new ArrayList<Price>();
		PriceResult priceResult = new PriceResult();
		priceResult.setPriceOutputList(priceInputList);
		mockSession.setAttribute("priceResult", priceResult);
        renderRequest.setSession(mockSession);
        
		target.retriveCatalogList(resourceReq,resourceRes );
		
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void testAddToCart() throws Exception {
		
		resourceReq.setParameter("partNumber", "12345");
		resourceReq.setParameter("partType", "printer");
		resourceReq.setParameter("partDesc", "vry good printer");
		resourceReq.setParameter("yield", "100");
		resourceReq.setParameter("partQty", "2");
		resourceReq.setParameter("catalogId", "123445");
		resourceReq.setParameter("consumableType", "consumableType");
		resourceReq.setParameter("supplyId", "123452244");
		resourceReq.setParameter("productId", "56756");
		resourceReq.setParameter("proModel", "Lexmark");
		resourceReq.setParameter("price", "110000");
		resourceReq.setParameter("currency", "USD");
		
		target.addToCart(resourceReq,resourceRes,mockModel);
		
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void testShowCatalogDetailPage() throws Exception {
		
		List<OrderPart> catalogOrderListToSession = new ArrayList<OrderPart>();
		OrderPart op = new OrderPart();
		op.setPartNumber("12345");
		op.setPartDesc("nice part");
		op.setPartType("printer");
		op.setYield("1000");
		op.setModel("d-1000");
		op.setOrderQuantity("2");
		op.setCatalogId("1-22332");
		op.setConsumableType("asset");
		op.setSupplyId("12678678");
		op.setProductId("9808758");
		catalogOrderListToSession.add(op);
		mockSession.setAttribute("catalogOrderListToSession", catalogOrderListToSession);
		accDetails.put("requestExpedite", "true");
		mockSession.setAttribute("accountCurrentDetails", accDetails,mockSession.APPLICATION_SCOPE);
		renderRequest.setSession(mockSession);
		String output= target.showCatalogDetailPage(renderRequest,renderResponse,mockModel);
		assertEquals("OK", "ordermanagement/catalogOrder/catalogDetail", output);
		
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void testSubmitCatalogOrder() throws Exception {
		
		CatalogDetailPageForm cdf = new CatalogDetailPageForm();
		AttachmentForm af= new AttachmentForm();
		ObjectError oe = new ObjectError("error", "lots of error");
		br.addError(oe)	;
		target.submitCatalogOrder(renderRequest,renderResponse,cdf,br,af,mockModel);
		
	}
	/**
	 * @throws Exception 
	 */
	@Test
	public void testShowCartViewPage() throws Exception {
		
		List<OrderPart> catalogOrderListToSession = new ArrayList<OrderPart>();
		OrderPart op = new OrderPart();
		op.setPartNumber("12345");
		op.setPartDesc("nice part");
		op.setPartType("printer");
		op.setYield("1000");
		op.setModel("d-1000");
		op.setOrderQuantity("2");
		op.setCatalogId("1-22332");
		op.setConsumableType("asset");
		op.setSupplyId("12678678");
		op.setProductId("9808758");
		catalogOrderListToSession.add(op);
		mockSession.setAttribute("catalogOrderListToSession", catalogOrderListToSession);
		renderRequest.setSession(mockSession);
		String output= target.showCartViewPage(renderRequest,renderResponse,mockModel);
		assertEquals("OK", "ordermanagement/catalogOrder/cartView", output);
		
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void testUpdateCartViewURL() throws Exception {
		resourceReq.setParameter("orderQuantity", "12");
		resourceReq.setParameter("catalogId", "12242122");
		List<OrderPart> cartViewCatalogOrderListToSession = new ArrayList<OrderPart>();
		
		OrderPart orderPart = new OrderPart();
			orderPart = generateOrderPart();
			cartViewCatalogOrderListToSession.add(orderPart);
		mockSession.setAttribute("cartViewCatalogOrderListToSession", cartViewCatalogOrderListToSession);
		
		for(int i = 0;i<2;i++){
			if(i==0){
				resourceReq.setParameter("jobType", "update");	
				target.updateCartViewURL(resourceReq,resourceRes);
			}
			if(i==1){
				resourceReq.setParameter("jobType", "developer");	
				target.updateCartViewURL(resourceReq,resourceRes);
			}
		}
	}
	
	
	/** 
	 * @throws Exception 
	 */
	@Test
	public void testUpdateCompleteCartView() throws Exception {
		resourceReq.setParameter("orderQuantity", "12");
		resourceReq.setParameter("catalogId", "12242122");
		List<OrderPart> cartViewCatalogOrderListToSession = new ArrayList<OrderPart>();
		
		OrderPart orderPart = new OrderPart();
			orderPart = generateOrderPart();
			cartViewCatalogOrderListToSession.add(orderPart);
			Map<String,Boolean> catalogFinalFlags= new HashMap<String, Boolean>();
			catalogFinalFlags.put("finalShowPriceFlag", true);
			accDetails.put("splitterFlag", "true");
			mockSession.setAttribute("accountCurrentDetails", accDetails,mockSession.APPLICATION_SCOPE);
			mockSession.setAttribute("cartViewCatalogOrderListToSession", cartViewCatalogOrderListToSession);
			mockSession.setAttribute("catalogFinalFlags", catalogFinalFlags,mockSession.APPLICATION_SCOPE);
				resourceReq.setParameter("jobType", "developer");	
				target.updateCompleteCartView(resourceReq,resourceRes);
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void testGoToAddCatalog() throws Exception {
		CatalogDetailPageForm cfd = new CatalogDetailPageForm();
		AttachmentForm af= new AttachmentForm();
		ServiceRequest sr = generateSR();
		cfd.setServiceRequest(sr);
		String output=target.goToAddCatalog(renderRequest,renderResponse,cfd,af, mockModel);
		assertEquals("OK", "ordermanagement/catalogOrder/catalogDetail", output);
		
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void testDeleteFromCatalogDetail() throws Exception {
		resourceReq.setParameter("catalogId", "122323");
		List<OrderPart> catalogOrderListToSession = new ArrayList<OrderPart>();
		OrderPart op = new OrderPart();
		op.setPartNumber("12345");
		op.setPartDesc("nice part");
		op.setPartType("printer");
		op.setYield("1000");
		op.setPartQuantity("2");
		op.setDescription("nice parts");
		op.setModel("d-1000");
		op.setOrderQuantity("2");
		op.setCatalogId("1-22332");
		op.setConsumableType("asset");
		op.setSupplyId("12678678");
		op.setProductId("9808758");
		catalogOrderListToSession.add(op);
		mockSession.setAttribute("catalogOrderListToSession", catalogOrderListToSession);
		target.deleteFromCatalogDetail(resourceReq,resourceRes);
		
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void testAddAttachment() throws Exception {
		
	   //String TEST_FILE = "C:\\Documents and Settings\\tanmoy\\Desktop\\test2.xls";
       //MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
       //MockMultipartHttpServletRequest mockMultipartHttpServletRequest = (MockMultipartHttpServletRequest)request;
       //MultipartFile multipartFile = (MockMultipartFile) mockMultipartHttpServletRequest.getFile(TEST_FILE);
       //MockMultipartFile multipartFile1 = new MockMultipartFile("Mock", "test2.xls", "xls", new byte[]{12});
		
        String path = "C:\\Documents and Settings\\tanmoy\\Desktop";
		CatalogDetailPageForm cfd = new CatalogDetailPageForm();
		Map<String, FileObject> attachmentFileMap = new HashMap<String, FileObject>();
		Map<String, FileObject> fileMap = new HashMap<String, FileObject>();
		
		FileObject fo= new FileObject();
		fo.setDisplayFileName("mockfile");
		fo.setFileName("Mockfile");
		fo.setFileSize("2");
		fo.setFileSizeInBytes("2048");
		fo.setUploadDate("12/7/2013");
		ServiceRequest sr = generateSR();
		cfd.setServiceRequest(sr);
		FileUploadForm file = new FileUploadForm();
		file.setFileCount(1);
		file.setPageType("xls");
		file.setTotalFileSize("12");
		fileItem = createMockFileitem(FileItem.class);
		fileItem.setFieldName("test");
		fileItem.setFormField(true);
		CommonsMultipartFile cmf= new CommonsMultipartFile(fileItem); 
		file.setFileData(cmf);
		attachmentFileMap.put("attachmentFileMap", fo);
		file.setAttachmentFileMap(attachmentFileMap);
		attachmentFileMap.put("fileMap", fo);
		file.setFileMap(fileMap);
		ObjectError oe = new ObjectError("error", "lots of error");
		br.addError(oe)	;
		
		Attachment attach = new Attachment();
		attach.setActualFileName("file1");
		attach.setAttachmentName("demo File");
		attach.setDescription("demo");
		attach.setStatus("completed");
		attachment.add(attach);
		mockSession.setAttribute("attachmentList", attachment);
		//target.addAttachment(mockModel,actionrequest,actionresponse,cfd,file,br);
		
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void testRemoveAttachment() throws Exception {
		CatalogDetailPageForm cfd = new CatalogDetailPageForm();
		ServiceRequest sr = generateSR();
		cfd.setServiceRequest(sr);
		Attachment attach = new Attachment();
		attach.setActualFileName("file1");
		attach.setAttachmentName("demo File");
		attach.setDescription("demo");
		attach.setStatus("completed");
		attachment.add(attach);
		mockSession.setAttribute("attachmentList", attachment);
		//target.removeAttachment(actionrequest,actionresponse,"test.xls",cfd,mockModel);
		
	}
	/**
	 * @param class1 
	 * @return FileItem 
	 */
	private FileItem createMockFileitem(Class<FileItem> class1) {
		return new FileItem() {
			
			@Override
			public void write(File arg0) throws Exception {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setFormField(boolean arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setFieldName(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isInMemory() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isFormField() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public String getString(String arg0) throws UnsupportedEncodingException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getString() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public long getSize() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public OutputStream getOutputStream() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public InputStream getInputStream() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getFieldName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getContentType() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public byte[] get() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void delete() {
				// TODO Auto-generated method stub
				
			}
		};
	}



	/**
	 * Test Cases End
	 **/
	
	/**
	 * @return ServiceRequest 
	 */
	private ServiceRequest generateSR() {
		ServiceRequest sr = new ServiceRequest(); 
		AccountContact primary = new AccountContact();
		AccountContact secondary = new AccountContact();
		
		primary.setFirstName("Bill");
		primary.setLastName("Gates");
		primary.setWorkPhone("1234456665");
		primary.setEmailAddress("billi@micro.com");
		primary.setContactId("12344");
		
		secondary.setFirstName("Bill2");
		secondary.setLastName("Gates2");
		secondary.setWorkPhone("12344566652222");
		secondary.setEmailAddress("billi22@micro.com");
		secondary.setContactId("12322244");
		
		sr.setPrimaryContact(primary);
		sr.setSecondaryContact(secondary);
		return sr;
	}



	/**
	 * Test Cases Utility methods to populate data 
	 **/
	/**
	 * @return RequestResult 
	 */
	public RequestResult mockRetrieveSupplyRequestDetail(){
		ServiceRequest sr = new ServiceRequest();
		sr.setServiceRequestNumber("1-23456789");
		sr.setAccountId("12345");
		sr.setAccountName("lexmark");		
		RequestResult reqRes = new RequestResult();
		reqRes.setServiceRequest(sr);
		return reqRes;
	}
	
	/**
	 * @return PriceResult 
	 */
	public PriceResult mockPriceResult(){
		PriceResult pr = new PriceResult();
		Price price = new Price();
		List<Price>priceOutputList = new ArrayList<Price>();
		price.setContractLineItemId("c-122344");
		price.setCurrency("USD");
		price.setPartNumber("123445");
		price.setPrice("1000");
		price.setTax("100");
		priceOutputList.add(price);
		pr.setPriceOutputList(priceOutputList);
		
		return pr;
	}
	
	/**
	 * @return OrderPart 
	 */
	private OrderPart generateOrderPart(){
		OrderPart orderPart = new OrderPart();
		orderPart.setCatalogId("12121212");
		orderPart.setPartNumber("2222222");
		orderPart.setPartDesc("printer working good");
		orderPart.setPartType("printer");
		orderPart.setYield("1000");
		orderPart.setPartQuantity("2");
		orderPart.setModel("D-1000");
		orderPart.setSupplyId("102902920");
		orderPart.setProductId("123434");
		orderPart.setUnitPrice("122");
		orderPart.setCurrency("USD");
		orderPart.setConsumableType("asset");
		return orderPart;
	}
	
	
	/**
	 * Test Cases Utility methods to populate data end
	 **/
	
}
