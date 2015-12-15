/**
 * Copyright@ 2013. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: OrderSuppliesAssetOrderControllerTest.java
 * Package     		: com.lexmark.services.tests.junitTests
 * Creation Date 	: 14th July 2013
 *
 * Modification History:
 *
 * -----------------------------------------------------------------------------------------------
 * Author 		                    		Date				Version  		Comments
 * -----------------------------------------------------------------------------------------------
 * wipro			16th July 2013 		     1.0             Initial Version
 
 *
 */

package com.lexmark.services.tests.junitTests;

import static org.junit.Assert.assertEquals;

import java.beans.PropertyEditor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.mock.web.portlet.MockActionRequest;
import org.springframework.mock.web.portlet.MockActionResponse;
import org.springframework.mock.web.portlet.MockMultipartActionRequest;
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
import com.lexmark.contract.AssetListContract;
import com.lexmark.contract.LocalizedPageCountNameContract;
import com.lexmark.contract.PriceContract;
import com.lexmark.contract.TaxContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Asset;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.Part;
import com.lexmark.domain.Price;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.ServicesUser;
import com.lexmark.result.LocalizedPageCountNameResult;
import com.lexmark.result.PriceResult;
import com.lexmark.result.UserGridSettingResult;
import com.lexmark.service.impl.mock.ContactServiceImpl;
import com.lexmark.service.impl.mock.GlobalServiceImpl;
import com.lexmark.service.impl.mock.OrderSuppliesAssetServiceImpl;
import com.lexmark.services.form.AssetDetailPageForm;
import com.lexmark.services.form.AttachmentForm;
import com.lexmark.services.form.FileUploadForm;
import com.lexmark.services.form.validator.CommonValidator;
import com.lexmark.services.portlet.OrderSuppliesAssetOrderController;
import com.lexmark.services.portlet.SharedPortletController;
import com.lexmark.services.portlet.common.CommonController;
import com.lexmark.services.portlet.common.ContactController;
import com.lexmark.services.webService.om.RetrievePriceServiceImpl;

/**.
 * This Class is designed to test the methods of OrderSuppliesAssetOrderController.java
 * @author wipro
 * 
 */

public class OrderSuppliesAssetOrderControllerTest {
	
	private MockResourceRequest resourceRequest = new MockResourceRequest();
	private MockResourceResponse resourceResponse = new MockResourceResponse();
	private MockRenderRequest renderRequest = new MockRenderRequest();
	private MockRenderResponse renderResponse = new MockRenderResponse();
	private Model model;
	private MockActionRequest actionRequest = new MockActionRequest();
	private MockActionResponse actionResponse = new MockActionResponse();
	private BindingResult BindingResult;
	
	private MockPortletSession mockSession;
	private OrderSuppliesAssetOrderController controller;
	AssetDetailPageForm assetDetailPageForm;
	FileUploadForm fileUploadForm;
	
	
	private String assetId;
	private String favoriteAssetId;
	private boolean favoriteFlag;
	private AssetDetailPageForm reviewAssetOrderForm;
	private Asset device;
	private Map<String, Object> ldapUserData;
	ServicesUser servicesUser;

