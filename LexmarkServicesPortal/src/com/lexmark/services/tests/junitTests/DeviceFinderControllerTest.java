/**
 * Copyright@ 2013. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: DeviceFinderController.java
 * Package     		: com.lexmark.services.tests
 * Creation Date 	: 16th July 2013
 *
 * DeviceFinderDetails:
 *
 * --------------------------------------------------------------------------
 * Author 				Date				Version  		Comments
 * --------------------------------------------------------------------------
 *Supriyo Dey,Silk Shilpa			16th July 2013 		     1.0             Initial Version
 
 *
 */

package com.lexmark.services.tests.junitTests;
import static org.junit.Assert.assertEquals;

import java.beans.PropertyEditor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.support.SessionStatus;

import com.amind.session.SessionFactory;
import com.lexmark.SiebelShared.createServiceRequest.client.PrimaryContact;
import com.lexmark.SiebelShared.createServiceRequest.client.ServiceAddress;
import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.AddressListContract;
import com.lexmark.contract.AssetContract;
import com.lexmark.contract.AssetListContract;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.contract.ContactListContract;
import com.lexmark.contract.GeographyCountryContract;
import com.lexmark.contract.GeographyStateContract;
import com.lexmark.contract.LocalizedServiceStatusContract;
import com.lexmark.contract.LocationReportingHierarchyContract;
import com.lexmark.contract.ProductImageContract;
import com.lexmark.contract.ServiceRequestContract;
import com.lexmark.contract.ServiceRequestHistoryListContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Asset;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.CHLNode;
import com.lexmark.domain.Entitlement;
import com.lexmark.domain.EntitlementServiceDetail;
import com.lexmark.domain.FileObject;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.Pagination;
import com.lexmark.domain.Part;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.ServiceRequestOrderLineItem;
import com.lexmark.domain.ServicesUser;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.result.AddressListResult;
import com.lexmark.result.AssetListResult;
import com.lexmark.result.AssetReportingHierarchyResult;
import com.lexmark.result.AssetResult;
import com.lexmark.result.ContactListResult;
import com.lexmark.result.GeographyListResult;
import com.lexmark.result.LocalizedServiceStatusResult;
import com.lexmark.result.RequestListResult;
import com.lexmark.result.ServiceRequestListResult;
import com.lexmark.result.ServiceRequestResult;
import com.lexmark.result.SiebelLOVListResult;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.mock.ContactServiceImpl;
import com.lexmark.service.impl.mock.DeviceServiceImpl;
import com.lexmark.service.impl.mock.GlobalServiceImpl;
import com.lexmark.service.impl.mock.MeterReadServiceImpl;
import com.lexmark.service.impl.mock.ProductImageServiceImpl;
import com.lexmark.service.impl.mock.ServiceRequestServiceImpl;
import com.lexmark.service.impl.real.AmindOrderSuppliesAssetService;
import com.lexmark.service.impl.real.AmindRequestTypeService;
import com.lexmark.service.impl.real.attachmentService.UploadAttachmentService;
import com.lexmark.service.impl.real.jdbc.GeographyServiceImpl;
import com.lexmark.service.impl.real.util.LogUtil;
import com.lexmark.services.form.AdvancedSearchForm;
import com.lexmark.services.form.CreateNewAddressForm;
import com.lexmark.services.form.DeviceDetailForm;
import com.lexmark.services.form.ManageAddressForm;
import com.lexmark.services.form.ServiceRequestConfirmationForm;
import com.lexmark.services.form.ShipmentForm;
import com.lexmark.services.form.validator.CommonValidator;
import com.lexmark.services.portlet.DeviceFinderController;
import com.lexmark.services.portlet.SharedPortletController;
import com.lexmark.services.portlet.common.CommonController;
import com.lexmark.services.portlet.common.ContactController;

/**.
 * This Class is designed to test the Device details methods of DeviceFinderController.java
 * @author Supriyo Dey,Silk Shilpa
 * 
 */

public class DeviceFinderControllerTest 
{
	private static final String BUNDLE = null;
	private DeviceFinderController target;
	private Model mockModel;
	private BindingResult BindingResult;
	private MockRenderRequest renderRequest;
	private MockRenderResponse renderResponse;
	private MockResourceRequest reReq;
	private MockResourceResponse reRes;
	private MockActionRequest mockactionrequest; 
	private MockActionResponse mockactionresponse;
	boolean updateSrFlag;
	boolean isBack;
	private MockActionRequest actionrequest;
	private MockActionResponse actionresponse;	
	private Map<String, Object> ldapUserData;
	private Map<String, String> accDetails;
	private ModelMap modelmap;
	ManageAddressForm manageAddressForm;
	private SessionStatus mockSessionStatus;
	private MockPortletSession mockSession;
	Asset assetInfo;
	ServicesUser servicesUser;
	
	/*renderRequest. addLocale(Locale.US);
	reReq. addLocale(Locale.US);*/
	boolean flagStatus = true;
	
	private List<Asset> deviceList;
	private Locale locale;
	SessionFactory statelessSessionFactory;

	private List<GenericAddress> addresslist= new ArrayList<GenericAddress>();
	
	private ListOfValues lov = new ListOfValues();
	private Map<String, String> userAccessMapForSr = new HashMap<String, String>();
	Map<String, String> requestAccessMap=new HashMap();
	private List<ListOfValues> lovList = new ArrayList<ListOfValues>();
	private Map<String, Object> filterCriteria = new HashMap<String, Object>();
	private Map<String, Object>  searchCriteria = new HashMap<String, Object>();
	private List<String> USERROLES = new ArrayList<String>();
	private List<Asset> asset  = new ArrayList<Asset>();
	//List<AccountContact> starredContactList = new Vector<AccountContact>();
	List<String> userRoleList = new ArrayList<String>();
	private ArrayList<ListOfValues> SiebelLOVList = new ArrayList<ListOfValues>(0);
	private SiebelLOVListResult lovRes = new SiebelLOVListResult();
	
	

	private AssetListContract downLoadContract;
	
	 String gridName= "primary";
	 String contactId=" contactId";
	 String lastName="lastName";
	 String firstName="firstName";
	 String workPhone="workPhone";
	 String emailAddress="emailAddress";
	 String COUNT = "count";
	 String POS_START = "posStart";
	 String DIRECTION = "direction";
	 String ORDER_BY = "orderBy";
	 String FILTER_CRITERIAS = "filterCriterias";
	 String FILTER_CRITERIAS_SEPERATOR = ",";
	 String SEARCH_CRITERIAS = "searchCriterias";
	 String SEARCH_CRITERIAS_SEPERATOR = "__";
	 String SEARCH_CRITERIA_NAME_VALUE_SPERATOR = "^";
	private List<CHLNode> chlNodeList;
	private int totalCount;
	private String countryCode;
	private Object property;
	private List<ServiceRequest> serviceRequests;
	
	
	/**
	 * @throws Exception 
	 */
	@Before
	/**
	 * Autowiring and resource binding
	 **/

