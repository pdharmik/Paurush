/**
 * Copyright@ 2013. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: PageCountsControllerTest.java
 * Package     		: com.lexmark.services.tests.junitTests
 * Creation Date 	: 11th September 2013
 *
 * Modification History:
 *
 * -----------------------------------------------------------------------------------------------
 * Author 		                    		Date				Version  		Comments
 * -----------------------------------------------------------------------------------------------
 * Preeti Paul, Silk Shilpa      	11th September 2013 		   1.0       Initial Version
 
 *
 */
package com.lexmark.services.tests.junitTests;

import static org.junit.Assert.*;

import java.beans.PropertyEditor;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.json.JSONArray;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.LocationReportingHierarchyContract;
import com.lexmark.contract.MeterReadAssetListContract;
import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.contract.UserFavoriteAssetContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Asset;
import com.lexmark.domain.CHLNode;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.Part;
import com.lexmark.domain.ServicesUser;
import com.lexmark.result.AssetReportingHierarchyResult;
import com.lexmark.result.FavoriteResult;
import com.lexmark.result.MeterReadAssetListResult;
import com.lexmark.result.UserGridSettingResult;
import com.lexmark.service.impl.mock.MeterReadServiceImpl;
import com.lexmark.services.form.PageCountsImportForm;
import com.lexmark.services.portlet.PageCountsController;
import com.lexmark.services.portlet.SharedPortletController;

/**.
 * This Class is designed to test the methods of PageCountsController.java
 * @author Preeti Paul, Silk Shilpa
 * 
 */
public class PageCountsControllerTest {
	
	private MockResourceRequest resourceRequest = new MockResourceRequest();
	private MockResourceResponse resourceResponse = new MockResourceResponse();
	private MockRenderRequest renderRequest = new MockRenderRequest();
	private MockRenderResponse renderResponse = new MockRenderResponse();
	private Model model;
	private MockActionRequest actionRequest = new MockActionRequest();
	private MockActionResponse actionResponse = new MockActionResponse();
	private MockPortletSession mockSession;
	private BindingResult BindingResult;
	private static final Long submitToken = null;
	