	/**
	 * @throws Exception 
	 */
	@Before
	public void setUp() throws Exception {
	    /**
		 * Autowiring and resource binding
		 **/
			controller = new OrderSuppliesAssetOrderController();
			ContactController contactController= new ContactController();
			CommonController commonController=new CommonController();
			ReflectionTestUtils.setField(contactController, "globalService",new GlobalServiceImpl());
			ReflectionTestUtils.setField(contactController, "contactService",new ContactServiceImpl());
			ReflectionTestUtils.setField(contactController, "commonValidator",new CommonValidator());
			ReflectionTestUtils.setField(commonController, "contactController",contactController);
			ReflectionTestUtils.setField(controller, "retrievePriceService",new RetrievePriceServiceImpl());
			ReflectionTestUtils.setField(controller, "commonController",commonController);
			ReflectionTestUtils.setField(controller, "orderSuppliesAssetService",new OrderSuppliesAssetServiceImpl());
			ReflectionTestUtils.setField(controller, "sharedPortletController",new SharedPortletController());
		/**
		 * Autowiring and resource binding end
		**/
		
			mockSession=new MockPortletSession();
			model = createMock(Model.class);
			BindingResult=createMockBindingResult(BindingResult.class);
			
			reviewAssetOrderForm = new AssetDetailPageForm();
			assetDetailPageForm = new AssetDetailPageForm();			
			ServiceRequest serviceRequest = new ServiceRequest();
			
			AccountContact accountContact = new AccountContact();
		/**
		  * Account Contact Information
		 **/
			accountContact.setFirstName("mock First Name");
			accountContact.setLastName("mock Last Name");
			accountContact.setWorkPhone("123456789");
			accountContact.setEmailAddress("mpsone@mpscust.com");
			accountContact.setContactId("0124987");
		/**
		  * Account Contact Information End
		 **/
			serviceRequest.setCostCenter("CostCenter");
			serviceRequest.setPrimaryContact(accountContact);
			serviceRequest.setSecondaryContact(accountContact);
			
			PageCounts pageCounts = new PageCounts();
			pageCounts.setBothValueBlank(true);
			pageCounts.setCount("123");
			pageCounts.setDate("15/07/2013");
			pageCounts.setName("mock Page Count Name");
			pageCounts.setSiebelName("mock SiebelName");
			List<PageCounts> pg = new ArrayList<PageCounts>();
			pg.add(pageCounts);
			
			assetDetailPageForm.setPageSubmitType("confirmOrderRequest");
			assetDetailPageForm.setAttachmentDescription("AttachmentDescription");
			assetDetailPageForm.setServiceRequest(serviceRequest);
			assetDetailPageForm.setPageCountsList(pg);
			
			
			String TEST_FILE = "C:\\Users\\pranjit\\Desktop\\new2.txt";
			MockMultipartActionRequest request = new MockMultipartActionRequest();
			MockMultipartActionRequest mockMultipartActionRequest = (MockMultipartActionRequest)request;
			CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) mockMultipartActionRequest.getFile(TEST_FILE);
			assetDetailPageForm.setFileData(commonsMultipartFile);
			
			fileUploadForm = new FileUploadForm();
			   
			    mockSession=new MockPortletSession();
				renderRequest.addLocale(Locale.US);
				ldapUserData=new HashMap<String, Object>();
		/**
		 * ldap Details Information
		 **/
				ldapUserData.put(LexmarkConstants.MDMID, "xxx Mdmid");
				ldapUserData.put(LexmarkConstants.MDMLEVEL, "Siebel");
				ldapUserData.put(LexmarkConstants.CONTACTID, "xxx ContactId");
				ldapUserData.put(LexmarkConstants.USERNUMBER, "xxx User Name");
				ldapUserData.put(LexmarkConstants.FIRSTNAME, "xxx First Name");
				ldapUserData.put(LexmarkConstants.LASTNAME, "xxx Last Name");
				ldapUserData.put(LexmarkConstants.WORKPHONE, "0123456789");
				ldapUserData.put(LexmarkConstants.LANGUAGE, "English");
				ldapUserData.put(LexmarkConstants.COUNTRY, "india");
				ldapUserData.put(LexmarkConstants.EMAIL, "abc@xyx.com");
				ldapUserData.put(LexmarkConstants.USERSEGMENT, "xxx Usersegment");
				ldapUserData.put(LexmarkConstants.SHORTID, "xxx sort id");
				ldapUserData.put(LexmarkConstants.COMPANYNAME, "xxx company name");
		/**
		 * ldap Details Information End
		 **/
		 
		/**
		 * Binding attributes with session and requests
		 **/
				servicesUser=new ServicesUser();
				servicesUser.setEmailAddress("xxx@abx.com");
				servicesUser.setUserNumber("123userNumber");
				servicesUser.setMdmId("1234");
				servicesUser.setMdmLevel("L1");
				