	public void setUp() throws Exception {
		target = new DeviceFinderController();
		ReflectionTestUtils.setField(target, "deviceService",new DeviceServiceImpl());
		ReflectionTestUtils.setField(target, "contactService",new ContactServiceImpl());
		ReflectionTestUtils.setField(target, "sharedPortletController",new SharedPortletController());
		ReflectionTestUtils.setField(target, "serviceRequestService",new ServiceRequestServiceImpl());
		ReflectionTestUtils.setField(target, "globalService",new GlobalServiceImpl());
		ReflectionTestUtils.setField(target, "productImageService",new ProductImageServiceImpl());
		ReflectionTestUtils.setField(target, "meterReadService",new MeterReadServiceImpl());
		ReflectionTestUtils.setField(target, "requestTypeService",new AmindRequestTypeService());
		ReflectionTestUtils.setField(target, "commonController",new CommonController());
		ReflectionTestUtils.setField(target, "orderSuppliesAssetService",new AmindOrderSuppliesAssetService());
		
		ReflectionTestUtils.setField(new CommonController(), "sharedPortletController",new SharedPortletController());
		
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

		/**
		 * Autowiring and resource binding end
		 **/
		mockactionrequest = new MockActionRequest();
		reReq = new MockResourceRequest();
		reRes = new MockResourceResponse();
		renderRequest = new MockRenderRequest();
		renderResponse = new MockRenderResponse();
		mockSession=new MockPortletSession();
		mockModel = createMock(Model.class);
		BindingResult=createMockBindingResult(BindingResult.class);
		renderRequest.addLocale(Locale.US);
		reReq .addLocale(Locale.US);	
		
		Map<String, FileObject> filemap = new HashMap<String, FileObject>();
		FileObject ob = new FileObject();
		ob.setDisplayFileName("displayFileName");
		ob.setFileName("fileName_filename");
		ob.setFileSize("20");
		ob.setFileSizeInBytes("20");
		ob.setUploadDate("uploadDate");
		filemap.put("0",ob);
		mockSession.setAttribute("fileMapInSession", filemap);
		mockactionrequest.setSession(mockSession);
		assetInfo = new Asset();
		assetInfo.setAssetId("mockassetid");
		
		
	
		
		
		
		/**
		 * ldap Details Information
		 **/
		ldapUserData=new HashMap<String, Object>();
		ldapUserData.put(LexmarkConstants.MDMID, "xxx Mdmid");
		ldapUserData.put(LexmarkConstants.MDMLEVEL, "xxx MdmLevel");
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
		
		
		mockSession.setAttribute(LexmarkConstants.LDAP_USER_DATA, ldapUserData, mockSession.APPLICATION_SCOPE);
		/**
		 * ldap Details Information End
		 **/
		
		
		/**
		 *ServiceUserDetails Information
		 **/
		servicesUser=new ServicesUser();
		servicesUser.setEmailAddress("xxx@abx.com");
		servicesUser.setUserNumber("123userNumber");
		servicesUser.setMdmId("1234");
		servicesUser.setMdmLevel("L1");
		servicesUser.setChlNodeId("765567");
		
		mockSession.setAttribute(LexmarkConstants.SERVICES_USER,servicesUser,mockSession.APPLICATION_SCOPE);
		reReq.setSession(mockSession);
		renderRequest.setSession(mockSession);
		/**
		 *ServiceUserDetails Information End
		 **/
		
		
		/**
		 *Assetcontract,ProductImageContract,userAccessMapForSr,productImageContract Information
		 **/
		AssetContract contract = new AssetContract();
		AssetListContract contract2 = new AssetListContract();
		contract.setMdmId("mdmId");
		contract.setMdmLevel("mdmLevel");
		contract2.setStartRecordNumber(1);
		contract2.setIncrement(10);
		
		ProductImageContract productImageContract = new ProductImageContract();
		productImageContract.setPartNumber("23");
		userAccessMapForSr.put("partner", "partner");
		userAccessMapForSr.put("Customer", "Customer");
		requestAccessMap.put("CREATE SERVICE REQUEST", "CREATE SERVICE REQUEST");
		requestAccessMap.put("CREATE SUPPLIES REQUEST", "CREATE SUPPLIES REQUEST");
		requestAccessMap.put("CREATE CHANGE MGMT REQUEST", "CREATE CHANGE MGMT REQUEST");
		requestAccessMap.put("SHOW SUPPLIES REQUEST", "SHOW SUPPLIES REQUEST");
		
		LocationReportingHierarchyContract contract1 = new LocationReportingHierarchyContract();
		contract1.setMdmId("mdmId");
		contract1.setMdmLevel("mdmLevel");
		contract1.setEntitlementEndDate("UTC-Time");

		/**
		 * Generic Address and other page related details
		 **/
		GenericAddress genericAddress = new GenericAddress();
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
		addresslist.add(genericAddress);
		
		
		ServiceRequest serviceRequest = new ServiceRequest();
		AccountContact accountContact = new AccountContact();
		Part part = new Part();
	    accountContact.setAddress(genericAddress);
		accountContact.setAlternatePhone("123124234234");
		accountContact.setContactId("122131");
		accountContact.setCountry("country");
		accountContact.setDepartment("department");
		accountContact.setEmailAddress("rahul@gmal.com");
		accountContact.setFirstName("firstName");
		accountContact.setLastName("lastName");
		accountContact.setManageContactFlag(true);
		accountContact.setMiddleName("middleName");
		accountContact.setNewContactFlag(true);
		accountContact.setShortId("gh677");
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
		lov.setName("rahul");
		lov.setType("add");
		lov.setValue("change request");
		lovList.add(lov);
		lovRes.setLovList(lovList);
		serviceRequest.setArea(lov);
		serviceRequest.setSubArea(lov);
		USERROLES.add("partner");
		USERROLES.add("Customer");
	
	
		userRoleList.add("ServiceManager");
		userRoleList.add("ServiceTechnician");
		userRoleList.add("ServiceAdminstration");
		
		filterCriteria.put(" filterCriteria"," Ascendind");
		filterCriteria.put(" filterCriteria"," decending");
		searchCriteria.put(" searchCriteria"," Ascendind");
		searchCriteria.put(" searchCriteria"," decending");

		/**
		 * Generic Address and other page related details End
		 **/
		
		/**
		 * Binding attributes with session and requests
		 **/
		mockSession.setAttribute("AssetContract",contract);
		mockSession.setAttribute("LocationReportingHierarchyContract",contract1);
		mockSession.setAttribute(LexmarkConstants.SERVICES_USER, servicesUser,
					mockSession.APPLICATION_SCOPE);
		
		mockSession.setAttribute("accountCurrentDetails", accDetails,
					mockSession.APPLICATION_SCOPE);
		mockSession.setAttribute("USERROLES", userRoleList,
					mockSession.APPLICATION_SCOPE);
		renderRequest.setSession(mockSession);
		reReq.setSession(mockSession);
		
	
		
		downLoadContract = new AssetListContract();
		downLoadContract.setMdmId("121312312");
		downLoadContract.setMdmLevel("siebel");
		downLoadContract.setFavoriteFlag(false);
		downLoadContract.setFilterCriteria(filterCriteria);
		mockSession.setAttribute("downLoadContract", downLoadContract);
		
		/**
		 * Binding attributes with session and requests end
		 **/
			/*mockSessionStatus= new SessionStatus() {
			
			@Override
			public void setComplete() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isComplete() {
				// TODO Auto-generated method stub
				return false;
			}
		};
		mockSession.setAttribute(LexmarkConstants.LDAP_USER_DATA, servicesUser, mockSession.APPLICATION_SCOPE);*/
	}
	
	/**
	 * @param contract 
	 * @return AssetListResult 
	 */
	public AssetListResult mockRetrieveAllDeviceList(AssetListContract contract)
	{
		AssetListResult deviceListResult=new AssetListResult() ;
		List<Asset> deviceList =new ArrayList<Asset>();
		GenericAddress genericAddress = new GenericAddress();
		Account account = new Account();
		account.setAccountId("11");
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
		Asset asset =new Asset();
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
		deviceList.add(asset);
		
		deviceListResult.setAccountAssets(deviceList);
		return deviceListResult;
	}
	
	
	/**
	 * @return AssetResult 
	 */
	public AssetResult retrieveDeviceDetail()
	{
		EntitlementServiceDetail esd = new EntitlementServiceDetail();
		Entitlement ent =new Entitlement();
		AccountContact ac =new AccountContact();
		
		ac.setWorkPhone("8420971389");
		esd.setPrimaryFlag(true);
		esd.setServiceDetailDescription("afdfd");
		esd.setServiceDetailId("supriyo");
		esd.setSiebelValue("sdvsdvsdf");
		
		List<EntitlementServiceDetail> li= new ArrayList<EntitlementServiceDetail>();
		li.add(esd);
		ent.setServiceDetails(li);
		ent.setEntitlementId("set123");
		ent.setEntitlementName("entitlementName");
		
		
		
		Date d = new Date(System.currentTimeMillis());
		Asset as = new Asset();
		
		as.setLastColorPageCount("lastcolorpagecount");
		as.setLastPageCount("lastPageCount");
		as.setLastPageReadDate(d);
		as.setLastColorPageReadDate(d);
		as.setColorCapableFlag(true);
		as.setProductTLI("/LexmarkServicesPortal/images/printer_na_color.png");
		as.setControlPanelURL("www.lexmark.com");
		AssetResult assetResult = new AssetResult();
		assetResult.setAsset(as);
		as.setEntitlement(ent);
		as.setAssetContact(ac);
		return assetResult;
		
	}
	/**
	 * @param countryCode 
	 * @return GeographyListResult 
	 */
	public GeographyListResult mockGetStateDetails(String countryCode)
	{
		
		List<GeographyCountryContract> countryList=new ArrayList<GeographyCountryContract>();
		GeographyCountryContract contract1=new GeographyCountryContract();
		contract1.setCountryCode("1");
		contract1.setCountryName("India");
		countryList.add(contract1);
	     List<GeographyStateContract> stateList= new ArrayList<GeographyStateContract>();
	     GeographyStateContract contract2=new GeographyStateContract();
	     contract2.setStateCode("2");
	     contract2.setStateName("WB");
	     stateList.add(contract2);
		GeographyListResult countryListResult=new GeographyListResult();
		countryListResult.setCountryList(countryList);
		countryListResult.setStateList(stateList);
         return countryListResult;
	}
	