	private PageCountsController controller;
	private Map<String, Object> ldapUserData;
	private List<CHLNode> chlNodeList;
	List<String> userRoleList = new ArrayList<String>();
	
	
	ServicesUser servicesUser;
	Asset asset;
	@Before
	public void setUp() throws Exception {
	    /**
		 * Autowiring and resource binding
		 **/
		controller = new PageCountsController(); 
		ReflectionTestUtils.setField(controller, "sharedPortletController",new SharedPortletController());
		ReflectionTestUtils.setField(controller, "meterReadService",new MeterReadServiceImpl());
		
		/**
		 * Autowiring and resource binding end
		**/
		
		mockSession=new MockPortletSession();
		model = createMock(Model.class);
		renderRequest.addLocale(Locale.US);
		ldapUserData=new HashMap<String, Object>();
		
		asset = new Asset();
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
				
				
				userRoleList.add("ServiceManager");
				userRoleList.add("ServiceTechnician");
				userRoleList.add("ServiceAdminstration");
				
		/**
		 * Binding attributes with session and requests end
		 **/ 		
		
	}
	private BindingResult createMockBindingResult(Class<BindingResult> class1) {
		return new BindingResult() {

			public void addAllErrors(Errors arg0) {
				// TODO Auto-generated method stub
				
			}

			public List<ObjectError> getAllErrors() {
				// TODO Auto-generated method stub
				return null;
			}

			public int getErrorCount() {
				// TODO Auto-generated method stub
				return 0;
			}

			public FieldError getFieldError() {
				// TODO Auto-generated method stub
				return null;
			}

			public FieldError getFieldError(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public int getFieldErrorCount() {
				// TODO Auto-generated method stub
				return 0;
			}

			public int getFieldErrorCount(String arg0) {
				// TODO Auto-generated method stub
				return 0;
			}

			public List<FieldError> getFieldErrors() {
				// TODO Auto-generated method stub
				return null;
			}

			public List<FieldError> getFieldErrors(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public Class getFieldType(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public Object getFieldValue(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public ObjectError getGlobalError() {
				// TODO Auto-generated method stub
				return null;
			}

			public int getGlobalErrorCount() {
				// TODO Auto-generated method stub
				return 0;
			}

			public List<ObjectError> getGlobalErrors() {
				// TODO Auto-generated method stub
				return null;
			}

			public String getNestedPath() {
				// TODO Auto-generated method stub
				return null;
			}

			public String getObjectName() {
				// TODO Auto-generated method stub
				return null;
			}

			public boolean hasErrors() {
				// TODO Auto-generated method stub
				return false;
			}

			public boolean hasFieldErrors() {
				// TODO Auto-generated method stub
				return false;
			}

			public boolean hasFieldErrors(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			public boolean hasGlobalErrors() {
				// TODO Auto-generated method stub
				return false;
			}

			public void popNestedPath() throws IllegalStateException {
				// TODO Auto-generated method stub
				
			}

			public void pushNestedPath(String arg0) {
				// TODO Auto-generated method stub
				
			}

			public void reject(String arg0) {
				// TODO Auto-generated method stub
				
			}

			public void reject(String arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			public void reject(String arg0, Object[] arg1, String arg2) {
				// TODO Auto-generated method stub
				
			}

			public void rejectValue(String arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			public void rejectValue(String arg0, String arg1, String arg2) {
				// TODO Auto-generated method stub
				
			}

			public void rejectValue(String arg0, String arg1, Object[] arg2,
					String arg3) {
				// TODO Auto-generated method stub
				
			}

			public void setNestedPath(String arg0) {
				// TODO Auto-generated method stub
				
			}

			public void addError(ObjectError arg0) {
				// TODO Auto-generated method stub
				
			}

			public PropertyEditor findEditor(String arg0, Class arg1) {
				// TODO Auto-generated method stub
				return null;
			}

			public Map<String, Object> getModel() {
				// TODO Auto-generated method stub
				return null;
			}

			public PropertyEditorRegistry getPropertyEditorRegistry() {
				// TODO Auto-generated method stub
				return null;
			}

			public Object getRawFieldValue(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public String[] getSuppressedFields() {
				// TODO Auto-generated method stub
				return null;
			}

			public Object getTarget() {
				// TODO Auto-generated method stub
				return null;
			}

			public void recordSuppressedField(String arg0) {
				// TODO Auto-generated method stub
				
			}

			public String[] resolveMessageCodes(String arg0, String arg1) {
				// TODO Auto-generated method stub
				return null;
			}
			
			
		};
	}
	
	private Model createMock(Class<Model> mockModelClass){

		return new Model() {

			public Model addAllAttributes(Collection<?> arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public Model addAllAttributes(Map<String, ?> arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public Model addAttribute(Object arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public Model addAttribute(String arg0, Object arg1) {
				// TODO Auto-generated method stub
				return null;
			}

			public Map<String, Object> asMap() {
				// TODO Auto-generated method stub
				return null;
			}

			public boolean containsAttribute(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			public Model mergeAttributes(Map<String, ?> arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			
		};	
	}
	/**
	 * Destroying objects
	 **/
	@After
	public void tearDown() throws Exception {
	    model = null;
		
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
	@Test
	public void pageCountListPageTest() throws Exception{
		renderRequest.setParameter("manualMeterReadFlag", "manualMeterReadFlag");
		
		String pageCountListPage = controller.pageCountListPage(model, renderRequest, renderResponse);
	}
	@Test
	public void pageCountListTest() throws Exception{
		renderRequest.setParameter("gridSettings", "gridSettings");
		String pageCountList= controller.pageCountList(model, renderRequest, renderResponse);
	}
	
	@Test
	public void retrieveMeterReadStatusListTest() throws Exception{
		controller.retrieveMeterReadStatusList(resourceRequest, resourceResponse);
	}
	
	@Test
	public void getPageCountPopUpTest() throws Exception{
		controller.getPageCountPopUp(resourceRequest, resourceResponse, model);
	}
	@Test
	public void updatePageCountsTest()throws Exception{
		
		resourceRequest.setParameter("ArrdataForLTPC", "[10,20]");
		resourceRequest.setParameter("ArrdataForColor","[red, black]");
		resourceRequest.setParameter("ArrdataForA3Color","[blue,pink]");
		resourceRequest.setParameter("ArrdataForA3LTPC","[3,4]");
		resourceRequest.setParameter("ArrdataForA4Color","[black,white]");
		resourceRequest.setParameter("ArrdataForA4LTPC","[7,8]");
		resourceRequest.setParameter("ArrdataForScan","[adc,xyz]");
		resourceRequest.setParameter("ArrdataForFax","[pqr,stu]");
		
		controller.updatePageCounts(model, resourceRequest, resourceResponse);
	}
	@Test
	public void retriveDeviceListTest()throws Exception{
		//chk tis out -- xml output generator
		PageCounts pagecount = new PageCounts();
		pagecount.setCount("123");
		pagecount.setName("daghgf");
		pagecount.setDate("Jan/09/2012");
		List<PageCounts> pc = new ArrayList<PageCounts>();
		pc.add(pagecount);
		asset.setPageCounts(pc);
		GenericAddress genericAddress = new GenericAddress(); 
		genericAddress.setPhysicalLocation2("physicalLocation2");
		genericAddress.setPhysicalLocation1("physicalLocation1");
		asset.setPhysicalLocationAddress(genericAddress);
		controller.retriveDeviceList(resourceRequest, resourceResponse);
	}
	
	@Test
	public void downLoadHelpFileTest()throws IOException {
		controller.downLoadHelpFile(resourceRequest, resourceResponse, model);
	}
	
	@Test
	public void showServiceRequestPrintPageTest()throws Exception{
		controller.showServiceRequestPrintPage(renderRequest, renderResponse,model);
	}
	@Test
	public void retrieveCHLTreeXMLTest()throws Exception{
		
		
		LocationReportingHierarchyContract contract =new LocationReportingHierarchyContract();
		mockSession.setAttribute("LocationReportingHierarchyContract",contract);
		List<CHLNode> childNodeList = new ArrayList<CHLNode>();
		CHLNode node=new CHLNode();
		node.setCHLNodeId("34");
		node.setChlNodeName("krish");
		node.setChlParentId("456");
		childNodeList.add(node);
	    mockSession.setAttribute("CHLNode",node);
	    resourceRequest.setSession(mockSession);
		controller.retrieveCHLTreeXML(resourceRequest, resourceResponse, model);
	}
	@Test
	public void retrieveDeviceLocationTreeXMLTest() throws Exception{
		
		
		LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
		contract.setMdmId("64");
		contract.setMdmLevel("li");
		contract.setVendorFlag(true);
	   	contract.setEntitlementEndDate("23-78-2000");
		mockSession.setAttribute("LocationReportingHierarchyContract",contract);
		resourceRequest.setParameter("UserRoles", "userRoleList");
		mockSession.setAttribute("userRoleList",userRoleList);
		resourceRequest.setSession(mockSession);
		
		controller.retrieveDeviceLocationTreeXML(resourceRequest, resourceResponse, model);
	}
	@Test
	public void exportPageCountTest()throws Exception{
		PageCounts pageCount = new PageCounts();
		pageCount.setName("name");
		pageCount.setCount("123");
		pageCount.setDate("9/01/2012");
		List<PageCounts> pageCounts = new ArrayList<PageCounts>();
		asset.setPageCounts(pageCounts);
		resourceRequest.setAttribute("asset", asset);
		controller.exportPageCount(resourceRequest, resourceResponse, model);
	}
	

	@Test
	public void importPageCounts() throws Exception{
		renderRequest.setAttribute("columns","columns");
		renderRequest.setAttribute("pageCountsImportForm","pageCountsImportForm");
		controller.importPageCounts(renderRequest , renderResponse , model);
	}
	@Test
	public void doFileUploadTest() throws Exception {
		controller.doFileUpload(actionRequest, actionResponse,  null, null, model);
	}
	
	@Test
	public void outPutCSVFileTest()throws Exception{
		controller.outPutCSVFile(resourceRequest, resourceResponse, model);
	}
	
	@Test
	public void showSRListAdvancedSearchOptionPageTest()throws Exception{
		String output= controller.showSRListAdvancedSearchOptionPage(renderRequest, renderResponse, model);
		assertEquals("OK", "pageCounts/pageCountsAdvancedSearch",
				output);
	}
	@Test
	public void showValidateWarningTest()throws Exception{
      
		
		String output= controller.showValidateWarning( renderRequest, renderResponse,model);
		assertEquals("OK", "pageCounts/validateWarning",
				output);
     }
	@Test
	public void downloadMeterReadTest ()throws Exception
	{
		
		List<Asset> deviceList= new ArrayList<Asset>();
		Asset asset=new Asset();

		asset.setSerialNumber("1-2345678");
		asset.setProductTLI("Lexmark Product");
		asset.setModelNumber("Lexmark P 12");
		asset.setInstallDate(new Date("12/12/12"));
		asset.setIpAddress("10.141.27.100");
		asset.setHostName("HO304L30");
		asset.setAssetTag("0230485");
		asset.setAddressFlag(true);
		GenericAddress genericAddress = new GenericAddress();
		Account account = new Account();
		account.setAccountId("11");
		account.setAccountName("Innova");
		AccountContact contact=new AccountContact();
		contact.setFirstName("rahul");
		contact.setLastName("sharma");
		contact.setEmailAddress("rahul@gmail.com");
		contact.setWorkPhone("45452223");
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
		asset.setAssetId("45");
		asset.setSerialNumber("1-2345678");
		asset.setProductTLI("Lexmark Product");
		asset.setModelNumber("Lexmark P 12");
		asset.setInstallDate(new Date("12/12/12"));
		asset.setIpAddress("10.141.27.100");
		asset.setHostName("HO304L30");
		asset.setAssetTag("0230485");
		asset.setProductLine("productLine");
		asset.setControlPanelURL("controlPanelURL");
		asset.setDeviceTag("deviceTag");
		asset.setUserFavoriteFlag(true);
		asset.setInstallAddress(genericAddress);
		asset.setAccount(account);
		asset.setAssetContact(contact);
		deviceList.add(asset);
		resourceRequest.setParameter("deviceList", "deviceList");
		MeterReadAssetListContract contract=new MeterReadAssetListContract();
		contract.setMdmID("79");
		contract.setMdmLevel("L1");
		contract.setChlNodeId("89798");
		contract.setFavoriteContactId("567");
		contract.setFavoriteFlag(true);
		contract.setContactId("5667788989");
		contract.setMeterReadType("read");
		contract.setStartRecordNumber(10);
		contract.setIncrement(5);
		mockSession.setAttribute("MeterReadAssetListContract", contract);
		resourceRequest.setSession(mockSession);
		MeterReadAssetListResult meterReadAssetListResult =new MeterReadAssetListResult();
		meterReadAssetListResult.setAccountAssets(deviceList);
		meterReadAssetListResult.setTotalCount(5);
		resourceRequest.setAttribute("MeterReadAssetListResult", meterReadAssetListResult);
		resourceRequest.setParameter("downloadType", "csv");
		controller.downloadMeterRead( resourceRequest, resourceResponse ,model);
		
		resourceRequest.setParameter("downloadType", "pdf");
		controller.downloadMeterRead( resourceRequest, resourceResponse ,model);
			
     }
	@Test
	public void gotoControlPanelTest()throws Exception{
		renderRequest.setAttribute("controlPanelURL", "controlPanelURL");
		renderRequest.setAttribute("pageName", "pageName");
		controller.gotoControlPanel(renderRequest, renderResponse, model);
		
	}
	@Test
	public void updateUserFavoriteAssetTest()throws Exception{
		String favoriteAssetId="favoriteAssetId";
		 boolean favoriteFlag=true;
		 UserFavoriteAssetContract contract = new UserFavoriteAssetContract();
		 contract.setContactId("565767");
		 contract.setFavoriteAssetId("6778");
		 contract.setFavoriteFlag(true);
		 mockSession.setAttribute("UserFavoriteAssetContract", contract);
		 resourceRequest.setSession(mockSession);
		 FavoriteResult favouriteResult = new FavoriteResult();
		 favouriteResult.setResult(true);
		 resourceRequest.setParameter("FavoriteResult", "favouriteResult");
		 controller.updateUserFavoriteAsset(favoriteAssetId, favoriteFlag, resourceRequest, resourceResponse);
	}
	@Test
	public void updateAssetMeterReadTest()throws Exception{
		String assetId="assetId";
		String rowId=" rowId";
		String newPageCount="newPageCount";
		
		String newColorPageCount="newColorPageCount";
		String OldPageCount="oldLTPCPageCount";
		String OldColorPageCount="oldColorPageCount";
		Date newReadDate= new Date(12/9/2013);
		List<PageCounts> pagecountsList = new ArrayList<PageCounts>();
		PageCounts pg=new PageCounts();
		pg.setBothValueBlank(true);
		pg.setCount("10");
		pg.setName("page");
		pg.setSiebelName("sibel");
		//pg.setDate(new date(12/9/2013));
		pagecountsList.add(pg);
		resourceRequest.setParameter("pagecountsList", "pagecountsList");
		mockSession.setAttribute("pagecountsList", "pagecountsList");
		resourceRequest.setSession(mockSession);
		controller.updateAssetMeterRead(assetId, rowId, newPageCount, newColorPageCount, newReadDate, OldPageCount, OldColorPageCount, resourceRequest, resourceResponse);
	}
	
	@Test
	public void  importPageCountsTest ()throws Exception
	{
    
    PageCountsImportForm pageCountsImportForm = new PageCountsImportForm();
    pageCountsImportForm.setCurrentDateStr("23/7/2013" );
    pageCountsImportForm.setSubmitToken(submitToken);
    renderRequest.setParameter("columns", "columns");
    renderRequest.setParameter("pageCountsImportForm","pageCountsImportForm");
    renderRequest.setAttribute("PageCountsImportForm", pageCountsImportForm);
		String output=controller.importPageCounts( renderRequest, renderResponse,model);
		assertEquals("OK", "pageCounts/importPageCountsPage",
				output);
     }
	 /**
	 * Test Cases End
	 **/
	
	/**
	 * Test Cases Utility methods to populate data 
	 **/
	public UserGridSettingResult retrieveUserGridSettings() {
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
	 * Test Cases Utility methods to populate data end
	 **/
}