				mockSession.setAttribute(LexmarkConstants.LDAP_USER_DATA, ldapUserData, mockSession.APPLICATION_SCOPE);
				mockSession.setAttribute(LexmarkConstants.SERVICES_USER,servicesUser,mockSession.APPLICATION_SCOPE);
				
				renderRequest.setSession(mockSession);
		/**
		 * Binding attributes with session and requests end
		 **/ 
			
	}
	
	/**
	 * @param class1 
	 * @return BindingResult 
	 */
	private BindingResult createMockBindingResult(Class<BindingResult> class1) {
		return new BindingResult() {
			
			@Override
			public void setNestedPath(String arg0) {
			
				
			}
			
			@Override
			public void rejectValue(String arg0, String arg1, Object[] arg2, String arg3) {
			
				
			}
			
			@Override
			public void rejectValue(String arg0, String arg1, String arg2) {
				
			}
			
			@Override
			public void rejectValue(String arg0, String arg1) {
			}
			
			@Override
			public void reject(String arg0, Object[] arg1, String arg2) {
				}
			
			@Override
			public void reject(String arg0, String arg1) {
				}
			
			@Override
			public void reject(String arg0) {
				}
			
			@Override
			public void pushNestedPath(String arg0) {
				}
			
			@Override
			public void popNestedPath() throws IllegalStateException {
				}
			
			@Override
			public boolean hasGlobalErrors() {
				return false;
			}
			
			@Override
			public boolean hasFieldErrors(String arg0) {
				return false;
			}
			
			@Override
			public boolean hasFieldErrors() {
				return false;
			}
			
			@Override
			public boolean hasErrors() {
				return false;
			}
			
			@Override
			public String getObjectName() {
				return null;
			}
			
			@Override
			public String getNestedPath() {
				return null;
			}
			
			@Override
			public List<ObjectError> getGlobalErrors() {
				return null;
			}
			
			@Override
			public int getGlobalErrorCount() {
				return 0;
			}
			
			@Override
			public ObjectError getGlobalError() {
				return null;
			}
			
			@Override
			public Object getFieldValue(String arg0) {
				return null;
			}
			
			@Override
			public Class getFieldType(String arg0) {
				return null;
			}
			
			@Override
			public List<FieldError> getFieldErrors(String arg0) {
				return null;
			}
			
			@Override
			public List<FieldError> getFieldErrors() {
				return null;
			}
			
			@Override
			public int getFieldErrorCount(String arg0) {
				return 0;
			}
			
			@Override
			public int getFieldErrorCount() {
				return 0;
			}
			
			@Override
			public FieldError getFieldError(String arg0) {
				return null;
			}
			
			@Override
			public FieldError getFieldError() {
				return null;
			}
			
			@Override
			public int getErrorCount() {
				return 0;
			}
			
			@Override
			public List<ObjectError> getAllErrors() {
				return null;
			}
			
			@Override
			public void addAllErrors(Errors arg0) {
				}
			
			@Override
			public String[] resolveMessageCodes(String arg0, String arg1) {
				return null;
			}
			
			@Override
			public void recordSuppressedField(String arg0) {
				}
			
			@Override
			public Object getTarget() {
				return null;
			}
			
			@Override
			public String[] getSuppressedFields() {
				return null;
			}
			
			@Override
			public Object getRawFieldValue(String arg0) {
				return null;
			}
			
			@Override
			public PropertyEditorRegistry getPropertyEditorRegistry() {
				return null;
			}
			
			@Override
			public Map<String, Object> getModel() {
				return null;
			}
			
			@Override
			public PropertyEditor findEditor(String arg0, Class arg1) {
				return null;
			}
			
			@Override
			public void addError(ObjectError arg0) {
				}
		};
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
	    model = null;
		mockSession.clearAttributes();
		actionRequest = null;
		renderRequest = null;
		resourceRequest = null;
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
	public void populatePartTableTest()throws Exception{
		
		List<Part> finalPartList = new ArrayList<Part>();  
		Part part = new Part();  
		part.setPartNumber("12345");  
		part.setDescription("mockDesc"); 
		part.setPartType("mockPartType"); 
		part.setUnitPrice("5342");
		finalPartList.add(0, part);
		mockSession.setAttribute("assetPartListFiltered", finalPartList);
		resourceRequest.setSession(mockSession);
	
	
		controller.populatePartTable(resourceRequest, resourceResponse);

	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void retrievePriceForPartsTest()throws Exception{
		List<Part> finalPartList = new ArrayList<Part>();
		List<Part> assetPartList = new ArrayList<Part>();  
		Part part1 = new Part();  
		part1.setPartNumber("12314");  
		part1.setDescription("mockDesc"); 
		part1.setPartType("mockPartType"); 
		part1.setUnitPrice("123");
		part1.setContractNo("123789");
		List<String> paymentTypes = new ArrayList<String>();
		paymentTypes.add(0, "abc");
		paymentTypes.add(1, "xyz");
		part1.setPaymentTypes(paymentTypes);
		assetPartList.add(0, part1);
		finalPartList.add(0, part1);
		mockSession.setAttribute("assetPartList", assetPartList);
		mockSession.setAttribute("finalpartList", finalPartList);
		resourceRequest.setSession(mockSession);
		resourceRequest.setParameter("showPriceFlag", "true");
		resourceRequest.setParameter("creditFlag", "true");
		resourceRequest.setParameter("poFlag", "true");
		resourceRequest.setParameter("paymentType", "Ship and Bill");
		controller.retrievePriceForParts(resourceRequest, resourceResponse);
	}
	
	
	/**
	 * 
	 */
	@Test
	public void displayAttachmentTest(){
		resourceRequest.setParameter("fileName", "SunCertifiedWebComponentDeveloperStudyGuide.pdf");
		//controller.displayAttachment(resourceRequest, resourceResponse);
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void gotoControlPanelTest() throws Exception{
		renderRequest.setParameter("controlPanelURL", "http://www.example.com/admin");
		String controlPanelPage = controller.gotoControlPanel(renderRequest, renderResponse, model);
		
		assertEquals("OK", "controlPanelPage",controlPanelPage);
	}

	/**
	 * 
	 */
	@Test
	public void goToAddAssetTest(){
		AttachmentForm af = new AttachmentForm();
		/*String goToAddAssetTest = controller.goToAddAsset(renderRequest, renderResponse, assetDetailPageForm, af, model);
		if(goToAddAssetTest=="orderSuppliesAssetOrder/assetDetail"){
			
			assertEquals("Ok", "orderSuppliesAssetOrder/assetDetail", goToAddAssetTest);
		}*/
	}
	/**
	 * @throws Exception 
	 */
	@Test
	public void downloadAssetListURLTest() throws Exception{
		   AssetListContract contract = new AssetListContract();
		    contract.setAssetType("assetType");
		    contract.setChlNodeId("12345");
		    contract.setContactId("783245");
		    contract.setEntitlementEndDate("15/07/2013");
		    contract.setFavoriteFlag(true);
		    contract.setIncrement(123);
		    contract.setLanguageName("english");
		    contract.setLoadAllFlag(true);
		    contract.setMdmId("mdm123");
		    contract.setMdmLevel("Siebel");
		    contract.setNewQueryIndicator(true);
		    contract.setStartRecordNumber(123);
		    Map<String,Object> sortCriteria = new HashMap<String, Object>();	
		    sortCriteria.put("area", "mps");
		    contract.setSortCriteria(sortCriteria);
		    mockSession.setAttribute("downLoadContract", contract);
		    resourceRequest.setAttribute("downLoadContract", mockSession);
		    resourceRequest.setParameter("downloadType", "csv");
		    controller.downloadAssetListURL(resourceRequest, resourceResponse);
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void showOrderAssetEmailTest() throws Exception{
		String orderAssetEmail = controller.showOrderAssetEmail(renderRequest, renderResponse, model);
		assertEquals("ok", "orderSuppliesAssetOrder/orderEmail", orderAssetEmail);
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void showOrderConfirmPrintPageTest() throws Exception{
		String orderConfirmPrintPage = controller.showOrderConfirmPrintPage(renderRequest, renderResponse, model);
		assertEquals("Ok", "orderSuppliesAssetOrder/orderConfirmPrint", orderConfirmPrintPage);
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void showPrintAssetListPageTest() throws Exception{
		String printAssetListPage = controller.showPrintAssetListPage(renderRequest, renderResponse, model);
		assertEquals("Ok", "orderSuppliesAssetOrder/assetListPrint", printAssetListPage);
	}
	
	
	
	/** 
	 * @throws Exception 
	 */
	@Test
	public void removeAttachmentTest() throws Exception {
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		Attachment attachment1 = new Attachment();
		attachment1.setAttachmentName("AllRequestList");
		attachment1.setId("16542");
		attachmentList.add(attachment1);
		mockSession.setAttribute("attachmentList", attachmentList);
		actionRequest.setSession(mockSession);
		
		String fileName = "AllRequestList";
		//controller.removeAttachment(actionRequest, actionResponse, fileName, assetDetailPageForm, model);
	}
	

	/**
	 * @throws Exception 
	 */
	@Test
	public void addAttachmentTest() throws Exception {
		
		
		Attachment attachment1 = new Attachment();
		attachment1.setAttachmentName("AllRequestList");
		attachment1.setDisplayAttachmentName("AllRequestList");
		attachment1.setActivityId("ac12345");
		attachment1.setActualFileName("AllRequestList");
		attachment1.setAttachmentName("AllRequestList");
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		attachmentList.add(attachment1);	
	  
		
		//controller.addAttachment(model, actionRequest, actionResponse, assetDetailPageForm, fileUploadForm, BindingResult);
	}	
	
	
	
	
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void showAssetListPageTest() throws Exception{
		String requestNumber = new String("12345");
		String reqStatus = new String("confirmed");
		String assetId = new String("abd123");
		renderRequest.setParameter("requestNumber", requestNumber);
		renderRequest.setParameter("reqStatus", reqStatus);
		renderRequest.setParameter("assetId",assetId);
		
		
		String assetListPageTest = controller.showAssetListPage(model, renderRequest, renderResponse); 
		assertEquals("ok", "orderSuppliesAssetOrder/assetDetail", assetListPageTest);
	}
 
	/**
	 * @throws Exception 
	 */
	@Test
	public void showAssetListPageForPartnerTest()throws Exception{
		String showAssetListPageForPartner = controller.showAssetListPageForPartner(model, renderRequest, renderResponse);
		assertEquals("ok", "orderSuppliesAssetOrder/assetList", showAssetListPageForPartner);
	}
	
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void getDeviceLocationXMLTest()throws Exception{
		controller.getDeviceLocationXML(resourceRequest, resourceResponse, model);	
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void retrieveContractedDeviceListTest() throws Exception{
		controller.retrieveContractedDeviceList(resourceRequest,resourceResponse);
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void getAssetRequestHistoryTest()throws Exception{
		String assetRequestHistoryTest = controller.getAssetRequestHistory(resourceRequest,resourceResponse,assetId,model);  
		assertEquals("ok", "orderSuppliesAssetOrder/recentOrderHistory", assetRequestHistoryTest);
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void getRetrieveOrderHistoryTest() throws Exception{
		String assetId = "1234";
		resourceRequest.setAttribute("assetId", assetId);
		
		AssetDetailPageForm assetDetailPageForm = new AssetDetailPageForm();
		
		Part part = new Part();
		part.setPartNumber("1234567");
		part.setDescription("Description");
		part.setPartType("Part Type");
		part.setLastOrderDate(new Date(07/16/2013));
		part.setOrderNumber("123");
		part.setOrderQuantity("");
		part.setShippedDate(new Date(07/16/2013));
		List<Part>  assetPartList= new ArrayList<Part>();
		assetPartList.add(part);
		assetDetailPageForm.setAssetPartList(assetPartList);
		resourceRequest.setAttribute("assetDetailPageForm", assetDetailPageForm);
		
		controller.getRetrieveOrderHistory(resourceRequest,resourceResponse,assetId);
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void showCatalogOrderPageTest() throws Exception{
		actionRequest.setParameter("friendlyURL", "12345");
		controller.showCatalogOrderPage(actionRequest, actionResponse, model);
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void updateUserFavoriteAssetTest()throws Exception{	
		controller.updateUserFavoriteAsset(favoriteAssetId, favoriteFlag, resourceRequest,resourceResponse);
	}
	

	/**
	 * @throws Exception 
	 */
	@Test
	public void submitAssetOrderTest() throws Exception{
	
	List<Part> modifiedAssetList = new ArrayList<Part>();
	Part part = new Part();
    part.setPartNumber("123");
    part.setDescription("part");
    part.setPartType("partType");
    part.setSupplyType("supplyType");
    part.setImplicitFlag(true);
    part.setOrderQuantity("7");
    part.setCatalogId("abc");
    part.setSupplyId("abc123");
    part.setProductId("pqr");
    part.setUnitPrice("rs-123");
    
    modifiedAssetList.add(part);
    assetDetailPageForm.setAssetPartList(modifiedAssetList);
    mockSession.setAttribute("assetPartListFiltered", modifiedAssetList);
    actionRequest.setSession(mockSession);
    
    List <Attachment> attachmentList = new ArrayList<Attachment>();
    Attachment attach = new Attachment();
	attach.setAttachmentName("pdf");
	attach.setDescription("describe");
	attachmentList.add(attach);
	mockSession.setAttribute("attachmentList", attachmentList);
	actionRequest.setSession(mockSession);
	
	Asset device = new Asset();
	device.setHostName("device host name");
	device.setProductTLI("765325");
	device.setIpAddress("10.141.27.187");
	assetDetailPageForm.setAsset(device);
    
	assetDetailPageForm.setFinalTaxCalcFlag(true);
	assetDetailPageForm.setFinalCreditFlag(true);
	assetDetailPageForm.setFinalPOFlag(true);
	assetDetailPageForm.setFinalPOFlag(true);
	assetDetailPageForm.setFinalShowPriceFlag(true);
	assetDetailPageForm.setSplitterFlag(true);
	
	actionRequest.setParameter("orderQuantity","orderQuantity");
	AttachmentForm af = new AttachmentForm();
	List<Price> priceInputList = new ArrayList<Price>();
	TaxContract taxContract = new TaxContract();
	taxContract.setSalesOrganization("salesOrg");
	taxContract.setSoldToNumber("soldToNumber");
	taxContract.setCountry("shipToAddress");
	taxContract.setCity("shipToAddress.getCity()");
	taxContract.setPostalCode("shipToAddress.getPostalCode()");
	taxContract.setLineInformationList(priceInputList);
	
	//controller.submitAssetOrder(actionRequest, actionResponse, assetDetailPageForm, BindingResult, af, model);
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void submitAssetOrderRenderTest()throws Exception{
		String returnPage = "returnPage";
		renderRequest.setAttribute("returnPage", returnPage);
		controller.submitAssetOrderRender( model, renderRequest , renderResponse); 
	}
	
    /**
     * @throws Exception 
     */
    @Test
	public void confirmAssetOrderTest() throws Exception{
		
	List<Part> modifiedAssetList = new ArrayList<Part>();
	Part part = new Part();
    part.setPartNumber("123");
    part.setDescription("part");
    part.setPartType("partType");
    part.setSupplyType("supplyType");
    part.setImplicitFlag(true);
    part.setOrderQuantity("7");
    part.setCatalogId("abc");
    part.setSupplyId("abc123");
    part.setProductId("pqr");
    modifiedAssetList.add(part);
    assetDetailPageForm.setAssetPartList(modifiedAssetList);
    assetDetailPageForm.setPoNumber("820009");
	assetDetailPageForm.setRelatedServiceRequestedNumber("dtfh");
    
    List<String> userRoleList = new ArrayList<String>();
	userRoleList.add("abc");
	mockSession.setAttribute("userRoleList", userRoleList);
	renderRequest.setSession(mockSession);
	
    Asset device = new Asset();
	device.setHostName("device host name");
	device.setProductTLI("765325");
	device.setIpAddress("10.141.27.187");
	assetDetailPageForm.setAsset(device);
	
   // String confirmAssetOrderTest = controller.confirmAssetOrder(renderRequest , renderResponse ,  assetDetailPageForm,  reviewAssetOrderForm, model );
    //assertEquals("ok", "orderSuppliesAssetOrder/confirmAssetOrder", confirmAssetOrderTest);
    }
	
    /**
	 * Test Cases End
	 **/
	
	/**
	 * Test Cases Utility methods to populate data 
	 **/
	/**
	 * @return UserGridSettingResult 
	 */
	public UserGridSettingResult retrieveDeviceDetail() {
	UserGridSettingResult userResult = new UserGridSettingResult();
	userResult.setColsFilter("1");
	userResult.setColsHidden("2");
	userResult.setColsOrder("3");
	userResult.setColsSorting("4");
	userResult.setColsWidth("5");
	userResult.setGridId("6");
	userResult.setUserNumber("7");
	return userResult;
	}
    
	/**
	 * @param contract 
	 * @return LocalizedPageCountNameResult 
	 */
	public LocalizedPageCountNameResult retrieveLocalizedPageCountName(LocalizedPageCountNameContract contract) {
		LocalizedPageCountNameResult serviceRequestLocaleImpl = new LocalizedPageCountNameResult();
		serviceRequestLocaleImpl.setLocalizedValue("7777777");
		return serviceRequestLocaleImpl;
	}
	
	/**
	 * @return PriceResult 
	 */
	public static PriceResult resultP(){
		PriceResult  r = new PriceResult();
		Price price1 = new Price();
		price1.setContractLineItemId("123765");
		price1.setCurrency("USD");
		price1.setPartNumber("7632");
		price1.setPrice("989");
		price1.setTax("140");
		List<Price> priceList = new ArrayList<Price>();
		priceList.add(price1);
		r.setPriceOutputList(priceList);
		return r;
	}

	/**
	 * @param modifiedAssetList 
	 * @param shipToAddress 
	 * @return TaxContract 
	 */
	public static TaxContract getAssetOrderTaxContract(
			List<Part> modifiedAssetList, GenericAddress shipToAddress) {
		AssetDetailPageForm assetDetailPageForm = new AssetDetailPageForm();
		modifiedAssetList = new ArrayList<Part>();
		Part part = new Part();
	    part.setPartNumber("123");
	    part.setDescription("part");
	    part.setPartType("partType");
	    part.setSupplyType("supplyType");
	    part.setImplicitFlag(true);
	    part.setOrderQuantity("7");
	    part.setCatalogId("abc");
	    part.setSupplyId("abc123");
	    part.setProductId("pqr");
	    part.setUnitPrice("rs-123");
	    
	    modifiedAssetList.add(part);
	    assetDetailPageForm.setAssetPartList(modifiedAssetList);
	    
	    TaxContract taxContract = new TaxContract();
	    List<Price> priceInputList = new ArrayList<Price>();
	    Price price = new Price();
	    price.setPrice(part.getUnitPrice());
		price.setPartNumber(part.getPartNumber());
	    
	    taxContract.setSalesOrganization("salesOrg");
		taxContract.setSoldToNumber("soldToNumber");
		taxContract.setCountry("shipToAddress.getCountryISOCode()");
		taxContract.setCity("shipToAddress.getCity()");
		taxContract.setPostalCode("shipToAddress.getPostalCode()");
		taxContract.setLineInformationList(priceInputList);
	    
		return taxContract;
	}

	/**
	 * @param contract 
	 * @return PriceResult 
	 * @throws Exception 
	 */
	public PriceResult retrievePriceList(PriceContract contract)throws Exception {
		Price price = new Price();
		price.setContractLineItemId("123765");
		price.setCurrency("USD");
		price.setPartNumber("7632");
		price.setPrice("989");
		price.setTax("140");
		List<Price> priceList = new ArrayList<Price>();
		priceList.add(price);
		PriceResult result = new PriceResult();
		result.setPriceOutputList(priceList);
		return result;
	}
	/**
	 * Test Cases Utility methods to populate data end
	 **/
}
	