	/**
	 * @return ServiceRequestListResult 
	 */
	public ServiceRequestListResult mockRetrieveAssociatedServiceRequestList()
	{
		ServiceRequestListResult serviceRequestListResult= new ServiceRequestListResult();
		List<ServiceRequest> serviceRequestList =new ArrayList<ServiceRequest>();
		ServiceRequest serviceRequest= new ServiceRequest();
		serviceRequest.setCustomerReferenceNumber("67787");
		serviceRequest.setOrderSource("orderSource");
		serviceRequest.setCostCenter("t456");
		serviceRequest.setExpediteOrder(true);
		serviceRequestList.add(serviceRequest);
		serviceRequest.setArea(lov);
		serviceRequest.setSubArea(lov);
		serviceRequestList.add(serviceRequest);
		
	 //  totalCount = serviceRequestListResult.setTotalCount();
		//serviceRequestList=serviceRequestListResult.setTotalCount();
		serviceRequestListResult.setServiceRequests(serviceRequestList);
	        return 	serviceRequestListResult;
	}
	
	/**
	 * @return RequestListResult 
	 */
	public RequestListResult retrieveRequestList()
	{
		RequestListResult serviceRequestListResult = new RequestListResult();
		ServiceRequest sr = new ServiceRequest();
		sr.setAccountId("set123");
		List<ServiceRequest> serviceRequestList = new ArrayList<ServiceRequest>();
		serviceRequestList.add(sr);
		serviceRequestListResult.setTotalCount(10);
		return serviceRequestListResult;
		
	}
	
	/**
	 * @return AddressListResult 
	 */
	public  AddressListResult  retrieveAddressList()
	{
		List<GenericAddress> addresslist= new ArrayList<GenericAddress>();
		GenericAddress genericAddress = new GenericAddress();
		
		genericAddress.setAddressId("addressid");
		genericAddress.setAddressLine1("line1");
		genericAddress.setAddressLine2("line2");
		genericAddress.setAddressLine3("line3");
		genericAddress.setAddressLine4("line4");
		genericAddress.setAddressName("addressname");
	/*	genericAddress.setCity("city");
		genericAddress.setCountry("country");
		genericAddress.setNewAddressFlag("addressflag");
		genericAddress.setPhysicalLocation1("building");
		genericAddress.setPhysicalLocation2("floor");
		genericAddress.setPhysicalLocation3("office");
		genericAddress.setPostalCode("123456");
		genericAddress.setProvince("province");
		genericAddress.setStoreFrontName("storeFrontName");
		genericAddress.setUserFavorite(true);
		addresslist.add(genericAddress);*/
		 AddressListResult addressListResult=new AddressListResult();
	
		 addressListResult.setAddressList(addresslist);
		return addressListResult;
		
	}
	/**
	 * @return ServiceRequestResult 
	 */
	public ServiceRequestResult retrieveServiceRequest()
	{
		ServiceRequestResult serviceRequestResult = new ServiceRequestResult();
		ServiceRequest sr = new ServiceRequest();
		Attachment attachmentList = new Attachment();
		attachmentList.setAttachmentName("Supriyo");
		attachmentList.setActivityId("ABC");
		attachmentList.setExtension("doc");
		attachmentList.setSize(15);
		attachmentList.setStatus("updated");
		attachmentList.setVisibility("visible");
		attachmentList.setCompletedOn(new Date());
		
		List<Attachment> attachmentList1 = new ArrayList<Attachment>();
		attachmentList1.add(attachmentList);
		
		sr.setAttachments(attachmentList1);
		serviceRequestResult.setServiceRequest(sr);
		return serviceRequestResult;
	}
	
	/**
	 * @return List<Attachment> 
	 */
	public List<Attachment> attachment()
	{
		Attachment attachmentList = new Attachment();
		attachmentList.setAttachmentName("Supriyo");
		attachmentList.setActivityId("ABC");
		attachmentList.setExtension("doc");
		attachmentList.setSize(15);
		attachmentList.setStatus("updated");
		attachmentList.setVisibility("visible");
		attachmentList.setCompletedOn(new Date());
		
		List<Attachment> attachmentList1 = new ArrayList<Attachment>();
		attachmentList1.add(attachmentList);
		return attachmentList1;
	}
	
	/**
	 * @param contract 
	 */
	public void uploadAttachments(AttachmentContract contract) {
    	UploadAttachmentService service = null;
		try {
			service = new UploadAttachmentService(contract);
			service.checkRequiredFields();
			service.renameAttachments();
			
			service.executeService(statelessSessionFactory);
		} catch (Exception e) {
            LogUtil.logAmindServiceCallException(LogUtil.stackTraceExecutionPoint(), e, contract);
            if (service != null) {
            	service.renameAttachmentsWithVisibility();
            }
		} finally {
            if (service != null) {
            	service.shutdownService();	
            }
            
		}
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
	 * @throws Exception 
	 */
	@After
	/**
	 * Destroying objects
	 **/
	public void tearDown() throws Exception {
		mockModel = null;
		actionrequest = null;
		renderRequest = null;
		reReq = null;
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
	public void getLtpcValues() throws Exception
	{
		target.getLtpcValues(reReq, reRes, mockModel);
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void retrieveHistoryListByAssetId() throws Exception
	{
		reReq.setParameter("ASSET_ROWID", "assetRowId");
		reReq.setParameter("SRHISTORY_TYPE" ,"ServiceRequestList");
		reReq.setParameter("TIMEZONE_OFFSET", "4.5");
		reReq.setParameter("FILTER_CRITERIAS", "assending");
		reReq.setParameter("SEARCH_CRITERIAS", "linear");
		//reReq.setParameter("ALL_REQUESTS", "ALL_REQUESTS");
		reReq.setParameter("pageName", "MySRList");
		reReq.setParameter("searchFilterCriteria", "showall");
		reReq.setParameter("startDate", "01/01/2013");
		reReq.setParameter("endDate", "31/12/2013");
		reReq.setParameter("historySrType", "ALL_REQUESTS");
		reReq.setParameter("historySrType", "SUPPLY_REQUESTS");
		reReq.setParameter("historySrType", "CHANGE_REQUESTS");
		List<String> reqTypeValues = new ArrayList<String>();
		reqTypeValues.add("Consumables Management");
		reqTypeValues.add("Fleet Management");
		reqTypeValues.add( "BreakFix");
		reReq.setParameter("reqTypeValues", "others");
		reReq.setParameter("requestedFrom", "ALL_REQUESTS");
		
		Map<String, Object>  filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("requestType", reqTypeValues);
		reReq.setAttribute("filterCriteria", filterCriteria);

		target.retrieveHistoryListByAssetId(reReq, reRes);
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void updatePageCounts() throws Exception
	{
		
		reReq.setParameter("NEWPAGECOUNT","newPageCount");
		reReq.setParameter("NEWCOLORPGCNT","newColorPageCount");
		reReq.setParameter("NEWPGREADDT","newPageReadDate");
		reReq.setParameter("NEWCOLORPGREADDT","newColorPageReadDate");
		reReq.setParameter("SELECTEDASSETID","selectedAssetId");
		target.updatePageCounts(mockModel, reReq, reRes);
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void setServiceAddressFavouriteFlag() throws Exception
	{
		String favoriteAddressId = "favoriteAddressId";
		String contactId = "contactId";
		String assetId = "assetid123";
		target.setServiceAddressFavouriteFlag(favoriteAddressId, contactId, flagStatus, reReq, reRes);
	}
	
	/**
	 * @throws Exception  
	 */ 
	@Test
	public void setContactFavouriteFlag() throws Exception
	{
		String favoriteAddressId = "favoriteAddressId";
		String contactId = "contactId";
		target.setContactFavouriteFlag(favoriteAddressId, contactId, flagStatus, reReq, reRes);
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void updateUserFavoriteAsset() throws Exception
	{
		String favoriteAddressId = "favoriteAddressId";
		target.updateUserFavoriteAsset(favoriteAddressId, flagStatus, reReq, reRes);
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void openPageCountPopUp() throws Exception
	{
		target.openPageCountPopUp();
	}
	
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void showServiceRequestDetailPage() throws Exception
	{
            String trackingUrl="shipmentTrackingUrl";
		renderRequest.setParameter("trackingUrl", "http://www.lexmark.com");
		renderRequest.setParameter("serviceRequestNumber", "122334");
		renderRequest.setParameter("lightBox", "lightBox");
		renderRequest.setParameter("newImageHeight","newImageHeight");
		renderRequest.setParameter("newImageWidth","newImageWidth");
		//String shipmentTrackingUrl = "http://";
		renderRequest.setParameter("shipmentTrackingUrl","shipmentTrackingUrl");
		renderRequest.setParameter("carrierLinkFromProperty","carrierLinkFromProperty");
	
		ServiceRequestContract contract = new ServiceRequestContract();
		contract.setLocale(locale);
		contract.setRequestNumber("45778");
		contract.setVisibilityRole("visibilityRole");
		renderRequest.setAttribute("ServiceRequestContract", contract);
		List<ShipmentForm> returnForms = new ArrayList<ShipmentForm>();
		ShipmentForm sf=new ShipmentForm();
		sf.setShipmentProgress(1);
		sf.setShipmentXML("shipmentXML");
		sf.setETA("eTA");
		sf.setCarrier("ups");
		sf.setCarrierLink("carrierLink");
		sf.setTrackingNumber("10");
		sf.setHasProperUrl(true);
		returnForms.add(sf);
		
		List<ServiceRequestOrderLineItem> orderLineItems=new ArrayList<ServiceRequestOrderLineItem>();
		ServiceRequestOrderLineItem sro=new ServiceRequestOrderLineItem();
		sro.setTrackingNumber("trackingNumber");
		sro.setCarrier("carrier");
		orderLineItems.add(sro);
		renderRequest.setAttribute("orderLineItems", orderLineItems);
		Map shipment = new HashMap();
		shipment.put("ShipmentForm", sf);
		renderRequest.setAttribute("ShipmentForm", sf);
		renderRequest.setAttribute("returnForms", returnForms);
		renderRequest.setParameter("shipmentTrackingUrl","shipmentTrackingUrl");
		renderRequest.setParameter("carrierLinkFromProperty","carrierLinkFromProperty");
		String output=target.showServiceRequestDetailPage(renderRequest, renderResponse, mockModel);
		
		assertEquals("OK", "serviceRequest/serviceRequestDrillDown",
				output);
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void showServiceRequestDrillDownLightBox() throws Exception
	{
		
		renderRequest.setParameter("serviceRequestNumber", "122334");
		renderRequest.setParameter("lightBox", "lightBox");
		renderRequest.setParameter("newImageHeight","newImageHeight");
		renderRequest.setParameter("newImageWidth","newImageWidth");
		String output=target.showServiceRequestDrillDownLightBox(renderRequest, renderResponse, mockModel);
		
		assertEquals("OK", "serviceRequest/serviceRequestDrillDownLightBox",
				output);
	}
	
	/**
	 * @throws LGSBusinessException 
	 * @throws SiebelCrmServiceException 
	 * @throws Exception 
	 */
	@Test
	public void submitServiceRequest() throws LGSBusinessException, SiebelCrmServiceException, Exception
	{
		ServiceRequestConfirmationForm serviceRequestConfirmationForm = new ServiceRequestConfirmationForm();
		ServiceRequest sr = new ServiceRequest();
		Asset as = new Asset();
		AccountContact ac = new AccountContact();
		GenericAddress ga= new GenericAddress(); 
		String[] servicedetails = {"serviceDetailId","serviceDetailDescription","siebelValue","primaryFlag"};
		EntitlementServiceDetail esd =new EntitlementServiceDetail();
		Entitlement ent =new Entitlement();
		
		esd.setPrimaryFlag(true);
		esd.setServiceDetailDescription("afdfd");
		esd.setServiceDetailId("supriyo");
		esd.setSiebelValue("sdvsdvsdf");
		
		List<EntitlementServiceDetail> li= new ArrayList<EntitlementServiceDetail>();
		li.add(esd);
		ent.setServiceDetails(li);
		as.setEntitlement(ent);
		
		ga.setAddressId("saltlake123");
		as.setAssetId("as123");
		as.setAssetContact(ac);
		as.setInstallAddress(ga);
		ac.setContactId("sudey90");
		sr.setAsset(as);
		sr.setSecondaryContact(ac);
		sr.setPrimaryContact(ac);
		sr.setSecondaryContact(ac);
		sr.setServiceAddress(ga);
		
		serviceRequestConfirmationForm.setAsset(as);
		serviceRequestConfirmationForm.setServiceRequest(sr);
		serviceRequestConfirmationForm.setLoginAccountContact(ac);
		serviceRequestConfirmationForm.setSelectedServiceDetails(servicedetails);
		
		Attachment atch = new Attachment();
		atch.setActivityId("124");
		atch.setActualFileName("file");
		List<Attachment> at = new LinkedList<Attachment>();
		at.add(atch);
		
		//mockactionresponse.setRenderParameter("action", "showServiceRequestConfirmation");	
		target.submitServiceRequest(mockactionrequest, mockactionresponse, serviceRequestConfirmationForm, BindingResult, mockModel);
	}
	/**
	 * @throws Exception 
	 */
	@Test
	public void showServiceRequestSubmitPage2() throws Exception
	{
		String assetId = "assetid";
		target.showServiceRequestSubmitPage2(renderRequest, renderResponse, assetId, mockModel);
	}

	/**
	 * @throws Exception 
	 */
	@Test
	public void testShowDeviceAdvancedSearchOptionPage()throws Exception
	{
		
		
		 AdvancedSearchForm advancedSearchForm = new AdvancedSearchForm();
		 List<String> countries= new ArrayList<String>();
		 countries.add("INDIA");
		 List<String> states =new ArrayList<String>();
		 states .add("JHARKHAND");
		 List<String> provinces =new ArrayList<String>();
		 provinces.add("provinice");
		 List<String> cities =new ArrayList<String>();
		 cities.add("JAMSHEDPUR");
			List<String> retList = new ArrayList<String>();
			retList.size();
			int index = 0;
			String key = "";
			String str="str";
			retList.add(index,str);
			renderRequest.setParameter("Locale", "Locale");
			renderRequest.setParameter("ResourceBundle", "bundle");
		
			advancedSearchForm.setCountries(countries);
			advancedSearchForm.setProvinces(provinces);
			advancedSearchForm.setStates(states);
			advancedSearchForm.setCities(cities);
			renderRequest.setAttribute("AdvancedSearchForm", "advancedSearchForm");
			mockSession.setAttribute("AdvancedSearchForm", advancedSearchForm);
			renderRequest.setSession(mockSession);
			target.showDeviceAdvancedSearchOptionPage(renderRequest, mockModel);
		
		
	}
	/**
	 * @throws Exception 
	 */ 
	@Test
	public void testshowServiceRequestConfirmationPage()throws Exception
	{
		
		
		String output=target.showServiceRequestConfirmationPage();
		assertEquals("OK", "serviceRequest/thankYou",
				output);
	
	
	}
	/**
	 * @throws Exception 
	 */
	@Test
	public void testshowServiceRequestDrillDownPrintPage()throws Exception
	{
		
		

		String output=target.showServiceRequestDrillDownPrintPage();
		assertEquals("OK", "serviceRequest/serviceRequestDrillDownPrint",
				output);
	}
	/**
	 * @throws Exception 
	 */
	@Test
	public void testshowThankYouEmailPage()throws Exception
	{
		
		
		String output=target.showThankYouEmailPage();
		assertEquals("OK", "serviceRequest/thankYouEmail",
				output);
	}
	/**
	 * @throws Exception 
	 */
	@Test
	public void testshowThankYouPrintPage()throws Exception
	{
		
		
		String output=target.showThankYouPrintPage();
		assertEquals("OK", "serviceRequest/thankYouPrint",
				output);
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void testshowServiceRequestConfirmationPrintPage()throws Exception
	{
		
		
		String output=target.showServiceRequestConfirmationPrintPage();
		assertEquals("OK", "serviceRequest/serviceRequestConfirmationPrint",
				output);
	}
	/**
	 * @throws Exception 
	 */
	@Test
	public void testShowPrintDeviceListPage()throws Exception
	{
		
		
		String output=target.showPrintDeviceListPage();
		assertEquals("OK", "deviceFinder/deviceListPrint",
				output);
	}
	/**
	 * @throws Exception 
	 */
	@Test
	public void testShowPrintDevicePage()throws Exception
	{
		
		
		String output=target.showPrintDevicePage();
		assertEquals("OK", "deviceFinder/deviceDetailsPrintPage",
				output);
		
	}
	/**
	 * @throws Exception 
	 */
	@Test
	public void testGotoControlPanel()throws Exception
	{
		
		renderRequest.setParameter("controlPanelURL","controlPanelURL");
		renderRequest.setParameter("pageName", "Device Finder");
	 
		 
		String output=target.gotoControlPanel( renderRequest, mockModel);
		assertEquals("OK", "controlPanelPage",
				output);
		
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void testRetrieveDeviceLocationTreeXML()throws Exception
	{
		
		renderRequest.setParameter("vendorAccountID","AccountID");
		
		Map<String,String> accDetails= new HashMap();
		accDetails.put("accountId", "1-28FWPH");
		accDetails.put("accountName", "AT&T Corporation");
		accDetails.put("accountOrganization", "Wipro");
		accDetails.put("agreementId", "Aggreement12345");
		accDetails.put("agreementName", "agreementName122121");
		accDetails.put("country", "India");
		LocationReportingHierarchyContract contract = new LocationReportingHierarchyContract();
		contract.setMdmId("64");
		contract.setMdmLevel("li");
		contract.setVendorFlag(true);
		contract.setEntitlementEndDate("23-78-2000");
		 mockSession.setAttribute("LocationReportingHierarchyContract",contract);
		reReq.setParameter("UserRoles", "userRoleList");
		 mockSession.setAttribute(LexmarkConstants.SERVICES_USER, servicesUser,
				 mockSession.APPLICATION_SCOPE);
	
		 mockSession.setAttribute("userRoleList",userRoleList);
		 mockSession.setAttribute(LexmarkConstants.LDAP_USER_DATA, ldapUserData,
				 mockSession.APPLICATION_SCOPE);
		 reReq.setSession(mockSession);
		
		String DATEFORMAT = "MM/dd/yyyy" ;
		SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		String utcTime = sdf.format(new Date());
		reReq.setParameter("utcTime","utcTime");
		reReq.setParameter("deviceLocType","deviceLocType");
		reReq.setParameter("AddressList", "result");
		
		target.retrieveDeviceLocationTreeXML( reReq, reRes,mockModel);
	}
	
	
	/**
	 * @throws Exception 
	 */
	@Test
	public void testShowEditContactPage()throws Exception
	{
		
		renderRequest.setParameter("contactId", "5768676");
		renderRequest.setParameter("lastName", "ffjh");
		renderRequest.setParameter("firstName", "bhjbjh");
		renderRequest.setParameter("workPhone", "65878787");
		renderRequest.setParameter("emailAddress", "sdd@hgjh.com");
		reReq.setParameter("gridName", "primary");
		
		if(gridName=="primary")
		{
		String output=target.showEditContactPage(renderRequest, renderResponse ,gridName, contactId, lastName, firstName, workPhone, emailAddress,mockModel);
		assertEquals("OK","serviceRequest/serviceRequestCreateNewContact",
				output);
		}
         reReq.setParameter("gridName", "secondary");
		
		if(gridName=="secondary")
		{
		String output1=target.showEditContactPage(renderRequest, renderResponse ,gridName, contactId, lastName, firstName, workPhone, emailAddress,mockModel);
		assertEquals("OK","serviceRequest/serviceRequestCreateNewContact",
				output1);
		}
        reReq.setParameter("gridName", "serviceAddress");
        if(gridName=="serviceAddress")
        {
		String output1=target.showEditContactPage(renderRequest, renderResponse ,gridName, contactId, lastName, firstName, workPhone, emailAddress,mockModel);
		assertEquals("OK","serviceRequest/serviceRequestCreateNewServiceAddress",
				output1);
        }
		
		//target.showEditContactPage( renderRequest, renderResponse ,gridName, contactId, lastName, firstName, workPhone, emailAddress,mockModel);
		
		
	}
	/**
	 * @throws Exception 
	 */
	@Test
	public void testshowCreateNewPage()throws Exception
	{
		
		String returnPage = "";
		CreateNewAddressForm createNewAddressForm = new CreateNewAddressForm();
		
		if(gridName.equals("primary")){
			returnPage = "serviceRequest/serviceRequestCreateNewContact";
		target.showCreateNewPage(renderRequest, renderResponse, gridName, mockModel);
		assertEquals("OK", "serviceRequest/serviceRequestCreateNewContact",
				returnPage);
		}
		else if(gridName.equals("secondary")){
			returnPage = "serviceRequest/serviceRequestCreateNewContact";
			target.showCreateNewPage(renderRequest, renderResponse, gridName, mockModel);
			assertEquals("OK", "serviceRequest/serviceRequestCreateNewContact",
					returnPage);
		}
			else if(gridName.equals("serviceAddress")){
				List<GeographyCountryContract> countries = new ArrayList<GeographyCountryContract>();
				GeographyCountryContract gcc=new GeographyCountryContract();
				gcc.setCountryCode("767878");
				gcc.setCountryName("usa");
				countries.add(gcc);
				GeographyListResult countryListResult = new GeographyListResult();
				countryListResult.setCountryList(countries);
				createNewAddressForm.setCountries(countries);
				returnPage = "serviceRequest/serviceRequestCreateNewAddress";
				
				target.showCreateNewPage(renderRequest, renderResponse, gridName, mockModel);
				assertEquals("OK", "serviceRequest/serviceRequestCreateNewAddress",
						returnPage);
			}
	
		target.showCreateNewPage(renderRequest, renderResponse, gridName, mockModel);
	}
	/**
	 * @throws Exception 
	 */
	@Test
	public void testShowDeviceListPage() throws Exception
	{
		
		renderRequest.setParameter("SEARCH_VALUE", "search value");
		renderRequest.setParameter("SEARCH_NAME","search name" );
		renderRequest.setParameter("DWNLD_DEVICELIST_URL","download devicelistUrl" );
	    renderRequest.setParameter("VIEWPARAM","view " );
	    renderRequest.setParameter("gridSettings","result " );
		mockSession.setAttribute("userAccessMapForSr", userAccessMapForSr,
				mockSession.APPLICATION_SCOPE);
		mockSession.setAttribute("requestAccessMap",requestAccessMap ,
				mockSession.APPLICATION_SCOPE);
		renderRequest.setSession(mockSession);
		String DATEFORMAT = "MM/dd/yyyy" ;
		SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		String utcTime = sdf.format(new Date());
		reReq.setParameter("utcTime","utcTime");
		renderRequest.setParameter("requestTypeLOVMap", "requestTypeLOVMap" );
		Map<String, String> lovMap = new LinkedHashMap<String, String>();
		
		lovMap.put("requestStatusLOVMap","requestStatusLOVMap");
		renderRequest.setParameter("requestStatusLOVMap", "true ");
		renderRequest.setParameter("hidePgCnts", "hidePgCnts ");
		renderRequest.setParameter("backToList", "true ");
		
		String output=target.showDeviceListPage(mockModel, renderRequest, renderResponse);
		assertEquals("OK", "deviceFinder/deviceFinder",
				output);
		
	}
	
		
	

	/**
	 * @throws Exception 
	 */
	@Test
	public void testShowDeviceDetailPage()throws Exception
	{
		
		
		
		String output =target.showDeviceDetailPage( renderRequest, renderResponse ,mockModel);
		assertEquals("OK", "deviceFinder/deviceFinder",
				output);
		
	}
	/**
	 * @throws Exception 
	 */
	@Test
	public void testRetriveDeviceList()throws Exception
	{
		
		reReq.setParameter("FILTER_CRITERIAS", "filter criterias");
		reReq.setParameter("viewType", "bookmarked");
		reReq.setParameter("userRoleList", "userRoleList");
		reReq.setParameter("imageSrc", "account.jpg");
		reReq.setParameter("imageName", "account"); 
		reReq.setParameter("ASSET_LIST_TYPE", "PrintSelection");
		reReq.setParameter("updateMeterReadsMsg", "updateMeterReadsMsg");
		reReq.setParameter("showViewMore", "showViewMore");
		Pagination page = new Pagination();
		AssetListContract contract =new AssetListContract();
		mockSession.setAttribute("AssetListContract",contract);
		reReq.setSession(mockSession);
		List<Asset> deviceList =new ArrayList<Asset>();
		Asset asset =new Asset();
		asset.setSerialNumber("1-2345678");
		asset.setProductTLI("Lexmark Product");
		asset.setModelNumber("Lexmark P 12");
		asset.setInstallDate(new Date("12/12/12"));
		asset.setIpAddress("10.141.27.100");
		asset.setHostName("HO304L30");
		asset.setAssetTag("0230485");
		asset.setAddressFlag(true);
		//deviceList.add(asset);
		AssetListResult deviceListResult =  new AssetListResult();
		deviceListResult.setAccountAssets(deviceList);
		deviceListResult.setTotalCount(totalCount);
	
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
		
		deviceListResult.setAccountAssets(deviceList);
	    Map<String, String> roleAccessMap= new HashMap<String, String>();
		roleAccessMap.put("CREATE SUPPLIES REQUEST","CREATE SUPPLIES REQUEST");
		roleAccessMap.put("CREATE_SERVICE_REQUEST","CREATE_SERVICE_REQUEST");
		roleAccessMap.put("CREATE CHANGE MGMT REQUEST","CREATE CHANGE MGMT REQUEST");
		reReq.setAttribute("deviceList", deviceList);
		reReq.setAttribute("deviceListResult", deviceListResult);
		mockSession.setAttribute("roleAccessMap", roleAccessMap,
				mockSession.APPLICATION_SCOPE);
	
	//	final String[] PAGE_COUNT_FILTER_COLUMNS =  new String[]{"serialNumber","serialNumber","productLine","assetTag","ipAddress", "installAddress.addressName", "meterReadDate", "lastPageCount","","lastColorPageCount","","buttonSave","installAddress.city","installAddress.state","installAddress.province","installAddress.country","hostName","macAddress","deviceTag","modelNumber","productTLI","physicalLocation1","assetContact.firstName"};
		List<String> reqTypeValues = new ArrayList<String>();
		reqTypeValues.add("Consumables Management");
		reqTypeValues.add("Fleet Management");
		reqTypeValues.add( "BreakFix");
		reReq.setParameter("reqTypeValues", "others");
		reReq.setParameter("requestedFrom", "ALL_REQUESTS");
		
		Map<String, Object>  filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("requestType", reqTypeValues);
		reReq.setAttribute("filterCriteria", filterCriteria);
		
		
		target.retriveDeviceList( reReq, reRes );
		
		
	}
	/**
	 * @throws Exception 
	 */
	@Test
	public void testRetrieveServiceRequestHistoryList()throws Exception
	{
		
		reReq.setParameter("assetRowId", "67686876");
		reReq.setParameter("accountRowId", "798788998");
		reReq.setParameter("serviceRequestNumber", "67789");
		reReq.setParameter("LexmarkConstants.TIMEZONE_OFFSET", "4.55");
		ServiceRequestHistoryListContract contract =  new ServiceRequestHistoryListContract();
		contract.setAssetId("2345");
		contract.setMdmID("2345");
		contract.setMdmLevel("2345");
		contract.setAccountId("2345");
		contract.setServiceRequestNumber("2345");
		contract.setStartRecordNumber(0);
		contract.setIncrement(2);
		
		List<String> reqTypeValues = new ArrayList<String>();
		reqTypeValues.add("Consumables Management");
		reqTypeValues.add("Fleet Management");
		reqTypeValues.add( "BreakFix");
		reReq.setParameter("reqTypeValues", "others");
		reReq.setParameter("requestedFrom", "ALL_REQUESTS");
		ServiceRequestListResult srlr= new ServiceRequestListResult();
	   
		srlr.setServiceRequests(serviceRequests);
		srlr.setTotalCount(5);
//		sortResult.add(serviceRequests);
		Map<String, Object>  filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("requestType", reqTypeValues);
		reReq.setAttribute("filterCriteria", filterCriteria);
		Map<String, Object> SearchCriteria = new HashMap<String, Object>();
		reReq.setAttribute("SearchCriteria", SearchCriteria);
		reReq.setAttribute("SearchCriteria", SearchCriteria);
		Map<String, Object> sortCriteria = new HashMap<String, Object>();
		sortCriteria.put("sortCriteria", sortCriteria);
		reReq.setAttribute("sortCriteria", sortCriteria);
		List properties = new ArrayList(1);
     
		properties.add(property);
		mockSession.setAttribute("ServiceRequestHistoryListContract", contract);
		reReq.setSession(mockSession);
		List<ServiceRequest> serviceRequestList=new ArrayList<ServiceRequest> ();
		ServiceRequest sr= new ServiceRequest();
		AccountContact accountContact = new AccountContact();
		Part part = new Part();
		GenericAddress genericAddress = new GenericAddress();
	    accountContact.setAddress(genericAddress);
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
		accountContact.setAlternatePhone("123124234234");
		accountContact.setContactId("122131");
		accountContact.setCountry("country");
		accountContact.setDepartment("department");
		accountContact.setEmailAddress("rahul@gmal.com");
		accountContact.setFirstName("firstName");
		accountContact.setLastName("lastName");
		accountContact.setManageContactFlag(true);
		accountContact.setMiddleName("middleName");
		accountContact.setNewContactFlag(true);
		accountContact.setShortId("gh677");
		accountContact.setWorkPhone("23423423423");
		accountContact.setUpdateContactFlag(true);
		accountContact.setUserFavorite(true);
		part.setPartId("12312312");
		part.setStatus("Authorized");
		part.setPartName("printer");
		part.setPartNumber("123456");
		List<Part> lp = new ArrayList<Part>();
		lp.add(part);
		sr.setParts(lp);
	    sr.setRequestor(accountContact);
		sr.setPrimaryContact(accountContact);
		sr.setSecondaryContact(accountContact);
		lov.setId("123");
		lov.setLanguage("english");
		lov.setLanguageName("en");
		lov.setName("rahul");
		lov.setType("add");
		lov.setValue("change request");
		lovList.add(lov);
		lovRes.setLovList(lovList);
		sr.setArea(lov);
		sr.setSubArea(lov);
	     sr.setCustomerReferenceNumber("67787");
		 sr.setOrderSource("orderSource");
		 sr.setExpediteOrder(true);
	     sr.setAccountId("5656");
	     sr.setProblemDescription("problemDescription");
	     sr.setAccountName("accountName");
	     sr.setArea(lov);
	     sr.setSubArea(lov);
	     sr.setStatusType(lov);
	     serviceRequestList.add(sr);
		

		 reReq.setAttribute("ServiceRequest", sr);
		 reReq.setAttribute("serviceRequestList", serviceRequestList);
			
		target.retrieveServiceRequestHistoryList( reReq, reRes ,mockModel);
	}
		/**
		 * @throws Exception 
		 */
		@Test
		public void testGetState()throws Exception
		{
			
			reReq.setParameter("countryCode", "56");
			List<GeographyStateContract> states= new ArrayList<GeographyStateContract>();
		
			GeographyStateContract gc=new GeographyStateContract();
			gc.setStateName("WB");
			gc.setStateCode("56");
			states.add(gc);
			GeographyListResult countryListResult =new GeographyListResult();
			countryListResult.setStateList(states);
			//countryListResult.getStateList();
			target.getState( reReq, reRes ,mockModel);
			
	} 
		/**
		 * @throws Exception 
		 */
		@Test
		public void testDownloadDeviceListURL()throws Exception
		{
			
		//	reReq.setParameter("downloadType", "downloadType");
			reReq.setParameter("Locale", "Locale");
			reReq.setParameter("TimeZone", "TimeZone");
			reReq.setParameter("utcTime","utcTime");
			List<Asset> deviceList =new ArrayList<Asset>();
			Asset asset =new Asset();
			asset.setSerialNumber("1-2345678");
			asset.setProductTLI("Lexmark Product");
			asset.setModelNumber("Lexmark P 12");
			asset.setInstallDate(new Date("12/12/12"));
			asset.setIpAddress("10.141.27.100");
			asset.setHostName("HO304L30");
			asset.setAssetTag("0230485");
			asset.setAddressFlag(true);
			
			Account account = new Account();
	        asset.setAccount(account);
	        account.setAccountId("");
	        account.setAccountName("");
	        AccountContact primaryContact = new AccountContact();
			primaryContact.setFirstName("Chick");
			primaryContact.setLastName("Dpian");
			primaryContact.setWorkPhone("1234567890");
			primaryContact.setEmailAddress("abc@mymail.com");
	        asset.setAccount(account);
	        asset.setAssetContact(primaryContact);
	        account.setAssetExpediteFlag(true);
	       // account.setShowPriceFlag(true);
	        account.setPoNumberFlag(true);
	        account.setCreditNumberFlag(true);
	      
	        GenericAddress serviceAddress = new GenericAddress();
			serviceAddress.setStoreFrontName("ABC agency");
			serviceAddress.setAddressLine1("123 lake road");
			serviceAddress.setAddressLine2("Apt 10");
			serviceAddress.setAddressLine3("Opp to Walmart");
			serviceAddress.setCity("Lexington");
			serviceAddress.setState("Kentucky");
			serviceAddress.setProvince("MN");
			serviceAddress.setPostalCode("40111");
			serviceAddress.setCountry("USA");
			serviceAddress.setPhysicalLocation1("Lex-082");
			serviceAddress.setPhysicalLocation2("2");
			serviceAddress.setPhysicalLocation3("Lexmark");
		
			deviceList.add(asset);
			AssetListResult deviceListResultt=new AssetListResult() ;
		
			deviceListResultt.setAccountAssets(deviceList);
			reReq.setParameter("devicelist","devicelist");
			reReq.setParameter("downloadType", "csv");
			reReq.setAttribute("AssetListResult", deviceListResultt);
	
			target.downloadDeviceListURL( reReq, reRes);
			
			reReq.setParameter("downloadType", "pdf");
			target.downloadDeviceListURL( reReq, reRes);
			
		
			
			
			
			target.downloadDeviceListURL( reReq, reRes);
		}

	

		/**
		 * @throws Exception 
		 */
		@Test
		public void testRetrieveCHLTreeXML()throws Exception
		{
			
			reReq.setParameter("nodeId", "666778");
			LocationReportingHierarchyContract contract =new LocationReportingHierarchyContract();
			mockSession.setAttribute("LocationReportingHierarchyContract",contract);
			List<CHLNode> childNodeList = new ArrayList<CHLNode>();
			CHLNode node=new CHLNode();
			node.setCHLNodeId("34");
			node.setChlNodeName("krish");
			node.setChlParentId("456");
			
			childNodeList.add(node);
			AssetReportingHierarchyResult result =new AssetReportingHierarchyResult();
			result.setChlNodeList( chlNodeList);
			 mockSession.setAttribute("CHLNode",node);
			 mockSession.setAttribute(LexmarkConstants.SERVICES_USER, servicesUser,
					 mockSession.APPLICATION_SCOPE);
		
			
			 mockSession.setAttribute(LexmarkConstants.LDAP_USER_DATA, ldapUserData,
					 mockSession.APPLICATION_SCOPE);
			 reReq.setSession(mockSession);
			
			target.retrieveCHLTreeXML( reReq, reRes ,mockModel);
			
	} 
	/**
	 * @throws Exception 
	 */
	@Test
		public void testRetrieveServiceRequestListByAssetId()throws Exception
		{
			
			reReq.setParameter("assetRowId", "66767");
			reReq.setParameter("accountRowId", "4542");
			reReq.setParameter("serviceRequestNumber", "86655");
			
			ListOfValues lov=new ListOfValues();
			lov.setValue("56565");
			List<ServiceRequest> serviceRequestList = new ArrayList<ServiceRequest>() ;
			ServiceRequest sr=new ServiceRequest();
			sr.setStatusType(lov);
		    sr.setServiceRequestNumber("67677887");
		    sr.setServiceRequestDate(new Date(4/5/2013));
		    sr.setProblemDescription("problemDescription");
		    sr.setResolutionCode("67677");
		    sr.setServiceRequestDate(new Date(12/7/2013));
		    sr.setServiceRequestStatus("true");
		
		    serviceRequestList.add(sr);
		   ServiceAddress sa= new ServiceAddress();
		   sa.setAddressLine1("Asia");
		   sa.setCity("kolkata");
		   sa.setState("WB");
		   sa.setCountry("India");
		
		   
		  PrimaryContact pc=new PrimaryContact();
			pc.setFirstName("rahul");
			pc.setWorkPhone("67787878");
			pc.setEmailAddress("rahul@gmail.com");
		   
		   
			LocalizedServiceStatusResult result = new LocalizedServiceStatusResult();
			result.setLocalizedString("RequestStatusEnum");
			
			LocalizedServiceStatusContract contract= new LocalizedServiceStatusContract();
			contract.setLocale(locale);
			contract.setSiebelValue("siebelValue");
			reReq.setAttribute("serviceRequestList", serviceRequestList);
				reReq.setAttribute("LocalizedServiceStatusContract", serviceRequestList);
			 mockSession.setAttribute(LexmarkConstants.SERVICES_USER, servicesUser,
						mockSession.APPLICATION_SCOPE);
			 mockSession.setAttribute(LexmarkConstants.LDAP_USER_DATA, ldapUserData,
					 mockSession.APPLICATION_SCOPE);
			 reReq.setSession(mockSession);
			 List<String> reqTypeValues = new ArrayList<String>();
				reqTypeValues.add("Consumables Management");
				reqTypeValues.add("Fleet Management");
				reqTypeValues.add( "BreakFix");
				reReq.setParameter("reqTypeValues", "others");
				reReq.setParameter("requestedFrom", "ALL_REQUESTS");
				
				Map<String, Object>  filterCriteria = new HashMap<String, Object>();
				filterCriteria.put("requestType", reqTypeValues);
				reReq.setAttribute("filterCriteria", filterCriteria);
			target.retrieveServiceRequestListByAssetId( reReq, reRes ,mockModel);
		}

/**
 * @throws Exception 
 */
@Test
public void testRetrieveAssociatedServiceTicketList()throws Exception
{
	
	
	
	reReq.setParameter("serviceRequestNumber", "4564322");
	reReq.setParameter("timezoneOffset", "4.55");
	
	List<ServiceRequest> serviceRequestList = new ArrayList<ServiceRequest>() ;
	ServiceRequest sr=new ServiceRequest();
	sr.setStatusType(lov);
    sr.setServiceRequestNumber("67677887");
    sr.setServiceRequestDate(new Date(4/5/2013));
    sr.setProblemDescription("problemDescription");
    sr.setResolutionCode("67677");
    sr.setServiceRequestDate(new Date(12/7/2013));
    sr.setServiceRequestStatus("true");

    serviceRequestList.add(sr);
   ServiceAddress sa= new ServiceAddress();
   sa.setAddressLine1("Asia");
   sa.setCity("kolkata");
   sa.setState("WB");
   sa.setCountry("India");

   
  PrimaryContact pc=new PrimaryContact();
	pc.setFirstName("rahul");
	pc.setWorkPhone("67787878");
	pc.setEmailAddress("rahul@gmail.com");
	 mockSession.setAttribute(LexmarkConstants.SERVICES_USER, servicesUser,
			 mockSession.APPLICATION_SCOPE);

	
	 mockSession.setAttribute(LexmarkConstants.LDAP_USER_DATA, ldapUserData,
			 mockSession.APPLICATION_SCOPE);
	 reReq.setSession(mockSession);
	 List<String> reqTypeValues = new ArrayList<String>();
		reqTypeValues.add("Consumables Management");
		reqTypeValues.add("Fleet Management");
		reqTypeValues.add( "BreakFix");
		reReq.setParameter("reqTypeValues", "others");
		reReq.setParameter("requestedFrom", "ALL_REQUESTS");
		
		Map<String, Object>  filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("requestType", reqTypeValues);
		reReq.setAttribute("filterCriteria", filterCriteria);
		reReq.setAttribute("serviceRequestList", serviceRequestList);
	target.retrieveAssociatedServiceTicketList( reReq, reRes ,mockModel);
}		
/**
 * @throws Exception 
 */
@Test
public void testRetrievePrimaryContactListURL()throws Exception
{
	
	
	reReq.setParameter("searchCriterias", "history");
	reReq.setParameter("contextPath", "path");
	ContactListContract contract = new ContactListContract();
	  contract.setMdmId("12864");
		contract.setMdmLevel("Legal");
   	contract.setContactId("1-UEH6FU");
    mockSession.setAttribute("ContactListContract",contract);
    ContactListContract contactListContract =new ContactListContract ();
    contactListContract.setContactId("56878889");
	contactListContract.setFavoriteFlag(true);
	contactListContract.setContactId("6678");
	  mockSession.setAttribute("ContactListContract",contactListContract);
	 mockSession.setAttribute(LexmarkConstants.SERVICES_USER, servicesUser,
			 mockSession.APPLICATION_SCOPE);

	
	 mockSession.setAttribute(LexmarkConstants.LDAP_USER_DATA, ldapUserData,
			 mockSession.APPLICATION_SCOPE);
	 reReq.setSession(mockSession);
	 List<String> reqTypeValues = new ArrayList<String>();
		reqTypeValues.add("Consumables Management");
		reqTypeValues.add("Fleet Management");
		reqTypeValues.add( "BreakFix");
		reReq.setParameter("reqTypeValues", "others");
		reReq.setParameter("requestedFrom", "ALL_REQUESTS");
		
		Map<String, Object>  filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("requestType", reqTypeValues);
		reReq.setAttribute("filterCriteria", filterCriteria);
	target.retrievePrimaryContactListURL( reReq, reRes ,mockModel);
}		
/**
 * @throws Exception 
 */
@Test
public void testRetrieveSecondaryContactListURL()throws Exception
{
	
	
	reReq.setParameter("searchCriterias", "history");
	reReq.setParameter("contextPath", "path");
	 mockSession.setAttribute(LexmarkConstants.SERVICES_USER, servicesUser,
			 mockSession.APPLICATION_SCOPE);

	
	 mockSession.setAttribute(LexmarkConstants.LDAP_USER_DATA, ldapUserData,
			 mockSession.APPLICATION_SCOPE);
	 reReq.setSession(mockSession);
	 ContactListResult contactListResult =new ContactListResult();
	 List<String> reqTypeValues = new ArrayList<String>();
		reqTypeValues.add("Consumables Management");
		reqTypeValues.add("Fleet Management");
		reqTypeValues.add( "BreakFix");
		reReq.setParameter("reqTypeValues", "others");
		reReq.setParameter("requestedFrom", "ALL_REQUESTS");
		
		Map<String, Object>  filterCriteria = new HashMap<String, Object>();
		filterCriteria.put("requestType", reqTypeValues);
		reReq.setAttribute("filterCriteria", filterCriteria);
	
	target.retrieveSecondaryContactListURL( reReq, reRes ,mockModel);
}	
/**
 * @throws Exception 
 */ 
@Test
public void testShowPastServiceRequestHistoryPage()throws Exception
{
	
	
	reReq.setParameter("serviceRequestsXML", "serviceRequestsXML");
	reReq.setParameter("createServiceRequestFlag", "true");
	reReq.setParameter("serviceRequestHistoryListXML", "serviceRequestHistoryListXML");
	reReq.setParameter("consumableAssetFlag", "true");
	DeviceDetailForm deviceDetailForm = new DeviceDetailForm();
	Asset device=new Asset();
	device.setSerialNumber("1-2345678");
	device.setProductTLI("Lexmark Product");
	device.setModelNumber("Lexmark P 12");
	device.setInstallDate(new Date("12/12/12"));
	device.setIpAddress("10.141.27.100");
	device.setHostName("HO304L30");
	device.setAssetTag("0230485");
	device.setAddressFlag(true);
	deviceDetailForm.setDevice(device);
	reReq.setParameter("DeviceDetailForm", "deviceDetailForm");
    mockSession.setAttribute("DeviceDetailForm", deviceDetailForm);
     reReq.setSession(mockSession);
	
	
	 mockSession.setAttribute(LexmarkConstants.SERVICES_USER, servicesUser,
			 mockSession.APPLICATION_SCOPE);

	
	 mockSession.setAttribute(LexmarkConstants.LDAP_USER_DATA, ldapUserData,
			 mockSession.APPLICATION_SCOPE);
	 reReq.setSession(mockSession);
	
	String output=target.showPastServiceRequestHistoryPage( renderResponse ,mockModel);
	assertEquals("OK", "serviceRequest/serviceRequestHistoryPage",
			output);
	
	
}	
/**
 * @throws Exception 
 */
@Test
public void testRetrieveServiceAddressListURL()throws Exception
{
	
	AddressListContract contract = new AddressListContract();
	contract.setMdmId("6768778");
	contract.setMdmId("l1");
	contract.setFavoriteFlag(true); 
	contract.setContactId("67989809");
	mockSession.setAttribute("AddressListContract",contract);
	AddressListContract addressListContract=new AddressListContract();
	
	addressListContract.setContactId("7890990");
	addressListContract.setFavoriteFlag(true);
	mockSession.setAttribute("AddressListContract",addressListContract);
	mockSession.setAttribute(LexmarkConstants.SERVICES_USER, servicesUser,
			 mockSession.APPLICATION_SCOPE);
	 mockSession.setAttribute("accountCurrentDetails", accDetails,
			 mockSession.APPLICATION_SCOPE);
		mockSession.setAttribute("USERROLES", userRoleList,
				mockSession.APPLICATION_SCOPE);
	
	 mockSession.setAttribute(LexmarkConstants.LDAP_USER_DATA, ldapUserData,
			 mockSession.APPLICATION_SCOPE);
	 reReq.setSession(mockSession);
	reReq.setParameter("isPopup", "filleddata");
	reReq.setParameter("contextPath", "path");
	reReq.setParameter("Locale", "locale");
	reReq.setParameter("searchCriterias", "account");
	
	List<String> reqTypeValues = new ArrayList<String>();
	reqTypeValues.add("Consumables Management");
	reqTypeValues.add("Fleet Management");
	reqTypeValues.add( "BreakFix");
	reReq.setParameter("reqTypeValues", "others");
	reReq.setParameter("requestedFrom", "ALL_REQUESTS");
	
	Map<String, Object>  filterCriteria = new HashMap<String, Object>();
	filterCriteria.put("requestType", reqTypeValues);
	reReq.setAttribute("filterCriteria", filterCriteria);
//	if(searchCriterias=="account")
	target.retrieveServiceAddressListURL( reReq, reRes ,mockModel);
}
}
//	reReq.setParameter("searchCriterias", "null");
//	if(searchCriterias=="null")
	//target.retrieveServiceAddressListURL( reReq, reRes ,mockModel);
	
	
	//target.retrieveServiceAddressListURL( reReq, reRes ,mockModel);




/*
 * Hard coded place--------retrieveHistoryListByAssetId()
 * 	1)ContrctFactory getAssetContract()
 * 
 * */

/*
 * Hard coded place--------updateUserFavoriteAsset()
 * 	1)FavoriteResult  isResult()
 * 
 * */

/**
 * Test Cases End
 **/















